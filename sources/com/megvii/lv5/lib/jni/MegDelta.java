package com.megvii.lv5.lib.jni;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MegDelta {
    public static native boolean checkExposure(byte[] bArr, int i, int i2, int i3, int i4);

    public static native boolean checkWhite(byte[] bArr, int i, int i2, int i3, int i4);

    public static native byte[] decodeParameter(boolean z, String str, byte[] bArr);

    public static native byte[] encodeDelta(byte[] bArr);

    public static native byte[] encodeParameter(boolean z, String str, byte[] bArr);
}
