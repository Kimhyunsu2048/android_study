package org.koreait.day08_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        actionBar = getSupportActionBar();
        //actionBar.hide();
        actionBar.setLogo(R.drawable.home);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_USE_LOGO|ActionBar.DISPLAY_SHOW_HOME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        View v = menu.findItem(R.id.menu_search).getActionView();

        EditText editText = v.findViewById(R.id.editText);
        ImageButton button = v.findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String searchKeyword = editText.getText().toString();
                Toast.makeText(getApplicationContext(), searchKeyword, Toast.LENGTH_LONG).show();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        String menu = null;
        switch (itemId) {
            case R.id.menu_refresh:
                menu = "새로고침";
                break;
            case R.id.menu_search:
                menu = "검색";
                break;
            case R.id.menu_setting:
                menu = "설정";
        }

        Toast.makeText(this, menu, Toast.LENGTH_LONG).show();

        return super.onOptionsItemSelected(item);
    }
}