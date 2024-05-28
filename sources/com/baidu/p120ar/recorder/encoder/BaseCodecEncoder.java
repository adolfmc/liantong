package com.baidu.p120ar.recorder.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import com.baidu.p120ar.record.EncoderParams;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.encoder.BaseCodecEncoder */
/* loaded from: E:\10201592_dexfile_execute.dex */
abstract class BaseCodecEncoder {
    private static final String TAG = "BaseCodecEncoder";
    private static final int TIMEOUT_USEC = 10000;
    protected MediaCodec mEncoder;
    protected EncoderCallback mEncoderCallback;
    protected boolean mMainTrack;
    protected MovieMuxer mMuxer;
    private int mTrackIndexInMuxer = -1;
    private boolean mTrackAdded = false;
    protected long mStartTimestampUS = 0;
    protected MediaCodec.BufferInfo mBufferInfo = new MediaCodec.BufferInfo();

    public abstract void initEncoder(EncoderParams encoderParams, MovieMuxer movieMuxer);

    protected abstract void syncTimestamp();

    public void startEncoder() {
        boolean z;
        ARLog.m20421d(TAG, "startEncoder !!!");
        MediaCodec mediaCodec = this.mEncoder;
        if (mediaCodec != null) {
            try {
                mediaCodec.start();
            } catch (Exception e) {
                z = false;
                e.printStackTrace();
            }
        }
        z = true;
        EncoderCallback encoderCallback = this.mEncoderCallback;
        if (encoderCallback != null) {
            encoderCallback.onEncoderStart(z);
        }
    }

    public void drainSurface(boolean z) {
        String str = TAG;
        ARLog.m20421d(str, "drainSurface endOfStream = " + z);
        if (z) {
            MovieMuxer movieMuxer = this.mMuxer;
            if (movieMuxer != null && movieMuxer.isMuxerStarted()) {
                this.mEncoder.signalEndOfInputStream();
            } else {
                EncoderCallback encoderCallback = this.mEncoderCallback;
                if (encoderCallback != null) {
                    encoderCallback.onEncoderStop(true);
                    return;
                }
                return;
            }
        }
        drainEncoder(z);
    }

    public void drainBuffer(boolean z, ByteBuffer byteBuffer, int i, long j) {
        if (this.mEncoder != null) {
            int i2 = -1;
            if (this.mTrackAdded && this.mTrackIndexInMuxer == -1) {
                return;
            }
            try {
                i2 = this.mEncoder.dequeueInputBuffer(10000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (i2 < 0) {
                ARLog.m20421d(TAG, "drainBuffer encode input buffer not available");
            } else if (z) {
                ARLog.m20421d(TAG, "drainBuffer sending EOS to drainBufferEncoder");
                this.mEncoder.queueInputBuffer(i2, 0, 0, 0L, 4);
            } else if (!setInputBuffer(i2, byteBuffer, i, j)) {
                return;
            } else {
                this.mEncoder.queueInputBuffer(i2, this.mBufferInfo.offset, this.mBufferInfo.size, this.mBufferInfo.presentationTimeUs, 0);
            }
            drainEncoder(z);
        }
    }

    private boolean setInputBuffer(int i, ByteBuffer byteBuffer, int i2, long j) {
        ByteBuffer byteBuffer2 = this.mEncoder.getInputBuffers()[i];
        if (byteBuffer2.capacity() >= byteBuffer.capacity()) {
            byteBuffer2.position(0);
            byteBuffer2.put(byteBuffer);
            byteBuffer2.flip();
            MediaCodec.BufferInfo bufferInfo = this.mBufferInfo;
            bufferInfo.offset = 0;
            bufferInfo.size = i2;
            bufferInfo.presentationTimeUs = j / 1000;
            return true;
        }
        return false;
    }

    public void stopEncoder() {
        ARLog.m20421d(TAG, "stopEncoder !!!");
        MediaCodec mediaCodec = this.mEncoder;
        if (mediaCodec != null) {
            try {
                mediaCodec.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void releaseEncoder() {
        this.mEncoder.release();
        this.mEncoder = null;
        this.mMuxer = null;
    }

    public void setEncoderCallback(EncoderCallback encoderCallback) {
        this.mEncoderCallback = encoderCallback;
    }

    private void drainEncoder(boolean z) {
        ByteBuffer[] byteBufferArr;
        int i;
        ARLog.m20421d(TAG, "drainEncoder endOfStream = " + z);
        try {
            byteBufferArr = this.mEncoder.getOutputBuffers();
        } catch (Exception e) {
            e.printStackTrace();
            byteBufferArr = null;
        }
        if (byteBufferArr == null) {
            return;
        }
        while (true) {
            try {
                i = this.mEncoder.dequeueOutputBuffer(this.mBufferInfo, 10000L);
            } catch (Exception e2) {
                e2.printStackTrace();
                i = 0;
            }
            ARLog.m20421d(TAG, "drainEncoder encoderStatus = " + i);
            if (i == -1) {
                if (!z) {
                    return;
                }
                ARLog.m20421d(TAG, "no output available, spinning to await EOS");
            } else if (i == -3) {
                byteBufferArr = this.mEncoder.getOutputBuffers();
            } else if (i == -2) {
                if (this.mMuxer.isMuxerStarted()) {
                    ARLog.m20419e(TAG, "format changed twice!!!!");
                    return;
                }
                MediaFormat outputFormat = this.mEncoder.getOutputFormat();
                ARLog.m20421d(TAG, "encoder output format changed: " + outputFormat);
                this.mTrackIndexInMuxer = this.mMuxer.addMuxerTrack(outputFormat);
                this.mTrackAdded = true;
                EncoderCallback encoderCallback = this.mEncoderCallback;
                if (encoderCallback != null) {
                    encoderCallback.onEncoderTrackAdd(this.mTrackAdded);
                }
                if (this.mMainTrack) {
                    this.mMuxer.startMuxer();
                }
            } else if (i < 0) {
                ARLog.m20413w(TAG, "unexpected result from encoder.dequeueOutputBuffer: " + i);
            } else {
                ByteBuffer byteBuffer = byteBufferArr[i];
                if (byteBuffer == null) {
                    throw new RuntimeException("encoderOutputBuffer " + i + " was null");
                }
                if ((this.mBufferInfo.flags & 2) != 0) {
                    ARLog.m20421d(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG");
                    this.mBufferInfo.size = 0;
                }
                if (this.mBufferInfo.size != 0) {
                    if (this.mMuxer.isMuxerStarted()) {
                        byteBuffer.position(this.mBufferInfo.offset);
                        byteBuffer.limit(this.mBufferInfo.offset + this.mBufferInfo.size);
                        syncTimestamp();
                        ARLog.m20421d(TAG, "drainEncoder writeSampleData mBufferInfo = " + this.mBufferInfo.presentationTimeUs + "&& size = " + this.mBufferInfo.size);
                        this.mMuxer.writeSampleData(this.mTrackIndexInMuxer, byteBuffer, this.mBufferInfo);
                    } else {
                        ARLog.m20421d(TAG, "drainEncoder wait for mMuxer start !!!");
                    }
                }
                this.mEncoder.releaseOutputBuffer(i, false);
                if ((this.mBufferInfo.flags & 4) != 0) {
                    if (z) {
                        if (this.mMainTrack) {
                            this.mMuxer.stopMuxer();
                        }
                        EncoderCallback encoderCallback2 = this.mEncoderCallback;
                        if (encoderCallback2 != null) {
                            encoderCallback2.onEncoderStop(true);
                            return;
                        }
                        return;
                    }
                    ARLog.m20419e(TAG, "reached end of stream unexpectedly");
                    return;
                }
            }
        }
    }
}
