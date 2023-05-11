package org.koreait.day15_1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String AUDIO_URL = "https://sites.google.com/site/ubiaccessmobile/sample_audio.mp3";

    private MediaPlayer mediaPlayer;
    private Button[] buttons;
    private int position; // 재생 위치

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[4];
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);

        buttons[0].setOnClickListener(new View.OnClickListener() { // 재생

            @Override
            public void onClick(View view) {
                startPlayer();
            }
        });

        buttons[1].setOnClickListener(new View.OnClickListener() { // 중지

            @Override
            public void onClick(View view) {
                stopPlayer();
            }
        });

        buttons[2].setOnClickListener(new View.OnClickListener() { // 일시 정지

            @Override
            public void onClick(View view) {
                if (mediaPlayer == null) {
                    return;
                }

                position = mediaPlayer.getCurrentPosition(); // 현재 재생 위치 기록
                mediaPlayer.pause();

            }
        });

        buttons[3].setOnClickListener(new View.OnClickListener() { // 재시작

            @Override
            public void onClick(View view) {
                // 시작을 하지 않았거나 재생중일떄는 재시작 X
                if (mediaPlayer == null || mediaPlayer.isPlaying()) {
                    return;
                }

                mediaPlayer.start();
                mediaPlayer.seekTo(position);
            }
        });
    }

    private void startPlayer() {
        try {
            stopPlayer();

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(AUDIO_URL);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (Exception e) {}
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release(); // 중지
        }
    }
}