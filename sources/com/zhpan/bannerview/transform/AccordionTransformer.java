package com.zhpan.bannerview.transform;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AccordionTransformer extends BaseTransformer {
    @Override // com.zhpan.bannerview.transform.BaseTransformer
    protected void onTransform(View view, float f) {
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        view.setPivotX(i >= 0 ? view.getWidth() : 0.0f);
        view.setScaleX(i < 0 ? f + 1.0f : 1.0f - f);
    }
}
