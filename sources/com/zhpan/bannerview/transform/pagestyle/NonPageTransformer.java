package com.zhpan.bannerview.transform.pagestyle;

import android.support.p083v4.view.ViewPager;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NonPageTransformer implements ViewPager.PageTransformer {
    public static final ViewPager.PageTransformer INSTANCE = new NonPageTransformer();

    @Override // android.support.p083v4.view.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        view.setScaleX(0.999f);
    }
}
