package com.sinovatech.unicom.separatemodule.audience.view;

import android.support.p086v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.billy.android.swipe.SmartSwipeRefresh;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LiveNoMoreDataFooter implements SmartSwipeRefresh.SmartSwipeRefreshFooter {
    private AppCompatActivity activity;

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onDataLoading() {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public long onFinish(boolean z) {
        return 0L;
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onInit(boolean z) {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onProgress(boolean z, float f) {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onReset() {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public void onStartDragging() {
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshFooter
    public void setNoMoreData(boolean z) {
    }

    public LiveNoMoreDataFooter(AppCompatActivity appCompatActivity) {
        this.activity = appCompatActivity;
    }

    @Override // com.billy.android.swipe.SmartSwipeRefresh.RefreshView
    public View getView() {
        return LayoutInflater.from(this.activity).inflate(2131493290, (ViewGroup) null);
    }
}
