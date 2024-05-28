package com.dueeeke.videoplayer.player;

import java.lang.ref.WeakReference;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class VideoViewManager {
    private static VideoViewManager sInstance;
    private WeakReference<IjkVideoView> mPlayer;

    private VideoViewManager() {
    }

    public static VideoViewManager instance() {
        if (sInstance == null) {
            synchronized (VideoViewManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoViewManager();
                }
            }
        }
        return sInstance;
    }

    public void setCurrentVideoPlayer(IjkVideoView ijkVideoView) {
        this.mPlayer = new WeakReference<>(ijkVideoView);
    }

    public IjkVideoView getCurrentVideoPlayer() {
        WeakReference<IjkVideoView> weakReference = this.mPlayer;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void releaseVideoPlayer() {
        WeakReference<IjkVideoView> weakReference = this.mPlayer;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.mPlayer.get().release();
        this.mPlayer = null;
    }

    public boolean onBackPressed() {
        WeakReference<IjkVideoView> weakReference = this.mPlayer;
        return (weakReference == null || weakReference.get() == null || !this.mPlayer.get().onBackPressed()) ? false : true;
    }
}
