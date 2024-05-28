package com.baidu.p120ar.audio;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.audio.AudioController */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioController implements IAudio {
    private static final String AUDIO_THREAD_NAME = "AudioHandlerThread";
    private static final String TAG = "AudioController";
    private static volatile AudioController sInstance;
    private AudioEngine mAudioEngine;
    private Handler mAudioHandler;
    private HandlerThread mAudioThread;

    public static AudioController getInstance() {
        if (sInstance == null) {
            synchronized (AudioController.class) {
                if (sInstance == null) {
                    sInstance = new AudioController();
                }
            }
        }
        return sInstance;
    }

    private static void releaseInstance() {
        sInstance = null;
    }

    private AudioController() {
    }

    private void startAudioThread() {
        this.mAudioThread = new HandlerThread("AudioHandlerThread");
        this.mAudioThread.start();
        this.mAudioHandler = new AudioHandler(this.mAudioThread.getLooper());
    }

    @Override // com.baidu.p120ar.audio.IAudio
    public boolean setupAudio(AudioParams audioParams, AudioCallback audioCallback) {
        if (isRunning()) {
            ARLog.m20419e(TAG, "setupAudio error! As last audio thread is alive!");
            return false;
        }
        if (this.mAudioEngine == null) {
            this.mAudioEngine = new AudioEngine();
        }
        this.mAudioEngine.setAudioCallback(audioCallback);
        startAudioThread();
        Handler handler = this.mAudioHandler;
        handler.sendMessage(handler.obtainMessage(1001, audioParams));
        return true;
    }

    @Override // com.baidu.p120ar.audio.IAudio
    public void setVolumeListener(VolumeListener volumeListener) {
        if (volumeListener != null) {
            if (this.mAudioEngine == null) {
                this.mAudioEngine = new AudioEngine();
            }
            this.mAudioEngine.setVolumeListener(volumeListener);
        }
    }

    @Override // com.baidu.p120ar.audio.IAudio
    public void startAudio() {
        Handler handler = this.mAudioHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1002));
        }
    }

    @Override // com.baidu.p120ar.audio.IAudio
    public void stopAudio() {
        handleStopAudioEngine();
    }

    @Override // com.baidu.p120ar.audio.IAudio
    public void releaseAudio() {
        Handler handler = this.mAudioHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Handler handler2 = this.mAudioHandler;
            handler2.sendMessage(handler2.obtainMessage(1004));
            Handler handler3 = this.mAudioHandler;
            handler3.sendMessage(handler3.obtainMessage(1005));
        }
    }

    public boolean hasPermission(Context context) {
        return AudioHelper.checkAudioRecordPermission(context);
    }

    public boolean isRunning() {
        HandlerThread handlerThread = this.mAudioThread;
        return handlerThread != null && handlerThread.isAlive();
    }

    public AudioParams getAudioParams() {
        AudioEngine audioEngine = this.mAudioEngine;
        if (audioEngine != null) {
            return audioEngine.getAudioParams();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.audio.AudioController$AudioHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioHandler extends Handler {
        public static final int MSG_QUIT = 1005;
        public static final int MSG_RELEASE_AUDIO_ENGINE = 1004;
        public static final int MSG_SETUP_AUDIO_ENGINE = 1001;
        public static final int MSG_START_AUDIO_ENGINE = 1002;
        public static final int MSG_STOP_AUDIO_ENGINE = 1003;

        public AudioHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    AudioController.this.handleSetupAudioEngine((AudioParams) message.obj);
                    return;
                case 1002:
                    AudioController.this.handleStartAudioEngine();
                    return;
                case 1003:
                    AudioController.this.handleStopAudioEngine();
                    return;
                case 1004:
                    AudioController.this.handleReleaseAudioEngine();
                    return;
                case 1005:
                    AudioController.this.handleQuit();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetupAudioEngine(AudioParams audioParams) {
        AudioEngine audioEngine = this.mAudioEngine;
        if (audioEngine != null) {
            audioEngine.setupAudioEngine(audioParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStartAudioEngine() {
        AudioEngine audioEngine = this.mAudioEngine;
        if (audioEngine != null) {
            audioEngine.startAudioEngine();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStopAudioEngine() {
        AudioEngine audioEngine = this.mAudioEngine;
        if (audioEngine != null) {
            audioEngine.stopAudioEngine();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleaseAudioEngine() {
        AudioEngine audioEngine = this.mAudioEngine;
        if (audioEngine != null) {
            audioEngine.releaseAudioEngine();
        }
        this.mAudioEngine = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQuit() {
        releaseInstance();
        Handler handler = this.mAudioHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mAudioHandler = null;
        }
        HandlerThread handlerThread = this.mAudioThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mAudioThread = null;
        }
    }
}
