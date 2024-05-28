package com.megvii.idcardlib.util;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.rotate(90.0f, (getMeasuredWidth() * 10) / 25, (getMeasuredHeight() * 10) / 25);
        super.onDraw(canvas);
    }
}
