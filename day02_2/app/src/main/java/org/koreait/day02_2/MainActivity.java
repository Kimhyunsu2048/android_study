package org.koreait.day02_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        Button button = new Button(this);
        button.setText("확인!");
        button.setLayoutParams(params);

        mainLayout.addView(button);
        EditText editText = new EditText(this);
        editText.setLayoutParams(params);
        editText.setHint("아무거나 입력....");

        mainLayout.addView(editText);

        setContentView(mainLayout);

        //setContentView(R.layout.activity_main);
    }
}