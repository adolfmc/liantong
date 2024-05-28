package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FadingLayout extends RelativeLayout {
    private int alpha;

    public FadingLayout(Context context) {
        super(context);
        this.alpha = 255;
        setWillNotDraw(false);
    }

    public FadingLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.alpha = 255;
        setWillNotDraw(false);
    }

    public void setAlpha(int i) {
        this.alpha = i;
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        canvas.saveLayerAlpha(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), this.alpha, 31);
        super.onDraw(canvas);
    }
}
