package com.zhpan.bannerview.transform;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RotateUpTransformer extends BaseTransformer {
    private static final float ROT_MOD = -15.0f;

    @Override // com.zhpan.bannerview.transform.BaseTransformer
    protected boolean isPagingEnabled() {
        return true;
    }

    @Override // com.zhpan.bannerview.transform.BaseTransformer
    protected void onTransform(View view, float f) {
        float f2 = f * ROT_MOD;
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(0.0f);
        view.setTranslationX(0.0f);
        view.setRotation(f2);
    }
}
