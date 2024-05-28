package cn.sharesdk.tencent.qzone.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import cn.sharesdk.framework.authorize.AuthorizeWebviewClient;
import cn.sharesdk.framework.authorize.WebAuthorizeActivity;
import cn.sharesdk.framework.utils.SSDKLog;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.utils.DeviceHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import java.net.URLDecoder;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: cn.sharesdk.tencent.qzone.utils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class QZoneAuthorizeWebviewClient extends AuthorizeWebviewClient {
    public QZoneAuthorizeWebviewClient(WebAuthorizeActivity webAuthorizeActivity) {
        super(webAuthorizeActivity);
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [cn.sharesdk.tencent.qzone.utils.a$1] */
    @Override // cn.sharesdk.framework.SSDKWebViewClient, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, final String str) {
        if (str.startsWith(this.redirectUri)) {
            webView.setVisibility(4);
            webView.stopLoading();
            this.activity.finish();
            new Thread() { // from class: cn.sharesdk.tencent.qzone.utils.a.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        QZoneAuthorizeWebviewClient.this.onComplete(str);
                    } catch (Throwable th) {
                        SSDKLog.m21740b().m21742a(th);
                    }
                }
            }.start();
            return true;
        }
        if (webView instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) webView, str);
        } else {
            webView.loadUrl(str);
        }
        return true;
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeWebviewClient
    public void onComplete(String str) {
        if (str.startsWith(this.redirectUri)) {
            str = str.substring(str.indexOf(35) + 1);
        }
        String[] split = str.split("&");
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str2 : split) {
            String[] split2 = str2.split("=");
            if (split2.length < 2) {
                hashMap.put(URLDecoder.decode(split2[0]), "");
            } else {
                hashMap.put(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1] == null ? "" : split2[1]));
            }
        }
        m21383a(hashMap);
    }

    /* renamed from: a */
    private void m21383a(HashMap<String, String> hashMap) {
        String str = hashMap.get("access_token");
        String str2 = hashMap.get("expires_in");
        String str3 = hashMap.get("error");
        String str4 = hashMap.get("error_description");
        String str5 = hashMap.get("pf");
        String str6 = hashMap.get("pfkey");
        String str7 = hashMap.get("pay_token");
        if (!TextUtils.isEmpty(str)) {
            try {
                HashMap<String, Object> m21359e = QZoneHelper.m21379a(this.activity.getHelper().getPlatform()).m21359e(str);
                if (m21359e != null && m21359e.size() > 0) {
                    if (!m21359e.containsKey("openid")) {
                        if (this.listener != null) {
                            this.listener.onError(new Throwable());
                            return;
                        }
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("access_token", str);
                    bundle.putString("open_id", String.valueOf(m21359e.get("openid")));
                    bundle.putString("expires_in", str2);
                    bundle.putString("pf", str5);
                    bundle.putString("pfkey", str6);
                    bundle.putString("pay_token", str7);
                    if (this.listener != null) {
                        this.listener.onComplete(bundle);
                        return;
                    }
                    return;
                }
                if (this.listener != null) {
                    this.listener.onError(new Throwable());
                }
            } catch (Throwable th) {
                if (this.listener != null) {
                    this.listener.onError(th);
                }
            }
        } else if (!TextUtils.isEmpty(str3)) {
            String str8 = str4 + " (" + str3 + ")";
            if (this.listener != null) {
                this.listener.onError(new Throwable(str8));
            }
        } else {
            this.listener.onError(new Throwable());
        }
    }

    @Override // cn.sharesdk.framework.SSDKWebViewClient, com.networkbench.agent.impl.instrumentation.NBSWebViewClient, android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        String[] strArr;
        String str;
        String str2;
        if (webView.getContext() instanceof Activity) {
            Activity activity = (Activity) webView.getContext();
            if ("zh".equals(DeviceHelper.getInstance(activity).getOSLanguage())) {
                strArr = new String[]{String.valueOf(new char[]{19981, 21463, 20449, 20219, 30340, 35777, 20070, 12290, 20320, 35201, 32487, 32493, 21527, 65311}), String.valueOf(new char[]{35777, 20070, 24050, 36807, 26399, 12290, 20320, 35201, 32487, 32493, 21527, 65311}), String.valueOf(new char[]{35777, 20070, 'I', 'D', 19981, 21305, 37197, 12290, 20320, 35201, 32487, 32493, 21527, 65311}), String.valueOf(new char[]{35777, 20070, 23578, 26410, 29983, 25928, 12290, 20320, 35201, 32487, 32493, 21527, 65311}), String.valueOf(new char[]{35777, 20070, 38169, 35823, 12290, 20320, 35201, 32487, 32493, 21527, 65311})};
                str = String.valueOf(new char[]{35777, 20070, 38169, 35823});
                String.valueOf(new char[]{32487, 32493});
                str2 = String.valueOf(new char[]{20572, 27490});
            } else {
                strArr = new String[]{"Certificate is untrusted. Do you want to continue anyway?", "Certificate has expired. Do you want to continue anyway?", "Certificate ID is mismatched. Do you want to continue anyway?", "Certificate is not yet valid. Do you want to continue anyway?", "Certificate error. Do you want to continue anyway?"};
                str = "SSL Certificate Error";
                str2 = "No";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(str);
            switch (sslError.getPrimaryError()) {
                case 0:
                    builder.setMessage(strArr[3]);
                    break;
                case 1:
                    builder.setMessage(strArr[1]);
                    break;
                case 2:
                    builder.setMessage(strArr[2]);
                    break;
                case 3:
                    builder.setMessage(strArr[0]);
                    break;
                default:
                    builder.setMessage(strArr[4]);
                    break;
            }
            builder.setCancelable(false);
            builder.setNegativeButton(str2, new DialogInterface.OnClickListener() { // from class: cn.sharesdk.tencent.qzone.utils.a.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tracker.onClick(dialogInterface, i);
                    dialogInterface.dismiss();
                    sslErrorHandler.cancel();
                }
            });
            try {
                builder.create().show();
                return;
            } catch (Throwable th) {
                SSDKLog.m21740b().m21737b(th);
                return;
            }
        }
        sslErrorHandler.cancel();
    }
}
