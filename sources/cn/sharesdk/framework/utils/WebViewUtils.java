package cn.sharesdk.framework.utils;

import android.os.Build;
import android.webkit.WebView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WebViewUtils {
    /* renamed from: a */
    public static void m21666a(WebView webView, boolean z) {
        if (webView == null) {
            return;
        }
        try {
            webView.getSettings().setAllowFileAccess(z);
            webView.getSettings().setSavePassword(z);
            if (Build.VERSION.SDK_INT >= 16) {
                webView.getSettings().setAllowFileAccessFromFileURLs(z);
                webView.getSettings().setAllowUniversalAccessFromFileURLs(z);
            }
        } catch (Throwable unused) {
        }
    }
}
