package org.koreait.day06_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button button; // 상단 메뉴
    private Button button2; // 하단 메뉴

    private Button button5;
    private EditText editText;
    private EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button5 = findViewById(R.id.button5);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() { // 상단 메뉴 클릭
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TopMenuActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BottomMenuActivity.class);
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = editText.getText().toString();
                String userPw = editText2.getText().toString();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.putExtra("userId", userId);
                intent.putExtra("userPw", userPw);

                startActivity(intent);
            }
        });
    }
}