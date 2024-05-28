package cn.finalteam.toolsfinal.adapter;

import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentPagerAdapter;
import java.util.List;

/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private List<String> mTabList;

    public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> list) {
        this(fragmentManager, list, null);
    }

    public FragmentAdapter(FragmentManager fragmentManager, List<Fragment> list, List<String> list2) {
        super(fragmentManager);
        this.mFragmentList = list;
        this.mTabList = list2;
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.mFragmentList.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.mFragmentList.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.mTabList.get(i);
    }
}
