package com.alipay.sdk.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Handler;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.util.C2052n;
import com.alipay.sdk.widget.C2058a;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.lang.ref.WeakReference;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.app.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1989b extends NBSWebViewClient {

    /* renamed from: a */
    private Activity f3566a;

    /* renamed from: b */
    private boolean f3567b;

    /* renamed from: c */
    private Handler f3568c;

    /* renamed from: d */
    private C2058a f3569d;

    /* renamed from: e */
    private boolean f3570e;

    public C1989b(Activity activity) {
        this.f3566a = activity;
        this.f3568c = new Handler(this.f3566a.getMainLooper());
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.f3570e = true;
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        Activity activity = this.f3566a;
        if (activity == null) {
            return;
        }
        C2000a.m20899a("net", "SSLError", "1" + sslError);
        if (this.f3567b) {
            sslErrorHandler.proceed();
            this.f3567b = false;
            return;
        }
        activity.runOnUiThread(new RunnableC1991c(this, activity, sslErrorHandler));
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return C2052n.m20666a(webView, str, this.f3566a);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Tracker.onPageStarted(this, webView, str, bitmap);
        Activity activity = this.f3566a;
        if (this.f3568c != null && activity != null && !activity.isFinishing()) {
            m20920c();
            this.f3568c.postDelayed(new RunnableC1990a(this), 30000L);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Tracker.onPageFinished(this, webView, str);
        Activity activity = this.f3566a;
        if (this.f3568c != null && activity != null && !activity.isFinishing()) {
            m20919d();
            this.f3568c.removeCallbacksAndMessages(null);
        }
        super.onPageFinished(webView, str);
    }

    /* renamed from: c */
    private void m20920c() {
        Activity activity = this.f3566a;
        if (activity == null) {
            return;
        }
        if (this.f3569d == null) {
            this.f3569d = new C2058a(activity, "正在加载");
            this.f3569d.m20620a(true);
        }
        this.f3569d.m20619b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m20919d() {
        C2058a c2058a = this.f3569d;
        if (c2058a != null) {
            c2058a.m20617c();
        }
        this.f3569d = null;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.app.b$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class RunnableC1990a implements Runnable {

        /* renamed from: a */
        private final WeakReference<C1989b> f3571a;

        RunnableC1990a(C1989b c1989b) {
            this.f3571a = new WeakReference<>(c1989b);
        }

        @Override // java.lang.Runnable
        public void run() {
            C1989b c1989b = this.f3571a.get();
            if (c1989b != null) {
                c1989b.m20919d();
            }
        }
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient
    /* renamed from: a */
    public void mo9870a() {
        this.f3568c = null;
        this.f3566a = null;
    }

    /* renamed from: b */
    public boolean m20921b() {
        return this.f3570e;
    }
}
