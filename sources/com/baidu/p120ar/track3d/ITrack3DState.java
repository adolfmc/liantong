package com.baidu.p120ar.track3d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.track3d.ITrack3DState */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ITrack3DState {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.track3d.ITrack3DState$DistanceState */
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
