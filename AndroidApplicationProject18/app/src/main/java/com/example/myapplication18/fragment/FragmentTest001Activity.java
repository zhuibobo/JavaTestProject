package com.example.myapplication18.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication18.R;
import com.example.myapplication18.fragment.ui.fragmenttest001.FragmentTest001ViewModel;
import com.example.myapplication18.fragment.ui.fragmenttest001.FragmentTest001_1Fragment;
import com.example.myapplication18.ui.main.PageViewModel;

public class FragmentTest001Activity extends AppCompatActivity {

    private static final String TAG = "FragmentTest001Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_test001_activity);

        FragmentTest001ViewModel viewModel_activity=new ViewModelProvider(this).get(FragmentTest001ViewModel.class);
        viewModel_activity.setName("FragmentTest001Activity");

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentTest001_1Fragment.newInstance())
                    .commitNow();
        }*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        //FragmentTest001ViewModel viewModel_activity=new ViewModelProvider(FragmentTest001_1Fragment.newInstance()).get(FragmentTest001ViewModel.class);
        Log.i(TAG, "onStart: ");
    }
}
