package com.mabeijianxi.smallvideorecord2;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Handler;
import android.os.Looper;
import com.mabeijianxi.smallvideorecord2.jniinterface.FFmpegBridge;
import com.mabeijianxi.smallvideorecord2.model.MediaObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaRecorderNative extends MediaRecorderBase implements MediaRecorder.OnErrorListener, FFmpegBridge.FFmpegStateListener {
    private static final String VIDEO_SUFFIX = ".ts";

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase
    protected void onStartPreviewSuccess() {
    }

    public MediaRecorderNative() {
        FFmpegBridge.registFFmpegStateListener(this);
    }

    @Override // com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public MediaObject.MediaPart startRecord() {
        FFmpegBridge.prepareJXFFmpegEncoder(this.mMediaObject.getOutputDirectory(), this.mMediaObject.getBaseName(), this.mCameraId == 0 ? 1 : 3, mSupportedPreviewWidth, SMALL_VIDEO_HEIGHT, SMALL_VIDEO_WIDTH, SMALL_VIDEO_HEIGHT, this.mFrameRate, mVideoBitrate);
        MediaObject.MediaPart mediaPart = null;
        if (this.mMediaObject != null) {
            mediaPart = this.mMediaObject.buildMediaPart(this.mCameraId, VIDEO_SUFFIX);
            String.format("filename = \"%s\"; ", mediaPart.mediaPath);
            if (this.mAudioRecorder == null && mediaPart != null) {
                this.mAudioRecorder = new AudioRecorder(this);
                this.mAudioRecorder.start();
            }
            this.mRecording = true;
        }
        return mediaPart;
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase, com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public void stopRecord() {
        super.stopRecord();
        if (this.mOnEncodeListener != null) {
            this.mOnEncodeListener.onEncodeStart();
        }
        FFmpegBridge.recordEnd();
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase, android.hardware.Camera.PreviewCallback
    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (this.mRecording) {
            FFmpegBridge.encodeFrame2H264(bArr);
            this.mPreviewFrameCallCount++;
        }
        super.onPreviewFrame(bArr, camera);
    }

    @Override // android.media.MediaRecorder.OnErrorListener
    public void onError(MediaRecorder mediaRecorder, int i, int i2) {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.reset();
            } catch (IllegalStateException e) {
                android.util.Log.w("jianxi", "stopRecord", e);
            } catch (Exception e2) {
                android.util.Log.w("jianxi", "stopRecord", e2);
            }
        }
        if (this.mOnErrorListener != null) {
            this.mOnErrorListener.onVideoError(i, i2);
        }
    }

    @Override // com.mabeijianxi.smallvideorecord2.MediaRecorderBase, com.mabeijianxi.smallvideorecord2.IMediaRecorder
    public void receiveAudioData(byte[] bArr, int i) {
        if (!this.mRecording || i <= 0) {
            return;
        }
        FFmpegBridge.encodeFrame2AAC(bArr);
    }

    @Override // com.mabeijianxi.smallvideorecord2.jniinterface.FFmpegBridge.FFmpegStateListener
    public void allRecordEnd() {
        final boolean captureThumbnails = FFMpegUtils.captureThumbnails(this.mMediaObject.getOutputTempTranscodingVideoPath(), this.mMediaObject.getOutputVideoThumbPath(), String.valueOf(CAPTURE_THUMBNAILS_TIME));
        if (this.mOnEncodeListener != null) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.mabeijianxi.smallvideorecord2.MediaRecorderNative.1
                @Override // java.lang.Runnable
                public void run() {
                    if (captureThumbnails) {
                        MediaRecorderNative.this.mOnEncodeListener.onEncodeComplete();
                    } else {
                        MediaRecorderNative.this.mOnEncodeListener.onEncodeError();
                    }
                }
            }, 0L);
        }
    }

    public void activityStop() {
        FFmpegBridge.unRegistFFmpegStateListener(this);
    }
}
