package org.koreait.day06_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MemberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user", User.class);
        Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
    }
}