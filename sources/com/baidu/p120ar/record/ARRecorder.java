package com.baidu.p120ar.record;

import android.content.Context;
import android.view.Surface;
import com.baidu.p120ar.ARPluginBuilder;
import com.baidu.p120ar.DuMixOutput;
import com.baidu.p120ar.arplay.core.engine.rotate.Orientation;
import com.baidu.p120ar.arplay.core.engine.rotate.OrientationManager;
import com.baidu.p120ar.arrender.FrameRenderListener;
import com.baidu.p120ar.arrender.IARRenderer;
import com.baidu.p120ar.audio.AudioParams;
import com.baidu.p120ar.audio.EasyAudioCallback;
import com.baidu.p120ar.audio.IEasyAudio;
import com.baidu.p120ar.bean.RotationType;
import com.baidu.p120ar.bean.Watermark;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.record.ARRecorder */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ARRecorder implements FrameRenderListener, IRecord {
    private static final String TAG = "ARRecorder";
    private IARRenderer mARRenderer;
    private EasyAudioCallback mAudioCallback;
    private AudioParams mAudioParams;
    private Context mContext;
    private DuMixOutput mDuMixOutput;
    private IEasyAudio mEasyAudio;
    private EncoderParams mEncoderParams;
    private IMovieRecorder mMovieRecorder;
    private MovieRecorderCallback mMovieRecorderCallback;
    private long mPauseTimeCurrent;
    private RecordCallback mRecordCallback;
    private Watermark mWatermark;
    private int mWindowWidth = 0;
    private int mWindowHeight = 0;
    private boolean mRecording = false;
    private boolean mPaused = false;
    private long mPauseTimeTotal = 0;

    @Override // com.baidu.p120ar.arrender.FrameRenderListener
    public void onRenderStarted(long j) {
    }

    public ARRecorder(Context context, IARRenderer iARRenderer) {
        this.mContext = context;
        this.mARRenderer = iARRenderer;
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void startRecord(String str, long j, RecordCallback recordCallback) {
        createEasyAudio();
        createMovieRecorder();
        this.mRecordCallback = recordCallback;
        EncoderParams encoderParams = this.mEncoderParams;
        if (encoderParams != null) {
            encoderParams.setOutputFile(str);
            this.mEncoderParams.setOutputTotalMs(j);
        }
        IEasyAudio iEasyAudio = this.mEasyAudio;
        if (iEasyAudio != null) {
            iEasyAudio.startAudio(this.mAudioParams, this.mAudioCallback);
        }
        IARRenderer iARRenderer = this.mARRenderer;
        if (iARRenderer != null) {
            iARRenderer.addFrameRenderListener(this);
        }
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void setRecordWatermark(Watermark watermark) {
        this.mWatermark = watermark;
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void pauseRecord() {
        if (!this.mRecording || this.mPaused) {
            return;
        }
        this.mPaused = true;
        this.mPauseTimeCurrent = System.nanoTime();
    }

    @Override // com.baidu.p120ar.record.IRecord
    public void resumeRecord() {
        if (this.mRecording && this.mPaused) {
            this.mPaused = false;
            this.mPauseTimeTotal += System.nanoTime() - this.mPauseTimeCurrent;
        }
    }

    @Override // com.baidu.p120ar.record.IRecord
    public synchronized void stopRecord() {
        if (this.mEasyAudio != null) {
            this.mEasyAudio.stopAudio(this.mAudioCallback);
            this.mEasyAudio = null;
        }
        this.mAudioParams = null;
        this.mAudioCallback = null;
        if (this.mMovieRecorder != null) {
            this.mMovieRecorder.stopRecorder();
            this.mMovieRecorder = null;
        }
        this.mEncoderParams = null;
        this.mMovieRecorderCallback = null;
        if (this.mARRenderer != null) {
            this.mARRenderer.removeOutputSurface(this.mDuMixOutput);
            this.mARRenderer.removeFrameRenderListener(this);
        }
        this.mDuMixOutput = null;
    }

    public void setCenterCrop(int i, int i2) {
        this.mWindowWidth = i;
        this.mWindowHeight = i2;
    }

    private void correctEncoderParams(EncoderParams encoderParams, AudioParams audioParams) {
        int videoWidth = encoderParams.getVideoWidth();
        int videoHeight = encoderParams.getVideoHeight();
        if (this.mWindowWidth > 0 && this.mWindowHeight > 0) {
            if (videoHeight > videoWidth) {
                videoHeight = (this.mEncoderParams.getVideoWidth() * this.mWindowHeight) / this.mWindowWidth;
            } else {
                videoWidth = (this.mEncoderParams.getVideoHeight() * this.mWindowHeight) / this.mWindowWidth;
            }
        }
        Orientation globalOrientation = OrientationManager.getGlobalOrientation();
        if (globalOrientation == Orientation.LANDSCAPE || globalOrientation == Orientation.LANDSCAPE_REVERSE) {
            int i = videoHeight;
            videoHeight = videoWidth;
            videoWidth = i;
        }
        if (videoWidth % 2 == 1) {
            videoWidth++;
        }
        if (videoHeight % 2 == 1) {
            videoHeight++;
        }
        encoderParams.setVideoWidth(videoWidth);
        encoderParams.setVideoHeight(videoHeight);
        encoderParams.setAudioSampleRate(audioParams.getSampleRate());
        encoderParams.setAudioFrameSize(audioParams.getFrameSize());
    }

    private void correctWatermark() {
        Orientation globalOrientation = OrientationManager.getGlobalOrientation();
        if (this.mWatermark != null) {
            if (globalOrientation == Orientation.LANDSCAPE) {
                this.mWatermark.setCoordinateType(Watermark.CoordinateType.RIGHT_BOTTOM);
                this.mWatermark.setRotationType(RotationType.ROTATE_270);
            } else if (globalOrientation == Orientation.LANDSCAPE_REVERSE) {
                this.mWatermark.setCoordinateType(Watermark.CoordinateType.LEFT_TOP);
                this.mWatermark.setRotationType(RotationType.ROTATE_90);
            } else if (globalOrientation == Orientation.PORTRAIT_REVERSE) {
                this.mWatermark.setCoordinateType(Watermark.CoordinateType.RIGHT_TOP);
                this.mWatermark.setRotationType(RotationType.ROTATE_180);
            } else {
                this.mWatermark.setCoordinateType(Watermark.CoordinateType.LEFT_BOTTOM);
                this.mWatermark.setRotationType(RotationType.ROTATE_0);
            }
        }
    }

    private void createEasyAudio() {
        if (this.mAudioParams == null) {
            this.mAudioParams = new AudioParams();
        }
        if (this.mAudioCallback == null) {
            this.mAudioCallback = new EasyAudioCallback() { // from class: com.baidu.ar.record.ARRecorder.1
                @Override // com.baidu.p120ar.audio.EasyAudioCallback
                public void onAudioStart(boolean z, AudioParams audioParams) {
                    String str = ARRecorder.TAG;
                    ARLog.m20421d(str, "onAudioStart result = " + z);
                    ARRecorder.this.startMovieRecorder(z, audioParams);
                }

                @Override // com.baidu.p120ar.audio.EasyAudioCallback
                public void onAudioFrameAvailable(ByteBuffer byteBuffer, int i, long j) {
                    ARRecorder.this.updateAudioData(byteBuffer, i);
                }

                @Override // com.baidu.p120ar.audio.EasyAudioCallback
                public void onAudioStop(boolean z) {
                    String str = ARRecorder.TAG;
                    ARLog.m20421d(str, "onAudioStop result = " + z);
                }
            };
        }
        if (this.mEasyAudio == null) {
            this.mEasyAudio = ARPluginBuilder.buildEasyAudio();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void startMovieRecorder(boolean z, AudioParams audioParams) {
        this.mEncoderParams.setAudioIncluded(z);
        if (this.mMovieRecorder != null) {
            correctEncoderParams(this.mEncoderParams, audioParams);
            correctWatermark();
            this.mMovieRecorder.startRecorder(this.mContext, this.mEncoderParams, this.mMovieRecorderCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void updateAudioData(ByteBuffer byteBuffer, int i) {
        long nanoTime = System.nanoTime() - this.mPauseTimeTotal;
        if (this.mMovieRecorder != null && byteBuffer != null && i > 0 && !this.mPaused) {
            this.mMovieRecorder.onAudioFrameAvailable(byteBuffer, i, nanoTime);
        }
    }

    private void createMovieRecorder() {
        if (this.mEncoderParams == null) {
            this.mEncoderParams = new EncoderParams();
        }
        if (this.mMovieRecorderCallback == null) {
            this.mMovieRecorderCallback = new MovieRecorderCallback() { // from class: com.baidu.ar.record.ARRecorder.2
                @Override // com.baidu.p120ar.record.MovieRecorderCallback
                public void onRecorderInit(Surface surface) {
                    if (ARRecorder.this.mEncoderParams == null) {
                        return;
                    }
                    String str = ARRecorder.TAG;
                    ARLog.m20421d(str, "onRecorderInit inputSurface = " + surface.hashCode());
                    ARRecorder aRRecorder = ARRecorder.this;
                    aRRecorder.mDuMixOutput = new DuMixOutput(surface, aRRecorder.mEncoderParams.getVideoWidth(), ARRecorder.this.mEncoderParams.getVideoHeight());
                    ARRecorder.this.mDuMixOutput.setRotationType(ARRecorder.this.getRotation());
                    ARRecorder.this.mDuMixOutput.setWatermark(ARRecorder.this.mWatermark);
                    if (ARRecorder.this.mARRenderer == null || ARRecorder.this.mEncoderParams == null) {
                        return;
                    }
                    ARRecorder.this.mARRenderer.addOutputSurface(ARRecorder.this.mDuMixOutput);
                }

                @Override // com.baidu.p120ar.record.MovieRecorderCallback
                public void onRecorderStart(boolean z) {
                    String str = ARRecorder.TAG;
                    ARLog.m20421d(str, "onRecorderStart result = " + z);
                    ARRecorder.this.mRecording = z;
                    if (ARRecorder.this.mRecordCallback != null) {
                        ARRecorder.this.mRecordCallback.onRecorderStart(z);
                    }
                }

                @Override // com.baidu.p120ar.record.MovieRecorderCallback
                public void onRecorderProcess(int i) {
                    String str = ARRecorder.TAG;
                    ARLog.m20421d(str, "onRecorderProcess process = " + i);
                    if (i <= 100) {
                        if (ARRecorder.this.mRecordCallback != null) {
                            ARRecorder.this.mRecordCallback.onRecorderProcess(i);
                            return;
                        }
                        return;
                    }
                    ARRecorder.this.stopRecord();
                }

                @Override // com.baidu.p120ar.record.MovieRecorderCallback
                public void onRecorderComplete(boolean z, String str) {
                    String str2 = ARRecorder.TAG;
                    ARLog.m20421d(str2, "onRecorderComplete result = " + z);
                    ARRecorder.this.mRecording = false;
                    if (ARRecorder.this.mRecordCallback != null) {
                        ARRecorder.this.mRecordCallback.onRecorderComplete(z, str);
                        ARRecorder.this.mRecordCallback = null;
                    }
                }

                @Override // com.baidu.p120ar.record.MovieRecorderCallback
                public void onRecorderError(int i) {
                    String str = ARRecorder.TAG;
                    ARLog.m20419e(str, "onRecorderError error = " + i);
                }
            };
        }
        if (this.mMovieRecorder == null) {
            this.mMovieRecorder = ARPluginBuilder.buildMovieRecorder();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RotationType getRotation() {
        RotationType rotationType = RotationType.ROTATE_0;
        switch (OrientationManager.getGlobalOrientation()) {
            case LANDSCAPE:
                return RotationType.ROTATE_90;
            case LANDSCAPE_REVERSE:
                return RotationType.ROTATE_270;
            case PORTRAIT_REVERSE:
                return RotationType.ROTATE_180;
            default:
                return rotationType;
        }
    }

    @Override // com.baidu.p120ar.arrender.FrameRenderListener
    public void onRenderFinished(long j) {
        if (this.mMovieRecorder == null || this.mPaused) {
            return;
        }
        this.mMovieRecorder.onVideoFrameAvailable(System.nanoTime() - this.mPauseTimeTotal);
    }
}
