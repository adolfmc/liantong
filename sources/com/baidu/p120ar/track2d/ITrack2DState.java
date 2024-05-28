package com.baidu.p120ar.track2d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.track2d.ITrack2DState */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ITrack2DState {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.track2d.ITrack2DState$DistanceState */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum DistanceState {
        NORMAL,
        TOO_FAR,
        TOO_NEAR,
        UNKNOWN
    }

    DistanceState distanceState();

    boolean isModelAppear();

    boolean isShowImmediately();

    boolean isTrackFound();
}
