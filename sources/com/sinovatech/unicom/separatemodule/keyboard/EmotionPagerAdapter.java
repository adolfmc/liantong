package com.sinovatech.unicom.separatemodule.keyboard;

import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class EmotionPagerAdapter extends PagerAdapter {
    private List<GridView> gvs;

    @Override // android.support.p083v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public EmotionPagerAdapter(List<GridView> list) {
        this.gvs = list;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.gvs.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ((ViewPager) viewGroup).removeView(this.gvs.get(i));
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ((ViewPager) viewGroup).addView(this.gvs.get(i));
        return this.gvs.get(i);
    }
}
