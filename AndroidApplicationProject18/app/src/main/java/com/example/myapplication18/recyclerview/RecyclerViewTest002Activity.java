package com.example.myapplication18.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.myapplication18.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest002Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewTest002Activity_RvAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test002);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewTest002Activity_RvAdapter(getMyDataset());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(RecyclerViewTest002Activity.this, getMyDataset().get(position).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<RecyclerViewTest002Activity_RvAdapterData> getMyDataset(){
        List<RecyclerViewTest002Activity_RvAdapterData> data=new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            data.add(new RecyclerViewTest002Activity_RvAdapterData(i%2,i+"=xy"));
        }
        return data;
    }
}
