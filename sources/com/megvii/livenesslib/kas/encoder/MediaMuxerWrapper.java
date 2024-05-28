package com.megvii.livenesslib.kas.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import com.megvii.livenesslib.util.SDCardUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class MediaMuxerWrapper {
    private static final boolean DEBUG = false;
    private static final String DIR_NAME = "AVRecSample";
    private static final String TAG = "MediaMuxerWrapper";
    private static final SimpleDateFormat mDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.US);
    private MediaEncoder mAudioEncoder;
    private int mEncoderCount;
    private boolean mIsStarted;
    private final MediaMuxer mMediaMuxer;
    private String mOutputPath;
    private int mStatredCount;
    private MediaEncoder mVideoEncoder;

    public MediaMuxerWrapper(String str) throws IOException {
        this.mOutputPath = "";
        try {
            this.mOutputPath = getCaptureFile(Environment.DIRECTORY_MOVIES, TextUtils.isEmpty(str) ? ".mp4" : str).toString();
            Log.w("FIL_MESSAGE", "视频录制保存的地址:video file = " + this.mOutputPath);
            this.mMediaMuxer = new MediaMuxer(this.mOutputPath, 0);
            this.mStatredCount = 0;
            this.mEncoderCount = 0;
            this.mIsStarted = false;
        } catch (NullPointerException unused) {
            throw new RuntimeException("This app has no permission of writing external storage");
        }
    }

    public String getOutputPath() {
        return this.mOutputPath;
    }

    public void prepare() throws IOException {
        MediaEncoder mediaEncoder = this.mVideoEncoder;
        if (mediaEncoder != null) {
            mediaEncoder.prepare();
        }
        MediaEncoder mediaEncoder2 = this.mAudioEncoder;
        if (mediaEncoder2 != null) {
            mediaEncoder2.prepare();
        }
    }

    public void startRecording() {
        MediaEncoder mediaEncoder = this.mVideoEncoder;
        if (mediaEncoder != null) {
            mediaEncoder.startRecording();
        }
        MediaEncoder mediaEncoder2 = this.mAudioEncoder;
        if (mediaEncoder2 != null) {
            mediaEncoder2.startRecording();
        }
    }

    public void stopRecording() {
        MediaEncoder mediaEncoder = this.mVideoEncoder;
        if (mediaEncoder != null) {
            mediaEncoder.stopRecording();
        }
        this.mVideoEncoder = null;
        MediaEncoder mediaEncoder2 = this.mAudioEncoder;
        if (mediaEncoder2 != null) {
            mediaEncoder2.stopRecording();
        }
        this.mAudioEncoder = null;
    }

    public synchronized boolean isStarted() {
        return this.mIsStarted;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addEncoder(MediaEncoder mediaEncoder) {
        if (mediaEncoder instanceof MediaVideoEncoder) {
            if (this.mVideoEncoder != null) {
                throw new IllegalArgumentException("Video encoder already added.");
            }
            this.mVideoEncoder = mediaEncoder;
        } else if (mediaEncoder instanceof MediaAudioEncoder) {
            if (this.mAudioEncoder != null) {
                throw new IllegalArgumentException("Video encoder already added.");
            }
            this.mAudioEncoder = mediaEncoder;
        } else {
            throw new IllegalArgumentException("unsupported encoder");
        }
        this.mEncoderCount = (this.mVideoEncoder != null ? 1 : 0) + (this.mAudioEncoder == null ? 0 : 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean start() {
        this.mStatredCount++;
        if (this.mEncoderCount > 0 && this.mStatredCount == this.mEncoderCount) {
            this.mMediaMuxer.start();
            this.mIsStarted = true;
            notifyAll();
        }
        return this.mIsStarted;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void stop() {
        this.mStatredCount--;
        if (this.mEncoderCount > 0 && this.mStatredCount <= 0) {
            this.mMediaMuxer.stop();
            this.mMediaMuxer.release();
            this.mIsStarted = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized int addTrack(MediaFormat mediaFormat) {
        if (this.mIsStarted) {
            throw new IllegalStateException("muxer already started");
        }
        return this.mMediaMuxer.addTrack(mediaFormat);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void writeSampleData(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (this.mStatredCount > 0) {
            this.mMediaMuxer.writeSampleData(i, byteBuffer, bufferInfo);
        }
    }

    public static final File getCaptureFile(String str, String str2) {
        File file = new File(SDCardUtil.getOwnFileUrl(DIR_NAME));
        Log.d(TAG, "path=" + file.toString());
        file.mkdirs();
        if (file.canWrite()) {
            return new File(file, getDateTimeString() + str2);
        }
        return null;
    }

    private static final String getDateTimeString() {
        return mDateTimeFormat.format(new GregorianCalendar().getTime());
    }
}
