package com.sinovatech.unicom.separatemodule.fuchuang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.RelativeLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.basic.p315ui.JSInvokeHandler;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.AndroidMsJSBridge;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;
import szcom.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom;

@Route(path = "/MsJSPlugin/HomeWebAlert")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeWebAlertJsPlugin extends BaseJSPlugin {
    private static JSONObject params;
    private static String wvUrl;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        exe();
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return exe();
    }

    private String exe() {
        char c;
        String optString = this.parameterJO.optString("action");
        int hashCode = optString.hashCode();
        if (hashCode == -75605984) {
            if (optString.equals("getData")) {
                c = 2;
            }
            c = 65535;
        } else if (hashCode == 3273774) {
            if (optString.equals("jump")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 3327206) {
            if (hashCode == 3529469 && optString.equals("show")) {
                c = 1;
            }
            c = 65535;
        } else {
            if (optString.equals(TrackLoadSettingsAtom.TYPE)) {
                c = 0;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                wvUrl = this.f18589wv.getUrl();
                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.HomeWebAlertJsPlugin.1
                    @Override // java.lang.Runnable
                    @SuppressLint({"CheckResult"})
                    public void run() {
                        JSONObject unused = HomeWebAlertJsPlugin.params = HomeWebAlertJsPlugin.this.parameterJO.optJSONObject("parmams");
                        String optString2 = HomeWebAlertJsPlugin.this.parameterJO.optString("url");
                        if (HomeWebAlertJsPlugin.this.activityContext instanceof MainActivity) {
                            ((MainActivity) HomeWebAlertJsPlugin.this.activityContext).showOrDismisspd(true);
                        }
                        HomeWebAlertJsPlugin.this.loadWebView(optString2);
                        Observable.timer(5L, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.HomeWebAlertJsPlugin.1.1
                            @Override // io.reactivex.functions.Consumer
                            public void accept(Long l) throws Exception {
                                if (HomeWebAlertJsPlugin.this.activityContext instanceof MainActivity) {
                                    ((MainActivity) HomeWebAlertJsPlugin.this.activityContext).showOrDismisspd(false);
                                }
                            }
                        });
                    }
                });
                return "";
            case 1:
                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.HomeWebAlertJsPlugin.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (HomeWebAlertJsPlugin.this.activityContext instanceof MainActivity) {
                            MainActivity mainActivity = (MainActivity) HomeWebAlertJsPlugin.this.activityContext;
                            mainActivity.showHomeAlertVisiable(0, HomeWebAlertJsPlugin.wvUrl);
                            mainActivity.showOrDismisspd(false);
                        }
                    }
                });
                return "";
            case 2:
                MsLogUtil.m7979d("callbackSuccessSync", "params:" + params);
                return callbackSuccessSync(params);
            case 3:
                this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.HomeWebAlertJsPlugin.3
                    @Override // java.lang.Runnable
                    public void run() {
                        String optString2 = HomeWebAlertJsPlugin.this.parameterJO.optString("url");
                        if (!TextUtils.isEmpty(optString2)) {
                            IntentManager.gotoWebViewActivity(HomeWebAlertJsPlugin.this.activityContext, optString2, "");
                        }
                        if (HomeWebAlertJsPlugin.this.activityContext instanceof MainActivity) {
                            MainActivity mainActivity = (MainActivity) HomeWebAlertJsPlugin.this.activityContext;
                            RelativeLayout relativeLayout = (RelativeLayout) mainActivity.findViewById(2131297997);
                            try {
                                ((PBWebView) relativeLayout.getChildAt(0)).destroy();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            relativeLayout.removeAllViews();
                            mainActivity.showHomeAlertVisiable(8, "");
                        }
                    }
                });
                return "";
            default:
                return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadWebView(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            PBWebView pBWebView = new PBWebView(this.activityContext);
            pBWebView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            pBWebView.requestFocus();
            pBWebView.setBackgroundColor(0);
            pBWebView.getSettings().setCacheMode(2);
            pBWebView.addJavascriptInterface(new AndroidMsJSBridge(this.activityContext, pBWebView), "AndroidMsJSBridge");
            pBWebView.addJavascriptInterface(new JSInvokeHandler((AppCompatActivity) this.activityContext, pBWebView, new Handler()), "js_invoke");
            pBWebView.loadURL(str);
            pBWebView.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuang.HomeWebAlertJsPlugin.4
                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onRecevieError(int i, String str2, String str3) {
                    if (HomeWebAlertJsPlugin.this.activityContext instanceof MainActivity) {
                        MainActivity mainActivity = (MainActivity) HomeWebAlertJsPlugin.this.activityContext;
                        mainActivity.showOrDismisspd(false);
                        mainActivity.showHomeAlertVisiable(8, "");
                    }
                    return super.onRecevieError(i, str2, str3);
                }
            });
            if (this.activityContext instanceof MainActivity) {
                RelativeLayout relativeLayout = (RelativeLayout) ((MainActivity) this.activityContext).findViewById(2131297997);
                relativeLayout.removeAllViews();
                relativeLayout.addView(pBWebView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
