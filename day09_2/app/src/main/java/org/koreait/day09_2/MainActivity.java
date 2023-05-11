package org.koreait.day09_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissions = {
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        checkPermissions(permissions);

    }

    private void checkPermissions(String[] permissions) {
        // ConextCompat.checkSelfPermission

        List<String> requestPermissions = new ArrayList<>();
        for (String permission : permissions) {
            // 승인 상태의 값
            int status = ContextCompat.checkSelfPermission(this, permission);
            if (status != PackageManager.PERMISSION_GRANTED
                    && !ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                // 권한 승인을 요청할 수 있는 권한
                requestPermissions.add(permission);
            }
        }

        if (requestPermissions.size() > 0) {

            String[] rPerms = new String[requestPermissions.size()];
            requestPermissions.toArray(rPerms);

            // 권한 요청 메서드
            ActivityCompat.requestPermissions(this, rPerms, 100);
        }
    }

    // 사용자가 승인하지 않았을 경우 메서드 실행
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode != 100) {
            return;
        }

        int granted = 0, denied = 0;
        for (int grantResult : grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) { // 승인된 권한
                granted++;
            } else { // 거부된 권한
                denied++;
            }
        }

        Toast.makeText(this, "승인권한 : " + granted + ", 거부 권한 : " + denied, Toast.LENGTH_LONG).show();

    }
}