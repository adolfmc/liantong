package com.sinovatech.unicom.common.utils;

import android.os.Build;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomOSUtils {
    private static final String KEY_COLOROS_VERSION_NAME = "ro.build.version.opporom";
    private static final String KEY_EMUI_VERSION_NAME = "ro.build.version.emui";
    private static final String KEY_FLYME_VERSION_NAME = "ro.build.display.id";
    private static final String KEY_HARMONYOS_VERSION_NAME = "hw_sc.build.platform.version";
    private static final String KEY_MAGICUI_VERSION = "ro.build.version.magic";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_NUBIA_VERSION_CODE = "ro.build.nubia.rom.code";
    private static final String KEY_NUBIA_VERSION_NAME = "ro.build.nubia.rom.name";
    private static final String KEY_ONEPLUS_VERSION_NAME = "ro.rom.version";
    private static final String KEY_VIVO_VERSION = "ro.vivo.os.version";
    private static final String KEY_VIVO_VERSION_NAME = "ro.vivo.os.name";
    private static String customOS = "";
    private static String customOSVersion = "";

    private static boolean isMagicUI() {
        return false;
    }

    private static String getSystemPropertyValue(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static boolean isHarmonyOS() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return !TextUtils.isEmpty((String) cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String getHarmonySystemPropertyValue() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            return (String) cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPhoneSystem(String str) {
        if (TextUtils.isEmpty(customOS)) {
            setCustomOSInfo(str);
        }
        return customOS + customOSVersion;
    }

    public static String getCustomOS(String str) {
        if (TextUtils.isEmpty(customOS)) {
            setCustomOSInfo(str);
        }
        return customOS;
    }

    public static String getCustomOSVersion(String str) {
        if (TextUtils.isEmpty(customOS)) {
            setCustomOSInfo(str);
        }
        return customOSVersion;
    }

    public static String getCustomOSVersionSimple(String str) {
        String str2 = customOSVersion;
        if (TextUtils.isEmpty(customOS)) {
            getCustomOSVersion(str);
        }
        if (customOSVersion.contains(".")) {
            return customOSVersion.substring(0, customOSVersion.indexOf("."));
        }
        return str2;
    }

    public static String deleteSpaceAndToUpperCase(String str) {
        return TextUtils.isEmpty(str) ? "" : str.replaceAll(" ", "").toUpperCase();
    }

    private static void setCustomOSInfo(String str) {
        try {
            String deleteSpaceAndToUpperCase = deleteSpaceAndToUpperCase(str);
            char c = 65535;
            switch (deleteSpaceAndToUpperCase.hashCode()) {
                case -1881642058:
                    if (deleteSpaceAndToUpperCase.equals("REALME")) {
                        c = 4;
                        break;
                    }
                    break;
                case -1706170181:
                    if (deleteSpaceAndToUpperCase.equals("XIAOMI")) {
                        c = 2;
                        break;
                    }
                    break;
                case -602397472:
                    if (deleteSpaceAndToUpperCase.equals("ONEPLUS")) {
                        c = 7;
                        break;
                    }
                    break;
                case 2432928:
                    if (deleteSpaceAndToUpperCase.equals("OPPO")) {
                        c = 5;
                        break;
                    }
                    break;
                case 2634924:
                    if (deleteSpaceAndToUpperCase.equals("VIVO")) {
                        c = 6;
                        break;
                    }
                    break;
                case 68924490:
                    if (deleteSpaceAndToUpperCase.equals("HONOR")) {
                        c = 1;
                        break;
                    }
                    break;
                case 73239724:
                    if (deleteSpaceAndToUpperCase.equals("MEIZU")) {
                        c = '\b';
                        break;
                    }
                    break;
                case 74632627:
                    if (deleteSpaceAndToUpperCase.equals("NUBIA")) {
                        c = '\t';
                        break;
                    }
                    break;
                case 77852109:
                    if (deleteSpaceAndToUpperCase.equals("REDMI")) {
                        c = 3;
                        break;
                    }
                    break;
                case 2141820391:
                    if (deleteSpaceAndToUpperCase.equals("HUAWEI")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    if (isHarmonyOS()) {
                        customOSVersion = getSystemPropertyValue("hw_sc.build.platform.version");
                        customOS = "HarmonyOS";
                        return;
                    }
                    customOS = "EMUI";
                    customOSVersion = getSystemPropertyValue("ro.build.version.emui");
                    return;
                case 1:
                    if (isHarmonyOS()) {
                        customOS = "HarmonyOS";
                        if (!TextUtils.isEmpty(getSystemPropertyValue("hw_sc.build.platform.version"))) {
                            customOSVersion = getSystemPropertyValue("hw_sc.build.platform.version");
                            return;
                        } else {
                            customOSVersion = "";
                            return;
                        }
                    } else if (!TextUtils.isEmpty(getSystemPropertyValue("ro.build.version.magic"))) {
                        customOS = "MagicUI";
                        customOSVersion = getSystemPropertyValue("ro.build.version.magic");
                        return;
                    } else {
                        customOS = "EMUI";
                        customOSVersion = getSystemPropertyValue("ro.build.version.emui");
                        return;
                    }
                case 2:
                case 3:
                    customOS = "MIUI";
                    customOSVersion = getSystemPropertyValue("ro.miui.ui.version.name");
                    return;
                case 4:
                case 5:
                    customOS = "ColorOS";
                    customOSVersion = getSystemPropertyValue("ro.build.version.opporom");
                    return;
                case 6:
                    customOS = "Funtouch";
                    customOSVersion = getSystemPropertyValue("ro.vivo.os.version");
                    return;
                case 7:
                    customOS = "HydrogenOS";
                    customOSVersion = getSystemPropertyValue("ro.rom.version");
                    return;
                case '\b':
                    customOS = "Flyme";
                    customOSVersion = getSystemPropertyValue("ro.build.display.id");
                    return;
                case '\t':
                    customOS = getSystemPropertyValue("ro.build.nubia.rom.name");
                    customOSVersion = getSystemPropertyValue("ro.build.nubia.rom.code");
                    return;
                default:
                    customOS = "Android";
                    customOSVersion = Build.VERSION.RELEASE;
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
