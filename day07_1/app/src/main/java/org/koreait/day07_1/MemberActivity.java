package org.koreait.day07_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MemberActivity extends AppCompatActivity {

    private TextView[] textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        textViews = new TextView[4];

        textViews[0] = findViewById(R.id.textView);
        textViews[1] = findViewById(R.id.textView2);
        textViews[2] = findViewById(R.id.textView3);
        textViews[3] = findViewById(R.id.textView4);

        Intent intent = getIntent();
        processData(intent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processData(intent);
    }

    private void processData(Intent intent) {
        Bundle bundle = intent.getExtras();
        Member member = bundle.getParcelable("member");

        textViews[0].setText(member.getUserId());
        textViews[1].setText(member.getUserPw());
        textViews[2].setText(member.getUserNm());
        textViews[3].setText(member.getRegDt().toString());
    }
}