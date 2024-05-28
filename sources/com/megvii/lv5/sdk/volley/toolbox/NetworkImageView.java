package com.megvii.lv5.sdk.volley.toolbox;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class NetworkImageView extends ImageView {

    /* renamed from: a */
    public int f13729a;

    /* renamed from: b */
    public int f13730b;

    public NetworkImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NetworkImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        boolean z3;
        super.onLayout(z, i, i2, i3, i4);
        int width = getWidth();
        int height = getHeight();
        getScaleType();
        boolean z4 = true;
        if (getLayoutParams() != null) {
            z2 = getLayoutParams().width == -2;
            z3 = getLayoutParams().height == -2;
        } else {
            z2 = false;
            z3 = false;
        }
        if (!z2 || !z3) {
            z4 = false;
        }
        if (width == 0 && height == 0 && !z4) {
            return;
        }
        if (TextUtils.isEmpty(null)) {
            int i5 = this.f13729a;
            if (i5 != 0) {
                setImageResource(i5);
                return;
            } else {
                setImageBitmap(null);
                return;
            }
        }
        throw null;
    }

    public void setDefaultImageResId(int i) {
        this.f13729a = i;
    }

    public void setErrorImageResId(int i) {
        this.f13730b = i;
    }
}
