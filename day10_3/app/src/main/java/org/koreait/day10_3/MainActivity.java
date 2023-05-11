package org.koreait.day10_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        recyclerView = findViewById(R.id.recyclerView);

        ListAdapter adapter = new ListAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // reverseLayout - true: 끝번호 -> 처음 , false: 앞번호 처음
        
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 1; i <= 100; i++) {
            PostData item = new PostData(R.drawable.ic_launcher_foreground, "제목" + i, "내용" + i);
            adapter.addItem(item);
        }

        ListAdapter.setListener(new ListItemListener() {
            @Override
            public void touch(View view, int position) {
                PostData item = adapter.getItem(position);
                String message = "제목 : " + item.getSubject() + ", 내용 : " + item.getContent();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });

    }
}