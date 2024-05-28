package com.sinovatech.unicom.common;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.support.p083v4.app.ActivityCompat;
import android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthNr;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.blankj.utilcode.util.AppUtils;
import com.p284qw.soul.permission.SoulPermission;
import com.p284qw.soul.permission.bean.Permission;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.webrtc.ContextUtils;
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.function.Consumer;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeviceHelper {
    private static final String TAG = "DeviceHelper";
    private static final String getMobileNetworkSignal = "getMobileNetworkSignal";
    private static SharePreferenceUtil preference = App.getSharePreferenceUtil();

    public static String getDeviceBrand() {
        boolean z = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = App.getSharePreferenceUtil().getString("DeviceHelper-getDeviceBrand");
        try {
            Permission checkSinglePermission = SoulPermission.getInstance().checkSinglePermission("android.permission.READ_PHONE_STATE");
            if (TextUtils.isEmpty(string) && checkSinglePermission != null && checkSinglePermission.isGranted() && z) {
                try {
                    string = Build.MANUFACTURER;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(string)) {
                    string = "unkown";
                }
                App.getSharePreferenceUtil().putString("DeviceHelper-getDeviceBrand", string);
            }
        } catch (Exception e2) {
            MsLogUtil.m7978e(e2.getMessage());
        }
        MsLogUtil.m7980d("DeviceHelper-getDeviceBrand 返回结果：" + string);
        return string;
    }

    public static String getDeviceBranD() {
        boolean z = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = App.getSharePreferenceUtil().getString("DeviceHelper-getDeviceBranD");
        if (TextUtils.isEmpty(string) && SoulPermission.getInstance().checkSinglePermission("android.permission.READ_PHONE_STATE").isGranted() && z) {
            try {
                string = Build.BRAND;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(string)) {
                string = "unkown";
            }
            App.getSharePreferenceUtil().putString("DeviceHelper-getDeviceBranD", string);
        }
        MsLogUtil.m7980d("DeviceHelper-getDeviceBranD 返回结果：" + string);
        return string;
    }

    public static String getDeviceModel() {
        boolean z = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = App.getSharePreferenceUtil().getString("DeviceHelper-getDeviceModel");
        Permission checkSinglePermission = SoulPermission.getInstance().checkSinglePermission("android.permission.READ_PHONE_STATE");
        if (TextUtils.isEmpty(string) && checkSinglePermission != null && checkSinglePermission.isGranted() && z) {
            try {
                string = Build.MODEL;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(string)) {
                string = "unkown";
            }
            App.getSharePreferenceUtil().putString("DeviceHelper-getDeviceModel", string);
        }
        MsLogUtil.m7980d("DeviceHelper-getDeviceModel 返回结果：" + string);
        return string;
    }

    public static String getAndroidId() {
        boolean z = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = App.getSharePreferenceUtil().getString("DeviceHelper-androidId");
        if (TextUtils.isEmpty(string) && z) {
            try {
                string = Settings.Secure.getString(App.getInstance().getContentResolver(), "android_id");
                MsLogUtil.m7979d("DeviceHelper", "androidId = " + string);
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7977e("DeviceHelper", "getAndroidId 异常" + e.getMessage());
            }
            if (TextUtils.isEmpty(string)) {
                string = "unkown";
            }
            App.getSharePreferenceUtil().putString("DeviceHelper-androidId", string);
        }
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        MsLogUtil.m7980d("DeviceHelper-getAndroidId 返回结果：" + string);
        return string;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0041, code lost:
        r0 = r2.getHardwareAddress();
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0045, code lost:
        if (r0 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0047, code lost:
        r1 = "unkown";
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x004b, code lost:
        r2 = new java.lang.StringBuilder();
        r3 = r0.length;
        r5 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0054, code lost:
        if (r5 >= r3) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
        r2.append(java.lang.String.format("%02X:", java.lang.Byte.valueOf(r0[r5])));
        r5 = r5 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0070, code lost:
        if (r2.length() <= 0) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0072, code lost:
        r2.deleteCharAt(r2.length() - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x007a, code lost:
        r1 = r2.toString();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getWifiMac() {
        /*
            com.sinovatech.unicom.common.SharePreferenceUtil r0 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()
            java.lang.String r1 = "hasShowYinsi"
            boolean r0 = r0.getBoolean(r1)
            com.sinovatech.unicom.common.SharePreferenceUtil r1 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()
            java.lang.String r2 = "DeviceHelper-getWifiMac"
            java.lang.String r1 = r1.getString(r2)
            if (r0 == 0) goto L96
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L96
            java.util.Enumeration r0 = java.net.NetworkInterface.getNetworkInterfaces()     // Catch: java.lang.Exception -> L7f
            java.util.ArrayList r0 = java.util.Collections.list(r0)     // Catch: java.lang.Exception -> L7f
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Exception -> L7f
        L28:
            boolean r2 = r0.hasNext()     // Catch: java.lang.Exception -> L7f
            if (r2 == 0) goto L83
            java.lang.Object r2 = r0.next()     // Catch: java.lang.Exception -> L7f
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2     // Catch: java.lang.Exception -> L7f
            java.lang.String r3 = r2.getName()     // Catch: java.lang.Exception -> L7f
            java.lang.String r4 = "wlan0"
            boolean r3 = r3.equalsIgnoreCase(r4)     // Catch: java.lang.Exception -> L7f
            if (r3 == 0) goto L28
            byte[] r0 = r2.getHardwareAddress()     // Catch: java.lang.Exception -> L7f
            if (r0 != 0) goto L4b
            java.lang.String r1 = "unkown"
            goto L83
        L4b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L7f
            r2.<init>()     // Catch: java.lang.Exception -> L7f
            int r3 = r0.length     // Catch: java.lang.Exception -> L7f
            r4 = 0
            r5 = r4
        L53:
            r6 = 1
            if (r5 >= r3) goto L6c
            r7 = r0[r5]     // Catch: java.lang.Exception -> L7f
            java.lang.String r8 = "%02X:"
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch: java.lang.Exception -> L7f
            java.lang.Byte r7 = java.lang.Byte.valueOf(r7)     // Catch: java.lang.Exception -> L7f
            r6[r4] = r7     // Catch: java.lang.Exception -> L7f
            java.lang.String r6 = java.lang.String.format(r8, r6)     // Catch: java.lang.Exception -> L7f
            r2.append(r6)     // Catch: java.lang.Exception -> L7f
            int r5 = r5 + 1
            goto L53
        L6c:
            int r0 = r2.length()     // Catch: java.lang.Exception -> L7f
            if (r0 <= 0) goto L7a
            int r0 = r2.length()     // Catch: java.lang.Exception -> L7f
            int r0 = r0 - r6
            r2.deleteCharAt(r0)     // Catch: java.lang.Exception -> L7f
        L7a:
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Exception -> L7f
            goto L83
        L7f:
            r0 = move-exception
            r0.printStackTrace()
        L83:
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L8d
            java.lang.String r0 = "unkown"
            r1 = r0
        L8d:
            com.sinovatech.unicom.common.SharePreferenceUtil r0 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()
            java.lang.String r2 = "DeviceHelper-getWifiMac"
            r0.putString(r2, r1)
        L96:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "DeviceHelper-getWifiMac 返回结果："
            r0.append(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7980d(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.DeviceHelper.getWifiMac():java.lang.String");
    }

    public static String getSimIMSI() {
        boolean z = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = App.getSharePreferenceUtil().getString("DeviceHelper-getSimIMSI");
        if (TextUtils.isEmpty(string) && SoulPermission.getInstance().checkSinglePermission("android.permission.READ_PHONE_STATE").isGranted() && z) {
            try {
                string = ((TelephonyManager) App.getInstance().getSystemService("phone")).getSubscriberId();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(string)) {
                string = uuid();
            }
            App.getSharePreferenceUtil().putString("DeviceHelper-getSimIMSI", string);
        }
        MsLogUtil.m7980d("DeviceHelper-getSimIMSI 返回结果：" + string);
        return string;
    }

    public static String getDeviceOSVersion() {
        try {
            if (isHarmonyOS()) {
                return getHarmonyVersion();
            }
            return "android" + Build.VERSION.RELEASE;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getHarmonyVersion() {
        return getProp("hw_sc.build.platform.version", "");
    }

    private static String getProp(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str3 = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
            return TextUtils.isEmpty(str3) ? str2 : str3;
        } catch (Throwable th) {
            th.printStackTrace();
            return str2;
        }
    }

    public static int getDeviceOSVersionCode() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    public static String getUUIDForInterceptor() {
        String string = preference.getString("DeviceHelper-getUUIDForInterceptor");
        try {
            if (TextUtils.isEmpty(string)) {
                string = uuid();
                preference.putString("DeviceHelper-getUUIDForInterceptor", string);
                return string;
            }
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String getDeviceID(boolean z) {
        boolean z2 = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = preference.getString("DeviceHelper-getDeviceID-true");
        if (z2 && TextUtils.isEmpty(string)) {
            try {
                if (ActivityCompat.checkSelfPermission(App.getInstance().getApplicationContext(), "android.permission.READ_PHONE_STATE") == 0) {
                    TelephonyManager telephonyManager = (TelephonyManager) App.instance.getSystemService("phone");
                    string = telephonyManager.getDeviceId();
                    if (TextUtils.isEmpty(string)) {
                        string = telephonyManager.getImei();
                        MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getDeviceId 读取真实设备信息 Imei：" + string);
                    }
                    MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getDeviceId 读取真实设备信息：" + string);
                }
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7977e("DeviceHelper", "DeviceHelper-getDeviceId 读取真实设备信息异常：" + e.getMessage());
            }
            if (TextUtils.isEmpty(string)) {
                String uuid = uuid();
                MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getDeviceId 采集不到真实设备信息-生成uuid：" + uuid);
                string = uuid;
            }
            preference.putString("DeviceHelper-getDeviceID-true", string);
        }
        MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getDeviceId 返回结果：" + string);
        return string;
    }

    public static String getDeviceCode() {
        String deviceID = getDeviceID(false);
        if (TextUtils.isEmpty(deviceID) || deviceID.length() != 32) {
            return deviceID;
        }
        String androidID = getAndroidID();
        return !TextUtils.isEmpty(androidID) ? androidID : deviceID;
    }

    public static String getAnquanzhongxinDeviceID(String str) {
        String str2 = UserManager.getInstance().getCurrentPhoneNumber() + "DeviceHelper-getDeviceID-true-aqzx" + str;
        String string = preference.getString(str2);
        if (TextUtils.isEmpty(string)) {
            string = uuid();
            preference.putString(str2, string);
        }
        MsLogUtil.m7979d("getAnquanzhongxinDeviceID", str + "=====" + string);
        return string;
    }

    public static void resetAnquanzhongxinDeviceID(String str) {
        preference.putString(UserManager.getInstance().getCurrentPhoneNumber() + "DeviceHelper-getDeviceID-true-aqzx" + str, "");
    }

    public static String getNETType(Context context) {
        try {
            switch (new EasyNetworkMod(context).getNetworkType()) {
                case 1:
                    return "Wifi";
                case 2:
                    return "Cellular Unknown";
                case 3:
                    return "2G";
                case 4:
                    return "3G";
                case 5:
                    return "4G";
                case 6:
                    return "Cellular Unidentified Generation";
                default:
                    return "Unknown";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a3, code lost:
        if (r1 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ac, code lost:
        if (r1 == null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ae, code lost:
        r1.close();
     */
    @android.annotation.SuppressLint({"MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String[] getShuangKaInfo(android.content.Context r10) {
        /*
            r0 = 2
            java.lang.String[] r0 = new java.lang.String[r0]
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 0
            r3 = 1
            r4 = 21
            if (r1 >= r4) goto L27
            java.lang.String r1 = "phone"
            java.lang.Object r10 = r10.getSystemService(r1)     // Catch: java.lang.Exception -> L21
            android.telephony.TelephonyManager r10 = (android.telephony.TelephonyManager) r10     // Catch: java.lang.Exception -> L21
            java.lang.String r1 = r10.getSimSerialNumber()     // Catch: java.lang.Exception -> L21
            r0[r3] = r1     // Catch: java.lang.Exception -> L21
            java.lang.String r10 = r10.getLine1Number()     // Catch: java.lang.Exception -> L21
            r0[r2] = r10     // Catch: java.lang.Exception -> L21
            goto Lb1
        L21:
            r10 = move-exception
            r10.printStackTrace()
            goto Lb1
        L27:
            java.lang.String r1 = "content://telephony/siminfo"
            android.net.Uri r5 = android.net.Uri.parse(r1)
            r1 = 0
            android.content.ContentResolver r4 = r10.getContentResolver()
            java.lang.String r10 = "_id"
            java.lang.String r6 = "sim_id"
            java.lang.String r7 = "number"
            java.lang.String r8 = "icc_id"
            java.lang.String[] r6 = new java.lang.String[]{r10, r6, r7, r8}     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            r7 = 0
            r8 = 0
            java.lang.String r9 = "sim_id"
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            if (r1 == 0) goto La3
            r10 = r2
        L4b:
            boolean r4 = r1.moveToNext()     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            if (r4 == 0) goto La3
            java.lang.String r4 = "_id"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            r1.getInt(r4)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r4 = "sim_id"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            int r4 = r1.getInt(r4)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r5 = "number"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r6 = "+86"
            java.lang.String r7 = ""
            java.lang.String r5 = r5.replace(r6, r7)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r6 = "-"
            java.lang.String r7 = ""
            java.lang.String r5 = r5.replace(r6, r7)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r6 = " "
            java.lang.String r7 = ""
            java.lang.String r5 = r5.replace(r6, r7)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r6 = "icc_id"
            int r6 = r1.getColumnIndex(r6)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            java.lang.String r6 = r1.getString(r6)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            if (r7 != 0) goto L4b
            if (r10 != 0) goto L4b
            if (r4 == 0) goto L9d
            if (r4 != r3) goto L4b
        L9d:
            r0[r2] = r5     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            r0[r3] = r6     // Catch: java.lang.Throwable -> La6 java.lang.Exception -> La8
            r10 = r3
            goto L4b
        La3:
            if (r1 == 0) goto Lb1
            goto Lae
        La6:
            r10 = move-exception
            goto Lb2
        La8:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto Lb1
        Lae:
            r1.close()
        Lb1:
            return r0
        Lb2:
            if (r1 == 0) goto Lb7
            r1.close()
        Lb7:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.DeviceHelper.getShuangKaInfo(android.content.Context):java.lang.String[]");
    }

    public static boolean getNeedHuawei() {
        return "HUAWEI".equals(getDeviceBrand().toUpperCase()) && getemuiApiLevel() > 9 && getHwid() > 20401300;
    }

    public static int getemuiApiLevel() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return Integer.parseInt((String) cls.getDeclaredMethod("get", String.class).invoke(cls, "ro.build.hw_emui_api_level"));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static int getHwid() {
        try {
            try {
                PackageInfo packageInfo = App.getInstance().getPackageManager().getPackageInfo("com.huawei.hwid", 0);
                if (packageInfo != null) {
                    return packageInfo.versionCode;
                }
                return 0;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return 0;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static int getSupportFinggerNum() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                FingerprintManagerCompat from = FingerprintManagerCompat.from(App.getInstance());
                if (from.isHardwareDetected()) {
                    return from.hasEnrolledFingerprints() ? 0 : 2;
                }
                return 1;
            } catch (Exception e) {
                e.printStackTrace();
                return 1;
            }
        }
        return 3;
    }

    public static boolean isTopFiveMarket() {
        String deviceBrand = getDeviceBrand();
        return "huawei".equalsIgnoreCase(deviceBrand) || "vivo".equalsIgnoreCase(deviceBrand) || "oppo".equalsIgnoreCase(deviceBrand) || "xiaomi".equalsIgnoreCase(deviceBrand) || "meizu".equalsIgnoreCase(deviceBrand) || "HONOR".equalsIgnoreCase(deviceBrand);
    }

    public static boolean isXIAOMI() {
        return "xiaomi".equalsIgnoreCase(getDeviceBrand());
    }

    public static boolean isVivo() {
        return "vivo".equalsIgnoreCase(getDeviceBrand());
    }

    public static boolean isOppo() {
        return "oppo".equalsIgnoreCase(getDeviceBrand());
    }

    public static boolean isChuizi() {
        return "smartisan".equalsIgnoreCase(getDeviceBrand());
    }

    public static boolean isHuawei() {
        return getNeedHuawei();
    }

    public static boolean isAvilible(String str) {
        return AppUtils.isAppInstalled(str);
    }

    public static boolean isWeixinInstall() {
        if (App.getWXAPI().isWXAppInstalled()) {
            return true;
        }
        return AppUtils.isAppInstalled("com.tencent.mm");
    }

    public static boolean isLocationEnabled() {
        if (Build.VERSION.SDK_INT >= 19) {
            try {
                return Settings.Secure.getInt(App.getInstance().getContentResolver(), "location_mode") != 0;
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }
        return !TextUtils.isEmpty(Settings.Secure.getString(App.getInstance().getContentResolver(), "location_providers_allowed"));
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00b2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getMEID() {
        /*
            com.sinovatech.unicom.common.SharePreferenceUtil r0 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()
            java.lang.String r1 = "hasShowYinsi"
            boolean r0 = r0.getBoolean(r1)
            com.sinovatech.unicom.common.SharePreferenceUtil r1 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()
            java.lang.String r2 = "DeviceHelper-getMEID"
            java.lang.String r1 = r1.getString(r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto La9
            com.qw.soul.permission.SoulPermission r2 = com.p284qw.soul.permission.SoulPermission.getInstance()
            java.lang.String r3 = "android.permission.READ_PHONE_STATE"
            com.qw.soul.permission.bean.Permission r2 = r2.checkSinglePermission(r3)
            boolean r2 = r2.isGranted()
            if (r2 == 0) goto La9
            if (r0 == 0) goto La9
            com.sinovatech.unicom.ui.App r0 = com.sinovatech.unicom.p318ui.App.getInstance()     // Catch: java.lang.Exception -> L75
            java.lang.String r2 = "phone"
            java.lang.Object r0 = r0.getSystemService(r2)     // Catch: java.lang.Exception -> L75
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0     // Catch: java.lang.Exception -> L75
            java.lang.Class r2 = r0.getClass()     // Catch: java.lang.Exception -> L75
            java.lang.String r3 = "getDeviceId"
            r4 = 1
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Exception -> L75
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch: java.lang.Exception -> L75
            r7 = 0
            r5[r7] = r6     // Catch: java.lang.Exception -> L75
            java.lang.reflect.Method r2 = r2.getMethod(r3, r5)     // Catch: java.lang.Exception -> L75
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch: java.lang.Exception -> L75
            r4 = 2
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> L75
            r3[r7] = r4     // Catch: java.lang.Exception -> L75
            java.lang.Object r0 = r2.invoke(r0, r3)     // Catch: java.lang.Exception -> L75
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L75
            java.lang.String r1 = "DeviceHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L70
            r2.<init>()     // Catch: java.lang.Exception -> L70
            java.lang.String r3 = "DeviceHelper-getMEID 采集真实的设备信息："
            r2.append(r3)     // Catch: java.lang.Exception -> L70
            r2.append(r0)     // Catch: java.lang.Exception -> L70
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> L70
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r1, r2)     // Catch: java.lang.Exception -> L70
            goto L94
        L70:
            r1 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L76
        L75:
            r0 = move-exception
        L76:
            r0.printStackTrace()
            java.lang.String r2 = "DeviceHelper"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "DeviceHelper-getMEID 采集真实的设备信息异常："
            r3.append(r4)
            java.lang.String r0 = r0.getMessage()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r2, r0)
            r0 = r1
        L94:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L9f
            java.lang.String r0 = "unkown"
            r1 = r0
            goto La0
        L9f:
            r1 = r0
        La0:
            com.sinovatech.unicom.common.SharePreferenceUtil r0 = com.sinovatech.unicom.p318ui.App.getSharePreferenceUtil()
            java.lang.String r2 = "DeviceHelper-getMEID"
            r0.putString(r2, r1)
        La9:
            java.lang.String r0 = "unkown"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lb4
            java.lang.String r1 = ""
        Lb4:
            java.lang.String r0 = "DeviceHelper"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "DeviceHelper-getMEID 返回结果："
            r2.append(r3)
            r2.append(r1)
            java.lang.String r2 = r2.toString()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.DeviceHelper.getMEID():java.lang.String");
    }

    public static String getAndroidID() {
        boolean z = App.getSharePreferenceUtil().getBoolean("hasShowYinsi");
        String string = App.getSharePreferenceUtil().getString("DeviceHelper-getAndroidID");
        if (TextUtils.isEmpty(string) && z) {
            try {
                string = Settings.System.getString(App.getInstance().getContentResolver(), "android_id");
                MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getAndroidID 采集真实的设备信息：" + string);
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7977e("DeviceHelper", "DeviceHelper-getAndroidID 采集真实的设备信息异常：" + e.getMessage());
            }
            if (TextUtils.isEmpty(string)) {
                string = "unkown";
            }
            App.getSharePreferenceUtil().putString("DeviceHelper-getAndroidID", string);
        }
        if ("unkown".equals(string)) {
            string = "";
        }
        MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getAndroidID 返回结果：" + string);
        return string;
    }

    public static String getUserAgent() {
        try {
            return System.getProperty("http.agent");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean getIsVPN() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService("connectivity");
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                return connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()).hasTransport(4);
            }
            return connectivityManager.getActiveNetworkInfo().getType() == 17;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkCapabilities networkCapabilities;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= 23) {
                Network activeNetwork = connectivityManager.getActiveNetwork();
                if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
                    return false;
                }
                return networkCapabilities.hasTransport(1) || networkCapabilities.hasTransport(0);
            }
            NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
            if (allNetworkInfo != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean netIsConnected() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getSystemService("connectivity");
            if (Build.VERSION.SDK_INT >= 23) {
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (networkCapabilities != null) {
                    return networkCapabilities.hasCapability(16);
                }
                return true;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static boolean isZhediepingPadMode(Activity activity) {
        try {
            if ((activity.getResources().getConfiguration().screenLayout & 15) == 3) {
                if (("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) || "HONOR".equalsIgnoreCase(Build.MANUFACTURER) || "Xiaomi".equalsIgnoreCase(Build.MANUFACTURER) || "samsung".equalsIgnoreCase(Build.MANUFACTURER) || "OPPO".equalsIgnoreCase(Build.MANUFACTURER)) && Build.VERSION.SDK_INT >= 24) {
                    return !activity.isInMultiWindowMode();
                }
                return false;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7980d(e.getMessage());
            return false;
        }
    }

    public static String getLanguage() {
        Locale locale;
        String str = "";
        try {
            Configuration configuration = Resources.getSystem().getConfiguration();
            if (Build.VERSION.SDK_INT >= 24) {
                locale = configuration.getLocales().get(0);
            } else {
                locale = configuration.locale;
            }
            str = locale.toString();
            MsLogUtil.m7977e("DeviceHelper", "DeviceHelper-getLanguage 返回结果：" + str);
            return str;
        } catch (Exception e) {
            MsLogUtil.m7977e("DeviceHelper", "DeviceHelper-getLanguage 异常：" + e.getMessage());
            return str;
        }
    }

    public static long[] getMemInfo() {
        long[] jArr = new long[4];
        try {
            Method method = Class.forName("android.os.Process").getMethod("readProcLines", String.class, String[].class, long[].class);
            String[] strArr = {"MemTotal:", "MemFree:", "Buffers:", "Cached:"};
            long[] jArr2 = new long[strArr.length];
            jArr2[0] = 30;
            jArr2[1] = -30;
            Object[] objArr = {new String("/proc/meminfo"), strArr, jArr2};
            if (method != null) {
                method.invoke(null, objArr);
                for (int i = 0; i < jArr2.length; i++) {
                    jArr[i] = jArr2[i];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jArr;
    }

    public static String getFreeMem() {
        long[] memInfo = getMemInfo();
        return Long.toString(memInfo[1] + memInfo[2] + memInfo[3]);
    }

    public static String getTotalMem() {
        return Long.toString(getMemInfo()[0]);
    }

    public static String getFreeAndTotalMem() {
        long[] memInfo = getMemInfo();
        return Long.toString(memInfo[1] + memInfo[2] + memInfo[3]) + "/" + Long.toString(memInfo[0]);
    }

    public static String trans2FreeAndTotalMem(long[] jArr) {
        return Long.toString((jArr[1] + jArr[2] + jArr[3]) * 1024) + "/" + Long.toString(jArr[0] * 1024);
    }

    public static String totlaMem(long[] jArr) {
        return Long.toString(jArr[0] * 1024);
    }

    public static String leftMem(long[] jArr) {
        return Long.toString((jArr[1] + jArr[2] + jArr[3]) * 1024);
    }

    public static String getSensorList() {
        return getSensorList(-1);
    }

    public static String getSensorList(int i) {
        List<Sensor> sensorList = ((SensorManager) App.getInstance().getSystemService("sensor")).getSensorList(i);
        StringBuilder sb = new StringBuilder();
        sb.append("sensorType = " + i);
        int i2 = 1;
        for (Sensor sensor : sensorList) {
            sb.append(i2 + ".");
            sb.append("  Sensor Type - " + sensor.getType() + "\r\n");
            sb.append("  Sensor Name - " + sensor.getName() + "\r\n");
            sb.append("  Sensor Version - " + sensor.getVersion() + "\r\n");
            sb.append("  Sensor Vendor - " + sensor.getVendor() + "\r\n");
            sb.append("  Maximum Range - " + sensor.getMaximumRange() + "\r\n");
            sb.append("  Minimum Delay - " + sensor.getMinDelay() + "\r\n");
            sb.append("  Power - " + sensor.getPower() + "\r\n");
            sb.append("  Resolution - " + sensor.getResolution() + "\r\n");
            sb.append("\r\n");
            i2++;
        }
        MsLogUtil.m7979d("DeviceHelper", "DeviceHelper-getSensorList 传感器列表 \n" + sb.toString());
        return "";
    }

    public static int getBatteryLevel() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((BatteryManager) App.getInstance().getSystemService("batterymanager")).getIntProperty(4);
            }
            Intent registerReceiver = new ContextWrapper(ContextUtils.getApplicationContext()).registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return (registerReceiver.getIntExtra("level", -1) * 100) / registerReceiver.getIntExtra("scale", -1);
        } catch (Exception e) {
            MsLogUtil.m7977e("DeviceHelper", "DeviceHelper-getBatteryLevel 电量异常" + e.getMessage());
            return -1;
        }
    }

    public static String getKernelVersion() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean isCharging() {
        return App.getInstance().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")).getIntExtra("plugged", -1) != 0;
    }

    public static String getOperator() {
        String str = "N/A";
        String str2 = "N/A";
        String str3 = "N/A";
        String str4 = "N/A";
        String str5 = "N/A";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) App.getInstance().getSystemService("phone");
            str = telephonyManager.getSimOperator();
            str2 = telephonyManager.getSimOperatorName();
            str3 = telephonyManager.getNetworkOperator();
            str4 = telephonyManager.getNetworkOperatorName();
            if (UIUtils.checkPermissions("android.permission.READ_PHONE_STATE")) {
                str5 = telephonyManager.getSubscriberId() + "";
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("DeviceHelper", "DeviceHelper-operator 运营商异常" + e.getMessage());
        }
        return str + "/" + str2 + "/" + str3 + "/" + str4 + "/" + str5;
    }

    public static int getSystemBrightness() {
        try {
            int i = Settings.System.getInt(App.getInstance().getContentResolver(), "screen_brightness");
            int integer = App.getInstance().getResources().getInteger(App.getInstance().getResources().getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android"));
            return integer > 0 ? (i * 100) / integer : i;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isHarmonyOS() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return "harmony".equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (ClassNotFoundException unused) {
            MsLogUtil.m7977e("DeviceHelper", "occured ClassNotFoundException");
            return false;
        } catch (Exception unused2) {
            MsLogUtil.m7977e("DeviceHelper", "occur other problem");
            return false;
        }
    }

    public static String getConnectWifiSsid(Context context) {
        if ("Wifi".equals(getNETType(context))) {
            WifiInfo connectionInfo = ((WifiManager) App.getInstance().getApplicationContext().getSystemService("wifi")).getConnectionInfo();
            MsLogUtil.m7979d("wifiInfo------", connectionInfo.toString());
            MsLogUtil.m7979d("SSID------", connectionInfo.getSSID());
            return connectionInfo.getSSID();
        }
        return "";
    }

    public static int getCarrierType(Context context) {
        int i = -1;
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList();
                SubscriptionManager.from(context);
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

    public static boolean hasSimCard(Context context) {
        switch (((TelephonyManager) context.getSystemService("phone")).getSimState()) {
            case 0:
            case 1:
                return false;
            default:
                return true;
        }
    }

    public static void getMobileNetworkSignal(Context context, Consumer<Integer> consumer) {
        int i;
        try {
            if (!hasSimCard(context)) {
                consumer.accept(12);
                return;
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                if (ActivityCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0) {
                    signlaListener(telephonyManager, consumer);
                    return;
                }
                List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
                ArrayList arrayList = new ArrayList();
                if (allCellInfo != null) {
                    for (CellInfo cellInfo : allCellInfo) {
                        try {
                            if (cellInfo instanceof CellInfoGsm) {
                                i = ((CellInfoGsm) cellInfo).getCellSignalStrength().getDbm();
                                MsLogUtil.m7979d("getMobileNetworkSignal", "CellInfoGsm" + i);
                            } else if (cellInfo instanceof CellInfoCdma) {
                                i = ((CellInfoCdma) cellInfo).getCellSignalStrength().getDbm();
                                MsLogUtil.m7979d("getMobileNetworkSignal", "CellInfoCdma" + i);
                            } else if (cellInfo instanceof CellInfoWcdma) {
                                i = ((CellInfoWcdma) cellInfo).getCellSignalStrength().getDbm();
                                MsLogUtil.m7979d("getMobileNetworkSignal", "CellInfoWcdma" + i);
                            } else if (cellInfo instanceof CellInfoLte) {
                                i = ((CellInfoLte) cellInfo).getCellSignalStrength().getDbm();
                                MsLogUtil.m7979d("getMobileNetworkSignal", "CellInfoLte" + i);
                            } else if (Build.VERSION.SDK_INT < 29 || !(cellInfo instanceof CellInfoNr)) {
                                i = 10;
                            } else {
                                i = ((CellSignalStrengthNr) ((CellInfoNr) cellInfo).getCellSignalStrength()).getDbm();
                                MsLogUtil.m7979d("getMobileNetworkSignal", "Q" + i);
                            }
                            arrayList.add(Integer.valueOf(i));
                        } catch (Exception e) {
                            MsLogUtil.m7979d("getMobileNetworkSignal", "Exception:" + e.getMessage());
                            signlaListener(telephonyManager, consumer);
                            return;
                        }
                    }
                }
                if (arrayList.isEmpty()) {
                    signlaListener(telephonyManager, consumer);
                    return;
                }
                Collections.sort(arrayList, new Comparator() { // from class: com.sinovatech.unicom.common.-$$Lambda$DeviceHelper$3L9MnYO6OpXEdjS0Kp7NwtmzIyw
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return DeviceHelper.lambda$getMobileNetworkSignal$0((Integer) obj, (Integer) obj2);
                    }
                });
                MsLogUtil.m7979d("getMobileNetworkSignal", "dbms:" + arrayList);
                Integer num = (Integer) arrayList.get(0);
                if (num.intValue() > 0) {
                    signlaListener(telephonyManager, consumer);
                    return;
                } else {
                    consumer.accept(num);
                    return;
                }
            }
            consumer.accept(11);
        } catch (Exception e2) {
            e2.printStackTrace();
            consumer.accept(10);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getMobileNetworkSignal$0(Integer num, Integer num2) {
        return num2.intValue() - num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.common.DeviceHelper$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C81001 extends PhoneStateListener {
        final /* synthetic */ Consumer val$consumer;
        final /* synthetic */ TelephonyManager val$telephonyManager;

        C81001(TelephonyManager telephonyManager, Consumer consumer) {
            this.val$telephonyManager = telephonyManager;
            this.val$consumer = consumer;
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            int networkType = this.val$telephonyManager.getNetworkType();
            int i = 1;
            if (networkType == 20) {
                ArrayList arrayList = new ArrayList();
                try {
                    Method declaredMethod = Class.forName("android.telephony.SignalStrength").getDeclaredMethod("getCellSignalStrengths", new Class[0]);
                    declaredMethod.setAccessible(true);
                    List<CellSignalStrength> list = (List) declaredMethod.invoke(signalStrength, new Object[0]);
                    if (list != null && list.size() > 0) {
                        for (CellSignalStrength cellSignalStrength : list) {
                            if (cellSignalStrength instanceof CellSignalStrengthNr) {
                                arrayList.add(Integer.valueOf(((CellSignalStrengthNr) cellSignalStrength).getSsRsrp()));
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Collections.sort(arrayList, new Comparator() { // from class: com.sinovatech.unicom.common.-$$Lambda$DeviceHelper$1$EqDMdAEHrRkWNnzKqcUpgXqAYY4
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return DeviceHelper.C81001.lambda$onSignalStrengthsChanged$0((Integer) obj, (Integer) obj2);
                    }
                });
                MsLogUtil.m7979d("getMobileNetworkSignal", "dbms:" + arrayList);
                Integer num = (Integer) arrayList.get(0);
                if (num.intValue() < 0) {
                    this.val$consumer.accept(num);
                    this.val$telephonyManager.listen(this, 0);
                    return;
                }
            }
            if (networkType == 13) {
                try {
                    int intValue = ((Integer) signalStrength.getClass().getMethod("getLteRsrp", new Class[0]).invoke(signalStrength, new Object[0])).intValue();
                    if (intValue < 0) {
                        this.val$consumer.accept(Integer.valueOf(intValue));
                        this.val$telephonyManager.listen(this, 0);
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            try {
            } catch (Exception e3) {
                e3.printStackTrace();
                int cdmaDbm = signalStrength.getCdmaDbm();
                int gsmSignalStrength = signalStrength.getGsmSignalStrength();
                if (gsmSignalStrength < 99) {
                    i = (gsmSignalStrength * 2) - 132;
                } else if (cdmaDbm > -120 && cdmaDbm < 0) {
                    i = cdmaDbm;
                }
                MsLogUtil.m7979d("getMobileNetworkSignal", "没有定位的时候，取2G信号强度" + i);
                if (i < 0) {
                    this.val$consumer.accept(Integer.valueOf(i));
                } else {
                    this.val$consumer.accept(11);
                }
            }
            if (Build.VERSION.SDK_INT >= 29) {
                List<CellSignalStrength> cellSignalStrengths = signalStrength.getCellSignalStrengths();
                if (!cellSignalStrengths.isEmpty()) {
                    int dbm = cellSignalStrengths.get(0).getDbm();
                    this.val$consumer.accept(Integer.valueOf(dbm));
                    MsLogUtil.m7979d("getMobileNetworkSignal", "没有定位的时候，取2G信号强度" + dbm);
                    this.val$telephonyManager.listen(this, 0);
                    return;
                }
                throw new NoSuchMethodException();
            }
            throw new NoSuchMethodException();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$onSignalStrengthsChanged$0(Integer num, Integer num2) {
            return num2.intValue() - num.intValue();
        }
    }

    private static void signlaListener(TelephonyManager telephonyManager, Consumer<Integer> consumer) {
        telephonyManager.listen(new C81001(telephonyManager, consumer), 256);
    }

    public static boolean is64BitCpu() {
        String[] strArr;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (App.getSharePreferenceUtil().getBoolean("hasShowYinsi") && (strArr = Build.SUPPORTED_64_BIT_ABIS) != null) {
            for (String str : strArr) {
                if ("arm64-v8a".equals(str)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
