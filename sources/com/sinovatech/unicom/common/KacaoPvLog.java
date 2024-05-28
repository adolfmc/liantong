package com.sinovatech.unicom.common;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.sinovatech.unicom.basic.myinterface.MyLocationInterface;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class KacaoPvLog {
    public static boolean isSlotOneEnabled(Context context, int i) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getSimState(i) == 5;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getSlotOneCarrier(Context context) {
        try {
            String simOperatorName = ((TelephonyManager) context.getSystemService("phone")).getSimOperatorName();
            return !TextUtils.isEmpty(simOperatorName) ? simOperatorName.replace(" ", "") : simOperatorName;
        } catch (Exception e) {
            e.printStackTrace();
            return "空";
        }
    }

    public static void log(final Context context, final String str) {
        PackageManager packageManager = context.getPackageManager();
        boolean z = packageManager.checkPermission("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName()) == 0;
        boolean z2 = packageManager.checkPermission("android.permission.ACCESS_FINE_LOCATION", context.getPackageName()) == 0;
        if (DeviceHelper.isLocationEnabled() && (z || z2)) {
            ManagerLocation.getInstance().startLocation((Activity) context, "卡槽日志", new MyLocationInterface.CallBack() { // from class: com.sinovatech.unicom.common.KacaoPvLog.1
                @Override // com.sinovatech.unicom.basic.myinterface.MyLocationInterface.CallBack
                public void Success(ManagerLocation.LocationEntity locationEntity) {
                    if (!locationEntity.isLocationSuccess()) {
                        KacaoPvLog.initLog(context, str, 0.0d, 0.0d);
                        return;
                    }
                    KacaoPvLog.initLog(context, str, locationEntity.getBdLocation().getLongitude(), locationEntity.getBdLocation().getLatitude());
                }
            });
        } else {
            initLog(context, str, 0.0d, 0.0d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void initLog(Context context, String str, double d, double d2) {
        String connectWifiSsid = DeviceHelper.getConnectWifiSsid(context);
        if (TextUtils.isEmpty(connectWifiSsid)) {
            connectWifiSsid = "-";
        }
        String deviceBrand = DeviceHelper.getDeviceBrand();
        if (TextUtils.isEmpty(deviceBrand)) {
            deviceBrand = "-";
        }
        String deviceModel = DeviceHelper.getDeviceModel();
        if (TextUtils.isEmpty(deviceModel)) {
            deviceModel = "-";
        }
        String str2 = d + "," + d2 + "#" + connectWifiSsid + "#" + deviceBrand + "#" + deviceModel;
        String str3 = "3";
        String str4 = "空";
        if (isSlotOneEnabled(context, 0)) {
            str3 = "1";
            str4 = getSlotOneCarrier(context);
        }
        PvCurrencyLogUtils.pvLogKacaoBG("S2ndpage1258", str, str2, str3 + "," + (isSlotOneEnabled(context, 1) ? "2" : "4"), str4 + ",空");
    }
}
