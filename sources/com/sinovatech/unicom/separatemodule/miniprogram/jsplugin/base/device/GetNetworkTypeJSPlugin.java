package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.List;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getNetworkType")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetNetworkTypeJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public String onExecSync() throws Exception {
        return callbackSuccessSync(getNetworkType());
    }

    private JSONObject getNetworkType() throws Exception {
        String str;
        String str2 = "";
        int i = -1;
        if (!SystemServiceUtils.netIsAvailable()) {
            str = "Unconnected";
        } else if (SystemServiceUtils.isWifiActive()) {
            str = "Wifi";
        } else {
            int carrierType = getCarrierType();
            if (carrierType == -1) {
                str = "";
                i = carrierType;
                str2 = "";
            } else {
                str = "Cellular";
                i = carrierType;
                str2 = getCellularType();
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("networkType", str);
        jSONObject.put("cellularType", str2);
        jSONObject.put("mnc", i);
        return jSONObject;
    }

    private int getCarrierType() {
        int i = -1;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.activityContext).getActiveSubscriptionInfoList();
                SubscriptionManager.from(this.activityContext);
                int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
                for (SubscriptionInfo subscriptionInfo : activeSubscriptionInfoList) {
                    if (subscriptionInfo.getSubscriptionId() == defaultDataSubscriptionId) {
                        i = subscriptionInfo.getMnc();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    private String getCellularType() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.activityContext.getSystemService("phone");
            if (telephonyManager == null || telephonyManager.getSimState() != 5) {
                return "Unknown";
            }
            int networkType = telephonyManager.getNetworkType();
            if (networkType != 15) {
                if (networkType != 20) {
                    switch (networkType) {
                        case 0:
                            return "Unknown";
                        case 1:
                        case 2:
                        case 4:
                        case 7:
                        case 11:
                            return "2G";
                        case 3:
                        case 5:
                        case 6:
                        case 8:
                        case 9:
                        case 10:
                        case 12:
                            break;
                        case 13:
                            return "4G";
                        default:
                            return "Unknown";
                    }
                } else {
                    return "5G";
                }
            }
            return "3G";
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
}
