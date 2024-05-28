package com.baidu.rtc;

import android.media.AudioFormat;
import android.media.AudioPlaybackCaptureConfiguration;
import android.media.AudioRecord;
import android.media.projection.MediaProjection;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.RequiresApi;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.config.BRTCScreenShareParams;
import com.webrtc.Logging;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ScreenAudioCapturer {
    private AudioRecord mAudioRecord;
    private RTCAudioSamples.RTCExternalSamplesReadyCallback mCallback;
    private MediaProjection mMediaProjection;
    private volatile boolean mRecording = false;
    private HandlerThread mAudioRecordThread = null;
    private Handler mAudioRecordHandler = null;
    private int mChannels = 2;
    private int mSampleRate = 48000;

    public ScreenAudioCapturer(RTCAudioSamples.RTCExternalSamplesReadyCallback rTCExternalSamplesReadyCallback) {
        this.mCallback = rTCExternalSamplesReadyCallback;
    }

    public void startCapture(MediaProjection mediaProjection, BRTCScreenShareParams.BRTCScreenShareAudioParams bRTCScreenShareAudioParams) {
        if (Build.VERSION.SDK_INT >= 29 && !this.mRecording) {
            this.mMediaProjection = mediaProjection;
            if (bRTCScreenShareAudioParams != null) {
                if (bRTCScreenShareAudioParams.channel == 2 || bRTCScreenShareAudioParams.channel == 1) {
                    this.mChannels = bRTCScreenShareAudioParams.channel;
                }
                if (bRTCScreenShareAudioParams.sampleRate > 0) {
                    this.mSampleRate = bRTCScreenShareAudioParams.sampleRate;
                }
                log("startCapture channel : " + this.mChannels + " sampleRate : " + this.mSampleRate + " volume : " + bRTCScreenShareAudioParams.volume);
            }
            if (this.mAudioRecordThread == null) {
                this.mAudioRecordThread = new HandlerThread("WebRTC_ScreenAudioCapture");
                this.mAudioRecordThread.start();
                this.mAudioRecordHandler = new Handler(this.mAudioRecordThread.getLooper());
            }
            this.mAudioRecordHandler.removeCallbacksAndMessages(null);
            this.mAudioRecordHandler.post(new Runnable() { // from class: com.baidu.rtc.ScreenAudioCapturer.1
                @Override // java.lang.Runnable
                @RequiresApi(api = 29)
                public void run() {
                    ScreenAudioCapturer.this.startCaptureInternal();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(api = 29)
    public void startCaptureInternal() {
        log("audio startCaptureInternal");
        this.mRecording = true;
        int i = this.mChannels == 1 ? 16 : 12;
        AudioPlaybackCaptureConfiguration build = new AudioPlaybackCaptureConfiguration.Builder(this.mMediaProjection).addMatchingUsage(1).build();
        AudioFormat build2 = new AudioFormat.Builder().setEncoding(2).setSampleRate(this.mSampleRate).setChannelMask(i).build();
        int i2 = this.mSampleRate;
        int i3 = this.mChannels * 2 * (i2 / 100);
        this.mAudioRecord = new AudioRecord.Builder().setAudioFormat(build2).setBufferSizeInBytes(Math.max(AudioRecord.getMinBufferSize(i2, i, 2) * 2, i3)).setAudioPlaybackCaptureConfig(build).build();
        this.mAudioRecord.startRecording();
        byte[] bArr = new byte[i3];
        while (this.mRecording) {
            this.mAudioRecord.read(bArr, 0, bArr.length);
            RTCAudioSamples.RTCExternalSamplesReadyCallback rTCExternalSamplesReadyCallback = this.mCallback;
            if (rTCExternalSamplesReadyCallback != null) {
                rTCExternalSamplesReadyCallback.onRtcAudioExternalSamplesReady(new RTCAudioSamples(this.mAudioRecord.getAudioFormat(), this.mChannels, this.mSampleRate, bArr));
            }
        }
    }

    public void stopCapture() {
        log("audio stopCapture");
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord != null) {
            this.mRecording = false;
            audioRecord.stop();
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        }
        HandlerThread handlerThread = this.mAudioRecordThread;
        if (handlerThread != null) {
            handlerThread.interrupt();
            this.mAudioRecordThread.quit();
            this.mAudioRecordThread = null;
        }
        Handler handler = this.mAudioRecordHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mAudioRecordHandler = null;
        }
    }

    private void log(String str) {
        Logging.m5305d(ScreenCapturerAndroid.class.getName(), str);
    }
}
