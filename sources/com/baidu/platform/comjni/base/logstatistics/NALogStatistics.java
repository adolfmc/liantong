package com.baidu.platform.comjni.base.logstatistics;

import com.baidu.platform.comjni.NativeComponent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class NALogStatistics extends NativeComponent {
    public NALogStatistics() {
        create();
    }

    public static native boolean nativeAddLog(long j, int i, int i2, String str, String str2, String str3);

    public static native long nativeCreate();

    public static native int nativeRelease(long j);

    public static native boolean nativeSave(long j);

    /* renamed from: a */
    public boolean m17677a(int i, int i2, String str, String str2, String str3) {
        return false;
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public long create() {
        return this.mNativePointer;
    }

    @Override // com.baidu.platform.comjni.NativeComponent
    public int dispose() {
        if (this.mNativePointer != 0) {
            int nativeRelease = nativeRelease(this.mNativePointer);
            this.mNativePointer = 0L;
            return nativeRelease;
        }
        return 0;
    }
}
