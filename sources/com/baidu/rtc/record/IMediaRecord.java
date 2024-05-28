package com.baidu.rtc.record;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface IMediaRecord {
    void startRecording(String str, MediaEncodeParams mediaEncodeParams, RecorderCallback recorderCallback);

    void stopRecording();
}
