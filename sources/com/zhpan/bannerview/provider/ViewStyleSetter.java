package com.zhpan.bannerview.provider;

import android.support.annotation.RequiresApi;
import android.view.View;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ViewStyleSetter {
    private View mView;

    public ViewStyleSetter(View view) {
        this.mView = view;
    }

    @RequiresApi(api = 21)
    public void setRoundCorner(float f) {
        this.mView.setClipToOutline(true);
        this.mView.setOutlineProvider(new RoundViewOutlineProvider(f));
    }

    @RequiresApi(api = 21)
    public void setOval() {
        this.mView.setClipToOutline(true);
        this.mView.setOutlineProvider(new OvalViewOutlineProvider());
    }

    @RequiresApi(api = 21)
    public void clearShapeStyle() {
        this.mView.setClipToOutline(false);
    }
}
