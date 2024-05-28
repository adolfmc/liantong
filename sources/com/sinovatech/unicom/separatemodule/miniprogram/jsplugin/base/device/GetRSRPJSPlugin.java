package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin;
import java.util.function.Consumer;
import org.json.JSONObject;

@Route(path = "/MsJSPlugin/getRSRP")
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GetRSRPJSPlugin extends BaseJSPlugin {
    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
    }

    @Override // com.sinovatech.unicom.separatemodule.miniprogram.web.BaseJSPlugin
    public void onExec() throws Exception {
        DeviceHelper.getMobileNetworkSignal(this.activityContext, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.device.-$$Lambda$GetRSRPJSPlugin$FA7AzZ03c24hrB4dJmIuMfhtWhE
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GetRSRPJSPlugin.lambda$onExec$0(GetRSRPJSPlugin.this, (Integer) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$onExec$0(GetRSRPJSPlugin getRSRPJSPlugin, Integer num) {
        try {
            if (num.intValue() == 12) {
                getRSRPJSPlugin.callbackFail("12", "没有安装sim卡");
            } else if (num.intValue() == 11) {
                getRSRPJSPlugin.callbackFail("11", "获取SIM卡服务失败，手机不支持");
            } else if (num.intValue() == 10) {
                getRSRPJSPlugin.callbackFail("10", "程序异常");
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("netWay", getRSRPJSPlugin.getCellularType());
                jSONObject.put("dbm", num);
                getRSRPJSPlugin.callbackSuccess(jSONObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
            getRSRPJSPlugin.callbackFail("10", "程序异常：" + e.getMessage());
        }
    }

    private String getCellularType() {
        if (SystemServiceUtils.isWifiActive()) {
            return "Wifi";
        }
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
