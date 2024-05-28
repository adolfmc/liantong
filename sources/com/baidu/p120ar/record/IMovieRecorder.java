package com.baidu.p120ar.record;

import android.content.Context;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.record.IMovieRecorder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IMovieRecorder {
    void onAudioFrameAvailable(ByteBuffer byteBuffer, int i, long j);

    void onDestroy();

    void onVideoFrameAvailable(long j);

    void startRecorder(Context context, EncoderParams encoderParams, MovieRecorderCallback movieRecorderCallback);

    void stopRecorder();
}
