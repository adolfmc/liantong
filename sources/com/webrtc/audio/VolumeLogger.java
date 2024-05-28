package com.webrtc.audio;

import android.media.AudioManager;
import android.support.annotation.Nullable;
import com.webrtc.Logging;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class VolumeLogger {
    private static final String TAG = "VolumeLogger";
    private static final String THREAD_NAME = "WebRtcVolumeLevelLoggerThread";
    private static final int TIMER_PERIOD_IN_SECONDS = 30;
    private final AudioManager audioManager;
    @Nullable
    private Timer timer;

    public VolumeLogger(AudioManager audioManager) {
        this.audioManager = audioManager;
    }

    public void start() {
        Logging.m5305d(TAG, "start" + WebRtcAudioUtils.getThreadInfo());
        if (this.timer != null) {
            return;
        }
        Logging.m5305d(TAG, "audio mode is: " + WebRtcAudioUtils.modeToString(this.audioManager.getMode()));
        this.timer = new Timer(THREAD_NAME);
        this.timer.schedule(new LogVolumeTask(this.audioManager.getStreamMaxVolume(2), this.audioManager.getStreamMaxVolume(0)), 0L, 30000L);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class LogVolumeTask extends TimerTask {
        private final int maxRingVolume;
        private final int maxVoiceCallVolume;

        LogVolumeTask(int i, int i2) {
            this.maxRingVolume = i;
            this.maxVoiceCallVolume = i2;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            int mode = VolumeLogger.this.audioManager.getMode();
            if (mode == 1) {
                Logging.m5305d(VolumeLogger.TAG, "STREAM_RING stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(2) + " (max=" + this.maxRingVolume + ")");
            } else if (mode == 3) {
                Logging.m5305d(VolumeLogger.TAG, "VOICE_CALL stream volume: " + VolumeLogger.this.audioManager.getStreamVolume(0) + " (max=" + this.maxVoiceCallVolume + ")");
            }
        }
    }

    public void stop() {
        Logging.m5305d(TAG, "stop" + WebRtcAudioUtils.getThreadInfo());
        Timer timer = this.timer;
        if (timer != null) {
            timer.cancel();
            this.timer = null;
        }
    }
}
