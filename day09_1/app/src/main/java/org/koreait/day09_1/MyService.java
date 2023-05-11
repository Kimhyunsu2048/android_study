package org.koreait.day09_1;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MYSERVICE", "onCreated()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 백그라운드에서 처리할 부분을 정의
        // 비정상 종료시 -> 다시 재시작
        if (intent == null) { // 비정상 종료
            return Service.START_STICKY; // 서비스 재시작
        } else { // 서비스가 실행 중일 때 intent 처리

            Bundle bundle = intent.getExtras();
            String message = bundle.getString("message");
            Log.d("MYSERVICE", message);

            Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

            intent2.putExtra("reply", "응답 메세지 from " + message);

            startActivity(intent2);

        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}