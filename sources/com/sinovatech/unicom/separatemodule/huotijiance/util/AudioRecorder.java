package com.sinovatech.unicom.separatemodule.huotijiance.util;

import android.media.AudioRecord;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudioRecorder {
    private static final int DEFAULT_CHANNELS = 1;
    private static final int DEFAULT_PCM_DATA_FORMAT = 2;
    private static final int DEFAULT_SAMPLE_RATE = 48000;
    private static final String TAG = "AudioRecorder";
    private AudioRecord mAudioRecord;
    private int mBufferSize;
    private Handler mHandler;
    private AudioRecordCallback mRecordCallback;
    private ExecutorService mExecutor = Executors.newCachedThreadPool();
    private int mSampleRate = 48000;
    private int mPcmFormat = 2;
    private int mChannels = 1;
    private boolean mIsRecording = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface AudioRecordCallback {
        void onRecordSample(byte[] bArr);
    }

    private int getAudioBufferSize(int i, int i2) {
        int i3 = 1024;
        if (i == 12) {
            i3 = 2048;
        }
        switch (i2) {
            case 2:
                return i3 * 2;
            case 3:
                return i3 * 1;
            default:
                return i3;
        }
    }

    public void setSampleRate(int i) {
        this.mSampleRate = i;
    }

    public int getSampleRate() {
        return this.mSampleRate;
    }

    public void setPcmFormat(int i) {
        this.mPcmFormat = i;
    }

    public void setRecordCallback(AudioRecordCallback audioRecordCallback) {
        this.mRecordCallback = audioRecordCallback;
    }

    public void setChannels(int i) {
        this.mChannels = i;
    }

    public int getChannels() {
        return this.mChannels;
    }

    public boolean start() {
        try {
            int i = this.mChannels == 1 ? 16 : 12;
            this.mBufferSize = getAudioBufferSize(i, this.mPcmFormat);
            this.mAudioRecord = new AudioRecord(1, this.mSampleRate, i, this.mPcmFormat, this.mBufferSize);
            if (this.mAudioRecord.getState() != 1) {
                Log.e(TAG, "cannot init AudioRecord");
                return false;
            }
            this.mIsRecording = true;
            this.mExecutor.execute(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.util.AudioRecorder.1
                @Override // java.lang.Runnable
                public void run() {
                    AudioRecorder.this.record();
                }
            });
            this.mHandler = new Handler(Looper.myLooper());
            new Handler();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "init AudioRecord exception: " + e.getLocalizedMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void record() {
        Process.setThreadPriority(-19);
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord == null || audioRecord.getState() != 1) {
            return;
        }
        ByteBuffer allocate = ByteBuffer.allocate(this.mBufferSize);
        this.mAudioRecord.startRecording();
        Log.d(TAG, "AudioRecorder started");
        while (this.mIsRecording) {
            int read = this.mAudioRecord.read(allocate.array(), 0, this.mBufferSize);
            if (read > 0 && this.mRecordCallback != null) {
                final byte[] bArr = new byte[read];
                allocate.position(0);
                allocate.limit(read);
                allocate.get(bArr, 0, read);
                this.mHandler.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.huotijiance.util.AudioRecorder.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AudioRecorder.this.mRecordCallback.onRecordSample(bArr);
                    }
                });
            }
        }
        release();
        Log.d(TAG, "AudioRecorder finished");
    }

    public void stop() {
        this.mIsRecording = false;
    }

    private void release() {
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord != null) {
            audioRecord.stop();
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        }
    }
}
