package org.koreait.day07_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Member member = new Member();
                member.setUserId("user01");
                member.setUserNm("사용자01");
                member.setUserPw("123456");
                member.setRegDt(LocalDateTime.now());

                Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                intent.putExtra("member", member);
                startActivity(intent);
            }
        });
    }
}