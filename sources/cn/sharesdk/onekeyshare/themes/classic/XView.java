package cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class XView extends View {
    private float ratio;

    public XView(Context context) {
        super(context);
    }

    public void setRatio(float f) {
        this.ratio = f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-6250336);
        float width = getWidth() / 2;
        canvas.drawRect(width, 0.0f, getWidth(), getHeight() / 2, paint);
        Paint paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(this.ratio * 3.0f);
        paint2.setColor(-1);
        float f = this.ratio * 8.0f;
        float f2 = width + f;
        float f3 = width - f;
        canvas.drawLine(f2, f, getWidth() - f, f3, paint2);
        canvas.drawLine(f2, f3, getWidth() - f, f, paint2);
    }
}
