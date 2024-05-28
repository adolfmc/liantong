package com.sinovatech.unicom.basic.p315ui.adapter;

import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import com.sinovatech.unicom.basic.view.MeasureGridView;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.adapter.MianGridPagerAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MianGridPagerAdapter extends PagerAdapter {
    private List<MeasureGridView> gvs;

    @Override // android.support.p083v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public MianGridPagerAdapter(List<MeasureGridView> list) {
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
