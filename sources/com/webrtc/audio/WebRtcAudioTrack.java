package com.webrtc.audio;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import android.support.annotation.Nullable;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.baidu.rtc.logreport.SLIReportInterface;
import com.baidu.rtc.logreport.StuckDataCalculator;
import com.webrtc.CalledByNative;
import com.webrtc.Logging;
import com.webrtc.ThreadUtils;
import com.webrtc.audio.JavaAudioDeviceModule;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WebRtcAudioTrack {
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    public static final int DEFAULT_AUDIO_CONTENT_TYPE = 1;
    private static final String TAG = "WebRtcAudioTrackExternal";
    private int audioFormat;
    private final AudioManager audioManager;
    private volatile JavaAudioDeviceModule.RemoteSamplesReadyCallback audioSamplesReadyCallback;
    private AudioTrackThread audioThread;
    private AudioTrack audioTrack;
    private ByteBuffer byteBuffer;
    private int channelConfig;
    private final Context context;
    private byte[] emptyBytes;
    private final JavaAudioDeviceModule.AudioTrackErrorCallback errorCallback;
    private int initialBufferSizeInFrames;
    private boolean isEnableSLIReport;
    private long nativeAudioTrack;
    private JavaAudioDeviceModule.RemoteAudioTrackCallback remoteAudioTrackCallback;
    private int sampleRateInHz;
    private volatile boolean speakerMute;
    private StuckDataCalculator stuckDataCalculator;
    private final ThreadUtils.ThreadChecker threadChecker;
    private final VolumeLogger volumeLogger;
    private static final int DEFAULT_USAGE = getDefaultUsageAttribute();
    private static int audioContentType = 1;

    private int channelCountToConfiguration(int i) {
        return i == 1 ? 4 : 12;
    }

    @TargetApi(21)
    private static int getDefaultUsageAttributeOnLollipopOrHigher() {
        return 2;
    }

    private static native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeGetPlayoutData(long j, int i);

    private static int getDefaultUsageAttribute() {
        if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return getDefaultUsageAttributeOnLollipopOrHigher();
        }
        return 0;
    }

    @CalledByNative
    WebRtcAudioTrack(Context context, AudioManager audioManager) {
        this(context, audioManager, 1, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebRtcAudioTrack(Context context, AudioManager audioManager, int i, @Nullable JavaAudioDeviceModule.RemoteSamplesReadyCallback remoteSamplesReadyCallback, JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback) {
        this.stuckDataCalculator = new StuckDataCalculator(200);
        this.isEnableSLIReport = false;
        this.threadChecker = new ThreadUtils.ThreadChecker();
        this.audioTrack = null;
        this.audioThread = null;
        this.speakerMute = false;
        this.threadChecker.detachThread();
        this.context = context;
        this.audioManager = audioManager;
        audioContentType = i;
        this.errorCallback = audioTrackErrorCallback;
        this.audioSamplesReadyCallback = remoteSamplesReadyCallback;
        this.volumeLogger = new VolumeLogger(audioManager);
    }

    public void setStuckEventListener(SLIReportInterface sLIReportInterface) {
        this.stuckDataCalculator.setStuckEventListener(sLIReportInterface);
    }

    public void setEnableSLIReport(boolean z) {
        this.isEnableSLIReport = z;
    }

    public void setAudioTrackCallback(JavaAudioDeviceModule.RemoteAudioTrackCallback remoteAudioTrackCallback) {
        this.remoteAudioTrackCallback = remoteAudioTrackCallback;
    }

    public void setRemoteSamplesReadyCallback(JavaAudioDeviceModule.RemoteSamplesReadyCallback remoteSamplesReadyCallback) {
        this.audioSamplesReadyCallback = remoteSamplesReadyCallback;
    }

    @CalledByNative
    public void setNativeAudioTrack(long j) {
        this.nativeAudioTrack = j;
    }

    @CalledByNative
    private int initPlayout(int i, int i2, double d) {
        this.threadChecker.checkIsOnValidThread();
        Logging.m5305d(TAG, "initPlayout(sampleRate=" + i + ", channels=" + i2 + ", bufferSizeFactor=" + d + ", contentType=" + audioContentType + ")");
        this.byteBuffer = ByteBuffer.allocateDirect(i2 * 2 * (i / 100));
        StringBuilder sb = new StringBuilder();
        sb.append("byteBuffer.capacity: ");
        sb.append(this.byteBuffer.capacity());
        Logging.m5305d(TAG, sb.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioTrack, this.byteBuffer);
        int channelCountToConfiguration = channelCountToConfiguration(i2);
        int minBufferSize = (int) (((double) AudioTrack.getMinBufferSize(i, channelCountToConfiguration, 2)) * d);
        this.sampleRateInHz = i;
        this.channelConfig = channelCountToConfiguration;
        this.audioFormat = 2;
        Logging.m5305d(TAG, "minBufferSizeInBytes: " + minBufferSize);
        if (minBufferSize < this.byteBuffer.capacity()) {
            reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
            return -1;
        } else if (this.audioTrack != null) {
            reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
            return -1;
        } else {
            try {
                if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
                    this.audioTrack = createAudioTrackOnLollipopOrHigher(i, channelCountToConfiguration, minBufferSize);
                } else {
                    this.audioTrack = createAudioTrackOnLowerThanLollipop(i, channelCountToConfiguration, minBufferSize);
                }
                AudioTrack audioTrack = this.audioTrack;
                if (audioTrack == null || audioTrack.getState() != 1) {
                    reportWebRtcAudioTrackInitError("Initialization of audio track failed.");
                    releaseAudioResources();
                    return -1;
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    this.initialBufferSizeInFrames = this.audioTrack.getBufferSizeInFrames();
                } else {
                    this.initialBufferSizeInFrames = -1;
                }
                logMainParameters();
                logMainParametersExtended();
                RtcLogCapturer.reportLog(RtcLogEvent.INIT_AUDIO_TRACK_SUCCESS, "WebRtcAudioTrack-initPlayout");
                return minBufferSize;
            } catch (IllegalArgumentException e) {
                reportWebRtcAudioTrackInitError(e.getMessage());
                releaseAudioResources();
                return -1;
            }
        }
    }

    @CalledByNative
    private boolean startPlayout() {
        this.threadChecker.checkIsOnValidThread();
        this.volumeLogger.start();
        Logging.m5305d(TAG, "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_AUDIO_TRACK_START, "WebRtcAudioTrack-startPlayout");
        this.audioThread = new AudioTrackThread("AudioTrackJavaThread");
        this.audioThread.start();
        JavaAudioDeviceModule.RemoteAudioTrackCallback remoteAudioTrackCallback = this.remoteAudioTrackCallback;
        if (remoteAudioTrackCallback != null) {
            remoteAudioTrackCallback.onStartPlay();
        }
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_AUDIO_TRACK_SUCCESS, "WebRtcAudioTrack-startPlayout");
        return true;
    }

    @CalledByNative
    private boolean stopPlayout() {
        JavaAudioDeviceModule.RemoteAudioTrackCallback remoteAudioTrackCallback = this.remoteAudioTrackCallback;
        if (remoteAudioTrackCallback != null) {
            remoteAudioTrackCallback.onStopPlay();
        }
        this.threadChecker.checkIsOnValidThread();
        this.volumeLogger.stop();
        Logging.m5305d(TAG, "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.stopThread();
        Logging.m5305d(TAG, "Stopping the AudioTrackThread...");
        this.audioThread.interrupt();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000L)) {
            Logging.m5304e(TAG, "Join of AudioTrackThread timed out.");
            WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        }
        Logging.m5305d(TAG, "AudioTrackThread has now been stopped.");
        this.audioThread = null;
        releaseAudioResources();
        return true;
    }

    @CalledByNative
    private int getStreamMaxVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.m5305d(TAG, "getStreamMaxVolume");
        return this.audioManager.getStreamMaxVolume(0);
    }

    @CalledByNative
    private boolean setStreamVolume(int i) {
        this.threadChecker.checkIsOnValidThread();
        Logging.m5305d(TAG, "setStreamVolume(" + i + ")");
        if (isVolumeFixed()) {
            Logging.m5304e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, i, 0);
        return true;
    }

    @SuppressLint({"NewApi"})
    private boolean isVolumeFixed() {
        if (WebRtcAudioUtils.runningOnLollipopOrHigher()) {
            return this.audioManager.isVolumeFixed();
        }
        return false;
    }

    @CalledByNative
    private int getStreamVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.m5305d(TAG, "getStreamVolume");
        return this.audioManager.getStreamVolume(0);
    }

    @CalledByNative
    private int GetPlayoutUnderrunCount() {
        if (Build.VERSION.SDK_INT >= 24) {
            AudioTrack audioTrack = this.audioTrack;
            if (audioTrack != null) {
                return audioTrack.getUnderrunCount();
            }
            return -1;
        }
        return -2;
    }

    private void logMainParameters() {
        Logging.m5305d(TAG, "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    @TargetApi(21)
    private static AudioTrack createAudioTrackOnLollipopOrHigher(int i, int i2, int i3) {
        Logging.m5305d(TAG, "createAudioTrackOnLollipopOrHigher");
        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(0);
        Logging.m5305d(TAG, "nativeOutputSampleRate: " + nativeOutputSampleRate);
        if (i != nativeOutputSampleRate) {
            Logging.m5301w(TAG, "Unable to use fast mode since requested sample rate is not native");
        }
        String str = Build.MODEL;
        if (audioContentType == 2 || str.contains("NV6001") || str.contains("NV6101") || str.contains("XDH-0F-A1") || str.contains("NV5001")) {
            return new AudioTrack(new AudioAttributes.Builder().setUsage(1).setContentType(2).build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(i2).build(), i3, 1, 0);
        }
        return new AudioTrack(new AudioAttributes.Builder().setUsage(DEFAULT_USAGE).setContentType(1).build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(i2).build(), i3, 1, 0);
    }

    private static AudioTrack createAudioTrackOnLowerThanLollipop(int i, int i2, int i3) {
        return new AudioTrack(0, i, i2, 2, i3, 1);
    }

    private void logBufferSizeInFrames() {
        if (Build.VERSION.SDK_INT >= 23) {
            Logging.m5305d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
    }

    @CalledByNative
    private int getBufferSizeInFrames() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.audioTrack.getBufferSizeInFrames();
        }
        return -1;
    }

    @CalledByNative
    private int getInitialBufferSizeInFrames() {
        return this.initialBufferSizeInFrames;
    }

    private void logBufferCapacityInFrames() {
        if (Build.VERSION.SDK_INT >= 24) {
            Logging.m5305d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    @TargetApi(24)
    private void logMainParametersExtended() {
        if (WebRtcAudioUtils.runningOnMarshmallowOrHigher()) {
            Logging.m5305d(TAG, "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.m5305d(TAG, "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    @TargetApi(24)
    private void logUnderrunCount() {
        if (WebRtcAudioUtils.runningOnNougatOrHigher()) {
            Logging.m5305d(TAG, "underrun count: " + this.audioTrack.getUnderrunCount());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public void setSpeakerMute(boolean z) {
        Logging.m5301w(TAG, "setSpeakerMute(" + z + ")");
        this.speakerMute = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAudioResources() {
        Logging.m5305d(TAG, "releaseAudioResources");
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this.audioTrack = null;
        }
    }

    private void reportWebRtcAudioTrackInitError(String str) {
        Logging.m5304e(TAG, "Init playout error: " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackInitError(str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.INIT_AUDIO_TRACK_FAILED, "WebRtcAudioTrack-initPlayout", -1, RtcLogCapturer.getErrorInfo(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode, String str) {
        Logging.m5304e(TAG, "Start playout error: " + audioTrackStartErrorCode + ". " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackStartError(audioTrackStartErrorCode, str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.OPEN_AUDIO_TRACK_FAILED, "WebRtcAudioTrack-startPlayout", audioTrackStartErrorCode, RtcLogCapturer.getErrorInfo(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackError(String str) {
        Logging.m5304e(TAG, "Run-time playback error: " + str);
        WebRtcAudioUtils.logAudioState(TAG, this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackError(str);
        }
        RtcLogCapturer.reportLog(RtcLogEvent.AUDIO_TRACK_ERROR, "AUDIO_TRACK_ERROR", -1, RtcLogCapturer.getErrorInfo(str));
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class AudioTrackThread extends Thread {
        private volatile boolean keepAlive;

        public AudioTrackThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.m5305d(WebRtcAudioTrack.TAG, "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            try {
                Thread.sleep(600L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                WebRtcAudioTrack.this.audioTrack.play();
                if (WebRtcAudioTrack.this.audioTrack.getPlayState() == 3) {
                    WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 3);
                    int capacity = WebRtcAudioTrack.this.byteBuffer.capacity();
                    while (this.keepAlive) {
                        WebRtcAudioTrack.nativeGetPlayoutData(WebRtcAudioTrack.this.nativeAudioTrack, capacity);
                        WebRtcAudioTrack.assertTrue(capacity <= WebRtcAudioTrack.this.byteBuffer.remaining());
                        if (WebRtcAudioTrack.this.speakerMute) {
                            WebRtcAudioTrack.this.byteBuffer.clear();
                            WebRtcAudioTrack.this.byteBuffer.put(WebRtcAudioTrack.this.emptyBytes);
                            WebRtcAudioTrack.this.byteBuffer.position(0);
                        }
                        if (WebRtcAudioTrack.this.isEnableSLIReport) {
                            WebRtcAudioTrack.this.stuckDataCalculator.calculateStuck();
                        }
                        if (WebRtcAudioTrack.this.audioSamplesReadyCallback != null) {
                            byte[] copyOfRange = Arrays.copyOfRange(WebRtcAudioTrack.this.byteBuffer.array(), WebRtcAudioTrack.this.byteBuffer.arrayOffset(), WebRtcAudioTrack.this.byteBuffer.capacity() + WebRtcAudioTrack.this.byteBuffer.arrayOffset());
                            if (WebRtcAudioTrack.this.audioSamplesReadyCallback != null) {
                                WebRtcAudioTrack.this.audioSamplesReadyCallback.onWebRtcAudioRemoteSamplesReady(new JavaAudioDeviceModule.AudioSamples(WebRtcAudioTrack.this.audioFormat, WebRtcAudioTrack.this.channelConfig, WebRtcAudioTrack.this.sampleRateInHz, copyOfRange));
                            }
                        }
                        int writeOnLollipop = WebRtcAudioUtils.runningOnLollipopOrHigher() ? writeOnLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, capacity) : writePreLollipop(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, capacity);
                        if (writeOnLollipop != capacity) {
                            Logging.m5304e(WebRtcAudioTrack.TAG, "AudioTrack.write played invalid number of bytes: " + writeOnLollipop);
                            if (writeOnLollipop < 0) {
                                this.keepAlive = false;
                                WebRtcAudioTrack webRtcAudioTrack = WebRtcAudioTrack.this;
                                webRtcAudioTrack.reportWebRtcAudioTrackError("AudioTrack.write failed: " + writeOnLollipop);
                            }
                        }
                        WebRtcAudioTrack.this.byteBuffer.rewind();
                    }
                    if (WebRtcAudioTrack.this.audioTrack != null) {
                        Logging.m5305d(WebRtcAudioTrack.TAG, "Calling AudioTrack.stop...");
                        try {
                            WebRtcAudioTrack.this.audioTrack.stop();
                            Logging.m5305d(WebRtcAudioTrack.TAG, "AudioTrack.stop is done.");
                            return;
                        } catch (IllegalStateException e2) {
                            Logging.m5304e(WebRtcAudioTrack.TAG, "AudioTrack.stop failed: " + e2.getMessage());
                            return;
                        }
                    }
                    return;
                }
                WebRtcAudioTrack webRtcAudioTrack2 = WebRtcAudioTrack.this;
                JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode = JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_STATE_MISMATCH;
                webRtcAudioTrack2.reportWebRtcAudioTrackStartError(audioTrackStartErrorCode, "AudioTrack.play failed - incorrect state :" + WebRtcAudioTrack.this.audioTrack.getPlayState());
                WebRtcAudioTrack.this.releaseAudioResources();
            } catch (IllegalStateException e3) {
                WebRtcAudioTrack webRtcAudioTrack3 = WebRtcAudioTrack.this;
                JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode2 = JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_EXCEPTION;
                webRtcAudioTrack3.reportWebRtcAudioTrackStartError(audioTrackStartErrorCode2, "AudioTrack.play failed: " + e3.getMessage());
                WebRtcAudioTrack.this.releaseAudioResources();
            }
        }

        @TargetApi(21)
        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer, i, 0);
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i);
        }

        public void stopThread() {
            Logging.m5305d(WebRtcAudioTrack.TAG, "stopThread");
            this.keepAlive = false;
            WebRtcAudioTrack.this.stuckDataCalculator.reset();
        }
    }
}
