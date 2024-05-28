package com.baidu.rtc;

import com.webrtc.audio.JavaAudioDeviceModule;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCAudioSamples extends JavaAudioDeviceModule.AudioSamples {
    private long captureTime;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RTCExternalSamplesReadyCallback {
        void onRtcAudioExternalSamplesReady(RTCAudioSamples rTCAudioSamples);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RTCMixedSamplesReadyCallback {
        void onRtcAudioMixedSamplesReady(RTCAudioSamples rTCAudioSamples);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RTCRemoteSamplesReadyCallback {
        void onRtcAudioRemoteSamplesReady(RTCAudioSamples rTCAudioSamples);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RTCSampleStatusCallback {
        void onStartRecord();

        void onStopRecord();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface RTCSamplesReadyCallback {
        void onRtcAudioRecordSamplesReady(RTCAudioSamples rTCAudioSamples);
    }

    public RTCAudioSamples(int i, int i2, int i3, byte[] bArr, long j) {
        super(i, i2, i3, bArr);
        this.captureTime = 0L;
        this.captureTime = j;
    }

    public RTCAudioSamples(int i, int i2, int i3, byte[] bArr) {
        super(i, i2, i3, bArr);
        this.captureTime = 0L;
    }

    public long getCaptureTime() {
        return this.captureTime;
    }
}
