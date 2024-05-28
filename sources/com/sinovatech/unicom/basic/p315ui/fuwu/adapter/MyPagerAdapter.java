package com.sinovatech.unicom.basic.p315ui.fuwu.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.adapter.MyPagerAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private static List<Fragment> fragmentList = new ArrayList();
    private static List<String> titleList = new ArrayList();

    @Override // android.support.p083v4.view.PagerAdapter
    public int getItemPosition(@NonNull Object obj) {
        return -2;
    }

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return fragmentList.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        return titleList.get(i);
    }

    public void removeFragment() {
        fragmentList.clear();
        titleList.clear();
        notifyDataSetChanged();
        notifyDataSetChanged();
    }

    public void addFragment(Fragment fragment, String str) {
        fragmentList.add(fragment);
        titleList.add(str);
        notifyDataSetChanged();
    }
}
