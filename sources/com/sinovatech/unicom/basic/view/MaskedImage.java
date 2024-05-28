package com.sinovatech.unicom.basic.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.io.PrintStream;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class MaskedImage extends ImageView {
    private static final Xfermode MASK_XFERMODE = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private Bitmap mask;
    private Paint paint;

    public abstract Bitmap createMask();

    public MaskedImage(Context context) {
        super(context);
    }

    public MaskedImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MaskedImage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        try {
            if (this.paint == null) {
                this.paint = new Paint();
                this.paint.setFilterBitmap(false);
                this.paint.setXfermode(MASK_XFERMODE);
            }
            int saveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
            drawable.setBounds(0, 0, getWidth(), getHeight());
            drawable.draw(canvas);
            if (this.mask == null || this.mask.isRecycled()) {
                this.mask = createMask();
            }
            canvas.drawBitmap(this.mask, 0.0f, 0.0f, this.paint);
            canvas.restoreToCount(saveLayer);
        } catch (Exception unused) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempting to draw with recycled bitmap. View ID = ");
            PrintStream printStream = System.out;
            printStream.println("localStringBuilder==" + ((Object) sb));
        }
    }
}
