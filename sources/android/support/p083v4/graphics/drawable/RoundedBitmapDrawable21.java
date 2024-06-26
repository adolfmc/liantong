package android.support.p083v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Outline;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.view.Gravity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(21)
/* renamed from: android.support.v4.graphics.drawable.RoundedBitmapDrawable21 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RoundedBitmapDrawable21 extends RoundedBitmapDrawable {
    /* JADX INFO: Access modifiers changed from: protected */
    public RoundedBitmapDrawable21(Resources resources, Bitmap bitmap) {
        super(resources, bitmap);
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        updateDstRect();
        outline.setRoundRect(this.mDstRect, getCornerRadius());
    }

    @Override // android.support.p083v4.graphics.drawable.RoundedBitmapDrawable
    public void setMipMap(boolean z) {
        if (this.mBitmap != null) {
            this.mBitmap.setHasMipMap(z);
            invalidateSelf();
        }
    }

    @Override // android.support.p083v4.graphics.drawable.RoundedBitmapDrawable
    public boolean hasMipMap() {
        return this.mBitmap != null && this.mBitmap.hasMipMap();
    }

    @Override // android.support.p083v4.graphics.drawable.RoundedBitmapDrawable
    void gravityCompatApply(int i, int i2, int i3, Rect rect, Rect rect2) {
        Gravity.apply(i, i2, i3, rect, rect2, 0);
    }
}
