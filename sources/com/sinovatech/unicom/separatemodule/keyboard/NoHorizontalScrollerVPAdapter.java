package com.sinovatech.unicom.separatemodule.keyboard;

import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class NoHorizontalScrollerVPAdapter extends FragmentPagerAdapter {
    private List<Fragment> datas;

    public NoHorizontalScrollerVPAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager);
        this.datas = null;
        this.datas = list;
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.datas.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.datas.size();
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter, android.support.p083v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        super.destroyItem(viewGroup, i, obj);
    }
}
