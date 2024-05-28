package com.sinovatech.unicom.separatemodule.idcard.camera1.utils;

import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MIUIUtils {
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";

    public static boolean isMIUI() {
        return "xiaomi".equalsIgnoreCase(Build.MANUFACTURER);
    }
}
