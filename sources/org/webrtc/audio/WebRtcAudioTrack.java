package org.webrtc.audio;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Process;
import android.support.annotation.Nullable;
import java.nio.ByteBuffer;
import org.webrtc.CalledByNative;
import org.webrtc.Logging;
import org.webrtc.ThreadUtils;
import org.webrtc.audio.JavaAudioDeviceModule;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class WebRtcAudioTrack {
    private static final int AUDIO_TRACK_START = 0;
    private static final int AUDIO_TRACK_STOP = 1;
    private static final long AUDIO_TRACK_THREAD_JOIN_TIMEOUT_MS = 2000;
    private static final int BITS_PER_SAMPLE = 16;
    private static final int BUFFERS_PER_SECOND = 100;
    private static final int CALLBACK_BUFFER_SIZE_MS = 10;
    private static final int DEFAULT_USAGE = getDefaultUsageAttribute();
    private static final String TAG = "WebRtcAudioTrackExternal";
    private final AudioManager audioManager;
    @Nullable
    private AudioTrackThread audioThread;
    @Nullable
    private AudioTrack audioTrack;
    private ByteBuffer byteBuffer;
    private final Context context;
    private byte[] emptyBytes;
    @Nullable
    private final JavaAudioDeviceModule.AudioTrackErrorCallback errorCallback;
    private long nativeAudioTrack;
    private volatile boolean speakerMute;
    @Nullable
    private final JavaAudioDeviceModule.AudioTrackStateCallback stateCallback;
    private final ThreadUtils.ThreadChecker threadChecker;
    private final VolumeLogger volumeLogger;

    private int channelCountToConfiguration(int i) {
        return i == 1 ? 4 : 12;
    }

    private static native void nativeCacheDirectBufferAddress(long j, ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void nativeGetPlayoutData(long j, int i);

    private static int getDefaultUsageAttribute() {
        return Build.VERSION.SDK_INT >= 21 ? 2 : 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    class AudioTrackThread extends Thread {
        private volatile boolean keepAlive;

        public AudioTrackThread(String str) {
            super(str);
            this.keepAlive = true;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(-19);
            Logging.m206d("WebRtcAudioTrackExternal", "AudioTrackThread" + WebRtcAudioUtils.getThreadInfo());
            WebRtcAudioTrack.assertTrue(WebRtcAudioTrack.this.audioTrack.getPlayState() == 3);
            WebRtcAudioTrack.this.doAudioTrackStateCallback(0);
            int capacity = WebRtcAudioTrack.this.byteBuffer.capacity();
            while (this.keepAlive) {
                WebRtcAudioTrack.nativeGetPlayoutData(WebRtcAudioTrack.this.nativeAudioTrack, capacity);
                WebRtcAudioTrack.assertTrue(capacity <= WebRtcAudioTrack.this.byteBuffer.remaining());
                if (WebRtcAudioTrack.this.speakerMute) {
                    WebRtcAudioTrack.this.byteBuffer.clear();
                    WebRtcAudioTrack.this.byteBuffer.put(WebRtcAudioTrack.this.emptyBytes);
                    WebRtcAudioTrack.this.byteBuffer.position(0);
                }
                int writeBytes = writeBytes(WebRtcAudioTrack.this.audioTrack, WebRtcAudioTrack.this.byteBuffer, capacity);
                if (writeBytes != capacity) {
                    Logging.m205e("WebRtcAudioTrackExternal", "AudioTrack.write played invalid number of bytes: " + writeBytes);
                    if (writeBytes < 0) {
                        this.keepAlive = false;
                        WebRtcAudioTrack webRtcAudioTrack = WebRtcAudioTrack.this;
                        webRtcAudioTrack.reportWebRtcAudioTrackError("AudioTrack.write failed: " + writeBytes);
                    }
                }
                WebRtcAudioTrack.this.byteBuffer.rewind();
            }
        }

        private int writeBytes(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            if (Build.VERSION.SDK_INT >= 21) {
                return audioTrack.write(byteBuffer, i, 0);
            }
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i);
        }

        public void stopThread() {
            Logging.m206d("WebRtcAudioTrackExternal", "stopThread");
            this.keepAlive = false;
        }
    }

    @CalledByNative
    WebRtcAudioTrack(Context context, AudioManager audioManager) {
        this(context, audioManager, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebRtcAudioTrack(Context context, AudioManager audioManager, @Nullable JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback, @Nullable JavaAudioDeviceModule.AudioTrackStateCallback audioTrackStateCallback) {
        this.threadChecker = new ThreadUtils.ThreadChecker();
        this.threadChecker.detachThread();
        this.context = context;
        this.audioManager = audioManager;
        this.errorCallback = audioTrackErrorCallback;
        this.stateCallback = audioTrackStateCallback;
        this.volumeLogger = new VolumeLogger(audioManager);
        Logging.m206d("WebRtcAudioTrackExternal", "ctor" + WebRtcAudioUtils.getThreadInfo());
    }

    @CalledByNative
    public void setNativeAudioTrack(long j) {
        this.nativeAudioTrack = j;
    }

    @CalledByNative
    private int initPlayout(int i, int i2, double d) {
        this.threadChecker.checkIsOnValidThread();
        Logging.m206d("WebRtcAudioTrackExternal", "initPlayout(sampleRate=" + i + ", channels=" + i2 + ", bufferSizeFactor=" + d + ")");
        this.byteBuffer = ByteBuffer.allocateDirect(i2 * 2 * (i / 100));
        StringBuilder sb = new StringBuilder();
        sb.append("byteBuffer.capacity: ");
        sb.append(this.byteBuffer.capacity());
        Logging.m206d("WebRtcAudioTrackExternal", sb.toString());
        this.emptyBytes = new byte[this.byteBuffer.capacity()];
        nativeCacheDirectBufferAddress(this.nativeAudioTrack, this.byteBuffer);
        int channelCountToConfiguration = channelCountToConfiguration(i2);
        int minBufferSize = (int) (AudioTrack.getMinBufferSize(i, channelCountToConfiguration, 2) * d);
        Logging.m206d("WebRtcAudioTrackExternal", "minBufferSizeInBytes: " + minBufferSize);
        if (minBufferSize < this.byteBuffer.capacity()) {
            reportWebRtcAudioTrackInitError("AudioTrack.getMinBufferSize returns an invalid value.");
            return -1;
        } else if (this.audioTrack != null) {
            reportWebRtcAudioTrackInitError("Conflict with existing AudioTrack.");
            return -1;
        } else {
            try {
                if (Build.VERSION.SDK_INT >= 21) {
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
                logMainParameters();
                logMainParametersExtended();
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
        Logging.m206d("WebRtcAudioTrackExternal", "startPlayout");
        assertTrue(this.audioTrack != null);
        assertTrue(this.audioThread == null);
        try {
            this.audioTrack.play();
            if (this.audioTrack.getPlayState() != 3) {
                JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode = JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_STATE_MISMATCH;
                reportWebRtcAudioTrackStartError(audioTrackStartErrorCode, "AudioTrack.play failed - incorrect state :" + this.audioTrack.getPlayState());
                releaseAudioResources();
                return false;
            }
            this.audioThread = new AudioTrackThread("AudioTrackJavaThread");
            this.audioThread.start();
            return true;
        } catch (IllegalStateException e) {
            JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode2 = JavaAudioDeviceModule.AudioTrackStartErrorCode.AUDIO_TRACK_START_EXCEPTION;
            reportWebRtcAudioTrackStartError(audioTrackStartErrorCode2, "AudioTrack.play failed: " + e.getMessage());
            releaseAudioResources();
            return false;
        }
    }

    @CalledByNative
    private boolean stopPlayout() {
        this.threadChecker.checkIsOnValidThread();
        this.volumeLogger.stop();
        Logging.m206d("WebRtcAudioTrackExternal", "stopPlayout");
        assertTrue(this.audioThread != null);
        logUnderrunCount();
        this.audioThread.stopThread();
        Logging.m206d("WebRtcAudioTrackExternal", "Stopping the AudioTrackThread...");
        this.audioThread.interrupt();
        if (!ThreadUtils.joinUninterruptibly(this.audioThread, 2000L)) {
            Logging.m205e("WebRtcAudioTrackExternal", "Join of AudioTrackThread timed out.");
            WebRtcAudioUtils.logAudioState("WebRtcAudioTrackExternal", this.context, this.audioManager);
        }
        Logging.m206d("WebRtcAudioTrackExternal", "AudioTrackThread has now been stopped.");
        this.audioThread = null;
        if (this.audioTrack != null) {
            Logging.m206d("WebRtcAudioTrackExternal", "Calling AudioTrack.stop...");
            try {
                this.audioTrack.stop();
                Logging.m206d("WebRtcAudioTrackExternal", "AudioTrack.stop is done.");
                doAudioTrackStateCallback(1);
            } catch (IllegalStateException e) {
                Logging.m205e("WebRtcAudioTrackExternal", "AudioTrack.stop failed: " + e.getMessage());
            }
        }
        releaseAudioResources();
        return true;
    }

    @CalledByNative
    private int getStreamMaxVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.m206d("WebRtcAudioTrackExternal", "getStreamMaxVolume");
        return this.audioManager.getStreamMaxVolume(0);
    }

    @CalledByNative
    private boolean setStreamVolume(int i) {
        this.threadChecker.checkIsOnValidThread();
        Logging.m206d("WebRtcAudioTrackExternal", "setStreamVolume(" + i + ")");
        if (isVolumeFixed()) {
            Logging.m205e("WebRtcAudioTrackExternal", "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(0, i, 0);
        return true;
    }

    private boolean isVolumeFixed() {
        if (Build.VERSION.SDK_INT < 21) {
            return false;
        }
        return this.audioManager.isVolumeFixed();
    }

    @CalledByNative
    private int getStreamVolume() {
        this.threadChecker.checkIsOnValidThread();
        Logging.m206d("WebRtcAudioTrackExternal", "getStreamVolume");
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
        Logging.m206d("WebRtcAudioTrackExternal", "AudioTrack: session ID: " + this.audioTrack.getAudioSessionId() + ", channels: " + this.audioTrack.getChannelCount() + ", sample rate: " + this.audioTrack.getSampleRate() + ", max gain: " + AudioTrack.getMaxVolume());
    }

    @TargetApi(21)
    private static AudioTrack createAudioTrackOnLollipopOrHigher(int i, int i2, int i3) {
        Logging.m206d("WebRtcAudioTrackExternal", "createAudioTrackOnLollipopOrHigher");
        int nativeOutputSampleRate = AudioTrack.getNativeOutputSampleRate(0);
        Logging.m206d("WebRtcAudioTrackExternal", "nativeOutputSampleRate: " + nativeOutputSampleRate);
        if (i != nativeOutputSampleRate) {
            Logging.m202w("WebRtcAudioTrackExternal", "Unable to use fast mode since requested sample rate is not native");
        }
        return new AudioTrack(new AudioAttributes.Builder().setUsage(DEFAULT_USAGE).setContentType(1).build(), new AudioFormat.Builder().setEncoding(2).setSampleRate(i).setChannelMask(i2).build(), i3, 1, 0);
    }

    private static AudioTrack createAudioTrackOnLowerThanLollipop(int i, int i2, int i3) {
        return new AudioTrack(0, i, i2, 2, i3, 1);
    }

    private void logBufferSizeInFrames() {
        if (Build.VERSION.SDK_INT >= 23) {
            Logging.m206d("WebRtcAudioTrackExternal", "AudioTrack: buffer size in frames: " + this.audioTrack.getBufferSizeInFrames());
        }
    }

    @CalledByNative
    private int getBufferSizeInFrames() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.audioTrack.getBufferSizeInFrames();
        }
        return -1;
    }

    private void logBufferCapacityInFrames() {
        if (Build.VERSION.SDK_INT >= 24) {
            Logging.m206d("WebRtcAudioTrackExternal", "AudioTrack: buffer capacity in frames: " + this.audioTrack.getBufferCapacityInFrames());
        }
    }

    private void logMainParametersExtended() {
        logBufferSizeInFrames();
        logBufferCapacityInFrames();
    }

    private void logUnderrunCount() {
        if (Build.VERSION.SDK_INT >= 24) {
            Logging.m206d("WebRtcAudioTrackExternal", "underrun count: " + this.audioTrack.getUnderrunCount());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    public void setSpeakerMute(boolean z) {
        Logging.m202w("WebRtcAudioTrackExternal", "setSpeakerMute(" + z + ")");
        this.speakerMute = z;
    }

    private void releaseAudioResources() {
        Logging.m206d("WebRtcAudioTrackExternal", "releaseAudioResources");
        AudioTrack audioTrack = this.audioTrack;
        if (audioTrack != null) {
            audioTrack.release();
            this.audioTrack = null;
        }
    }

    private void reportWebRtcAudioTrackInitError(String str) {
        Logging.m205e("WebRtcAudioTrackExternal", "Init playout error: " + str);
        WebRtcAudioUtils.logAudioState("WebRtcAudioTrackExternal", this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackInitError(str);
        }
    }

    private void reportWebRtcAudioTrackStartError(JavaAudioDeviceModule.AudioTrackStartErrorCode audioTrackStartErrorCode, String str) {
        Logging.m205e("WebRtcAudioTrackExternal", "Start playout error: " + audioTrackStartErrorCode + ". " + str);
        WebRtcAudioUtils.logAudioState("WebRtcAudioTrackExternal", this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackStartError(audioTrackStartErrorCode, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportWebRtcAudioTrackError(String str) {
        Logging.m205e("WebRtcAudioTrackExternal", "Run-time playback error: " + str);
        WebRtcAudioUtils.logAudioState("WebRtcAudioTrackExternal", this.context, this.audioManager);
        JavaAudioDeviceModule.AudioTrackErrorCallback audioTrackErrorCallback = this.errorCallback;
        if (audioTrackErrorCallback != null) {
            audioTrackErrorCallback.onWebRtcAudioTrackError(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAudioTrackStateCallback(int i) {
        Logging.m206d("WebRtcAudioTrackExternal", "doAudioTrackStateCallback: " + i);
        JavaAudioDeviceModule.AudioTrackStateCallback audioTrackStateCallback = this.stateCallback;
        if (audioTrackStateCallback != null) {
            if (i == 0) {
                audioTrackStateCallback.onWebRtcAudioTrackStart();
            } else if (i == 1) {
                audioTrackStateCallback.onWebRtcAudioTrackStop();
            } else {
                Logging.m205e("WebRtcAudioTrackExternal", "Invalid audio state");
            }
        }
    }
}
