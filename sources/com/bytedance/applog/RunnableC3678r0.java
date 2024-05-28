package com.bytedance.applog;

import android.webkit.WebView;
import com.bytedance.applog.tracker.WebViewJsUtil;

/* renamed from: com.bytedance.applog.r0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC3678r0 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ WebView f8785a;

    /* renamed from: b */
    public final /* synthetic */ C3685s0 f8786b;

    public RunnableC3678r0(C3685s0 c3685s0, WebView webView) {
        this.f8786b = c3685s0;
        this.f8785a = webView;
    }

    @Override // java.lang.Runnable
    public void run() {
        WebViewJsUtil.getWebInfo(this.f8785a, this.f8786b.f8805i);
    }
}
