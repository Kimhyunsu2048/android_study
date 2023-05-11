package org.koreait.day15_2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final String VIDEO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_video.mp4";
    private VideoView videoView;
    private Button button;
    private Button button2;
    private Button button3;
    private int position; // 재생 위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        videoView = findViewById(R.id.videoView);


        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(0);
                videoView.setVideoURI(Uri.parse(VIDEO_URL));
                videoView.requestFocus();
                videoView.start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {  // 일시 정지
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()) {
                    return;
                }

                position = videoView.getCurrentPosition();
                videoView.pause();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {  // 재시작
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying()) {
                    return;
                }

                videoView.start();
                videoView.seekTo(position);

            }
        });

    }
}