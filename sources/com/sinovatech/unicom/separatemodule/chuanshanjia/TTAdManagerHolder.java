package com.sinovatech.unicom.separatemodule.chuanshanjia;

import android.content.Context;
import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTCustomController;
import com.bytedance.sdk.openadsdk.TTLocation;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TTAdManagerHolder {
    private static final String TAG = "TTAdManagerHolder";
    private static boolean sInit;

    public static TTAdManager get() {
        return TTAdSdk.getAdManager();
    }

    public static void init(Context context) {
        doInit(context);
    }

    private static void doInit(Context context) {
        if (sInit) {
            return;
        }
        TTAdSdk.init(context, buildConfig(context), new TTAdSdk.InitCallback() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.TTAdManagerHolder.1
            @Override // com.bytedance.sdk.openadsdk.TTAdSdk.InitCallback
            public void success() {
                MsLogUtil.m7979d("TTAdManagerHolder", "success: " + TTAdSdk.isInitSuccess());
            }

            @Override // com.bytedance.sdk.openadsdk.TTAdSdk.InitCallback
            public void fail(int i, String str) {
                MsLogUtil.m7979d("TTAdManagerHolder", "fail:  code = " + i + " msg = " + str);
            }
        });
        sInit = true;
    }

    private static TTAdConfig buildConfig(Context context) {
        return new TTAdConfig.Builder().appId(ParamsID.AppId).useTextureView(true).appName("中国联通APP").titleBarTheme(1).allowShowNotify(true).allowShowPageWhenScreenLock(true).debug(false).directDownloadNetworkType(new int[0]).supportMultiProcess(true).needClearTaskReset(new String[0]).customController(new TTCustomController() { // from class: com.sinovatech.unicom.separatemodule.chuanshanjia.TTAdManagerHolder.2
            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public boolean alist() {
                return false;
            }

            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public boolean isCanUseLocation() {
                return false;
            }

            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public boolean isCanUsePhoneState() {
                return false;
            }

            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public boolean isCanUseWifiState() {
                return false;
            }

            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public boolean isCanUseWriteExternal() {
                return true;
            }

            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public TTLocation getTTLocation() {
                try {
                    return new TTLocation(Double.parseDouble(App.getSharePreferenceUtil().getString("lat")), Double.parseDouble(App.getSharePreferenceUtil().getString("long")));
                } catch (Exception unused) {
                    return null;
                }
            }

            @Override // com.bytedance.sdk.openadsdk.TTCustomController
            public String getDevImei() {
                return DeviceHelper.getDeviceID(true);
            }
        }).build();
    }
}
