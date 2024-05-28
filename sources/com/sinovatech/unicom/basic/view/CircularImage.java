package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CircularImage extends MaskedImage {
    public CircularImage(Context context) {
        super(context);
    }

    public CircularImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CircularImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.sinovatech.unicom.basic.view.MaskedImage
    public Bitmap createMask() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint(1);
        paint.setColor(-16777216);
        canvas.drawOval(new RectF(0.0f, 0.0f, getWidth(), getHeight()), paint);
        return createBitmap;
    }
}
