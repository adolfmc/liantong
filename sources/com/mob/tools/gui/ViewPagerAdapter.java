package com.mob.tools.gui;

import android.view.View;
import android.view.ViewGroup;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class ViewPagerAdapter {
    private MobViewPager parent;

    public abstract int getCount();

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    public void onScreenChange(int i, int i2) {
    }

    public void onScreenChanging(float f) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setMobViewPager(MobViewPager mobViewPager) {
        this.parent = mobViewPager;
    }

    public void invalidate() {
        MobViewPager mobViewPager = this.parent;
        if (mobViewPager != null) {
            mobViewPager.setAdapter(this);
        }
    }
}
