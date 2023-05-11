package org.koreait.day03_4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    private RadioButton[] radioButtons = new RadioButton[2];
    private CheckBox[] checkBoxes = new CheckBox[3];
    private TextView textView2;

    private Button button;
    private Button button2;
    private EditText editText;
    private TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButtons[0] = findViewById(R.id.radioButton);
        radioButtons[1] = findViewById(R.id.radioButton2);

        checkBoxes[0] = findViewById(R.id.checkBox);
        checkBoxes[1] = findViewById(R.id.checkBox2);
        checkBoxes[2] = findViewById(R.id.checkBox3);

        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);

        for (RadioButton button : radioButtons) {
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton button, boolean checked) {
                    if (checked) {
                        String chkValue = button.getText().toString();
                        Toast.makeText(getApplicationContext(), chkValue, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        // 체크 박스 선택시 선택 항목 출력
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton button, boolean checked) {
                    updateCheckedFruits();
                }
            });
        }

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        editText = findViewById(R.id.editText);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // editText.selectAll(); // 전체 글 선택
                String text = editText.getText().toString();

                int startNo = (int)Math.floor(Math.random() * text.length());
                int endNo = (int)Math.floor(Math.random() * text.length());

                int tmp = 0;
                if (startNo > endNo) {
                    tmp = startNo;
                    startNo = endNo;
                    endNo = tmp;
                }

                editText.setSelection(startNo, endNo);

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int startNo = editText.getSelectionStart(); // 선택 첫번째 위치
                int endNo = editText.getPaddingEnd(); // 선택 종료 위치
                String text = editText.getText().toString();
                String selected = text.substring(startNo, endNo+1);

                Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_LONG).show();

            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // start - 시작 위치, count - 변경 갯수, after - 변경 후 위치
                //showToast("변경 전....: " + charSequence.toString());

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //showToast("변경 중....: " + charSequence.toString());
                String text = charSequence.toString();
                int len = text.length();
                textView4.setText(len + "자 입력...");
                if (len > 20) {
                    text = text.substring(0, 20);
                    editText.setText(text);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //showToast("변경 후....: " + editable.toString());
            }
        });

    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void updateCheckedFruits() {
        List<String> fruits = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isChecked()) {
                String fruit = checkBox.getText().toString();
                fruits.add(fruit);
            }
        }

        String checkedFruits = fruits.stream().collect(Collectors.joining(","));
        textView2.setText(checkedFruits);
    }
}