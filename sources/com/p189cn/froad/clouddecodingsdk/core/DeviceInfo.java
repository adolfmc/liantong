package com.p189cn.froad.clouddecodingsdk.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import java.io.UnsupportedEncodingException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.core.DeviceInfo */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DeviceInfo {
    private static final String TAG = "DeviceInfo";

    @SuppressLint({"WrongConstant"})
    public static String carrierOperator(Context context) {
        String simOperator = ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
        if (simOperator != null) {
            return (simOperator.equals("46000") || simOperator.equals("46002")) ? "中国移动" : simOperator.equals("46001") ? "中国联通" : simOperator.equals("46003") ? "中国电信" : "其他";
        }
        return null;
    }

    public static String format(String str) {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        byte[] bArr = new byte[0];
        try {
            bArr = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bArr.length; i++) {
            sb.append(charArray[(bArr[i] & 240) >> 4]);
            sb.append(charArray[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public static String getfftDeviceInfo(Context context, long j, long j2) {
        StringBuilder sb = new StringBuilder();
        sb.append("{netWorkType:'" + carrierOperator(context) + "'");
        sb.append(",rCardSumTime:'" + j2 + "'");
        sb.append(",netWorkMode:'" + netType(context) + "'");
        sb.append(",sysVersion:'" + sysV() + "'");
        sb.append(",deviceProduct:'" + product() + "'");
        sb.append(",deviceModel:'" + model() + "'");
        sb.append(",sysType:'Android'");
        sb.append(",sdkVersion:'" + FFTeIDSE.getSDKVersion() + "'");
        sb.append(",rCardStartTime:'" + j + "'");
        sb.append(",package:'" + context.getPackageName() + "'");
        sb.append("}");
        String sb2 = sb.toString();
        TMKeyLog.m16310d("DeviceInfo", sb2);
        return "FEDFEDED" + format(sb2);
    }

    public static String model() {
        return Build.MODEL;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @SuppressLint({"WrongConstant"})
    public static String netType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return "";
            }
            if (activeNetworkInfo.getType() == 1) {
                return "WIFI";
            }
            if (activeNetworkInfo.getType() == 0) {
                String subtypeName = activeNetworkInfo.getSubtypeName();
                switch (activeNetworkInfo.getSubtype()) {
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
                    case 14:
                    case 15:
                        break;
                    case 13:
                        return "4G";
                    default:
                        if (!subtypeName.equalsIgnoreCase("TD-SCDMA") && !subtypeName.equalsIgnoreCase("WCDMA")) {
                            if (!subtypeName.equalsIgnoreCase("CDMA2000")) {
                                return subtypeName;
                            }
                        }
                        break;
                }
                return "3G";
            }
            return "";
        } catch (Exception unused) {
            return "WIFI";
        }
    }

    public static String product() {
        return Build.BRAND;
    }

    public static String sysV() {
        return Build.VERSION.RELEASE;
    }
}
