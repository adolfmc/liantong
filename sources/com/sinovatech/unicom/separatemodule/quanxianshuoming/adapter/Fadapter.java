package com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentStatePagerAdapter;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class Fadapter extends FragmentStatePagerAdapter {
    private List<Fragment> datas;
    private List<String> titles;

    public Fadapter(@NonNull FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
        super(fragmentManager);
        this.datas = list;
        this.titles = list2;
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter
    @NonNull
    public Fragment getItem(int i) {
        return this.datas.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.datas.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        return this.titles.get(i);
    }
}
