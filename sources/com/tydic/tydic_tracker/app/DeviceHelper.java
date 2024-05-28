package com.tydic.tydic_tracker.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DeviceHelper {
    private static final String TAG = "DeviceHelper";

    public static void getCPUInfo() {
    }

    public static String getDeviceBrand() {
        try {
            return Build.MANUFACTURER;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDeviceBranD() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDeviceModel() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getWifiMac() {
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        return "unkown";
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    return sb.toString();
                }
            }
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDeviceOSVersion() {
        try {
            return "android" + Build.VERSION.RELEASE;
        } catch (Exception unused) {
            return "";
        }
    }

    public static int getDeviceOSVersionCode() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x00a0, code lost:
        if (r1 != null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a9, code lost:
        if (r1 == null) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00ab, code lost:
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
            goto Lae
        L21:
            r10 = move-exception
            r10.printStackTrace()
            goto Lae
        L27:
            java.lang.String r1 = "content://telephony/siminfo"
            android.net.Uri r5 = android.net.Uri.parse(r1)
            r1 = 0
            android.content.ContentResolver r4 = r10.getContentResolver()
            java.lang.String r10 = "_id"
            java.lang.String r6 = "sim_id"
            java.lang.String r7 = "number"
            java.lang.String r8 = "icc_id"
            java.lang.String[] r6 = new java.lang.String[]{r10, r6, r7, r8}     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            r7 = 0
            r8 = 0
            java.lang.String r9 = "sim_id"
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            if (r1 == 0) goto La0
            r10 = r2
        L49:
            boolean r4 = r1.moveToNext()     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            if (r4 == 0) goto La0
            java.lang.String r4 = "_id"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            r1.getInt(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r4 = "sim_id"
            int r4 = r1.getColumnIndex(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            int r4 = r1.getInt(r4)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r5 = "number"
            int r5 = r1.getColumnIndex(r5)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r5 = r1.getString(r5)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r6 = "+86"
            java.lang.String r7 = ""
            java.lang.String r5 = r5.replace(r6, r7)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r6 = "-"
            java.lang.String r7 = ""
            java.lang.String r5 = r5.replace(r6, r7)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r6 = " "
            java.lang.String r7 = ""
            java.lang.String r5 = r5.replace(r6, r7)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r6 = "icc_id"
            int r6 = r1.getColumnIndex(r6)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            java.lang.String r6 = r1.getString(r6)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            boolean r7 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            if (r7 != 0) goto L49
            if (r10 != 0) goto L49
            if (r4 == 0) goto L9a
            if (r4 != r3) goto L49
        L9a:
            r0[r2] = r5     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            r0[r3] = r6     // Catch: java.lang.Throwable -> La3 java.lang.Exception -> La5
            r10 = r3
            goto L49
        La0:
            if (r1 == 0) goto Lae
            goto Lab
        La3:
            r10 = move-exception
            goto Laf
        La5:
            r10 = move-exception
            r10.printStackTrace()     // Catch: java.lang.Throwable -> La3
            if (r1 == 0) goto Lae
        Lab:
            r1.close()
        Lae:
            return r0
        Laf:
            if (r1 == 0) goto Lb4
            r1.close()
        Lb4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tydic.tydic_tracker.app.DeviceHelper.getShuangKaInfo(android.content.Context):java.lang.String[]");
    }

    public static String getUserAgent() {
        try {
            return System.getProperty("http.agent");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
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

    public static int getBatteryLevel(Context context) {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((BatteryManager) context.getSystemService("batterymanager")).getIntProperty(4);
            }
            Intent registerReceiver = new ContextWrapper(context).registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            return (registerReceiver.getIntExtra("level", -1) * 100) / registerReceiver.getIntExtra("scale", -1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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
}
