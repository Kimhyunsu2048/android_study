package org.koreait.day12_5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button; // 시작
    private Button button2; // 중지
    private Button button3; // 일시 정지
    private int value;
    private boolean isStop = false;
    private boolean isPause = false;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { // 시작
                // 중지 상태 -> 중지 해제, 중지 X -> 쓰레스 시작
                if (isPause && !isStop) { // 일시 정지 해제
                    isPause = false;
                } else { // 쓰레드 시작
                    Counter counter = new Counter();
                    counter.start();
                    isStop = false;
                    isPause = false;
                }
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                isStop = true;
                value = 0;
                Thread.currentThread().interrupt();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!isStop) {
                    isPause = true;
                    Thread.currentThread().interrupt();
                }
            }
        });

        handler = new CounterHandler();
    }

    class CounterHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) { // 메인 쓰레드
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            textView.setText("" + value);
        }
    }

    class Counter extends Thread {
        public void run() {
            while(!isStop) {
                if (!isPause) {
                    Log.i("COUNTER", String.valueOf(value));

                    //textView.setText(value);
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putInt("value", value);

                    message.setData(bundle);

                    handler.sendMessage(message);

                    value++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        interrupt();
                    }


                }
            }
        }
    }
}