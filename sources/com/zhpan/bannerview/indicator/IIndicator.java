package com.zhpan.bannerview.indicator;

import android.support.p083v4.view.ViewPager;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IIndicator extends ViewPager.OnPageChangeListener {
    void notifyDataChanged();

    void setCheckedColor(int i);

    void setIndicatorGap(int i);

    void setIndicatorWidth(int i, int i2);

    void setNormalColor(int i);

    void setPageSize(int i);

    void setSlideMode(int i);
}
