package com.baidu.p120ar.arplay.component;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.baidu.p120ar.arplay.component.bean.AudioBean;
import com.baidu.p120ar.arplay.component.bean.MediaInfo;
import com.baidu.p120ar.arplay.core.engine.ARPEngine;
import com.baidu.p120ar.arplay.core.message.ARPMessage;
import com.baidu.p120ar.arplay.util.LogUtil;
import com.baidu.p120ar.arplay.util.MsgParamsUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.AudioPlayerManager */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AudioPlayerManager {
    private static final String HANDLER_THREAD_NAME = "MediaPlayerThread";
    private static final int HANDLER_THREAD_QUIT_DELAY = 10000;
    private static final int MSG_PAUSE_PLAY = 3006;
    private static final int MSG_RELEASE_PLAYERS = 3008;
    private static final int MSG_RESET_PLAY = 3009;
    private static final int MSG_RESUME_PLAY = 3007;
    private static final int MSG_START_PLAY = 3004;
    private static final int MSG_STOP_PLAY = 3005;
    private static Map<String, Integer> mAudioPlayOperationMap;
    private static MediaCallback mCallback;
    private static AudioPlayerManager mInstance;
    private static ConcurrentHashMap<String, AudioMaterialWrapper> mMediaPlayerMaps;
    private static Timer mTimer;
    private static TimerTask mTimerTask;
    private static HandlerThread sHandlerThread;
    private Map<String, Integer> mAudioPlayCountMap;
    private Handler mHandler;
    private static Handler sUiHandler = new Handler(Looper.getMainLooper());
    public static final String TAG = AudioPlayerManager.class.getSimpleName();
    private static Runnable sQuitRunnable = new Runnable() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.1
        @Override // java.lang.Runnable
        public void run() {
            if (AudioPlayerManager.sHandlerThread != null) {
                AudioPlayerManager.sHandlerThread.quit();
                HandlerThread unused = AudioPlayerManager.sHandlerThread = null;
            }
        }
    };
    private int mAudioIndex = 0;
    private boolean mHasRun = false;
    private Handler.Callback mHandlerCallback = new Handler.Callback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.2
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (ARPEngine.getInstance().isEngineCanAccess()) {
                switch (message.what) {
                    case 3004:
                        AudioPlayerManager.this.startPlayInMsg(message);
                        break;
                    case 3005:
                        AudioPlayerManager.this.stopPlayInMsg(message);
                        break;
                    case 3006:
                        AudioPlayerManager.this.pausePlayInMsg(message);
                        break;
                    case 3007:
                        AudioPlayerManager.this.resumePlayInMsg(message);
                        break;
                    case 3008:
                        AudioPlayerManager.this.releasePlayersInMsg(message);
                        break;
                    case 3009:
                        AudioPlayerManager.this.resetPlayInMsg(message);
                        break;
                }
                return false;
            }
            return false;
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.AudioPlayerManager$MediaCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface MediaCallback {
        void onException(Exception exc);

        void onResult(boolean z);
    }

    public void pauseAll() {
        ConcurrentHashMap<String, AudioMaterialWrapper> concurrentHashMap = mMediaPlayerMaps;
        if (concurrentHashMap != null) {
            for (Map.Entry<String, AudioMaterialWrapper> entry : concurrentHashMap.entrySet()) {
                if (entry != null && entry.getValue() != null && entry.getValue().mMediaPlayer != null) {
                    entry.getValue().mMediaPlayer.pause();
                }
            }
        }
    }

    public void resumeAll() {
        ConcurrentHashMap<String, AudioMaterialWrapper> concurrentHashMap = mMediaPlayerMaps;
        if (concurrentHashMap != null) {
            for (Map.Entry<String, AudioMaterialWrapper> entry : concurrentHashMap.entrySet()) {
                if (entry != null && entry.getValue() != null && entry.getValue().mMediaPlayer != null) {
                    entry.getValue().mMediaPlayer.start();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.AudioPlayerManager$UiThreadMediaCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class UiThreadMediaCallback implements MediaCallback {
        UiThreadMediaCallback(MediaCallback mediaCallback) {
            MediaCallback unused = AudioPlayerManager.mCallback = mediaCallback;
        }

        @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
        public void onResult(final boolean z) {
            if (AudioPlayerManager.mCallback == null) {
                return;
            }
            AudioPlayerManager.sUiHandler.post(new Runnable() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.UiThreadMediaCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AudioPlayerManager.mCallback != null) {
                        AudioPlayerManager.mCallback.onResult(z);
                    }
                }
            });
        }

        @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
        public void onException(final Exception exc) {
            if (AudioPlayerManager.mCallback == null) {
                return;
            }
            AudioPlayerManager.sUiHandler.post(new Runnable() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.UiThreadMediaCallback.2
                @Override // java.lang.Runnable
                public void run() {
                    if (AudioPlayerManager.mCallback != null) {
                        AudioPlayerManager.mCallback.onException(exc);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.AudioPlayerManager$AudioMaterialWrapper */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AudioMaterialWrapper {
        String mAudioPath;
        MediaInfo mInfo = new MediaInfo();
        MediaPlayer mMediaPlayer;
        SurfaceTexture mSurfaceTexture;
        int textureId;

        AudioMaterialWrapper() {
        }
    }

    private AudioPlayerManager() {
        synchronized (AudioPlayerManager.class) {
            if (sHandlerThread == null) {
                sHandlerThread = new HandlerThread("MediaPlayerThread");
                sHandlerThread.start();
            } else {
                sUiHandler.removeCallbacks(sQuitRunnable);
            }
        }
        this.mHandler = new Handler(sHandlerThread.getLooper(), this.mHandlerCallback);
        this.mAudioPlayCountMap = new Hashtable();
        mAudioPlayOperationMap = new Hashtable();
    }

    public static synchronized AudioPlayerManager getInstance() {
        AudioPlayerManager audioPlayerManager;
        synchronized (AudioPlayerManager.class) {
            if (mInstance == null) {
                mInstance = new AudioPlayerManager();
            }
            audioPlayerManager = mInstance;
        }
        return audioPlayerManager;
    }

    private AudioMaterialWrapper initMediaPlayerInMsg(MediaPlayer.OnCompletionListener onCompletionListener, MediaPlayer.OnErrorListener onErrorListener) {
        LogUtil.m20423e(TAG, "initMediaPlayerInMsg start()");
        try {
            final AudioMaterialWrapper audioMaterialWrapper = new AudioMaterialWrapper();
            MediaPlayer mediaPlayer = new MediaPlayer();
            audioMaterialWrapper.mMediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            if (onCompletionListener == null) {
                onCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.3
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer2) {
                        LogUtil.m20423e(AudioPlayerManager.TAG, "initMediaPlayerInMsg onCompletion()");
                        if (mediaPlayer2 != null) {
                            mediaPlayer2.release();
                        }
                    }
                };
            }
            mediaPlayer.setOnCompletionListener(onCompletionListener);
            if (onErrorListener == null) {
                onErrorListener = new MediaPlayer.OnErrorListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.4
                    @Override // android.media.MediaPlayer.OnErrorListener
                    public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                        String str = AudioPlayerManager.TAG;
                        LogUtil.m20423e(str, "onError: " + i);
                        audioMaterialWrapper.mInfo.mType = "ERROR";
                        audioMaterialWrapper.mInfo.mErrorCode = i;
                        AudioPlayerManager.onAudioCallBack(audioMaterialWrapper);
                        AudioPlayerManager.this.stopPlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.4.1
                            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
                            public void onResult(boolean z) {
                            }

                            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
                            public void onException(Exception exc) {
                                AudioPlayerManager.this.sendResponseMessage(1010, null);
                            }
                        }, audioMaterialWrapper.mInfo.mActionId);
                        return false;
                    }
                };
            }
            mediaPlayer.setOnErrorListener(onErrorListener);
            return audioMaterialWrapper;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startPlay(MediaCallback mediaCallback, String str, String str2, MediaPlayer.OnCompletionListener onCompletionListener, int i, String str3, long j) {
        Message obtainMessage = this.mHandler.obtainMessage(3004);
        obtainMessage.obj = new Object[]{new UiThreadMediaCallback(mediaCallback), str, str2, onCompletionListener, Integer.valueOf(i), str3, Long.valueOf(j)};
        obtainMessage.sendToTarget();
    }

    public void pausePlay(MediaCallback mediaCallback, String str) {
        Message obtainMessage = this.mHandler.obtainMessage(3006);
        obtainMessage.obj = new Object[]{new UiThreadMediaCallback(mediaCallback), str};
        obtainMessage.sendToTarget();
    }

    public void stopPlay(MediaCallback mediaCallback, String str) {
        Message obtainMessage = this.mHandler.obtainMessage(3005);
        obtainMessage.obj = new Object[]{new UiThreadMediaCallback(mediaCallback), str};
        obtainMessage.sendToTarget();
    }

    public void resetPlay(MediaCallback mediaCallback, String str) {
        Message obtainMessage = this.mHandler.obtainMessage(3009);
        obtainMessage.obj = new Object[]{new UiThreadMediaCallback(mediaCallback), str};
        obtainMessage.sendToTarget();
    }

    public void releasePlayers(MediaCallback mediaCallback) {
        Message obtainMessage = this.mHandler.obtainMessage(3008);
        obtainMessage.obj = new Object[]{new UiThreadMediaCallback(mediaCallback)};
        obtainMessage.sendToTarget();
    }

    public AudioMaterialWrapper createMediaPlayerInMsg(String str, MediaPlayer.OnCompletionListener onCompletionListener, MediaPlayer.OnErrorListener onErrorListener) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ConcurrentHashMap<String, AudioMaterialWrapper> concurrentHashMap = mMediaPlayerMaps;
        if (concurrentHashMap != null && concurrentHashMap.containsKey(str)) {
            return mMediaPlayerMaps.get(str);
        }
        AudioMaterialWrapper initMediaPlayerInMsg = initMediaPlayerInMsg(onCompletionListener, onErrorListener);
        if (initMediaPlayerInMsg == null) {
            return null;
        }
        if (mMediaPlayerMaps == null) {
            mMediaPlayerMaps = new ConcurrentHashMap<>();
        }
        mMediaPlayerMaps.put(str, initMediaPlayerInMsg);
        return initMediaPlayerInMsg;
    }

    public AudioMaterialWrapper getMediaPlayerInMsg(String str) {
        if (mMediaPlayerMaps == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return mMediaPlayerMaps.get(str);
    }

    public void removeMediaPlayerInMsg(String str) {
        if (mMediaPlayerMaps == null || TextUtils.isEmpty(str) || !mMediaPlayerMaps.containsKey(str)) {
            return;
        }
        mMediaPlayerMaps.remove(str);
    }

    public void startPlayInMsg(Message message) {
        Object[] objArr = (Object[]) message.obj;
        MediaCallback mediaCallback = (MediaCallback) objArr[0];
        String str = (String) objArr[1];
        long longValue = ((Long) objArr[6]).longValue();
        mAudioPlayOperationMap.put(str, 3004);
        startPlayInner(mediaCallback, str, (String) objArr[2], null, (MediaPlayer.OnCompletionListener) objArr[3], null, ((Integer) objArr[4]).intValue(), (String) objArr[5], longValue);
    }

    public static AudioMaterialWrapper startPlayInMsg(final AudioMaterialWrapper audioMaterialWrapper, final String str, String str2, AssetFileDescriptor assetFileDescriptor, MediaPlayer.OnCompletionListener onCompletionListener, int i, String str3, final long j) {
        if ((TextUtils.isEmpty(str2) && assetFileDescriptor == null) || audioMaterialWrapper == null) {
            return audioMaterialWrapper;
        }
        audioMaterialWrapper.mInfo.mId = MsgParamsUtil.obj2Long(str, 0L);
        audioMaterialWrapper.mInfo.mActionId = str;
        audioMaterialWrapper.mInfo.mTarget = str3;
        MediaPlayer mediaPlayer = audioMaterialWrapper.mMediaPlayer;
        if (onCompletionListener != null) {
            mediaPlayer.setOnCompletionListener(onCompletionListener);
        }
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.seekTo(0);
                mediaPlayer.stop();
            }
            mediaPlayer.setAudioStreamType(3);
            mediaPlayer.reset();
            if (!TextUtils.isEmpty(str2)) {
                mediaPlayer.setDataSource(str2);
            } else if (assetFileDescriptor != null) {
                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            }
            mediaPlayer.prepareAsync();
            mediaPlayer.setLooping(false);
            LogUtil.m20423e(TAG, "wrapper.mMediaPlayer.prepareAsync()");
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.5
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer2) {
                    LogUtil.m20423e(AudioPlayerManager.TAG, "mMediaPlayer onPrepared");
                    AudioMaterialWrapper.this.mInfo.mType = "STATUS";
                    AudioMaterialWrapper.this.mInfo.mPlayStatus = "prepared";
                    AudioPlayerManager.onAudioCallBack(AudioMaterialWrapper.this);
                    if (ARPEngine.getInstance().isPaused() || AudioPlayerManager.mAudioPlayOperationMap == null || AudioPlayerManager.mAudioPlayOperationMap.get(str) == null) {
                        return;
                    }
                    if (((Integer) AudioPlayerManager.mAudioPlayOperationMap.get(str)).intValue() == 3004 || ((Integer) AudioPlayerManager.mAudioPlayOperationMap.get(str)).intValue() == 3007) {
                        AudioPlayerManager.scheduleTimerTask();
                        try {
                            if (AudioMaterialWrapper.this.mMediaPlayer.getDuration() >= 0) {
                                if (AudioMaterialWrapper.this.mMediaPlayer.getDuration() > j && j >= 0) {
                                    AudioMaterialWrapper.this.mMediaPlayer.seekTo((int) j);
                                }
                                AudioMaterialWrapper.this.mMediaPlayer.seekTo(0);
                            }
                            LogUtil.m20423e(AudioPlayerManager.TAG, "mMediaPlayer start");
                            AudioMaterialWrapper.this.mMediaPlayer.start();
                            AudioMaterialWrapper.this.mInfo.mPlayStatus = "playing";
                        } catch (Exception e) {
                            AudioPlayerManager.releasePlayer(AudioMaterialWrapper.this.mMediaPlayer);
                            e.printStackTrace();
                        }
                    }
                }
            });
            audioMaterialWrapper.mMediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.6
                @Override // android.media.MediaPlayer.OnBufferingUpdateListener
                public void onBufferingUpdate(MediaPlayer mediaPlayer2, int i2) {
                    AudioMaterialWrapper.this.mInfo.mType = "INFO";
                    AudioMaterialWrapper.this.mInfo.mBufferProgress = i2;
                    AudioPlayerManager.onAudioCallBack(AudioMaterialWrapper.this);
                }
            });
            audioMaterialWrapper.mMediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.7
                @Override // android.media.MediaPlayer.OnInfoListener
                public boolean onInfo(MediaPlayer mediaPlayer2, int i2, int i3) {
                    AudioMaterialWrapper.this.mInfo.mType = "INFO";
                    switch (i2) {
                        case 701:
                            AudioMaterialWrapper.this.mInfo.mBufferStatus = "buffer_start";
                            AudioPlayerManager.onAudioCallBack(AudioMaterialWrapper.this);
                            return false;
                        case 702:
                            AudioMaterialWrapper.this.mInfo.mBufferStatus = "buffer_end";
                            AudioPlayerManager.onAudioCallBack(AudioMaterialWrapper.this);
                            return false;
                        default:
                            return false;
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (SecurityException e4) {
            e4.printStackTrace();
        }
        return audioMaterialWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onAudioCallBack(AudioMaterialWrapper audioMaterialWrapper) {
        if (audioMaterialWrapper == null) {
            return;
        }
        filterPlayProgress(audioMaterialWrapper);
        updateAudioPlayInfo(audioMaterialWrapper.mInfo);
        MediaInfo mediaInfo = audioMaterialWrapper.mInfo;
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", 5211);
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

    private static void updateAudioPlayInfo(MediaInfo mediaInfo) {
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
        ARPMessage.getInstance().sendMessage(1011, hashMap);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0041 -> B:29:0x0044). Please submit an issue!!! */
    private static void filterPlayProgress(AudioMaterialWrapper audioMaterialWrapper) {
        if (audioMaterialWrapper == null || audioMaterialWrapper.mMediaPlayer == null) {
            return;
        }
        MediaInfo mediaInfo = audioMaterialWrapper.mInfo;
        if (mediaInfo.mPlayStatus == "playing" || mediaInfo.mPlayStatus == "paused") {
            try {
                mediaInfo.mDuration = audioMaterialWrapper.mMediaPlayer.getDuration();
                if (mediaInfo.mDuration <= 0) {
                    mediaInfo.mPlayingProgress = 0.0f;
                } else {
                    mediaInfo.mPlayingProgress = (audioMaterialWrapper.mMediaPlayer.getCurrentPosition() * 1.0f) / mediaInfo.mDuration;
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

    private void startPlayInner(MediaCallback mediaCallback, String str, String str2, AssetFileDescriptor assetFileDescriptor, MediaPlayer.OnCompletionListener onCompletionListener, MediaPlayer.OnErrorListener onErrorListener, int i, String str3, long j) {
        try {
            AudioMaterialWrapper createMediaPlayerInMsg = createMediaPlayerInMsg(str, onCompletionListener, onErrorListener);
            if (createMediaPlayerInMsg != null) {
                startPlayInMsg(createMediaPlayerInMsg, str, str2, assetFileDescriptor, onCompletionListener, i, str3, j);
            }
            if (mediaCallback != null) {
                mediaCallback.onResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            AudioMaterialWrapper mediaPlayerInMsg = getMediaPlayerInMsg(str);
            removeMediaPlayerInMsg(str);
            try {
                if (mediaPlayerInMsg.mMediaPlayer != null) {
                    mediaPlayerInMsg.mMediaPlayer.release();
                }
                try {
                    AudioMaterialWrapper createMediaPlayerInMsg2 = getInstance().createMediaPlayerInMsg(str, onCompletionListener, onErrorListener);
                    if (createMediaPlayerInMsg2 != null) {
                        startPlayInMsg(createMediaPlayerInMsg2, str, str2, assetFileDescriptor, onCompletionListener, i, str3, j);
                    }
                    if (mediaCallback != null) {
                        mediaCallback.onResult(true);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (mediaCallback != null) {
                        mediaCallback.onException(e2);
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                if (mediaCallback != null) {
                    mediaCallback.onException(e3);
                }
            }
        }
    }

    public void stopPlayInMsg(Message message) {
        Object[] objArr = (Object[]) message.obj;
        MediaCallback mediaCallback = (MediaCallback) objArr[0];
        String str = (String) objArr[1];
        mAudioPlayOperationMap.put(str, 3005);
        AudioMaterialWrapper mediaPlayerInMsg = getMediaPlayerInMsg(str);
        if (mediaPlayerInMsg != null) {
            try {
                mediaPlayerInMsg.mInfo.mType = "STATUS";
                mediaPlayerInMsg.mInfo.mPlayStatus = "unstarted";
                onAudioCallBack(mediaPlayerInMsg);
                LogUtil.m20423e(TAG, "mMediaPlayer stopPlay");
                releasePlayer(mediaPlayerInMsg.mMediaPlayer);
                mediaPlayerInMsg.mMediaPlayer = null;
                removeMediaPlayerInMsg(str);
            } catch (Exception e) {
                e.printStackTrace();
                if (mediaCallback != null) {
                    mediaCallback.onException(e);
                    return;
                }
                return;
            }
        }
        if (mediaCallback != null) {
            mediaCallback.onResult(true);
        }
    }

    public void pausePlayInMsg(Message message) {
        Object[] objArr = (Object[]) message.obj;
        MediaCallback mediaCallback = (MediaCallback) objArr[0];
        String str = (String) objArr[1];
        mAudioPlayOperationMap.put(str, 3006);
        try {
            AudioMaterialWrapper mediaPlayerInMsg = getMediaPlayerInMsg(str);
            if (mediaPlayerInMsg.mMediaPlayer != null && mediaPlayerInMsg.mInfo.mPlayStatus != "unstarted" && mediaPlayerInMsg.mMediaPlayer.isPlaying()) {
                mediaPlayerInMsg.mInfo.mType = "STATUS";
                mediaPlayerInMsg.mInfo.mPlayStatus = "paused";
                onAudioCallBack(mediaPlayerInMsg);
                LogUtil.m20423e(TAG, "mMediaPlayer pause");
                mediaPlayerInMsg.mMediaPlayer.pause();
            }
            if (mediaCallback != null) {
                mediaCallback.onResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (mediaCallback != null) {
                mediaCallback.onException(e);
            }
        }
    }

    public void resumePlay(MediaCallback mediaCallback, String str, String str2, MediaPlayer.OnCompletionListener onCompletionListener, int i) {
        Message obtainMessage = this.mHandler.obtainMessage(3007);
        obtainMessage.obj = new Object[]{new UiThreadMediaCallback(mediaCallback), str, str2, onCompletionListener, Integer.valueOf(i)};
        obtainMessage.sendToTarget();
    }

    public void resetPlayInMsg(Message message) {
        Object[] objArr = (Object[]) message.obj;
        if (objArr.length < 2) {
            return;
        }
        MediaCallback mediaCallback = (MediaCallback) objArr[0];
        String str = (String) objArr[1];
        mAudioPlayOperationMap.put(str, 3009);
        try {
            AudioMaterialWrapper mediaPlayerInMsg = getMediaPlayerInMsg(str);
            if (mediaPlayerInMsg == null || mediaPlayerInMsg.mInfo.mPlayStatus == "unstarted") {
                return;
            }
            mediaPlayerInMsg.mMediaPlayer.seekTo(0);
        } catch (Exception e) {
            e.printStackTrace();
            if (mediaCallback != null) {
                mediaCallback.onException(e);
            }
        }
    }

    public void resumePlayInMsg(Message message) {
        long j;
        String str;
        if (ARPEngine.getInstance().isPaused()) {
            return;
        }
        Object[] objArr = (Object[]) message.obj;
        if (objArr.length < 5) {
            return;
        }
        MediaCallback mediaCallback = (MediaCallback) objArr[0];
        String str2 = (String) objArr[1];
        String str3 = (String) objArr[2];
        MediaPlayer.OnCompletionListener onCompletionListener = (MediaPlayer.OnCompletionListener) objArr[3];
        int intValue = ((Integer) objArr[4]).intValue();
        if (objArr.length > 6) {
            j = ((Long) objArr[6]).longValue();
            str = (String) objArr[5];
        } else {
            j = 0;
            str = null;
        }
        mAudioPlayOperationMap.put(str2, 3007);
        try {
            AudioMaterialWrapper mediaPlayerInMsg = getMediaPlayerInMsg(str2);
            if (mediaPlayerInMsg != null && !mediaPlayerInMsg.mMediaPlayer.isPlaying() && mediaPlayerInMsg.mInfo.mPlayStatus != "unstarted") {
                mediaPlayerInMsg.mInfo.mType = "STATUS";
                mediaPlayerInMsg.mInfo.mPlayStatus = "playing";
                onAudioCallBack(mediaPlayerInMsg);
                LogUtil.m20423e(TAG, "MediaPlayer resume");
                mediaPlayerInMsg.mMediaPlayer.seekTo(mediaPlayerInMsg.mMediaPlayer.getCurrentPosition());
                mediaPlayerInMsg.mMediaPlayer.start();
            }
            if (mediaCallback != null) {
                mediaCallback.onResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            rebuildPlayerInMsg(mediaCallback, str2, str3, onCompletionListener, intValue, str, j);
        }
    }

    private void rebuildPlayerInMsg(MediaCallback mediaCallback, String str, String str2, MediaPlayer.OnCompletionListener onCompletionListener, int i, String str3, long j) {
        AudioMaterialWrapper mediaPlayerInMsg = getMediaPlayerInMsg(str);
        removeMediaPlayerInMsg(str);
        if (mediaPlayerInMsg != null) {
            try {
                if (mediaPlayerInMsg.mMediaPlayer != null) {
                    mediaPlayerInMsg.mMediaPlayer.release();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (mediaCallback != null) {
                    mediaCallback.onException(e);
                    return;
                }
                return;
            }
        }
        try {
            AudioMaterialWrapper createMediaPlayerInMsg = createMediaPlayerInMsg(str, onCompletionListener, null);
            if (createMediaPlayerInMsg != null) {
                startPlayInMsg(createMediaPlayerInMsg, str, str2, null, onCompletionListener, i, str3, j);
            }
            if (mediaCallback != null) {
                mediaCallback.onResult(true);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (mediaCallback != null) {
                mediaCallback.onException(e2);
            }
        }
    }

    public void releaseMediaPlayer() {
        this.mHandlerCallback = null;
        Timer timer = mTimer;
        if (timer != null) {
            timer.cancel();
            mTimer.purge();
            mTimer = null;
            mTimerTask.cancel();
            mTimerTask = null;
        }
        Map<String, Integer> map = mAudioPlayOperationMap;
        if (map != null) {
            map.clear();
        }
        ConcurrentHashMap<String, AudioMaterialWrapper> concurrentHashMap = mMediaPlayerMaps;
        if (concurrentHashMap != null) {
            try {
                for (AudioMaterialWrapper audioMaterialWrapper : concurrentHashMap.values()) {
                    audioMaterialWrapper.mInfo.mType = "STATUS";
                    audioMaterialWrapper.mInfo.mPlayStatus = "unstarted";
                    releasePlayer(audioMaterialWrapper.mMediaPlayer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            ConcurrentHashMap<String, AudioMaterialWrapper> concurrentHashMap2 = mMediaPlayerMaps;
            if (concurrentHashMap2 != null) {
                concurrentHashMap2.clear();
            }
        }
        Map<String, Integer> map2 = this.mAudioPlayCountMap;
        if (map2 != null) {
            map2.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void scheduleTimerTask() {
        synchronized (AudioPlayerManager.class) {
            if (mTimer == null) {
                mTimer = new Timer();
                mTimerTask = new TimerTask() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.8
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        AudioMaterialWrapper audioMaterialWrapper;
                        if (AudioPlayerManager.mMediaPlayerMaps != null) {
                            for (Map.Entry entry : AudioPlayerManager.mMediaPlayerMaps.entrySet()) {
                                if (entry != null && (audioMaterialWrapper = (AudioMaterialWrapper) entry.getValue()) != null && audioMaterialWrapper.mInfo != null && audioMaterialWrapper.mInfo.mPlayStatus == "playing") {
                                    AudioPlayerManager.onAudioCallBack((AudioMaterialWrapper) entry.getValue());
                                }
                            }
                        }
                    }
                };
                mTimer.scheduleAtFixedRate(mTimerTask, 0L, 200L);
            }
        }
    }

    public static synchronized void release() {
        synchronized (AudioPlayerManager.class) {
            if (mInstance != null) {
                mInstance.releaseMediaPlayer();
            }
            mInstance = null;
            sUiHandler.postDelayed(sQuitRunnable, 10000L);
            if (mCallback != null) {
                mCallback = null;
            }
        }
    }

    public void releasePlayersInMsg(Message message) {
        MediaCallback mediaCallback = (MediaCallback) ((Object[]) message.obj)[0];
        this.mHasRun = false;
        this.mAudioIndex = 0;
        ConcurrentHashMap<String, AudioMaterialWrapper> concurrentHashMap = mMediaPlayerMaps;
        if (concurrentHashMap != null) {
            try {
                for (AudioMaterialWrapper audioMaterialWrapper : concurrentHashMap.values()) {
                    if (audioMaterialWrapper != null) {
                        audioMaterialWrapper.mMediaPlayer.release();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            mMediaPlayerMaps.clear();
        }
        if (mediaCallback != null) {
            mediaCallback.onResult(true);
        }
    }

    public void openAudio(final AudioBean audioBean, final HashMap<String, Object> hashMap) {
        if (audioBean.getLoop() > 1) {
            this.mAudioPlayCountMap.put(audioBean.getId(), Integer.valueOf(audioBean.getLoop()));
        }
        sendResponseMessage(1002, hashMap);
        startPlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.9
            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onException(Exception exc) {
                AudioPlayerManager.this.sendResponseMessage(1010, hashMap);
            }
        }, audioBean.getId(), audioBean.getUrl(), new MediaPlayer.OnCompletionListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.10
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                LogUtil.m20423e(AudioPlayerManager.TAG, "openAudio mMediaPlayer onCompletion");
                if (AudioPlayerManager.this.mAudioPlayCountMap == null) {
                    return;
                }
                AudioMaterialWrapper mediaPlayerInMsg = AudioPlayerManager.this.getMediaPlayerInMsg(audioBean.getId());
                if (mediaPlayerInMsg == null || mediaPlayerInMsg.mInfo.mPlayStatus != "paused") {
                    if (mediaPlayerInMsg != null) {
                        mediaPlayerInMsg.mInfo.mType = "STATUS";
                        mediaPlayerInMsg.mInfo.mPlayStatus = "finished";
                        AudioPlayerManager.onAudioCallBack(mediaPlayerInMsg);
                        if (audioBean.isLoopForever()) {
                            LogUtil.m20423e(AudioPlayerManager.TAG, "openAudio mMediaPlayer onCompletion  isLoopForever open！");
                            AudioPlayerManager.this.openAudio(audioBean, hashMap);
                            return;
                        }
                    }
                    if (AudioPlayerManager.this.mAudioPlayCountMap.size() > 0) {
                        int playCount = AudioPlayerManager.getPlayCount(AudioPlayerManager.this.mAudioPlayCountMap, audioBean.getId());
                        if (playCount > 1) {
                            LogUtil.m20423e(AudioPlayerManager.TAG, "openAudio mMediaPlayer onCompletion  count open ！");
                            AudioPlayerManager.this.openAudio(audioBean, hashMap);
                            int i = playCount - 1;
                            AudioPlayerManager.this.mAudioPlayCountMap.put(audioBean.getId(), Integer.valueOf(i));
                            audioBean.setLoop(i);
                            return;
                        }
                        AudioPlayerManager.this.sendResponseMessage(1009, hashMap);
                        mediaPlayer.release();
                        return;
                    }
                    AudioPlayerManager.this.sendResponseMessage(1009, hashMap);
                    mediaPlayer.release();
                }
            }
        }, audioBean.getLoop(), audioBean.getTargetName(), audioBean.getFromTime());
    }

    public static int getPlayCount(Map<String, Integer> map, String str) {
        Integer num = map.get(str);
        if (num == null) {
            return 0;
        }
        return num.intValue();
    }

    public void pauseAudio(AudioBean audioBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1004, hashMap);
        pausePlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.11
            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onException(Exception exc) {
                AudioPlayerManager.this.sendResponseMessage(1010, hashMap);
            }
        }, audioBean.getId());
    }

    public void resumeAudio(final AudioBean audioBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1006, hashMap);
        resumePlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.12
            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onException(Exception exc) {
                AudioPlayerManager.this.sendResponseMessage(1010, hashMap);
            }
        }, audioBean.getId(), audioBean.getUrl(), new MediaPlayer.OnCompletionListener() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.13
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                if (AudioPlayerManager.this.mAudioPlayCountMap == null) {
                    return;
                }
                AudioMaterialWrapper mediaPlayerInMsg = AudioPlayerManager.this.getMediaPlayerInMsg(audioBean.getId());
                if (mediaPlayerInMsg != null) {
                    mediaPlayerInMsg.mInfo.mType = "STATUS";
                    mediaPlayerInMsg.mInfo.mPlayStatus = "finished";
                    AudioPlayerManager.onAudioCallBack(mediaPlayerInMsg);
                    if (audioBean.isLoopForever()) {
                        AudioPlayerManager.this.openAudio(audioBean, hashMap);
                        return;
                    }
                }
                if (AudioPlayerManager.this.mAudioPlayCountMap.size() > 0) {
                    int playCount = AudioPlayerManager.getPlayCount(AudioPlayerManager.this.mAudioPlayCountMap, audioBean.getId());
                    if (playCount > 1) {
                        AudioPlayerManager.this.openAudio(audioBean, hashMap);
                        int i = playCount - 1;
                        AudioPlayerManager.this.mAudioPlayCountMap.put(audioBean.getId(), Integer.valueOf(i));
                        audioBean.setLoop(i);
                        return;
                    }
                    AudioPlayerManager.this.sendResponseMessage(1009, hashMap);
                    return;
                }
                AudioPlayerManager.this.sendResponseMessage(1009, hashMap);
            }
        }, audioBean.getLoop());
    }

    public void stopAudio(AudioBean audioBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1008, hashMap);
        stopPlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.14
            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onException(Exception exc) {
                AudioPlayerManager.this.sendResponseMessage(1010, hashMap);
            }
        }, audioBean.getId());
    }

    public void resetAudio(AudioBean audioBean, final HashMap<String, Object> hashMap) {
        sendResponseMessage(1013, hashMap);
        resetPlay(new MediaCallback() { // from class: com.baidu.ar.arplay.component.AudioPlayerManager.15
            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onResult(boolean z) {
            }

            @Override // com.baidu.p120ar.arplay.component.AudioPlayerManager.MediaCallback
            public void onException(Exception exc) {
                AudioPlayerManager.this.sendResponseMessage(1010, hashMap);
            }
        }, audioBean.getId());
    }

    public void sendResponseMessage(int i, HashMap<String, Object> hashMap) {
        ARPMessage.getInstance().sendMessage(i, hashMap);
    }

    public static void releasePlayer(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
