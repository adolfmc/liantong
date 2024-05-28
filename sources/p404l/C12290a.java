package p404l;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: l.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12290a extends StateListDrawable {
    public C12290a(int i, int i2, int i3, int i4, int i5) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new C12292c(i, i2, i3, i4).m1864a());
        shapeDrawable.getPaint().setColor(-1381654);
        ShapeDrawable shapeDrawable2 = new ShapeDrawable(new C12292c(i, i2, i3, i4).m1864a());
        shapeDrawable2.getPaint().setColor(i5);
        addState(new int[]{16842919}, shapeDrawable);
        addState(new int[]{-16842919}, shapeDrawable2);
    }
}
