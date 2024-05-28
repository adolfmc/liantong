package com.baidu.rtc.player;

import com.baidu.rtc.RTCVideoView;
import com.baidu.rtc.record.IMediaRecord;
import com.baidu.rtc.snapshot.ISnapShot;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface BRTCPlayer extends IMediaRecord, ISnapShot {
    PlayerState getPlayerState();

    boolean hasAudio();

    boolean hasVideo();

    long initPlayer(BRTCPlayerParameters bRTCPlayerParameters, BRTCPlayerEvents bRTCPlayerEvents);

    void pausePlay();

    void playWithRemoteSdp(String str);

    void prepareAsync();

    void releasePlayer();

    void resumePlay();

    void setEventObserver(BRTCPlayerEvents bRTCPlayerEvents);

    void setMediaServerIp(String str);

    void setPlayWhenReady(boolean z);

    void setScalingType(RTCVideoView.ScalingType scalingType);

    void setStreamUri(String str);

    void setSurfaceView(RTCVideoView rTCVideoView);

    void setVolume(double d);

    void startPlay();

    void stopPlay();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum PlayerState {
        STATE_ERROR(0),
        STATE_IDLE(1),
        STATE_INITIALIZED(2),
        STATE_PREPARING(4),
        STATE_PREPARED(8),
        STATE_STARTED(16),
        STATE_PLAYING(32),
        STATE_PAUSED(64),
        STATE_STOPPED(128);
        
        private int mCode;

        public int getValue() {
            return this.mCode;
        }

        PlayerState(int i) {
            this.mCode = i;
        }
    }
}
