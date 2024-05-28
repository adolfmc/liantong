package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import com.baidu.mapapi.animation.Animation;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2876i implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ C2875h f7100a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2876i(C2875h c2875h) {
        this.f7100a = c2875h;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7100a.f7096d;
        if (animationListener != null) {
            animationListener2 = this.f7100a.f7096d;
            animationListener2.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7100a.f7096d;
        if (animationListener != null) {
            animationListener2 = this.f7100a.f7096d;
            animationListener2.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7100a.f7096d;
        if (animationListener != null) {
            animationListener2 = this.f7100a.f7096d;
            animationListener2.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7100a.f7096d;
        if (animationListener != null) {
            animationListener2 = this.f7100a.f7096d;
            animationListener2.onAnimationStart();
        }
    }
}
