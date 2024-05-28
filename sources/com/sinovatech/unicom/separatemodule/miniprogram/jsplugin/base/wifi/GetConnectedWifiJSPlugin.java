package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getConnectedWifi")
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetConnectedWifiJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("onExec ");
        JSONObject jSONObject = this.originConfigJO;
        sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        MsLogUtil.m7980d(sb.toString());
        new RxPermissions(this.activityContext).request("android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION").subscribe(new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.wifi.GetConnectedWifiJSPlugin.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    if (((ConnectivityManager) GetConnectedWifiJSPlugin.this.activityContext.getSystemService("connectivity")).getNetworkInfo(1).isConnected()) {
                        if (DeviceHelper.isLocationEnabled()) {
                            WifiInfo connectionInfo = ((WifiManager) GetConnectedWifiJSPlugin.this.activityContext.getApplicationContext().getSystemService("wifi")).getConnectionInfo();
                            MsLogUtil.m7979d("GetConnectedWifiJSPlugin", connectionInfo.toString());
                            String replaceAll = connectionInfo.getSSID().replaceAll("\"", "");
                            String bssid = connectionInfo.getBSSID();
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put("SSID", replaceAll);
                            jSONObject2.put("BSSID", bssid);
                            GetConnectedWifiJSPlugin.this.callbackSuccess(jSONObject2);
                            return;
                        }
                        GetConnectedWifiJSPlugin.this.callbackFail("13", "用户没有开启手机位置信息开关");
                        return;
                    }
                    GetConnectedWifiJSPlugin.this.callbackFail("11", "未连接wifi网络");
                    return;
                }
                GetConnectedWifiJSPlugin.this.callbackFail("12", "没有开启定位权限");
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.wifi.GetConnectedWifiJSPlugin.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                GetConnectedWifiJSPlugin getConnectedWifiJSPlugin = GetConnectedWifiJSPlugin.this;
                getConnectedWifiJSPlugin.callbackFail("10", "程序错误:" + th.getMessage());
            }
        });
    }
}
