package com.youth.banner.transformer;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class FlipHorizontalTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    protected void onTransform(View view, float f) {
        float f2 = f * 180.0f;
        view.setAlpha((f2 > 90.0f || f2 < -90.0f) ? 0.0f : 1.0f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.youth.banner.transformer.ABaseTransformer
    public void onPostTransform(View view, float f) {
        super.onPostTransform(view, f);
        if (f > -0.5f && f < 0.5f) {
            view.setVisibility(0);
        } else {
            view.setVisibility(4);
        }
    }
}
