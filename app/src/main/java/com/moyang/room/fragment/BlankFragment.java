package com.moyang.room.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moyang.room.R;

/**
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    private static final String TAG = "BlankFragment";
    private View mRootView;


    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate start ... ");
        Bundle arguments = getArguments();
        assert arguments != null;
        Toast.makeText(getContext(), arguments.getString("moyang", "暂无消息"), Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate end ... ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView start ... ");
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_blank, container, false);

        Log.d(TAG, "onCreateView end ... ");
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(TAG, "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        Log.d(TAG, "onMultiWindowModeChanged");
        super.onMultiWindowModeChanged(isInMultiWindowMode);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
    }


    @Override
    public void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
    }


    @Override
    public void onDestroyView() {
        Log.d(TAG, "onDestroyView");
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach");
        super.onDetach();
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.d(TAG, "onCreateOptionsMenu");
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        Log.d(TAG, "onPrepareOptionsMenu");
        super.onPrepareOptionsMenu(menu);
    }

}