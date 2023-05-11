package org.koreait.day10_3;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public interface ListItemListener {
    void touch(View view, int position);
}
