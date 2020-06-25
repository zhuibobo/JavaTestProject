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
import com.example.myapplication18.R;

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
                Intent intent=new Intent(MainActivity.this,ScrollViewTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view002=findViewById(R.id.btn_scroll_view002);
        btn_scroll_view002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,ScrollViewTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view003=findViewById(R.id.btn_scroll_view003);
        btn_scroll_view003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,ScrollViewTest003Activity.class);
                startActivity(intent);
            }
        });

        Button btn_scroll_view004=findViewById(R.id.btn_scroll_view004);
        btn_scroll_view004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,ScrollViewTest004Activity.class);
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
                Intent intent=new Intent(MainActivity.this,RecyclerViewTest001Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view002=findViewById(R.id.btn_recycler_view002);
        btn_recycler_view002.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,RecyclerViewTest002Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view003=findViewById(R.id.btn_recycler_view003);
        btn_recycler_view003.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,RecyclerViewTest003Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view004=findViewById(R.id.btn_recycler_view004);
        btn_recycler_view004.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,RecyclerViewTest004Activity.class);
                startActivity(intent);
            }
        });

        Button btn_recycler_view005=findViewById(R.id.btn_recycler_view005);
        btn_recycler_view005.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"hello  toast!",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this,RecyclerViewTest005Activity.class);
                startActivity(intent);
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