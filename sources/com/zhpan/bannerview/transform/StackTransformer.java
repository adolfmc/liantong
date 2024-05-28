package com.zhpan.bannerview.transform;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class StackTransformer extends BaseTransformer {
    @Override // com.zhpan.bannerview.transform.BaseTransformer
    protected void onTransform(View view, float f) {
        view.setTranslationX(f >= 0.0f ? (-view.getWidth()) * f : 0.0f);
    }
}
