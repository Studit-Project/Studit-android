package com.example.studit.search;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragSearchAdapter extends FragmentPagerAdapter {

    public static int PAGE_POSITION = 3;

    public FragSearchAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FragSearchStudy.newInstance();
            case 1:
                return FragSearchChallenge.newInstance();
            case 2:
                return FragSearchFree.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
