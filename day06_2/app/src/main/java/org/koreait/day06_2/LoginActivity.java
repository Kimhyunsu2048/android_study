package org.koreait.day06_2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("userId");
        String userPw = intent.getStringExtra("userPw");

        textView.setText(userId);
        textView2.setText(userPw);

    }
}