package org.koreait.day10_1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

public class MyButton extends AppCompatButton {

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("MY_BUTTON", "onDraw");
    }

    public MyButton(Context context) {
        super(context);
        init(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.rgb(255, 178, 245));
        setTextColor(Color.DKGRAY);
        //setTextSize();  // 픽셀 단위
        Resources res = getResources();
        float fontSize = res.getDimension(R.dimen.text_size);
        setTextSize(fontSize);
        setText("클릭 하세요!");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction(); // 버튼의 클릭 상태
        // MotionEvent.ACTION_DOWN : 버튼 클릭
        // MotionEvent.UP : 버튼을 클릭하고 뗄데
        if (action == MotionEvent.ACTION_DOWN) {
            setBackgroundColor(Color.GREEN);
            setTextColor(Color.WHITE);
        } else {
            setBackgroundColor(Color.BLUE);
        }

        Toast.makeText(getContext(), "기능 수행!!", Toast.LENGTH_LONG).show();

        return true;
    }
}
