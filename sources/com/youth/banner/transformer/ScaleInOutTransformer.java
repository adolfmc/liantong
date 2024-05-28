package com.youth.banner.transformer;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ScaleInOutTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    protected void onTransform(View view, float f) {
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        view.setPivotX(i >= 0 ? view.getWidth() : 0.0f);
        view.setPivotY(view.getHeight() / 2.0f);
        float f2 = i < 0 ? f + 1.0f : 1.0f - f;
        view.setScaleX(f2);
        view.setScaleY(f2);
    }
}
