package com.megvii.lv5;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import com.megvii.lv5.sdk.view.LoadingCoverView;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.i3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5477i3 extends AnimatorListenerAdapter {

    /* renamed from: a */
    public final /* synthetic */ LoadingCoverView f12807a;

    public C5477i3(LoadingCoverView loadingCoverView) {
        this.f12807a = loadingCoverView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        this.f12807a.f13680p = LoadingCoverView.EnumC5610a.END;
    }
}
