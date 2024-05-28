package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.blankj.utilcode.util.BarUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.manager.ManagerWebViewFullScreen */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ManagerWebViewFullScreen {
    private static View customView;
    private static WebChromeClient.CustomViewCallback customViewCallback;
    public static boolean isPortrait;

    public static void showCustomView(Activity activity, WebView webView, View view, WebChromeClient.CustomViewCallback customViewCallback2) {
        try {
            if (customView != null) {
                customViewCallback2.onCustomViewHidden();
                return;
            }
            customView = view;
            customViewCallback = customViewCallback2;
            ((FrameLayout) activity.getWindow().getDecorView()).addView(customView);
            webView.setVisibility(8);
            if (isPortrait) {
                activity.setRequestedOrientation(1);
            } else {
                activity.setRequestedOrientation(0);
            }
            BarUtils.setNavBarVisibility(activity, false);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void hideCustomView(Activity activity, WebView webView) {
        try {
            webView.setVisibility(0);
            isPortrait = false;
            if (customView == null) {
                return;
            }
            customView.setVisibility(8);
            FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
            frameLayout.removeView(customView);
            frameLayout.clearFocus();
            customView = null;
            try {
                if (customViewCallback != null) {
                    customViewCallback.onCustomViewHidden();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            BarUtils.setNavBarVisibility(activity, true);
            activity.setRequestedOrientation(1);
            webView.scrollBy(0, -200);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
