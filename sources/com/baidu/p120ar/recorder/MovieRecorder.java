package com.baidu.p120ar.recorder;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import com.baidu.p120ar.record.EncoderParams;
import com.baidu.p120ar.record.IMovieRecorder;
import com.baidu.p120ar.record.MovieRecorderCallback;
import com.baidu.p120ar.recorder.base.AudioRecorder;
import com.baidu.p120ar.recorder.base.VideoRecorder;
import com.baidu.p120ar.recorder.encoder.EncoderCallback;
import com.baidu.p120ar.recorder.encoder.MovieMuxer;
import com.baidu.p120ar.recorder.encoder.MuxerCallback;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.MovieRecorder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MovieRecorder implements IMovieRecorder {
    public static final int ERROR_CODE_ON_START = 4001;
    public static final int ERROR_CODE_ON_STOP = 4002;
    private static final int MOVIE_CONDITION_NULL = 0;
    private static final int MOVIE_CONDITION_WITHOUT_AUDIO = 2;
    private static final int MOVIE_CONDITION_WITH_AUDIO = 3;
    private static final int MSG_MOVIE_RECORDER_COMPLETE = 7003;
    private static final long MSG_MOVIE_RECORDER_DELAY_MS = 500;
    private static final int MSG_MOVIE_RECORDER_ERROR = 7004;
    private static final int MSG_MOVIE_RECORDER_INIT = 7000;
    private static final int MSG_MOVIE_RECORDER_PROCESS = 7002;
    private static final int MSG_MOVIE_RECORDER_RESTART = 7005;
    private static final int MSG_MOVIE_RECORDER_START = 7001;
    private static final int MSG_MOVIE_RECORDER_STOP = 7006;
    private static final int STATE_AUDIO_RECORDER_RUN = 4;
    private static final int STATE_MOVIE_MUXER_RUN = 1;
    private static final int STATE_MOVIE_RECORD_STOP = 0;
    private static final int STATE_VIDEO_RECORDER_RUN = 2;
    private static final String TAG = "MovieRecorder";
    private static volatile boolean mOtherTrackPrepared;
    private static volatile MovieRecorder sInstance;
    private static volatile int sMovieRecordState;
    private EncoderCallback mAudioCallback;
    private AudioRecorder mAudioRecorder;
    private Context mContext;
    private EncoderParams mEncoderParams;
    private MovieMuxer mMovieMuxer;
    private HandlerThread mMovieRecordThread;
    private MovieRecorderCallback mMovieRecorderCallback;
    private MovieRecorderHandler mMovieRecorderHandler;
    private MuxerCallback mMuxerCallback;
    private ProcessManager mProcessManager;
    private EncoderCallback mVideoCallback;
    private VideoRecorder mVideoRecorder;
    private int mMovieConditionCount = 0;
    private boolean mCallbackInMainThread = false;
    private volatile boolean mStarting = false;
    private boolean mRestartTried = false;
    private volatile boolean mAudioEncoderStart = false;
    private volatile boolean mVideoEncoderStart = false;

    public static MovieRecorder getInstance() {
        if (sInstance == null) {
            synchronized (MovieRecorder.class) {
                if (sInstance == null) {
                    sInstance = new MovieRecorder();
                }
            }
        }
        return sInstance;
    }

    @Override // com.baidu.p120ar.record.IMovieRecorder
    public void startRecorder(Context context, EncoderParams encoderParams, MovieRecorderCallback movieRecorderCallback) {
        String str = TAG;
        ARLog.m20417i(str, "startRecorder mStarting = " + this.mStarting);
        if (this.mStarting) {
            startErrorCallback();
            return;
        }
        this.mStarting = true;
        this.mContext = context;
        this.mEncoderParams = encoderParams;
        this.mMovieRecorderCallback = movieRecorderCallback;
        startRecorder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRecorder() {
        initMovieRecorder();
        initEncoderCallbacks();
        if (prepareMovieRecorder()) {
            startAudioRecorder();
            startVideoRecorder();
        } else if (!this.mRestartTried) {
            restartRecorder();
        } else {
            startErrorCallback();
        }
    }

    @Override // com.baidu.p120ar.record.IMovieRecorder
    public void onVideoFrameAvailable(long j) {
        VideoRecorder videoRecorder = this.mVideoRecorder;
        if (videoRecorder != null && videoRecorder.isRunning() && this.mVideoEncoderStart && mOtherTrackPrepared) {
            this.mVideoRecorder.frameAvailable(j);
            updateMovieProcess(j / 1000000);
        }
    }

    @Override // com.baidu.p120ar.record.IMovieRecorder
    public void onAudioFrameAvailable(ByteBuffer byteBuffer, int i, long j) {
        AudioRecorder audioRecorder;
        if (this.mAudioEncoderStart && (audioRecorder = this.mAudioRecorder) != null && audioRecorder.isRunning()) {
            this.mAudioRecorder.frameAvailable(byteBuffer, i, j);
        }
    }

    @Override // com.baidu.p120ar.record.IMovieRecorder
    public void stopRecorder() {
        MovieRecorderHandler movieRecorderHandler;
        String str = TAG;
        ARLog.m20417i(str, "stopRecorder mStarting = " + this.mStarting);
        if (this.mStarting) {
            if (!isMovieRecordStarted() && (movieRecorderHandler = this.mMovieRecorderHandler) != null) {
                movieRecorderHandler.sendMessage(movieRecorderHandler.obtainMessage(7004, 4002));
            }
            ARLog.m20421d(TAG, "stopRecorder() MovieRecorder is starting, we will try to stop 500ms later!!!");
            MovieRecorderHandler movieRecorderHandler2 = this.mMovieRecorderHandler;
            if (movieRecorderHandler2 != null) {
                movieRecorderHandler2.sendMessageDelayed(movieRecorderHandler2.obtainMessage(7006), 500L);
                return;
            }
            return;
        }
        this.mAudioEncoderStart = false;
        this.mVideoEncoderStart = false;
        AudioRecorder audioRecorder = this.mAudioRecorder;
        if (audioRecorder != null && audioRecorder.isRunning()) {
            this.mAudioRecorder.stopRecording();
        }
        VideoRecorder videoRecorder = this.mVideoRecorder;
        if (videoRecorder != null && videoRecorder.isRunning()) {
            this.mVideoRecorder.stopRecording();
        }
        setOtherTrackPrepared(false);
    }

    @Override // com.baidu.p120ar.record.IMovieRecorder
    public void onDestroy() {
        this.mProcessManager = null;
        this.mContext = null;
        this.mEncoderParams = null;
        this.mMovieRecorderCallback = null;
        setMovieRecordStateValue(0);
        releaseInstance();
        MovieRecorderHandler movieRecorderHandler = this.mMovieRecorderHandler;
        if (movieRecorderHandler != null) {
            movieRecorderHandler.removeCallbacksAndMessages(null);
            this.mMovieRecorderHandler = null;
        }
        HandlerThread handlerThread = this.mMovieRecordThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mMovieRecordThread = null;
        }
    }

    private static void releaseInstance() {
        sInstance = null;
    }

    private static void setOtherTrackPrepared(boolean z) {
        mOtherTrackPrepared = z;
    }

    private void startErrorCallback() {
        MovieRecorderHandler movieRecorderHandler = this.mMovieRecorderHandler;
        if (movieRecorderHandler != null) {
            movieRecorderHandler.sendMessageDelayed(movieRecorderHandler.obtainMessage(7001, false), 500L);
        }
    }

    private void initMovieRecorder() {
        if (Build.VERSION.SDK_INT >= 18) {
            this.mMovieMuxer = new MovieMuxer();
        }
        if (this.mEncoderParams.isAudioIncluded()) {
            this.mAudioRecorder = new AudioRecorder();
        } else {
            mOtherTrackPrepared = true;
        }
        this.mVideoRecorder = new VideoRecorder();
        this.mMovieConditionCount = 0;
        if (!this.mCallbackInMainThread && this.mMovieRecordThread == null) {
            this.mMovieRecordThread = new HandlerThread(TAG);
            this.mMovieRecordThread.start();
        }
        MovieRecorderHandler movieRecorderHandler = this.mMovieRecorderHandler;
        if (movieRecorderHandler == null) {
            HandlerThread handlerThread = this.mMovieRecordThread;
            if (handlerThread != null) {
                this.mMovieRecorderHandler = new MovieRecorderHandler(handlerThread.getLooper());
            } else {
                this.mMovieRecorderHandler = new MovieRecorderHandler(this.mContext.getMainLooper());
            }
        } else {
            movieRecorderHandler.removeCallbacksAndMessages(null);
        }
        this.mProcessManager = new ProcessManager(this.mEncoderParams.getOutputTotalMs());
    }

    private void initEncoderCallbacks() {
        this.mVideoCallback = new EncoderCallback() { // from class: com.baidu.ar.recorder.MovieRecorder.1
            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderTrackAdd(boolean z) {
            }

            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderSetup(boolean z, Object obj) {
                if (z) {
                    if (MovieRecorder.this.mMovieRecorderHandler != null) {
                        MovieRecorder.this.mMovieRecorderHandler.sendMessage(MovieRecorder.this.mMovieRecorderHandler.obtainMessage(7000, obj));
                    }
                    if (MovieRecorder.this.mVideoRecorder != null) {
                        MovieRecorder.this.mVideoRecorder.startRecording();
                    }
                }
            }

            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderStart(boolean z) {
                MovieRecorder.this.mVideoEncoderStart = z;
                MovieRecorder.this.checkMovieRecordStartState(2, z);
            }

            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderStop(boolean z) {
                if (MovieRecorder.this.mVideoRecorder != null) {
                    MovieRecorder.this.mVideoRecorder.releaseRecorder();
                    MovieRecorder.this.mVideoRecorder = null;
                }
                MovieRecorder.this.mVideoCallback = null;
                MovieRecorder.this.checkMovieRecordStopState(2, z);
            }
        };
        this.mAudioCallback = new EncoderCallback() { // from class: com.baidu.ar.recorder.MovieRecorder.2
            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderSetup(boolean z, Object obj) {
                if (z) {
                    MovieRecorder.this.mAudioRecorder.startRecording();
                }
            }

            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderStart(boolean z) {
                MovieRecorder.this.mAudioEncoderStart = z;
                MovieRecorder.this.checkMovieRecordStartState(4, z);
            }

            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderTrackAdd(boolean z) {
                boolean unused = MovieRecorder.mOtherTrackPrepared = z;
            }

            @Override // com.baidu.p120ar.recorder.encoder.EncoderCallback
            public void onEncoderStop(boolean z) {
                MovieRecorder.this.mAudioRecorder.releaseRecorder();
                MovieRecorder.this.mAudioRecorder = null;
                MovieRecorder.this.mAudioCallback = null;
                MovieRecorder.this.checkMovieRecordStopState(4, z);
            }
        };
        this.mMuxerCallback = new MuxerCallback() { // from class: com.baidu.ar.recorder.MovieRecorder.3
            @Override // com.baidu.p120ar.recorder.encoder.MuxerCallback
            public void onMuxerStart(boolean z) {
                MovieRecorder.this.checkMovieRecordStartState(1, z);
            }

            @Override // com.baidu.p120ar.recorder.encoder.MuxerCallback
            public void onMuxerStop(boolean z) {
                if (Build.VERSION.SDK_INT >= 18) {
                    MovieRecorder.this.mMovieMuxer.releaseMuxer();
                    MovieRecorder.this.mMovieMuxer = null;
                }
                MovieRecorder.this.mMuxerCallback = null;
                MovieRecorder.this.checkMovieRecordStopState(1, z);
            }
        };
    }

    private boolean prepareMovieRecorder() {
        boolean z;
        AudioRecorder audioRecorder = this.mAudioRecorder;
        if (audioRecorder == null || !audioRecorder.isRunning()) {
            z = true;
        } else {
            ARLog.m20419e(TAG, "prepareMovieRecorder mAudioRecorder.isRunning !!!");
            this.mAudioRecorder.stopRecording();
            this.mAudioRecorder.releaseRecorder();
            z = false;
        }
        VideoRecorder videoRecorder = this.mVideoRecorder;
        if (videoRecorder != null && videoRecorder.isRunning()) {
            ARLog.m20419e(TAG, "prepareMovieRecorder mVideoRecorder.isRunning !!!");
            this.mVideoRecorder.stopRecording();
            this.mVideoRecorder.releaseRecorder();
            z = false;
        }
        EncoderParams encoderParams = this.mEncoderParams;
        if (encoderParams == null || this.mMovieMuxer.initMovieMuxer(encoderParams.getOutputFile(), this.mEncoderParams.getOutputFormat(), this.mMuxerCallback)) {
            return z;
        }
        ARLog.m20419e(TAG, "prepareMovieRecorder movieMuxerInit error!!!");
        return false;
    }

    private void startAudioRecorder() {
        AudioRecorder audioRecorder = this.mAudioRecorder;
        if (audioRecorder != null) {
            audioRecorder.setupRecorder(this.mEncoderParams, this.mMovieMuxer, this.mAudioCallback);
        }
    }

    private void startVideoRecorder() {
        this.mVideoRecorder.setupRecorder(this.mEncoderParams, this.mMovieMuxer, this.mVideoCallback);
    }

    private void restartRecorder() {
        String str = TAG;
        ARLog.m20417i(str, "restartRecorder mRestartTried = " + this.mRestartTried);
        MovieRecorderHandler movieRecorderHandler = this.mMovieRecorderHandler;
        if (movieRecorderHandler != null) {
            this.mRestartTried = true;
            movieRecorderHandler.sendMessageDelayed(movieRecorderHandler.obtainMessage(7005), 500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkMovieRecordStartState(int i, boolean z) {
        String str = TAG;
        ARLog.m20417i(str, "checkMovieRecordStartState condition = " + i + " && state = " + z);
        setMovieRecordState(i, z);
        String str2 = TAG;
        ARLog.m20417i(str2, "checkMovieRecordStartState sMovieRecordState = " + sMovieRecordState);
        if (isStartConditionPrepared()) {
            this.mMovieRecorderHandler.sendMessage(this.mMovieRecorderHandler.obtainMessage(7001, Boolean.valueOf(isMovieRecordStarted())));
        }
    }

    private static void setMovieRecordStateValue(int i) {
        sMovieRecordState = i;
    }

    private void setMovieRecordState(int i, boolean z) {
        if (z) {
            sMovieRecordState = i | sMovieRecordState;
        }
        this.mMovieConditionCount++;
    }

    private boolean isStartConditionPrepared() {
        EncoderParams encoderParams = this.mEncoderParams;
        if (encoderParams == null) {
            return false;
        }
        if (encoderParams.isAudioIncluded()) {
            if (this.mMovieConditionCount == 3) {
                return true;
            }
        } else if (this.mMovieConditionCount == 2) {
            return true;
        }
        return false;
    }

    private synchronized boolean isMovieRecordStarted() {
        int i;
        String str = TAG;
        ARLog.m20417i(str, "isMovieRecordStarted sMovieRecordState = " + sMovieRecordState);
        i = (sMovieRecordState ^ 1) ^ 2;
        if (this.mEncoderParams != null) {
            if (this.mEncoderParams.isAudioIncluded()) {
                i ^= 4;
            }
        }
        return i == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void checkMovieRecordStopState(int i, boolean z) {
        String str = TAG;
        ARLog.m20417i(str, "checkMovieRecordStopState condition = " + i + " && state = " + z);
        clearMovieRecordState(i, z);
        String str2 = TAG;
        ARLog.m20417i(str2, "checkMovieRecordStopState sMovieRecordState = " + sMovieRecordState);
        if (isStopConditionPrepared() && this.mMovieRecorderHandler != null) {
            this.mMovieRecorderHandler.sendMessage(this.mMovieRecorderHandler.obtainMessage(7003, Boolean.valueOf(isMovieRecordStopped())));
        }
    }

    private void clearMovieRecordState(int i, boolean z) {
        if (z) {
            sMovieRecordState = i ^ sMovieRecordState;
        }
        this.mMovieConditionCount--;
    }

    private boolean isStopConditionPrepared() {
        return this.mMovieConditionCount == 0;
    }

    private synchronized boolean isMovieRecordStopped() {
        return sMovieRecordState == 0;
    }

    private void updateMovieProcess(long j) {
        MovieRecorderHandler movieRecorderHandler;
        if (!this.mProcessManager.isStart()) {
            this.mProcessManager.start(j);
            return;
        }
        int currentProcess = this.mProcessManager.getCurrentProcess(j);
        if (currentProcess <= 0 || (movieRecorderHandler = this.mMovieRecorderHandler) == null) {
            return;
        }
        movieRecorderHandler.sendMessage(movieRecorderHandler.obtainMessage(7002, Integer.valueOf(currentProcess)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.recorder.MovieRecorder$MovieRecorderHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class MovieRecorderHandler extends Handler {
        public MovieRecorderHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 7000:
                    if (MovieRecorder.this.mMovieRecorderCallback != null) {
                        MovieRecorder.this.mMovieRecorderCallback.onRecorderInit((Surface) message.obj);
                        break;
                    }
                    break;
                case 7001:
                    if (MovieRecorder.this.mMovieRecorderCallback != null) {
                        MovieRecorder.this.mMovieRecorderCallback.onRecorderStart(((Boolean) message.obj).booleanValue());
                    }
                    MovieRecorder.this.mStarting = false;
                    break;
                case 7002:
                    if (MovieRecorder.this.mMovieRecorderCallback != null) {
                        MovieRecorder.this.mMovieRecorderCallback.onRecorderProcess(((Integer) message.obj).intValue());
                        break;
                    }
                    break;
                case 7003:
                    if (MovieRecorder.this.mMovieRecorderCallback != null) {
                        MovieRecorder.this.mMovieRecorderCallback.onRecorderComplete(((Boolean) message.obj).booleanValue(), MovieRecorder.this.mEncoderParams != null ? MovieRecorder.this.mEncoderParams.getOutputFile() : null);
                        break;
                    }
                    break;
                case 7004:
                    if (MovieRecorder.this.mMovieRecorderCallback != null) {
                        MovieRecorder.this.mMovieRecorderCallback.onRecorderError(((Integer) message.obj).intValue());
                        break;
                    }
                    break;
                case 7005:
                    MovieRecorder.this.startRecorder();
                    break;
                case 7006:
                    MovieRecorder.this.mStarting = false;
                    MovieRecorder.this.stopRecorder();
                    break;
            }
            super.handleMessage(message);
        }
    }
}
