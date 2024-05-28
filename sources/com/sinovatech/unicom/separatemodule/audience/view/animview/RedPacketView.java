package com.sinovatech.unicom.separatemodule.audience.view.animview;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.util.Random;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RedPacketView extends View {
    private float amount;
    private int height;
    private boolean isClicked;
    private Paint paint;
    private RedPackClickListener redPackClickListener;
    private Bitmap redPacketBitmap;
    private int width;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface RedPackClickListener {
        void click();
    }

    public RedPacketView(Context context) {
        this(context, null);
    }

    public RedPacketView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RedPacketView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.width = 100;
        this.height = 100;
        init();
    }

    private void init() {
        this.paint = new Paint(1);
        this.redPacketBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), 2131232110), (int) dpToPixel(this.width - 20), (int) dpToPixel(this.height - 20), false);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension((int) dpToPixel(this.width), (int) dpToPixel(this.height));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(this.redPacketBitmap, 0.0f, 0.0f, this.paint);
    }

    private void setRedPacketBitmap() {
        this.redPacketBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), 2131232112), (int) dpToPixel(this.width), (int) dpToPixel(this.height), false);
        invalidate();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0 && !isClicked()) {
            setClicked(true);
            setRedPacketBitmap();
            RedPackClickListener redPackClickListener = this.redPackClickListener;
            if (redPackClickListener != null) {
                redPackClickListener.click();
            }
        }
        return true;
    }

    public float getAmount() {
        return this.amount;
    }

    public void setAmount(float f) {
        this.amount = f;
    }

    public boolean isClicked() {
        return this.isClicked;
    }

    public void setClicked(boolean z) {
        this.isClicked = z;
    }

    public void setRedPackClick(RedPackClickListener redPackClickListener) {
        this.redPackClickListener = redPackClickListener;
    }

    public static float dpToPixel(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    public static int[] getWindowWidthAndHeight(Context context) {
        WindowManager windowManager = ((Activity) context).getWindowManager();
        return new int[]{windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight()};
    }

    public static float getRandomFloat(int i) {
        return new Random().nextInt(i);
    }

    public static int getRandomInt(int i) {
        return new Random().nextInt(i);
    }
}
