package com.mabeijianxi.smallvideorecord2;

import android.media.AudioRecord;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AudioRecorder extends Thread {
    private IMediaRecorder mMediaRecorder;
    private AudioRecord mAudioRecord = null;
    private int mSampleRate = 44100;

    public AudioRecorder(IMediaRecorder iMediaRecorder) {
        this.mMediaRecorder = iMediaRecorder;
    }

    public void setSampleRate(int i) {
        this.mSampleRate = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        int i = this.mSampleRate;
        if (i != 8000 && i != 16000 && i != 22050 && i != 44100) {
            this.mMediaRecorder.onAudioError(1, "sampleRate not support.");
            return;
        }
        int minBufferSize = AudioRecord.getMinBufferSize(this.mSampleRate, 16, 2);
        if (-2 == minBufferSize) {
            this.mMediaRecorder.onAudioError(2, "parameters are not supported by the hardware.");
            return;
        }
        this.mAudioRecord = new AudioRecord(1, this.mSampleRate, 16, 2, minBufferSize);
        AudioRecord audioRecord = this.mAudioRecord;
        if (audioRecord == null) {
            this.mMediaRecorder.onAudioError(3, "new AudioRecord failed.");
            return;
        }
        try {
            audioRecord.startRecording();
            byte[] bArr = new byte[2048];
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    int read = this.mAudioRecord.read(bArr, 0, 2048);
                    if (read > 0) {
                        this.mMediaRecorder.receiveAudioData(bArr, read);
                    }
                } catch (Exception e) {
                    this.mMediaRecorder.onAudioError(0, e.getMessage());
                }
            }
            this.mAudioRecord.release();
            this.mAudioRecord = null;
        } catch (IllegalStateException unused) {
            this.mMediaRecorder.onAudioError(0, "startRecording failed.");
        }
    }
}
