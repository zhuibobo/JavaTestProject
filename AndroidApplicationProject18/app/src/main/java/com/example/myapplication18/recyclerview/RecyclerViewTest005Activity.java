package com.example.myapplication18.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication18.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest005Activity extends AppCompatActivity {

    private RecyclerView rvCompany;
    private RecyclerViewTest005Activity_TreeAdapter customerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test005);

        rvCompany = findViewById(R.id.rv_company);
        rvCompany.setLayoutManager(new LinearLayoutManager(this));

        List<RecyclerViewTest005Activity_TreeData> companyList=GetData();

        customerAdapter = new RecyclerViewTest005Activity_TreeAdapter(this,companyList);
        customerAdapter.setOnItemClickListener(new RecyclerViewTest005Activity_TreeAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(RecyclerViewTest005Activity_TreeData company)
            {
                Toast.makeText(RecyclerViewTest005Activity.this,company.toString(),Toast.LENGTH_SHORT).show();
                //TODO 进行相关操作
            }
        });
        rvCompany.setAdapter(customerAdapter);
    }

    private List<RecyclerViewTest005Activity_TreeData> GetData() {
        List<RecyclerViewTest005Activity_TreeData> treeDataList=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RecyclerViewTest005Activity_TreeData treeDataL0=new RecyclerViewTest005Activity_TreeData(String.valueOf(i),"name"+i,0,"0",true);
            treeDataList.add(treeDataL0);
            for (int j = 0; j < 5; j++) {
                RecyclerViewTest005Activity_TreeData treeDataL1=new RecyclerViewTest005Activity_TreeData(String.valueOf(i*j),"name"+i*j,1,treeDataL0.getCompanyID(),true);
                treeDataList.add(treeDataL1);

                for (int k = 0; k < 5; k++) {
                    RecyclerViewTest005Activity_TreeData treeDataL2=new RecyclerViewTest005Activity_TreeData(String.valueOf(i*j*k),"name"+i*j*k,2,treeDataL1.getCompanyID(),false);
                    treeDataList.add(treeDataL2);
                }
            }
        }
        return treeDataList;
    }
}
