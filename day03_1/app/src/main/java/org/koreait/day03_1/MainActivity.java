package org.koreait.day03_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visibility = imageView.getVisibility();
                // 첫번째 이미지가 보이면, 첫번째 이미지 감춤, 두번째 이미지 보임
                if (visibility == View.VISIBLE) {
                    imageView.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                } else {
                    // 첫번째 이미지 보임, 두번째 이미지 감춤
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}