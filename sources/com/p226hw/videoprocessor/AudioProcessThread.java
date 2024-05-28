package com.p226hw.videoprocessor;

import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import com.p226hw.videoprocessor.VideoProcessor;
import com.p226hw.videoprocessor.util.AudioUtil;
import com.p226hw.videoprocessor.util.C5140CL;
import com.p226hw.videoprocessor.util.VideoProgressAve;
import com.p226hw.videoprocessor.util.VideoProgressListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jetbrains.annotations.Nullable;

/* renamed from: com.hw.videoprocessor.AudioProcessThread */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AudioProcessThread extends Thread implements VideoProgressListener {
    private Context mContext;
    private Integer mEndTimeMs;
    private Exception mException;
    private MediaExtractor mExtractor;
    private VideoProcessor.MediaSource mMediaSource;
    private MediaMuxer mMuxer;
    private int mMuxerAudioTrackIndex;
    private CountDownLatch mMuxerStartLatch;
    private VideoProgressAve mProgressAve;
    private Float mSpeed;
    private Integer mStartTimeMs;

    public AudioProcessThread(Context context, VideoProcessor.MediaSource mediaSource, MediaMuxer mediaMuxer, @Nullable Integer num, @Nullable Integer num2, @Nullable Float f, int i, CountDownLatch countDownLatch) {
        super("VideoProcessDecodeThread");
        this.mMediaSource = mediaSource;
        this.mStartTimeMs = num;
        this.mEndTimeMs = num2;
        this.mSpeed = f;
        this.mMuxer = mediaMuxer;
        this.mContext = context;
        this.mMuxerAudioTrackIndex = i;
        this.mExtractor = new MediaExtractor();
        this.mMuxerStartLatch = countDownLatch;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        try {
            try {
                doProcessAudio();
            } catch (Exception e) {
                this.mException = e;
                C5140CL.m13757e(e);
            }
        } finally {
            this.mExtractor.release();
        }
    }

    private void doProcessAudio() throws Exception {
        this.mMediaSource.setDataSource(this.mExtractor);
        int selectTrack = VideoUtil.selectTrack(this.mExtractor, true);
        if (selectTrack >= 0) {
            this.mExtractor.selectTrack(selectTrack);
            MediaFormat trackFormat = this.mExtractor.getTrackFormat(selectTrack);
            String string = trackFormat.containsKey("mime") ? trackFormat.getString("mime") : "audio/mp4a-latm";
            Integer num = this.mStartTimeMs;
            Integer valueOf = num == null ? null : Integer.valueOf(num.intValue() * 1000);
            Integer num2 = this.mEndTimeMs;
            Integer valueOf2 = num2 != null ? Integer.valueOf(num2.intValue() * 1000) : null;
            if (!this.mMuxerStartLatch.await(3L, TimeUnit.SECONDS)) {
                throw new TimeoutException("wait muxerStartLatch timeout!");
            }
            if (this.mSpeed != null || !string.equals("audio/mp4a-latm")) {
                Context context = this.mContext;
                MediaExtractor mediaExtractor = this.mExtractor;
                MediaMuxer mediaMuxer = this.mMuxer;
                int i = this.mMuxerAudioTrackIndex;
                Float f = this.mSpeed;
                AudioUtil.writeAudioTrackDecode(context, mediaExtractor, mediaMuxer, i, valueOf, valueOf2, Float.valueOf(f == null ? 1.0f : f.floatValue()), this);
            } else {
                AudioUtil.writeAudioTrack(this.mExtractor, this.mMuxer, this.mMuxerAudioTrackIndex, valueOf, valueOf2, this);
            }
        }
        VideoProgressAve videoProgressAve = this.mProgressAve;
        if (videoProgressAve != null) {
            videoProgressAve.setAudioProgress(1.0f);
        }
        C5140CL.m13755i("Audio Process Done!", new Object[0]);
    }

    public Exception getException() {
        return this.mException;
    }

    public void setProgressAve(VideoProgressAve videoProgressAve) {
        this.mProgressAve = videoProgressAve;
    }

    @Override // com.p226hw.videoprocessor.util.VideoProgressListener
    public void onProgress(float f) {
        VideoProgressAve videoProgressAve = this.mProgressAve;
        if (videoProgressAve != null) {
            videoProgressAve.setAudioProgress(f);
        }
    }
}
