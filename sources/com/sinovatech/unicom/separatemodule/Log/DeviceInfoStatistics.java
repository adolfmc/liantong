package com.sinovatech.unicom.separatemodule.Log;

import android.content.Context;
import android.os.Build;
import android.support.p083v4.app.ActivityCompat;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.MemoryTools;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import github.nisrulz.easydeviceinfo.base.EasySimMod;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeviceInfoStatistics {
    public static String getDeviceInfo(Context context, String str) {
        return "";
    }

    public static void uploadDeviceInfoStatistics(Context context, String str) {
    }

    private static String uploadTianYuanInfo(Context context, String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        if (TextUtils.isEmpty(str3)) {
            str3 = "0";
        }
        try {
            UserManager userManager = UserManager.getInstance();
            jSONObject.put("titleName", str);
            jSONObject.put("mobile", str3);
            jSONObject.put("provinceCode", userManager.getCurrentProvinceCode());
            jSONObject.put("cityCode", userManager.getUserAreaid());
            jSONObject.put("netType", userManager.getMenuNetType());
            jSONObject.put("loginTime", System.currentTimeMillis() + "");
            jSONObject.put("loginType", userManager.getLoginType());
            jSONObject.put("loginState", str2);
            jSONObject.put("deviceId", DeviceHelper.getDeviceID(true));
            jSONObject.put("appid", userManager.getLoginAppId());
            jSONObject.put("lat", App.getSharePreferenceUtil().getString("lat"));
            jSONObject.put("lon", App.getSharePreferenceUtil().getString("long"));
            jSONObject.put("deviceBrand", DeviceHelper.getDeviceBrand());
            jSONObject.put("deviceModel", DeviceHelper.getDeviceModel());
            jSONObject.put("os", "android");
            jSONObject.put("osVersion", DeviceHelper.getDeviceOSVersion());
            jSONObject.put("screen", UIUtils.getFullScreenHeight(context) + "*" + UIUtils.getScreenWidth(context));
            jSONObject.put("memorySpace", MemoryTools.getTotalMemory(context));
            jSONObject.put("phoneSpace", MemoryTools.getInternalToatalSpace(context));
            jSONObject.put("version", context.getString(2131886969));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    public static String uploadTianYuan(String str, String str2, String str3, String str4) {
        StatisticsUploadUtils.uploadRealTime2BeiDong(App.getInstance(), "sfkdl0001", "风控登录埋点", str, "", "风控登录埋点", "", "", uploadTianYuanInfo(App.getInstance(), str2, str3, str4));
        return "";
    }

    public static String uploadTianYuan(String str, String str2, String str3) {
        StatisticsUploadUtils.uploadRealTime2BeiDong(App.getInstance(), "sfkdl0001", "风控登录埋点", str, "", "风控登录埋点", "", "", uploadTianYuanInfo(App.getInstance(), str2, str3, UserManager.getInstance().getCurrentPhoneNumber()));
        return "";
    }

    public static boolean isHasSim(Context context) {
        int i;
        EasySimMod easySimMod;
        try {
            easySimMod = new EasySimMod(context);
        } catch (Exception e) {
            e.printStackTrace();
            i = 1;
        }
        if (Build.VERSION.SDK_INT >= 22 && ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0) {
            i = easySimMod.getActiveMultiSimInfo().size();
            return i != 0;
        }
        return true;
    }
}
