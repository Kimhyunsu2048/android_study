package org.koreait.day13_1;

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

    private Button[] buttons;
    private TextView textView;
    private int value;
    private boolean isStop;
    private boolean isPause;

   // private CounterHandler handler;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[3];
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);

        textView = findViewById(R.id.textView);

        buttons[0].setOnClickListener(new View.OnClickListener() { // 시작 버튼

            @Override
            public void onClick(View view) {
                if (!isStop && isPause) { // 일시 정지 상태
                    isPause = false;
                    Thread.currentThread().interrupt();
                } else { // 새로 시작
                    isStop = false;
                    isPause = false;
                    new Counter().start();
                }
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() { // 일시 정지

            @Override
            public void onClick(View view) {
                if (!isStop) {
                    isPause = true;
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        buttons[2].setOnClickListener(new View.OnClickListener() { // 정지

            @Override
            public void onClick(View view) {
                isStop = true;
                value = 0;
                Thread.currentThread().interrupt();
            }
        });

        //handler = new CounterHandler();
        handler = new Handler();

    }


    class Counter extends Thread {
        public void run() {
            while(!isStop) { // 정지 상태가 아닌 경우 계속 카운팅
                if (!isPause) {

                    //textView.setText(value);
                    /**
                    Message message = handler.obtainMessage();
                    Bundle bundle = new Bundle();
                    bundle.putInt("value", value);
                    message.setData(bundle);

                    handler.sendMessage(message);
                    */
                    /*
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            textView.setText("" + value);
                        }
                    });
                    */
                    /**
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            textView.setText("" + value);
                        }
                    });
                     */

                    // 지연실행 postDelayed, 정시 실행 postAtTime
                    handler.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            textView.setText("" + value);
                        }
                    }, 5000);


                    Log.i("COUNTER", "" + value);
                    value++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }
    }
    /**
    class CounterHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
           Bundle bundle = msg.getData();
           int value = bundle.getInt("value");
           textView.setText("" + value);
        }
    }
     */
}