package com.sinovatech.unicom.separatemodule.gamecenter.adapter;

import android.support.annotation.NonNull;
import android.support.p083v4.view.PagerAdapter;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HotViewPagerAdapter extends PagerAdapter {
    private AppCompatActivity activityContext;
    private List<HotGameAdapter> adapterList = new ArrayList();
    private AdapterView.OnItemClickListener listener;
    private List<List<Map<String, HotGamesEntity.HotGame>>> pagerList;

    @Override // android.support.p083v4.view.PagerAdapter
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    public HotViewPagerAdapter(AppCompatActivity appCompatActivity, List<List<Map<String, HotGamesEntity.HotGame>>> list) {
        this.pagerList = list;
        this.activityContext = appCompatActivity;
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public int getCount() {
        return this.pagerList.size();
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2131493212, (ViewGroup) null);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(2131298402);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.activityContext, 0, false));
        HotGameAdapter hotGameAdapter = new HotGameAdapter(this.activityContext, this.pagerList.get(i));
        recyclerView.setAdapter(hotGameAdapter);
        this.adapterList.add(hotGameAdapter);
        viewGroup.addView(inflate);
        return inflate;
    }

    public HotGameAdapter getItemAdapter(int i) {
        return this.adapterList.get(i);
    }

    @Override // android.support.p083v4.view.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        viewGroup.removeView((View) obj);
    }
}
