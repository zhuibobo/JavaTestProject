package com.example.myapplication18.toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication18.R;

public class ToolbarTest001Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar_test001);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("Title");//设置主标题
        toolbar.setSubtitle("Subtitle");//设置子标题
        toolbar.setNavigationOnClickListener(View->finish());
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Button btn_open_left=findViewById(R.id.btn_open_left);
        btn_open_left.setOnClickListener((View v)->{
            drawerLayout.openDrawer(GravityCompat.START);
        });

        Button btn_open_right=findViewById(R.id.btn_open_right);
        btn_open_right.setOnClickListener((View v)->{
            drawerLayout.openDrawer(GravityCompat.END);
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int menuItemId =v.getId();
                ToolbarTest001Activity.this.finish();
                //NavUtils.navigateUpFromSameTask(ToolbarTest001Activity.this);
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int menuItemId = item.getItemId();
                if(menuItemId==R.id.action_item1){
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                else if(menuItemId==R.id.action_item2){
                    drawerLayout.openDrawer(GravityCompat.END);
                }
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_test001, menu);
        return true;
    }


}
