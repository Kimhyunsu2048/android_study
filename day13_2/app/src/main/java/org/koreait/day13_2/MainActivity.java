package org.koreait.day13_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                if (url.trim().isEmpty()) { // 값이 없는 경우는 메세지
                    Toast.makeText(getApplicationContext(), "주소를 입력하세요!", Toast.LENGTH_LONG).show();

                    return;
                }
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        request(url);
                    }
                }).start();

            }
        });
    }

    private void request(String url) {
        StringBuffer buffer = new StringBuffer(5000);
        try {
            URL url2 = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            if (conn == null) {
                return;
            }

            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            try (
                InputStream in = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(in); // 바이트 단위(InputStream) -> 문자 단위 입력 스트림(Reader)
                BufferedReader br = new BufferedReader(isr)) {
                // readLine()
                String line = null;
                while((line = br.readLine()) != null) {
                    buffer.append(line);
                }
            }


        } catch (Exception e) {}

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                textView.setText(buffer.toString());
            }
        });

    }
}