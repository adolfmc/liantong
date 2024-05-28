package com.baidu.mapapi.map;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.y */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2785y implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    final /* synthetic */ ViewGroup.LayoutParams f6598a;

    /* renamed from: b */
    final /* synthetic */ SwipeDismissTouchListener f6599b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2785y(SwipeDismissTouchListener swipeDismissTouchListener, ViewGroup.LayoutParams layoutParams) {
        this.f6599b = swipeDismissTouchListener;
        this.f6598a = layoutParams;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        View view;
        this.f6598a.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        view = this.f6599b.f6393e;
        view.setLayoutParams(this.f6598a);
    }
}
