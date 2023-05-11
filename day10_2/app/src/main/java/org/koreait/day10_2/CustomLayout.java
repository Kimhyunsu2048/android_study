package org.koreait.day10_2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CustomLayout extends LinearLayout {

    private ImageView imageView;
    private TextView textView;
    private TextView textView2;

    public CustomLayout(Context context) {
        super(context);
        init(context);
    }

    public CustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item, this, true);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void setImage(int ImageId) {
        imageView.setImageResource(ImageId);
    }

    public void setSubject(String subject) { // 제목 설정
        textView.setText(subject);
    }

    public void setContent(String content) { // 내용 설정
        textView2.setText(content);
    }
}
