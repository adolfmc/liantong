package com.alipay.sdk.widget;

import android.app.Activity;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.alipay.sdk.app.C1989b;
import com.alipay.sdk.app.C1998j;
import com.alipay.sdk.app.EnumC1999k;
import com.alipay.sdk.util.C2052n;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.sdk.widget.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2066h extends AbstractC2065g {

    /* renamed from: b */
    private C1989b f3941b;

    /* renamed from: c */
    private WebView f3942c;

    public C2066h(Activity activity) {
        super(activity);
        this.f3942c = new WebView(activity);
        m20606a(this.f3942c, activity);
        addView(this.f3942c);
        this.f3941b = new C1989b(activity);
        WebView webView = this.f3942c;
        C1989b c1989b = this.f3941b;
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, c1989b);
        } else {
            webView.setWebViewClient(c1989b);
        }
    }

    @Override // com.alipay.sdk.widget.AbstractC2065g
    /* renamed from: b */
    public boolean mo20593b() {
        if (this.f3942c.canGoBack()) {
            if (this.f3941b.m20921b()) {
                EnumC1999k m20903b = EnumC1999k.m20903b(EnumC1999k.NETWORK_ERROR.m20907a());
                C1998j.m20913a(C1998j.m20914a(m20903b.m20907a(), m20903b.m20904b(), ""));
                this.f3940a.finish();
                return true;
            }
            return true;
        }
        C1998j.m20913a(C1998j.m20910c());
        this.f3940a.finish();
        return true;
    }

    @Override // com.alipay.sdk.widget.AbstractC2065g
    /* renamed from: a */
    public void mo20605a() {
        this.f3941b.mo9870a();
        removeAllViews();
    }

    /* renamed from: a */
    private void m20606a(WebView webView, Context context) {
        WebSettings settings = this.f3942c.getSettings();
        settings.setUserAgentString(settings.getUserAgentString() + C2052n.m20654c(context));
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(false);
        settings.setTextSize(WebSettings.TextSize.NORMAL);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
        this.f3942c.resumeTimers();
        this.f3942c.setVerticalScrollbarOverlay(true);
        this.f3942c.setDownloadListener(new C2067i(this));
        try {
            try {
                this.f3942c.removeJavascriptInterface("searchBoxJavaBridge_");
                this.f3942c.removeJavascriptInterface("accessibility");
                this.f3942c.removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable unused) {
                Method method = this.f3942c.getClass().getMethod("removeJavascriptInterface", new Class[0]);
                if (method != null) {
                    method.invoke(this.f3942c, "searchBoxJavaBridge_");
                    method.invoke(this.f3942c, "accessibility");
                    method.invoke(this.f3942c, "accessibilityTraversal");
                }
            }
        } catch (Throwable unused2) {
        }
    }

    @Override // com.alipay.sdk.widget.AbstractC2065g
    /* renamed from: a */
    public void mo20597a(String str) {
        WebView webView = this.f3942c;
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
    }
}
