package com.sinovatech.unicom.separatemodule.webrtc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.net.URLEncoder;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcWebInstance {
    private static final String TAG = "RtcWebInstance";
    private static final RtcWebInstance mInstance = new RtcWebInstance();
    @SuppressLint({"HandlerLeak"})
    private Handler androidMSJSBridgeHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcWebInstance.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };
    private Context context;
    private boolean flag;
    private String lastUrl;
    private PBWebView rtcWebView;
    private String url;

    private RtcWebInstance() {
    }

    public static RtcWebInstance getInstance() {
        return mInstance;
    }

    public boolean isFlag() {
        return this.flag;
    }

    public void setFlag(boolean z) {
        this.flag = z;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setUrl(String str) {
        this.url = str;
        Log.d(TAG, "音视频推送url==" + str);
        Log.d(TAG, "app是否处于后台==" + this.flag);
        try {
            if (!TextUtils.isEmpty(str) && !"null".equals(str) && !this.flag) {
                openHiddenWebView(str);
            } else {
                PvCurrencyLogUtils.pvCurrencyLog("", 2, "S2ndpage1197", str, "200", "后台忽略", "", "", "", "");
            }
        } catch (Exception e) {
            MsLogUtil.m7978e("RtcWebInstance--setUrl==" + e.getMessage());
        }
    }

    public String getUrl() {
        return this.url;
    }

    private void openHiddenWebView(final String str) {
        this.rtcWebView = new PBWebView(this.context);
        this.rtcWebView.setBackgroundColor(0);
        this.rtcWebView.getSettings().setCacheMode(-1);
        PBWebView pBWebView = this.rtcWebView;
        pBWebView.addJavascriptInterface(new AndroidMsJSBridge(pBWebView), "AndroidMsJSBridge");
        this.rtcWebView.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcWebInstance.2
            @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
            public boolean onPageFinished() {
                Log.d(RtcWebInstance.TAG, "onPageFinished=");
                PvCurrencyLogUtils.pvCurrencyLog("", 2, "S2ndpage1197", str, "200", "正常拉起", "", "", "", "");
                return false;
            }
        });
        PBWebView pBWebView2 = this.rtcWebView;
        if (pBWebView2 instanceof Object) {
            NBSWebLoadInstrument.loadUrl((Object) pBWebView2, str);
        } else {
            pBWebView2.loadUrl(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AndroidMsJSBridge {

        /* renamed from: wv */
        private WebView f18639wv;

        public AndroidMsJSBridge(WebView webView) {
            this.f18639wv = webView;
        }

        @JavascriptInterface
        public String execSync(String str) {
            try {
                String string = new JSONObject(str).getString("action");
                ARouter aRouter = ARouter.getInstance();
                BaseJSPlugin baseJSPlugin = (BaseJSPlugin) aRouter.build("/MsJSPlugin/" + string).navigation(RtcWebInstance.this.context);
                MsLogUtil.m7979d("MsJSPlugin", "JS插件=" + baseJSPlugin.getClass().getSimpleName() + " 单例模式=" + baseJSPlugin.SingletonPattern);
                return baseJSPlugin.SingletonPattern.booleanValue() ? baseJSPlugin.execSync((Activity) RtcWebInstance.this.context, new WebFragment(), this.f18639wv, str) : ((BaseJSPlugin) Class.forName(baseJSPlugin.getClass().getName()).newInstance()).execSync((Activity) RtcWebInstance.this.context, new WebFragment(), this.f18639wv, str);
            } catch (Exception e) {
                MsLogUtil.m7978e("BaseWebFragment>>AndroidMsJSBridge>>exec>>>>Exception: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        @JavascriptInterface
        public void exec(final String str) {
            RtcWebInstance.this.androidMSJSBridgeHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcWebInstance.AndroidMsJSBridge.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String string = new JSONObject(str).getString("action");
                        ARouter aRouter = ARouter.getInstance();
                        BaseJSPlugin baseJSPlugin = (BaseJSPlugin) aRouter.build("/MsJSPlugin/" + string).navigation(RtcWebInstance.this.context);
                        MsLogUtil.m7979d("MsJSPlugin", "JS插件=" + baseJSPlugin.getClass().getSimpleName() + " 单例模式=" + baseJSPlugin.SingletonPattern);
                        if (baseJSPlugin.SingletonPattern.booleanValue()) {
                            baseJSPlugin.exec((Activity) RtcWebInstance.this.context, new WebFragment(), AndroidMsJSBridge.this.f18639wv, str);
                        } else {
                            ((BaseJSPlugin) Class.forName(baseJSPlugin.getClass().getName()).newInstance()).exec((Activity) RtcWebInstance.this.context, new WebFragment(), AndroidMsJSBridge.this.f18639wv, str);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("BaseWebFragment>>AndroidMsJSBridge>>exec>>>>Exception: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        }

        public void postEventToJS(String str, Object obj) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("action", str);
                jSONObject.put("parameter", obj);
                String encodeToString = Base64.encodeToString(URLEncoder.encode(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), "utf-8").getBytes("utf-8"), 0);
                WebView webView = this.f18639wv;
                String str2 = "javascript:try{MsJSBridge.receiveEventActionFromNative('" + encodeToString + "')}catch(e){}";
                if (webView instanceof Object) {
                    NBSWebLoadInstrument.loadUrl((Object) webView, str2);
                } else {
                    webView.loadUrl(str2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
