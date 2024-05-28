package com.sdk.base.framework.bean;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DeviceUtils {
    public static String getDeviceBoard() {
        return Build.BOARD;
    }

    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getDeviceName() {
        return Build.DEVICE;
    }

    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    public static String getPhoneDetail() {
        return getPhoneBrand() + " " + getDeviceName() + " " + getPhoneModel() + " " + getVersionRelease();
    }

    public static String getPhoneInfo() {
        return "设备信息如下：\n手机厂商 = " + getPhoneBrand() + "\n手机型号= " + getPhoneModel() + "\n安卓版本 = " + getVersionRelease() + "\n设备名称 = " + getDeviceName() + "\n主板名称 = " + getDeviceBoard() + "\n生产制造商 = " + getDeviceManufacturer() + "\n";
    }

    public static String getPhoneModel() {
        return Build.MODEL;
    }

    public static String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }
}
