package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.w */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2783w extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ SwipeDismissTouchListener f6594a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2783w(SwipeDismissTouchListener swipeDismissTouchListener) {
        this.f6594a = swipeDismissTouchListener;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f6594a.m18874a();
    }
}
