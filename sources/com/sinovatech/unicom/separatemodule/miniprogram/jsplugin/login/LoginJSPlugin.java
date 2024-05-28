package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.WebView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.p315ui.activity.LoginActivity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.LoginJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/login")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        if (this.parameterJO == null) {
            this.parameterJO = new JSONObject();
        }
        String optString = this.parameterJO.optString("url");
        String optString2 = this.parameterJO.optString("msg");
        String optString3 = this.parameterJO.optString("isLoginTarget");
        Intent intent = new Intent(this.activityContext, LoginBindActivity.class);
        intent.putExtra("paygreenUrl", this.f18589wv.getUrl());
        if ("y".equals(optString3)) {
            String optString4 = this.parameterJO.optString("loginType");
            String optString5 = this.parameterJO.optString("account");
            String optString6 = this.parameterJO.optString("areaId");
            Intent intent2 = new Intent(this.activityContext, LoginActivity.class);
            if ("02".equals(optString4)) {
                intent2.putExtra("guhuaAreaId", optString6);
                intent2.putExtra("guhuaTologin", "YES");
            } else if ("03".equals(optString4)) {
                intent2.putExtra("broadbandAreaId", optString6);
                intent2.putExtra("broadbandAccount", optString5);
                intent2.putExtra("broadbandAccountAndAreaidToLogin", "YES");
            } else if ("05".equals(optString4)) {
                intent2.putExtra("broadbandAccountAndAreaidToLogin", "YES");
            }
            intent2.putExtra("account", optString5);
            intent2.putExtra("logintype", optString4);
            intent = intent2;
        }
        UnicomHomeConstants.isJsStartLogin = true;
        UnicomHomeConstants.isJsStartLogin2 = true;
        UnicomHomeConstants.isJsStartLogin3 = true;
        new AvoidOnResult(this.activityContext).startForResult(intent, new C92691(optString, optString2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.LoginJSPlugin$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C92691 implements AvoidOnResult.Callback {
        final /* synthetic */ String val$msg;
        final /* synthetic */ String val$url;

        C92691(String str, String str2) {
            this.val$url = str;
            this.val$msg = str2;
        }

        @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
        public void onActivityResult(int i, Intent intent) {
            if (App.hasLogined()) {
                UnicomCookieManager.addLoginCookie();
                if (TextUtils.isEmpty(this.val$url)) {
                    LoginJSPlugin.this.f18589wv.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.-$$Lambda$LoginJSPlugin$1$2xh0ZUlZXAcCZlWSi13dU7ooWuw
                        @Override // java.lang.Runnable
                        public final void run() {
                            LoginJSPlugin.C92691.lambda$onActivityResult$0(LoginJSPlugin.C92691.this);
                        }
                    });
                    return;
                }
                WebView webView = LoginJSPlugin.this.f18589wv;
                final String str = this.val$url;
                webView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.-$$Lambda$LoginJSPlugin$1$lXZobc3ZuLqUcZc_Xcc9hhyj9Zc
                    @Override // java.lang.Runnable
                    public final void run() {
                        LoginJSPlugin.C92691.lambda$onActivityResult$1(LoginJSPlugin.C92691.this, str);
                    }
                });
            } else if ("retainWebview".equals(this.val$msg) || !(LoginJSPlugin.this.activityContext instanceof WebDetailActivity)) {
            } else {
                LoginJSPlugin.this.activityContext.finish();
            }
        }

        public static /* synthetic */ void lambda$onActivityResult$0(C92691 c92691) {
            WebView webView = LoginJSPlugin.this.f18589wv;
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, "javascript:history.go(0)");
            } else {
                webView.loadUrl("javascript:history.go(0)");
            }
        }

        public static /* synthetic */ void lambda$onActivityResult$1(C92691 c92691, String str) {
            WebView webView = LoginJSPlugin.this.f18589wv;
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, str);
            } else {
                webView.loadUrl(str);
            }
        }
    }
}
