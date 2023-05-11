package org.koreait.day03_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources res = getResources();
                BitmapDrawable bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
                imageView.setImageDrawable(bitmap);

                int width = bitmap.getIntrinsicWidth(); // 실제 너비(px)
                int height = bitmap.getIntrinsicHeight(); // 실제 높이(px)

                String text = width + "px X " + height + "px";
                textView.setText(text);
            }
        });
    }
}