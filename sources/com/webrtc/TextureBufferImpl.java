package com.webrtc;

import android.graphics.Matrix;
import android.os.Handler;
import android.support.annotation.Nullable;
import com.webrtc.VideoFrame;
import java.util.concurrent.Callable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TextureBufferImpl implements VideoFrame.TextureBuffer {
    private final int height;

    /* renamed from: id */
    private final int f21267id;
    private final RefCountDelegate refCountDelegate;
    private final RefCountMonitor refCountMonitor;
    private final Handler toI420Handler;
    private final Matrix transformMatrix;
    private final VideoFrame.TextureBuffer.Type type;
    private final int unscaledHeight;
    private final int unscaledWidth;
    private final int width;
    private final YuvConverter yuvConverter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface RefCountMonitor {
        void onDestroy(TextureBufferImpl textureBufferImpl);

        void onRelease(TextureBufferImpl textureBufferImpl);

        void onRetain(TextureBufferImpl textureBufferImpl);
    }

    public TextureBufferImpl(int i, int i2, VideoFrame.TextureBuffer.Type type, int i3, Matrix matrix, Handler handler, YuvConverter yuvConverter, @Nullable final Runnable runnable) {
        this(i, i2, i, i2, type, i3, matrix, handler, yuvConverter, new RefCountMonitor() { // from class: com.webrtc.TextureBufferImpl.1
            @Override // com.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRelease(TextureBufferImpl textureBufferImpl) {
            }

            @Override // com.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRetain(TextureBufferImpl textureBufferImpl) {
            }

            @Override // com.webrtc.TextureBufferImpl.RefCountMonitor
            public void onDestroy(TextureBufferImpl textureBufferImpl) {
                Runnable runnable2 = runnable;
                if (runnable2 != null) {
                    runnable2.run();
                }
            }
        });
    }

    TextureBufferImpl(int i, int i2, VideoFrame.TextureBuffer.Type type, int i3, Matrix matrix, Handler handler, YuvConverter yuvConverter, RefCountMonitor refCountMonitor) {
        this(i, i2, i, i2, type, i3, matrix, handler, yuvConverter, refCountMonitor);
    }

    private TextureBufferImpl(int i, int i2, int i3, int i4, VideoFrame.TextureBuffer.Type type, int i5, Matrix matrix, Handler handler, YuvConverter yuvConverter, final RefCountMonitor refCountMonitor) {
        this.unscaledWidth = i;
        this.unscaledHeight = i2;
        this.width = i3;
        this.height = i4;
        this.type = type;
        this.f21267id = i5;
        this.transformMatrix = matrix;
        this.toI420Handler = handler;
        this.yuvConverter = yuvConverter;
        this.refCountDelegate = new RefCountDelegate(new Runnable() { // from class: com.webrtc.-$$Lambda$TextureBufferImpl$-UlirL6oF8hVMYwtW4i7_-lak2Y
            @Override // java.lang.Runnable
            public final void run() {
                refCountMonitor.onDestroy(TextureBufferImpl.this);
            }
        });
        this.refCountMonitor = refCountMonitor;
    }

    @Override // com.webrtc.VideoFrame.TextureBuffer
    public VideoFrame.TextureBuffer.Type getType() {
        return this.type;
    }

    @Override // com.webrtc.VideoFrame.TextureBuffer
    public int getTextureId() {
        return this.f21267id;
    }

    @Override // com.webrtc.VideoFrame.TextureBuffer
    public Matrix getTransformMatrix() {
        return this.transformMatrix;
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
        return (VideoFrame.I420Buffer) ThreadUtils.invokeAtFrontUninterruptibly(this.toI420Handler, new Callable() { // from class: com.webrtc.-$$Lambda$TextureBufferImpl$OXwYoyij7oNTi90tWxF1X2khFxc
            @Override // java.util.concurrent.Callable
            public final Object call() {
                VideoFrame.I420Buffer convert;
                convert = r0.yuvConverter.convert(TextureBufferImpl.this);
                return convert;
            }
        });
    }

    @Override // com.webrtc.VideoFrame.Buffer, com.webrtc.RefCounted
    public void retain() {
        this.refCountMonitor.onRetain(this);
        this.refCountDelegate.retain();
    }

    @Override // com.webrtc.VideoFrame.Buffer, com.webrtc.RefCounted
    public void release() {
        this.refCountMonitor.onRelease(this);
        this.refCountDelegate.release();
    }

    @Override // com.webrtc.VideoFrame.Buffer
    public VideoFrame.Buffer cropAndScale(int i, int i2, int i3, int i4, int i5, int i6) {
        Matrix matrix = new Matrix();
        int i7 = this.height;
        matrix.preTranslate(i / this.width, (i7 - (i2 + i4)) / i7);
        matrix.preScale(i3 / this.width, i4 / this.height);
        return applyTransformMatrix(matrix, Math.round((this.unscaledWidth * i3) / this.width), Math.round((this.unscaledHeight * i4) / this.height), i5, i6);
    }

    public int getUnscaledWidth() {
        return this.unscaledWidth;
    }

    public int getUnscaledHeight() {
        return this.unscaledHeight;
    }

    public Handler getToI420Handler() {
        return this.toI420Handler;
    }

    public YuvConverter getYuvConverter() {
        return this.yuvConverter;
    }

    public TextureBufferImpl applyTransformMatrix(Matrix matrix, int i, int i2) {
        return applyTransformMatrix(matrix, i, i2, i, i2);
    }

    private TextureBufferImpl applyTransformMatrix(Matrix matrix, int i, int i2, int i3, int i4) {
        Matrix matrix2 = new Matrix(this.transformMatrix);
        matrix2.preConcat(matrix);
        retain();
        return new TextureBufferImpl(i, i2, i3, i4, this.type, this.f21267id, matrix2, this.toI420Handler, this.yuvConverter, new RefCountMonitor() { // from class: com.webrtc.TextureBufferImpl.2
            @Override // com.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRetain(TextureBufferImpl textureBufferImpl) {
                TextureBufferImpl.this.refCountMonitor.onRetain(TextureBufferImpl.this);
            }

            @Override // com.webrtc.TextureBufferImpl.RefCountMonitor
            public void onRelease(TextureBufferImpl textureBufferImpl) {
                TextureBufferImpl.this.refCountMonitor.onRelease(TextureBufferImpl.this);
            }

            @Override // com.webrtc.TextureBufferImpl.RefCountMonitor
            public void onDestroy(TextureBufferImpl textureBufferImpl) {
                TextureBufferImpl.this.release();
            }
        });
    }
}
