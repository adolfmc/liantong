package com.youth.banner.transformer;

import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class CubeInTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    public boolean isPagingEnabled() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    protected void onTransform(View view, float f) {
        view.setPivotX(f > 0.0f ? 0.0f : view.getWidth());
        view.setPivotY(0.0f);
        view.setRotationY(f * (-90.0f));
    }
}
