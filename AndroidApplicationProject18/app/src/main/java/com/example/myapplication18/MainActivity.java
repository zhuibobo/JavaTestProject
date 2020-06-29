package com.example.myapplication18;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication18.bottomnavigation.BottomNavigationTest001Activity;
import com.example.myapplication18.bottomnavigation.BottomNavigationTest002Activity;
import com.example.myapplication18.fragment.FragmentTest001Activity;
import com.example.myapplication18.fragment.FragmentTest002Activity;
import com.example.myapplication18.navigation_drawer.NavigationDrawerTest001Activity;
import com.example.myapplication18.navigation_drawer.NavigationViewTest002Activity;
import com.example.myapplication18.navigation_drawer.NavigationViewTest003Activity;
import com.example.myapplication18.recyclerview.RecyclerViewTest001Activity;
import com.example.myapplication18.recyclerview.RecyclerViewTest002Activity;
import com.example.myapplication18.recyclerview.RecyclerViewTest003Activity;
import com.example.myapplication18.recyclerview.RecyclerViewTest004Activity;
import com.example.myapplication18.recyclerview.RecyclerViewTest005Activity;
import com.example.myapplication18.scrollview.ScrollViewTest001Activity;
import com.example.myapplication18.scrollview.ScrollViewTest002Activity;
import com.example.myapplication18.scrollview.ScrollViewTest003Activity;
import com.example.myapplication18.scrollview.ScrollViewTest004Activity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: hello word "+R.string.app_name);
        Button btn_activity_linear_layout_test001=findViewById(R.id.btn_activity_linear_layout_test001);
        btn_activity_linear_layout_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,LinearLayoutTest001Activity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        Button btn_activity_linear_layout_test002=findViewById(R.id.btn_activity_linear_layout_test002);
        btn_activity_linear_layout_test002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,LinearLayoutTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_constraint_layout_test001=findViewById(R.id.btn_constraint_layout_test001);
        btn_constraint_layout_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,ConstraintLayoutTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view001=findViewById(R.id.btn_scroll_view001);
        btn_scroll_view001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, ScrollViewTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view002=findViewById(R.id.btn_scroll_view002);
        btn_scroll_view002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, ScrollViewTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view003=findViewById(R.id.btn_scroll_view003);
        btn_scroll_view003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, ScrollViewTest003Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view004=findViewById(R.id.btn_scroll_view004);
        btn_scroll_view004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, ScrollViewTest004Activity.class);
                startActivity(intent);
            }
        });

        Button btn_swipe_refresh_view001=findViewById(R.id.btn_swipe_refresh_view001);
        btn_swipe_refresh_view001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,SwipeRefreshLayoutTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_swipe_refresh_view002=findViewById(R.id.btn_swipe_refresh_view002);
        btn_swipe_refresh_view002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,SwipeRefreshLayoutTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view001=findViewById(R.id.btn_recycler_view001);
        btn_recycler_view001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, RecyclerViewTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view002=findViewById(R.id.btn_recycler_view002);
        btn_recycler_view002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, RecyclerViewTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view003=findViewById(R.id.btn_recycler_view003);
        btn_recycler_view003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, RecyclerViewTest003Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view004=findViewById(R.id.btn_recycler_view004);
        btn_recycler_view004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, RecyclerViewTest004Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view005=findViewById(R.id.btn_recycler_view005);
        btn_recycler_view005.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, RecyclerViewTest005Activity.class);
                startActivity(intent);
            }
        });

        Button btn_relative_layout_test001=findViewById(R.id.btn_relative_layout_test001);
        btn_relative_layout_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,RelativeLayoutTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_table_layout_test001=findViewById(R.id.btn_table_layout_test001);
        btn_table_layout_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,TableLayoutTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_smart_refresh_layout_test001=findViewById(R.id.btn_smart_refresh_layout_test001);
        btn_smart_refresh_layout_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,SmartRefreshLayoutTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_bottom_navigation_test001=findViewById(R.id.btn_bottom_navigation_test001);
        btn_bottom_navigation_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, BottomNavigationTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_bottom_navigation_test002=findViewById(R.id.btn_bottom_navigation_test002);
        btn_bottom_navigation_test002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, BottomNavigationTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_tabbed_test001=findViewById(R.id.btn_tabbed_test001);
        btn_tabbed_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,TabbedTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_fragment_test001=findViewById(R.id.btn_fragment_test001);
        btn_fragment_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, FragmentTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_fragment_test002=findViewById(R.id.btn_fragment_test002);
        btn_fragment_test002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, FragmentTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_navigation_drawer_test001=findViewById(R.id.btn_navigation_drawer_test001);
        btn_navigation_drawer_test001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, NavigationDrawerTest001Activity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        Button btn_navigation_view_test002=findViewById(R.id.btn_navigation_view_test002);
        btn_navigation_view_test002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, NavigationViewTest002Activity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        Button btn_navigation_view_test003=findViewById(R.id.btn_navigation_view_test003);
        btn_navigation_view_test003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, NavigationViewTest003Activity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
        /*findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity,menu);
        //getMenuInflater().inflate(R.me);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity.this,item.getTitle()+" click ",Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}
