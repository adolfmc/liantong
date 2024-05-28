package com.baidu.rtc.recorder;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface IRtcMediaRecorder {
    boolean isReleased();

    void release();

    void setMediaRecorderCallback(BRTCMediaRecorderCallback bRTCMediaRecorderCallback);

    int startRecording(BRTCMediaRecorderParams bRTCMediaRecorderParams);

    int stopRecording();
}
