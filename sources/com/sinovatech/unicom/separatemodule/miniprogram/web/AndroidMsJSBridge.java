package com.sinovatech.unicom.separatemodule.miniprogram.web;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.net.URLEncoder;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AndroidMsJSBridge {
    private Activity activityContext;
    private Handler androidMSJSBridgeHandler;
    private BaseWebFragment webFragment;

    /* renamed from: wv */
    private WebView f18588wv;

    public AndroidMsJSBridge(Activity activity, WebView webView) {
        this.activityContext = activity;
        this.f18588wv = webView;
        this.webFragment = new BaseWebFragment();
        this.androidMSJSBridgeHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.web.AndroidMsJSBridge.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
            }
        };
    }

    public AndroidMsJSBridge(Activity activity, WebView webView, BaseWebFragment baseWebFragment) {
        this.activityContext = activity;
        this.f18588wv = webView;
        this.webFragment = baseWebFragment;
        this.androidMSJSBridgeHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.web.AndroidMsJSBridge.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
            }
        };
    }

    @JavascriptInterface
    public String execSync(String str) {
        String execSync;
        try {
            String string = new JSONObject(str).getString("action");
            ARouter aRouter = ARouter.getInstance();
            BaseJSPlugin baseJSPlugin = (BaseJSPlugin) aRouter.build("/MsJSPlugin/" + string).navigation(this.activityContext);
            MsLogUtil.m7979d("MsJSPlugin", "JS插件=" + baseJSPlugin.getClass().getSimpleName() + " 单例模式=" + baseJSPlugin.SingletonPattern);
            if (baseJSPlugin.SingletonPattern.booleanValue()) {
                execSync = baseJSPlugin.execSync(this.activityContext, this.webFragment, this.f18588wv, str);
            } else {
                execSync = ((BaseJSPlugin) Class.forName(baseJSPlugin.getClass().getName()).newInstance()).execSync(this.activityContext, this.webFragment, this.f18588wv, str);
            }
            return execSync;
        } catch (Exception e) {
            MsLogUtil.m7978e("BaseWebFragment>>AndroidMsJSBridge>>exec>>>>Exception: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public void exec(final String str) {
        this.androidMSJSBridgeHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.web.AndroidMsJSBridge.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String string = new JSONObject(str).getString("action");
                    ARouter aRouter = ARouter.getInstance();
                    BaseJSPlugin baseJSPlugin = (BaseJSPlugin) aRouter.build("/MsJSPlugin/" + string).navigation(AndroidMsJSBridge.this.activityContext);
                    MsLogUtil.m7979d("MsJSPlugin", "JS插件=" + baseJSPlugin.getClass().getSimpleName() + " 单例模式=" + baseJSPlugin.SingletonPattern);
                    if (baseJSPlugin.SingletonPattern.booleanValue()) {
                        baseJSPlugin.exec(AndroidMsJSBridge.this.activityContext, AndroidMsJSBridge.this.webFragment, AndroidMsJSBridge.this.f18588wv, str);
                    } else {
                        ((BaseJSPlugin) Class.forName(baseJSPlugin.getClass().getName()).newInstance()).exec(AndroidMsJSBridge.this.activityContext, AndroidMsJSBridge.this.webFragment, AndroidMsJSBridge.this.f18588wv, str);
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
            WebView webView = this.f18588wv;
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
