package com.king.zxing;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import java.io.Closeable;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class BeepManager implements MediaPlayer.OnErrorListener, Closeable {
    private static final float BEEP_VOLUME = 0.1f;
    private static final String TAG = "BeepManager";
    private static final long VIBRATE_DURATION = 200;
    private final Activity activity;
    private MediaPlayer mediaPlayer = null;
    private boolean playBeep;
    private boolean vibrate;

    public BeepManager(Activity activity) {
        this.activity = activity;
        updatePrefs();
    }

    public void setVibrate(boolean z) {
        this.vibrate = z;
    }

    public void setPlayBeep(boolean z) {
        this.playBeep = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updatePrefs() {
        shouldBeep(PreferenceManager.getDefaultSharedPreferences(this.activity), this.activity);
        if (this.playBeep && this.mediaPlayer == null) {
            this.activity.setVolumeControlStream(3);
            this.mediaPlayer = buildMediaPlayer(this.activity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void playBeepSoundAndVibrate() {
        if (this.playBeep && this.mediaPlayer != null) {
            this.mediaPlayer.start();
        }
        if (this.vibrate) {
            ((Vibrator) this.activity.getSystemService("vibrator")).vibrate(VIBRATE_DURATION);
        }
    }

    private static boolean shouldBeep(SharedPreferences sharedPreferences, Context context) {
        boolean z = sharedPreferences.getBoolean("preferences_play_beep", false);
        if (!z || ((AudioManager) context.getSystemService("audio")).getRingerMode() == 2) {
            return z;
        }
        return false;
    }

    @TargetApi(19)
    private MediaPlayer buildMediaPlayer(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(C5188R.C5193raw.zxl_beep);
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.setLooping(false);
            mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
            mediaPlayer.prepare();
            if (openRawResourceFd != null) {
                openRawResourceFd.close();
            }
            return mediaPlayer;
        } catch (Exception e) {
            Log.w(TAG, e);
            mediaPlayer.release();
            return null;
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (i == 100) {
            this.activity.finish();
        } else {
            close();
            updatePrefs();
        }
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }
}
