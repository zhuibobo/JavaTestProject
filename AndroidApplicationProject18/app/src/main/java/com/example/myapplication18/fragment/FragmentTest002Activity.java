package com.example.myapplication18.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.myapplication18.R;
import com.example.myapplication18.fragment.ui.fragmenttest001.FragmentTest001_1Fragment;
import com.example.myapplication18.fragment.ui.fragmenttest001.FragmentTest001_2Fragment;

public class FragmentTest002Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test002);

        Fragment f2 = new FragmentTest001_2Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,f2).commit();

        FragmentTest001_1Fragment f1 = new FragmentTest001_1Fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,f1).commit();

        //f1.setArguments();
        //f1.getDa
        //getFragmentManager().beginTransaction().replace(R.id.fragment, f1).commit();
    }
}
