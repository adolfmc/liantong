package com.example.asus.detectionandalign.listener;

import android.graphics.Bitmap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ResultListener {
    void captureSuccess(Bitmap bitmap);

    void onFaceImageCaptured(String str);

    void onSDKUsingFail(String str, String str2);
}
