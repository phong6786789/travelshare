package com.duan.travelshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.duan.travelshare.firebasedao.UserDao;
import com.duan.travelshare.fragment.GiaoDichFragment;
import com.duan.travelshare.fragment.HomeFragment;
import com.duan.travelshare.fragment.ThongBaoFragment;
import com.duan.travelshare.fragment.UserFragment;
import com.duan.travelshare.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    public static String name = "", email = "", userName = "";
    static UserDao userDao;
    static ArrayList<User> users;
    static Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        userName = i.getStringExtra("userName");
//        Toast.makeText(this, userName, Toast.LENGTH_SHORT).show();

        //Get toàn bộ list về
        if (userName.matches("0")) {
            userDao = new UserDao(this);
            users = userDao.checkUser();
            Log.d("TAG", "login:"+"đăng nhập bằng gg fb");
        }
        else {
            Log.d("TAG", "login:"+"đăng nhập bằng tk mk");
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        //Tạo màn hình ban đầu là fragment home đầu tiên
        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }
    }

    //Menu bottom
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()) {
                case R.id.home:
                    toolbar.setTitle("TRANG CHỦ");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.giaodich:
                    toolbar.setTitle("GIAO DỊCH");
                    fragment = new GiaoDichFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.thongbao:
                    toolbar.setTitle("THÔNG BÁO");
                    fragment = new ThongBaoFragment();
                    loadFragment(fragment);
                    return true;

                case R.id.user:
                    toolbar.setTitle("CÁ NHÂN");
                    fragment = new UserFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    //Chặn hành động bấm phím back của người dùng
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    //Check tài khoản là Google hoặc Facebook đã đăng nhập trước đó chưa
    public static void checkUser() {
        for (int i = 0; i < users.size(); i++) {
            String tk = users.get(i).getUserName();
            if (email.equalsIgnoreCase(tk)) {
                check = true;
                break;
            }
        }
        if(check==false){
            userDao.insert(new User(email,"","0"));
            check=true;
        }
    }


}