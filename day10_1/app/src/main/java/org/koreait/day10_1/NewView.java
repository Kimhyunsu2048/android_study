package org.koreait.day10_1;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class NewView extends View {
    public NewView(Context context) {
        super(context);
        init(context);
    }

    public NewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.RED);
    }
}
