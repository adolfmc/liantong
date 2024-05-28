package com.webrtc.audio;

import android.content.Context;
import android.media.AudioManager;
import com.baidu.rtc.logreport.SLIReportInterface;
import com.baidu.rtc.recorder.RtcAudioRecordListener;
import com.webrtc.JniCommon;
import com.webrtc.Logging;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class JavaAudioDeviceModule implements AudioDeviceModule {
    private static final String TAG = "JavaAudioDeviceModule";
    private final WebRtcAudioRecord audioInput;
    private final AudioManager audioManager;
    private final WebRtcAudioTrack audioOutput;
    private final Context context;
    private final int inputSampleRate;
    private long nativeAudioDeviceModule;
    private final Object nativeLock;
    private final int outputSampleRate;
    private SLIReportInterface stuckEvent;
    private final boolean useStereoInput;
    private final boolean useStereoOutput;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface AudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String str);

        void onWebRtcAudioRecordInitError(String str);

        void onWebRtcAudioRecordStartError(AudioRecordStartErrorCode audioRecordStartErrorCode, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum AudioRecordStartErrorCode {
        AUDIO_RECORD_START_EXCEPTION,
        AUDIO_RECORD_START_STATE_MISMATCH
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface AudioRecordStateCallback {
        void onWebRtcAudioRecordStart();

        void onWebRtcAudioRecordStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface AudioTrackErrorCallback {
        void onWebRtcAudioTrackError(String str);

        void onWebRtcAudioTrackInitError(String str);

        void onWebRtcAudioTrackStartError(AudioTrackStartErrorCode audioTrackStartErrorCode, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum AudioTrackStartErrorCode {
        AUDIO_TRACK_START_EXCEPTION,
        AUDIO_TRACK_START_STATE_MISMATCH
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface AudioTrackStateCallback {
        void onWebRtcAudioTrackStart();

        void onWebRtcAudioTrackStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface ExternalSamplesReadyCallback {
        void onWebRtcAudioExternalSamplesReady(AudioSamples audioSamples);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface RemoteAudioTrackCallback {
        void onStartPlay();

        void onStopPlay();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface RemoteSamplesReadyCallback {
        void onWebRtcAudioRemoteSamplesReady(AudioSamples audioSamples);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface SamplesReadyCallback {
        void onStartRecord();

        void onStopRecord();

        void onWebRtcAudioRecordSamplesReady(AudioSamples audioSamples);
    }

    private static native long nativeCreateAudioDeviceModule(Context context, AudioManager audioManager, WebRtcAudioRecord webRtcAudioRecord, WebRtcAudioTrack webRtcAudioTrack, int i, int i2, boolean z, boolean z2);

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class Builder {
        private SamplesReadyCallback aecSamplesReadyCallback;
        private int audioContentType;
        private final AudioManager audioManager;
        private AudioRecordErrorCallback audioRecordErrorCallback;
        private int audioSource;
        private AudioTrackErrorCallback audioTrackErrorCallback;
        private final Context context;
        private boolean externalRecord;
        private ExternalSamplesReadyCallback externalSamplesReadyCallback;
        private int inputSampleRate;
        private boolean isStartConnection;
        private boolean mEnableAudioEffect;
        private int outputSampleRate;
        private RemoteSamplesReadyCallback remoteSamplesReadyCallback;
        private RtcAudioRecordListener rtcAudioRecordListener;
        private SamplesReadyCallback samplesReadyCallback;
        private boolean useBdAEC;
        private boolean useBdAGC;
        private boolean useBdNS;
        private boolean useHardwareAcousticEchoCanceler;
        private boolean useHardwareNoiseSuppressor;
        private boolean useStereoInput;
        private boolean useStereoOutput;

        private Builder(Context context) {
            this.audioSource = 7;
            this.useHardwareAcousticEchoCanceler = JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported();
            this.useHardwareNoiseSuppressor = JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported();
            this.isStartConnection = false;
            this.context = context;
            this.audioManager = (AudioManager) context.getSystemService("audio");
            this.inputSampleRate = WebRtcAudioManager.getSampleRate(this.audioManager);
            this.outputSampleRate = WebRtcAudioManager.getSampleRate(this.audioManager);
        }

        public Builder setSampleRate(int i) {
            Logging.m5305d(JavaAudioDeviceModule.TAG, "Sample rate overridden to: " + i);
            this.inputSampleRate = i;
            this.outputSampleRate = i;
            return this;
        }

        public Builder setInputSampleRate(int i) {
            Logging.m5305d(JavaAudioDeviceModule.TAG, "Input sample rate overridden to: " + i);
            this.inputSampleRate = i;
            return this;
        }

        public Builder setOutputSampleRate(int i) {
            Logging.m5305d(JavaAudioDeviceModule.TAG, "Output sample rate overridden to: " + i);
            this.outputSampleRate = i;
            return this;
        }

        public Builder setAudioSource(int i) {
            this.audioSource = i;
            return this;
        }

        public Builder setAudioContentType(int i) {
            this.audioContentType = i;
            return this;
        }

        public Builder setAudioTrackErrorCallback(AudioTrackErrorCallback audioTrackErrorCallback) {
            this.audioTrackErrorCallback = audioTrackErrorCallback;
            return this;
        }

        public Builder setAudioRecordErrorCallback(AudioRecordErrorCallback audioRecordErrorCallback) {
            this.audioRecordErrorCallback = audioRecordErrorCallback;
            return this;
        }

        public Builder setExternalAudioRecord(boolean z) {
            this.externalRecord = z;
            return this;
        }

        public Builder setEnableAudioEffect(boolean z) {
            this.mEnableAudioEffect = z;
            return this;
        }

        public Builder setSamplesReadyCallback(SamplesReadyCallback samplesReadyCallback) {
            this.samplesReadyCallback = samplesReadyCallback;
            return this;
        }

        public Builder setAecSamplesReadyCallback(SamplesReadyCallback samplesReadyCallback) {
            this.aecSamplesReadyCallback = samplesReadyCallback;
            return this;
        }

        public Builder setRemoteSamplesReadyCallback(RemoteSamplesReadyCallback remoteSamplesReadyCallback) {
            this.remoteSamplesReadyCallback = remoteSamplesReadyCallback;
            return this;
        }

        public Builder setUseHardwareNoiseSuppressor(boolean z) {
            if (z && !JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported()) {
                Logging.m5304e(JavaAudioDeviceModule.TAG, "HW NS not supported");
                z = false;
            }
            this.useHardwareNoiseSuppressor = z;
            return this;
        }

        public Builder setUseHardwareAcousticEchoCanceler(boolean z) {
            if (z && !JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported()) {
                Logging.m5304e(JavaAudioDeviceModule.TAG, "HW AEC not supported");
                z = false;
            }
            this.useHardwareAcousticEchoCanceler = z;
            return this;
        }

        public Builder setAudioRecordListener(RtcAudioRecordListener rtcAudioRecordListener) {
            this.rtcAudioRecordListener = rtcAudioRecordListener;
            return this;
        }

        public Builder setUseBdAEC(boolean z) {
            this.useBdAEC = z;
            return this;
        }

        public Builder setUseBdAGC(boolean z) {
            this.useBdAGC = z;
            return this;
        }

        public Builder setUseBdNS(boolean z) {
            this.useBdNS = z;
            return this;
        }

        public Builder setUseStereoInput(boolean z) {
            this.useStereoInput = z;
            return this;
        }

        public Builder setUseStereoOutput(boolean z) {
            this.useStereoOutput = z;
            return this;
        }

        public AudioDeviceModule createAudioDeviceModule() {
            Logging.m5305d(JavaAudioDeviceModule.TAG, "createAudioDeviceModule");
            if (this.useHardwareNoiseSuppressor) {
                Logging.m5305d(JavaAudioDeviceModule.TAG, "HW NS will be used.");
            } else {
                if (JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported()) {
                    Logging.m5305d(JavaAudioDeviceModule.TAG, "Overriding default behavior; now using WebRTC NS!");
                }
                Logging.m5305d(JavaAudioDeviceModule.TAG, "HW NS will not be used.");
            }
            if (this.useHardwareAcousticEchoCanceler) {
                Logging.m5305d(JavaAudioDeviceModule.TAG, "HW AEC will be used.");
            } else {
                if (JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported()) {
                    Logging.m5305d(JavaAudioDeviceModule.TAG, "Overriding default behavior; now using WebRTC AEC!");
                }
                Logging.m5305d(JavaAudioDeviceModule.TAG, "HW AEC will not be used.");
            }
            SamplesReadyCallback samplesReadyCallback = new SamplesReadyCallback() { // from class: com.webrtc.audio.JavaAudioDeviceModule.Builder.1
                @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
                public void onWebRtcAudioRecordSamplesReady(AudioSamples audioSamples) {
                    if (Builder.this.samplesReadyCallback != null) {
                        Builder.this.samplesReadyCallback.onWebRtcAudioRecordSamplesReady(audioSamples);
                    }
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
                public void onStartRecord() {
                    if (Builder.this.samplesReadyCallback != null) {
                        Builder.this.samplesReadyCallback.onStartRecord();
                    }
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.SamplesReadyCallback
                public void onStopRecord() {
                    if (Builder.this.samplesReadyCallback != null) {
                        Builder.this.samplesReadyCallback.onStopRecord();
                    }
                }
            };
            RemoteSamplesReadyCallback remoteSamplesReadyCallback = new RemoteSamplesReadyCallback() { // from class: com.webrtc.audio.JavaAudioDeviceModule.Builder.2
                @Override // com.webrtc.audio.JavaAudioDeviceModule.RemoteSamplesReadyCallback
                public void onWebRtcAudioRemoteSamplesReady(AudioSamples audioSamples) {
                    if (Builder.this.remoteSamplesReadyCallback != null) {
                        Builder.this.remoteSamplesReadyCallback.onWebRtcAudioRemoteSamplesReady(audioSamples);
                    }
                }
            };
            WebRtcAudioRecord webRtcAudioRecord = new WebRtcAudioRecord(this.context, this.audioManager, this.audioSource, this.externalRecord, this.mEnableAudioEffect, this.audioRecordErrorCallback, samplesReadyCallback, this.aecSamplesReadyCallback, this.useHardwareAcousticEchoCanceler, this.useHardwareNoiseSuppressor, this.useBdAEC || this.useBdAGC || this.useBdNS);
            webRtcAudioRecord.setAudioRecordListener(this.rtcAudioRecordListener);
            this.externalSamplesReadyCallback = webRtcAudioRecord.getExternalSamplesCallback();
            WebRtcAudioTrack webRtcAudioTrack = new WebRtcAudioTrack(this.context, this.audioManager, this.audioContentType, remoteSamplesReadyCallback, this.audioTrackErrorCallback);
            webRtcAudioTrack.setAudioTrackCallback(new RemoteAudioTrackCallback() { // from class: com.webrtc.audio.JavaAudioDeviceModule.Builder.3
                @Override // com.webrtc.audio.JavaAudioDeviceModule.RemoteAudioTrackCallback
                public void onStartPlay() {
                    Builder.this.isStartConnection = true;
                }

                @Override // com.webrtc.audio.JavaAudioDeviceModule.RemoteAudioTrackCallback
                public void onStopPlay() {
                    Builder.this.isStartConnection = false;
                }
            });
            return new JavaAudioDeviceModule(this.context, this.audioManager, webRtcAudioRecord, webRtcAudioTrack, this.inputSampleRate, this.outputSampleRate, this.useStereoInput, this.useStereoOutput);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class AudioSamples {
        private final int audioFormat;
        private final int channelCount;
        private final byte[] data;
        private final int sampleRate;

        public AudioSamples(int i, int i2, int i3, byte[] bArr) {
            this.audioFormat = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.data = bArr;
        }

        public int getAudioFormat() {
            return this.audioFormat;
        }

        public int getChannelCount() {
            return this.channelCount;
        }

        public int getSampleRate() {
            return this.sampleRate;
        }

        public byte[] getData() {
            return this.data;
        }
    }

    public static boolean isBuiltInAcousticEchoCancelerSupported() {
        return WebRtcAudioEffects.isAcousticEchoCancelerSupported();
    }

    public static boolean isBuiltInNoiseSuppressorSupported() {
        return WebRtcAudioEffects.isNoiseSuppressorSupported();
    }

    private JavaAudioDeviceModule(Context context, AudioManager audioManager, WebRtcAudioRecord webRtcAudioRecord, WebRtcAudioTrack webRtcAudioTrack, int i, int i2, boolean z, boolean z2) {
        this.nativeLock = new Object();
        this.stuckEvent = null;
        this.context = context;
        this.audioManager = audioManager;
        this.audioInput = webRtcAudioRecord;
        this.audioOutput = webRtcAudioTrack;
        this.inputSampleRate = i;
        this.outputSampleRate = i2;
        this.useStereoInput = z;
        this.useStereoOutput = z2;
    }

    public void stopCaptureAudio() {
        WebRtcAudioRecord webRtcAudioRecord = this.audioInput;
        if (webRtcAudioRecord != null) {
            webRtcAudioRecord.stopRecord();
        }
    }

    public void reStartCaptureAudio() {
        WebRtcAudioRecord webRtcAudioRecord = this.audioInput;
        if (webRtcAudioRecord != null) {
            webRtcAudioRecord.reStartRecord();
        }
    }

    @Override // com.webrtc.audio.AudioDeviceModule
    public long getNativeAudioDeviceModulePointer() {
        long j;
        synchronized (this.nativeLock) {
            if (this.nativeAudioDeviceModule == 0) {
                this.nativeAudioDeviceModule = nativeCreateAudioDeviceModule(this.context, this.audioManager, this.audioInput, this.audioOutput, this.inputSampleRate, this.outputSampleRate, this.useStereoInput, this.useStereoOutput);
            }
            j = this.nativeAudioDeviceModule;
        }
        return j;
    }

    @Override // com.webrtc.audio.AudioDeviceModule
    public void release() {
        synchronized (this.nativeLock) {
            if (this.nativeAudioDeviceModule != 0) {
                JniCommon.nativeReleaseRef(this.nativeAudioDeviceModule);
                this.nativeAudioDeviceModule = 0L;
            }
        }
    }

    @Override // com.webrtc.audio.AudioDeviceModule
    public void setSpeakerMute(boolean z) {
        Logging.m5305d(TAG, "setSpeakerMute: " + z);
        this.audioOutput.setSpeakerMute(z);
    }

    @Override // com.webrtc.audio.AudioDeviceModule
    public void setMicrophoneMute(boolean z) {
        Logging.m5305d(TAG, "setMicrophoneMute: " + z);
        this.audioInput.setMicrophoneMute(z);
    }

    public ExternalSamplesReadyCallback getExternalSamplesReadyCallback() {
        return this.audioInput.getExternalSamplesCallback();
    }

    public void setStuckEventListener(SLIReportInterface sLIReportInterface) {
        this.stuckEvent = sLIReportInterface;
        WebRtcAudioTrack webRtcAudioTrack = this.audioOutput;
        if (webRtcAudioTrack != null) {
            webRtcAudioTrack.setStuckEventListener(sLIReportInterface);
        }
    }

    public void setEnableSLIReport(boolean z) {
        this.audioOutput.setEnableSLIReport(z);
    }

    public void setRemoteSamplesReadyCallback(RemoteSamplesReadyCallback remoteSamplesReadyCallback) {
        WebRtcAudioTrack webRtcAudioTrack = this.audioOutput;
        if (webRtcAudioTrack != null) {
            webRtcAudioTrack.setRemoteSamplesReadyCallback(remoteSamplesReadyCallback);
        }
    }
}
