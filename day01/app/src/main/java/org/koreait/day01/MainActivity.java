package org.koreait.day01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 인플레이션 이후(객체화 이후)
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // MainActivity.this
                // getApplicationContext()

                Toast.makeText(getApplicationContext(), "안녕하세요!", Toast.LENGTH_LONG).show();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"));
                startActivity(intent);
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-0000-0000"));
                startActivity(intent);
            }
        });

    }

    public void onButtonClick1(View v) {
        Toast.makeText(this, "안녕하세요!", Toast.LENGTH_LONG).show();
    }
}