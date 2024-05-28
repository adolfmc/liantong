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
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.networkbench.agent.impl.instrumentation.NBSWebViewClient;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WebAuthPage extends FakeActivity implements View.OnClickListener {
    private static final String AUTH_URL = "https://open.weibo.cn/oauth2/authorize";
    public static final String SCOPE = "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write";
    private AuthorizeListener actionListener;
    private String appKey;
    private Button btnRetry;
    private WeiboWebPageLayout layout;
    private LinearLayout llRetry;
    private int pageStatus;
    private String redirectUrl;
    private String token;
    private TextView tvBack;
    private WebView webView;

    public void setAuthParams(String str, String str2, String str3) {
        this.appKey = str;
        this.redirectUrl = str2;
        this.token = str3;
    }

    public void setListener(AuthorizeListener authorizeListener) {
        this.actionListener = authorizeListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        this.layout = new WeiboWebPageLayout(getContext());
        this.activity.setContentView(this.layout.m21609a(ResHelper.getStringRes(getContext(), "ssdk_sina_web_login_title")));
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
        NBSWebViewClient nBSWebViewClient = new NBSWebViewClient() { // from class: cn.sharesdk.sina.weibo.utils.WebAuthPage.1
            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageStarted(WebView webView2, String str, Bitmap bitmap) {
                Tracker.onPageStarted(this, webView2, str, bitmap);
                super.onPageStarted(webView2, str, bitmap);
            }

            @Override // com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
            public void onPageFinished(WebView webView2, String str) {
                Tracker.onPageFinished(this, webView2, str);
                if (WebAuthPage.this.pageStatus == -1) {
                    WebAuthPage.this.llRetry.setVisibility(0);
                    WebAuthPage.this.webView.setVisibility(8);
                }
                WebAuthPage.this.pageStatus = 0;
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
                    WebAuthPage.this.pageStatus = -1;
                }
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                if (str.startsWith(WebAuthPage.this.redirectUrl)) {
                    if (WebAuthPage.this.actionListener != null) {
                        WebAuthPage.this.reCode(str);
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
        AuthorizeListener authorizeListener;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this.tvBack && (authorizeListener = this.actionListener) != null) {
            authorizeListener.onCancel();
            finish();
        } else if (view == this.btnRetry) {
            this.llRetry.setVisibility(8);
            this.webView.setVisibility(0);
            loadWebView();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAuthorizeUrl() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("client_id", this.appKey));
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("redirect_uri", this.redirectUrl));
        arrayList.add(new KVPair("packagename", this.activity.getPackageName()));
        arrayList.add(new KVPair("response_type", "code"));
        arrayList.add(new KVPair("luicode", "10000360"));
        String str = this.token;
        if (str != null && !TextUtils.isEmpty(str)) {
            arrayList.add(new KVPair("trans_token", this.token));
            arrayList.add(new KVPair("trans_access_token", this.token));
        }
        arrayList.add(new KVPair("key_hash", C1809a.m21612a(getContext(), this.activity.getPackageName())));
        arrayList.add(new KVPair("version", "0041005000"));
        arrayList.add(new KVPair("scope", "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write"));
        arrayList.add(new KVPair("display", "mobile"));
        return "https://open.weibo.cn/oauth2/authorize?" + ResHelper.encodeUrl(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reCode(String str) {
        Bundle urlToBundle = ResHelper.urlToBundle(str);
        if (urlToBundle.containsKey("access_token")) {
            this.actionListener.onComplete(urlToBundle);
        } else if (urlToBundle.containsKey("error")) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("error_uri:", urlToBundle.containsKey("error_uri") ? urlToBundle.getString("error_uri") : "");
                jSONObject.put("error:", urlToBundle.containsKey("error") ? urlToBundle.getString("error") : "");
                jSONObject.put("error_description:", urlToBundle.containsKey("error_description") ? urlToBundle.getString("error_description") : "");
                jSONObject.put("error_code:", urlToBundle.containsKey("error_code") ? urlToBundle.getString("error_code") : "");
                this.actionListener.onError(new Throwable(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)));
            } catch (Throwable th) {
                SSDKLog.m21740b().m21732d(th);
                this.actionListener.onError(th);
            }
        }
        finish();
    }

    @Override // com.mob.tools.FakeActivity
    public boolean onFinish() {
        if (this.actionListener != null) {
            this.actionListener = null;
        }
        return super.onFinish();
    }

    private String loadWebView() {
        RxMob.Subscribable create = RxMob.create(new RxMob.OnSubscribe<String>() { // from class: cn.sharesdk.sina.weibo.utils.WebAuthPage.2
            @Override // com.mob.tools.RxMob.OnSubscribe
            public void call(RxMob.Subscriber<String> subscriber) {
                WebView webView = WebAuthPage.this.webView;
                String authorizeUrl = WebAuthPage.this.getAuthorizeUrl();
                if (webView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) webView, authorizeUrl);
                } else {
                    webView.loadUrl(authorizeUrl);
                }
                subscriber.onCompleted();
            }
        });
        create.subscribeOn(RxMob.Thread.UI_THREAD);
        create.observeOn(RxMob.Thread.UI_THREAD);
        create.subscribe(new RxMob.Subscriber<String>() { // from class: cn.sharesdk.sina.weibo.utils.WebAuthPage.3
            @Override // com.mob.tools.RxMob.Subscriber
            public void onCompleted() {
                super.onCompleted();
            }

            @Override // com.mob.tools.RxMob.Subscriber
            public void onError(Throwable th) {
                SSDKLog.m21740b().m21742a(th);
            }
        });
        return null;
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

    @Override // com.mob.tools.FakeActivity
    public void onDestroy() {
        WebView webView = this.webView;
        if (webView != null) {
            webView.setFocusable(false);
        }
    }
}
