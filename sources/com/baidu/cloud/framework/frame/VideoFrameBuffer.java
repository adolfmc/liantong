package com.baidu.cloud.framework.frame;

import android.media.MediaCodec;
import android.support.annotation.Nullable;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoFrameBuffer extends BaseFrame {
    @Nullable
    public byte[] data;
    public int height;
    public PIXEL_FORMAT pixelFormat;
    public int rotation;
    @Nullable
    public SurfaceBuffer surfaceBuffer;
    @Nullable
    public TextureBuffer textureBuffer;
    public long timestampNs;
    public float[] transformMatrix;
    public int width;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum PIXEL_FORMAT {
        TEXTURE,
        SURFACE,
        YUV
    }

    @Override // com.baidu.cloud.framework.frame.BaseFrame
    public String toString() {
        return "VFrame";
    }

    public VideoFrameBuffer() {
    }

    public VideoFrameBuffer(ByteBuffer byteBuffer, long j, int i) {
        this.buffer = byteBuffer;
        this.ptsUs = j;
        this.size = i;
    }

    public VideoFrameBuffer(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        this.buffer = byteBuffer;
        this.ptsUs = bufferInfo.presentationTimeUs;
        this.size = bufferInfo.size;
        this.offset = bufferInfo.offset;
        this.flag = bufferInfo.flags;
    }
}
