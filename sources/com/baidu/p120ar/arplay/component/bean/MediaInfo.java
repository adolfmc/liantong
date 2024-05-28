package com.baidu.p120ar.arplay.component.bean;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.bean.MediaInfo */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MediaInfo {
    public static final String KEY_ACTION_ID = "action_id";
    public static final String KEY_BUFFER_PEOGRESS = "buffer_progress";
    public static final String KEY_BUFFER_STATUS = "buffer_status";
    public static final String KEY_DATA = "data";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_ERROR = "error_code";
    public static final String KEY_ID = "id";
    public static final String KEY_MSG_DATA = "msg_data";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_PLAYING_PEOGRESS = "play_progress";
    public static final String KEY_PLAY_STATUS = "play_status";
    public static final String KEY_TARGET = "target";
    public static final String KEY_TYPE = "type";
    public static final String TYPE_ERROR = "ERROR";
    public static final String TYPE_INFO = "INFO";
    public static final String TYPE_STATUS = "STATUS";
    public String mActionId;
    public long mId;
    public String mPlatform;
    public String mTarget;
    public String mType;
    public int mDuration = 0;
    public int mErrorCode = -1;
    public String mPlayStatus = "unstarted";
    public String mBufferStatus = "unstarted";
    public int mBufferProgress = 0;
    public float mPlayingProgress = 0.0f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.bean.MediaInfo$MediaBufferStatus */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MediaBufferStatus {
        public static final String BUFFER_DEFAULT = "unstarted";
        public static final String BUFFER_END = "buffer_end";
        public static final String BUFFER_START = "buffer_start";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.arplay.component.bean.MediaInfo$MediaPlayStatus */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MediaPlayStatus {
        public static final String COMPLETED = "finished";
        public static final String PAUSED = "paused";
        public static final String PLAYING = "playing";
        public static final String PREPARED = "prepared";
        public static final String UN_INITIALIZED = "unstarted";
    }
}
