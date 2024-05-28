package org.webrtc.audio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.support.annotation.RequiresApi;
import org.webrtc.JniCommon;
import org.webrtc.Logging;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
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
    private final boolean useStereoInput;
    private final boolean useStereoOutput;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface AudioRecordErrorCallback {
        void onWebRtcAudioRecordError(String str);

        void onWebRtcAudioRecordInitError(String str);

        void onWebRtcAudioRecordStartError(AudioRecordStartErrorCode audioRecordStartErrorCode, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum AudioRecordStartErrorCode {
        AUDIO_RECORD_START_EXCEPTION,
        AUDIO_RECORD_START_STATE_MISMATCH
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface AudioRecordStateCallback {
        void onWebRtcAudioRecordStart();

        void onWebRtcAudioRecordStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface AudioTrackErrorCallback {
        void onWebRtcAudioTrackError(String str);

        void onWebRtcAudioTrackInitError(String str);

        void onWebRtcAudioTrackStartError(AudioTrackStartErrorCode audioTrackStartErrorCode, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public enum AudioTrackStartErrorCode {
        AUDIO_TRACK_START_EXCEPTION,
        AUDIO_TRACK_START_STATE_MISMATCH
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface AudioTrackStateCallback {
        void onWebRtcAudioTrackStart();

        void onWebRtcAudioTrackStop();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public interface SamplesReadyCallback {
        void onWebRtcAudioRecordSamplesReady(AudioSamples audioSamples);
    }

    private static native long nativeCreateAudioDeviceModule(Context context, AudioManager audioManager, WebRtcAudioRecord webRtcAudioRecord, WebRtcAudioTrack webRtcAudioTrack, int i, int i2, boolean z, boolean z2);

    public static Builder builder(Context context) {
        return new Builder(context);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Builder {
        private int audioFormat;
        private final AudioManager audioManager;
        private AudioRecordErrorCallback audioRecordErrorCallback;
        private AudioRecordStateCallback audioRecordStateCallback;
        private int audioSource;
        private AudioTrackErrorCallback audioTrackErrorCallback;
        private AudioTrackStateCallback audioTrackStateCallback;
        private final Context context;
        private int inputSampleRate;
        private int outputSampleRate;
        private SamplesReadyCallback samplesReadyCallback;
        private boolean useHardwareAcousticEchoCanceler;
        private boolean useHardwareNoiseSuppressor;
        private boolean useStereoInput;
        private boolean useStereoOutput;

        private Builder(Context context) {
            this.audioSource = 7;
            this.audioFormat = 2;
            this.useHardwareAcousticEchoCanceler = JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported();
            this.useHardwareNoiseSuppressor = JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported();
            this.context = context;
            this.audioManager = (AudioManager) context.getSystemService("audio");
            this.inputSampleRate = WebRtcAudioManager.getSampleRate(this.audioManager);
            this.outputSampleRate = WebRtcAudioManager.getSampleRate(this.audioManager);
        }

        public Builder setSampleRate(int i) {
            Logging.m206d("JavaAudioDeviceModule", "Input/Output sample rate overridden to: " + i);
            this.inputSampleRate = i;
            this.outputSampleRate = i;
            return this;
        }

        public Builder setInputSampleRate(int i) {
            Logging.m206d("JavaAudioDeviceModule", "Input sample rate overridden to: " + i);
            this.inputSampleRate = i;
            return this;
        }

        public Builder setOutputSampleRate(int i) {
            Logging.m206d("JavaAudioDeviceModule", "Output sample rate overridden to: " + i);
            this.outputSampleRate = i;
            return this;
        }

        public Builder setAudioSource(int i) {
            this.audioSource = i;
            return this;
        }

        public Builder setAudioFormat(int i) {
            this.audioFormat = i;
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

        public Builder setSamplesReadyCallback(SamplesReadyCallback samplesReadyCallback) {
            this.samplesReadyCallback = samplesReadyCallback;
            return this;
        }

        public Builder setAudioTrackStateCallback(AudioTrackStateCallback audioTrackStateCallback) {
            this.audioTrackStateCallback = audioTrackStateCallback;
            return this;
        }

        public Builder setAudioRecordStateCallback(AudioRecordStateCallback audioRecordStateCallback) {
            this.audioRecordStateCallback = audioRecordStateCallback;
            return this;
        }

        public Builder setUseHardwareNoiseSuppressor(boolean z) {
            if (z && !JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported()) {
                Logging.m205e("JavaAudioDeviceModule", "HW NS not supported");
                z = false;
            }
            this.useHardwareNoiseSuppressor = z;
            return this;
        }

        public Builder setUseHardwareAcousticEchoCanceler(boolean z) {
            if (z && !JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported()) {
                Logging.m205e("JavaAudioDeviceModule", "HW AEC not supported");
                z = false;
            }
            this.useHardwareAcousticEchoCanceler = z;
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

        public JavaAudioDeviceModule createAudioDeviceModule() {
            Logging.m206d("JavaAudioDeviceModule", "createAudioDeviceModule");
            if (this.useHardwareNoiseSuppressor) {
                Logging.m206d("JavaAudioDeviceModule", "HW NS will be used.");
            } else {
                if (JavaAudioDeviceModule.isBuiltInNoiseSuppressorSupported()) {
                    Logging.m206d("JavaAudioDeviceModule", "Overriding default behavior; now using WebRTC NS!");
                }
                Logging.m206d("JavaAudioDeviceModule", "HW NS will not be used.");
            }
            if (this.useHardwareAcousticEchoCanceler) {
                Logging.m206d("JavaAudioDeviceModule", "HW AEC will be used.");
            } else {
                if (JavaAudioDeviceModule.isBuiltInAcousticEchoCancelerSupported()) {
                    Logging.m206d("JavaAudioDeviceModule", "Overriding default behavior; now using WebRTC AEC!");
                }
                Logging.m206d("JavaAudioDeviceModule", "HW AEC will not be used.");
            }
            return new JavaAudioDeviceModule(this.context, this.audioManager, new WebRtcAudioRecord(this.context, this.audioManager, this.audioSource, this.audioFormat, this.audioRecordErrorCallback, this.audioRecordStateCallback, this.samplesReadyCallback, this.useHardwareAcousticEchoCanceler, this.useHardwareNoiseSuppressor), new WebRtcAudioTrack(this.context, this.audioManager, this.audioTrackErrorCallback, this.audioTrackStateCallback), this.inputSampleRate, this.outputSampleRate, this.useStereoInput, this.useStereoOutput);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
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
        this.context = context;
        this.audioManager = audioManager;
        this.audioInput = webRtcAudioRecord;
        this.audioOutput = webRtcAudioTrack;
        this.inputSampleRate = i;
        this.outputSampleRate = i2;
        this.useStereoInput = z;
        this.useStereoOutput = z2;
    }

    @Override // org.webrtc.audio.AudioDeviceModule
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

    @Override // org.webrtc.audio.AudioDeviceModule
    public void release() {
        synchronized (this.nativeLock) {
            if (this.nativeAudioDeviceModule != 0) {
                JniCommon.nativeReleaseRef(this.nativeAudioDeviceModule);
                this.nativeAudioDeviceModule = 0L;
            }
        }
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setSpeakerMute(boolean z) {
        Logging.m206d("JavaAudioDeviceModule", "setSpeakerMute: " + z);
        this.audioOutput.setSpeakerMute(z);
    }

    @Override // org.webrtc.audio.AudioDeviceModule
    public void setMicrophoneMute(boolean z) {
        Logging.m206d("JavaAudioDeviceModule", "setMicrophoneMute: " + z);
        this.audioInput.setMicrophoneMute(z);
    }

    @RequiresApi(23)
    public void setPreferredInputDevice(AudioDeviceInfo audioDeviceInfo) {
        Logging.m206d("JavaAudioDeviceModule", "setPreferredInputDevice: " + audioDeviceInfo);
        this.audioInput.setPreferredDevice(audioDeviceInfo);
    }
}
