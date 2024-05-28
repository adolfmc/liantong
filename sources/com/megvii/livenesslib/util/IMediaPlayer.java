package com.megvii.livenesslib.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import com.megvii.kas.livenessdetection.Detector;
import com.megvii.livenesslib.C5351R;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IMediaPlayer {
    private Context mContext;
    public MediaPlayer mMediaPlayer = new MediaPlayer();

    public IMediaPlayer(Context context) {
        this.mContext = context;
    }

    public void close() {
        this.mContext = null;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    public void reset() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }

    public void setOnCompletionListener(final Detector.DetectionType detectionType) {
        this.mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.megvii.livenesslib.util.IMediaPlayer.1
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                IMediaPlayer iMediaPlayer = IMediaPlayer.this;
                iMediaPlayer.doPlay(iMediaPlayer.getSoundRes(detectionType));
                IMediaPlayer.this.mMediaPlayer.setOnCompletionListener(null);
            }
        });
    }

    public void doPlay(int i) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.reset();
        try {
            AssetFileDescriptor openRawResourceFd = this.mContext.getResources().openRawResourceFd(i);
            this.mMediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.megvii.livenesslib.util.IMediaPlayer.2
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    IMediaPlayer.this.mMediaPlayer.start();
                }
            });
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getSoundRes(Detector.DetectionType detectionType) {
        switch (detectionType) {
            case POS_PITCH:
                return C5351R.C5355raw.meglive_pitch_down;
            case POS_YAW_LEFT:
            case POS_YAW_RIGHT:
            case POS_YAW:
                return C5351R.C5355raw.meglive_yaw;
            case MOUTH:
                return C5351R.C5355raw.meglive_mouth_open;
            case BLINK:
                return C5351R.C5355raw.meglive_eye_blink;
            default:
                return -1;
        }
    }
}
