package org.koreait.day10_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private CustomLayout customLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customLayout = findViewById(R.id.custom_layout);
        customLayout.setSubject("글 제목!!!!");
        customLayout.setContent("글 내용!!!!");
        customLayout.setImage(R.drawable.ic_launcher_background);
    }
}