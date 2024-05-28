package com.baidu.cloud.media.player.render.record;

import android.graphics.SurfaceTexture;
import android.text.TextUtils;
import com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder;
import com.baidu.cloud.media.player.render.p134a.FullFrameRect;
import com.baidu.cloud.media.player.render.p135b.Logger;
import com.baidu.cloud.media.player.render.p135b.SafeHandler;
import com.baidu.cloud.media.player.render.texturerender.CommonRecordRenderer;
import com.baidu.cloud.media.player.render.texturerender.IMediaRenderCallback;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RecordController implements TextureMovieEncoder.OnDrawEncoderFrameCallback, IMediaRenderCallback {
    private static final String TAG = "RecordController";
    private boolean isStartRecord;
    private CommonRecordRenderer mBaseRenderer;
    private IRecordListener mOnRecordListener;
    private String mRecordVideoPath;
    private SurfaceTexture mSurfaceTexture;
    private int mVideoBitrate;
    private int mVideoFrameRate;
    private int mVideoHeight;
    private VideoRecorder mVideoRecorder;
    private int mVideoWidth;

    public void init(SurfaceTexture surfaceTexture, int i, int i2) {
        this.mSurfaceTexture = surfaceTexture;
        this.mVideoBitrate = i2;
        this.mVideoFrameRate = i;
        this.mBaseRenderer = new CommonRecordRenderer();
    }

    public void setRecordListener(IRecordListener iRecordListener) {
        this.mOnRecordListener = iRecordListener;
    }

    public List<IMediaRenderCallback> getRenderCallbackList() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add(this);
        copyOnWriteArrayList.add(this.mBaseRenderer);
        return copyOnWriteArrayList;
    }

    public String getVideoAbsolutePath() {
        return this.mRecordVideoPath;
    }

    public void setRecordSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void onDestroy() {
        if (this.isStartRecord) {
            stopRecord();
        }
        this.mSurfaceTexture = null;
        this.mBaseRenderer = null;
        this.mVideoRecorder = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyError(final int i, final String str) {
        if (this.mOnRecordListener != null) {
            SafeHandler.m20029a().post(new Runnable() { // from class: com.baidu.cloud.media.player.render.record.RecordController.1
                @Override // java.lang.Runnable
                public void run() {
                    if (RecordController.this.mOnRecordListener != null) {
                        RecordController.this.mOnRecordListener.onError(i, str);
                    }
                }
            });
        }
    }

    public void startRecord(String str) {
        if (TextUtils.isEmpty(str)) {
            Logger.m20030b("RecordController", "设置输出路径错误");
            notifyError(1101, "设置输出路径错误");
        } else if (this.isStartRecord) {
            notifyError(1113, "已经开始录制");
        } else {
            VideoRecorder videoRecorder = this.mVideoRecorder;
            if (videoRecorder == null || !videoRecorder.m19979a()) {
                this.mRecordVideoPath = str;
                this.isStartRecord = true;
                if (this.mVideoRecorder == null) {
                    this.mVideoRecorder = new VideoRecorder();
                    this.mVideoRecorder.m19977a(this);
                    this.mVideoRecorder.m19978a(this.mVideoBitrate);
                    this.mVideoRecorder.m19976b(this.mVideoFrameRate);
                    this.mVideoRecorder.setOnEncoderStatusUpdateListener(new TextureMovieEncoder.OnEncoderStatusUpdateListener() { // from class: com.baidu.cloud.media.player.render.record.RecordController.2
                        @Override // com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder.OnEncoderStatusUpdateListener
                        public void onError(int i, String str2) {
                            RecordController.this.notifyError(i, str2);
                        }

                        @Override // com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder.OnEncoderStatusUpdateListener
                        public void onProgress(long j) {
                            if (RecordController.this.mOnRecordListener != null) {
                                RecordController.this.mOnRecordListener.onProgress(j);
                            }
                        }

                        @Override // com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder.OnEncoderStatusUpdateListener
                        public void onStartSuccess() {
                            SafeHandler.m20029a().post(new Runnable() { // from class: com.baidu.cloud.media.player.render.record.RecordController.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (RecordController.this.mOnRecordListener != null) {
                                        RecordController.this.mOnRecordListener.onStartSuccess();
                                    }
                                }
                            });
                        }

                        @Override // com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder.OnEncoderStatusUpdateListener
                        public void onStopSuccess() {
                            SafeHandler.m20029a().post(new Runnable() { // from class: com.baidu.cloud.media.player.render.record.RecordController.2.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    if (RecordController.this.mOnRecordListener != null) {
                                        RecordController.this.mOnRecordListener.onStopSuccess();
                                    }
                                }
                            });
                        }
                    });
                }
                this.mVideoRecorder.setSurfaceTexture(this.mSurfaceTexture);
                int i = this.mVideoHeight;
                int i2 = i % 16;
                if (i2 != 0) {
                    i += 16 - i2;
                }
                this.mVideoRecorder.setRecordSize(this.mVideoWidth, i);
                this.mVideoRecorder.setRecordFrameListener(this.mBaseRenderer);
                this.mVideoRecorder.startRecord(str);
            }
        }
    }

    public boolean stopRecord() {
        this.isStartRecord = false;
        if (this.mVideoRecorder.m19979a()) {
            try {
                this.mVideoRecorder.stopRecord();
                return true;
            } catch (Throwable th) {
                th.printStackTrace();
                notifyError(1116, "结束录制错误");
                return false;
            }
        }
        return false;
    }

    public void updateExternalAudioData(byte[] bArr, int i) {
        VideoRecorder videoRecorder;
        if (!this.isStartRecord || (videoRecorder = this.mVideoRecorder) == null) {
            return;
        }
        videoRecorder.updateExternalAudioData(bArr, i);
    }

    public boolean isRecording() {
        VideoRecorder videoRecorder = this.mVideoRecorder;
        return videoRecorder != null && videoRecorder.m19979a();
    }

    @Override // com.baidu.cloud.media.player.render.texturerender.IMediaRenderCallback
    public void onDrawFrame(FullFrameRect fullFrameRect, int i, float[] fArr) {
        VideoRecorder videoRecorder;
        if (!this.isStartRecord || (videoRecorder = this.mVideoRecorder) == null) {
            return;
        }
        videoRecorder.onRecord();
    }

    @Override // com.baidu.cloud.media.player.render.encoder.TextureMovieEncoder.OnDrawEncoderFrameCallback
    public void onDrawEncoderFrame() {
        CommonRecordRenderer commonRecordRenderer = this.mBaseRenderer;
        if (commonRecordRenderer != null) {
            commonRecordRenderer.m19975a();
        }
    }
}
