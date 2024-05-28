package org.webrtc;

import android.support.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EncodedImage implements RefCounted {
    public final ByteBuffer buffer;
    public final long captureTimeMs;
    public final long captureTimeNs;
    public final boolean completeFrame;
    public final int encodedHeight;
    public final int encodedWidth;
    public final FrameType frameType;
    @Nullable

    /* renamed from: qp */
    public final Integer f27431qp;
    private final RefCountDelegate refCountDelegate;
    public final int rotation;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum FrameType {
        EmptyFrame(0),
        VideoFrameKey(3),
        VideoFrameDelta(4);
        
        private final int nativeIndex;

        FrameType(int i) {
            this.nativeIndex = i;
        }

        public int getNative() {
            return this.nativeIndex;
        }

        @CalledByNative("FrameType")
        static FrameType fromNativeIndex(int i) {
            FrameType[] values;
            for (FrameType frameType : values()) {
                if (frameType.getNative() == i) {
                    return frameType;
                }
            }
            throw new IllegalArgumentException("Unknown native frame type: " + i);
        }
    }

    @Override // org.webrtc.RefCounted
    public void retain() {
        this.refCountDelegate.retain();
    }

    @Override // org.webrtc.RefCounted
    public void release() {
        this.refCountDelegate.release();
    }

    @CalledByNative
    private EncodedImage(ByteBuffer byteBuffer, @Nullable Runnable runnable, int i, int i2, long j, FrameType frameType, int i3, boolean z, @Nullable Integer num) {
        this.buffer = byteBuffer;
        this.encodedWidth = i;
        this.encodedHeight = i2;
        this.captureTimeMs = TimeUnit.NANOSECONDS.toMillis(j);
        this.captureTimeNs = j;
        this.frameType = frameType;
        this.rotation = i3;
        this.completeFrame = z;
        this.f27431qp = num;
        this.refCountDelegate = new RefCountDelegate(runnable);
    }

    @CalledByNative
    private ByteBuffer getBuffer() {
        return this.buffer;
    }

    @CalledByNative
    private int getEncodedWidth() {
        return this.encodedWidth;
    }

    @CalledByNative
    private int getEncodedHeight() {
        return this.encodedHeight;
    }

    @CalledByNative
    private long getCaptureTimeNs() {
        return this.captureTimeNs;
    }

    @CalledByNative
    private int getFrameType() {
        return this.frameType.getNative();
    }

    @CalledByNative
    private int getRotation() {
        return this.rotation;
    }

    @CalledByNative
    private boolean getCompleteFrame() {
        return this.completeFrame;
    }

    @CalledByNative
    @Nullable
    private Integer getQp() {
        return this.f27431qp;
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Builder {
        private ByteBuffer buffer;
        private long captureTimeNs;
        private boolean completeFrame;
        private int encodedHeight;
        private int encodedWidth;
        private FrameType frameType;
        @Nullable

        /* renamed from: qp */
        private Integer f27432qp;
        @Nullable
        private Runnable releaseCallback;
        private int rotation;

        private Builder() {
        }

        public Builder setBuffer(ByteBuffer byteBuffer, @Nullable Runnable runnable) {
            this.buffer = byteBuffer;
            this.releaseCallback = runnable;
            return this;
        }

        public Builder setEncodedWidth(int i) {
            this.encodedWidth = i;
            return this;
        }

        public Builder setEncodedHeight(int i) {
            this.encodedHeight = i;
            return this;
        }

        @Deprecated
        public Builder setCaptureTimeMs(long j) {
            this.captureTimeNs = TimeUnit.MILLISECONDS.toNanos(j);
            return this;
        }

        public Builder setCaptureTimeNs(long j) {
            this.captureTimeNs = j;
            return this;
        }

        public Builder setFrameType(FrameType frameType) {
            this.frameType = frameType;
            return this;
        }

        public Builder setRotation(int i) {
            this.rotation = i;
            return this;
        }

        public Builder setCompleteFrame(boolean z) {
            this.completeFrame = z;
            return this;
        }

        public Builder setQp(@Nullable Integer num) {
            this.f27432qp = num;
            return this;
        }

        public EncodedImage createEncodedImage() {
            return new EncodedImage(this.buffer, this.releaseCallback, this.encodedWidth, this.encodedHeight, this.captureTimeNs, this.frameType, this.rotation, this.completeFrame, this.f27432qp);
        }
    }
}
