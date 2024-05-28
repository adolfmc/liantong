package com.alipay.sdk.widget;

import android.view.animation.Animation;
import com.alipay.sdk.widget.C2068j;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2072m extends C2068j.AbstractanimationAnimation$AnimationListenerC2069a {

    /* renamed from: a */
    final /* synthetic */ WebViewWindow f3972a;

    /* renamed from: b */
    final /* synthetic */ String f3973b;

    /* renamed from: c */
    final /* synthetic */ C2068j f3974c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2072m(C2068j c2068j, WebViewWindow webViewWindow, String str) {
        super(c2068j, null);
        this.f3974c = c2068j;
        this.f3972a = webViewWindow;
        this.f3973b = str;
    }

    @Override // com.alipay.sdk.widget.C2068j.AbstractanimationAnimation$AnimationListenerC2069a, android.view.animation.Animation.AnimationListener
    public void onAnimationEnd(Animation animation) {
        WebViewWindow webViewWindow;
        this.f3974c.removeView(this.f3972a);
        webViewWindow = this.f3974c.f3966x;
        webViewWindow.m20634a(this.f3973b);
        this.f3974c.f3964v = false;
    }
}
