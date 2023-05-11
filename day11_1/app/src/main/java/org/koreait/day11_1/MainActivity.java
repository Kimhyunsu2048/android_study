package org.koreait.day11_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        PostDataAdapter adapter = new PostDataAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        for (int i = 1; i <= 100; i++) {
            PostData item = new PostData(R.drawable.ic_launcher_foreground, "제목" + i, "내용" + i);
            adapter.add(item);
        }

        PostDataAdapter.setListener(new ItemClickListener() {
            @Override
            public void click(View view, PostData item) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("item", item);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}