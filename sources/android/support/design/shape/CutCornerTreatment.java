package android.support.design.shape;

import android.support.design.internal.Experimental;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Experimental("The shapes API is currently experimental and subject to change")
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CutCornerTreatment extends CornerTreatment {
    private final float size;

    public CutCornerTreatment(float f) {
        this.size = f;
    }

    @Override // android.support.design.shape.CornerTreatment
    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        shapePath.reset(0.0f, this.size * f2);
        double d = f;
        double d2 = f2;
        shapePath.lineTo((float) (Math.sin(d) * this.size * d2), (float) (Math.cos(d) * this.size * d2));
    }
}
