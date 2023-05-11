package org.koreait.day09_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsViewActivity2 extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_view2);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                processIntent(intent);
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        String sender = intent.getStringExtra("sender");
        String content = intent.getStringExtra("content");

        textView.setText(sender);
        textView2.setText(content);

    }
}