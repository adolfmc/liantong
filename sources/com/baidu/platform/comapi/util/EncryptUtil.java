package com.baidu.platform.comapi.util;

import com.baidu.platform.comjni.JNIBaseApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class EncryptUtil extends JNIBaseApi {
    private static native String nativeDecrypt(String str, String str2);

    private static native String nativeEncrypt(String str, String str2);
}
