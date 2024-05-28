package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.annotation.TargetApi;
import android.view.animation.Interpolator;
import com.baidu.mapapi.animation.Animation;
import com.baidu.mapapi.map.Marker;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2868a extends AbstractC2870c {

    /* renamed from: a */
    private Animator f7070a = null;

    /* renamed from: b */
    private long f7071b = 0;

    /* renamed from: c */
    private Interpolator f7072c = null;

    /* renamed from: d */
    private Animation.AnimationListener f7073d = null;

    /* renamed from: e */
    private int f7074e = 1;

    /* renamed from: f */
    private int f7075f = 0;

    /* renamed from: g */
    private float[] f7076g;

    public C2868a(float... fArr) {
        this.f7076g = fArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(11)
    /* renamed from: a */
    public ObjectAnimator m18529a(Marker marker) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(marker, "alpha", this.f7076g);
        if (ofFloat != null) {
            ofFloat.setRepeatCount(this.f7075f);
            ofFloat.setRepeatMode(m18527c());
            ofFloat.setDuration(this.f7071b);
            Interpolator interpolator = this.f7072c;
            if (interpolator != null) {
                ofFloat.setInterpolator(interpolator);
            }
        }
        return ofFloat;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18514a() {
        Animator animator = this.f7070a;
        if (animator == null) {
            return;
        }
        animator.start();
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18513a(int i) {
        this.f7074e = i;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18512a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.f7071b = j;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    protected void mo18511a(Animator animator) {
        if (animator == null) {
            return;
        }
        animator.addListener(new C2869b(this));
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18510a(TypeEvaluator typeEvaluator) {
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18509a(Interpolator interpolator) {
        this.f7072c = interpolator;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: a */
    public void mo18508a(Animation.AnimationListener animationListener) {
        this.f7073d = animationListener;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: a */
    public void mo18506a(Marker marker, Animation animation) {
        this.f7070a = m18529a(marker);
        mo18511a(this.f7070a);
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    @TargetApi(11)
    /* renamed from: b */
    public void mo18504b() {
        Animator animator = this.f7070a;
        if (animator != null) {
            animator.cancel();
            this.f7070a = null;
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: b */
    public void mo18503b(int i) {
        if (i > 0 || i == -1) {
            this.f7075f = i;
        }
    }

    /* renamed from: c */
    public int m18527c() {
        return this.f7074e;
    }

    @Override // com.baidu.mapsdkplatform.comapi.p141a.AbstractC2870c
    /* renamed from: c */
    public void mo18501c(int i) {
    }
}
