package com.baidu.rtc.recorder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.baidu.rtc.internal.BaiduRtcRoomImp;
import com.webrtc.EglBase;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class BRTCMediaRecorder implements IRtcMediaRecorder, MediaDataCallback {
    public static final int STATE_IDLE = 0;
    public static final int STATE_RECORDING = 1;
    public static final int STATE_RELEASED = 2;
    private static final String TAG = "com.baidu.rtc.recorder.BRTCMediaRecorder";
    static final long TIMEOUT = 10000;
    private RtcAudioRecorder mAudioRecorder;
    private BRTCMediaRecorderCallback mCallback;
    private long mCurrentCallbackCount;
    private int mCurrentState;
    private FileOutputStream mFosAccAudio;
    private long mInfoUpdateInterval;
    private long mMaxRecordDuration;
    private MediaMuxer mMediaMuxer;
    private boolean mMediaMuxerStarted;
    private BRTCMediaRecorderParams mParams;
    private final WeakReference<BaiduRtcRoomImp> mRtcRoomImpRef;
    private final EglBase.Context mSharedContext;
    private boolean mVideoOnly;
    private RtcVideoRecorder mVideoRecorder;
    private final Handler mWorkThreadHandler;
    private int mVideoTrackId = -1;
    private int mAudioTrackId = -1;
    private final HandlerThread mWorkThread = new HandlerThread("RtcMediaMuxer");

    public BRTCMediaRecorder(BaiduRtcRoomImp baiduRtcRoomImp, EglBase.Context context) {
        this.mCurrentState = 0;
        this.mRtcRoomImpRef = new WeakReference<>(baiduRtcRoomImp);
        this.mSharedContext = context;
        this.mWorkThread.start();
        this.mWorkThreadHandler = new Handler(this.mWorkThread.getLooper());
        this.mCurrentState = 0;
    }

    @Override // com.baidu.rtc.recorder.MediaDataCallback
    public void addTrack(final boolean z, final MediaFormat mediaFormat) {
        if (this.mMediaMuxer != null) {
            this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$BRTCMediaRecorder$e99iD7mIOo83XZKBD_QJAum7IhE
                @Override // java.lang.Runnable
                public final void run() {
                    BRTCMediaRecorder.lambda$addTrack$0(BRTCMediaRecorder.this, mediaFormat, z);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$addTrack$0(BRTCMediaRecorder bRTCMediaRecorder, MediaFormat mediaFormat, boolean z) {
        MediaMuxer mediaMuxer = bRTCMediaRecorder.mMediaMuxer;
        if (mediaMuxer != null) {
            int addTrack = mediaMuxer.addTrack(mediaFormat);
            log(" addTrack isVideo : " + z + " trackId : " + addTrack);
            if (z) {
                bRTCMediaRecorder.mVideoTrackId = addTrack;
            } else {
                bRTCMediaRecorder.mAudioTrackId = addTrack;
            }
            if (bRTCMediaRecorder.mMediaMuxerStarted) {
                return;
            }
            if (z && bRTCMediaRecorder.mVideoOnly) {
                bRTCMediaRecorder.notifyStart();
            } else if (bRTCMediaRecorder.mAudioTrackId == -1 || bRTCMediaRecorder.mVideoTrackId == -1) {
            } else {
                bRTCMediaRecorder.notifyStart();
            }
        }
    }

    @Override // com.baidu.rtc.recorder.MediaDataCallback
    public void onStop(final boolean z) {
        this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$BRTCMediaRecorder$WEMl2fSdKDL_5rtHrkZ4AdDwMBo
            @Override // java.lang.Runnable
            public final void run() {
                BRTCMediaRecorder.lambda$onStop$1(BRTCMediaRecorder.this, z);
            }
        });
    }

    public static /* synthetic */ void lambda$onStop$1(BRTCMediaRecorder bRTCMediaRecorder, boolean z) {
        if (bRTCMediaRecorder.mMediaMuxer != null) {
            if (z) {
                bRTCMediaRecorder.mVideoTrackId = -1;
            } else {
                bRTCMediaRecorder.mAudioTrackId = -1;
            }
            if (z && bRTCMediaRecorder.mVideoOnly) {
                bRTCMediaRecorder.notifyStop();
            } else if (bRTCMediaRecorder.mAudioTrackId == -1 && bRTCMediaRecorder.mVideoTrackId == -1) {
                bRTCMediaRecorder.notifyStop();
            }
        }
        FileOutputStream fileOutputStream = bRTCMediaRecorder.mFosAccAudio;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bRTCMediaRecorder.mFosAccAudio = null;
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = bRTCMediaRecorder.mCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(2, 0);
            }
            if (bRTCMediaRecorder.mCurrentState == 2) {
                bRTCMediaRecorder.mWorkThread.quitSafely();
            }
        }
    }

    private void notifyStart() {
        RtcAudioRecorder rtcAudioRecorder;
        RtcAudioRecorder rtcAudioRecorder2;
        log(" notifyStart ");
        try {
            this.mMediaMuxer.start();
            this.mMediaMuxerStarted = true;
            if (!this.mVideoOnly && this.mAudioRecorder != null) {
                this.mAudioRecorder.setMuxerState(true);
            }
            if (this.mVideoRecorder != null) {
                this.mVideoRecorder.setMuxerState(true);
            }
            if (this.mCallback != null) {
                this.mCallback.onRecordStateChanged(1, 0);
            }
        } catch (Exception e) {
            log(" error muxer start by exception : " + e);
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = this.mCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(1, -108);
            }
            BaiduRtcRoomImp baiduRtcRoomImp = this.mRtcRoomImpRef.get();
            if (baiduRtcRoomImp != null) {
                if (!this.mVideoOnly && (rtcAudioRecorder2 = this.mAudioRecorder) != null) {
                    baiduRtcRoomImp.unregisterAudioRecord(rtcAudioRecorder2);
                }
                RtcVideoRecorder rtcVideoRecorder = this.mVideoRecorder;
                if (rtcVideoRecorder != null) {
                    baiduRtcRoomImp.unregisterLocalSink(rtcVideoRecorder);
                }
            }
            if (!this.mVideoOnly && (rtcAudioRecorder = this.mAudioRecorder) != null) {
                rtcAudioRecorder.releaseOnceOnWorkThread();
            }
            RtcVideoRecorder rtcVideoRecorder2 = this.mVideoRecorder;
            if (rtcVideoRecorder2 != null) {
                rtcVideoRecorder2.releaseOnceOnWorkThread();
            }
            this.mCurrentState = 0;
        }
    }

    private void notifyStop() {
        log(" notifyStop ");
        if (this.mMediaMuxerStarted) {
            try {
                try {
                    this.mMediaMuxer.stop();
                    this.mMediaMuxer.release();
                    if (this.mCallback != null) {
                        this.mCallback.onRecordStateChanged(2, 0);
                    }
                    this.mMediaMuxer = null;
                    this.mMediaMuxerStarted = false;
                    if (this.mCurrentState != 2) {
                        return;
                    }
                } catch (Exception e) {
                    log(" error muxer stop by exception : " + e);
                    if (this.mCallback != null) {
                        this.mCallback.onRecordStateChanged(2, -107);
                    }
                    this.mMediaMuxer = null;
                    this.mMediaMuxerStarted = false;
                    if (this.mCurrentState != 2) {
                        return;
                    }
                }
                this.mWorkThread.quitSafely();
            } catch (Throwable th) {
                this.mMediaMuxer = null;
                this.mMediaMuxerStarted = false;
                if (this.mCurrentState == 2) {
                    this.mWorkThread.quitSafely();
                }
                throw th;
            }
        }
    }

    @Override // com.baidu.rtc.recorder.MediaDataCallback
    public void writeSampleData(final boolean z, final ByteBuffer byteBuffer, final MediaCodec.BufferInfo bufferInfo) {
        if (this.mMediaMuxer != null) {
            this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$BRTCMediaRecorder$wtzpvjN_-udnn1gtroUx20h27VY
                @Override // java.lang.Runnable
                public final void run() {
                    BRTCMediaRecorder.lambda$writeSampleData$2(BRTCMediaRecorder.this, z, byteBuffer, bufferInfo);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$writeSampleData$2(BRTCMediaRecorder bRTCMediaRecorder, boolean z, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (!bRTCMediaRecorder.mMediaMuxerStarted) {
            log(" error writeSampleData by not started");
            return;
        }
        try {
            if (bRTCMediaRecorder.mMediaMuxer != null) {
                bRTCMediaRecorder.mMediaMuxer.writeSampleData(z ? bRTCMediaRecorder.mVideoTrackId : bRTCMediaRecorder.mAudioTrackId, byteBuffer, bufferInfo);
            }
            bRTCMediaRecorder.checkCallback(bufferInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log(" error writeSampleData by exception : " + e);
            bRTCMediaRecorder.stopRecording();
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = bRTCMediaRecorder.mCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(-1, -105);
            }
        }
    }

    @Override // com.baidu.rtc.recorder.MediaDataCallback
    public void writeSampleData(final byte[] bArr, final MediaCodec.BufferInfo bufferInfo) {
        if (this.mFosAccAudio != null) {
            this.mWorkThreadHandler.post(new Runnable() { // from class: com.baidu.rtc.recorder.-$$Lambda$BRTCMediaRecorder$W3nRVj7UXIgvwYTFB5gMufVpoX8
                @Override // java.lang.Runnable
                public final void run() {
                    BRTCMediaRecorder.lambda$writeSampleData$3(BRTCMediaRecorder.this, bArr, bufferInfo);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$writeSampleData$3(BRTCMediaRecorder bRTCMediaRecorder, byte[] bArr, MediaCodec.BufferInfo bufferInfo) {
        try {
            if (bRTCMediaRecorder.mFosAccAudio != null) {
                bRTCMediaRecorder.mFosAccAudio.write(bArr);
            }
            bRTCMediaRecorder.checkCallback(bufferInfo);
        } catch (IOException e) {
            e.printStackTrace();
            bRTCMediaRecorder.stopRecording();
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = bRTCMediaRecorder.mCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(-1, -105);
            }
        }
    }

    private void checkCallback(MediaCodec.BufferInfo bufferInfo) {
        long j;
        long j2 = bufferInfo.presentationTimeUs / 1000;
        logFrequently(" check callback mMaxRecordDuration : " + this.mMaxRecordDuration + " mInfoUpdateInterval : " + this.mInfoUpdateInterval + " currentDuration : " + j2 + " mCurrentCallbackCount : " + this.mCurrentCallbackCount);
        if (this.mInfoUpdateInterval > 0) {
            if (this.mParams.audioOnly()) {
                j = this.mParams.encodeParams.audioBitrate / 8;
            } else if (this.mParams.videoOnly()) {
                j = this.mParams.encodeParams.videoBitrate / 8;
            } else {
                j = (this.mParams.encodeParams.audioBitrate / 8) + (this.mParams.encodeParams.videoBitrate / 8);
            }
            long j3 = j * j2;
            if (j2 / this.mInfoUpdateInterval > this.mCurrentCallbackCount) {
                if (this.mCallback != null) {
                    logFrequently(" check callback currentDuration : " + j2 + " fileSize : " + j3);
                    this.mCallback.onRecordInfoUpdate(j2, j3);
                }
                this.mCurrentCallbackCount = j2 / this.mInfoUpdateInterval;
            }
        }
        long j4 = this.mMaxRecordDuration;
        if (j4 <= 0 || j2 <= j4) {
            return;
        }
        stopRecording();
        BRTCMediaRecorderCallback bRTCMediaRecorderCallback = this.mCallback;
        if (bRTCMediaRecorderCallback != null) {
            bRTCMediaRecorderCallback.onRecordStateChanged(-1, -102);
        }
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public int startRecording(BRTCMediaRecorderParams bRTCMediaRecorderParams) {
        BRTCMediaRecorderCallback bRTCMediaRecorderCallback;
        StringBuilder sb = new StringBuilder();
        sb.append(" startRecording --- ");
        sb.append(this.mCurrentState == 1);
        sb.append(" params : ");
        sb.append(bRTCMediaRecorderParams.mediaRecorderType);
        log(sb.toString());
        if (this.mCurrentState == 1) {
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback2 = this.mCallback;
            if (bRTCMediaRecorderCallback2 != null) {
                bRTCMediaRecorderCallback2.onRecordStateChanged(-1, -103);
            }
            return -103;
        } else if (!bRTCMediaRecorderParams.checkParamsValidate()) {
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback3 = this.mCallback;
            if (bRTCMediaRecorderCallback3 != null) {
                bRTCMediaRecorderCallback3.onRecordStateChanged(-1, -100);
            }
            return -100;
        } else {
            File file = new File(bRTCMediaRecorderParams.storagePath);
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                try {
                    if (!file.getParentFile().mkdirs()) {
                        if (this.mCallback != null) {
                            this.mCallback.onRecordStateChanged(-1, -100);
                        }
                        return -100;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    BRTCMediaRecorderCallback bRTCMediaRecorderCallback4 = this.mCallback;
                    if (bRTCMediaRecorderCallback4 != null) {
                        bRTCMediaRecorderCallback4.onRecordStateChanged(-1, -100);
                    }
                    return -100;
                }
            }
            BaiduRtcRoomImp baiduRtcRoomImp = this.mRtcRoomImpRef.get();
            if (baiduRtcRoomImp == null) {
                BRTCMediaRecorderCallback bRTCMediaRecorderCallback5 = this.mCallback;
                if (bRTCMediaRecorderCallback5 != null) {
                    bRTCMediaRecorderCallback5.onRecordStateChanged(-1, -104);
                }
                return -104;
            }
            this.mParams = bRTCMediaRecorderParams;
            this.mVideoOnly = bRTCMediaRecorderParams.videoOnly();
            this.mMaxRecordDuration = bRTCMediaRecorderParams.maxRecordDuration;
            this.mInfoUpdateInterval = bRTCMediaRecorderParams.infoUpdateInterval;
            if (bRTCMediaRecorderParams.hasVideo()) {
                try {
                    this.mMediaMuxer = new MediaMuxer(bRTCMediaRecorderParams.storagePath, 0);
                } catch (IOException e2) {
                    e2.printStackTrace();
                    BRTCMediaRecorderCallback bRTCMediaRecorderCallback6 = this.mCallback;
                    if (bRTCMediaRecorderCallback6 != null) {
                        bRTCMediaRecorderCallback6.onRecordStateChanged(-1, -101);
                    }
                    return -101;
                }
            }
            if (bRTCMediaRecorderParams.audioOnly()) {
                try {
                    this.mFosAccAudio = new FileOutputStream(bRTCMediaRecorderParams.storagePath);
                } catch (FileNotFoundException e3) {
                    e3.printStackTrace();
                    BRTCMediaRecorderCallback bRTCMediaRecorderCallback7 = this.mCallback;
                    if (bRTCMediaRecorderCallback7 != null) {
                        bRTCMediaRecorderCallback7.onRecordStateChanged(-1, -101);
                    }
                    return -101;
                }
            }
            if (bRTCMediaRecorderParams.hasAudio()) {
                if (this.mAudioRecorder == null) {
                    this.mAudioRecorder = new RtcAudioRecorder();
                    this.mAudioRecorder.setMediaDataCallback(this);
                }
                this.mAudioRecorder.setMediaRecorderCallback(this.mCallback);
                baiduRtcRoomImp.registerAudioRecord(this.mAudioRecorder);
                if (this.mAudioRecorder.startRecording(bRTCMediaRecorderParams) != 0) {
                    BRTCMediaRecorderCallback bRTCMediaRecorderCallback8 = this.mCallback;
                    if (bRTCMediaRecorderCallback8 != null) {
                        bRTCMediaRecorderCallback8.onRecordStateChanged(-1, -103);
                    }
                    return -103;
                } else if (bRTCMediaRecorderParams.audioOnly()) {
                    this.mAudioRecorder.setMuxerState(true);
                }
            }
            if (bRTCMediaRecorderParams.hasVideo()) {
                if (this.mVideoRecorder == null) {
                    this.mVideoRecorder = new RtcVideoRecorder(this.mSharedContext);
                    this.mVideoRecorder.setMediaDataCallback(this);
                }
                this.mVideoRecorder.setMediaRecorderCallback(this.mCallback);
                baiduRtcRoomImp.registerLocalSink(this.mVideoRecorder);
                if (this.mVideoRecorder.startRecording(bRTCMediaRecorderParams) != 0) {
                    BRTCMediaRecorderCallback bRTCMediaRecorderCallback9 = this.mCallback;
                    if (bRTCMediaRecorderCallback9 != null) {
                        bRTCMediaRecorderCallback9.onRecordStateChanged(-1, -103);
                    }
                    return -103;
                }
            }
            this.mCurrentState = 1;
            if (bRTCMediaRecorderParams.audioOnly() && (bRTCMediaRecorderCallback = this.mCallback) != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(1, 0);
            }
            return 0;
        }
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public int stopRecording() {
        StringBuilder sb = new StringBuilder();
        sb.append(" stopRecording --- ");
        sb.append(this.mCurrentState == 1);
        log(sb.toString());
        if (this.mCurrentState != 1) {
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = this.mCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(-1, -103);
            }
            return -103;
        }
        BaiduRtcRoomImp baiduRtcRoomImp = this.mRtcRoomImpRef.get();
        if (baiduRtcRoomImp == null) {
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback2 = this.mCallback;
            if (bRTCMediaRecorderCallback2 != null) {
                bRTCMediaRecorderCallback2.onRecordStateChanged(-1, -104);
            }
            return -104;
        }
        RtcAudioRecorder rtcAudioRecorder = this.mAudioRecorder;
        if (rtcAudioRecorder != null) {
            baiduRtcRoomImp.unregisterAudioRecord(rtcAudioRecorder);
            this.mAudioRecorder.stopRecording();
        }
        RtcVideoRecorder rtcVideoRecorder = this.mVideoRecorder;
        if (rtcVideoRecorder != null) {
            baiduRtcRoomImp.unregisterLocalSink(rtcVideoRecorder);
            this.mVideoRecorder.stopRecording();
        }
        this.mCurrentState = 0;
        return 0;
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public void release() {
        log(" release ");
        if (this.mCurrentState == 1) {
            stopRecording();
            BRTCMediaRecorderCallback bRTCMediaRecorderCallback = this.mCallback;
            if (bRTCMediaRecorderCallback != null) {
                bRTCMediaRecorderCallback.onRecordStateChanged(-1, -106);
            }
        }
        RtcAudioRecorder rtcAudioRecorder = this.mAudioRecorder;
        if (rtcAudioRecorder != null) {
            rtcAudioRecorder.release();
            this.mAudioRecorder = null;
        }
        RtcVideoRecorder rtcVideoRecorder = this.mVideoRecorder;
        if (rtcVideoRecorder != null) {
            rtcVideoRecorder.release();
            this.mVideoRecorder = null;
        }
        if (this.mCurrentState != 1) {
            this.mWorkThread.quitSafely();
        }
        this.mCurrentState = 2;
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public boolean isReleased() {
        return this.mCurrentState == 2;
    }

    @Override // com.baidu.rtc.recorder.IRtcMediaRecorder
    public void setMediaRecorderCallback(BRTCMediaRecorderCallback bRTCMediaRecorderCallback) {
        this.mCallback = bRTCMediaRecorderCallback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void log(String str) {
        Log.d(TAG, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logFrequently(String str) {
        Log.d(TAG, str);
    }
}
