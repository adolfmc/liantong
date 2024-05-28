package com.sinovatech.unicom.separatemodule.recentmenu.adapter;

import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentPagerAdapter;
import com.sinovatech.unicom.separatemodule.recentmenu.fragment.CollectionFragment;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CollectionViewPagerAdapter extends FragmentPagerAdapter {
    private List<CollectionFragment> fragmentList;
    private String[] mTitles;

    public CollectionViewPagerAdapter(FragmentManager fragmentManager, List<CollectionFragment> list, String[] strArr) {
        super(fragmentManager);
        this.fragmentList = new ArrayList();
        this.fragmentList = list;
        this.mTitles = strArr;
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.fragmentList.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    @Nullable
    public CharSequence getPageTitle(int i) {
        return this.mTitles[i];
    }
}
