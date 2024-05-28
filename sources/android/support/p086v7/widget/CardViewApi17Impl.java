package android.support.p086v7.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.RequiresApi;
import android.support.p086v7.widget.RoundRectDrawableWithShadow;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(17)
/* renamed from: android.support.v7.widget.CardViewApi17Impl */
/* loaded from: E:\10201592_dexfile_execute.dex */
class CardViewApi17Impl extends CardViewBaseImpl {
    @Override // android.support.p086v7.widget.CardViewBaseImpl, android.support.p086v7.widget.CardViewImpl
    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new RoundRectDrawableWithShadow.RoundRectHelper() { // from class: android.support.v7.widget.CardViewApi17Impl.1
            @Override // android.support.p086v7.widget.RoundRectDrawableWithShadow.RoundRectHelper
            public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
                canvas.drawRoundRect(rectF, f, f, paint);
            }
        };
    }
}
