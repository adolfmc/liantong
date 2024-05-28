package com.sinovatech.unicom.separatemodule.fuchuangs.view;

import android.os.Handler;
import android.os.Message;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.widget.RelativeLayout;
import com.alibaba.android.arouter.launcher.ARouter;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.p315ui.WaveDialogManager;
import com.sinovatech.unicom.basic.server.UnicomCookieManager;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FloatWindowWebVIew {
    private static final String TAG = "FloatWindowWebVIew";
    private static volatile FloatWindowWebVIew mCameraInterface;
    private Handler androidMSJSBridgeHandler;
    private AppCompatActivity context;
    private String entranceURL;
    public boolean isLoadError;
    public WebLoadListener listener;

    /* renamed from: wv */
    private PBWebView f18524wv;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface WebLoadListener {
        void onFail();

        void onSuccess();
    }

    public void setListener(WebLoadListener webLoadListener) {
        this.listener = webLoadListener;
    }

    public static synchronized FloatWindowWebVIew getInstance(AppCompatActivity appCompatActivity) {
        FloatWindowWebVIew floatWindowWebVIew;
        synchronized (FloatWindowWebVIew.class) {
            mCameraInterface = new FloatWindowWebVIew(appCompatActivity);
            floatWindowWebVIew = mCameraInterface;
        }
        return floatWindowWebVIew;
    }

    public FloatWindowWebVIew(AppCompatActivity appCompatActivity) {
        this.isLoadError = false;
        this.context = appCompatActivity;
        this.isLoadError = false;
        getWv();
    }

    public PBWebView getWv() {
        try {
            UnicomCookieManager.addLoginCookie();
            if (this.f18524wv != null) {
                return this.f18524wv;
            }
            this.f18524wv = new PBWebView(this.context);
            this.f18524wv.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.f18524wv.requestFocus();
            this.f18524wv.setBackgroundColor(1);
            this.f18524wv.getSettings().setCacheMode(2);
            this.f18524wv.addJavascriptInterface(new AlertJS(), "AndroidMsJSBridge");
            this.f18524wv.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew.1
                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onReceivedTitle(String str) {
                    MsLogUtil.m7979d("FLOAT_WINDOW", "onReceivedTitle = " + str);
                    if (!TextUtils.isEmpty(str) && (str.contains("受理结果") || str.contains("405 Not Allowed") || str.contains("找不到网页"))) {
                        FloatWindowWebVIew floatWindowWebVIew = FloatWindowWebVIew.this;
                        floatWindowWebVIew.isLoadError = true;
                        if (floatWindowWebVIew.listener != null) {
                            FloatWindowWebVIew.this.listener.onFail();
                        }
                    }
                    return true;
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onRecevieError(int i, String str, String str2) {
                    MsLogUtil.m7979d("FLOAT_WINDOW", "onRecevieError = " + str);
                    FloatWindowWebVIew floatWindowWebVIew = FloatWindowWebVIew.this;
                    floatWindowWebVIew.isLoadError = true;
                    if (floatWindowWebVIew.listener != null) {
                        FloatWindowWebVIew.this.listener.onFail();
                    }
                    return true;
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public void onProgressChanged(int i) {
                    MsLogUtil.m7979d("FLOAT_WINDOW", i + "");
                    if (i < 80 || FloatWindowWebVIew.this.isLoadError || FloatWindowWebVIew.this.listener == null) {
                        return;
                    }
                    FloatWindowWebVIew.this.listener.onSuccess();
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onPageFinished() {
                    WaveDialogManager.disMiss();
                    MsLogUtil.m7979d("FLOAT_WINDOW", "onPageFinished");
                    return false;
                }
            });
            return this.f18524wv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void loadUrl(String str) {
        this.entranceURL = str;
        this.f18524wv.loadURL(this.entranceURL);
        this.f18524wv.setBackgroundColor(0);
    }

    public void destory() {
        try {
            if (this.f18524wv != null) {
                PBWebView pBWebView = this.f18524wv;
                if (pBWebView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) pBWebView, "about:blank");
                } else {
                    pBWebView.loadUrl("about:blank");
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "销毁页面异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AlertJS {
        public AlertJS() {
            FloatWindowWebVIew.this.androidMSJSBridgeHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew.AlertJS.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                }
            };
        }

        @JavascriptInterface
        public void exec(final String str) {
            FloatWindowWebVIew.this.androidMSJSBridgeHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.view.-$$Lambda$FloatWindowWebVIew$AlertJS$FcLHeWpTZwHI6V49cn2RamqrrpE
                @Override // java.lang.Runnable
                public final void run() {
                    FloatWindowWebVIew.AlertJS.lambda$exec$0(FloatWindowWebVIew.AlertJS.this, str);
                }
            });
        }

        public static /* synthetic */ void lambda$exec$0(AlertJS alertJS, String str) {
            try {
                String optString = new JSONObject(str).optString("action");
                ARouter aRouter = ARouter.getInstance();
                ((BaseJSPlugin) aRouter.build("/MsJSPlugin/" + optString).navigation(FloatWindowWebVIew.this.context)).exec(FloatWindowWebVIew.this.context, new BaseWebFragment(), FloatWindowWebVIew.this.f18524wv, str);
            } catch (Exception e) {
                MsLogUtil.m7977e(FloatWindowWebVIew.TAG, "浮窗解析数据异常:" + e.getMessage());
            }
        }

        @JavascriptInterface
        public String execSync(String str) {
            String str2 = null;
            try {
                String string = new JSONObject(str).getString("action");
                ARouter aRouter = ARouter.getInstance();
                BaseJSPlugin baseJSPlugin = (BaseJSPlugin) aRouter.build("/MsJSPlugin/" + string).navigation(FloatWindowWebVIew.this.context);
                MsLogUtil.m7979d("MsJSPlugin", "JS插件=" + baseJSPlugin.getClass().getSimpleName() + " 单例模式=" + baseJSPlugin.SingletonPattern);
                str2 = baseJSPlugin.SingletonPattern.booleanValue() ? baseJSPlugin.execSync(FloatWindowWebVIew.this.context, null, FloatWindowWebVIew.this.f18524wv, str) : ((BaseJSPlugin) Class.forName(baseJSPlugin.getClass().getName()).newInstance()).execSync(FloatWindowWebVIew.this.context, null, FloatWindowWebVIew.this.f18524wv, str);
            } catch (Exception e) {
                MsLogUtil.m7978e("BaseWebFragment>>AndroidMsJSBridge>>exec>>>>Exception: " + e.getMessage());
                e.printStackTrace();
            }
            return str2;
        }
    }
}
