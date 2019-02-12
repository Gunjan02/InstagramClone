package com.example.admin.instagramclone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionsStatePagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList=new ArrayList<>();
    private final HashMap<Fragment,Integer> mFragments=new HashMap<>();
    private final HashMap<String,Integer> mFrgamentNumbers=new HashMap<>();
    private final HashMap<Integer,String> mFragmentNames=new HashMap<>();
    public SectionsStatePagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment,String fragmentName){
        mFragmentList.add(fragment);
        mFragments.put(fragment,mFragmentList.size()-1);
        mFrgamentNumbers.put(fragmentName,mFragmentList.size());
        mFragmentNames.put(mFragmentList.size()-1,fragmentName);

    }

    public Integer getFragmentNumber(Fragment fragment){
        if(mFrgamentNumbers.containsKey(fragment)){
            return mFrgamentNumbers.get(fragment);
        }else {
            return null;
        }
    }

    public Integer getFragmentName(Fragment fragmentNumber){
        if(mFrgamentNumbers.containsKey(fragmentNumber)){
            return mFrgamentNumbers.get(fragmentNumber);
        }else {
            return null;
        }
    }
}
