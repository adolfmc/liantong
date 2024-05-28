package com.p281qq.p282e.ads.nativ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.p281qq.p282e.comm.util.GDTLogger;

/* renamed from: com.qq.e.ads.nativ.MediaView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class MediaView extends FrameLayout {
    public MediaView(Context context) {
        super(context);
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    @SuppressLint({"NewApi"})
    protected void onAttachedToWindow() {
        GDTLogger.m8235d("onAttachedToWindow");
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT < 11 || isHardwareAccelerated()) {
            return;
        }
        GDTLogger.m8231w("硬件加速未开启");
    }
}
