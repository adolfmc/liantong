package com.sinovatech.unicom.separatemodule.videocenter.adapter;

import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.sinovatech.unicom.separatemodule.videocenter.entity.TabEntity;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VpAdapter extends FragmentStatePagerAdapter {
    private List<TabEntity> mList;
    SparseArray<WeakReference<Fragment>> registeredFragments;

    @Override // android.support.p083v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    public VpAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.registeredFragments = new SparseArray<>();
        this.mList = new ArrayList();
    }

    public VpAdapter(FragmentManager fragmentManager, List<TabEntity> list) {
        this(fragmentManager);
        updateList(list);
    }

    public void updateList(List<TabEntity> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    public List<TabEntity> getData() {
        return this.mList;
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        TabEntity tabEntity = this.mList.get(i);
        if (tabEntity.getPageName().equals("视频彩铃")) {
            return new VideoRingFragment();
        }
        if (tabEntity.getPageName().equals("直播")) {
            return new ZhiBoFragment();
        }
        VideoCommonFragment videoCommonFragment = new VideoCommonFragment();
        videoCommonFragment.setCode(tabEntity.getSubCode());
        return videoCommonFragment;
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter, android.support.p083v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        this.registeredFragments.put(i, new WeakReference<>(fragment));
        return fragment;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.mList.size();
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter, android.support.p083v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        this.registeredFragments.remove(i);
        super.destroyItem(viewGroup, i, obj);
    }

    public void remove(int i) {
        this.mList.remove(i);
        notifyDataSetChanged();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.mList.get(i).getPageName();
    }

    public Fragment getRegisteredFragment(int i) {
        return this.registeredFragments.get(i).get();
    }
}
