package org.limlee.hipraiseanimationlib;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class BezierEvaluator implements TypeEvaluator<PointF> {
    PointF pointF1;
    PointF pointF2;

    public BezierEvaluator(PointF pointF, PointF pointF2) {
        this.pointF1 = pointF;
        this.pointF2 = pointF2;
    }

    @Override // android.animation.TypeEvaluator
    public PointF evaluate(float f, PointF pointF, PointF pointF2) {
        PointF pointF3 = new PointF();
        float f2 = 1.0f - f;
        pointF3.x = (pointF.x * f2 * f2 * f2) + (this.pointF1.x * 3.0f * f * f2 * f2) + (this.pointF2.x * 3.0f * f * f * f2) + (pointF2.x * f * f * f);
        pointF3.y = (pointF.y * f2 * f2 * f2) + (this.pointF1.y * 3.0f * f * f2 * f2) + (this.pointF2.y * 3.0f * f * f * f2) + (pointF2.y * f * f * f);
        return pointF3;
    }
}
