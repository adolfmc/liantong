package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.C2068j;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2071l extends C2068j.AbstractanimationAnimation$AnimationListenerC2069a {

    /* renamed from: a */
    final /* synthetic */ WebViewWindow f3970a;

    /* renamed from: b */
    final /* synthetic */ C2068j f3971b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2071l(C2068j c2068j, WebViewWindow webViewWindow) {
        super(c2068j, null);
        this.f3971b = c2068j;
        this.f3970a = webViewWindow;
    }

    @Override // com.alipay.sdk.widget.C2068j.AbstractanimationAnimation$AnimationListenerC2069a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        this.f3970a.m20639a();
        this.f3971b.f3964v = false;
    }
}
