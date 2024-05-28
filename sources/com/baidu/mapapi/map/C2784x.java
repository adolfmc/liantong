package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.mapapi.map.SwipeDismissTouchListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.x */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2784x extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ ViewGroup.LayoutParams f6595a;

    /* renamed from: b */
    final /* synthetic */ int f6596b;

    /* renamed from: c */
    final /* synthetic */ SwipeDismissTouchListener f6597c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2784x(SwipeDismissTouchListener swipeDismissTouchListener, ViewGroup.LayoutParams layoutParams, int i) {
        this.f6597c = swipeDismissTouchListener;
        this.f6595a = layoutParams;
        this.f6596b = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        SwipeDismissTouchListener.DismissCallbacks dismissCallbacks;
        View view;
        Object obj;
        View view2;
        View view3;
        dismissCallbacks = this.f6597c.f6394f;
        view = this.f6597c.f6393e;
        obj = this.f6597c.f6400l;
        dismissCallbacks.onDismiss(view, obj);
        view2 = this.f6597c.f6393e;
        view2.setTranslationX(0.0f);
        this.f6595a.height = this.f6596b;
        view3 = this.f6597c.f6393e;
        view3.setLayoutParams(this.f6595a);
    }
}
