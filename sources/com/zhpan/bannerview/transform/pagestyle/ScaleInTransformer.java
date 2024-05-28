package com.zhpan.bannerview.transform.pagestyle;

import android.annotation.TargetApi;
import android.support.p083v4.view.ViewPager;
import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ScaleInTransformer extends BasePageTransformer {
    public static final float DEFAULT_MIN_SCALE = 0.85f;
    public static final float MAX_SCALE = 0.999f;
    private float mMinScale;

    public ScaleInTransformer() {
        this.mMinScale = 0.85f;
    }

    public ScaleInTransformer(float f) {
        this(f, NonPageTransformer.INSTANCE);
    }

    public ScaleInTransformer(ViewPager.PageTransformer pageTransformer) {
        this(0.85f, pageTransformer);
    }

    public ScaleInTransformer(float f, ViewPager.PageTransformer pageTransformer) {
        this.mMinScale = 0.85f;
        this.mMinScale = f;
        this.mPageTransformer = pageTransformer;
    }

    @Override // com.zhpan.bannerview.transform.pagestyle.BasePageTransformer
    @TargetApi(11)
    public void pageTransform(View view, float f) {
        int width = view.getWidth();
        view.setPivotY(view.getHeight() / 2);
        view.setPivotX(width / 2);
        if (f < -1.0f) {
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
            view.setPivotX(width);
        } else if (f > 1.0f) {
            view.setPivotX(0.0f);
            view.setScaleX(this.mMinScale);
            view.setScaleY(this.mMinScale);
        } else if (f < 0.0f) {
            float f2 = this.mMinScale;
            float f3 = ((f + 1.0f) * (1.0f - f2)) + f2;
            view.setScaleX(f3);
            view.setScaleY(f3);
            view.setPivotX(width * (((-f) * 0.5f) + 0.5f));
        } else {
            float f4 = 1.0f - f;
            float f5 = this.mMinScale;
            float f6 = ((1.0f - f5) * f4) + f5;
            view.setScaleX(f6);
            view.setScaleY(f6);
            view.setPivotX(width * f4 * 0.5f);
        }
    }
}
