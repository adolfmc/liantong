package com.sinovatech.unicom.separatemodule.tongyicaiji;

import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentCustomManager;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TYCJJSManager {
    private static final String TAG = "TYCJJSManager";

    public static void registerJs(WebView webView, final Consumer<String> consumer) {
        try {
            if (!"about:blank".equals(webView.getUrl()) && TYCJConfigUtil.isNotJsInjection()) {
                String tongyicaijiJs = CacheDataCenter.getInstance().getTongyicaijiJs();
                webView.evaluateJavascript("javascript:" + tongyicaijiJs, new ValueCallback<String>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJJSManager.1
                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(String str) {
                        try {
                            Consumer.this.accept(str);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void collectWebTimingData(WebView webView, String str) {
        try {
            if ("about:blank".equals(webView.getUrl())) {
                return;
            }
            if ((TYCJConfigUtil.isOpen("tianYan") && TYCJConfigUtil.isWhiteUrl("tianYan", webView.getUrl(), str)) || (TYCJConfigUtil.isOpen("H5Info") && TYCJConfigUtil.isWhiteUrl("H5Info", webView.getUrl(), str))) {
                String uuid = RecentCustomManager.uuid();
                String pvLogSessionId = App.getPvLogSessionId();
                webView.evaluateJavascript("javascript:try{var result = unicomYTH5Info('" + uuid + "','" + pvLogSessionId + "');js_invoke.handleH5data(JSON.stringify(result));}catch(err){js_invoke.handleH5data(err);}", new ValueCallback<String>() { // from class: com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJJSManager.2
                    @Override // android.webkit.ValueCallback
                    public void onReceiveValue(String str2) {
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
