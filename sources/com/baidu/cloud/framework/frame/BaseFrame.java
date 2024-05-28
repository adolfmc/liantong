package com.baidu.cloud.framework.frame;

import android.media.MediaCodec;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BaseFrame {
    public ByteBuffer buffer;
    public int flag;
    public int offset;
    public long ptsUs;
    public int size;

    public String toString() {
        return "BaseFrame";
    }

    public BaseFrame() {
    }

    public BaseFrame(ByteBuffer byteBuffer, long j, int i) {
        this.buffer = byteBuffer;
        this.ptsUs = j;
        this.size = i;
    }

    public BaseFrame(byte[] bArr, long j, int i) {
        this.buffer = ByteBuffer.allocateDirect(i);
        this.buffer.order(ByteOrder.nativeOrder()).put(bArr).position(0);
        this.ptsUs = j;
        this.size = i;
    }

    public MediaCodec.BufferInfo toBufferInfo() {
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.set(this.offset, this.size, this.ptsUs, this.flag);
        return bufferInfo;
    }
}
