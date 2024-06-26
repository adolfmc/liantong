package com.baidu.p120ar.face.algo;

import android.content.res.AssetManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.algo.FaceJniClient */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FaceJniClient {
    public static native FaceAlgoData animateFace(long j, FaceAlgoData faceAlgoData, long j2);

    public static native long createAnimateCore(String[] strArr);

    public static native long createAnimateCoreFromAssetDir(String[] strArr);

    public static native long createDetectCore(String[] strArr);

    public static native long createDetectCoreFromAssetDir(String[] strArr);

    public static native long createFrame(FAUImage fAUImage);

    public static native long createTrackCore(String[] strArr);

    public static native long createTrackCoreFromAssetDir(String[] strArr);

    public static native boolean destoryFrame(long j);

    public static native FaceAlgoData detectFace(long j, FaceAlgoData faceAlgoData, long j2);

    public static native String getFaceAlgoVersion();

    public static native boolean releaseAnimateCore(long j);

    public static native boolean releaseDetectCore(long j);

    public static native boolean releaseTrackCore(long j);

    public static native int setAssetManager(AssetManager assetManager);

    public static native FaceAlgoData trackFace(long j, FaceAlgoData faceAlgoData, long j2);
}
