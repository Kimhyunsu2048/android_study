package org.koreait.day13_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    private TextView textView;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String url = editText.getText().toString().trim();
                if (url.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "주소를 입력하세요.", Toast.LENGTH_LONG).show();
                    return;
                }

                request(url);
            }
        });

        requestQueue = Volley.newRequestQueue(this);
    }

    private void request(String url) {
        StringRequest req = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) { // 응답 성공시 데이터
                        textView.setText(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        return params;
                    }
        };

        req.setShouldCache(false);
        requestQueue.add(req);
    }
}