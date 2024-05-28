package com.mabeijianxi.smallvideorecord2;

import com.mabeijianxi.smallvideorecord2.model.MediaObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IMediaRecorder {
    void onAudioError(int i, String str);

    void receiveAudioData(byte[] bArr, int i);

    MediaObject.MediaPart startRecord();

    void stopRecord();
}
