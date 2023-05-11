package org.koreait.day08_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigation;

    private MapFragment map;
    private JoinFragment join;
    private LoginFragment login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);

        map = new MapFragment();
        join = new JoinFragment();
        login = new LoginFragment();

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int tabId = item.getItemId();
                Fragment selected = null;
                switch(tabId) {
                    case R.id.tab1: // 지도 확인
                        selected = map;
                        break;
                    case R.id.tab2: // 회원가입
                        selected = join;
                        break;
                    case R.id.tab3: // 로그인
                        selected = login;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();

                return true;
            }
        });

    }
}