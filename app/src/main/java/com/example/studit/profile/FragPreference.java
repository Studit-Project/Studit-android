package com.example.studit.profile;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.studit.R;

class FragPreference extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preference, rootKey);
    }
}

