package org.koreait.day01_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = findViewById(R.id.linearlayout); // ViewGroup // View
        ViewGroup v1 = layout;
        View v2 = layout;

        TextView textView = findViewById(R.id.textview); // View

        View v3 = textView;
    }
}