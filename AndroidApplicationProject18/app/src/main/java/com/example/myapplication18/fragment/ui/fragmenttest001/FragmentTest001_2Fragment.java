package com.example.myapplication18.fragment.ui.fragmenttest001;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication18.R;

public class FragmentTest001_2Fragment  extends Fragment {

    private FragmentTest001ViewModel mViewModel;

    private static final String TAG = "FragmentTest001_2Fragme";

    public static FragmentTest001_1Fragment newInstance() {
        return new FragmentTest001_1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test001_2_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(FragmentTest001ViewModel.class);
        // TODO: Use the ViewModel

        FragmentTest001ViewModel viewModel_activity=new ViewModelProvider(getActivity()).get(FragmentTest001ViewModel.class);
        Log.i(TAG, "onActivityCreated: "+viewModel_activity.getName());
    }

}