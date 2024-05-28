package com.zhpan.bannerview.transform.pagestyle;

import android.annotation.TargetApi;
import android.support.p083v4.view.ViewPager;
import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class BasePageTransformer implements ViewPager.PageTransformer {
    public static final float DEFAULT_CENTER = 0.5f;
    protected ViewPager.PageTransformer mPageTransformer = NonPageTransformer.INSTANCE;

    protected abstract void pageTransform(View view, float f);

    @Override // android.support.p083v4.view.ViewPager.PageTransformer
    @TargetApi(11)
    public void transformPage(View view, float f) {
        ViewPager.PageTransformer pageTransformer = this.mPageTransformer;
        if (pageTransformer != null) {
            pageTransformer.transformPage(view, f);
        }
        pageTransform(view, f);
    }
}
