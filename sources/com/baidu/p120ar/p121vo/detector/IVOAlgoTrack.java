package com.baidu.p120ar.p121vo.detector;

import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.callback.ICallbackWith;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.vo.detector.IVOAlgoTrack */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IVOAlgoTrack {
    void init();

    void release();

    void track(FramePixels framePixels, ICallbackWith<VOResult> iCallbackWith);
}
