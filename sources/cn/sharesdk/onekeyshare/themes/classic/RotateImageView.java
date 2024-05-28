package cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.widget.ImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RotateImageView extends ImageView {
    private float rotation;

    public RotateImageView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void setRotation(float f) {
        this.rotation = f;
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        canvas.rotate(this.rotation, getWidth() / 2, getHeight() / 2);
        super.onDraw(canvas);
    }
}
