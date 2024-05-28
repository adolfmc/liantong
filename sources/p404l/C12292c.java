package p404l;

import android.graphics.drawable.shapes.RoundRectShape;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: l.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C12292c {

    /* renamed from: a */
    public RoundRectShape f24913a;

    public C12292c(int i, int i2, int i3, int i4) {
        float[] fArr = new float[8];
        if (i > 0) {
            float f = i;
            fArr[0] = f;
            fArr[1] = f;
        }
        if (i2 > 0) {
            float f2 = i2;
            fArr[2] = f2;
            fArr[3] = f2;
        }
        if (i3 > 0) {
            float f3 = i3;
            fArr[4] = f3;
            fArr[5] = f3;
        }
        if (i4 > 0) {
            float f4 = i4;
            fArr[6] = f4;
            fArr[7] = f4;
        }
        this.f24913a = new RoundRectShape(fArr, null, null);
    }

    /* renamed from: a */
    public final RoundRectShape m1864a() {
        return this.f24913a;
    }
}
