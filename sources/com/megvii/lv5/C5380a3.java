package com.megvii.lv5;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.ContextThemeWrapper;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.a3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5380a3 extends Animation {

    /* renamed from: a */
    public final float f12366a;

    /* renamed from: b */
    public final float f12367b;

    /* renamed from: c */
    public final float f12368c;

    /* renamed from: d */
    public final float f12369d;

    /* renamed from: e */
    public final float f12370e;

    /* renamed from: f */
    public final boolean f12371f;

    /* renamed from: g */
    public Camera f12372g;

    /* renamed from: h */
    public float f12373h;

    /* renamed from: i */
    public final int f12374i;

    public C5380a3(ContextThemeWrapper contextThemeWrapper, float f, float f2, float f3, float f4, float f5, boolean z, int i) {
        this.f12373h = 1.0f;
        this.f12366a = f;
        this.f12367b = f2;
        this.f12368c = f3;
        this.f12369d = f4;
        this.f12370e = f5;
        this.f12371f = z;
        this.f12374i = i;
        this.f12373h = contextThemeWrapper.getResources().getDisplayMetrics().density;
    }

    @Override // android.view.animation.Animation
    public void applyTransformation(float f, Transformation transformation) {
        float f2 = this.f12366a;
        float f3 = f2 + ((this.f12367b - f2) * f);
        float f4 = this.f12368c;
        float f5 = this.f12369d;
        Camera camera = this.f12372g;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        camera.translate(0.0f, 0.0f, this.f12371f ? this.f12370e * f : this.f12370e * (1.0f - f));
        if (this.f12374i == 0) {
            camera.rotateX(f3);
        } else {
            camera.rotateY(f3);
        }
        camera.getMatrix(matrix);
        camera.restore();
        float[] fArr = {0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
        matrix.getValues(fArr);
        float f6 = fArr[6];
        float f7 = this.f12373h;
        fArr[6] = f6 / f7;
        fArr[7] = fArr[7] / f7;
        matrix.setValues(fArr);
        matrix.preTranslate(-f4, -f5);
        matrix.postTranslate(f4, f5);
    }

    @Override // android.view.animation.Animation
    public void initialize(int i, int i2, int i3, int i4) {
        super.initialize(i, i2, i3, i4);
        this.f12372g = new Camera();
    }
}
