package com.baidu.p120ar.capture;

import android.graphics.Bitmap;
import com.baidu.p120ar.callback.ICallbackWith;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.ICapture */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ICapture {
    void capture(ICallbackWith<ICaptureResult> iCallbackWith);

    void sendBase64ImageToLua(String... strArr);

    void sendImageToLua(Bitmap... bitmapArr);

    void setAbilityListener(ICaptureAbilityListener iCaptureAbilityListener);

    void setCaptureCallback(ICallbackWith<ICaptureResult> iCallbackWith);
}
