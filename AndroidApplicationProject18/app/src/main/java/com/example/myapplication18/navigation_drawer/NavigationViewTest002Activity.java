package com.example.myapplication18.navigation_drawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.myapplication18.R;

public class NavigationViewTest002Activity extends AppCompatActivity {

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view_test002);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        ListView drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item,
                new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n"}));

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        );

        drawerLayout.setDrawerListener(mDrawerToggle);

        Button btn_open_left=findViewById(R.id.btn_open_left);
        btn_open_left.setOnClickListener((View v)->{
            drawerLayout.openDrawer(GravityCompat.START);
        });

        Button btn_open_right=findViewById(R.id.btn_open_right);
        btn_open_right.setOnClickListener((View v)->{
            drawerLayout.openDrawer(GravityCompat.END);
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                Log.i("---", "滑动中");
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Log.i("---", "打开");
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Log.i("---", "关闭");
            }

            @Override
            public void onDrawerStateChanged(int i) {
                Log.i("---", "状态改变");
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
