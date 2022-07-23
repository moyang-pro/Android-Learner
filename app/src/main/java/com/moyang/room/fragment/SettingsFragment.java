package com.moyang.room.fragment;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.moyang.room.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}