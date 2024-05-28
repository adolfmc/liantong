package com.mabeijianxi.smallvideorecord2.jniinterface;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FFmpegBridge {
    public static final int ALL_RECORD_END = 1;
    public static final int ROTATE_0_CROP_LF = 0;
    public static final int ROTATE_180 = 2;
    public static final int ROTATE_270_CROP_LT_MIRROR_LR = 3;
    public static final int ROTATE_90_CROP_LT = 1;
    private static ArrayList<FFmpegStateListener> listeners = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface FFmpegStateListener {
        void allRecordEnd();
    }

    public static native int encodeFrame2AAC(byte[] bArr);

    public static native int encodeFrame2H264(byte[] bArr);

    public static native String getFFmpegConfig();

    public static native void initJXFFmpeg(boolean z, String str);

    private static native int jxCMDRun(String[] strArr);

    public static native void nativeRelease();

    public static native int prepareJXFFmpegEncoder(String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native int recordEnd();

    static {
        System.loadLibrary("avutil");
        System.loadLibrary("fdk-aac");
        System.loadLibrary("avcodec");
        System.loadLibrary("avformat");
        System.loadLibrary("swscale");
        System.loadLibrary("swresample");
        System.loadLibrary("avfilter");
        System.loadLibrary("jx_ffmpeg_jni");
    }

    public static int jxFFmpegCMDRun(String str) {
        return jxCMDRun(str.split("[ \\t]+"));
    }

    public static synchronized void notifyState(int i, float f) {
        synchronized (FFmpegBridge.class) {
            Iterator<FFmpegStateListener> it = listeners.iterator();
            while (it.hasNext()) {
                FFmpegStateListener next = it.next();
                if (next != null && i == 1) {
                    next.allRecordEnd();
                }
            }
        }
    }

    public static void registFFmpegStateListener(FFmpegStateListener fFmpegStateListener) {
        if (listeners.contains(fFmpegStateListener)) {
            return;
        }
        listeners.add(fFmpegStateListener);
    }

    public static void unRegistFFmpegStateListener(FFmpegStateListener fFmpegStateListener) {
        if (listeners.contains(fFmpegStateListener)) {
            listeners.remove(fFmpegStateListener);
        }
    }
}
