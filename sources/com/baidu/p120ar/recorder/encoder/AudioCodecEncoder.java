package com.baidu.p120ar.recorder.encoder;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.view.Surface;
import com.baidu.p120ar.record.EncoderParams;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.encoder.AudioCodecEncoder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioCodecEncoder extends BaseCodecEncoder {
    private static final String TAG = "AudioCodecEncoder";
    private long mLastAudioEncodedTimestamp = 0;

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public /* bridge */ /* synthetic */ void drainBuffer(boolean z, ByteBuffer byteBuffer, int i, long j) {
        super.drainBuffer(z, byteBuffer, i, j);
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public /* bridge */ /* synthetic */ void drainSurface(boolean z) {
        super.drainSurface(z);
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public /* bridge */ /* synthetic */ void releaseEncoder() {
        super.releaseEncoder();
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public /* bridge */ /* synthetic */ void setEncoderCallback(EncoderCallback encoderCallback) {
        super.setEncoderCallback(encoderCallback);
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public /* bridge */ /* synthetic */ void startEncoder() {
        super.startEncoder();
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public /* bridge */ /* synthetic */ void stopEncoder() {
        super.stopEncoder();
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    public void initEncoder(EncoderParams encoderParams, MovieMuxer movieMuxer) {
        boolean z = false;
        if (encoderParams != null && movieMuxer != null) {
            this.mMuxer = movieMuxer;
            MediaFormat mediaFormat = new MediaFormat();
            mediaFormat.setString("mime", encoderParams.getAudioCodec());
            mediaFormat.setInteger("aac-profile", 2);
            mediaFormat.setInteger("sample-rate", encoderParams.getAudioSampleRate());
            mediaFormat.setInteger("channel-count", encoderParams.getAudioChannel());
            mediaFormat.setInteger("bitrate", encoderParams.getAudioBitrate());
            mediaFormat.setInteger("max-input-size", encoderParams.getAudioFrameSize());
            try {
                this.mEncoder = MediaCodec.createEncoderByType(encoderParams.getAudioCodec());
                this.mEncoder.configure(mediaFormat, (Surface) null, (MediaCrypto) null, 1);
                if (!encoderParams.isVideoIncluded()) {
                    this.mMainTrack = true;
                } else {
                    this.mMainTrack = false;
                }
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.mEncoderCallback != null) {
            this.mEncoderCallback.onEncoderSetup(z, null);
        }
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    protected void syncTimestamp() {
        if (this.mStartTimestampUS == 0) {
            this.mStartTimestampUS = this.mBufferInfo.presentationTimeUs;
        }
        this.mBufferInfo.presentationTimeUs -= this.mStartTimestampUS;
        if (this.mBufferInfo.presentationTimeUs < this.mLastAudioEncodedTimestamp) {
            MediaCodec.BufferInfo bufferInfo = this.mBufferInfo;
            long j = this.mLastAudioEncodedTimestamp + 10000;
            this.mLastAudioEncodedTimestamp = j;
            bufferInfo.presentationTimeUs = j;
        }
        this.mLastAudioEncodedTimestamp = this.mBufferInfo.presentationTimeUs;
        ARLog.m20421d(TAG, "syncTimestamp mAudioEncoder = " + this.mBufferInfo.size + "|" + this.mBufferInfo.presentationTimeUs);
    }
}
