package org.koreait.day08_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.koreait.day08_4.constants.ApiURL;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class DiaryViewActivity extends AppCompatActivity {

    private RequestQueue requestQueue;

    private TextView diaryViewSubject;
    private TextView diaryViewContent;
    private TextView diaryViewDate;
    private Button diaryViewClose;
    private ImageView diaryViewPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_view);

        requestQueue = Volley.newRequestQueue(this);

        diaryViewSubject = findViewById(R.id.diary_view_subject);
        diaryViewContent = findViewById(R.id.diary_view_content);
        diaryViewClose = findViewById(R.id.diary_view_close);
        diaryViewDate = findViewById(R.id.diary_view_date);
        diaryViewPhoto = findViewById(R.id.diary_view_photo);

        diaryViewClose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Intent intent = getIntent();
        processIntent(intent);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    private void setFieldData(Diary diary) {
        diaryViewSubject.setText(diary.getSubject());
        diaryViewContent.setText(diary.getContent());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy.MM.dd HH:mm");
        diaryViewDate.setText(formatter.format(diary.getRegDt()));

        String imageUrl = diary.getImageUrl();
        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            diaryViewPhoto.setVisibility(View.INVISIBLE);
        } else {
            diaryViewPhoto.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(ApiURL.HOST + imageUrl)
                    .into(diaryViewPhoto);

        }
    }

    private void processIntent(Intent intent) {
        int diaryId = intent.getIntExtra("diaryId", 0);
        if (diaryId == 0) {
            Bundle bundle = intent.getExtras();
            Diary diary = bundle.getParcelable("item");
            setFieldData(diary);
            return;
        }

        //Toast.makeText(this, "" + diaryId, Toast.LENGTH_LONG).show();

        String url = ApiURL.DIARY_URL + "/view/" + diaryId;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                //Log.d("DIARY_VIEW", response);
                ObjectMapper om = new ObjectMapper();
                om.registerModule(new JavaTimeModule());

                try {
                    Diary diary = om.readValue(response, Diary.class);
                    setFieldData(diary);
                    //Log.d("DIARY_VIEW", diary.toString());

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("DIARY_VIEW", error.getMessage());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                try {
                    String utf8Str = new String(response.data, "UTF-8");
                    return Response.success(utf8Str, HttpHeaderParser.parseCacheHeaders(response));
                } catch (Exception e) {
                    return Response.error(new ParseError(e));
                }

            }
        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
}