package com.mob.tools.gui;

import android.content.Context;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class PullToRequestAdatper {
    private Context context;
    private PullToRequestView parent;

    public abstract Scrollable getBodyView();

    public abstract View getFooterView();

    public abstract View getHeaderView();

    public abstract boolean isPullDownReady();

    public abstract boolean isPullUpReady();

    public void onPullDown(int i) {
    }

    public void onPullUp(int i) {
    }

    public void onRefresh() {
    }

    public void onRequestNext() {
    }

    public void onReversed() {
    }

    public PullToRequestAdatper(PullToRequestView pullToRequestView) {
        this.context = pullToRequestView.getContext();
        this.parent = pullToRequestView;
    }

    public Context getContext() {
        return this.context;
    }

    protected PullToRequestView getParent() {
        return this.parent;
    }

    public void notifyDataSetChanged() {
        this.parent.stopPulling();
    }
}
