package com.webrtc;

import android.os.Build;
import com.webrtc.VideoFrame;
import java.nio.ByteBuffer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NV21Buffer implements VideoFrame.Buffer {
    private final byte[] data;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final boolean uvSwapped = Build.MODEL.contains("SABRESD-MX6DQ");
    private final int width;

    private static native void nativeCropAndScale(int i, int i2, int i3, int i4, int i5, int i6, byte[] bArr, int i7, int i8, ByteBuffer byteBuffer, int i9, ByteBuffer byteBuffer2, int i10, ByteBuffer byteBuffer3, int i11);

    public NV21Buffer(byte[] bArr, int i, int i2, Runnable runnable) {
        this.data = bArr;
        this.width = i;
        this.height = i2;
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
        if (this.uvSwapped) {
            nativeCropAndScale(i, i2, i3, i4, i5, i6, this.data, this.width, this.height, allocate.getDataY(), allocate.getStrideY(), allocate.getDataV(), allocate.getStrideV(), allocate.getDataU(), allocate.getStrideU());
        } else {
            nativeCropAndScale(i, i2, i3, i4, i5, i6, this.data, this.width, this.height, allocate.getDataY(), allocate.getStrideY(), allocate.getDataU(), allocate.getStrideU(), allocate.getDataV(), allocate.getStrideV());
        }
        return allocate;
    }
}
