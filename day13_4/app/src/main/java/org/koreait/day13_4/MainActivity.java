package org.koreait.day13_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapter = new ItemsAdapter();

        recyclerView.setLayoutManager(layoutManager);

        String url = "https://jsonplaceholder.typicode.com/posts";
        request(url);

        ItemsAdapter.setListener(new ItemClickListener() {
            @Override
            public void click(View view, Item item) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("item", item);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    private void request(String url) {
        StringRequest request = new StringRequest(
                Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) { // 성공시 데이터
                        ObjectMapper om = new ObjectMapper();
                        try {
                            List<Item> items = om.readValue(response, new TypeReference<List<Item>>() {});
                            //Log.i("RESPONSE", items.toString());
                            adapter.setItems(items);
                            recyclerView.setAdapter(adapter);

                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) { // 실패시 데이터
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                return params;
            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
}