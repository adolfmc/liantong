package com.baidu.rtc;

import com.webrtc.JniCommon;
import com.webrtc.RefCounted;
import com.webrtc.VideoFrame;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCI420Buffer implements VideoFrame.I420Buffer {
    static Class mCropClass;
    static Method mCropMethod;
    private final ByteBuffer dataU;
    private final ByteBuffer dataV;
    private final ByteBuffer dataY;
    private final int height;
    private final RefCountDelegate refCountDelegate;
    private final int strideU;
    private final int strideV;
    private final int strideY;
    private final int width;

    static {
        Method method;
        Method method2 = null;
        try {
            Class<?> cls = Class.forName("com.webrtc.VideoFrame");
            try {
                mCropClass = cls;
                method = cls.getMethod("cropAndScaleI420", VideoFrame.I420Buffer.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                method = null;
            }
            if (method != null) {
                mCropMethod = method;
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        if (mCropMethod == null) {
            try {
                Class<?> cls2 = Class.forName("com.webrtc.JavaI420Buffer");
                try {
                    mCropClass = cls2;
                    method2 = cls2.getMethod("cropAndScaleI420", VideoFrame.I420Buffer.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE);
                } catch (NoSuchMethodException e3) {
                    e3.printStackTrace();
                }
                if (method2 != null) {
                    mCropMethod = method2;
                }
            } catch (ClassNotFoundException e4) {
                e4.printStackTrace();
            }
        }
    }

    private RTCI420Buffer(int i, int i2, ByteBuffer byteBuffer, int i3, ByteBuffer byteBuffer2, int i4, ByteBuffer byteBuffer3, int i5, Runnable runnable) {
        this.width = i;
        this.height = i2;
        this.dataY = byteBuffer;
        this.dataU = byteBuffer2;
        this.dataV = byteBuffer3;
        this.strideY = i3;
        this.strideU = i4;
        this.strideV = i5;
        this.refCountDelegate = new RefCountDelegate(runnable);
    }

    private static void checkCapacity(ByteBuffer byteBuffer, int i, int i2, int i3) {
        int i4 = (i3 * (i2 - 1)) + i;
        if (byteBuffer.capacity() >= i4) {
            return;
        }
        throw new IllegalArgumentException("Buffer must be at least " + i4 + " bytes, but was " + byteBuffer.capacity());
    }

    public static RTCI420Buffer wrap(int i, int i2, ByteBuffer byteBuffer, int i3, ByteBuffer byteBuffer2, int i4, ByteBuffer byteBuffer3, int i5, Runnable runnable) {
        if (byteBuffer != null && byteBuffer2 != null && byteBuffer3 != null) {
            if (byteBuffer.isDirect() && byteBuffer2.isDirect() && byteBuffer3.isDirect()) {
                ByteBuffer slice = byteBuffer.slice();
                ByteBuffer slice2 = byteBuffer2.slice();
                ByteBuffer slice3 = byteBuffer3.slice();
                int i6 = (i + 1) / 2;
                int i7 = (i2 + 1) / 2;
                checkCapacity(slice, i, i2, i3);
                checkCapacity(slice2, i6, i7, i4);
                checkCapacity(slice3, i6, i7, i5);
                return new RTCI420Buffer(i, i2, slice, i3, slice2, i4, slice3, i5, runnable);
            }
            throw new IllegalArgumentException("Data buffers must be direct byte buffers.");
        }
        throw new IllegalArgumentException("Data buffers cannot be null.");
    }

    public static RTCI420Buffer allocate(int i, int i2) {
        int i3 = (i2 + 1) / 2;
        int i4 = (i + 1) / 2;
        int i5 = i * i2;
        int i6 = i5 + 0;
        int i7 = i4 * i3;
        int i8 = i6 + i7;
        final ByteBuffer nativeAllocateByteBuffer = JniCommon.nativeAllocateByteBuffer(i5 + (i4 * 2 * i3));
        nativeAllocateByteBuffer.position(0);
        nativeAllocateByteBuffer.limit(i6);
        ByteBuffer slice = nativeAllocateByteBuffer.slice();
        nativeAllocateByteBuffer.position(i6);
        nativeAllocateByteBuffer.limit(i8);
        ByteBuffer slice2 = nativeAllocateByteBuffer.slice();
        nativeAllocateByteBuffer.position(i8);
        nativeAllocateByteBuffer.limit(i8 + i7);
        return new RTCI420Buffer(i, i2, slice, i, slice2, i4, nativeAllocateByteBuffer.slice(), i4, new Runnable() { // from class: com.baidu.rtc.-$$Lambda$RTCI420Buffer$MVKNXKaN7jHpOaqK1ceHPyNb6Ts
            @Override // java.lang.Runnable
            public final void run() {
                JniCommon.nativeFreeByteBuffer(nativeAllocateByteBuffer);
            }
        });
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public int getWidth() {
        return this.width;
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public int getHeight() {
        return this.height;
    }

    @Override // com.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataY() {
        return this.dataY.slice();
    }

    @Override // com.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataU() {
        return this.dataU.slice();
    }

    @Override // com.webrtc.VideoFrame.I420Buffer
    public ByteBuffer getDataV() {
        return this.dataV.slice();
    }

    @Override // com.webrtc.VideoFrame.I420Buffer
    public int getStrideY() {
        return this.strideY;
    }

    @Override // com.webrtc.VideoFrame.I420Buffer
    public int getStrideU() {
        return this.strideU;
    }

    @Override // com.webrtc.VideoFrame.I420Buffer
    public int getStrideV() {
        return this.strideV;
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public VideoFrame.I420Buffer toI420() {
        retain();
        return this;
    }

    @Override // com.webrtc.VideoFrame.Buffer, com.webrtc.RefCounted
    public void retain() {
        this.refCountDelegate.retain();
    }

    @Override // com.webrtc.VideoFrame.Buffer, com.webrtc.RefCounted
    public void release() {
        this.refCountDelegate.release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class RefCountDelegate implements RefCounted {
        private final AtomicInteger refCount = new AtomicInteger(1);
        private final Runnable releaseCallback;

        public RefCountDelegate(Runnable runnable) {
            this.releaseCallback = runnable;
        }

        @Override // com.webrtc.RefCounted
        public void retain() {
            this.refCount.incrementAndGet();
        }

        @Override // com.webrtc.RefCounted
        public void release() {
            Runnable runnable;
            if (this.refCount.decrementAndGet() != 0 || (runnable = this.releaseCallback) == null) {
                return;
            }
            runnable.run();
        }
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        try {
            if (mCropMethod != null) {
                return (VideoFrame.Buffer) mCropMethod.invoke(mCropClass, this, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5), Integer.valueOf(i6));
            }
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
