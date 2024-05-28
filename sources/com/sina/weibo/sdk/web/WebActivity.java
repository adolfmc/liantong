package com.sina.weibo.sdk.web;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sina.weibo.sdk.web.p312a.AbstractC7121b;
import com.sina.weibo.sdk.web.p313b.AbstractC7125b;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WebActivity extends Activity implements InterfaceC7119a {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: am */
    private LinearLayout f18341am;

    /* renamed from: an */
    private TextView f18342an;

    /* renamed from: ao */
    private TextView f18343ao;

    /* renamed from: ap */
    private WebView f18344ap;

    /* renamed from: aq */
    private ProgressBar f18345aq;

    /* renamed from: ar */
    private AbstractC7125b f18346ar;

    /* renamed from: as */
    private AbstractC7121b f18347as;

    /* renamed from: at */
    private String f18348at;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 31);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sina.weibo.sdk.web.WebActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    final class C71141 implements AbstractC7125b.InterfaceC7126a {
        C71141() {
        }

        @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b.InterfaceC7126a
        public final void onComplete() {
            String url = WebActivity.this.f18346ar.getUrl();
            if (TextUtils.isEmpty(url) || !WebActivity.m8022j(url)) {
                return;
            }
            WebView webView = WebActivity.this.f18344ap;
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, url);
            } else {
                webView.loadUrl(url);
            }
        }

        @Override // com.sina.weibo.sdk.web.p313b.AbstractC7125b.InterfaceC7126a
        public final void onError(String str) {
            WebActivity.this.f18347as.mo8011p(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public static boolean m8022j(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith("https://service.weibo.com/share/mobilesdk.php") || str.startsWith("https://open.weibo.cn/oauth2/authorize?");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sina.weibo.sdk.web.WebActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    final class View$OnClickListenerC71152 implements View.OnClickListener {
        View$OnClickListenerC71152() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            WebActivity.this.f18347as.mo8010q();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sina.weibo.sdk.web.WebActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    final class View$OnClickListenerC71163 implements View.OnClickListener {
        View$OnClickListenerC71163() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            WebActivity.m8024d(WebActivity.this);
            WebActivity.this.f18344ap.reload();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.web.WebActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    final class C71174 extends WebChromeClient {
        C71174() {
        }

        @Override // android.webkit.WebChromeClient
        public final void onProgressChanged(WebView webView, int i) {
            super.onProgressChanged(webView, i);
            WebActivity.this.f18345aq.setProgress(i);
            if (i == 100) {
                WebActivity.this.f18345aq.setVisibility(4);
            } else {
                WebActivity.this.f18345aq.setVisibility(0);
            }
        }

        @Override // android.webkit.WebChromeClient
        public final void onReceivedTitle(WebView webView, String str) {
            super.onReceivedTitle(webView, str);
        }
    }

    /* renamed from: a */
    private static void m8028a(WebView webView, String str) {
        try {
            WebView.class.getDeclaredMethod("removeJavascriptInterface", String.class).invoke(webView, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sina.weibo.sdk.web.InterfaceC7119a
    /* renamed from: q */
    public final void mo8017q() {
        finish();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.f18347as.mo8009s()) {
                return true;
            }
            if (this.f18344ap.canGoBack()) {
                this.f18344ap.goBack();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.sina.weibo.sdk.web.InterfaceC7119a
    /* renamed from: p */
    public final void mo8018p() {
        this.f18341am.setVisibility(0);
        this.f18344ap.setVisibility(8);
    }

    /* renamed from: d */
    static /* synthetic */ void m8024d(WebActivity webActivity) {
        webActivity.f18341am.setVisibility(8);
        webActivity.f18344ap.setVisibility(0);
    }
}
