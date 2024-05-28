package com.youth.banner.transformer;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RotateDownTransformer extends ABaseTransformer {
    private static final float ROT_MOD = -15.0f;

    @Override // com.youth.banner.transformer.ABaseTransformer
    protected boolean isPagingEnabled() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    protected void onTransform(View view, float f) {
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight());
        view.setRotation(f * ROT_MOD * (-1.25f));
    }
}
