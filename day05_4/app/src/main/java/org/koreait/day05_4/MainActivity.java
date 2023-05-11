package org.koreait.day05_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private LinearLayout subContainer;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subContainer = findViewById(R.id.sub_container);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                
                // R.layout.sub의 객체화 전 - 선택불가
                
                inflater.inflate(R.layout.sub, subContainer, true); // attachRoot
                
                // R.layout.sub의 객체화 후

                CheckBox[] checkBoxes = new CheckBox[3];
                checkBoxes[0] = subContainer.findViewById(R.id.checkBox);
                checkBoxes[1] = subContainer.findViewById(R.id.checkBox2);
                checkBoxes[2] = subContainer.findViewById(R.id.checkBox3);

                TextView textView3 = subContainer.findViewById(R.id.textView3);
                List<String> checkedMenus = new ArrayList<>();
                for (CheckBox checkbox : checkBoxes) {
                    checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton button, boolean checked) {

                            String menu = button.getText().toString();

                            if (checked) { // 체크박스가 체크된 상태 일때
                                checkedMenus.add(menu);
                            } else {
                                checkedMenus.remove(menu);
                            }

                            String checkedM = checkedMenus.stream().collect(Collectors.joining(","));
                            textView3.setText(checkedM);
                        }
                    });
                }


            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

    }
}