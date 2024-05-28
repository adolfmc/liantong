package com.baidu.p120ar.recorder.base;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.baidu.p120ar.record.EncoderParams;
import com.baidu.p120ar.recorder.encoder.EncoderCallback;
import com.baidu.p120ar.recorder.encoder.MovieMuxer;
import com.baidu.p120ar.recorder.encoder.VideoCodecEncoder;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.base.VideoRecorder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoRecorder {
    private static final String TAG = "VideoRecorder";
    private static final String VIDEO_RECORDER_THREAD_NAME = "VideoRecorderThread";
    private MovieMuxer mMovieMuxer;
    private volatile boolean mRecording = false;
    private VideoCodecEncoder mVideoEncoder;
    private Handler mVideoRecorderHandler;
    private HandlerThread mVideoRecorderThread;

    private void initRecorder(MovieMuxer movieMuxer, EncoderCallback encoderCallback) {
        this.mVideoRecorderThread = new HandlerThread("VideoRecorderThread");
        this.mVideoRecorderThread.start();
        this.mVideoRecorderHandler = new VideoRecorderHandler(this.mVideoRecorderThread.getLooper());
        if (Build.VERSION.SDK_INT >= 18) {
            this.mVideoEncoder = new VideoCodecEncoder();
            this.mVideoEncoder.setEncoderCallback(encoderCallback);
            this.mMovieMuxer = movieMuxer;
        }
    }

    public boolean isRunning() {
        HandlerThread handlerThread = this.mVideoRecorderThread;
        return handlerThread != null && handlerThread.isAlive();
    }

    public boolean setupRecorder(EncoderParams encoderParams, MovieMuxer movieMuxer, EncoderCallback encoderCallback) {
        if (isRunning()) {
            ARLog.m20419e(TAG, "setupRecorder error! As last video recorder thread is alive!");
            return false;
        }
        initRecorder(movieMuxer, encoderCallback);
        Handler handler = this.mVideoRecorderHandler;
        handler.sendMessage(handler.obtainMessage(1001, encoderParams));
        this.mRecording = true;
        return true;
    }

    public void startRecording() {
        Handler handler = this.mVideoRecorderHandler;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1002));
        }
    }

    public void frameAvailable(long j) {
        int i = (int) (j >> 32);
        int i2 = (int) j;
        if (this.mVideoRecorderHandler == null || !this.mRecording) {
            return;
        }
        Handler handler = this.mVideoRecorderHandler;
        handler.sendMessage(handler.obtainMessage(1003, i, i2));
    }

    public void stopRecording() {
        if (this.mVideoRecorderHandler == null || !this.mRecording) {
            return;
        }
        this.mRecording = false;
        Handler handler = this.mVideoRecorderHandler;
        handler.sendMessage(handler.obtainMessage(1004));
    }

    public void releaseRecorder() {
        Handler handler = this.mVideoRecorderHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            Handler handler2 = this.mVideoRecorderHandler;
            handler2.sendMessage(handler2.obtainMessage(1005));
            Handler handler3 = this.mVideoRecorderHandler;
            handler3.sendMessage(handler3.obtainMessage(1006));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.recorder.base.VideoRecorder$VideoRecorderHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class VideoRecorderHandler extends Handler {
        public static final int MSG_FRAME_AVAILABLE = 1003;
        public static final int MSG_QUIT = 1006;
        public static final int MSG_RELEASE_ENCODER = 1005;
        public static final int MSG_SETUP_SURFACE_RECORD = 1001;
        public static final int MSG_START_SURFACE_RECORD = 1002;
        public static final int MSG_STOP_RECORD = 1004;

        public VideoRecorderHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1001:
                    VideoRecorder.this.handleSetupRecorder((EncoderParams) message.obj);
                    return;
                case 1002:
                    VideoRecorder.this.handleStartRecording();
                    return;
                case 1003:
                    VideoRecorder.this.handleFrameAvailable((message.arg1 << 32) | (message.arg2 & 4294967295L));
                    return;
                case 1004:
                    VideoRecorder.this.handleStopRecord();
                    return;
                case 1005:
                    VideoRecorder.this.handleReleaseRecorder();
                    return;
                case 1006:
                    VideoRecorder.this.handleQuit();
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSetupRecorder(EncoderParams encoderParams) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mVideoEncoder.initEncoder(encoderParams, this.mMovieMuxer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStartRecording() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mVideoEncoder.startEncoder();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleFrameAvailable(long j) {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mVideoEncoder.drainSurface(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleStopRecord() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mVideoEncoder.drainSurface(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleaseRecorder() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mVideoEncoder.stopEncoder();
            this.mVideoEncoder.releaseEncoder();
            this.mVideoEncoder = null;
            this.mMovieMuxer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleQuit() {
        Handler handler = this.mVideoRecorderHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mVideoRecorderHandler = null;
        }
        HandlerThread handlerThread = this.mVideoRecorderThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mVideoRecorderThread = null;
        }
    }
}
