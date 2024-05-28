package com.sinovatech.unicom.p318ui;

import android.text.TextUtils;
import com.fort.andjni.JniLib;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.ui.PackageFilter */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PackageFilter {
    private static String[] ClassNameFilterCollection = {"com.android.gallery3d", "com.android.camera", "com.android.mms", "com.android.phone", "com.cooltest.viki", "com.android.packageinstaller", "com.android.dialer", "com.android.incallui", "com.android.email", "com.android.contacts"};
    private static final String TAG = "PackageFilter";

    public PackageFilter() {
        JniLib.m15918cV(this, 329);
    }

    public static boolean isExitInClassNameFilterCollection(String str) {
        String[] strArr;
        try {
            for (String str2 : ClassNameFilterCollection) {
                if (!TextUtils.isEmpty(str) && str.trim().startsWith(str2.trim())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "isExitInClassNameFilterCollection() 异常:" + e.getMessage());
            return false;
        }
    }
}
