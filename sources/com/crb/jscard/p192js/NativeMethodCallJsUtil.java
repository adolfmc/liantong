package com.crb.jscard.p192js;

import android.os.Build;
import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import java.util.Objects;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.crb.jscard.js.NativeMethodCallJsUtil */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NativeMethodCallJsUtil {
    private static final String TAG = "NaviteCallJsUtil";

    @NBSInstrumented
    /* renamed from: com.crb.jscard.js.NativeMethodCallJsUtil$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC41891 implements Runnable {
        public final /* synthetic */ String val$fucName;
        public final /* synthetic */ StringBuilder val$sb;
        public final /* synthetic */ WebView val$webView;

        public RunnableC41891(WebView webView, String str, StringBuilder sb) {
            this.val$webView = webView;
            this.val$fucName = str;
            this.val$sb = sb;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (Build.VERSION.SDK_INT >= 19) {
                ((WebView) Objects.requireNonNull(this.val$webView)).evaluateJavascript("javascript:" + this.val$fucName + this.val$sb.toString(), new ValueCallback() { // from class: com.crb.jscard.js.-$$Lambda$NativeMethodCallJsUtil$1$cvPgwgU1PxzxLnVrX_-FgwZdMtc
                    @Override // android.webkit.ValueCallback
                    public final void onReceiveValue(Object obj) {
                        C14231v.m72b("NaviteCallJsUtil", "onReceiveValue:" + ((String) obj));
                    }
                });
                return;
            }
            WebView webView = (WebView) Objects.requireNonNull(this.val$webView);
            String str = "javascript:" + this.val$fucName + this.val$sb.toString();
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, str);
            } else {
                webView.loadUrl(str);
            }
        }
    }

    public static void refreshWebView(WebView webView, String str, String... strArr) {
        StringBuilder sb = new StringBuilder("('");
        for (int i = 0; i < strArr.length; i++) {
            String str2 = strArr[i];
            if (!TextUtils.isEmpty(str2)) {
                if (i == strArr.length - 1) {
                    sb.append(str2);
                } else {
                    sb.append(str2);
                    sb.append("','");
                }
            }
        }
        sb.append("')");
        webView.post(new RunnableC41891(webView, str, sb));
    }
}
