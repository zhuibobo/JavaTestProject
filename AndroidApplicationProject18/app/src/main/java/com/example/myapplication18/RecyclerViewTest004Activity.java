package com.example.myapplication18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest004Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewTest004Activity_MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test004);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewTest004Activity_MyAdapter(RecyclerViewTest004Activity.this);
        mAdapter.setLists(getMyDataset());
        recyclerView.setAdapter(mAdapter);
    }

    public List<RecyclerViewTest004Activity_MyAdapterData> getMyDataset(){
        List<RecyclerViewTest004Activity_MyAdapterData> data=new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            data.add(new RecyclerViewTest004Activity_MyAdapterData("name"+i,i%2==0?"男":"女",i,"主要的代码是 ViewHolder 中 bindView() , onClick() 这两个方法对变量 opened 的操作.主要的代码是 ViewHolder 中 bindView() , onClick() 这两个方法对变量 opened 的操作.主要的代码是 ViewHolder 中 bindView() , onClick() 这两个方法对变量 opened 的操作."));
        }
        return data;
    }
}
