package org.koreait.day07_3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Menu1Fragment menu1;
    private Menu2Fragment menu2;
    private Menu3Fragment menu3;

    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu1 = new Menu1Fragment();
        menu2 = new Menu2Fragment();
        menu3 = new Menu3Fragment();

        buttons = new Button[3];
        buttons[0] = findViewById(R.id.button);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);

        for (int i=0; i < buttons.length; i++) {
            MENUS menu = MENUS.valueOf("MENU" + (i+1));

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeMenus(menu);
                }
            });
        }
    }

    public void changeMenus(MENUS menu) {
        FragmentManager manager = getSupportFragmentManager();
        switch (menu) {
            case MENU1:
                manager.beginTransaction().replace(R.id.container, menu1).commit();
                break;
            case MENU2:
                manager.beginTransaction().replace(R.id.container, menu2).commit();
                break;
            case MENU3:
                manager.beginTransaction().replace(R.id.container, menu3).commit();
                break;
        }
    }

    public void sendDataFromFragment(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}