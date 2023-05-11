package org.koreait.day07_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("LIFE_CYCLE", "onCreate");

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                clearState();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("LIFE_CYCLE", "onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("LIFE_CYCLE", "onResume()");
        // 상태 복구
        restoreState();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("LIFE_CYCLE", "onPause()");

        // 현재 상태 저장
        saveState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("LIFE_CYCLE", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("LIFE_CYCLE", "onDestroy()");
    }

    private void saveState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", editText.getText().toString());
        editor.commit();
        Log.i("SAVE_RESTORE_STATE", "저장됨");
    }

    private void restoreState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        String name = pref.getString("name", "없음");
        editText.setText(name);

        Log.i("SAVE_RESTORE_STATE", "복구됨! : "+ name);
    }

    private void clearState() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        Log.i("SAVE_RESTORE_STATE", "초기화됨!");
    }





}