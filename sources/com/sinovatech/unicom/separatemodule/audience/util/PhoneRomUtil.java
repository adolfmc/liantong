package com.sinovatech.unicom.separatemodule.audience.util;

import android.os.Build;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PhoneRomUtil {
    public static boolean isMIUIRom() {
        String str = Build.MANUFACTURER;
        if (StringUtils.isNull(str)) {
            return false;
        }
        return str.equalsIgnoreCase("xiaomi");
    }

    public static boolean isMediaMetadataRetrieverSlowRom() {
        return isOPPOR9sk();
    }

    public static boolean isOPPOR9sk() {
        String str = Build.MODEL;
        if (StringUtils.isNull(str)) {
            return false;
        }
        return str.equalsIgnoreCase("OPPO R9sk");
    }

    public static boolean isSmartisanRom() {
        return Build.BRAND.equalsIgnoreCase("smartisan");
    }

    public static boolean isHuaWei() {
        return "HUAWEI".equals(Build.MANUFACTURER) || TextUtils.equals(Build.BRAND, "HUAWEI");
    }

    public static boolean isHonor() {
        return TextUtils.equals(Build.BRAND, "HONOR");
    }

    public static boolean isSamsungG9250() {
        return TextUtils.equals(Build.MODEL, "SM-G9250");
    }

    public static boolean isMeitu() {
        return TextUtils.equals(Build.BRAND, "Meitu");
    }
}
