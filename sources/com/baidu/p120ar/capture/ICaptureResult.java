package com.baidu.p120ar.capture;

import android.graphics.Bitmap;
import com.baidu.p120ar.face.IFaceResultData;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.ICaptureResult */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ICaptureResult {
    IFaceResultData getFaceData();

    Bitmap getOriginPhoto();

    Bitmap getOutputPhoto();

    int getPhotoHeight();

    int getPhotoWidth();

    long getTimestamp();

    void release();
}
