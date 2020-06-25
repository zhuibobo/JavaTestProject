package com.example.myapplication18;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class RecyclerViewTest003Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test003);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewTest003Activity_MyAdapter(getMyDataset());
        recyclerView.addItemDecoration(new RecyclerViewTest003Activity_DemoItemDecoration());
        recyclerView.setAdapter(mAdapter);
    }

    public String[] getMyDataset(){
        return new String[]{"alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses","alex", "sa","ses"};
    }
}
