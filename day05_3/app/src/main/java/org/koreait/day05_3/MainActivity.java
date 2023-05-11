package org.koreait.day05_3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private Button[] buttons;

    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setMin(0);

        buttons = new Button[6];
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);

        textView2 = findViewById(R.id.textView2);

        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.incrementProgressBy(1);
                updateProgress();
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.incrementProgressBy(-1);
                updateProgress();
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.incrementProgressBy(10);
                updateProgress();
            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.incrementProgressBy(-10);
                updateProgress();
            }
        });

        buttons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int center = Math.round(progressBar.getMax() / 2);
                progressBar.setProgress(center);
                updateProgress();
            }
        });

        buttons[5] = findViewById(R.id.button6);
        buttons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                dialog.setMessage("처리중 입니다....");

                dialog.show();

                dialog.setProgress(50);
            }
        });

    }

    private void updateProgress() {
        String text = "현재 위치 : " + progressBar.getProgress();
        textView2.setText(text);
    }
}