package com.baidu.rtc.recorder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public interface MediaDataCallback {
    void addTrack(boolean z, MediaFormat mediaFormat);

    void onStop(boolean z);

    void writeSampleData(boolean z, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo);

    void writeSampleData(byte[] bArr, MediaCodec.BufferInfo bufferInfo);
}
