package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

import android.support.p083v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> mViewList;

    @Override // android.support.p083v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public MyViewPagerAdapter(List<View> list) {
        this.mViewList = list;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView(this.mViewList.get(i));
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView(this.mViewList.get(i));
        return this.mViewList.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        List<View> list = this.mViewList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
