package com.baidu.p120ar.arplay.component;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import com.baidu.p120ar.arplay.component.bean.MediaInfo;
import com.baidu.p120ar.arplay.component.bean.VideoBean;
import com.baidu.p120ar.arplay.core.engine.ARPEngine;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.util.INetChangeObserver;
import com.baidu.p120ar.arplay.util.LogUtil;
import com.baidu.p120ar.arplay.util.NetStateReceiver;
import com.baidu.p120ar.arplay.util.NetUtils;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.VideoPlayerManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VideoPlayerManager {
    public static final String TAG = "VideoPlayerManager";
    private static VideoPlayerManager mInstance;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private int mAudioIndex = 0;
    private boolean mHasRun = false;
    private ConcurrentHashMap<String, VideoMaterialWrapper> mVideoMaterialWrapperList = new ConcurrentHashMap<>();
    private INetChangeObserver mNetChangerObserver = new INetChangeObserver() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.1
        @Override // com.baidu.p120ar.arplay.util.INetChangeObserver
        public void onNetConnected(NetUtils.NetType netType) {
            VideoPlayerManager.this.resumeAllPlay(null, true);
        }

        @Override // com.baidu.p120ar.arplay.util.INetChangeObserver
        public void onNetDisConnect() {
            VideoPlayerManager.this.pauseAllPlay(null, true);
        }
    };
    private Map<String, Integer> mVideoPlayerCountMap = new Hashtable();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.VideoPlayerManager$MediaCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface MediaCallback {
        void onException(Exception exc);

        void onResult(boolean z);
    }

    private void removePlayerInMsg(Message message) {
    }

    private VideoPlayerManager() {
        NetStateReceiver.registerObserver(this.mNetChangerObserver);
    }

    public static synchronized VideoPlayerManager getInstance() {
        VideoPlayerManager videoPlayerManager;
        synchronized (VideoPlayerManager.class) {
            if (mInstance == null) {
                mInstance = new VideoPlayerManager();
            }
            videoPlayerManager = mInstance;
        }
        return videoPlayerManager;
    }

    public void startPlay(MediaCallback mediaCallback, String str, String str2, MediaPlayer.OnCompletionListener onCompletionListener, int i, int i2, String str3, long j) {
        startPlayInner(mediaCallback, str, str2, i, onCompletionListener, false, str3, j);
    }

    public void pausePlay(MediaCallback mediaCallback, String str, boolean z) {
        MediaPlayer mediaPlayerInMsg = getMediaPlayerInMsg(str);
        if (mediaPlayerInMsg != null) {
            try {
                if (mediaPlayerInMsg.isPlaying()) {
                    VideoMaterialWrapper videoMaterialWrapper = getVideoMaterialWrapper(str);
                    if (videoMaterialWrapper != null) {
                        videoMaterialWrapper.mInfo.mType = "STATUS";
                        videoMaterialWrapper.mInfo.mPlayStatus = "paused";
                        videoMaterialWrapper.mTargetToResume = z;
                        onVideoCallBack(videoMaterialWrapper);
                    }
                    LogUtil.m20423e(TAG, "mMediaPlayer pause");
                    mediaPlayerInMsg.pause();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopPlay(MediaCallback mediaCallback, String str) {
        VideoMaterialWrapper videoMaterialWrapper = getVideoMaterialWrapper(str);
        if (videoMaterialWrapper != null) {
            videoMaterialWrapper.mInfo.mType = "STATUS";
            videoMaterialWrapper.mInfo.mPlayStatus = "unstarted";
            onVideoCallBack(videoMaterialWrapper);
        }
        MediaPlayer mediaPlayerInMsg = getMediaPlayerInMsg(str);
        if (mediaPlayerInMsg != null) {
            LogUtil.m20423e(TAG, "mMediaPlayer stopPlay");
            AudioPlayerManager.releasePlayer(mediaPlayerInMsg);
            removeMediaPlayerInMsg(str);
        }
    }

    public void resumePlay(MediaCallback mediaCallback, String str) {
        if (ARPEngine.getInstance().isPaused()) {
            return;
        }
        MediaPlayer mediaPlayerInMsg = getMediaPlayerInMsg(str);
        VideoMaterialWrapper videoMaterialWrapper = getVideoMaterialWrapper(str);
        if (videoMaterialWrapper == null) {
            return;
        }
        if ((videoMaterialWrapper.mInfo.mPlayStatus == "paused" || videoMaterialWrapper.mInfo.mPlayStatus == "prepared") && mediaPlayerInMsg != null) {
            LogUtil.m20423e(TAG, "mMediaPlayer start");
            scheduleTimerTask();
            mediaPlayerInMsg.start();
            videoMaterialWrapper.mInfo.mType = "STATUS";
            videoMaterialWrapper.mInfo.mPlayStatus = "playing";
            onVideoCallBack(videoMaterialWrapper);
        }
    }

    private MediaPlayer getMediaPlayerInMsg(String str) {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap == null || concurrentHashMap.get(str) == null) {
            return null;
        }
        return this.mVideoMaterialWrapperList.get(str).mMediaPlayer;
    }

    public VideoMaterialWrapper getVideoMaterialWrapper(String str) {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap != null) {
            return concurrentHashMap.get(str);
        }
        return null;
    }

    public int getVideoMaterialWrapperTextureId(String str) {
        VideoMaterialWrapper videoMaterialWrapper = getVideoMaterialWrapper(str);
        if (videoMaterialWrapper != null) {
            return videoMaterialWrapper.textureId;
        }
        return 0;
    }

    private void removeMediaPlayerInMsg(String str) {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap == null || concurrentHashMap.get(str) == null) {
            return;
        }
        this.mVideoMaterialWrapperList.remove(str);
    }

    private void startPlayInner(MediaCallback mediaCallback, String str, String str2, int i, MediaPlayer.OnCompletionListener onCompletionListener, boolean z, String str3, final long j) {
        if (this.mVideoMaterialWrapperList.containsKey(str)) {
            VideoMaterialWrapper videoMaterialWrapper = this.mVideoMaterialWrapperList.get(str);
            if (videoMaterialWrapper != null) {
                try {
                    if (videoMaterialWrapper.mMediaPlayer != null) {
                        videoMaterialWrapper.mMediaPlayer.reset();
                        videoMaterialWrapper.mMediaPlayer.setDataSource(str2);
                        videoMaterialWrapper.mMediaPlayer.setLooping(z);
                        videoMaterialWrapper.mMediaPlayer.setOnCompletionListener(onCompletionListener);
                        videoMaterialWrapper.mMediaPlayer.prepareAsync();
                        videoMaterialWrapper.mVideoPath = str2;
                        return;
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        try {
            final VideoMaterialWrapper videoMaterialWrapper2 = new VideoMaterialWrapper();
            videoMaterialWrapper2.mVideoPath = str2;
            videoMaterialWrapper2.mMediaPlayer = new MediaPlayer();
            videoMaterialWrapper2.mMediaPlayer.setDataSource(str2);
            videoMaterialWrapper2.textureId = i;
            videoMaterialWrapper2.mSurfaceTexture = new SurfaceTexture(i);
            videoMaterialWrapper2.mInfo.mId = Long.valueOf(str).longValue();
            videoMaterialWrapper2.mInfo.mTarget = str3;
            videoMaterialWrapper2.mMediaPlayer.setSurface(new Surface(videoMaterialWrapper2.mSurfaceTexture));
            videoMaterialWrapper2.mMediaPlayer.setOnCompletionListener(onCompletionListener);
            videoMaterialWrapper2.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.2
                @Override // android.media.MediaPlayer.OnErrorListener
                public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                    videoMaterialWrapper2.mInfo.mType = "ERROR";
                    videoMaterialWrapper2.mInfo.mErrorCode = i2;
                    VideoPlayerManager.onVideoCallBack(videoMaterialWrapper2);
                    return true;
                }
            });
            videoMaterialWrapper2.mMediaPlayer.setLooping(z);
            videoMaterialWrapper2.mMediaPlayer.prepareAsync();
            LogUtil.m20423e(TAG, "wrapper.mMediaPlayer.prepareAsync()");
            videoMaterialWrapper2.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.3
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    LogUtil.m20423e(VideoPlayerManager.TAG, "mMediaPlayer onPrepared");
                    videoMaterialWrapper2.mInfo.mType = "STATUS";
                    videoMaterialWrapper2.mInfo.mPlayStatus = "prepared";
                    VideoPlayerManager.onVideoCallBack(videoMaterialWrapper2);
                    if (ARPEngine.getInstance().isEngineCanAccess() && !ARPEngine.getInstance().isPaused()) {
                        VideoPlayerManager.this.scheduleTimerTask();
                        try {
                            if (videoMaterialWrapper2.mMediaPlayer.getDuration() >= 0) {
                                if (videoMaterialWrapper2.mMediaPlayer.getDuration() > j && j >= 0) {
                                    videoMaterialWrapper2.mMediaPlayer.seekTo((int) j);
                                }
                                videoMaterialWrapper2.mMediaPlayer.seekTo(0);
                            }
                            LogUtil.m20423e(VideoPlayerManager.TAG, "mMediaPlayer start");
                            videoMaterialWrapper2.mMediaPlayer.start();
                            videoMaterialWrapper2.mInfo.mPlayStatus = "playing";
                        } catch (Exception e2) {
                            e2.fillInStackTrace();
                        }
                    }
                }
            });
            videoMaterialWrapper2.mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.4
                @Override // android.media.MediaPlayer.OnBufferingUpdateListener
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                    videoMaterialWrapper2.mInfo.mType = "INFO";
                    videoMaterialWrapper2.mInfo.mBufferProgress = i2;
                    VideoPlayerManager.onVideoCallBack(videoMaterialWrapper2);
                }
            });
            videoMaterialWrapper2.mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.5
                @Override // android.media.MediaPlayer.OnInfoListener
                public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                    videoMaterialWrapper2.mInfo.mType = "INFO";
                    switch (i2) {
                        case 701:
                            videoMaterialWrapper2.mInfo.mBufferStatus = "buffer_start";
                            VideoPlayerManager.onVideoCallBack(videoMaterialWrapper2);
                            return false;
                        case 702:
                            videoMaterialWrapper2.mInfo.mBufferStatus = "buffer_end";
                            VideoPlayerManager.onVideoCallBack(videoMaterialWrapper2);
                            return false;
                        default:
                            return false;
                    }
                }
            });
            if (this.mVideoMaterialWrapperList == null || str == null) {
                return;
            }
            this.mVideoMaterialWrapperList.put(str, videoMaterialWrapper2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void scheduleTimerTask() {
        if (this.mTimer == null) {
            this.mTimer = new Timer();
            this.mTimerTask = new TimerTask() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.6
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    VideoMaterialWrapper videoMaterialWrapper;
                    if (VideoPlayerManager.this.mVideoMaterialWrapperList != null) {
                        for (Map.Entry entry : VideoPlayerManager.this.mVideoMaterialWrapperList.entrySet()) {
                            if (entry != null && (videoMaterialWrapper = (VideoMaterialWrapper) entry.getValue()) != null && videoMaterialWrapper.mInfo != null && videoMaterialWrapper.mInfo.mPlayStatus == "playing") {
                                VideoPlayerManager.onVideoCallBack((VideoMaterialWrapper) entry.getValue());
                            }
                        }
                    }
                }
            };
            this.mTimer.scheduleAtFixedRate(this.mTimerTask, 0L, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onVideoCallBack(VideoMaterialWrapper videoMaterialWrapper) {
        if (videoMaterialWrapper == null) {
            return;
        }
        filterPlayProgress(videoMaterialWrapper);
        updateVideoPlayInfo(videoMaterialWrapper.mInfo);
        MediaInfo mediaInfo = videoMaterialWrapper.mInfo;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 5210);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("action_id", String.valueOf(mediaInfo.mId));
        hashMap2.put("platform", "android");
        hashMap2.put("type", mediaInfo.mType);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("error_code", Integer.valueOf(mediaInfo.mErrorCode));
        hashMap3.put("buffer_status", mediaInfo.mBufferStatus);
        hashMap3.put("buffer_progress", Integer.valueOf(mediaInfo.mBufferProgress));
        hashMap3.put("play_status", mediaInfo.mPlayStatus);
        hashMap3.put("play_progress", Integer.valueOf((int) (mediaInfo.mPlayingProgress * 100.0f)));
        hashMap2.put("data", hashMap3);
        hashMap.put("msg_data", hashMap2);
        ARPMessage.getInstance().sendMessage(1902, hashMap);
    }

    private static void updateVideoPlayInfo(MediaInfo mediaInfo) {
        if (mediaInfo == null) {
            return;
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", String.valueOf(mediaInfo.mId));
        hashMap.put("target", mediaInfo.mTarget);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("play_status", mediaInfo.mPlayStatus);
        hashMap2.put("buffer_status", mediaInfo.mBufferStatus);
        hashMap2.put("duration", String.valueOf(mediaInfo.mDuration));
        hashMap2.put("buffer_progress", String.valueOf(mediaInfo.mBufferProgress));
        hashMap2.put("play_progress", String.valueOf((int) (mediaInfo.mPlayingProgress * 100.0f)));
        hashMap.put("msg_data", hashMap2);
        ARPMessage.getInstance().sendMessage(1031, hashMap);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0041 -> B:29:0x0044). Please submit an issue!!! */
    private static void filterPlayProgress(VideoMaterialWrapper videoMaterialWrapper) {
        if (videoMaterialWrapper == null || videoMaterialWrapper.mMediaPlayer == null) {
            return;
        }
        MediaInfo mediaInfo = videoMaterialWrapper.mInfo;
        if (mediaInfo.mPlayStatus == "playing" || mediaInfo.mPlayStatus == "paused") {
            try {
                mediaInfo.mDuration = videoMaterialWrapper.mMediaPlayer.getDuration();
                if (mediaInfo.mDuration <= 0) {
                    mediaInfo.mPlayingProgress = 0.0f;
                } else {
                    mediaInfo.mPlayingProgress = (videoMaterialWrapper.mMediaPlayer.getCurrentPosition() * 1.0f) / mediaInfo.mDuration;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (mediaInfo.mPlayStatus == "finished") {
            mediaInfo.mPlayingProgress = 1.0f;
        }
        if (mediaInfo.mPlayingProgress > 1.0f) {
            mediaInfo.mPlayingProgress = 1.0f;
        }
        if (mediaInfo.mPlayingProgress < 0.0f) {
            mediaInfo.mPlayingProgress = 0.0f;
        }
    }

    public SurfaceTexture getVideoSurfaceTexture(String str) {
        VideoMaterialWrapper videoMaterialWrapper;
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap == null || (videoMaterialWrapper = concurrentHashMap.get(str)) == null) {
            return null;
        }
        return videoMaterialWrapper.mSurfaceTexture;
    }

    public void resetMediaSurface(String str, int i) {
        VideoMaterialWrapper videoMaterialWrapper = this.mVideoMaterialWrapperList.get(str);
        if (videoMaterialWrapper != null) {
            videoMaterialWrapper.mSurfaceTexture = new SurfaceTexture(i);
            try {
                videoMaterialWrapper.mMediaPlayer.setSurface(new Surface(videoMaterialWrapper.mSurfaceTexture));
            } catch (Exception unused) {
                Log.i("VideoPlayerManager", "MediaPlayer setSurface failed.");
            }
            videoMaterialWrapper.textureId = i;
        }
    }

    public void pauseAllPlay(MediaCallback mediaCallback, boolean z) {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap != null) {
            for (Map.Entry<String, VideoMaterialWrapper> entry : concurrentHashMap.entrySet()) {
                pausePlay(mediaCallback, entry.getKey(), z);
            }
        }
    }

    public void stopAllPlay(MediaCallback mediaCallback) {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap != null) {
            for (Map.Entry<String, VideoMaterialWrapper> entry : concurrentHashMap.entrySet()) {
                stopPlay(mediaCallback, entry.getKey());
            }
        }
    }

    public void resumeAllPlay(MediaCallback mediaCallback, boolean z) {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap != null) {
            for (Map.Entry<String, VideoMaterialWrapper> entry : concurrentHashMap.entrySet()) {
                if (z) {
                    if (entry != null && entry.getValue() != null && entry.getValue().mTargetToResume) {
                        resumePlay(mediaCallback, entry.getKey());
                    }
                } else {
                    resumePlay(mediaCallback, entry.getKey());
                }
            }
        }
    }

    public void releaseMediaPlayer() {
        ConcurrentHashMap<String, VideoMaterialWrapper> concurrentHashMap = this.mVideoMaterialWrapperList;
        if (concurrentHashMap != null) {
            for (Map.Entry<String, VideoMaterialWrapper> entry : concurrentHashMap.entrySet()) {
                if (entry != null) {
                    stopPlay(null, entry.getKey());
                    entry.getValue().mSurfaceTexture = null;
                    entry.getValue().mInfo.mType = "STATUS";
                    entry.getValue().mInfo.mPlayStatus = "unstarted";
                    entry.getValue().mTargetToResume = false;
                    onVideoCallBack(entry.getValue());
                    MediaPlayer mediaPlayer = entry.getValue().mMediaPlayer;
                    if (mediaPlayer != null) {
                        try {
                            LogUtil.m20423e(TAG, "releaseMediaPlayer");
                            mediaPlayer.release();
                        } catch (Exception unused) {
                            System.out.println("player release Exception");
                        }
                    }
                }
            }
            this.mVideoMaterialWrapperList.clear();
        }
    }

    public synchronized void release() {
        if (this.mTimer != null) {
            this.mTimer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
            if (this.mTimerTask != null) {
                this.mTimerTask.cancel();
                this.mTimerTask = null;
            }
        }
        NetStateReceiver.removeRegisterObserver(this.mNetChangerObserver);
        releaseMediaPlayer();
        releaseInstance();
    }

    private static void releaseInstance() {
        mInstance = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.VideoPlayerManager$VideoMaterialWrapper */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class VideoMaterialWrapper {
        MediaPlayer mMediaPlayer;
        SurfaceTexture mSurfaceTexture;
        String mVideoPath;
        int textureId;
        boolean mTargetToResume = false;
        MediaInfo mInfo = new MediaInfo();

        VideoMaterialWrapper() {
        }
    }

    public void openVideo(final VideoBean videoBean, final HashMap<String, Object> hashMap) {
        if (videoBean.getLoop() > 1) {
            this.mVideoPlayerCountMap.put(videoBean.getId(), Integer.valueOf(videoBean.getLoop()));
        }
        sendResponseMessage(1022, hashMap);
        startPlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.7
            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onException(Exception exc) {
                VideoPlayerManager.this.sendResponseMessage(1029, hashMap);
            }
        }, videoBean.getId(), videoBean.getUrl(), new MediaPlayer.OnCompletionListener() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.8
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoBean videoBean2;
                if (VideoPlayerManager.this.mVideoPlayerCountMap == null || (videoBean2 = videoBean) == null) {
                    return;
                }
                VideoMaterialWrapper videoMaterialWrapper = VideoPlayerManager.this.getVideoMaterialWrapper(videoBean2.getId());
                if (videoMaterialWrapper != null) {
                    videoMaterialWrapper.mInfo.mType = "STATUS";
                    videoMaterialWrapper.mInfo.mPlayStatus = "finished";
                    VideoPlayerManager.onVideoCallBack(videoMaterialWrapper);
                    if (videoBean.isLoopForever()) {
                        VideoPlayerManager.this.openVideo(videoBean, hashMap);
                        return;
                    }
                }
                if (VideoPlayerManager.this.mVideoPlayerCountMap.size() > 0) {
                    VideoPlayerManager videoPlayerManager = VideoPlayerManager.this;
                    int playCount = videoPlayerManager.getPlayCount(videoPlayerManager.mVideoPlayerCountMap, videoBean.getId());
                    if (playCount > 1) {
                        int i = playCount - 1;
                        VideoPlayerManager.this.mVideoPlayerCountMap.put(videoBean.getId(), Integer.valueOf(i));
                        videoBean.setLoop(i);
                        VideoPlayerManager.this.openVideo(videoBean, hashMap);
                        return;
                    }
                    VideoPlayerManager.this.sendResponseMessage(1030, hashMap);
                    return;
                }
                VideoPlayerManager.this.sendResponseMessage(1030, hashMap);
            }
        }, videoBean.getTextureid(), videoBean.getLoop(), videoBean.getTargetName(), videoBean.getFromTime());
    }

    public void pauseVideo(VideoBean videoBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1024, hashMap);
        pausePlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.9
            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onException(Exception exc) {
                VideoPlayerManager.this.sendResponseMessage(1029, hashMap);
            }
        }, videoBean.getId(), false);
    }

    public void resumeVideo(VideoBean videoBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1026, hashMap);
        LogUtil.m20423e(TAG, "mMediaPlayer resumeVideo");
        resumePlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.10
            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onException(Exception exc) {
                VideoPlayerManager.this.sendResponseMessage(1029, hashMap);
            }
        }, videoBean.getId());
    }

    public void stopVideo(VideoBean videoBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1028, hashMap);
        stopPlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.VideoPlayerManager.11
            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.VideoPlayerManager.MediaCallback
            public void onException(Exception exc) {
                VideoPlayerManager.this.sendResponseMessage(1010, hashMap);
            }
        }, videoBean.getId());
    }

    public void sendResponseMessage(int i, HashMap<String, Object> hashMap) {
        ARPMessage.getInstance().sendMessage(i, hashMap);
    }

    public int getPlayCount(Map<String, Integer> map, String str) {
        Integer num = map.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }
}
