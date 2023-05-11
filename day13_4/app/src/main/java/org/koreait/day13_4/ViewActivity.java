package org.koreait.day13_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    private TextView textView4;
    private TextView textView5;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        Item item = bundle.getParcelable("item");

        textView4.setText(item.getTitle());
        textView5.setText(item.getBody());
    }
}