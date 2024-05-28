package com.sinovatech.unicom.separatemodule.miniprogram.web;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import com.google.gson.JsonObject;
import com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpTheme;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaseWebFragment extends BaseFragment {
    public static String currentPublishType = "2";
    public Activity activityContext;
    public CumpEntity currentCumpAppEntity;
    public JSONObject currentEdopShareConfig;
    private AndroidMsJSBridge jsBridge;
    public Map<String, Object> navigateParams;
    private WebNetwrokCallback netwrokCallback;
    private final String TAG = getClass().getSimpleName();
    public String currentCumpAppId = "";
    public String currentCumpAppName = "";
    public String currentURLForPlugin = "";
    public CumpTheme currentCumpTheme = new CumpTheme();
    public boolean currentCumpDebugMode = false;
    public String currentH5Id = "";
    public boolean ignoreLifeEventToJS = false;
    private Map<String, LifeListener> listeners = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LifeListener {
        void onDestory();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (Activity) context;
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 24) {
            registerNetworkCallback();
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        super.onResume();
        AndroidMsJSBridge androidMsJSBridge = this.jsBridge;
        if (androidMsJSBridge != null) {
            if (!this.ignoreLifeEventToJS) {
                androidMsJSBridge.postEventToJS(MsJSEvent.onPageShow, new JsonObject());
            }
            this.ignoreLifeEventToJS = false;
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        super.onPause();
        AndroidMsJSBridge androidMsJSBridge = this.jsBridge;
        if (androidMsJSBridge != null) {
            if (!this.ignoreLifeEventToJS) {
                androidMsJSBridge.postEventToJS(MsJSEvent.onPageHidden, new JsonObject());
            }
            this.ignoreLifeEventToJS = false;
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        for (Map.Entry<String, LifeListener> entry : this.listeners.entrySet()) {
            entry.getValue().onDestory();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            unRegisterNetworkCallback();
        }
    }

    public void addLifeListener(String str, LifeListener lifeListener) {
        Map<String, LifeListener> map = this.listeners;
        map.put(str + UUID.randomUUID().toString(), lifeListener);
    }

    public void registerJSPluginService(WebView webView) {
        this.jsBridge = new AndroidMsJSBridge(this.activityContext, webView, this);
        webView.addJavascriptInterface(this.jsBridge, "AndroidMsJSBridge");
    }

    private void registerNetworkCallback() {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                MsLogUtil.m7980d("网络状态变化监听 注册一个监听器>>>");
                ConnectivityManager connectivityManager = (ConnectivityManager) this.activityContext.getSystemService("connectivity");
                if (connectivityManager != null) {
                    this.netwrokCallback = new WebNetwrokCallback();
                    connectivityManager.registerDefaultNetworkCallback(this.netwrokCallback);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("注册网络状态变化监听器 " + e.getMessage());
        }
    }

    private void unRegisterNetworkCallback() {
        ConnectivityManager connectivityManager;
        try {
            if (Build.VERSION.SDK_INT < 24 || (connectivityManager = (ConnectivityManager) this.activityContext.getSystemService("connectivity")) == null || this.netwrokCallback == null) {
                return;
            }
            connectivityManager.unregisterNetworkCallback(this.netwrokCallback);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7978e("取消注册网络状态变化监听器 " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class WebNetwrokCallback extends ConnectivityManager.NetworkCallback {
        private String lastestStatus = "";
        private Handler handler = new Handler();
        private Runnable runnable = new Runnable() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment.WebNetwrokCallback.1
            @Override // java.lang.Runnable
            public void run() {
                MsLogUtil.m7980d("网络状态变化监听 发送事件通知给前端>>>");
                if (BaseWebFragment.this.jsBridge != null) {
                    BaseWebFragment.this.jsBridge.postEventToJS(MsJSEvent.onNetworkStatusChanged, new JsonObject());
                }
            }
        };

        WebNetwrokCallback() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            super.onAvailable(network);
            MsLogUtil.m7980d("网络状态变化监听(onAvailable)");
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            super.onLost(network);
            MsLogUtil.m7980d("网络状态变化监听(onLost)");
            if (this.lastestStatus.equals("onLost")) {
                return;
            }
            this.lastestStatus = "onLost";
            this.handler.removeCallbacks(this.runnable);
            this.handler.postDelayed(this.runnable, 2000L);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities);
            MsLogUtil.m7980d("网络状态变化监听(onCapabilitiesChanged)：" + networkCapabilities.toString());
            String nETType = DeviceHelper.getNETType(BaseWebFragment.this.activityContext);
            if (this.lastestStatus.equals(nETType)) {
                return;
            }
            this.lastestStatus = nETType;
            this.handler.removeCallbacks(this.runnable);
            this.handler.postDelayed(this.runnable, 2000L);
        }
    }

    public void postUserCaptureScreenEvent() {
        AndroidMsJSBridge androidMsJSBridge = this.jsBridge;
        if (androidMsJSBridge != null) {
            androidMsJSBridge.postEventToJS(MsJSEvent.onUserCaptureScreen, new JsonObject());
        }
    }

    public void postEventToJS(String str, Object obj) {
        AndroidMsJSBridge androidMsJSBridge = this.jsBridge;
        if (androidMsJSBridge != null) {
            androidMsJSBridge.postEventToJS(str, obj);
        }
    }
}
