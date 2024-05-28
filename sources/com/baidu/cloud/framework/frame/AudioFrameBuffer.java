package com.baidu.cloud.framework.frame;

import android.media.MediaCodec;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioFrameBuffer extends BaseFrame {
    public int audioFormat;
    public int channelCount;
    public int sampleRate;

    @Override // com.baidu.cloud.framework.frame.BaseFrame
    public String toString() {
        return "AFrame";
    }

    public AudioFrameBuffer() {
    }

    public AudioFrameBuffer(ByteBuffer byteBuffer, long j, int i) {
        this.buffer = byteBuffer;
        this.ptsUs = j;
        this.size = i;
    }

    public AudioFrameBuffer(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        this.buffer = byteBuffer;
        this.ptsUs = bufferInfo.presentationTimeUs;
        this.size = bufferInfo.size;
        this.offset = bufferInfo.offset;
        this.flag = bufferInfo.flags;
    }
}
