package com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.view.animation.BounceInterpolator;
import android.webkit.WebView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.PermissionUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.IFloatWindow;
import com.yhao.floatwindow.IFloatWindowImpl;
import com.yhao.floatwindow.PermissionListener;
import java.net.URLEncoder;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/freeWebWindow")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FloatwindowJsPlugin extends BaseJSPlugin {
    private static final String TAG = "Floatwindow";
    private Handler androidMSJSBridgeHandler;

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        try {
            String optString = this.parameterJO.optString("type");
            JSONObject optJSONObject = this.parameterJO.optJSONObject("params");
            IFloatWindow iFloatWindow = FloatWindow.get();
            char c = 65535;
            switch (optString.hashCode()) {
                case -806066213:
                    if (optString.equals("fullScreen")) {
                        c = 6;
                        break;
                    }
                    break;
                case -763348009:
                    if (optString.equals("smallWindow")) {
                        c = 5;
                        break;
                    }
                    break;
                case 3417674:
                    if (optString.equals("open")) {
                        c = 3;
                        break;
                    }
                    break;
                case 94756344:
                    if (optString.equals("close")) {
                        c = 4;
                        break;
                    }
                    break;
                case 94924937:
                    if (optString.equals("creat")) {
                        c = 2;
                        break;
                    }
                    break;
                case 686218487:
                    if (optString.equals("checkPermission")) {
                        c = 0;
                        break;
                    }
                    break;
                case 746581438:
                    if (optString.equals("requestPermission")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    try {
                        if (PermissionUtils.isGrantedDrawOverlays()) {
                            callBack("hasWindowPermission");
                        } else {
                            callBack("noWindowPermission");
                        }
                        return;
                    } catch (Exception e) {
                        callBack("checkPermissionFail");
                        MsLogUtil.m7977e(TAG, e.getMessage());
                        return;
                    }
                case 1:
                    try {
                        PermissionUtils.requestDrawOverlays(new PermissionUtils.SimpleCallback() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin.FloatwindowJsPlugin.1
                            @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
                            public void onGranted() {
                                FloatwindowJsPlugin.this.callBack("requestWindowPermissionSuccess");
                                MsLogUtil.m7979d(FloatwindowJsPlugin.TAG, "权限申请成功");
                            }

                            @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
                            public void onDenied() {
                                FloatwindowJsPlugin.this.callBack("requestWindowPermissionFail");
                                MsLogUtil.m7979d(FloatwindowJsPlugin.TAG, "权限申请失败");
                            }
                        });
                        return;
                    } catch (Exception e2) {
                        callBack("windowPermissionFail");
                        MsLogUtil.m7977e(TAG, e2.getMessage());
                        return;
                    }
                case 2:
                    if (optJSONObject != null) {
                        try {
                            final String optString2 = optJSONObject.optString("url");
                            if (TextUtils.isEmpty(optString2)) {
                                return;
                            }
                            PermissionUtils.requestDrawOverlays(new PermissionUtils.SimpleCallback() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin.FloatwindowJsPlugin.2
                                @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
                                public void onGranted() {
                                    FloatwindowJsPlugin floatwindowJsPlugin = FloatwindowJsPlugin.this;
                                    floatwindowJsPlugin.createWindow((AppCompatActivity) floatwindowJsPlugin.activityContext, optString2);
                                    MsLogUtil.m7979d(FloatwindowJsPlugin.TAG, "权限申请成功");
                                }

                                @Override // com.blankj.utilcode.util.PermissionUtils.SimpleCallback
                                public void onDenied() {
                                    FloatwindowJsPlugin.this.callBack("noWindowPermission");
                                    MsLogUtil.m7979d(FloatwindowJsPlugin.TAG, "权限申请失败");
                                }
                            });
                            return;
                        } catch (Exception e3) {
                            callBack("createFail");
                            MsLogUtil.m7977e(TAG, e3.getMessage());
                            return;
                        }
                    }
                    return;
                case 3:
                    try {
                        if (PermissionUtils.isGrantedDrawOverlays()) {
                            showWindow();
                            callBack("opened");
                        } else {
                            callBack("noWindowPermission");
                        }
                        return;
                    } catch (Exception e4) {
                        callBack("noWindowPermission");
                        MsLogUtil.m7978e(e4.getMessage());
                        return;
                    }
                case 4:
                    try {
                        FloatWindowWebVIew.getInstance((AppCompatActivity) this.activityContext).destory();
                        callBack("closed");
                        FloatWindow.destroy();
                        return;
                    } catch (Exception e5) {
                        callBack("closedFail");
                        MsLogUtil.m7977e(TAG, "销毁弹窗异常:" + e5.getMessage());
                        return;
                    }
                case 5:
                    if (optJSONObject == null || iFloatWindow == null) {
                        return;
                    }
                    try {
                        String optString3 = optJSONObject.optString("topMargin");
                        String optString4 = optJSONObject.optString("width");
                        String optString5 = optJSONObject.optString("height");
                        String optString6 = optJSONObject.optString("onLeft");
                        if (TextUtils.isEmpty(optString3)) {
                            optString3 = "50";
                        }
                        if (TextUtils.isEmpty(optString4)) {
                            optString4 = "150";
                        }
                        if (TextUtils.isEmpty(optString5)) {
                            optString5 = "200";
                        }
                        int i = 10;
                        try {
                            i = Integer.parseInt(optString3);
                        } catch (Exception e6) {
                            MsLogUtil.m7977e(TAG, "解析窗口距离顶部数据异常:" + e6.getMessage());
                        }
                        int i2 = 150;
                        try {
                            i2 = Integer.parseInt(optString4);
                        } catch (Exception e7) {
                            MsLogUtil.m7977e(TAG, "解析窗口宽度数据异常:" + e7.getMessage());
                        }
                        int i3 = 200;
                        try {
                            i3 = Integer.parseInt(optString5);
                        } catch (Exception e8) {
                            MsLogUtil.m7977e(TAG, "解析窗口距离顶部数据异常:" + e8.getMessage());
                        }
                        FloatWindow.get().setDrag(true);
                        float f = i2;
                        FloatWindow.get().updateSize(UIUtils.dip2px(f), UIUtils.dip2px(i3));
                        if (TextUtils.equals("true", optString6)) {
                            FloatWindow.get().updateX(0);
                        } else {
                            FloatWindow.get().updateX(UIUtils.getScreenWidth(this.activityContext) - UIUtils.dip2px(f));
                        }
                        FloatWindow.get().updateY(UIUtils.dip2px(i));
                        callBack("smalled");
                        return;
                    } catch (Exception e9) {
                        callBack("smalledFail");
                        MsLogUtil.m7977e(TAG, "缩小弹窗异常:" + e9.getMessage());
                        return;
                    }
                case 6:
                    if (iFloatWindow != null) {
                        try {
                            FloatWindow.get().setDrag(false);
                            FloatWindow.get().updateSize(UIUtils.getScreenWidth(this.activityContext), UIUtils.getFullScreenHeight(this.activityContext));
                            FloatWindow.get().updateX(0);
                            FloatWindow.get().updateY(0);
                            callBack("fulled");
                            return;
                        } catch (Exception e10) {
                            callBack("fulledFail");
                            MsLogUtil.m7979d(TAG, "全屏异常:" + e10.getMessage());
                            return;
                        }
                    }
                    return;
                default:
                    callBack("undefinedAction");
                    return;
            }
        } catch (Exception e11) {
            callBack("floatWindowFail");
            MsLogUtil.m7977e(TAG, "浮窗解析数据异常:" + e11.getMessage());
        }
        callBack("floatWindowFail");
        MsLogUtil.m7977e(TAG, "浮窗解析数据异常:" + e11.getMessage());
    }

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.androidMSJSBridgeHandler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin.FloatwindowJsPlugin.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createWindow(AppCompatActivity appCompatActivity, String str) {
        try {
            final FloatWindowWebVIew floatWindowWebVIew = FloatWindowWebVIew.getInstance(appCompatActivity);
            if (floatWindowWebVIew instanceof Object) {
                NBSWebLoadInstrument.loadUrl(floatWindowWebVIew, str);
            } else {
                floatWindowWebVIew.loadUrl(str);
            }
            floatWindowWebVIew.setListener(new FloatWindowWebVIew.WebLoadListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin.FloatwindowJsPlugin.4
                @Override // com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew.WebLoadListener
                public void onSuccess() {
                    FloatwindowJsPlugin.this.callBack("created");
                    try {
                        FloatWindow.destroy();
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                    FloatWindow.with(App.getInstance()).setView(floatWindowWebVIew.getWv()).setWidth(1).setHeight(1).setX(0, 0.0f).setY(1, 0.0f).setMoveType(3, 0, 0).setMoveStyle(1L, new BounceInterpolator()).setViewStateListener(null).setPermissionListener(new PermissionListener() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin.FloatwindowJsPlugin.4.1
                        @Override // com.yhao.floatwindow.PermissionListener
                        public void onSuccess() {
                        }

                        @Override // com.yhao.floatwindow.PermissionListener
                        public void onFail() {
                            IFloatWindowImpl.isShow = false;
                            try {
                                FloatWindow.destroy();
                            } catch (Exception unused) {
                            }
                        }
                    }).setDesktopShow(true).build();
                    FloatWindow.get().setDrag(false);
                    FloatWindow.get().hide();
                }

                @Override // com.sinovatech.unicom.separatemodule.fuchuangs.view.FloatWindowWebVIew.WebLoadListener
                public void onFail() {
                    FloatwindowJsPlugin.this.callBack("createFail");
                    try {
                        floatWindowWebVIew.destory();
                        FloatWindow.destroy();
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            callBack("createFail");
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    private void showWindow() {
        try {
            final IFloatWindow iFloatWindow = FloatWindow.get();
            if (iFloatWindow != null) {
                if (!iFloatWindow.isShowing()) {
                    IFloatWindowImpl.once = true;
                }
                iFloatWindow.setDrag(false);
                iFloatWindow.show();
                iFloatWindow.updateSize(UIUtils.getScreenWidth(this.activityContext), UIUtils.getFullScreenHeight(this.activityContext));
                FloatWindow.get().updateX(0);
                FloatWindow.get().updateY(0);
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.fuchuangs.jsplugin.FloatwindowJsPlugin.5
                    @Override // java.lang.Runnable
                    public void run() {
                        iFloatWindow.getView().invalidate();
                    }
                }, 100L);
            }
        } catch (Exception e) {
            callBack("showFail");
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void callBack(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", str);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("action", "freeWebWindow");
            jSONObject2.put("parameter", jSONObject);
            String encodeToString = Base64.encodeToString(URLEncoder.encode(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2), "utf-8").getBytes("utf-8"), 0);
            WebView webView = this.f18589wv;
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
