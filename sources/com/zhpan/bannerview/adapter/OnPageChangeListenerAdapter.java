package com.zhpan.bannerview.adapter;

import android.support.p083v4.view.ViewPager;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class OnPageChangeListenerAdapter implements ViewPager.OnPageChangeListener {
    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        NBSActionInstrumentation.onPageSelectedExit();
    }
}
