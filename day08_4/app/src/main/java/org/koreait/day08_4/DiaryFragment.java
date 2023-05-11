package org.koreait.day08_4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.koreait.day08_4.constants.ApiURL;
import org.koreait.day08_4.constants.Menus;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class DiaryFragment extends Fragment {

    private Button diaryListBtn;
    private Button diaryWriteBtn;
    private Button diaryPhotoBtn;

    private ImageView diaryPhoto;

    private EditText diarySubject;
    private EditText diaryContent;

    private File file;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_diary, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (!SessionData.isLogin()) { // 미로그인 상태
            mainActivity.changeMenu(Menus.LOGIN);
        }

        diaryListBtn = viewGroup.findViewById(R.id.diary_list_btn);
        diaryWriteBtn = viewGroup.findViewById(R.id.diary_write_btn);
        diaryPhotoBtn = viewGroup.findViewById(R.id.diary_photo_btn);

        diaryPhoto = viewGroup.findViewById(R.id.diary_photo);

        diarySubject = viewGroup.findViewById(R.id.diary_subject);
        diaryContent = viewGroup.findViewById(R.id.diary_content);

        diaryPhotoBtn.setOnClickListener(new View.OnClickListener() { // 사진 촬영
            @Override
            public void onClick(View view) {
                mainActivity.takePicture();
            }
        });

        diaryListBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mainActivity.changeMenu(Menus.DIARY_LIST);
            }
        });

        diaryWriteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                /**
                 * 1. 유효성 검사 - 필수항목 체크(제목, 내용)
                 * 2. 작성 요청(REST 서버)
                 * 3. 일기 목록(프레그먼트) 변경 -> 일기 보기 페이지(액티비티)
                 */
                try {
                    // 1. 유효성 검사
                    requiredCheck();

                    // 2. 작성 요청(Rest 서버)
                    mainActivity.request(ApiURL.DIARY_URL, "POST", getData(), new RequestCallback() {
                        @Override
                        public void process(String response) {
                            //Log.d("DIARY_WRITE", response);
                            ObjectMapper om = new ObjectMapper();
                            try {
                                JSONResult<Integer> jsonResult = om.readValue(response, JSONResult.class);
                                if (jsonResult.isSuccess()) { // 성공시 처리
                                    // 입력 항목 초기화
                                    diarySubject.setText("");
                                    diaryContent.setText("");

                                    // 일기 목록(프레그먼트) 변경 -> 일기 보기 페이지(액티비티)
                                    int diaryId = jsonResult.getData();
                                    mainActivity.changeMenu(Menus.DIARY_LIST);

                                    Intent intent = new Intent(getContext(), DiaryViewActivity.class);
                                    intent.putExtra("diaryId", diaryId);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);


                                } else { // 실패시 처리
                                    Toast.makeText(getContext(), "일기 작성에 실패하였습니다.", Toast.LENGTH_LONG).show();
                                }
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                } catch (RuntimeException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        return viewGroup;
    }

    private void requiredCheck() {
        Map<String, String> fields = new HashMap<>();
        fields.put("subject", "제목");
        fields.put("content", "내용");

        for(Map.Entry<String, String> entry : getData().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.isEmpty()) {
                throw new RuntimeException(fields.get(key) + "을 입력하세요.");
            }
        }
    }

    private Map<String, String> getData() {
        Map<String, String> params = new HashMap<>();
        params.put("subject", diarySubject.getText().toString().trim());
        params.put("content", diaryContent.getText().toString().trim());

        User user = SessionData.user;
        params.put("userId", user.getUserId());

        if (file != null) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                while(bis.available() > 0) {
                    bos.write(bis.read());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] bytes = bos.toByteArray();
            String imageData = Base64.encodeToString(bytes, Base64.NO_WRAP);

            params.put("imageData", imageData);
        }

        return params;

    }

    public void updateImage(Bitmap bitmap, File file) {
        diaryPhoto.setImageBitmap(bitmap);
        this.file = file;
    }
}