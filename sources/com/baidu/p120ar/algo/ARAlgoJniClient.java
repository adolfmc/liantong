package com.baidu.p120ar.algo;

import com.baidu.p120ar.libloader.LibLoader;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.algo.ARAlgoJniClient */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARAlgoJniClient {
    static volatile ARAlgoJniClient arAlgoJniClient;
    private long trackSystemHandler = 0;

    public static native int calModelPosition(float[] fArr, float f, float[] fArr2, float[] fArr3);

    private native int nativeAddTracker2D(long j, String str);

    private native int nativeAddTracker3D(long j, String str, int i);

    private native int nativeAddTrackerVO(long j, String str);

    private native int nativeAddTrackerVPS(long j, String str);

    private native int nativeCreateTrackingSystem(long j, int i, int i2, float[] fArr, float[] fArr2);

    private native int nativeGet2dMarkerSize(long j, int i, int[] iArr);

    private native int nativeGetModelPose(long j, int i, String str, float[] fArr);

    private native int nativeGetTrackerPose(long j, int i, float[] fArr);

    private static native String nativeGetVersion();

    private native long nativeInitTrackerSystem();

    private native int nativeInsertModel(long j, int i, int i2, int i3, String str, float f, float[] fArr);

    private native int nativeRelease(long j);

    private native int nativeRemoveAllModels(long j, int i);

    private native int nativeRemoveAllTrackers(long j);

    private native int nativeRemoveModel(long j, int i, String str);

    private native int nativeRemoveTracker(long j, int i);

    private native int nativeResetAllTrackers(long j);

    private native int nativeResetTracker(long j, int i);

    private native int nativeTrackFrame(long j, byte[] bArr, double d, float[] fArr, FrameType frameType);

    private native int nativeTrackFrameByteBuffer(long j, ByteBuffer byteBuffer, double d, float[] fArr, FrameType frameType);

    private native int nativeVpsServerReceiver(long j, int i, byte[] bArr);

    static {
        LibLoader.require("module_basic");
    }

    public static ARAlgoJniClient getAlgoInstance() {
        if (arAlgoJniClient == null) {
            synchronized (ARAlgoJniClient.class) {
                if (arAlgoJniClient == null) {
                    arAlgoJniClient = new ARAlgoJniClient();
                }
            }
        }
        return arAlgoJniClient;
    }

    public static String getVersion() {
        return nativeGetVersion();
    }

    public int createTrackingSystem(int i, int i2, float[] fArr, float[] fArr2) {
        this.trackSystemHandler = nativeInitTrackerSystem();
        return nativeCreateTrackingSystem(this.trackSystemHandler, i, i2, fArr, fArr2);
    }

    public int addTrackerVO(String str) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeAddTrackerVO(j, str);
    }

    public int addTrackerVPS(String str) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeAddTrackerVPS(j, str);
    }

    public int addTracker2D(String str) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeAddTracker2D(j, str);
    }

    public int addTracker3D(String str, int i) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeAddTracker3D(j, str, i);
    }

    public int trackFrame(byte[] bArr, double d, float[] fArr, FrameType frameType) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeTrackFrame(j, bArr, d, fArr, frameType);
    }

    public int trackFrame(ByteBuffer byteBuffer, double d, float[] fArr, FrameType frameType) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeTrackFrameByteBuffer(j, byteBuffer, d, fArr, frameType);
    }

    public int getTrackerPose(int i, float[] fArr) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeGetTrackerPose(j, i, fArr);
    }

    public int removeTracker(int i) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeRemoveTracker(j, i);
    }

    public int removeAllTrackers() {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeRemoveAllTrackers(j);
    }

    public int resetTracker(int i) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeResetTracker(j, i);
    }

    public int resetAllTrackers() {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeResetAllTrackers(j);
    }

    public int release() {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return 0;
        }
        int nativeRelease = nativeRelease(j);
        this.trackSystemHandler = 0L;
        return nativeRelease;
    }

    public int insertModel(int i, int i2, int i3, String str, float f, float[] fArr) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeInsertModel(j, i, i2, i3, str, f, fArr);
    }

    public int removeModel(int i, String str) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeRemoveModel(j, i, str);
    }

    public int removeAllModels(int i) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeRemoveAllModels(j, i);
    }

    public int getModelPose(int i, String str, float[] fArr) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeGetModelPose(j, i, str, fArr);
    }

    public int get2DMarkerSize(int i, int[] iArr) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeGet2dMarkerSize(j, i, iArr);
    }

    public int vpsServerReceiver(int i, byte[] bArr) {
        long j = this.trackSystemHandler;
        if (j == 0) {
            return -2;
        }
        return nativeVpsServerReceiver(j, i, bArr);
    }
}
