package com.huawei.hms.framework.common;

import android.util.Base64;
import com.huawei.secure.android.common.util.SafeBase64;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SecurityBase64Utils {
    private static volatile boolean IS_AEGIS_BASE64_LIBRARY_LOADED = false;
    private static final String SAFE_BASE64_PATH = "com.huawei.secure.android.common.util.SafeBase64";

    public static String encodeToString(byte[] bArr, int i) {
        if (IS_AEGIS_BASE64_LIBRARY_LOADED || checkCompatible("com.huawei.secure.android.common.util.SafeBase64")) {
            return SafeBase64.encodeToString(bArr, i);
        }
        try {
            return Base64.encodeToString(bArr, i);
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] decode(String str, int i) {
        if (IS_AEGIS_BASE64_LIBRARY_LOADED || checkCompatible("com.huawei.secure.android.common.util.SafeBase64")) {
            return SafeBase64.decode(str, i);
        }
        try {
            return Base64.decode(str, i);
        } catch (Exception unused) {
            return new byte[0];
        }
    }

    private static boolean checkCompatible(String str) {
        ClassLoader classLoader = SecurityBase64Utils.class.getClassLoader();
        if (classLoader == null) {
            return false;
        }
        try {
            classLoader.loadClass(str);
            synchronized (SecurityBase64Utils.class) {
                IS_AEGIS_BASE64_LIBRARY_LOADED = true;
            }
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
