package org.koreait.day08_4;

import android.os.Bundle;

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

public class JoinFragment extends Fragment {

    private EditText[] joinFields;
    private Button joinBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_join, container, false);

        joinFields = new EditText[4];

        joinBtn = viewGroup.findViewById(R.id.join_btn);
        joinFields[0] = viewGroup.findViewById(R.id.join_userId);
        joinFields[1] = viewGroup.findViewById(R.id.join_userPw);
        joinFields[2] = viewGroup.findViewById(R.id.join_userPwRe);
        joinFields[3] = viewGroup.findViewById(R.id.join_userNm);

        MainActivity mainActivity = (MainActivity) getActivity();

        joinBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /**
                 * 1. 유효성 검사
                 * 2. 회원 가입 처리
                 * 3. 로그인 메뉴 이동 
                 */

                try {
                    // 1. 유효성 검사
                    checkRequired();

                    // 2. 회원 가입 처리
                    mainActivity.request(ApiURL.JOIN_URL, "POST", getData(), new RequestCallback() {
                        @Override
                        public void process(String response) {
                            ObjectMapper om = new ObjectMapper();
                            try {
                                JSONResult<Object> jsonResult = om.readValue(response, JSONResult.class);

                                if (jsonResult.isSuccess()) { // 가입 성공
                                    // 성공 처리 - 입력 값 초기화, 로그인 메뉴로 이동
                                    Toast.makeText(getContext(), "가입되었습니다.", Toast.LENGTH_LONG).show();

                                    initializeFieldData();
                                    mainActivity.changeMenu(Menus.LOGIN);

                                } else { // 가입 실패
                                    String message = jsonResult.getMessage();
                                    Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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
     * 필수 데이터 검증 + 비번 일치 여부
     */
    private void checkRequired() {
        Map<String, String> fields = new HashMap<>();
        fields.put("userId", "아이디");
        fields.put("userPw", "비밀번호");
        fields.put("userPwRe", "비밀번호 확인");
        fields.put("userNm", "회원명");

        Map<String, String> params = getData();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            if (value.isEmpty()) { // 필수 데이터가 없는 경우
                throw new RuntimeException(fields.get(field) + "을(를) 입력하세요.");
            }
        }

        // 비번 일치 여부
        String userPw = params.get("userPw");
        String userPwRe = params.get("userPwRe");
        if (!userPw.equals(userPwRe)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Map<String, String> getData() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", joinFields[0].getText().toString().trim());
        params.put("userPw", joinFields[1].getText().toString().trim());
        params.put("userPwRe", joinFields[2].getText().toString().trim());
        params.put("userNm", joinFields[3].getText().toString().trim());

        return params;
    }

    /**
     * 입력 항목 초기화
     *
     */
    private void initializeFieldData() {
        for (EditText field : joinFields) {
            field.setText("");
        }
    }
}