package com.baidu.mapsdkplatform.comapi.p141a;

import android.animation.Animator;
import com.baidu.mapapi.animation.Animation;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.a.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2874g implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ C2873f f7092a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2874g(C2873f c2873f) {
        this.f7092a = c2873f;
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7092a.f7088d;
        if (animationListener != null) {
            animationListener2 = this.f7092a.f7088d;
            animationListener2.onAnimationCancel();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7092a.f7088d;
        if (animationListener != null) {
            animationListener2 = this.f7092a.f7088d;
            animationListener2.onAnimationEnd();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7092a.f7088d;
        if (animationListener != null) {
            animationListener2 = this.f7092a.f7088d;
            animationListener2.onAnimationRepeat();
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        Animation.AnimationListener animationListener;
        Animation.AnimationListener animationListener2;
        animationListener = this.f7092a.f7088d;
        if (animationListener != null) {
            animationListener2 = this.f7092a.f7088d;
            animationListener2.onAnimationStart();
        }
    }
}
