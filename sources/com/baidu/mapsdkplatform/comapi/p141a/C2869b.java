package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import com.baidu.mapapi.animation.Animation;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2869b implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ C2868a f7077a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2869b(C2868a c2868a) {
        this.f7077a = c2868a;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7077a.f7073d;
        if (animationListener != null) {
            animationListener2 = this.f7077a.f7073d;
            animationListener2.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7077a.f7073d;
        if (animationListener != null) {
            animationListener2 = this.f7077a.f7073d;
            animationListener2.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7077a.f7073d;
        if (animationListener != null) {
            animationListener2 = this.f7077a.f7073d;
            animationListener2.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7077a.f7073d;
        if (animationListener != null) {
            animationListener2 = this.f7077a.f7073d;
            animationListener2.onAnimationStart();
        }
    }
}
