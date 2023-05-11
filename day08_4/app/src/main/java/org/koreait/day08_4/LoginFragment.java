package org.koreait.day08_4;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.koreait.day08_4.constants.ApiURL;
import org.koreait.day08_4.constants.Menus;

import java.util.HashMap;
import java.util.Map;


public class LoginFragment extends Fragment {

    private Button loginBtn;
    private EditText userIdField;
    private EditText userPwField;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_login, container, false);

        loginBtn = viewGroup.findViewById(R.id.login_btn);
        userIdField = viewGroup.findViewById(R.id.login_userId);
        userPwField = viewGroup.findViewById(R.id.login_userPw);

        MainActivity mainActivity = (MainActivity) getActivity();

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /**
                 * 1. 유효성 검사 - 필수 데이터
                 * 2. 안드로이드 -> 데이터 -> 서버 -> 회원 데이터 전송
                 * 3. 로그인 처리 - SharedPreference 저장
                 * 4. onResume
                 */
                try {
                    // 1. 유효성 검사
                    checkRequired();

                    // 2. 데이터 전송
                    mainActivity.request(ApiURL.LOGIN_URL, "POST", getData(), new RequestCallback() {
                        @Override
                        public void process(String response) {
                            ObjectMapper om = new ObjectMapper();
                            try {
                                Log.d("LOGIN_RESPONSE", response);
                                JSONResult<String> jsonResult = om.readValue(response, JSONResult.class);
                                if (jsonResult.isSuccess()) { // 로그인한 회원 데이터 수신 성공
                                    // 로그인 처리
                                    String userId = jsonResult.getData();
                                    SharedPreferences pref = getContext().getSharedPreferences("pref", Activity.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("userId", userId);
                                    editor.commit();

                                    // 로그인한 회원 정보 갱신
                                    mainActivity.updateUserInfo();

                                    // 아이디, 비밀번호 항목 초기화
                                    userIdField.setText("");
                                    userPwField.setText("");

                                    // 페이지 이동 - 메인페이지
                                    mainActivity.changeMenu(Menus.MAIN);

                                } else { // 로그인 실패 -> 메세지 출력
                                    Toast.makeText(getContext(), jsonResult.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }


                        }
                    });

                } catch (RuntimeException e) {
                    String message = e.getMessage();
                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
                }


            }
        });

        return viewGroup;
    }

    /**
     * 유효성 검사
     *
     */
    private void checkRequired() {
        if (getData("userId").isEmpty()) {
            throw new RuntimeException("아이디를 입력하세요.");
        }

        if (getData("userPw").isEmpty()) {
            throw new RuntimeException("비밀번호를 입력하세요.");
        }
    }

    private String getData(String key) {
        Map<String, String> params = getData();

        return params.get(key);
    }

    private Map<String, String> getData() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", userIdField.getText().toString().trim());
        params.put("userPw", userPwField.getText().toString().trim());

        return params;
    }
}