package com.huawei.hms.framework.common;

import com.huawei.android.os.BuildEx;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class EmuiUtil {
    private static final String BUILDEX_NAME = "com.huawei.android.os.BuildEx";
    public static final String BUILDEX_VERSION = "com.huawei.android.os.BuildEx$VERSION";
    private static final int EMUI_3_0 = 7;
    private static final int EMUI_3_1 = 8;
    private static final int EMUI_4_0 = 9;
    private static final int EMUI_4_1 = 10;
    private static final int EMUI_5_0 = 11;
    private static final int EMUI_6_0 = 14;
    private static final int EMUI_8_0_1 = 15;
    private static final int EMUI_9_0 = 17;
    public static final String EMUI_SDK_INT = "EMUI_SDK_INT";
    private static final int EMUI_TYPE_UNKOWN = -1;
    public static final String GET_PRIMARY_COLOR = "getPrimaryColor";
    public static final String GET_SUGGESTION_FOR_GROUND_COLOR_STYLE = "getSuggestionForgroundColorStyle";
    public static final String IMMERSION_STYLE = "com.huawei.android.immersion.ImmersionStyle";
    private static final String TAG = "KPMS_Util_Emui";
    private static final int TYPE_EMUI_30 = 30;
    private static final int TYPE_EMUI_31 = 31;
    private static final int TYPE_EMUI_40 = 40;
    private static final int TYPE_EMUI_41 = 41;
    private static final int TYPE_EMUI_50 = 50;
    private static final int TYPE_EMUI_60 = 60;
    private static final int TYPE_EMUI_801 = 81;
    private static final int TYPE_EMUI_90 = 90;
    private static int emuiType = -1;

    static {
        initEmuiType();
    }

    public static boolean isEMUI() {
        return -1 != emuiType;
    }

    private static void initEmuiType() {
        int eMUIVersionCode = getEMUIVersionCode();
        Logger.m15054d("KPMS_Util_Emui", "getEmuiType emuiVersionCode=" + eMUIVersionCode);
        if (eMUIVersionCode >= 17) {
            emuiType = 90;
        } else if (eMUIVersionCode >= 15) {
            emuiType = 81;
        } else if (eMUIVersionCode >= 14) {
            emuiType = 60;
        } else if (eMUIVersionCode >= 11) {
            emuiType = 50;
        } else if (eMUIVersionCode >= 10) {
            emuiType = 41;
        } else if (eMUIVersionCode >= 9) {
            emuiType = 40;
        } else if (eMUIVersionCode >= 8) {
            emuiType = 31;
        } else if (eMUIVersionCode >= 7) {
            emuiType = 30;
        }
        if (emuiType == -1) {
            Logger.m15049i("KPMS_Util_Emui", "emuiType is unkown");
        }
    }

    public static int getEMUIVersionCode() {
        int intValue;
        Object staticFieldObj = ReflectionUtils.getStaticFieldObj("com.huawei.android.os.BuildEx$VERSION", "EMUI_SDK_INT");
        if (staticFieldObj != null) {
            try {
                intValue = ((Integer) staticFieldObj).intValue();
            } catch (ClassCastException e) {
                Logger.m15051e("KPMS_Util_Emui", "getEMUIVersionCode ClassCastException:", e);
            }
            Logger.m15054d("KPMS_Util_Emui", "the emui version code is::" + intValue);
            return intValue;
        }
        intValue = 0;
        Logger.m15054d("KPMS_Util_Emui", "the emui version code is::" + intValue);
        return intValue;
    }

    public static boolean isUpPVersion() {
        try {
            if (ReflectionUtils.checkCompatible("com.huawei.android.os.BuildEx") && ReflectionUtils.checkCompatible("com.huawei.android.os.BuildEx$VERSION")) {
                return BuildEx.VERSION.EMUI_SDK_INT >= 17;
            }
        } catch (NoSuchMethodError unused) {
            Logger.m15054d("KPMS_Util_Emui", "no such method for com.huawei.android.os.BuildEx.VERSION");
        } catch (Throwable unused2) {
            Logger.m15054d("KPMS_Util_Emui", "com.huawei.android.os.BuildEx.VERSION has other exception");
        }
        Logger.m15054d("KPMS_Util_Emui", "com.huawei.android.os.BuildEx : false");
        return false;
    }
}
