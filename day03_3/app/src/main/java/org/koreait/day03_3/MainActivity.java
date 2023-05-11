package org.koreait.day03_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button2;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.exam01);

        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                textView.setMaxLines(10);
            }
        });
    }
}