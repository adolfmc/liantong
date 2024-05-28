package com.sinovatech.unicom.separatemodule.audience.view.heart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HonorHeartView extends ImageView {
    private static Bitmap sHeart;
    private static Bitmap sHeartBorder;
    private int mHeartBorderResId;
    private int mHeartResId;
    private static final Paint sPaint = new Paint(3);
    private static final Canvas sCanvas = new Canvas();

    public HonorHeartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHeartResId = 2131230972;
        this.mHeartBorderResId = 2131230976;
    }

    public HonorHeartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHeartResId = 2131230972;
        this.mHeartBorderResId = 2131230976;
    }

    public HonorHeartView(Context context) {
        super(context);
        this.mHeartResId = 2131230972;
        this.mHeartBorderResId = 2131230976;
    }

    public void setColor(int i) {
        setImageDrawable(new BitmapDrawable(getResources(), createHeart(i)));
    }

    public void setColorAndDrawables(int i, int i2, int i3) {
        if (i2 != this.mHeartResId) {
            sHeart = null;
        }
        if (i3 != this.mHeartBorderResId) {
            sHeartBorder = null;
        }
        this.mHeartResId = i2;
        this.mHeartBorderResId = i3;
        setColor(i);
    }

    public Bitmap createHeart(int i) {
        if (sHeart == null) {
            sHeart = BitmapFactory.decodeResource(getResources(), this.mHeartResId);
        }
        Bitmap bitmap = sHeart;
        Bitmap createBitmapSafely = createBitmapSafely(bitmap.getWidth(), bitmap.getHeight());
        if (createBitmapSafely == null) {
            return null;
        }
        Canvas canvas = sCanvas;
        canvas.setBitmap(createBitmapSafely);
        Paint paint = sPaint;
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        paint.setColorFilter(null);
        canvas.setBitmap(null);
        return createBitmapSafely;
    }

    private static Bitmap createBitmapSafely(int i, int i2) {
        try {
            return Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }
    }
}
