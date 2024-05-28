package cn.sharesdk.tencent.p099qq.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.framework.utils.WebViewUtils;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: cn.sharesdk.tencent.qq.utils.DownLoadWebPage */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DownLoadWebPage extends FakeActivity {
    private SSOListener listener;
    private LinearLayout mainlayout;
    private WebView webView;
    private String url = "http://qzs.qq.com/open/mobile/login/qzsjump.html?sdkv=3.3.0.lite&display=mobile";
    private String downLoadUrl = "http://app.qq.com/detail/com.tencent.mobileqq?autodownload=1&norecommend=1&rootvia=opensdk";

    public void setListener(SSOListener sSOListener) {
        this.listener = sSOListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(-1));
        initPage(this.activity);
        this.activity.setContentView(this.mainlayout);
    }

    private void initPage(Activity activity) {
        initView();
        WebView webView = this.webView;
        NBSWebViewClient nBSWebViewClient = new NBSWebViewClient() { // from class: cn.sharesdk.tencent.qq.utils.DownLoadWebPage.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                Tracker.onPageFinished(this, webView2, str);
                super.onPageFinished(webView2, str);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                Tracker.onPageStarted(this, webView2, str, bitmap);
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                if (str != null) {
                    try {
                        if (str.startsWith("download://")) {
                            DownLoadWebPage.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(DownLoadWebPage.this.downLoadUrl)));
                            return true;
                        }
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21742a(th);
                        DownLoadWebPage.this.listener.onFailed(th);
                        DownLoadWebPage.this.listener = null;
                        DownLoadWebPage.this.finishOnSuccess();
                        return false;
                    }
                }
                return false;
            }
        };
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, nBSWebViewClient);
        } else {
            webView.setWebViewClient(nBSWebViewClient);
        }
        WebView webView2 = this.webView;
        String str = this.url;
        if (webView2 instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView2, str);
        } else {
            webView2.loadUrl(str);
        }
    }

    private void initView() {
        this.mainlayout = new LinearLayout(getContext());
        this.mainlayout.setOrientation(1);
        new LinearLayout.LayoutParams(-1, -1);
        this.webView = new WebView(getContext());
        this.mainlayout.addView(this.webView, new LinearLayout.LayoutParams(-1, 0, 11.0f));
        initWebView();
    }

    private void initWebView() {
        if (Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT < 17) {
            try {
                Method method = this.webView.getClass().getMethod("removeJavascriptInterface", String.class);
                method.setAccessible(true);
                method.invoke(this.webView, "searchBoxJavaBridge_");
                method.invoke(this.webView, "accessibility");
                method.invoke(this.webView, "accessibilityTraversal");
            } catch (Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        }
        WebSettings settings = this.webView.getSettings();
        settings.setCacheMode(2);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        this.webView.setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        WebViewUtils.m21666a(this.webView, false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 1) {
            WebView webView = this.webView;
            if (webView != null && webView.canGoBack()) {
                this.webView.goBack();
            } else {
                this.listener.onCancel();
                this.listener = null;
                finishOnSuccess();
            }
            return true;
        }
        return super.onKeyEvent(i, keyEvent);
    }

    public void finishOnSuccess() {
        finish();
    }
}
