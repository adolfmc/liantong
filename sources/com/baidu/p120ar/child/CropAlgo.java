package com.baidu.p120ar.child;

import com.baidu.p120ar.libloader.LibLoader;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.child.CropAlgo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CropAlgo {
    public native int nativeClear();

    public native byte[] nativeCorpFace(ChildFaceCropInputModel childFaceCropInputModel);

    public native float[] nativeGetFaceBoxList(long j);

    public native float[] nativeTrackingPoints(long j);

    public native long nativeWriteCameraDataToHandel(long j, byte[] bArr, int i, int i2, float f);

    public native long nativeWriteFaceDataToHandel(long j, byte[] bArr);

    public native void nativeWriteTypeToHandle(long j);

    static {
        LibLoader.require("cropface");
        LibLoader.require("childCropFace");
    }
}
