package com.baidu.p120ar.recorder.encoder;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.baidu.p120ar.recorder.utils.FileUtil;
import com.baidu.p120ar.utils.ARLog;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.recorder.encoder.MovieMuxer */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MovieMuxer {
    public static final int ERROR_TRACK_INDEX = -1;
    private static final String TAG = "MovieMuxer";
    private MediaMuxer mMediaMuxer;
    private MuxerCallback mMuxerCallback;
    private volatile boolean mMuxerStarted = false;

    public boolean isMuxerStarted() {
        return this.mMuxerStarted;
    }

    public boolean initMovieMuxer(String str, int i, MuxerCallback muxerCallback) {
        if (!FileUtil.deleteIfExist(str)) {
            FileUtil.mkDirExist(str);
        }
        try {
            this.mMediaMuxer = new MediaMuxer(str, i);
            this.mMuxerCallback = muxerCallback;
            this.mMuxerStarted = false;
            return true;
        } catch (Exception e) {
            ARLog.m20419e(TAG, "initMovieMuxer init error!!!");
            e.printStackTrace();
            return false;
        }
    }

    public synchronized int addMuxerTrack(MediaFormat mediaFormat) {
        try {
            int addTrack = this.mMediaMuxer.addTrack(mediaFormat);
            if (addTrack >= 0) {
                return addTrack;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ARLog.m20419e(TAG, "addMuxerTrack error!!!");
        return -1;
    }

    public synchronized void startMuxer() {
        boolean z = false;
        try {
            this.mMediaMuxer.start();
            this.mMuxerStarted = true;
            z = true;
        } catch (Exception unused) {
            ARLog.m20419e(TAG, "startMuxer error!!!");
        }
        if (this.mMuxerCallback != null) {
            this.mMuxerCallback.onMuxerStart(z);
        }
    }

    public boolean writeSampleData(int i, ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) {
        if (i != -1) {
            try {
                this.mMediaMuxer.writeSampleData(i, byteBuffer, bufferInfo);
                return true;
            } catch (Exception unused) {
                ARLog.m20419e(TAG, "startMuxer error!!!");
                return false;
            }
        }
        return false;
    }

    public synchronized void stopMuxer() {
        boolean z = false;
        try {
            this.mMediaMuxer.stop();
            this.mMuxerStarted = false;
            z = true;
        } catch (Exception unused) {
            ARLog.m20419e(TAG, "stopMuxer error!!!");
        }
        if (this.mMuxerCallback != null) {
            this.mMuxerCallback.onMuxerStop(z);
        }
    }

    public void releaseMuxer() {
        if (this.mMuxerStarted) {
            return;
        }
        this.mMediaMuxer.release();
        this.mMediaMuxer = null;
    }
}
