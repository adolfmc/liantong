package com.baidu.platform.comjni.util;

import com.baidu.platform.comjni.JNIBaseApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10201592_dexfile_execute.dex */
public class JNIMD5 extends JNIBaseApi {
    public static native String EncodeUrlParamsValue(String str);

    public static native String GetSignMD5String(String str);

    public static native String GetWebSignMD5String(String str);

    public static native String SignOpra(String str);
}
