package org.koreait.day08_4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import org.koreait.day08_4.constants.ApiURL;
import org.koreait.day08_4.constants.Menus;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private TabLayout tabsTop;

    private MainFragment mainFragment; // 메인 화면
    private JoinFragment joinFragment; // 회원가입 화면 
    private LoginFragment loginFragment; // 로그인 화면
    private MypageFragment mypageFragment; // 마이페이지

    private PhotoFragment photoFragment; // 사진 촬영
    private DiaryFragment diaryFragment; // 일기 쓰기
    private DiaryListFragment diaryListFragment; // 일기 목록

    private CsFragment csFragment; // 고객센터
    
    private LinearLayout slideMenu; // 슬라이드 메뉴
    private FrameLayout slideBg; // 슬라이드 배경 레이어
    
    private boolean isSlideMenuOpen; // true - 슬라이드 메뉴 노출, false - 닫힘

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 감추기
        actionBar = getSupportActionBar();
        actionBar.hide();

        // 위험권한 부여
        permissionProcess();

        csFragment = new CsFragment();
        diaryFragment = new DiaryFragment();
        diaryListFragment = new DiaryListFragment();
        photoFragment = new PhotoFragment();

        // 상단 탭 메뉴
        tabsTop = findViewById(R.id.top_tabs);
        tabsTop.addTab(tabsTop.newTab().setText(R.string.menu_camera));
        tabsTop.addTab(tabsTop.newTab().setText(R.string.menu_diary));
        tabsTop.addTab(tabsTop.newTab().setText(R.string.menu_cs));

        tabsTop.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int menuId = tab.getPosition();
                Fragment selected = null;
                switch (menuId) {
                    case 1:  // 일기쓰기
                        if (!SessionData.isLogin()) { // 미로그인 상태 -> 로그인 페이지 이동
                            changeMenu(Menus.LOGIN);
                            return;
                        }

                        selected = diaryFragment;
                        break;
                    case 2: // 고객센터
                        selected = csFragment;
                        break;
                    default : // 사진촬영
                        selected = photoFragment;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });


        mainFragment = new MainFragment();
        joinFragment = new JoinFragment();
        loginFragment = new LoginFragment();
        mypageFragment = new MypageFragment();

        BottomNavigationView bottomTabs = findViewById(R.id.bottom_tabs);
        bottomTabs.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selected = null;
                int itemId = item.getItemId();
                switch(itemId) {
                    case R.id.tab_main:
                        selected = mainFragment;
                        break;
                    case R.id.tab_join:
                        // 로그인 시 -> 마이페이지
                        // 미로그인 시 -> 회원가입
                        if (SessionData.isLogin()) {
                            selected = mypageFragment;
                        } else {
                            selected = joinFragment;
                        }

                        break;
                    case R.id.tab_login:
                        // 로그인 시 -> 로그아웃 처리
                        // 미로그인시에는 로그인
                        if (SessionData.isLogin()) {
                            // 로그아웃 처리
                            logout();
                        }

                        selected = loginFragment;

                        break;
                    case R.id.tab_menu:
                        slideMenuOpen();
                        return true;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
                return true;
            }
        });

        slideMenu = findViewById(R.id.slide_menu);
        slideBg = findViewById(R.id.slide_bg);
        slideMenu.bringToFront();
        slideBg.bringChildToFront(slideMenu);

        slideBg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                slideMenuOpen();
            }
        });

        requestQueue = Volley.newRequestQueue(this);
    }

    private void slideMenuOpen() {
        Animation rightAnim = AnimationUtils.loadAnimation(this, R.anim.translater_right);
        Animation leftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        SlideMenuListener listener = new SlideMenuListener();
        rightAnim.setAnimationListener(listener);
        leftAnim.setAnimationListener(listener);

        if (isSlideMenuOpen) { // 열린 상태 -> 닫기
            slideMenu.startAnimation(leftAnim);
            slideMenu.setVisibility(View.INVISIBLE); // 닫힌 다음 안보임 처리
        } else { // 닫힌 상태 -> 열기

            slideMenu.setVisibility(View.VISIBLE);
            slideMenu.startAnimation(rightAnim);
            slideBg.setVisibility(View.VISIBLE);


        }
    }

    private class SlideMenuListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (isSlideMenuOpen) { // 메뉴가 열려 있는 경우
                // 메뉴가 완전히 닫히면 배경 레이어 제거
                slideBg.setVisibility(View.INVISIBLE);
                isSlideMenuOpen = false;
            } else {
                isSlideMenuOpen = true;
            }
         }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public void request(String url, RequestCallback callback) {
        request(url, "GET", null, callback);
    }

    public void request(String url, String method, Map<String, String> requestParams, RequestCallback callback) {

        StringRequest request = new StringRequest(
                method.equals("POST") ? Request.Method.POST : Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.process(response);
                    }
            }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                callback.process(error.getMessage());
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = requestParams == null ? new HashMap<>() : requestParams;

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

    /**
     * 메뉴 변경
     *
     */
    public void changeMenu(Menus menu) {
        switch(menu) {
            case LOGIN :
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();
                break;
            case JOIN :
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new JoinFragment()).commit();
                break;
            case MAIN : // 메인 화면
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new MainFragment()).commit();
                break;
            case DIARY : // 일기 쓰기
                getSupportFragmentManager().beginTransaction().replace(R.id.container, diaryFragment).commit();
                break;
            case DIARY_LIST : // 일기 목록
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new DiaryListFragment()).commit();
                break;
            case PHOTO : // 사진 촬영
                getSupportFragmentManager().beginTransaction().replace(R.id.container, photoFragment).commit();
                break;
            case PHOTO_LIST : // 사진 목록
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new PhotoListFragment()).commit();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUserInfo();

    }

    /**
     * 로그인한 회원 정보의 갱신
     *
     */
    public void updateUserInfo() {
        // SharedPreference 에 userId가 있으면 회원 데이터 유지
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        String userId = pref.getString("userId", null);
        if (userId == null) {
            SessionData.user = null;
        } else { // 회원 데이터 조회 갱신

            request(ApiURL.USER_INFO_URL + userId, new RequestCallback() {
                @Override
                public void process(String response) {
                    //Log.d("USER_INFO_RESPONSE", response);
                    ObjectMapper om = new ObjectMapper();
                    try {

                        User user = om.readValue(response, User.class);
                        // Log.d("USER_INFO_RESPONSE", user.toString());
                        SessionData.user = user;
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

        }
    }

    /**
     * 로그아웃 처리
     *
     */
    public void logout() {
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        SessionData.user = null;
    }

    private File file;
    /** 사진 촬영 */
    public void takePicture() {
        file = new File(getFilesDir(), "picture.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }

            file.createNewFile();
        } catch (IOException e) {
            Log.d("PHOTO_FILE_ERROR", e.getMessage());
        }

        Uri uri = null;
        if (Build.VERSION.SDK_INT >= 24) { // 	Nougat
            uri = FileProvider.getUriForFile(this, this.getPackageName(), file);
        } else {
            uri = Uri.fromFile(file);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            diaryFragment.updateImage(bitmap, file);
        }
    }

    private void permissionProcess() {
        String[] permissions = {
                Manifest.permission.CAMERA
        };

        List<String> requiredPermissions = new ArrayList<>();
        for (String permission : permissions) {
            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                requiredPermissions.add(permission);
            }
        }
        if (requiredPermissions.size() > 0) {
            String[] targets = new String[requiredPermissions.size()];
            requiredPermissions.toArray(targets);

            ActivityCompat.requestPermissions(this, targets, 102);
        }
    }
}