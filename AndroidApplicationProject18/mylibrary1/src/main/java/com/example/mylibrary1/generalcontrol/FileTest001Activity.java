package com.example.mylibrary1.generalcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mylibrary1.R;
import com.example.mylibrary1.sqlite.OrderDBHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileTest001Activity extends AppCompatActivity {

    private static final String TAG = "FileTest001Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_test001);

        Button btn_create_file = findViewById(R.id.btn_create_file);
        btn_create_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String filecontent = "逗鹅冤";
                    FileOutputStream output = FileTest001Activity.this.openFileOutput("FileTest001ActivityOnClick.txt", Context.MODE_PRIVATE);
                    output.write(filecontent.getBytes());  //将String字符串以字节流的形式写入到输出流中
                    output.close();

                    Toast.makeText(FileTest001Activity.this,read("FileTest001ActivityOnClick.txt"),Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Log.e(TAG, "onClick: " + e.getMessage(), e);
                }
            }
        });

        Button btn_env_directory = findViewById(R.id.btn_env_directory);
        btn_env_directory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    int REQUEST_CODE_CONTACT = 101;
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    //验证是否许可权限
                    for (String str : permissions) {
                        if (FileTest001Activity.this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                            //申请权限
                            FileTest001Activity.this.requestPermissions(permissions, REQUEST_CODE_CONTACT);
                            return;
                        }
                    }
                }

                String dataDirectory = Environment.getDataDirectory().toString();

                String downLoadCacheDirectory = Environment.getDownloadCacheDirectory().toString();

                String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();

                String externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory("zip").toString();

                String rootDirectory = Environment.getRootDirectory().getPath();

                Log.i("dir_dataDirectory",dataDirectory);

                Log.i("dir_downLoadCacheDir",downLoadCacheDirectory);

                Log.i("dir_externalStorage",externalStorageDirectory);

                Log.i("dir_externalStoragePu",externalStoragePublicDirectory);

                Log.i("dir_rootDirectory",rootDirectory);

                Log.i("dir_getFilesDir",FileTest001Activity.this.getFilesDir().getAbsolutePath());

                File[] files;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    files = getExternalFilesDirs(Environment.MEDIA_MOUNTED);
                    for(File file:files){
                        Log.i(TAG,file.getAbsolutePath());
                    }
                }

                try {
                    String path = createOutPublicDir()+File.separator+"逗鹅冤1.txt";
                    FileOutputStream output = new FileOutputStream(path);
                    String filecontent = "逗鹅冤1";
                    output.write(filecontent.getBytes());
                    //将String字符串以字节流的形式写入到输出流中
                    output.close();
                }
                catch (IOException e) {
                    Log.e(TAG, "onClick: " + e.getMessage(), e);
                }
            }
        });

        Button btn_shared_preferences = findViewById(R.id.btn_shared_preferences);
        btn_shared_preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> data = new HashMap<String, String>();
                SharedPreferences sp = FileTest001Activity.this.getSharedPreferences("mysp", Context.MODE_PRIVATE);
                data.put("username", sp.getString("username", ""));
                data.put("passwd", sp.getString("passwd", ""));

                //sp = FileTest001Activity.this.getSharedPreferences("mysp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", "逗鹅冤");
                editor.putString("passwd", "222");
                editor.commit();
                Toast.makeText(FileTest001Activity.this, "信息已写入SharedPreference中", Toast.LENGTH_SHORT).show();
            }
        });

        Button btn_sqlite_001 = findViewById(R.id.btn_sqlite_001);
        btn_sqlite_001.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDBHelper ordersDBHelper=new OrderDBHelper(FileTest001Activity.this);
                SQLiteDatabase db = ordersDBHelper.getWritableDatabase();

                db.execSQL("delete from "+OrderDBHelper.TABLE_NAME);
                db.beginTransaction();

                db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, OrderPrice, Country) values (1, 'Arc', 100, 'China')");
                db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, OrderPrice, Country) values (2, 'Bor', 200, 'USA')");
                db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, OrderPrice, Country) values (3, 'Cut', 500, 'Japan')");
                db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, OrderPrice, Country) values (4, 'Bor', 300, 'USA')");
                db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, OrderPrice, Country) values (5, 'Arc', 600, 'China')");
                db.execSQL("insert into " + OrderDBHelper.TABLE_NAME + " (Id, CustomName, OrderPrice, Country) values (6, 'Doom', 200, 'China')");

                db.setTransactionSuccessful();
                db.endTransaction();

                db.beginTransaction();
                ContentValues contentValues = new ContentValues();
                contentValues.put("Id", 7);
                contentValues.put("CustomName", "Jne");
                contentValues.put("OrderPrice", 700);
                contentValues.put("Country", "China");
                db.insertOrThrow(OrderDBHelper.TABLE_NAME, null, contentValues);

                db.setTransactionSuccessful();
                db.endTransaction();

                String[] ORDER_COLUMNS={"*"};
                // select * from Orders where CustomName = 'Bor'
                Cursor cursor = db.query(OrderDBHelper.TABLE_NAME,
                        ORDER_COLUMNS,
                        "CustomName = ?",
                        new String[] {"Bor"},
                        null, null, null);

                //db.q

                if (cursor.getCount() > 0) {
                    //List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                    Log.i(TAG, "onClick: "+ cursor.getCount());
                    while (cursor.moveToNext()) {
                        Log.i(TAG, "onClick: "+ cursor.getString(1));
                        //Order order = parseOrder(cursor);
                        //orderList.add(order);
                    }
                    //return orderList;
                }

                Toast.makeText(FileTest001Activity.this, "信息已写入"+OrderDBHelper.TABLE_NAME+"SharedPreference中", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String read(String filename) throws IOException {
        //打开文件输入流
        FileInputStream input = FileTest001Activity.this.openFileInput(filename);
        byte[] temp = new byte[1024];
        StringBuilder sb = new StringBuilder("");
        int len = 0;
        //读取文件内容:
        while ((len = input.read(temp)) > 0) {
            sb.append(new String(temp, 0, len));
        }
        //关闭输入流
        input.close();
        return sb.toString();
    }

    protected String createOutPublicDir() {
        String fileOutName = Environment.getExternalStorageDirectory() + File.separator+"早起的虫儿"+File.separator+"有鸟吃";
        File filePublic = new File(fileOutName);   //   /storage/emulated/0/AutPublic/outDir
        if (!filePublic.exists()) {
            filePublic.mkdirs();
        }
        else
        {
            Log.i(TAG, "createOutPublicDir: "+filePublic.getAbsolutePath());
        }
        return fileOutName;
    }
}
