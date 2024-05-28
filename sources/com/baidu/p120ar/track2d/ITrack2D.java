package com.baidu.p120ar.track2d;

import android.graphics.Bitmap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.track2d.ITrack2D */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ITrack2D {
    Bitmap getTargetBitmap();

    void haltTrack();

    void resumeTrack();

    void setStateChangedListener(ITrack2DStateChangedListener iTrack2DStateChangedListener);
}
