package com.sinovatech.unicom.separatemodule.videocenter.refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PlaneLoadDrawable extends PlaneDrawable {
    public PlaneLoadDrawable(Context context, PullRefreshLayout pullRefreshLayout) {
        super(context, pullRefreshLayout);
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.PlaneDrawable, com.sinovatech.unicom.separatemodule.videocenter.refresh.RefreshDrawable
    public void setPercent(float f) {
        this.mPercent = f;
        int centerX = getBounds().centerX();
        int i = getBounds().bottom;
        float f2 = centerX;
        this.rectF.left = f2 - (this.drawableMinddleWidth * this.mPercent);
        this.rectF.right = f2 + (this.drawableMinddleWidth * this.mPercent);
        float f3 = i;
        this.rectF.top = f3;
        this.rectF.bottom = f3 + (this.drawableMinddleWidth * 2 * this.mPercent);
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.PlaneDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        canvas.translate(0.0f, this.mOffset);
        canvas.drawBitmap(this.bitmaps.get((int) ((System.currentTimeMillis() / 50) % 11)), (Rect) null, this.rectF, (Paint) null);
        canvas.restoreToCount(save);
    }
}
