package cn.sharesdk.framework.utils;

import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ViewRound {
    /* renamed from: a */
    public static ShapeDrawable m21667a(float f, int i) {
        float[] fArr = new float[8];
        float[] fArr2 = new float[8];
        for (int i2 = 0; i2 < 8; i2++) {
            fArr[i2] = 0.0f + f;
            fArr2[i2] = f;
        }
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(0.0f, 0.0f, 0.0f, 0.0f), fArr2));
        shapeDrawable.getPaint().setColor(i);
        return shapeDrawable;
    }
}
