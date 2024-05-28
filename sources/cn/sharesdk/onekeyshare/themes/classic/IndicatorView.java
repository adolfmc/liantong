package cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class IndicatorView extends View {
    private static final int DESIGN_BOTTOM_HEIGHT = 52;
    private static final int DESIGN_INDICATOR_DISTANCE = 14;
    private static final int DESIGN_INDICATOR_RADIUS = 6;
    private int count;
    private int current;

    public IndicatorView(Context context) {
        super(context);
    }

    public void setScreenCount(int i) {
        this.count = i;
    }

    public void onScreenChange(int i, int i2) {
        if (i != this.current) {
            this.current = i;
            postInvalidate();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        if (this.count <= 1) {
            setVisibility(8);
            return;
        }
        float height = getHeight();
        float f = (6.0f * height) / 52.0f;
        float f2 = (14.0f * height) / 52.0f;
        float f3 = f * 2.0f;
        float width = (getWidth() - ((this.count * f3) + ((i - 1) * f2))) / 2.0f;
        float f4 = height / 2.0f;
        canvas.drawColor(-1);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        for (int i2 = 0; i2 < this.count; i2++) {
            if (i2 == this.current) {
                paint.setColor(-10653280);
            } else {
                paint.setColor(-5262921);
            }
            canvas.drawCircle(((f3 + f2) * i2) + width, f4, f, paint);
        }
    }
}
