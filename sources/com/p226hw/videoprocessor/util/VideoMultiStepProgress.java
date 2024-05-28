package com.p226hw.videoprocessor.util;

/* renamed from: com.hw.videoprocessor.util.VideoMultiStepProgress */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoMultiStepProgress implements VideoProgressListener {
    private int mCurrentStep;
    private VideoProgressListener mListener;
    private float mStepBaseProgress;
    private float[] mStepPercentes;

    public VideoMultiStepProgress(float[] fArr, VideoProgressListener videoProgressListener) {
        this.mStepPercentes = fArr;
        this.mListener = videoProgressListener;
    }

    public void setCurrentStep(int i) {
        this.mCurrentStep = i;
        this.mStepBaseProgress = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            this.mStepBaseProgress += this.mStepPercentes[i2];
        }
    }

    @Override // com.p226hw.videoprocessor.util.VideoProgressListener
    public void onProgress(float f) {
        VideoProgressListener videoProgressListener = this.mListener;
        if (videoProgressListener != null) {
            videoProgressListener.onProgress((f * this.mStepPercentes[this.mCurrentStep]) + this.mStepBaseProgress);
        }
    }

    public void setListener(VideoProgressListener videoProgressListener) {
        this.mListener = videoProgressListener;
    }
}
