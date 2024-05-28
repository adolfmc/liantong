package cn.sharesdk.tencent.qzone.utils;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.SSDKWebViewClient;
import cn.sharesdk.framework.authorize.RegisterView;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.WebViewUtils;
import cn.sharesdk.tencent.qzone.ReceiveActivity;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.PrefetchCumpLauncher;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ShareActivity extends FakeActivity {
    private QZoneWebShareAdapter adapter;

    /* renamed from: pa */
    private PlatformActionListener f3203pa;
    private boolean resultFailed;
    private boolean resultOk;

    /* renamed from: rv */
    private RegisterView f3204rv;
    private String scheme;
    private String uriScheme;
    private boolean useClient;
    private WebView webView;

    public void setScheme(String str, boolean z) {
        this.scheme = str;
        this.useClient = z;
    }

    public void setSharedCallback(PlatformActionListener platformActionListener) {
        this.f3203pa = platformActionListener;
    }

    public void setAppId(String str) {
        this.uriScheme = "tencent" + str;
    }

    @Override // com.mob.tools.FakeActivity
    public void setActivity(Activity activity) {
        super.setActivity(activity);
        if (this.adapter == null) {
            this.adapter = getAdapter();
            if (this.adapter == null) {
                this.adapter = new QZoneWebShareAdapter();
            }
        }
        this.adapter.m21352a(activity);
    }

    private QZoneWebShareAdapter getAdapter() {
        try {
            String string = this.activity.getPackageManager().getActivityInfo(this.activity.getComponentName(), 128).metaData.getString("QZoneWebShareAdapter");
            if (string != null && string.length() > 0) {
                Object newInstance = Class.forName(string).newInstance();
                if (newInstance instanceof QZoneWebShareAdapter) {
                    return (QZoneWebShareAdapter) newInstance;
                }
                return null;
            }
            return null;
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            return null;
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        try {
            try {
                Class<?> cls = Class.forName("cn.sharesdk.tencent.qq.ReceiveActivity");
                cls.getMethod("setUriScheme", String.class).invoke(null, this.uriScheme);
                cls.getMethod("setPlatformActionListener", PlatformActionListener.class).invoke(null, this.f3203pa);
                if (this.useClient) {
                    clientShare();
                } else {
                    webShare();
                }
            } catch (Throwable th) {
                this.activity.finish();
                PlatformActionListener platformActionListener = this.f3203pa;
                if (platformActionListener != null) {
                    platformActionListener.onError(null, 9, th);
                }
            }
        } catch (Throwable unused) {
            ReceiveActivity.m21384a(this.uriScheme);
            ReceiveActivity.m21385a(this.f3203pa);
            if (this.useClient) {
                clientShare();
            } else {
                webShare();
            }
        }
    }

    private void clientShare() {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(this.scheme));
            intent.putExtra("pkg_name", this.activity.getPackageName());
            if (Build.VERSION.SDK_INT >= 11) {
                intent.setFlags(335544320);
            }
            this.activity.startActivityForResult(intent, 100);
        } catch (Throwable th) {
            PlatformActionListener platformActionListener = this.f3203pa;
            if (platformActionListener != null) {
                platformActionListener.onError(null, 0, th);
            }
        }
    }

    private void webShare() {
        this.f3204rv = getBodyView();
        try {
            int stringRes = ResHelper.getStringRes(getContext(), "ssdk_share_to_qzone");
            if (stringRes > 0) {
                this.f3204rv.m21879c().getTvTitle().setText(stringRes);
            }
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            this.f3204rv.m21879c().setVisibility(8);
        }
        this.adapter.m21350a(this.f3204rv.m21878d());
        this.adapter.m21351a(this.f3204rv.m21881b());
        this.adapter.m21349a(this.f3204rv.m21879c());
        this.adapter.m21353a();
        this.activity.setContentView(this.f3204rv);
        if ("none".equals(DeviceHelper.getInstance(this.activity).getDetailNetworkTypeForStatic())) {
            this.resultFailed = true;
            finish();
            this.f3203pa.onError(null, 0, new Throwable("failed to load webpage, network disconnected."));
            return;
        }
        WebView m21881b = this.f3204rv.m21881b();
        String str = this.scheme;
        if (m21881b instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) m21881b, str);
        } else {
            m21881b.loadUrl(str);
        }
    }

    protected RegisterView getBodyView() {
        RegisterView registerView = new RegisterView(this.activity);
        registerView.m21879c().getChildAt(registerView.m21879c().getChildCount() - 1).setVisibility(8);
        registerView.m21885a().setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.tencent.qzone.utils.ShareActivity.1
            /* JADX WARN: Type inference failed for: r1v1, types: [cn.sharesdk.tencent.qzone.utils.ShareActivity$1$1] */
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                new Thread() { // from class: cn.sharesdk.tencent.qzone.utils.ShareActivity.1.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        try {
                            new Instrumentation().sendKeyDownUpSync(4);
                        } catch (Throwable th) {
                            SSDKLog.m21740b().m21742a(th);
                            ShareActivity.this.finish();
                            ShareActivity.this.f3203pa.onCancel(null, 0);
                        }
                    }
                }.start();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.webView = registerView.m21881b();
        WebSettings settings = this.webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(1);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        WebViewUtils.m21666a(this.webView, false);
        settings.setDatabasePath(this.activity.getDir("database", 0).getPath());
        settings.setSavePassword(false);
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        WebView webView = this.webView;
        SSDKWebViewClient sSDKWebViewClient = new SSDKWebViewClient() { // from class: cn.sharesdk.tencent.qzone.utils.ShareActivity.2
            @Override // cn.sharesdk.framework.SSDKWebViewClient, android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                if (str != null && str.startsWith(ShareActivity.this.uriScheme)) {
                    ShareActivity.this.afterShare(str);
                } else if (str != null && str.startsWith("mqzone://")) {
                    ShareActivity.this.launchQZone(str);
                }
                return super.shouldOverrideUrlLoading(webView2, str);
            }
        };
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, sSDKWebViewClient);
        } else {
            webView.setWebViewClient(sSDKWebViewClient);
        }
        return registerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void afterShare(String str) {
        String str2 = str == null ? "" : new String(str);
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        if (urlToBundle == null) {
            this.resultFailed = true;
            finish();
            PlatformActionListener platformActionListener = this.f3203pa;
            platformActionListener.onError(null, 0, new Throwable("failed to parse callback uri: " + str2));
            return;
        }
        String string = urlToBundle.getString("action");
        if (!"share".equals(string) && !"shareToQzone".equals(string)) {
            this.resultFailed = true;
            finish();
            PlatformActionListener platformActionListener2 = this.f3203pa;
            platformActionListener2.onError(null, 0, new Throwable("action error: " + str2));
            return;
        }
        String string2 = urlToBundle.getString("result");
        if ("cancel".equals(string2)) {
            finish();
            this.f3203pa.onCancel(null, 0);
        } else if (!PrefetchCumpLauncher.PrefetchStatus_Complete.equals(string2)) {
            this.resultFailed = true;
            finish();
            PlatformActionListener platformActionListener3 = this.f3203pa;
            platformActionListener3.onError(null, 0, new Throwable("operation failed: " + str2));
        } else {
            String string3 = urlToBundle.getString("response");
            if (TextUtils.isEmpty(string3)) {
                this.resultFailed = true;
                finish();
                PlatformActionListener platformActionListener4 = this.f3203pa;
                platformActionListener4.onError(null, 0, new Throwable("response empty" + str2));
                return;
            }
            this.resultOk = true;
            finish();
            this.f3203pa.onComplete(null, 0, new Hashon().fromJson(string3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchQZone(String str) {
        ResolveInfo resolveInfo;
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        try {
            resolveInfo = this.activity.getPackageManager().resolveActivity(intent, 0);
        } catch (Throwable th) {
            SSDKLog.m21740b().m21742a(th);
            resolveInfo = null;
        }
        if (resolveInfo != null) {
            try {
                startActivity(intent);
            } catch (Throwable th2) {
                SSDKLog.m21740b().m21742a(th2);
            }
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onStart() {
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            qZoneWebShareAdapter.m21347c();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onPause() {
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            qZoneWebShareAdapter.m21346d();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onResume() {
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            qZoneWebShareAdapter.m21345e();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onStop() {
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            qZoneWebShareAdapter.m21344f();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onRestart() {
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            qZoneWebShareAdapter.m21343g();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        if (!this.useClient && !this.resultFailed && !this.resultOk) {
            this.f3203pa.onCancel(null, 0);
        }
        WebView webView = this.webView;
        if (webView != null) {
            webView.setFocusable(false);
        }
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            qZoneWebShareAdapter.m21348b();
        }
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onFinish() {
        QZoneWebShareAdapter qZoneWebShareAdapter = this.adapter;
        if (qZoneWebShareAdapter != null) {
            return qZoneWebShareAdapter.m21342h();
        }
        return super.onFinish();
    }

    @Override // com.mob.tools.FakeActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        finish();
    }
}
