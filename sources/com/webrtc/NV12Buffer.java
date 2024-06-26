package com.webrtc;

import android.support.annotation.Nullable;
import com.webrtc.VideoFrame;
import java.nio.ByteBuffer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NV12Buffer implements VideoFrame.Buffer {
    private final ByteBuffer buffer;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final int sliceHeight;
    private final int stride;
    private final int width;

    private static native void nativeCropAndScale(int i, int i2, int i3, int i4, int i5, int i6, ByteBuffer byteBuffer, int i7, int i8, int i9, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, ByteBuffer byteBuffer4, int i13);

    public NV12Buffer(int i, int i2, int i3, int i4, ByteBuffer byteBuffer, @Nullable Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.stride = i3;
        this.sliceHeight = i4;
        this.buffer = byteBuffer;
        this.refCountDelegate = new RefCountDelegate(runnable);
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        int i = this.width;
        int i2 = this.height;
        return (VideoFrame.I420Buffer) cropAndScale(0, 0, i, i2, i, i2);
    }

    @Override // com.webrtc.VideoFrame.Buffer, com.webrtc.RefCounted
    public void retain() {
        this.refCountDelegate.retain();
    }

    @Override // com.webrtc.VideoFrame.Buffer, com.webrtc.RefCounted
    public void release() {
        this.refCountDelegate.release();
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        JavaI420Buffer allocate = JavaI420Buffer.allocate(i5, i6);
        nativeCropAndScale(i, i2, i3, i4, i5, i6, this.buffer, this.width, this.height, this.stride, this.sliceHeight, allocate.getDataY(), allocate.getStrideY(), allocate.getDataU(), allocate.getStrideU(), allocate.getDataV(), allocate.getStrideV());
        return allocate;
    }
}
