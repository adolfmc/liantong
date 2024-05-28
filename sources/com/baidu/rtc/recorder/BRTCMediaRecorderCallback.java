package com.baidu.rtc.recorder;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface BRTCMediaRecorderCallback {
    public static final int RECORDER_CODE_ERROR_ENGINE_RELEASED = -104;
    public static final int RECORDER_CODE_ERROR_INITIALIZED = -101;
    public static final int RECORDER_CODE_ERROR_MUXER_START = -108;
    public static final int RECORDER_CODE_ERROR_MUXER_STOP = -107;
    public static final int RECORDER_CODE_ERROR_OVER_MAX_DURATION = -102;
    public static final int RECORDER_CODE_ERROR_PARAMS_INVALID = -100;
    public static final int RECORDER_CODE_ERROR_RECORDER_RELEASED = -106;
    public static final int RECORDER_CODE_ERROR_RECORDING_STATE = -103;
    public static final int RECORDER_CODE_ERROR_WRITE_FAILED = -105;
    public static final int RECORDER_CODE_SUCCESS = 0;
    public static final int RECORDER_STATE_ERROR = -1;
    public static final int RECORDER_STATE_START = 1;
    public static final int RECORDER_STATE_STOP = 2;

    void onRecordInfoUpdate(long j, long j2);

    void onRecordStateChanged(int i, int i2);
}
