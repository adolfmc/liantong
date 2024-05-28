package com.liang.jtablayout.indicator;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

@SuppressLint({"WrongConstant"})
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class JIndicator extends Indicator {
    public static final int TYPE_LINE = 0;
    public static final int TYPE_OVAL = 4;
    public static final int TYPE_RECT = 1;
    public static final int TYPE_RING = 3;
    public static final int TYPE_TRIANGLE = 2;
    private int type = 0;
    private int radius = 0;

    public JIndicator setType(int i) {
        this.type = i;
        return this;
    }

    @Override // com.liang.jtablayout.indicator.Indicator
    public Drawable getIndicator() {
        return createGradientDrawable();
    }

    public JIndicator setRadius(int i) {
        this.radius = i;
        return this;
    }

    private Drawable createGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getColor());
        if (this.type != 2) {
            gradientDrawable.setColor(getColor());
            int i = this.type;
            if (i == 0 || i == 1) {
                gradientDrawable.setShape(0);
                gradientDrawable.setCornerRadius(this.radius);
            }
            if (this.type == 4) {
                gradientDrawable.setShape(1);
            }
            if (this.type == 3) {
                gradientDrawable.setShape(3);
            }
        }
        return gradientDrawable;
    }
}
