package com.baidu.p120ar.track3d;

import android.graphics.Bitmap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.track3d.ITrack3D */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ITrack3D {
    Bitmap getTargetBitmap();

    void haltTrack();

    void resumeTrack();

    void setStateChangedListener(ITrack3DStateChangedListener iTrack3DStateChangedListener);
}
