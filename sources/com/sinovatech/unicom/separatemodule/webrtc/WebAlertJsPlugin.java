package com.sinovatech.unicom.separatemodule.webrtc;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.webview.PBWebView;
import com.sinovatech.unicom.basic.webview.WebViewStatusListener;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.sinovatech.unicom.separatemodule.webrtc.RtcPermissDialog;
import com.sinovatech.unicom.separatemodule.webrtc.WebAlertJsPlugin;
import org.json.JSONException;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/webAlert")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebAlertJsPlugin extends BaseJSPlugin {
    private static final String TAG = "WebAlertJsPlugin";
    private Handler androidMSJSBridgeHandler;
    private boolean isError;
    private PBWebView webView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiddenWebView$0(int i, Intent intent) {
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        String optString = this.parameterJO.optString("action");
        UIUtils.logD("webAlert", "========action=" + optString);
        JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
        StringBuilder sb = new StringBuilder();
        sb.append("webAlert--params==");
        sb.append(!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject));
        Log.d(TAG, sb.toString());
        char c = 65535;
        int hashCode = optString.hashCode();
        if (hashCode != -1217487446) {
            if (hashCode != 3529469) {
                if (hashCode == 1599917632 && optString.equals("showAuthorizationAlert")) {
                    c = 2;
                }
            } else if (optString.equals("show")) {
                c = 0;
            }
        } else if (optString.equals("hidden")) {
            c = 1;
        }
        switch (c) {
            case 0:
                showWebView(optJSONObject.optString("url"));
                return;
            case 1:
                hiddenWebView(optJSONObject.optBoolean("shouldJump"), optJSONObject.optString("url"));
                return;
            case 2:
                if (optJSONObject != null) {
                    String str = "\"中国联通\"想访问您的相机和麦克风";
                    String optString2 = optJSONObject.optString("authorizationType");
                    if (!TextUtils.isEmpty(optString2) && !"null".equals(optString2)) {
                        if ("audio".equals(optString2)) {
                            str = "\"中国联通\"想访问您的麦克风";
                        } else if ("video".equals(optString2)) {
                            str = "\"中国联通\"想访问您的相机";
                        } else {
                            "all".equals(optString2);
                        }
                    }
                    new RtcPermissDialog.Builder(this.activityContext).setTitle(str).setContent("为了给您带来更好的服务，需要获取您的相机权限、音频权限，用于视频通话功能，对于您授权的信息我们竭尽提供安全保护").setButtonCancel("不允许", new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebAlertJsPlugin.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("msg", "拒绝了权限");
                                WebAlertJsPlugin.this.callbackFail(jSONObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    }).setButtonConfirm("好", new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebAlertJsPlugin.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("msg", "同意去开启权限");
                                WebAlertJsPlugin.this.callbackSuccess(jSONObject);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    }).create().show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void callbackStatus(String str, boolean z) {
        if (z) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("msg", str);
                callbackSuccess(jSONObject);
                return;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("msg", "调用失败");
            callbackFail(jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AlertJS {
        public AlertJS() {
            WebAlertJsPlugin.this.androidMSJSBridgeHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebAlertJsPlugin.AlertJS.1
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                }
            };
        }

        @JavascriptInterface
        public void exec(final String str) {
            WebAlertJsPlugin.this.androidMSJSBridgeHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebAlertJsPlugin$AlertJS$mVIin5kUMFY_ioWajNCPpBoQ82Y
                @Override // java.lang.Runnable
                public final void run() {
                    WebAlertJsPlugin.AlertJS.lambda$exec$0(WebAlertJsPlugin.AlertJS.this, str);
                }
            });
        }

        public static /* synthetic */ void lambda$exec$0(AlertJS alertJS, String str) {
            try {
                String string = new JSONObject(str).getString("action");
                ARouter aRouter = ARouter.getInstance();
                BaseJSPlugin baseJSPlugin = (BaseJSPlugin) aRouter.build("/MsJSPlugin/" + string).navigation(WebAlertJsPlugin.this.activityContext);
                MsLogUtil.m7979d("MsJSPlugin", "JS插件=" + baseJSPlugin.getClass().getSimpleName() + " 单例模式=" + baseJSPlugin.SingletonPattern);
                if (baseJSPlugin.SingletonPattern.booleanValue()) {
                    baseJSPlugin.exec(WebAlertJsPlugin.this.activityContext, WebAlertJsPlugin.this.webFragment, WebAlertJsPlugin.this.f18589wv, str);
                } else {
                    ((BaseJSPlugin) Class.forName(baseJSPlugin.getClass().getName()).newInstance()).exec(WebAlertJsPlugin.this.activityContext, WebAlertJsPlugin.this.webFragment, WebAlertJsPlugin.this.f18589wv, str);
                }
            } catch (Exception e) {
                MsLogUtil.m7978e("BaseWebFragment>>AndroidMsJSBridge>>exec>>>>Exception: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void showWebView(String str) {
        try {
            UIUtils.logD("webAlert", "url=" + str);
            if (TextUtils.isEmpty(str)) {
                UIUtils.toast("url异常");
                return;
            }
            this.isError = false;
            this.webView = new PBWebView(this.activityContext);
            this.webView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
            this.webView.requestFocus();
            this.webView.setBackgroundColor(0);
            this.webView.getSettings().setCacheMode(2);
            this.webView.addJavascriptInterface(new AlertJS(), "AndroidMsJSBridge");
            this.webView.setStatusListener(new WebViewStatusListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.WebAlertJsPlugin.3
                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onShouldOverrideUrlLoading(String str2) {
                    return false;
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onRecevieError(int i, String str2, String str3) {
                    UIUtils.logD("webAlert", "失败onRecevieError=" + str2);
                    WebAlertJsPlugin.this.isError = true;
                    WebAlertJsPlugin.this.callbackFail(str2);
                    return true;
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                    UIUtils.logD("webAlert", "失败onReceivedHttpError=" + webResourceResponse.getReasonPhrase());
                    WebAlertJsPlugin.this.isError = true;
                    WebAlertJsPlugin webAlertJsPlugin = WebAlertJsPlugin.this;
                    webAlertJsPlugin.callbackFail(webResourceResponse.getStatusCode() + ":" + webResourceResponse.getReasonPhrase());
                    return true;
                }

                @Override // com.sinovatech.unicom.basic.webview.WebViewStatusListener
                public boolean onPageFinished() {
                    UIUtils.logD("webAlert", "onPageFinished()");
                    if (WebAlertJsPlugin.this.isError) {
                        return false;
                    }
                    UIUtils.logD("webAlert", "onPageFinished()成功");
                    if (RtcHelper.getInstance().getRtcStatus()) {
                        RtcHelper.getInstance().addAlertView(WebAlertJsPlugin.this.webView);
                    } else if (RtcVoiceHelper.getInstance().getRtcStatus()) {
                        RtcVoiceHelper.getInstance().addAlertView(WebAlertJsPlugin.this.webView);
                    } else {
                        ((ViewGroup) WebAlertJsPlugin.this.f18589wv.getParent()).addView(WebAlertJsPlugin.this.webView);
                    }
                    WebAlertJsPlugin.this.callbackSuccess("页面加载成功");
                    return false;
                }
            });
            this.webView.loadURL(str);
            this.webView.setBackgroundColor(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void hiddenWebView(boolean z, String str) {
        if (RtcHelper.getInstance().getRtcStatus()) {
            RtcHelper.getInstance().hiddenAlertView();
        } else if (RtcVoiceHelper.getInstance().getRtcStatus()) {
            RtcVoiceHelper.getInstance().hiddenAlertView();
        } else {
            ((ViewGroup) this.f18589wv.getParent()).removeView(this.webView);
        }
        if (!z || TextUtils.isEmpty(str)) {
            return;
        }
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setTitle("");
        webParamsEntity.setBackMode("1");
        webParamsEntity.setRequestType("get");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setYule(false);
        Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        new AvoidOnResult(this.activityContext).startForResult(intent, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.webrtc.-$$Lambda$WebAlertJsPlugin$XWLIHcr3HwvehNIL2cC4nXlh-5c
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public final void onActivityResult(int i, Intent intent2) {
                WebAlertJsPlugin.lambda$hiddenWebView$0(i, intent2);
            }
        });
    }
}
