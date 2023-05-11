package org.koreait.day11_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    private ImageView imageView2;
    private TextView textView4;
    private TextView textView5;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        imageView2 = findViewById(R.id.imageView2);
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
        PostData item = bundle.getParcelable("item");

        imageView2.setImageResource(item.getImageId());
        textView4.setText(item.getSubject());
        textView5.setText(item.getContent());
    }
}