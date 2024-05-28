package com.dueeeke.videoplayer.listener;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface MediaPlayerControl {
    int getBufferPercentage();

    int getCurrentPosition();

    int getDuration();

    String getTitle();

    boolean isFullScreen();

    boolean isMute();

    boolean isPlaying();

    void pause();

    void seekTo(int i);

    void setLock(boolean z);

    void setMute();

    void setScreenScale(int i);

    void start();

    void startFullScreen();

    void stopFullScreen();
}
