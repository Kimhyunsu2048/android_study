package org.koreait.day04_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private View view;
    private View view2;
    private TextView textView;

    private ScrollView scrollView;

    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        textView = findViewById(R.id.textView);

        scrollView = findViewById(R.id.scrollView);
        scrollView.setEnabled(true);
        scrollView.setVerticalScrollBarEnabled(true);

        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                float xpos = motionEvent.getX();
                float ypos = motionEvent.getY();
                int action = motionEvent.getAction();
                String msg = "좌표 : " + xpos + ", " + ypos;

                switch(action) {
                    case MotionEvent.ACTION_DOWN:
                        msg += ", 눌렀을때";
                        break;
                    case MotionEvent.ACTION_UP :
                        msg += ", 뗄때";
                        break;
                    case MotionEvent.ACTION_MOVE :
                        msg += ", 움직일때";
                }

                appendText(msg);

                return true;
            }
        });


        detector = new GestureDetector(this, new GestureDetector.OnGestureListener() {

            @Override
            public boolean onDown(@NonNull MotionEvent motionEvent) {
                showToast("onDown");
                return true;
            }

            @Override
            public void onShowPress(@NonNull MotionEvent motionEvent) {
                showToast("onShowPress");
            }

            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
                //showToast("onSingleTapUp");
                return true;
            }

            @Override
            public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
                //showToast("onScroll");
                return true;
            }

            @Override
            public void onLongPress(@NonNull MotionEvent motionEvent) {
                //showToast("LongPress");
            }

            @Override
            public boolean onFling(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
                //showToast("onFling");
                return true;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                detector.onTouchEvent(motionEvent);
                return true;
            }
        });
    }

    private void appendText(String msg) {
        textView.append(msg + "\n");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}