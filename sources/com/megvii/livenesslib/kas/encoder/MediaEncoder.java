package com.megvii.livenesslib.kas.encoder;

import android.media.MediaCodec;
import android.util.Log;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class MediaEncoder implements Runnable {
    private static final boolean DEBUG = false;
    protected static final int MSG_FRAME_AVAILABLE = 1;
    protected static final int MSG_STOP_RECORDING = 9;
    private static final String TAG = "MediaEncoder";
    protected static final int TIMEOUT_USEC = 10000;
    private MediaCodec.BufferInfo mBufferInfo;
    protected volatile boolean mIsCapturing;
    protected boolean mIsEOS;
    protected final MediaEncoderListener mListener;
    protected MediaCodec mMediaCodec;
    protected boolean mMuxerStarted;
    private int mRequestDrain;
    protected volatile boolean mRequestStop;
    protected int mTrackIndex;
    protected final WeakReference<MediaMuxerWrapper> mWeakMuxer;
    protected final Object mSync = new Object();
    private long prevOutputPTSUs = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface MediaEncoderListener {
        void onPrepared(MediaEncoder mediaEncoder);

        void onStopped(MediaEncoder mediaEncoder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void prepare() throws IOException;

    public MediaEncoder(MediaMuxerWrapper mediaMuxerWrapper, MediaEncoderListener mediaEncoderListener) {
        if (mediaEncoderListener == null) {
            throw new NullPointerException("MediaEncoderListener is null");
        }
        if (mediaMuxerWrapper == null) {
            throw new NullPointerException("MediaMuxerWrapper is null");
        }
        this.mWeakMuxer = new WeakReference<>(mediaMuxerWrapper);
        mediaMuxerWrapper.addEncoder(this);
        this.mListener = mediaEncoderListener;
        synchronized (this.mSync) {
            this.mBufferInfo = new MediaCodec.BufferInfo();
            new Thread(this, getClass().getSimpleName()).start();
            try {
                this.mSync.wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    public String getOutputPath() {
        MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
        if (mediaMuxerWrapper != null) {
            return mediaMuxerWrapper.getOutputPath();
        }
        return null;
    }

    public boolean frameAvailableSoon() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestDrain++;
                this.mSync.notifyAll();
                return true;
            }
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0048 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            r1 = 0
            r6.mRequestStop = r1     // Catch: java.lang.Throwable -> L56
            r6.mRequestDrain = r1     // Catch: java.lang.Throwable -> L56
            java.lang.Object r2 = r6.mSync     // Catch: java.lang.Throwable -> L56
            r2.notify()     // Catch: java.lang.Throwable -> L56
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
        Le:
            java.lang.Object r2 = r6.mSync
            monitor-enter(r2)
            boolean r0 = r6.mRequestStop     // Catch: java.lang.Throwable -> L53
            int r3 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L53
            r4 = 1
            if (r3 <= 0) goto L1a
            r3 = r4
            goto L1b
        L1a:
            r3 = r1
        L1b:
            if (r3 == 0) goto L22
            int r5 = r6.mRequestDrain     // Catch: java.lang.Throwable -> L53
            int r5 = r5 - r4
            r6.mRequestDrain = r5     // Catch: java.lang.Throwable -> L53
        L22:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L53
            if (r0 == 0) goto L32
            r6.drain()
            r6.signalEndOfInputStream()
            r6.drain()
            r6.release()
            goto L45
        L32:
            if (r3 == 0) goto L38
            r6.drain()
            goto Le
        L38:
            java.lang.Object r0 = r6.mSync
            monitor-enter(r0)
            java.lang.Object r2 = r6.mSync     // Catch: java.lang.Throwable -> L42 java.lang.InterruptedException -> L44
            r2.wait()     // Catch: java.lang.Throwable -> L42 java.lang.InterruptedException -> L44
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L42
            goto Le
        L42:
            r1 = move-exception
            goto L51
        L44:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L42
        L45:
            java.lang.Object r2 = r6.mSync
            monitor-enter(r2)
            r6.mRequestStop = r4     // Catch: java.lang.Throwable -> L4e
            r6.mIsCapturing = r1     // Catch: java.lang.Throwable -> L4e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4e
            return
        L4e:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L4e
            throw r0
        L51:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L42
            throw r1
        L53:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L53
            throw r0
        L56:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L56
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.megvii.livenesslib.kas.encoder.MediaEncoder.run():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startRecording() {
        synchronized (this.mSync) {
            this.mIsCapturing = true;
            this.mRequestStop = false;
            this.mSync.notifyAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopRecording() {
        synchronized (this.mSync) {
            if (this.mIsCapturing && !this.mRequestStop) {
                this.mRequestStop = true;
                this.mSync.notifyAll();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void release() {
        try {
            this.mListener.onStopped(this);
        } catch (Exception e) {
            Log.e(TAG, "failed onStopped", e);
        }
        this.mIsCapturing = false;
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
                this.mMediaCodec.release();
                this.mMediaCodec = null;
            } catch (Exception e2) {
                Log.e(TAG, "failed releasing MediaCodec", e2);
            }
        }
        if (this.mMuxerStarted) {
            WeakReference<MediaMuxerWrapper> weakReference = this.mWeakMuxer;
            MediaMuxerWrapper mediaMuxerWrapper = weakReference != null ? weakReference.get() : null;
            if (mediaMuxerWrapper != null) {
                try {
                    mediaMuxerWrapper.stop();
                } catch (Exception e3) {
                    Log.e(TAG, "failed stopping muxer", e3);
                }
            }
        }
        this.mBufferInfo = null;
    }

    protected void signalEndOfInputStream() {
        encode(null, 0, getPTSUs());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void encode(ByteBuffer byteBuffer, int i, long j) {
        if (this.mIsCapturing) {
            ByteBuffer[] inputBuffers = this.mMediaCodec.getInputBuffers();
            while (this.mIsCapturing) {
                int dequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer(10000L);
                if (dequeueInputBuffer >= 0) {
                    ByteBuffer byteBuffer2 = inputBuffers[dequeueInputBuffer];
                    byteBuffer2.clear();
                    if (byteBuffer != null) {
                        byteBuffer2.put(byteBuffer);
                    }
                    if (i <= 0) {
                        this.mIsEOS = true;
                        this.mMediaCodec.queueInputBuffer(dequeueInputBuffer, 0, 0, j, 4);
                        return;
                    }
                    this.mMediaCodec.queueInputBuffer(dequeueInputBuffer, 0, i, j, 0);
                    return;
                }
            }
        }
    }

    protected void drain() {
        MediaCodec mediaCodec = this.mMediaCodec;
        if (mediaCodec == null) {
            return;
        }
        ByteBuffer[] outputBuffers = mediaCodec.getOutputBuffers();
        MediaMuxerWrapper mediaMuxerWrapper = this.mWeakMuxer.get();
        if (mediaMuxerWrapper == null) {
            Log.w(TAG, "muxer is unexpectedly null");
            return;
        }
        ByteBuffer[] byteBufferArr = outputBuffers;
        int i = 0;
        while (this.mIsCapturing) {
            int dequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(this.mBufferInfo, 10000L);
            if (dequeueOutputBuffer == -1) {
                if (!this.mIsEOS && (i = i + 1) > 5) {
                    return;
                }
            } else if (dequeueOutputBuffer == -3) {
                byteBufferArr = this.mMediaCodec.getOutputBuffers();
            } else if (dequeueOutputBuffer == -2) {
                if (this.mMuxerStarted) {
                    throw new RuntimeException("format changed twice");
                }
                this.mTrackIndex = mediaMuxerWrapper.addTrack(this.mMediaCodec.getOutputFormat());
                this.mMuxerStarted = true;
                if (mediaMuxerWrapper.start()) {
                    continue;
                } else {
                    synchronized (mediaMuxerWrapper) {
                        while (!mediaMuxerWrapper.isStarted()) {
                            try {
                                mediaMuxerWrapper.wait(100L);
                            } catch (InterruptedException unused) {
                                return;
                            }
                        }
                    }
                }
            } else if (dequeueOutputBuffer >= 0) {
                ByteBuffer byteBuffer = byteBufferArr[dequeueOutputBuffer];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + dequeueOutputBuffer + " was null");
                }
                if ((this.mBufferInfo.flags & 2) != 0) {
                    this.mBufferInfo.size = 0;
                }
                if (this.mBufferInfo.size != 0) {
                    if (!this.mMuxerStarted) {
                        throw new RuntimeException("drain:muxer hasn't started");
                    }
                    this.mBufferInfo.presentationTimeUs = getPTSUs();
                    mediaMuxerWrapper.writeSampleData(this.mTrackIndex, byteBuffer, this.mBufferInfo);
                    this.prevOutputPTSUs = this.mBufferInfo.presentationTimeUs;
                    i = 0;
                }
                this.mMediaCodec.releaseOutputBuffer(dequeueOutputBuffer, false);
                if ((this.mBufferInfo.flags & 4) != 0) {
                    this.mIsCapturing = false;
                    return;
                }
            } else {
                continue;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long getPTSUs() {
        long nanoTime = System.nanoTime() / 1000;
        long j = this.prevOutputPTSUs;
        return nanoTime < j ? nanoTime + (j - nanoTime) : nanoTime;
    }
}
