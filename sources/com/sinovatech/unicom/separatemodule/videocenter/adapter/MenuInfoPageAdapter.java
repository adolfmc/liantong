package com.sinovatech.unicom.separatemodule.videocenter.adapter;

import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import com.sinovatech.unicom.separatemodule.videocenter.entity.TabEntity;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class MenuInfoPageAdapter extends FragmentPagerAdapter {
    Map<String, Integer> IdsMap;
    List<TabEntity> data;

    /* renamed from: id */
    int f18630id;
    List<String> preIds;

    public MenuInfoPageAdapter(FragmentManager fragmentManager, List<TabEntity> list) {
        super(fragmentManager);
        this.f18630id = 1;
        this.IdsMap = new HashMap();
        this.preIds = new ArrayList();
        this.data = list == null ? new ArrayList<>() : list;
        notifyDataSetChanged();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.data.size();
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        TabEntity tabEntity = this.data.get(i);
        if (tabEntity.getPageName().equals("视频彩铃")) {
            VideoRingFragment videoRingFragment = new VideoRingFragment();
            videoRingFragment.setPageName(tabEntity.getPageName());
            return videoRingFragment;
        }
        VideoCommonFragment videoCommonFragment = new VideoCommonFragment();
        videoCommonFragment.setPageName(tabEntity.getPageName());
        videoCommonFragment.setCode(tabEntity.getSubCode());
        return videoCommonFragment;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public CharSequence getPageTitle(int i) {
        return this.data.get(i).getPageName();
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter, android.support.p083v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        return super.instantiateItem(viewGroup, i);
    }

    @Override // android.support.p083v4.app.FragmentPagerAdapter
    public long getItemId(int i) {
        return this.IdsMap.get(getPageTitle(i)).intValue();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getItemPosition(Object obj) {
        String pageName;
        if (obj instanceof VideoCommonFragment) {
            pageName = ((VideoCommonFragment) obj).getPageName();
        } else {
            pageName = ((VideoRingFragment) obj).getPageName();
        }
        int indexOf = this.preIds.indexOf(pageName);
        int i = 0;
        int count = getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            } else if (getPageTitle(i).equals(pageName)) {
                break;
            } else {
                i++;
            }
        }
        if (i != -1 && i == indexOf) {
            Log.i("zgh", "title=" + pageName + " POSITION_UNCHANGED");
            return -1;
        } else if (i != -1) {
            Log.i("zgh", "title=" + pageName + " newId=" + i);
            return i;
        } else {
            Log.i("zgh", "title=" + pageName + " POSITION_NONE");
            return -2;
        }
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void notifyDataSetChanged() {
        for (TabEntity tabEntity : this.data) {
            if (!this.IdsMap.containsKey(tabEntity.getPageName())) {
                Map<String, Integer> map = this.IdsMap;
                String pageName = tabEntity.getPageName();
                int i = this.f18630id;
                this.f18630id = i + 1;
                map.put(pageName, Integer.valueOf(i));
            }
        }
        super.notifyDataSetChanged();
        this.preIds.clear();
        int count = getCount();
        for (int i2 = 0; i2 < count; i2++) {
            this.preIds.add((String) getPageTitle(i2));
        }
    }
}
