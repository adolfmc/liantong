package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import com.baidu.mapapi.animation.Animation;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2882m implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ C2879l f7120a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2882m(C2879l c2879l) {
        this.f7120a = c2879l;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7120a.f7114e;
        if (animationListener != null) {
            animationListener2 = this.f7120a.f7114e;
            animationListener2.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7120a.f7114e;
        if (animationListener != null) {
            animationListener2 = this.f7120a.f7114e;
            animationListener2.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7120a.f7114e;
        if (animationListener != null) {
            animationListener2 = this.f7120a.f7114e;
            animationListener2.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7120a.f7114e;
        if (animationListener != null) {
            animationListener2 = this.f7120a.f7114e;
            animationListener2.onAnimationStart();
        }
    }
}
