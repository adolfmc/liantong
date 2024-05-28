package com.sinovatech.unicom.basic.p315ui.home.adapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentStatePagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.adapter.UnicomMainHomePagerAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomMainHomePagerAdapter<F extends Fragment> extends FragmentStatePagerAdapter {
    private final List<F> mFragmentSet;
    private final List<CharSequence> mFragmentTitle;
    private boolean mLazyMode;
    private F mShowFragment;
    private ViewPager mViewPager;

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter, android.support.p083v4.view.PagerAdapter
    public Parcelable saveState() {
        return null;
    }

    public UnicomMainHomePagerAdapter(FragmentActivity fragmentActivity) {
        this(fragmentActivity.getSupportFragmentManager());
    }

    public UnicomMainHomePagerAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    public UnicomMainHomePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFragmentSet = new ArrayList();
        this.mFragmentTitle = new ArrayList();
        this.mLazyMode = true;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.mFragmentSet.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.mFragmentTitle.get(i);
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter
    public F getItem(int i) {
        return this.mFragmentSet.get(i);
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter, android.support.p083v4.view.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        if (getShowFragment() != obj) {
            this.mShowFragment = (F) obj;
        }
    }

    public void addFragment(F f) {
        addFragment(f, null);
    }

    public void addFragment(F f, CharSequence charSequence) {
        this.mFragmentSet.add(f);
        this.mFragmentTitle.add(charSequence);
        if (this.mViewPager == null) {
            return;
        }
        notifyDataSetChanged();
        if (this.mLazyMode) {
            this.mViewPager.setOffscreenPageLimit(getCount());
        } else {
            this.mViewPager.setOffscreenPageLimit(1);
        }
    }

    public String getCurrentTabTitle(int i) {
        List<CharSequence> list;
        return (i < 0 || (list = this.mFragmentTitle) == null || list.size() == 0 || i >= this.mFragmentTitle.size()) ? "" : this.mFragmentTitle.get(i).toString();
    }

    public List<CharSequence> getmFragmentTitle() {
        return this.mFragmentTitle;
    }

    public F getShowFragment() {
        return this.mShowFragment;
    }

    public int getFragmentIndex(Class<? extends Fragment> cls) {
        if (cls == null) {
            return -1;
        }
        for (int i = 0; i < this.mFragmentSet.size(); i++) {
            if (cls.getName().equals(this.mFragmentSet.get(i).getClass().getName())) {
                return i;
            }
        }
        return -1;
    }

    @Override // android.support.p083v4.app.FragmentStatePagerAdapter, android.support.p083v4.view.PagerAdapter
    public void startUpdate(@NonNull ViewGroup viewGroup) {
        super.startUpdate(viewGroup);
        if (viewGroup instanceof ViewPager) {
            this.mViewPager = (ViewPager) viewGroup;
            refreshLazyMode();
        }
    }

    public void setLazyMode(boolean z) {
        this.mLazyMode = z;
        refreshLazyMode();
    }

    private void refreshLazyMode() {
        ViewPager viewPager = this.mViewPager;
        if (viewPager == null) {
            return;
        }
        if (this.mLazyMode) {
            viewPager.setOffscreenPageLimit(getCount());
        } else {
            viewPager.setOffscreenPageLimit(1);
        }
    }
}
