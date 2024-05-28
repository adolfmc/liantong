package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.ak */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2759ak extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ View f6537a;

    /* renamed from: b */
    final /* synthetic */ WearMapView f6538b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2759ak(WearMapView wearMapView, View view) {
        this.f6538b = wearMapView;
        this.f6537a = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.f6537a.setVisibility(4);
        super.onAnimationEnd(animator);
    }
}
