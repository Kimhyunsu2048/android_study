package org.koreait.day06_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PopupActivity extends AppCompatActivity {

    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("greeting", "반갑습니다.");

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}