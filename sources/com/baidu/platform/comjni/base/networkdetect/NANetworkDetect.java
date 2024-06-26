package com.baidu.platform.comjni.base.networkdetect;

import com.baidu.platform.comjni.NativeComponent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NANetworkDetect extends NativeComponent {
    public NANetworkDetect() {
        create();
    }

    private native long nativeCreate();

    private native boolean nativeNetworkDetect(long j, String str);

    private native int nativeRelease(long j);

    @Override // com.baidu.platform.comjni.NativeComponent
    public long create() {
        this.mNativePointer = nativeCreate();
        return this.mNativePointer;
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public int dispose() {
        return nativeRelease(this.mNativePointer);
    }
}
