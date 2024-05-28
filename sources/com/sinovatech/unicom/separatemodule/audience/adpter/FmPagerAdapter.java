package com.sinovatech.unicom.separatemodule.audience.adpter;

import android.support.annotation.NonNull;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentPagerAdapter;
import com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FmPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;

    @Override // android.support.p083v4.view.PagerAdapter
    public int getItemPosition(@NonNull Object obj) {
        return -2;
    }

    public FmPagerAdapter(List<Fragment> list, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.fragmentList = list;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        List<Fragment> list = this.fragmentList;
        if (list == null || list.isEmpty()) {
            return 0;
        }
        return this.fragmentList.size();
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        return this.fragmentList.get(i);
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public long getItemId(int i) {
        Fragment item = getItem(i);
        if (item instanceof BaseFragment) {
            try {
                return ((BaseFragment) item).getFragmentId();
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
        return super.getItemId(i);
    }
}
