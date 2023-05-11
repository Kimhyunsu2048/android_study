package org.koreait.day08_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabs;
    private Menu1Fragment menu1;
    private Menu2Fragment menu2;
    private Menu3Fragment menu3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("메뉴1"));
        tabs.addTab(tabs.newTab().setText("메뉴2"));
        tabs.addTab(tabs.newTab().setText("메뉴3"));

        menu1 = new Menu1Fragment();
        menu2 = new Menu2Fragment();
        menu3 = new Menu3Fragment();

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { // 선택시 호출
                int position = tab.getPosition(); // 선택된 탭의 순서 번호(0번 부터 시작)
                Fragment selected = null;
                if (position == 0) {
                    selected = menu1;
                } else if (position == 1) {
                    selected = menu2;
                } else if (position == 2) {
                    selected = menu3;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { // 선택이 해제 되었을때

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { // 다시 선택 했을때

            }
        });

    }
}