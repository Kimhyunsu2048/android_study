package org.koreait.day02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText userId;
    private EditText userPw;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.exam02);

        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userId = userId.getText().toString();
                String _userPw = userPw.getText().toString();

                String msg = "아이디 : " + _userId + ", 비밀번호" + _userPw;
                Toast.makeText(getApplicationContext(),msg, Toast.LENGTH_LONG).show();

            }
        });
    }
}