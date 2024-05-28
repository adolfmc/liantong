package com.p226hw.videoprocessor.util;

/* renamed from: com.hw.videoprocessor.util.VideoProgressAve */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoProgressAve {
    private float mAudioProgress;
    private float mEncodeProgress;
    private int mEndTimeMs;
    private VideoProgressListener mListener;
    private Float mSpeed;
    private int mStartTimeMs;

    public VideoProgressAve(VideoProgressListener videoProgressListener) {
        this.mListener = videoProgressListener;
    }

    public void setEncodeTimeStamp(long j) {
        int i;
        if (this.mListener == null) {
            return;
        }
        Float f = this.mSpeed;
        if (f != null) {
            j = ((float) j) * f.floatValue();
        }
        this.mEncodeProgress = ((((float) j) / 1000.0f) - this.mStartTimeMs) / (this.mEndTimeMs - i);
        float f2 = this.mEncodeProgress;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.mEncodeProgress = f2;
        float f3 = this.mEncodeProgress;
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        this.mEncodeProgress = f3;
        this.mListener.onProgress((this.mEncodeProgress + this.mAudioProgress) / 2.0f);
        C5140CL.m13755i("mEncodeProgress:" + this.mEncodeProgress, new Object[0]);
    }

    public void setAudioProgress(float f) {
        this.mAudioProgress = f;
        VideoProgressListener videoProgressListener = this.mListener;
        if (videoProgressListener != null) {
            videoProgressListener.onProgress((this.mEncodeProgress + this.mAudioProgress) / 2.0f);
        }
        C5140CL.m13755i("mAudioProgress:" + this.mAudioProgress, new Object[0]);
    }

    public void setStartTimeMs(int i) {
        this.mStartTimeMs = i;
    }

    public void setEndTimeMs(int i) {
        this.mEndTimeMs = i;
    }

    public void setSpeed(Float f) {
        this.mSpeed = f;
    }
}
