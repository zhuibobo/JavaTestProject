package com.example.myapplication18.navigation_drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication18.MainActivity;
import com.example.myapplication18.R;
import com.google.android.material.navigation.NavigationView;

public class NavigationViewTest003Activity extends AppCompatActivity {
    ActionBarDrawerToggle mDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view_test003);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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

        final NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navigationView);
                Toast.makeText(NavigationViewTest003Activity.this, "Header View is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                /*switch (item.getItemId()) {
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Home is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_settings:
                        Toast.makeText(MainActivity.this, "Settings is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_share:
                        Toast.makeText(MainActivity.this, "Share is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this, "About is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                }*/
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
