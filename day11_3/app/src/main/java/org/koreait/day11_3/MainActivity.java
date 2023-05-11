package org.koreait.day11_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                //Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate);
                //Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);
                view.startAnimation(anim);
            }
        });
    }
}