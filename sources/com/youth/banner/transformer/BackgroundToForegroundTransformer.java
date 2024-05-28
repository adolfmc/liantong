package com.youth.banner.transformer;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BackgroundToForegroundTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    protected void onTransform(View view, float f) {
        float height = view.getHeight();
        float width = view.getWidth();
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        float min = min(i >= 0 ? Math.abs(1.0f - f) : 1.0f, 0.5f);
        view.setScaleX(min);
        view.setScaleY(min);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(i < 0 ? width * f : (-width) * f * 0.25f);
    }
}
