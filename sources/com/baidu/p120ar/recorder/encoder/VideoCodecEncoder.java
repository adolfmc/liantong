package com.baidu.p120ar.recorder.encoder;

import android.view.Surface;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.encoder.VideoCodecEncoder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoCodecEncoder extends BaseCodecEncoder {
    private static final String TAG = "VideoCodecEncoder";
    private Surface mInputSurface;

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

    /* JADX WARN: Removed duplicated region for block: B:13:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void initEncoder(com.baidu.p120ar.record.EncoderParams r4, com.baidu.p120ar.recorder.encoder.MovieMuxer r5) {
        /*
            r3 = this;
            r0 = 1
            if (r4 == 0) goto L59
            if (r5 == 0) goto L59
            r3.mMuxer = r5
            java.lang.String r5 = r4.getVideoCodec()
            int r1 = r4.getVideoWidth()
            int r2 = r4.getVideoHeight()
            android.media.MediaFormat r5 = android.media.MediaFormat.createVideoFormat(r5, r1, r2)
            java.lang.String r1 = "color-format"
            r2 = 2130708361(0x7f000789, float:1.701803E38)
            r5.setInteger(r1, r2)
            java.lang.String r1 = "bitrate"
            int r2 = r4.getVideoBitrate()
            r5.setInteger(r1, r2)
            java.lang.String r1 = "frame-rate"
            int r2 = r4.getVideoFrameRate()
            r5.setInteger(r1, r2)
            java.lang.String r1 = "i-frame-interval"
            int r2 = r4.getVideoIFrameInterval()
            r5.setInteger(r1, r2)
            java.lang.String r4 = r4.getVideoCodec()     // Catch: java.lang.Exception -> L55
            android.media.MediaCodec r4 = android.media.MediaCodec.createEncoderByType(r4)     // Catch: java.lang.Exception -> L55
            r3.mEncoder = r4     // Catch: java.lang.Exception -> L55
            android.media.MediaCodec r4 = r3.mEncoder     // Catch: java.lang.Exception -> L55
            r1 = 0
            r4.configure(r5, r1, r1, r0)     // Catch: java.lang.Exception -> L55
            android.media.MediaCodec r4 = r3.mEncoder     // Catch: java.lang.Exception -> L55
            android.view.Surface r4 = r4.createInputSurface()     // Catch: java.lang.Exception -> L55
            r3.mInputSurface = r4     // Catch: java.lang.Exception -> L55
            r3.mMainTrack = r0     // Catch: java.lang.Exception -> L55
            goto L5a
        L55:
            r4 = move-exception
            r4.printStackTrace()
        L59:
            r0 = 0
        L5a:
            com.baidu.ar.recorder.encoder.EncoderCallback r4 = r3.mEncoderCallback
            if (r4 == 0) goto L65
            com.baidu.ar.recorder.encoder.EncoderCallback r4 = r3.mEncoderCallback
            android.view.Surface r5 = r3.mInputSurface
            r4.onEncoderSetup(r0, r5)
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.recorder.encoder.VideoCodecEncoder.initEncoder(com.baidu.ar.record.EncoderParams, com.baidu.ar.recorder.encoder.MovieMuxer):void");
    }

    public Surface getInputSurface() {
        return this.mInputSurface;
    }

    @Override // com.baidu.p120ar.recorder.encoder.BaseCodecEncoder
    protected void syncTimestamp() {
        if (this.mStartTimestampUS == 0) {
            this.mStartTimestampUS = this.mBufferInfo.presentationTimeUs;
        }
        this.mBufferInfo.presentationTimeUs -= this.mStartTimestampUS;
        ARLog.m20421d(TAG, "syncTimestamp mVideoEncoder = " + this.mBufferInfo.size + "|" + this.mBufferInfo.presentationTimeUs);
    }
}
