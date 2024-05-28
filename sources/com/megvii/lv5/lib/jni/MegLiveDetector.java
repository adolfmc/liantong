package com.megvii.lv5.lib.jni;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MegLiveDetector {

    /* renamed from: a */
    public static MegLiveDetector f12866a;

    /* renamed from: a */
    public static MegLiveDetector m13440a() {
        if (f12866a == null) {
            f12866a = new MegLiveDetector();
        }
        return f12866a;
    }

    public native synchronized boolean doActionVideoRecord(long j);

    public native synchronized void enableWhiteBalance(long j, boolean z);

    public native synchronized int getActionCurrentIndex(long j);

    public native synchronized int getActionCurrentType(long j);

    public native synchronized int getActionFailedType(long j);

    public native synchronized int getBestImageActionIndex(long j);

    public native synchronized int getCurrentStep(long j);

    public native synchronized byte[] getDelta(long j, String str, boolean z, boolean z2, String str2, String str3, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, int i, int i2);

    public native synchronized byte[] getFarMirrorImage(long j);

    public native synchronized byte[] getImageBest(long j);

    public native synchronized int getLiveFailedType(long j);

    public native synchronized float getLiveProgress(long j);

    public native synchronized float getMoveProgress(long j);

    public native synchronized String getPassLivenessQualityInfoJson(long j);

    public native synchronized String getPassMirrorQualityInfoJson(long j);

    public native synchronized int getQualityErrorType(long j);

    public native synchronized long nativeCreateHandle(int i, double d, double d2, int i2, boolean z, int i3, int i4, int[] iArr, String str, String str2, int i5, long j, long j2, long j3, long j4, float f, String str3, float f2, float f3, String str4, int i6, boolean z2, boolean z3, int i7, float f4, int i8, int i9);

    public native synchronized void nativeLiveDetect(long j, byte[] bArr, int i, int i2, int i3, boolean z, float f, boolean z2, boolean z3);

    public native synchronized boolean nativeLoadModel(long j, byte[] bArr, byte[] bArr2, byte[] bArr3);

    public native synchronized void nativeRelease(long j);

    public native synchronized void nativeResetAction(long j, int[] iArr);

    public native synchronized void nativeResetLiveDetect(long j);

    public native synchronized void nativeSetActionBlockConfig(long j, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8);

    public native synchronized void nativeSetLiveConfig(long j, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, int i, int i2, boolean z, float f20);

    public native synchronized void nativeStartLiveDetect(long j);

    public native synchronized void nativeStopLiveDetect(long j);

    public native synchronized void setActionBestImage(long j, byte[] bArr, int i, int i2, int i3, int i4);

    public native synchronized void setBadImageTypeArray(long j, int i, int[] iArr);

    public native synchronized void setPrivate(long j, boolean z);
}
