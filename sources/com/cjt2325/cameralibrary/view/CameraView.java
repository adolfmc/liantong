package com.cjt2325.cameralibrary.view;

import android.graphics.Bitmap;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface CameraView {
    void confirmState(int i);

    boolean handlerFoucs(float f, float f2);

    void playVideo(Bitmap bitmap, String str);

    void resetState(int i);

    void setTip(String str);

    void showPicture(Bitmap bitmap, boolean z);

    void startPreviewCallback();

    void stopVideo();
}
