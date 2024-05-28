package com.megvii.lv5.lib.jni;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MegBlur {
    public static native long nativeInit(int i, int i2);

    public static native void nativeProcess(long j, int i, int i2);

    public static native void nativeSetBeautify(long j, float f, float f2);

    public static native void nativeSetBlendStrength(long j, float f);

    public static native void nativeSetBlur(long j, int i, float f);

    public static native void nativeSetCloring(long j, float f, int i, int i2, int i3);

    public static native void nativeSetColorContour(long j, int i, int i2, int i3, float f);

    public static native void nativeSetTextureContour(long j, int i, int i2, int i3);

    public static native void nativeSetTextureMaskBlur(long j, int i, int i2, int i3);

    public static native void nativeSetTextureMaskColor(long j, int i, int i2, int i3);

    public static native void nativeSetTransformContour(long j, int i, int i2, float f);

    public static native void nativeSetTransformMaskBlur(long j, int i, int i2, float f);

    public static native void nativeSetTransformMaskColor(long j, int i, int i2, float f);
}
