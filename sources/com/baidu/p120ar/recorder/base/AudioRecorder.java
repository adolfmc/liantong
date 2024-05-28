package com.baidu.p120ar.recorder.base;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.record.EncoderParams;
import com.baidu.p120ar.recorder.encoder.AudioCodecEncoder;
import com.baidu.p120ar.recorder.encoder.EncoderCallback;
import com.baidu.p120ar.recorder.encoder.MovieMuxer;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.base.AudioRecorder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AudioRecorder {
    private static final String AUDIO_RECORDER_THREAD_NAME = "AudioRecorderThread";
    private static final String TAG = "AudioRecorder";
    private AudioCodecEncoder mAudioEncoder;
    private Handler mAudioRecorderHandler;
    private HandlerThread mAudioRecorderThread;
    private MovieMuxer mMovieMuxer;
    private volatile boolean mRecording = false;

    private void initRecorder(MovieMuxer movieMuxer, EncoderCallback encoderCallback) {
        this.mAudioRecorderThread = new HandlerThread("AudioRecorderThread");
        this.mAudioRecorderThread.start();
        this.mAudioRecorderHandler = new AudioRecorderHandler(this.mAudioRecorderThread.getLooper());
        this.mAudioEncoder = new AudioCodecEncoder();
        this.mMovieMuxer = movieMuxer;
        if (Build.VERSION.SDK_INT >= 18) {
            this.mAudioEncoder.setEncoderCallback(encoderCallback);
        }
    }

    public boolean isRunning() {
        HandlerThread handlerThread = this.mAudioRecorderThread;
        return handlerThread != null && handlerThread.isAlive();
    }

    public boolean setupRecorder(EncoderParams encoderParams, MovieMuxer movieMuxer, EncoderCallback encoderCallback) {
        if (isRunning()) {
            ARLog.m20419e(TAG, "setupRecorder error! As last audio recorder thread is alive!");
            return false;
        }
        initRecorder(movieMuxer, encoderCallback);
        Handler handler = this.mAudioRecorderHandler;
        handler.sendMessage(handler.obtainMessage(1001, encoderParams));
        this.mRecording = true;
        return true;
    }

    public void startRecording() {
        Handler handler = this.mAudioRecorderHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1002));
        }
    }

    public void frameAvailable(ByteBuffer byteBuffer, int i, long j) {
        if (byteBuffer == null || i <= 0) {
            return;
        }
        AudioFrameModel audioFrameModel = new AudioFrameModel(byteBuffer, i, j);
        if (this.mAudioRecorderHandler == null || !this.mRecording) {
            return;
        }
        Handler handler = this.mAudioRecorderHandler;
        handler.sendMessage(handler.obtainMessage(1003, audioFrameModel));
    }

    public void stopRecording() {
        if (this.mAudioRecorderHandler == null || !this.mRecording) {
            return;
        }
        this.mRecording = false;
        Handler handler = this.mAudioRecorderHandler;
        handler.sendMessage(handler.obtainMessage(1004));
    }

    public void releaseRecorder() {
        Handler handler = this.mAudioRecorderHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Handler handler2 = this.mAudioRecorderHandler;
            handler2.sendMessage(handler2.obtainMessage(1005));
            Handler handler3 = this.mAudioRecorderHandler;
            handler3.sendMessage(handler3.obtainMessage(1006));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.recorder.base.AudioRecorder$AudioRecorderHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class AudioRecorderHandler extends Handler {
        public static final int MSG_FRAME_AVAILABLE = 1003;
        public static final int MSG_QUIT = 1006;
        public static final int MSG_RELEASE_ENCODER = 1005;
        public static final int MSG_SETUP_AUDIO_RECORD = 1001;
        public static final int MSG_START_AUDIO_RECORD = 1002;
        public static final int MSG_STOP_RECORD = 1004;

        public AudioRecorderHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    AudioRecorder.this.handleSetupRecorder((EncoderParams) message.obj);
                    return;
                case 1002:
                    AudioRecorder.this.handleStartRecording();
                    return;
                case 1003:
                    AudioFrameModel audioFrameModel = (AudioFrameModel) message.obj;
                    AudioRecorder.this.handleFrameAvailable(audioFrameModel.mInputBuffer, audioFrameModel.mInputLength, audioFrameModel.mNanoTimeStamp);
                    return;
                case 1004:
                    AudioRecorder.this.handleStopRecording();
                    return;
                case 1005:
                    AudioRecorder.this.handleReleaseRecoder();
                    return;
                case 1006:
                    AudioRecorder.this.handleQuitRecoder();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetupRecorder(EncoderParams encoderParams) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mAudioEncoder.initEncoder(encoderParams, this.mMovieMuxer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStartRecording() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mAudioEncoder.startEncoder();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFrameAvailable(ByteBuffer byteBuffer, int i, long j) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mAudioEncoder.drainBuffer(false, byteBuffer, i, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStopRecording() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mAudioEncoder.drainBuffer(true, null, 0, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleaseRecoder() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mAudioEncoder.stopEncoder();
            this.mAudioEncoder.releaseEncoder();
            this.mAudioEncoder = null;
            this.mMovieMuxer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQuitRecoder() {
        Handler handler = this.mAudioRecorderHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mAudioRecorderHandler = null;
        }
        HandlerThread handlerThread = this.mAudioRecorderThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mAudioRecorderThread = null;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.recorder.base.AudioRecorder$AudioFrameModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class AudioFrameModel {
        ByteBuffer mInputBuffer;
        int mInputLength;
        long mNanoTimeStamp;

        public AudioFrameModel(ByteBuffer byteBuffer, int i, long j) {
            this.mInputBuffer = byteBuffer;
            this.mInputLength = i;
            this.mNanoTimeStamp = j;
        }
    }
}
