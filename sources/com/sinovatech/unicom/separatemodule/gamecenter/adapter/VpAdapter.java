package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import cn.finalteam.toolsfinal.adapter.FragmentAdapter;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VpAdapter extends FragmentAdapter {
    private List<Fragment> list;

    public VpAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager, list);
        this.list = list;
    }

    @Override // cn.finalteam.toolsfinal.adapter.FragmentAdapter, android.support.p083v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.list.get(i);
    }

    @Override // cn.finalteam.toolsfinal.adapter.FragmentAdapter, android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.list.size();
    }
}
