package com.baidu.platform.comapi.map.p153b;

import android.os.Build;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.baidu.platform.comapi.C2981b;
import com.baidu.platform.comapi.map.p153b.C3020a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3038f {

    /* renamed from: a */
    public final int f7845a;

    /* renamed from: b */
    public final int f7846b;

    /* renamed from: c */
    private VelocityTracker f7847c;

    public C3038f() {
        int scaledMaximumFlingVelocity;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(C2981b.m18068d());
        if (viewConfiguration == null) {
            this.f7846b = ViewConfiguration.getMinimumFlingVelocity();
            scaledMaximumFlingVelocity = ViewConfiguration.getMaximumFlingVelocity();
        } else {
            this.f7846b = viewConfiguration.getScaledMinimumFlingVelocity();
            scaledMaximumFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        }
        this.f7845a = scaledMaximumFlingVelocity;
    }

    /* renamed from: a */
    public void m17871a() {
        this.f7847c = VelocityTracker.obtain();
    }

    /* renamed from: a */
    public void m17870a(MotionEvent motionEvent) {
        VelocityTracker velocityTracker = this.f7847c;
        if (velocityTracker == null) {
            this.f7847c = VelocityTracker.obtain();
        } else {
            velocityTracker.addMovement(motionEvent);
        }
    }

    /* renamed from: b */
    public void m17869b() {
        VelocityTracker velocityTracker = this.f7847c;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f7847c = null;
        }
    }

    /* renamed from: c */
    public Pair<C3020a.C3024d, C3020a.C3024d> m17868c() {
        float xVelocity;
        float yVelocity;
        float xVelocity2;
        float yVelocity2;
        VelocityTracker velocityTracker = this.f7847c;
        if (velocityTracker == null) {
            return new Pair<>(new C3020a.C3024d(0.0d, 0.0d), new C3020a.C3024d(0.0d, 0.0d));
        }
        velocityTracker.computeCurrentVelocity(1000, this.f7845a);
        if (Build.VERSION.SDK_INT < 8) {
            xVelocity = this.f7847c.getXVelocity();
            yVelocity = this.f7847c.getYVelocity();
            xVelocity2 = this.f7847c.getXVelocity();
            yVelocity2 = this.f7847c.getYVelocity();
        } else {
            xVelocity = this.f7847c.getXVelocity(0);
            yVelocity = this.f7847c.getYVelocity(0);
            xVelocity2 = this.f7847c.getXVelocity(1);
            yVelocity2 = this.f7847c.getYVelocity(1);
        }
        return new Pair<>(new C3020a.C3024d(xVelocity, yVelocity), new C3020a.C3024d(xVelocity2, yVelocity2));
    }
}
