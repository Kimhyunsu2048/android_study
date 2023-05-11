package org.koreait.day06_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        showMessage(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this, "onNewIntent", Toast.LENGTH_LONG).show();
        // SingleTop : onCreate() 1번만 호출
        // Intent가 onCreate() 일때만 접근 가능
        // 액티비티를 재활용하는 경우 onCreate() 호출 X -> onNewIntent로 Intent 데이터가 공유
        showMessage(intent);
    }

    private void showMessage(Intent intent) {
        String message = intent.getStringExtra("message");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}