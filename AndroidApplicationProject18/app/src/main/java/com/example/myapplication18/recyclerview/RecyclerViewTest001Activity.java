package com.example.myapplication18.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication18.R;

public class RecyclerViewTest001Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewTest001Activity_MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private int mLastVisibleItemPosition;
    private RecyclerView.OnScrollListener monScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {


            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (mAdapter != null) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == mAdapter.getItemCount()) {
                    Toast.makeText(RecyclerViewTest001Activity.this,"发送网络请求获取更多数据",Toast.LENGTH_SHORT).show();
                    mAdapter.setmDataset(getMyDataset(40));
                    mAdapter.notifyDataSetChanged();
                    //发送网络请求获取更多数据
                    //sendMoreRequest();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test001);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecyclerViewTest001Activity_MyAdapter(getMyDataset(20));
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(monScrollListener);
        //recyclerView.addOnItemTouchListener();
    }

    public String[] getMyDataset(int length){
        String[] data=new String[length];
        for (int i = 0; i < length; i++) {
            data[i]=String.valueOf(i);
        }
        return data;
    }
}
