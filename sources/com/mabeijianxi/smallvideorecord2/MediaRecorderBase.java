package com.mabeijianxi.smallvideorecord2;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.hardware.Camera;
import android.os.Build;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.SurfaceHolder;
import com.mabeijianxi.smallvideorecord2.jniinterface.FFmpegBridge;
import com.mabeijianxi.smallvideorecord2.model.BaseMediaBitrateConfig;
import com.mabeijianxi.smallvideorecord2.model.MediaObject;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class MediaRecorderBase implements Camera.PreviewCallback, SurfaceHolder.Callback, IMediaRecorder {
    public static final int AUDIO_RECORD_ERROR_CREATE_FAILED = 3;
    public static final int AUDIO_RECORD_ERROR_GET_MIN_BUFFER_SIZE_NOT_SUPPORT = 2;
    public static final int AUDIO_RECORD_ERROR_SAMPLERATE_NOT_SUPPORT = 1;
    public static final int AUDIO_RECORD_ERROR_UNKNOWN = 0;
    protected static int CAPTURE_THUMBNAILS_TIME = 1;
    protected static int MAX_FRAME_RATE = 20;
    public static final int MEDIA_ERROR_CAMERA_AUTO_FOCUS = 103;
    public static final int MEDIA_ERROR_CAMERA_PREVIEW = 102;
    public static final int MEDIA_ERROR_CAMERA_SET_PREVIEW_DISPLAY = 101;
    public static final int MEDIA_ERROR_UNKNOWN = 1;
    protected static final int MESSAGE_ENCODE_COMPLETE = 2;
    protected static final int MESSAGE_ENCODE_ERROR = 3;
    protected static final int MESSAGE_ENCODE_PROGRESS = 1;
    protected static final int MESSAGE_ENCODE_START = 0;
    protected static int MIN_FRAME_RATE = 8;
    public static boolean NEED_FULL_SCREEN = false;
    public static int SMALL_VIDEO_HEIGHT = 480;
    public static int SMALL_VIDEO_WIDTH = 360;
    public static final int VIDEO_BITRATE_HIGH = 2048;
    public static final int VIDEO_BITRATE_MEDIUM = 1536;
    public static final int VIDEO_BITRATE_NORMAL = 1024;
    public static int mSupportedPreviewWidth;
    protected static int mVideoBitrate;
    protected Camera camera;
    protected BaseMediaBitrateConfig compressConfig;
    protected AudioRecorder mAudioRecorder;
    protected MediaObject mMediaObject;
    protected OnEncodeListener mOnEncodeListener;
    protected OnErrorListener mOnErrorListener;
    protected OnPreparedListener mOnPreparedListener;
    protected boolean mPrepared;
    protected volatile boolean mRecording;
    protected boolean mStartPreview;
    protected List<Camera.Size> mSupportedPreviewSizes;
    protected boolean mSurfaceCreated;
    protected SurfaceHolder mSurfaceHolder;
    protected Camera.Parameters mParameters = null;
    protected int mFrameRate = MAX_FRAME_RATE;
    protected int mCameraId = 0;
    protected volatile long mPreviewFrameCallCount = 0;
    private String mFrameRateCmd = "";

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnEncodeListener {
        void onEncodeComplete();

        void onEncodeError();

        void onEncodeProgress(int i);

        void onEncodeStart();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnErrorListener {
        void onAudioError(int i, String str);

        void onVideoError(int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnPreparedListener {
        void onPrepared();
    }

    protected String getScaleWH() {
        return "";
    }

    protected void onStartPreviewSuccess() {
    }

    @Override // com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public void receiveAudioData(byte[] bArr, int i) {
    }

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        if (surfaceHolder != null) {
            surfaceHolder.addCallback(this);
            if (DeviceUtils.hasHoneycomb()) {
                return;
            }
            surfaceHolder.setType(3);
        }
    }

    public void setRecordState(boolean z) {
        this.mRecording = z;
    }

    public boolean getRecordState() {
        return this.mRecording;
    }

    public void setOnEncodeListener(OnEncodeListener onEncodeListener) {
        this.mOnEncodeListener = onEncodeListener;
    }

    public void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public boolean isFrontCamera() {
        return this.mCameraId == 1;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(9)
    public static boolean isSupportFrontCamera() {
        return DeviceUtils.hasGingerbread() && 2 == Camera.getNumberOfCameras();
    }

    public void switchCamera(int i) {
        switch (i) {
            case 0:
            case 1:
                this.mCameraId = i;
                stopPreview();
                startPreview();
                return;
            default:
                return;
        }
    }

    public void switchCamera() {
        if (this.mCameraId == 0) {
            switchCamera(1);
        } else {
            switchCamera(0);
        }
    }

    public boolean autoFocus(Camera.AutoFocusCallback autoFocusCallback) {
        Camera camera = this.camera;
        if (camera != null) {
            try {
                camera.cancelAutoFocus();
                if (this.mParameters != null) {
                    String autoFocusMode = getAutoFocusMode();
                    if (StringUtils.isNotEmpty(autoFocusMode)) {
                        this.mParameters.setFocusMode(autoFocusMode);
                        this.camera.setParameters(this.mParameters);
                    }
                }
                this.camera.autoFocus(autoFocusCallback);
                return true;
            } catch (Exception e) {
                OnErrorListener onErrorListener = this.mOnErrorListener;
                if (onErrorListener != null) {
                    onErrorListener.onVideoError(103, 0);
                }
                android.util.Log.e("jianxi", "autoFocus", e);
            }
        }
        return false;
    }

    private String getAutoFocusMode() {
        Camera.Parameters parameters = this.mParameters;
        if (parameters != null) {
            List<String> supportedFocusModes = parameters.getSupportedFocusModes();
            if ((Build.MODEL.startsWith("GT-I950") || Build.MODEL.endsWith("SCH-I959") || Build.MODEL.endsWith("MEIZU MX3")) && isSupported(supportedFocusModes, "continuous-picture")) {
                return "continuous-picture";
            }
            if (isSupported(supportedFocusModes, "continuous-video")) {
                return "continuous-video";
            }
            if (isSupported(supportedFocusModes, "auto")) {
                return "auto";
            }
            return null;
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(14)
    public boolean manualFocus(Camera.AutoFocusCallback autoFocusCallback, List<Camera.Area> list) {
        if (this.camera != null && list != null && this.mParameters != null && DeviceUtils.hasICS()) {
            try {
                this.camera.cancelAutoFocus();
                if (this.mParameters.getMaxNumFocusAreas() > 0) {
                    this.mParameters.setFocusAreas(list);
                }
                if (this.mParameters.getMaxNumMeteringAreas() > 0) {
                    this.mParameters.setMeteringAreas(list);
                }
                this.mParameters.setFocusMode("macro");
                this.camera.setParameters(this.mParameters);
                this.camera.autoFocus(autoFocusCallback);
                return true;
            } catch (Exception e) {
                OnErrorListener onErrorListener = this.mOnErrorListener;
                if (onErrorListener != null) {
                    onErrorListener.onVideoError(103, 0);
                }
                android.util.Log.e("jianxi", "autoFocus", e);
            }
        }
        return false;
    }

    public boolean toggleFlashMode() {
        Camera.Parameters parameters = this.mParameters;
        if (parameters != null) {
            try {
                String flashMode = parameters.getFlashMode();
                if (!TextUtils.isEmpty(flashMode) && !"off".equals(flashMode)) {
                    setFlashMode("off");
                    return true;
                }
                setFlashMode("torch");
                return true;
            } catch (Exception e) {
                android.util.Log.e("jianxi", "toggleFlashMode", e);
                return false;
            }
        }
        return false;
    }

    private boolean setFlashMode(String str) {
        if (this.mParameters == null || this.camera == null) {
            return false;
        }
        try {
            if ("torch".equals(str) || "off".equals(str)) {
                this.mParameters.setFlashMode(str);
                this.camera.setParameters(this.mParameters);
                return true;
            }
            return true;
        } catch (Exception e) {
            android.util.Log.e("jianxi", "setFlashMode", e);
            return false;
        }
    }

    public void setVideoBitRate(int i) {
        if (i > 0) {
            mVideoBitrate = i;
        }
    }

    public void prepare() {
        this.mPrepared = true;
        if (this.mSurfaceCreated) {
            startPreview();
        }
    }

    public MediaObject setOutputDirectory(String str, String str2) {
        if (StringUtils.isNotEmpty(str2)) {
            File file = new File(str2);
            if (file.exists()) {
                if (file.isDirectory()) {
                    FileUtils.deleteDir(file);
                } else {
                    FileUtils.deleteFile(file);
                }
            }
            if (file.mkdirs()) {
                this.mMediaObject = new MediaObject(str, str2, mVideoBitrate);
            }
        }
        return this.mMediaObject;
    }

    public void setMediaObject(MediaObject mediaObject) {
        this.mMediaObject = mediaObject;
    }

    @Override // com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public void stopRecord() {
        this.mRecording = false;
        setStopDate();
    }

    public void setStopDate() {
        MediaObject.MediaPart currentPart;
        MediaObject mediaObject = this.mMediaObject;
        if (mediaObject == null || (currentPart = mediaObject.getCurrentPart()) == null || !currentPart.recording) {
            return;
        }
        currentPart.recording = false;
        currentPart.endTime = System.currentTimeMillis();
        currentPart.duration = (int) (currentPart.endTime - currentPart.startTime);
        currentPart.cutStartTime = 0;
        currentPart.cutEndTime = currentPart.duration;
    }

    private void stopAllRecord() {
        this.mRecording = false;
        MediaObject mediaObject = this.mMediaObject;
        if (mediaObject == null || mediaObject.getMedaParts() == null) {
            return;
        }
        Iterator<MediaObject.MediaPart> it = this.mMediaObject.getMedaParts().iterator();
        while (it.hasNext()) {
            MediaObject.MediaPart next = it.next();
            if (next != null && next.recording) {
                next.recording = false;
                next.endTime = System.currentTimeMillis();
                next.duration = (int) (next.endTime - next.startTime);
                next.cutStartTime = 0;
                next.cutEndTime = next.duration;
                if (new File(next.mediaPath).length() < 1) {
                    this.mMediaObject.removePart(next, true);
                }
            }
        }
    }

    private boolean isSupported(List<String> list, String str) {
        return list != null && list.contains(str);
    }

    protected void prepareCameraParaments() {
        boolean z;
        Camera.Parameters parameters = this.mParameters;
        if (parameters == null) {
            return;
        }
        List<Integer> supportedPreviewFrameRates = parameters.getSupportedPreviewFrameRates();
        boolean z2 = false;
        if (supportedPreviewFrameRates != null) {
            if (supportedPreviewFrameRates.contains(Integer.valueOf(MAX_FRAME_RATE))) {
                this.mFrameRate = MAX_FRAME_RATE;
            } else {
                Collections.sort(supportedPreviewFrameRates);
                int size = supportedPreviewFrameRates.size() - 1;
                while (true) {
                    if (size < 0) {
                        z = false;
                        break;
                    } else if (supportedPreviewFrameRates.get(size).intValue() <= MAX_FRAME_RATE) {
                        this.mFrameRate = supportedPreviewFrameRates.get(size).intValue();
                        z = true;
                        break;
                    } else {
                        size--;
                    }
                }
                if (!z) {
                    this.mFrameRate = supportedPreviewFrameRates.get(0).intValue();
                }
            }
        }
        this.mParameters.setPreviewFrameRate(this.mFrameRate);
        int size2 = this.mSupportedPreviewSizes.size() - 1;
        while (true) {
            if (size2 < 0) {
                break;
            }
            Camera.Size size3 = this.mSupportedPreviewSizes.get(size2);
            if (size3.height == SMALL_VIDEO_HEIGHT) {
                mSupportedPreviewWidth = size3.width;
                checkFullWidth(mSupportedPreviewWidth, SMALL_VIDEO_WIDTH);
                z2 = true;
                break;
            }
            size2--;
        }
        if (!z2) {
            android.util.Log.e(getClass().getSimpleName(), "传入高度不支持或未找到对应宽度,请按照要求重新设置，否则会出现一些严重问题");
            mSupportedPreviewWidth = 640;
            checkFullWidth(640, 360);
            SMALL_VIDEO_HEIGHT = 480;
        }
        this.mParameters.setPreviewSize(mSupportedPreviewWidth, SMALL_VIDEO_HEIGHT);
        this.mParameters.setPreviewFormat(IjkMediaPlayer.SDL_FCC_YV12);
        String autoFocusMode = getAutoFocusMode();
        if (StringUtils.isNotEmpty(autoFocusMode)) {
            this.mParameters.setFocusMode(autoFocusMode);
        }
        if (isSupported(this.mParameters.getSupportedWhiteBalance(), "auto")) {
            this.mParameters.setWhiteBalance("auto");
        }
        if ("true".equals(this.mParameters.get("video-stabilization-supported"))) {
            this.mParameters.set("video-stabilization", "true");
        }
        if (DeviceUtils.isDevice("GT-N7100", "GT-I9308", "GT-I9300")) {
            return;
        }
        this.mParameters.set("cam_mode", 1);
        this.mParameters.set("cam-mode", 1);
    }

    private void checkFullWidth(int i, int i2) {
        if (NEED_FULL_SCREEN) {
            SMALL_VIDEO_WIDTH = i;
        } else {
            SMALL_VIDEO_WIDTH = i2;
        }
    }

    public void startPreview() {
        if (this.mStartPreview || this.mSurfaceHolder == null || !this.mPrepared) {
            return;
        }
        this.mStartPreview = true;
        try {
            if (this.mCameraId == 0) {
                this.camera = Camera.open();
            } else {
                this.camera = Camera.open(this.mCameraId);
            }
            this.camera.setDisplayOrientation(90);
            try {
                this.camera.setPreviewDisplay(this.mSurfaceHolder);
            } catch (IOException e) {
                if (this.mOnErrorListener != null) {
                    this.mOnErrorListener.onVideoError(101, 0);
                }
                android.util.Log.e("jianxi", "setPreviewDisplay fail " + e.getMessage());
            }
            this.mParameters = this.camera.getParameters();
            this.mSupportedPreviewSizes = this.mParameters.getSupportedPreviewSizes();
            prepareCameraParaments();
            this.camera.setParameters(this.mParameters);
            setPreviewCallback();
            this.camera.startPreview();
            onStartPreviewSuccess();
            if (this.mOnPreparedListener != null) {
                this.mOnPreparedListener.onPrepared();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            OnErrorListener onErrorListener = this.mOnErrorListener;
            if (onErrorListener != null) {
                onErrorListener.onVideoError(102, 0);
            }
            android.util.Log.e("jianxi", "startPreview fail :" + e2.getMessage());
        }
    }

    protected void setPreviewCallback() {
        Camera.Size previewSize = this.mParameters.getPreviewSize();
        if (previewSize != null) {
            int i = ((previewSize.width * previewSize.height) * 3) / 2;
            try {
                this.camera.addCallbackBuffer(new byte[i]);
                this.camera.addCallbackBuffer(new byte[i]);
                this.camera.addCallbackBuffer(new byte[i]);
                this.camera.setPreviewCallbackWithBuffer(this);
            } catch (OutOfMemoryError e) {
                android.util.Log.e("jianxi", "startPreview...setPreviewCallback...", e);
            }
            android.util.Log.e("jianxi", "startPreview...setPreviewCallbackWithBuffer...width:" + previewSize.width + " height:" + previewSize.height);
            return;
        }
        this.camera.setPreviewCallback(this);
    }

    public void stopPreview() {
        Camera camera = this.camera;
        if (camera != null) {
            try {
                camera.stopPreview();
                this.camera.setPreviewCallback(null);
                this.camera.release();
            } catch (Exception unused) {
                android.util.Log.e("jianxi", "stopPreview...");
            }
            this.camera = null;
        }
        this.mStartPreview = false;
    }

    public void release() {
        FFmpegBridge.nativeRelease();
        stopAllRecord();
        stopPreview();
        AudioRecorder audioRecorder = this.mAudioRecorder;
        if (audioRecorder != null) {
            audioRecorder.interrupt();
            this.mAudioRecorder = null;
        }
        this.mSurfaceHolder = null;
        this.mPrepared = false;
        this.mSurfaceCreated = false;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        this.mSurfaceCreated = true;
        if (!this.mPrepared || this.mStartPreview) {
            return;
        }
        startPreview();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        this.mSurfaceHolder = surfaceHolder;
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = null;
        this.mSurfaceCreated = false;
    }

    @Override // com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public void onAudioError(int i, String str) {
        OnErrorListener onErrorListener = this.mOnErrorListener;
        if (onErrorListener != null) {
            onErrorListener.onAudioError(i, str);
        }
    }

    @Override // android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        camera.addCallbackBuffer(bArr);
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.mabeijianxi.smallvideorecord2.MediaRecorderBase$1] */
    public void testPreviewFrameCallCount() {
        new CountDownTimer(60000L, 1000L) { // from class: com.mabeijianxi.smallvideorecord2.MediaRecorderBase.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                android.util.Log.e("[Vitamio Recorder]", "testFrameRate..." + MediaRecorderBase.this.mPreviewFrameCallCount);
                MediaRecorderBase.this.mPreviewFrameCallCount = 0L;
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Boolean doCompress(boolean z) {
        String str;
        BaseMediaBitrateConfig baseMediaBitrateConfig = this.compressConfig;
        boolean z2 = true;
        if (baseMediaBitrateConfig != null) {
            String str2 = " -vbr 4 ";
            if (baseMediaBitrateConfig != null && baseMediaBitrateConfig.getMode() == 2) {
                str2 = "";
            }
            String scaleWH = getScaleWH();
            if (TextUtils.isEmpty(scaleWH)) {
                str = "";
            } else {
                str = "-s " + scaleWH;
            }
            boolean z3 = FFmpegBridge.jxFFmpegCMDRun(String.format("ffmpeg -threads 16 -i %s -c:v libx264 %s %s %s -c:a libfdk_aac %s %s %s %s", this.mMediaObject.getOutputTempVideoPath(), getBitrateModeCommand(this.compressConfig, "", false), getBitrateCrfSize(this.compressConfig, "-crf 28", false), getBitrateVelocity(this.compressConfig, "-preset:v ultrafast", false), str2, getFrameRateCmd(), str, this.mMediaObject.getOutputTempTranscodingVideoPath())) == 0;
            boolean captureThumbnails = FFMpegUtils.captureThumbnails(this.mMediaObject.getOutputTempTranscodingVideoPath(), this.mMediaObject.getOutputVideoThumbPath(), String.valueOf(CAPTURE_THUMBNAILS_TIME));
            FileUtils.deleteCacheFile(this.mMediaObject.getOutputDirectory());
            if (!z || !captureThumbnails || !z3) {
                z2 = false;
            }
            return Boolean.valueOf(z2);
        }
        boolean captureThumbnails2 = FFMpegUtils.captureThumbnails(this.mMediaObject.getOutputTempVideoPath(), this.mMediaObject.getOutputVideoThumbPath(), String.valueOf(CAPTURE_THUMBNAILS_TIME));
        FileUtils.deleteCacheFile2TS(this.mMediaObject.getOutputDirectory());
        if (!captureThumbnails2 || !z) {
            z2 = false;
        }
        return Boolean.valueOf(z2);
    }

    protected String getFrameRateCmd() {
        return this.mFrameRateCmd;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTranscodingFrameRate(int i) {
        this.mFrameRateCmd = String.format(" -r %d", Integer.valueOf(i));
    }

    protected String getBitrateModeCommand(BaseMediaBitrateConfig baseMediaBitrateConfig, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (baseMediaBitrateConfig != null) {
            if (baseMediaBitrateConfig.getMode() == 1) {
                return z ? String.format(" -x264opts \"bitrate=%d:vbv-maxrate=%d\" ", Integer.valueOf(baseMediaBitrateConfig.getBitrate()), Integer.valueOf(baseMediaBitrateConfig.getMaxBitrate())) : String.format(" -x264opts bitrate=%d:vbv-maxrate=%d ", Integer.valueOf(baseMediaBitrateConfig.getBitrate()), Integer.valueOf(baseMediaBitrateConfig.getMaxBitrate()));
            } else if (baseMediaBitrateConfig.getMode() == 2) {
                return z ? String.format(" -x264opts \"bitrate=%d:vbv-bufsize=%d:nal_hrd=cbr\" ", Integer.valueOf(baseMediaBitrateConfig.getBitrate()), Integer.valueOf(baseMediaBitrateConfig.getBufSize())) : String.format(" -x264opts bitrate=%d:vbv-bufsize=%d:nal_hrd=cbr ", Integer.valueOf(baseMediaBitrateConfig.getBitrate()), Integer.valueOf(baseMediaBitrateConfig.getBufSize()));
            }
        }
        return str;
    }

    protected String getBitrateCrfSize(BaseMediaBitrateConfig baseMediaBitrateConfig, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return (baseMediaBitrateConfig == null || baseMediaBitrateConfig.getMode() != 3 || baseMediaBitrateConfig.getCrfSize() <= 0) ? str : z ? String.format("-crf \"%d\" ", Integer.valueOf(baseMediaBitrateConfig.getCrfSize())) : String.format("-crf %d ", Integer.valueOf(baseMediaBitrateConfig.getCrfSize()));
    }

    protected String getBitrateVelocity(BaseMediaBitrateConfig baseMediaBitrateConfig, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return (baseMediaBitrateConfig == null || TextUtils.isEmpty(baseMediaBitrateConfig.getVelocity())) ? str : z ? String.format("-preset \"%s\" ", baseMediaBitrateConfig.getVelocity()) : String.format("-preset %s ", baseMediaBitrateConfig.getVelocity());
    }
}
