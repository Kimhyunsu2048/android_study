package org.koreait.day04_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private int backbtnCnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                EditText text = (EditText)view;
                String str = text.getText().toString();
                textView.setText(str);
                return true;
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                // 입력 항목을 클릭 - true
                // 입력 항목에서 나왔을때(blur) - false
                Toast.makeText(getApplicationContext(), "상태 : " + focused, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            backbtnCnt++;
            if (backbtnCnt == 2) {
                Toast.makeText(this, "정말 종료 하시겠습니까?", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        return true;
    }
}