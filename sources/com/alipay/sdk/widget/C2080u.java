package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.u */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2080u {

    /* renamed from: a */
    private Stack<WebViewWindow> f3984a = new Stack<>();

    /* renamed from: a */
    public WebViewWindow m20582a() {
        return this.f3984a.pop();
    }

    /* renamed from: a */
    public void m20581a(WebViewWindow webViewWindow) {
        this.f3984a.push(webViewWindow);
    }

    /* renamed from: b */
    public boolean m20580b() {
        return this.f3984a.isEmpty();
    }

    /* renamed from: c */
    public void m20579c() {
        if (m20580b()) {
            return;
        }
        Iterator<WebViewWindow> it = this.f3984a.iterator();
        while (it.hasNext()) {
            it.next().m20639a();
        }
        this.f3984a.clear();
    }
}
