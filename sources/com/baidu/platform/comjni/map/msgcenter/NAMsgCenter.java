package com.baidu.platform.comjni.map.msgcenter;

import com.baidu.platform.comjni.JNIBaseApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NAMsgCenter extends JNIBaseApi {

    /* renamed from: a */
    private long f8127a = 0;

    private native boolean nativeCancelRequest(long j);

    private native long nativeCreate();

    private native boolean nativeFetchAccessToken(long j);

    private native String nativeGetCenterParam(long j, String str);

    private native boolean nativeMSGCStartup(long j);

    private native boolean nativeRegMsgCenter(long j, String str);

    private native int nativeRelease(long j);

    private native boolean nativeSetCenterParam(long j, String str);
}
