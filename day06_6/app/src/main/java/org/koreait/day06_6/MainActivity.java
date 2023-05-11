package org.koreait.day06_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setUserId("user01");
                user.setUserNm("사용자01");
                user.setEmail("email");
                user.setRegDt(LocalDateTime.now());

                Intent intent = new Intent(MainActivity.this, MemberActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);

            }
        });
    }
}