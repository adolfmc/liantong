package com.webrtc.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.os.Build;
import android.os.Process;
import com.baidu.rtc.logreport.ErrorInfoReport;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.baidu.rtc.recorder.RtcAudioRecordListener;
import com.webrtc.CalledByNative;
import com.webrtc.Logging;
import com.webrtc.ThreadUtils;
import com.webrtc.audio.JavaAudioDeviceModule;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WebRtcAudioRecord {
    private static final long AUDIO_RECORD_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int BUFFER_SIZE_FACTOR = 2;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    public static final int DEFAULT_AUDIO_SOURCE = 7;
    public static final int EXT_RECORDSTATE_RECORDING = 3;
    public static final int EXT_RECORDSTATE_STOPPED = 1;
    public static final int EXT_STATE_INITIALIZED = 1;
    public static final int EXT_STATE_UNINITIALIZED = 0;
    private static final String TAG = "WebRtcAudioRecordExternal";
    private final JavaAudioDeviceModule.SamplesReadyCallback aecAudioSamplesReadyCallback;
    private ByteBuffer aecByteBuffer;
    private final AudioManager audioManager;
    private AudioRecord audioRecord;
    private final JavaAudioDeviceModule.SamplesReadyCallback audioSamplesReadyCallback;
    private final int audioSource;
    private final AtomicReference<Boolean> audioSourceMatchesRecordingSessionRef;
    private AudioRecordThread audioThread;
    private ByteBuffer byteBuffer;
    private final Context context;
    private final WebRtcAudioEffects effects;
    private byte[] emptyBytes;
    private final JavaAudioDeviceModule.AudioRecordErrorCallback errorCallback;
    private final boolean externalAudioRecord;
    JavaAudioDeviceModule.ExternalSamplesReadyCallback externalSamplesCallback;
    private final boolean isAcousticEchoCancelerSupported;
    private final boolean isNoiseSuppressorSupported;
    private boolean isStartRecord;
    private RtcAudioRecordListener mAudioRecordListener;
    private boolean mEnableAudioEffect;
    private boolean mEnalbeBd3A;
    private int mExtRecordState;
    private final Object mExtRecordStateLock;
    private volatile boolean microphoneMute;
    private long nativeAudioRecord;

    private int channelCountToConfiguration(int i) {
        return i == 1 ? 16 : 12;
    }

    private native void nativeCacheAecRecordedBufferAddress(long j, ByteBuffer byteBuffer);

    private native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeDataIsRecorded(long j, int i);

    public void setAudioRecordListener(RtcAudioRecordListener rtcAudioRecordListener) {
        this.mAudioRecordListener = rtcAudioRecordListener;
    }

    @CalledByNative
    WebRtcAudioRecord(Context context, AudioManager audioManager) {
        this(context, audioManager, 7, false, false, null, null, null, WebRtcAudioEffects.isAcousticEchoCancelerSupported(), WebRtcAudioEffects.isNoiseSuppressorSupported(), false);
    }

    public WebRtcAudioRecord(Context context, AudioManager audioManager, int i, boolean z, boolean z2, JavaAudioDeviceModule.AudioRecordErrorCallback audioRecordErrorCallback, JavaAudioDeviceModule.SamplesReadyCallback samplesReadyCallback, JavaAudioDeviceModule.SamplesReadyCallback samplesReadyCallback2, boolean z3, boolean z4, boolean z5) {
        this.mExtRecordState = 0;
        this.mExtRecordStateLock = new Object();
        this.audioSourceMatchesRecordingSessionRef = new AtomicReference<>();
        this.externalSamplesCallback = new JavaAudioDeviceModule.ExternalSamplesReadyCallback() { // from class: com.webrtc.audio.WebRtcAudioRecord.1
            @Override // com.webrtc.audio.JavaAudioDeviceModule.ExternalSamplesReadyCallback
            public void onWebRtcAudioExternalSamplesReady(JavaAudioDeviceModule.AudioSamples audioSamples) {
                byte[] data;
                if (audioSamples == null || audioSamples.getData() == null || !WebRtcAudioRecord.this.isStartRecord) {
                    return;
                }
                int length = audioSamples.getData().length;
                if (WebRtcAudioRecord.this.byteBuffer == null || length != WebRtcAudioRecord.this.byteBuffer.capacity()) {
                    return;
                }
                if (WebRtcAudioRecord.this.microphoneMute) {
                    data = WebRtcAudioRecord.this.emptyBytes;
                } else {
                    data = audioSamples.getData();
                }
                WebRtcAudioRecord.this.byteBuffer.clear();
                WebRtcAudioRecord.this.byteBuffer.put(data, 0, length);
                WebRtcAudioRecord.this.byteBuffer.flip();
                WebRtcAudioRecord webRtcAudioRecord = WebRtcAudioRecord.this;
                webRtcAudioRecord.nativeDataIsRecorded(webRtcAudioRecord.nativeAudioRecord, length);
                if (WebRtcAudioRecord.this.mAudioRecordListener != null) {
                    byte[] bArr = new byte[length];
                    System.arraycopy(data, 0, bArr, 0, length);
                    WebRtcAudioRecord.this.mAudioRecordListener.onAudioRecord(bArr, length);
                }
            }
        };
        this.effects = new WebRtcAudioEffects();
        this.audioRecord = null;
        this.audioThread = null;
        this.microphoneMute = false;
        this.externalAudioRecord = z;
        this.mEnableAudioEffect = z2;
        this.mEnalbeBd3A = z5;
        if (z3 && !WebRtcAudioEffects.isAcousticEchoCancelerSupported()) {
            throw new IllegalArgumentException("HW AEC not supported");
        }
        if (z4 && !WebRtcAudioEffects.isNoiseSuppressorSupported()) {
            throw new IllegalArgumentException("HW NS not supported");
        }
        this.context = context;
        this.audioManager = audioManager;
        this.audioSource = i;
        this.errorCallback = audioRecordErrorCallback;
        this.audioSamplesReadyCallback = samplesReadyCallback;
        this.aecAudioSamplesReadyCallback = samplesReadyCallback2;
        this.isAcousticEchoCancelerSupported = z3;
        this.isNoiseSuppressorSupported = z4;
    }

    @CalledByNative
    public void setNativeAudioRecord(long j) {
        this.nativeAudioRecord = j;
    }

    @CalledByNative
    boolean isAcousticEchoCancelerSupported() {
        return this.isAcousticEchoCancelerSupported;
    }

    @CalledByNative
    boolean isNoiseSuppressorSupported() {
        return this.isNoiseSuppressorSupported;
    }

    @CalledByNative
    private boolean enableBuiltInAEC(boolean z) {
        Logging.m5305d(TAG, "enableBuiltInAEC(" + z + ")");
        if (this.externalAudioRecord) {
            Logging.m5304e(TAG, "Can not support builtIn AEC in external record mode");
            return false;
        }
        return this.effects.setAEC(z);
    }

    @CalledByNative
    private boolean enableBuiltInNS(boolean z) {
        Logging.m5305d(TAG, "enableBuiltInNS(" + z + ")");
        if (this.externalAudioRecord) {
            Logging.m5304e(TAG, "Can not support builtIn NS in external record mode");
            return false;
        }
        return this.effects.setNS(z);
    }

    @CalledByNative
    boolean isAudioConfigVerified() {
        return this.audioSourceMatchesRecordingSessionRef.get() != null;
    }

    @CalledByNative
    boolean isAudioSourceMatchingRecordingSession() {
        Boolean bool = this.audioSourceMatchesRecordingSessionRef.get();
        if (bool == null) {
            Logging.m5301w(TAG, "Audio configuration has not yet been verified");
            return false;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @CalledByNative
    public void onAecRecordedDataCallback() {
        if (this.aecAudioSamplesReadyCallback != null) {
            this.aecAudioSamplesReadyCallback.onWebRtcAudioRecordSamplesReady(new JavaAudioDeviceModule.AudioSamples(this.audioRecord.getAudioFormat(), this.audioRecord.getChannelCount(), this.audioRecord.getSampleRate(), Arrays.copyOfRange(this.aecByteBuffer.array(), this.aecByteBuffer.arrayOffset(), this.aecByteBuffer.capacity() + this.aecByteBuffer.arrayOffset())));
        }
    }

    @CalledByNative
    private int initRecording(int i, int i2) {
        Logging.m5305d(TAG, "initRecording(sampleRate=" + i + ", audioSource=" + this.audioSource + ", channels=" + i2 + ")");
        if (this.audioRecord != null || (this.externalAudioRecord && this.mExtRecordState != 0)) {
            reportWebRtcAudioRecordInitError("InitRecording called twice without StopRecording.");
            return -1;
        }
        int i3 = i / 100;
        int i4 = i2 * 2 * i3;
        this.byteBuffer = ByteBuffer.allocateDirect(i4);
        this.aecByteBuffer = ByteBuffer.allocateDirect(i4);
        if (!this.byteBuffer.hasArray()) {
            reportWebRtcAudioRecordInitError("ByteBuffer does not have backing array.");
            return -1;
        }
        Logging.m5305d(TAG, "byteBuffer.capacity: " + this.byteBuffer.capacity());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioRecord, this.byteBuffer);
        nativeCacheAecRecordedBufferAddress(this.nativeAudioRecord, this.aecByteBuffer);
        int channelCountToConfiguration = channelCountToConfiguration(i2);
        int minBufferSize = AudioRecord.getMinBufferSize(i, channelCountToConfiguration, 2);
        if (minBufferSize != -1 && minBufferSize != -2) {
            Logging.m5305d(TAG, "AudioRecord.getMinBufferSize: " + minBufferSize);
            int max = Math.max(minBufferSize * 2, this.byteBuffer.capacity());
            Logging.m5305d(TAG, "bufferSizeInBytes: " + max);
            if (this.externalAudioRecord) {
                this.mExtRecordState = 1;
                return i3;
            }
            try {
                this.audioRecord = new AudioRecord(this.audioSource, i, channelCountToConfiguration, 2, max);
                this.audioSourceMatchesRecordingSessionRef.set(null);
                RtcLogCapturer.reportLog(RtcLogEvent.INIT_AUDIO_RECORD_SUCCESS, "WebRtcAudioRecord-initRecording");
                AudioRecord audioRecord = this.audioRecord;
                if (audioRecord != null && audioRecord.getState() == 1) {
                    this.effects.enable(this.audioRecord.getAudioSessionId());
                    logMainParameters();
                    logMainParametersExtended();
                    int logRecordingConfigurations = logRecordingConfigurations(this.audioRecord, false);
                    if (logRecordingConfigurations != 0) {
                        Logging.m5301w(TAG, "Potential microphone conflict. Active sessions: " + logRecordingConfigurations);
                    }
                    return i3;
                }
                reportWebRtcAudioRecordInitError("Failed to create a new AudioRecord instance");
                releaseAudioResources();
                return -1;
            } catch (IllegalArgumentException e) {
                reportWebRtcAudioRecordInitError("AudioRecord ctor error: " + e.getMessage());
                releaseAudioResources();
                return -1;
            }
        }
        reportWebRtcAudioRecordInitError("AudioRecord.getMinBufferSize failed: " + minBufferSize);
        return -1;
    }

    @CalledByNative
    private boolean startRecording() {
        Logging.m5305d(TAG, "startRecording");
        this.isStartRecord = true;
        JavaAudioDeviceModule.SamplesReadyCallback samplesReadyCallback = this.audioSamplesReadyCallback;
        if (samplesReadyCallback != null) {
            samplesReadyCallback.onStartRecord();
        }
        if (this.externalAudioRecord) {
            this.mExtRecordState = 3;
            return true;
        }
        assertTrue(this.audioRecord != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() != 3) {
                JavaAudioDeviceModule.AudioRecordStartErrorCode audioRecordStartErrorCode = JavaAudioDeviceModule.AudioRecordStartErrorCode.AUDIO_RECORD_START_STATE_MISMATCH;
                reportWebRtcAudioRecordStartError(audioRecordStartErrorCode, "AudioRecord.startRecording failed - incorrect state :" + this.audioRecord.getRecordingState());
                return false;
            }
            this.audioThread = new AudioRecordThread("AudioRecordJavaThread");
            this.audioThread.start();
            RtcLogCapturer.reportLog(RtcLogEvent.OPEN_AUDIO_RECORD_SUCCESS, "WebRtcAudioRecord-startRecording");
            return true;
        } catch (IllegalStateException e) {
            JavaAudioDeviceModule.AudioRecordStartErrorCode audioRecordStartErrorCode2 = JavaAudioDeviceModule.AudioRecordStartErrorCode.AUDIO_RECORD_START_EXCEPTION;
            reportWebRtcAudioRecordStartError(audioRecordStartErrorCode2, "AudioRecord.startRecording failed: " + e.getMessage());
            return false;
        }
    }

    public synchronized void reStartRecord() {
        Logging.m5305d(TAG, "self reStartRecord");
        if (this.audioThread == null) {
            return;
        }
        this.audioThread.muteDevice(false);
    }

    public synchronized void stopRecord() {
        Logging.m5305d(TAG, "self stopRecord");
        if (this.audioThread == null) {
            return;
        }
        this.audioThread.muteDevice(true);
    }

    @CalledByNative
    private boolean stopRecording() {
        Logging.m5305d(TAG, "stopRecording");
        this.isStartRecord = false;
        JavaAudioDeviceModule.SamplesReadyCallback samplesReadyCallback = this.audioSamplesReadyCallback;
        if (samplesReadyCallback != null) {
            samplesReadyCallback.onStopRecord();
        }
        if (this.externalAudioRecord) {
            this.mExtRecordState = 0;
            return true;
        }
        assertTrue(this.audioThread != null);
        this.audioThread.stopThread();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000L)) {
            Logging.m5304e(TAG, "Join of AudioRecordJavaThread timed out");
            WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        }
        this.audioThread = null;
        this.effects.release();
        releaseAudioResources();
        return true;
    }

    private int getExtRecordState() {
        int i;
        synchronized (this.mExtRecordStateLock) {
            i = this.mExtRecordState;
        }
        return i;
    }

    private void logMainParameters() {
        Logging.m5305d(TAG, "AudioRecord: session ID: " + this.audioRecord.getAudioSessionId() + ", channels: " + this.audioRecord.getChannelCount() + ", sample rate: " + this.audioRecord.getSampleRate());
    }

    @TargetApi(23)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            Logging.m5305d(TAG, "AudioRecord: buffer size in frames: " + this.audioRecord.getBufferSizeInFrames());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    @TargetApi(24)
    private int logRecordingConfigurations(AudioRecord audioRecord, boolean z) {
        if (Build.VERSION.SDK_INT < 24) {
            Logging.m5301w(TAG, "AudioManager#getActiveRecordingConfigurations() requires N or higher");
            return 0;
        } else if (audioRecord == null) {
            return 0;
        } else {
            List<AudioRecordingConfiguration> activeRecordingConfigurations = this.audioManager.getActiveRecordingConfigurations();
            int size = activeRecordingConfigurations.size();
            Logging.m5305d(TAG, "Number of active recording sessions: " + size);
            if (size > 0) {
                logActiveRecordingConfigs(audioRecord.getAudioSessionId(), activeRecordingConfigurations);
                if (z) {
                    this.audioSourceMatchesRecordingSessionRef.set(Boolean.valueOf(verifyAudioConfig(audioRecord.getAudioSource(), audioRecord.getAudioSessionId(), audioRecord.getFormat(), audioRecord.getRoutedDevice(), activeRecordingConfigurations)));
                }
            }
            return size;
        }
    }

    public void setMicrophoneMute(boolean z) {
        Logging.m5301w(TAG, "setMicrophoneMute(" + z + ")");
        this.microphoneMute = z;
    }

    private void releaseAudioResources() {
        Logging.m5305d(TAG, "releaseAudioResources");
        AudioRecord audioRecord = this.audioRecord;
        if (audioRecord != null) {
            audioRecord.release();
            this.audioRecord = null;
        }
        this.audioSourceMatchesRecordingSessionRef.set(null);
    }

    private void reportWebRtcAudioRecordInitError(String str) {
        Logging.m5304e(TAG, "Init recording error: " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        logRecordingConfigurations(this.audioRecord, false);
        JavaAudioDeviceModule.AudioRecordErrorCallback audioRecordErrorCallback = this.errorCallback;
        if (audioRecordErrorCallback != null) {
            audioRecordErrorCallback.onWebRtcAudioRecordInitError(str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.INIT_AUDIO_RECORD_FAILED, "WebRtcAudioRecord-initRecording", -1, RtcLogCapturer.getErrorInfo(str));
    }

    private void reportWebRtcAudioRecordStartError(JavaAudioDeviceModule.AudioRecordStartErrorCode audioRecordStartErrorCode, String str) {
        Logging.m5304e(TAG, "Start recording error: " + audioRecordStartErrorCode + ". " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        logRecordingConfigurations(this.audioRecord, false);
        JavaAudioDeviceModule.AudioRecordErrorCallback audioRecordErrorCallback = this.errorCallback;
        if (audioRecordErrorCallback != null) {
            audioRecordErrorCallback.onWebRtcAudioRecordStartError(audioRecordStartErrorCode, str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_AUDIO_RECORD_FAILED, "WebRtcAudioRecord-startRecording", audioRecordStartErrorCode, RtcLogCapturer.getErrorInfo(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioRecordError(String str) {
        Logging.m5304e(TAG, "Run-time recording error: " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioRecordErrorCallback audioRecordErrorCallback = this.errorCallback;
        if (audioRecordErrorCallback != null) {
            audioRecordErrorCallback.onWebRtcAudioRecordError(str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.AUDIO_RECORD_ERROR, "WebRtcAudioRecord-reportWebRtcAudioRecordError", -1, RtcLogCapturer.getErrorInfo(str));
    }

    public JavaAudioDeviceModule.ExternalSamplesReadyCallback getExternalSamplesCallback() {
        return this.externalSamplesCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class AudioRecordThread extends Thread {
        private volatile boolean isMuteDevice;
        private volatile boolean keepAlive;
        private List<Runnable> mPendingTask;
        private Runnable mStartRunnable;
        private Runnable mStopRunnable;

        public AudioRecordThread(String str) {
            super(str);
            this.keepAlive = true;
            this.isMuteDevice = false;
            this.mPendingTask = new ArrayList();
            this.mStopRunnable = new Runnable() { // from class: com.webrtc.audio.WebRtcAudioRecord.AudioRecordThread.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        WebRtcAudioRecord.this.audioRecord.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            this.mStartRunnable = new Runnable() { // from class: com.webrtc.audio.WebRtcAudioRecord.AudioRecordThread.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        WebRtcAudioRecord.this.audioRecord.startRecording();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int read;
            Process.setThreadPriority(-19);
            Logging.m5305d(WebRtcAudioRecord.TAG, "AudioRecordThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioRecord.assertTrue(WebRtcAudioRecord.this.audioRecord.getRecordingState() == 3);
            System.nanoTime();
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(WebRtcAudioRecord.this.byteBuffer.capacity());
            while (this.keepAlive) {
                runTask();
                allocateDirect.clear();
                if (this.isMuteDevice) {
                    allocateDirect.put(WebRtcAudioRecord.this.emptyBytes);
                    read = allocateDirect.capacity();
                    try {
                        Thread.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    read = WebRtcAudioRecord.this.audioRecord.read(allocateDirect, allocateDirect.capacity());
                    if (this.isMuteDevice && read == 0) {
                        allocateDirect.clear();
                        allocateDirect.put(WebRtcAudioRecord.this.emptyBytes);
                        read = allocateDirect.capacity();
                        Logging.m5305d(WebRtcAudioRecord.TAG, "set bytesRead " + read);
                    }
                }
                if (read == allocateDirect.capacity()) {
                    if (WebRtcAudioRecord.this.microphoneMute) {
                        allocateDirect.clear();
                        allocateDirect.put(WebRtcAudioRecord.this.emptyBytes);
                    }
                    if (!WebRtcAudioRecord.this.mEnableAudioEffect && !WebRtcAudioRecord.this.mEnalbeBd3A && this.keepAlive) {
                        WebRtcAudioRecord.this.byteBuffer.clear();
                        WebRtcAudioRecord.this.byteBuffer.put(allocateDirect);
                        WebRtcAudioRecord webRtcAudioRecord = WebRtcAudioRecord.this;
                        webRtcAudioRecord.nativeDataIsRecorded(webRtcAudioRecord.nativeAudioRecord, read);
                        if (WebRtcAudioRecord.this.mAudioRecordListener != null) {
                            WebRtcAudioRecord.this.mAudioRecordListener.onAudioRecord(Arrays.copyOfRange(allocateDirect.array(), allocateDirect.arrayOffset(), allocateDirect.capacity() + allocateDirect.arrayOffset()), read);
                        }
                    }
                    WebRtcAudioRecord.this.onAecRecordedDataCallback();
                    if (WebRtcAudioRecord.this.audioSamplesReadyCallback != null) {
                        WebRtcAudioRecord.this.audioSamplesReadyCallback.onWebRtcAudioRecordSamplesReady(new JavaAudioDeviceModule.AudioSamples(WebRtcAudioRecord.this.audioRecord.getAudioFormat(), WebRtcAudioRecord.this.audioRecord.getChannelCount(), WebRtcAudioRecord.this.audioRecord.getSampleRate(), Arrays.copyOfRange(allocateDirect.array(), allocateDirect.arrayOffset(), allocateDirect.capacity() + allocateDirect.arrayOffset())));
                    }
                } else {
                    String str = "AudioRecord.read failed: " + read + " isMuteDevice : " + this.isMuteDevice;
                    Logging.m5304e(WebRtcAudioRecord.TAG, str);
                    if (read == -3) {
                        this.keepAlive = false;
                        WebRtcAudioRecord.this.reportWebRtcAudioRecordError(str);
                    }
                    if (read < 0) {
                        if (read != -6) {
                            switch (read) {
                                case -3:
                                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_RECORD_INVALID_OPERATION_ERROR);
                                    continue;
                                case -2:
                                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_RECORD_BAD_VALUE_ERROR);
                                    continue;
                                case -1:
                                    ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_RECORD_GENERIC_ERROR);
                                    continue;
                            }
                        } else {
                            ErrorInfoReport.getInstance().reportErrorInfo(ErrorInfoReport.ErrorCode.AUDIO_RECORD_READ_OBJECT_ERROR);
                        }
                    }
                }
            }
            try {
                if (WebRtcAudioRecord.this.audioRecord != null) {
                    WebRtcAudioRecord.this.audioRecord.stop();
                }
            } catch (IllegalStateException e2) {
                Logging.m5304e(WebRtcAudioRecord.TAG, "AudioRecord.stop failed: " + e2.getMessage());
            }
        }

        public void stopThread() {
            Logging.m5305d(WebRtcAudioRecord.TAG, "stopThread");
            this.keepAlive = false;
        }

        public synchronized void muteDevice(boolean z) {
            if (this.isMuteDevice == z) {
                return;
            }
            this.mPendingTask.clear();
            if (!this.isMuteDevice && z) {
                this.mPendingTask.add(this.mStopRunnable);
            } else {
                this.mPendingTask.add(this.mStartRunnable);
            }
            this.isMuteDevice = z;
        }

        private synchronized void runTask() {
            if (this.mPendingTask.size() == 0) {
                return;
            }
            Runnable runnable = this.mPendingTask.get(0);
            if (runnable != null) {
                runnable.run();
            }
            this.mPendingTask.remove(0);
        }
    }

    @TargetApi(24)
    private static boolean logActiveRecordingConfigs(int i, List<AudioRecordingConfiguration> list) {
        assertTrue(!list.isEmpty());
        Logging.m5305d(TAG, "AudioRecordingConfigurations: ");
        for (AudioRecordingConfiguration audioRecordingConfiguration : list) {
            StringBuilder sb = new StringBuilder();
            int clientAudioSource = audioRecordingConfiguration.getClientAudioSource();
            sb.append("  client audio source=");
            sb.append(WebRtcAudioUtils.audioSourceToString(clientAudioSource));
            sb.append(", client session id=");
            sb.append(audioRecordingConfiguration.getClientAudioSessionId());
            sb.append(" (");
            sb.append(i);
            sb.append(")");
            sb.append("\n");
            AudioFormat format = audioRecordingConfiguration.getFormat();
            sb.append("  Device AudioFormat: ");
            sb.append("channel count=");
            sb.append(format.getChannelCount());
            sb.append(", channel index mask=");
            sb.append(format.getChannelIndexMask());
            sb.append(", channel mask=");
            sb.append(WebRtcAudioUtils.channelMaskToString(format.getChannelMask()));
            sb.append(", encoding=");
            sb.append(WebRtcAudioUtils.audioEncodingToString(format.getEncoding()));
            sb.append(", sample rate=");
            sb.append(format.getSampleRate());
            sb.append("\n");
            AudioFormat clientFormat = audioRecordingConfiguration.getClientFormat();
            sb.append("  Client AudioFormat: ");
            sb.append("channel count=");
            sb.append(clientFormat.getChannelCount());
            sb.append(", channel index mask=");
            sb.append(clientFormat.getChannelIndexMask());
            sb.append(", channel mask=");
            sb.append(WebRtcAudioUtils.channelMaskToString(clientFormat.getChannelMask()));
            sb.append(", encoding=");
            sb.append(WebRtcAudioUtils.audioEncodingToString(clientFormat.getEncoding()));
            sb.append(", sample rate=");
            sb.append(clientFormat.getSampleRate());
            sb.append("\n");
            AudioDeviceInfo audioDevice = audioRecordingConfiguration.getAudioDevice();
            if (audioDevice != null) {
                assertTrue(audioDevice.isSource());
                sb.append("  AudioDevice: ");
                sb.append("type=");
                sb.append(WebRtcAudioUtils.deviceTypeToString(audioDevice.getType()));
                sb.append(", id=");
                sb.append(audioDevice.getId());
            }
            Logging.m5305d(TAG, sb.toString());
        }
        return true;
    }

    @TargetApi(24)
    private static boolean verifyAudioConfig(int i, int i2, AudioFormat audioFormat, AudioDeviceInfo audioDeviceInfo, List<AudioRecordingConfiguration> list) {
        assertTrue(!list.isEmpty());
        for (AudioRecordingConfiguration audioRecordingConfiguration : list) {
            AudioDeviceInfo audioDevice = audioRecordingConfiguration.getAudioDevice();
            if (audioDevice != null && audioRecordingConfiguration.getClientAudioSource() == i && audioRecordingConfiguration.getClientAudioSessionId() == i2 && audioRecordingConfiguration.getClientFormat().getEncoding() == audioFormat.getEncoding() && audioRecordingConfiguration.getClientFormat().getSampleRate() == audioFormat.getSampleRate() && audioRecordingConfiguration.getClientFormat().getChannelMask() == audioFormat.getChannelMask() && audioRecordingConfiguration.getClientFormat().getChannelIndexMask() == audioFormat.getChannelIndexMask() && audioRecordingConfiguration.getFormat().getEncoding() != 0 && audioRecordingConfiguration.getFormat().getSampleRate() > 0 && (audioRecordingConfiguration.getFormat().getChannelMask() != 0 || audioRecordingConfiguration.getFormat().getChannelIndexMask() != 0)) {
                if (checkDeviceMatch(audioDevice, audioDeviceInfo)) {
                    Logging.m5305d(TAG, "verifyAudioConfig: PASS");
                    return true;
                }
            }
        }
        Logging.m5304e(TAG, "verifyAudioConfig: FAILED");
        return false;
    }

    @TargetApi(24)
    private static boolean checkDeviceMatch(AudioDeviceInfo audioDeviceInfo, AudioDeviceInfo audioDeviceInfo2) {
        return audioDeviceInfo.getId() == audioDeviceInfo2.getId() && audioDeviceInfo.getType() == audioDeviceInfo2.getType();
    }
}
