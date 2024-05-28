package com.huawei.hms.opendevice;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.p */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class StringUtils {
    /* renamed from: a */
    public static boolean m14330a(String... strArr) {
        for (String str : strArr) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
        }
        return true;
    }
}
