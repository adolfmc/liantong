package cn.sharesdk.sina.weibo.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.sina.weibo.sdk.C1809a;
import cn.sharesdk.sina.weibo.sdk.p097a.WeiboWebPageLayout;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.mob.tools.RxMob;
import com.mob.tools.network.KVPair;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WebSharePage extends FakeActivity implements View.OnClickListener {
    private static final String SHARE_URL = "http://service.weibo.com/share/mobilesdk.php";
    private static final String UPLOAD_PIC_URL = "http://service.weibo.com/share/mobilesdk_uppic.php";
    private AuthorizeListener actionListener;
    private String appKey;
    private Button btnRetry;
    private WeiboWebPageLayout layout;
    private LinearLayout llRetry;
    private int pageStatus;
    private Platform.ShareParams params;
    private String token;
    private TextView tvBack;
    private WebView webView;

    public void setAppKey(String str, String str2) {
        this.appKey = str;
        this.token = str2;
    }

    public void setShareParams(Platform.ShareParams shareParams) {
        this.params = shareParams;
    }

    public void setListener(AuthorizeListener authorizeListener) {
        this.actionListener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        this.layout = new WeiboWebPageLayout(getContext());
        this.activity.setContentView(this.layout.m21609a(ResHelper.getStringRes(getContext(), "ssdk_sina_web_title")));
        initView();
    }

    private void initView() {
        this.tvBack = this.layout.m21607b();
        this.webView = this.layout.m21606c();
        this.llRetry = this.layout.m21605d();
        this.btnRetry = this.layout.m21610a();
        this.tvBack.setOnClickListener(this);
        this.btnRetry.setOnClickListener(this);
        if (ShareSDK.isRemoveCookieOnAuthorize()) {
            CookieSyncManager.createInstance(this.activity);
            CookieManager.getInstance().removeAllCookie();
        }
        WebView webView = this.webView;
        NBSWebViewClient nBSWebViewClient = new NBSWebViewClient() { // from class: cn.sharesdk.sina.weibo.utils.WebSharePage.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                Tracker.onPageStarted(this, webView2, str, bitmap);
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                Tracker.onPageFinished(this, webView2, str);
                if (WebSharePage.this.pageStatus == -1) {
                    WebSharePage.this.llRetry.setVisibility(0);
                    WebSharePage.this.webView.setVisibility(8);
                }
                WebSharePage.this.pageStatus = 0;
                super.onPageFinished(webView2, str);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onReceivedError(WebView webView2, int i, String str, String str2) {
                super.onReceivedError(webView2, i, str, str2);
                String url = webView2.getUrl();
                if (TextUtils.isEmpty(url) || TextUtils.isEmpty(str2)) {
                    return;
                }
                Uri parse = Uri.parse(url);
                Uri parse2 = Uri.parse(str2);
                if (parse.getHost().equals(parse2.getHost()) && parse.getScheme().equals(parse2.getScheme())) {
                    WebSharePage.this.pageStatus = -1;
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                if (str.startsWith("sinaweibo://browser/close")) {
                    if (WebSharePage.this.actionListener != null) {
                        WebSharePage.this.reCode(str);
                        return true;
                    }
                    return true;
                }
                return false;
            }
        };
        if (webView instanceof WebView) {
            NBSWebLoadInstrument.setWebViewClient(webView, nBSWebViewClient);
        } else {
            webView.setWebViewClient(nBSWebViewClient);
        }
        loadWebView();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.tvBack) {
            AuthorizeListener authorizeListener = this.actionListener;
            if (authorizeListener != null) {
                authorizeListener.onCancel();
            }
            finish();
        } else if (view == this.btnRetry) {
            this.llRetry.setVisibility(8);
            this.webView.setVisibility(0);
            loadWebView();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reCode(String str) {
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        String string = urlToBundle.getString("code");
        String string2 = urlToBundle.getString("msg");
        if (this.actionListener != null) {
            if (TextUtils.isEmpty(string)) {
                this.actionListener.onCancel();
            } else if ("0".equals(string)) {
                this.actionListener.onComplete(urlToBundle);
            } else {
                this.actionListener.onError(new Throwable(string2));
            }
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getReqUrl(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("title", this.params.getText()));
        arrayList.add(new KVPair("source", this.appKey));
        arrayList.add(new KVPair("access_token", this.token));
        arrayList.add(new KVPair("packagename", this.activity.getPackageName()));
        arrayList.add(new KVPair("picinfo", str));
        arrayList.add(new KVPair("luicode", "10000360"));
        arrayList.add(new KVPair("key_hash", C1809a.m21612a(getContext(), this.activity.getPackageName())));
        arrayList.add(new KVPair("lfid", "OP_" + this.appKey));
        arrayList.add(new KVPair("version", "0041005000"));
        return "http://service.weibo.com/share/mobilesdk.php?" + ResHelper.encodeUrl(arrayList);
    }

    private String loadWebView() {
        RxMob.Subscribable create = RxMob.create(new RxMob.OnSubscribe<String>() { // from class: cn.sharesdk.sina.weibo.utils.WebSharePage.2
            @Override // com.mob.tools.RxMob.OnSubscribe
            public void call(RxMob.Subscriber<String> subscriber) {
                subscriber.onNext(WebSharePage.this.getPicId());
            }
        });
        create.subscribeOn(RxMob.Thread.NEW_THREAD);
        create.observeOn(RxMob.Thread.UI_THREAD);
        create.subscribe(new RxMob.Subscriber<String>() { // from class: cn.sharesdk.sina.weibo.utils.WebSharePage.3
            @Override // com.mob.tools.RxMob.Subscriber
            public void onStart() {
                super.onStart();
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onNext(String str) {
                WebView webView = WebSharePage.this.webView;
                String reqUrl = WebSharePage.this.getReqUrl(str);
                if (webView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) webView, reqUrl);
                } else {
                    webView.loadUrl(reqUrl);
                }
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onCompleted() {
                super.onCompleted();
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onError(Throwable th) {
                SSDKLog.m21740b().m21737b(th);
                WebView webView = WebSharePage.this.webView;
                String reqUrl = WebSharePage.this.getReqUrl(null);
                if (webView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) webView, reqUrl);
                } else {
                    webView.loadUrl(reqUrl);
                }
            }
        });
        return null;
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onFinish() {
        if (this.actionListener != null) {
            this.actionListener = null;
        }
        return super.onFinish();
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onKeyEvent(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getAction() == 0) {
            AuthorizeListener authorizeListener = this.actionListener;
            if (authorizeListener != null) {
                authorizeListener.onCancel();
            }
            finish();
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0063 A[Catch: Throwable -> 0x00fc, TryCatch #0 {Throwable -> 0x00fc, blocks: (B:3:0x0001, B:5:0x0009, B:7:0x0015, B:13:0x003e, B:15:0x004a, B:17:0x004f, B:19:0x005c, B:22:0x0063, B:24:0x0099, B:25:0x009e, B:27:0x00a6, B:28:0x00ab, B:31:0x00bb, B:33:0x00c6, B:35:0x00ce, B:38:0x00d7, B:40:0x00f3, B:9:0x0022, B:11:0x002e), top: B:48:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getPicId() {
        /*
            Method dump skipped, instructions count: 261
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.sina.weibo.utils.WebSharePage.getPicId():java.lang.String");
    }

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        WebView webView = this.webView;
        if (webView != null) {
            webView.setFocusable(false);
        }
    }
}
