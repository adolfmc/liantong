package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.Marker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2877j extends AbstractC2870c {

    /* renamed from: a */
    private Animator f7101a = null;

    /* renamed from: b */
    private long f7102b = 0;

    /* renamed from: c */
    private Interpolator f7103c = null;

    /* renamed from: d */
    private Animation.AnimationListener f7104d = null;

    /* renamed from: e */
    private int f7105e = 1;

    /* renamed from: f */
    private int f7106f = 0;

    /* renamed from: g */
    private float[] f7107g;

    /* renamed from: h */
    private int f7108h;

    public C2877j(int i, float... fArr) {
        this.f7108h = 1;
        this.f7107g = fArr;
        this.f7108h = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0017  */
    @android.annotation.TargetApi(11)
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.animation.ObjectAnimator m18517a(com.baidu.mapapi.map.Marker r3) {
        /*
            r2 = this;
            int r0 = r2.f7108h
            r1 = 1
            if (r0 != r1) goto Le
            java.lang.String r0 = "scaleX"
        L7:
            float[] r1 = r2.f7107g
            android.animation.ObjectAnimator r3 = android.animation.ObjectAnimator.ofFloat(r3, r0, r1)
            goto L15
        Le:
            r1 = 2
            if (r0 != r1) goto L14
            java.lang.String r0 = "scaleY"
            goto L7
        L14:
            r3 = 0
        L15:
            if (r3 == 0) goto L2f
            int r0 = r2.f7106f
            r3.setRepeatCount(r0)
            int r0 = r2.m18515c()
            r3.setRepeatMode(r0)
            long r0 = r2.f7102b
            r3.setDuration(r0)
            android.view.animation.Interpolator r0 = r2.f7103c
            if (r0 == 0) goto L2f
            r3.setInterpolator(r0)
        L2f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.p141a.C2877j.m18517a(com.baidu.mapapi.map.Marker):android.animation.ObjectAnimator");
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18514a() {
        Animator animator = this.f7101a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18513a(int i) {
        this.f7105e = i;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18512a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f7102b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    protected void mo18511a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new C2878k(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18510a(TypeEvaluator typeEvaluator) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18509a(Interpolator interpolator) {
        this.f7103c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18508a(Animation.AnimationListener animationListener) {
        this.f7104d = animationListener;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18506a(Marker marker, Animation animation) {
        this.f7101a = m18517a(marker);
        mo18511a(this.f7101a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: b */
    public void mo18504b() {
        Animator animator = this.f7101a;
        if (animator != null) {
            animator.cancel();
            this.f7101a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: b */
    public void mo18503b(int i) {
        if (i > 0 || i == -1) {
            this.f7106f = i;
        }
    }

    /* renamed from: c */
    public int m18515c() {
        return this.f7105e;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: c */
    public void mo18501c(int i) {
    }
}
