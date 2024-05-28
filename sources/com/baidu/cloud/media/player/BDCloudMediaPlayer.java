package com.baidu.cloud.media.player;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.baidu.cloud.license.LicenseManager;
import com.baidu.cloud.media.download.LocalHlsSec;
import com.baidu.cloud.media.player.BDCloudMediaMeta;
import com.baidu.cloud.media.player.annotations.AccessedByNative;
import com.baidu.cloud.media.player.annotations.CalledByNative;
import com.baidu.cloud.media.player.apm.APMEventHandle;
import com.baidu.cloud.media.player.misc.BDCloudTrackInfo;
import com.baidu.cloud.media.player.misc.IMediaDataSource;
import com.baidu.cloud.media.player.pragma.DebugLog;
import com.baidu.cloud.media.player.render.record.OnReceiveAudioDataCallback;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class BDCloudMediaPlayer extends AbstractMediaPlayer {
    private static final int AVAPP_EVENT_DNS = 81920;
    private static final int AVAPP_EVENT_HTTP_CONNECT_END = 196610;
    private static final int AVAPP_EVENT_METADATA = 77824;
    public static final int DECODE_AUTO = 0;
    public static final int DECODE_SW = 1;
    private static final int FFP_PROPV_DECODER_AVCODEC = 1;
    private static final int FFP_PROPV_DECODER_MEDIACODEC = 2;
    private static final int FFP_PROPV_DECODER_UNKNOWN = 0;
    private static final int FFP_PROPV_DECODER_VIDEOTOOLBOX = 3;
    private static final int FFP_PROP_FLOAT_PLAYBACK_RATE = 10003;
    private static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_BACKWARDS = 20201;
    private static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_CAPACITY = 20203;
    private static final int FFP_PROP_INT64_ASYNC_STATISTIC_BUF_FORWARDS = 20202;
    private static final int FFP_PROP_INT64_AUDIO_CACHED_BYTES = 20008;
    private static final int FFP_PROP_INT64_AUDIO_CACHED_DURATION = 20006;
    private static final int FFP_PROP_INT64_AUDIO_CACHED_PACKETS = 20010;
    private static final int FFP_PROP_INT64_AUDIO_DECODER = 20004;
    private static final int FFP_PROP_INT64_BIT_RATE = 20100;
    private static final int FFP_PROP_INT64_CACHE_STATISTIC_BUF_FORWARDS = 20206;
    private static final int FFP_PROP_INT64_CACHE_STATISTIC_COUNT_BYTES = 20208;
    private static final int FFP_PROP_INT64_CACHE_STATISTIC_FILE_POS = 20207;
    private static final int FFP_PROP_INT64_CACHE_STATISTIC_PHYSICAL_POS = 20205;
    private static final int FFP_PROP_INT64_LATEST_SEEK_LOAD_DURATION = 20300;
    private static final int FFP_PROP_INT64_SELECTED_AUDIO_STREAM = 20002;
    private static final int FFP_PROP_INT64_SELECTED_TIMEDTEXT_STREAM = 20011;
    private static final int FFP_PROP_INT64_SELECTED_VIDEO_STREAM = 20001;
    private static final int FFP_PROP_INT64_TCP_SPEED = 20200;
    private static final int FFP_PROP_INT64_TRAFFIC_STATISTIC_BYTE_COUNT = 20204;
    private static final int FFP_PROP_INT64_VIDEO_CACHED_BYTES = 20007;
    private static final int FFP_PROP_INT64_VIDEO_CACHED_DURATION = 20005;
    private static final int FFP_PROP_INT64_VIDEO_CACHED_PACKETS = 20009;
    private static final int FFP_PROP_INT64_VIDEO_DECODER = 20003;
    private static final int IJK_LOG_DEBUG = 3;
    private static final int IJK_LOG_DEFAULT = 1;
    private static final int IJK_LOG_ERROR = 6;
    private static final int IJK_LOG_FATAL = 7;
    private static final int IJK_LOG_INFO = 4;
    private static final int IJK_LOG_SILENT = 8;
    private static final int IJK_LOG_UNKNOWN = 0;
    private static final int IJK_LOG_VERBOSE = 2;
    private static final int IJK_LOG_WARN = 5;
    private static final int MEDIA_BUFFERING_UPDATE = 4;
    private static final int MEDIA_ERROR = 100;
    private static final int MEDIA_INFO = 200;
    private static final int MEDIA_NOP = 0;
    private static final int MEDIA_PLAYBACK_COMPLETE = 3;
    private static final int MEDIA_PLAYBACK_STOPPED = 2;
    private static final int MEDIA_PREPARED = 1;
    private static final int MEDIA_SEEK_COMPLETE = 5;
    protected static final int MEDIA_SET_VIDEO_SAR = 10001;
    private static final int MEDIA_SET_VIDEO_SIZE = 6;
    private static final int MEDIA_TIMED_TEXT = 99;
    private static final int OPT_CATEGORY_CODEC = 2;
    private static final int OPT_CATEGORY_FORMAT = 1;
    private static final int OPT_CATEGORY_PLAYER = 4;
    private static final int OPT_CATEGORY_SWS = 3;
    public static final int PLAY_TYPE_LIVE = 1;
    public static final int PLAY_TYPE_VOD = 0;
    private static final int PROP_FLOAT_VIDEO_DECODE_FRAMES_PER_SECOND = 10001;
    private static final int PROP_FLOAT_VIDEO_OUTPUT_FRAMES_PER_SECOND = 10002;
    public static final String SDK_VERSION = "2.3.6";
    private static final int SDL_FCC_RV16 = 909203026;
    private static final int SDL_FCC_RV32 = 842225234;
    private static final int SDL_FCC_YV12 = 842094169;
    public static final int SOURCE_SWITCH_NORMAL_MODE = 0;
    public static final int SOURCE_SWITCH_SMOOTH_HLS_MODE = 2;
    public static final int SOURCE_SWITCH_SMOOTH_MODE = 1;
    private static final String SP_FILE_FOR_KEY = "__cyberplayer_dl_sec";
    private static final String TAG = "com.baidu.cloud.media.player.BDCloudMediaPlayer";
    private static String mAK = "";
    private static String mAppId = "";
    private static String playId = "";
    private static String sCacheRootPath = null;
    private static String sCuid = "";
    private static boolean sEnableCache;
    private static boolean sEnableP2P;
    private Timer apmPlayingTimer;
    private Context appContext;
    private JSONArray bufferStatJsonArray;
    private long bufferingStartPosition;
    private int cachePauseTimeForApm;
    private int decodeMode;
    private int firstBufferingTimeForApm;
    private volatile boolean isEndSended;
    private long lastStartPlayTime;
    private Date latestBufferStartTime;
    private boolean mApmEventPlayerCreated;
    private String mCustomisedHeaders;
    private String mDataSource;
    private EventHandler mEventHandler;
    @AccessedByNative
    private int mListenerContext;
    @AccessedByNative
    private long mNativeAndroidIO;
    @AccessedByNative
    private long mNativeMediaDataSource;
    @AccessedByNative
    private long mNativeMediaPlayer;
    @AccessedByNative
    private int mNativeSurfaceTexture;
    private int mNetworkTimeoutInUs;
    private OnControlMessageListener mOnControlMessageListener;
    private OnMediaCodecSelectListener mOnMediaCodecSelectListener;
    private OnNativeInvokeListener mOnNativeInvokeListener;
    private int mPlayType;
    private boolean mScreenOnWhilePlaying;
    private boolean mStayAwake;
    private SurfaceHolder mSurfaceHolder;
    private int mVideoHeight;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private int mVideoWidth;
    private PowerManager.WakeLock mWakeLock;
    private int maxCacheSizeForApm;
    private long startPrepareTimeForApm;
    private int stayInterval;
    private boolean toggleFrameChasingForApm;
    private static final BDCloudLibLoader sLocalLibLoader = new BDCloudLibLoader() { // from class: com.baidu.cloud.media.player.BDCloudMediaPlayer.1
        @Override // com.baidu.cloud.media.player.BDCloudLibLoader
        public void loadLibrary(String str) throws UnsatisfiedLinkError, SecurityException {
            System.loadLibrary(str);
        }
    };
    private static volatile boolean mIsLibLoaded = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface OnControlMessageListener {
        String onControlResolveSegmentUrl(int i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnMediaCodecSelectListener {
        String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface OnNativeInvokeListener {
        public static final String ARG_ERROR = "error";
        public static final String ARG_FAMILIY = "family";
        public static final String ARG_FD = "fd";
        public static final String ARG_HTTP_CODE = "http_code";
        public static final String ARG_IP = "ip";
        public static final String ARG_OFFSET = "offset";
        public static final String ARG_PORT = "port";
        public static final String ARG_RETRY_COUNTER = "retry_counter";
        public static final String ARG_SEGMENT_INDEX = "segment_index";
        public static final String ARG_URL = "url";
        public static final int CTRL_DID_TCP_OPEN = 131074;
        public static final int CTRL_WILL_CONCAT_RESOLVE_SEGMENT = 131079;
        public static final int CTRL_WILL_HTTP_OPEN = 131075;
        public static final int CTRL_WILL_LIVE_OPEN = 131077;
        public static final int CTRL_WILL_TCP_OPEN = 131073;
        public static final int EVENT_DID_HTTP_OPEN = 2;
        public static final int EVENT_DID_HTTP_SEEK = 4;
        public static final int EVENT_WILL_HTTP_OPEN = 1;
        public static final int EVENT_WILL_HTTP_SEEK = 3;

        boolean onNativeInvoke(int i, Bundle bundle);
    }

    private native String _getAudioCodecInfo();

    private static native String _getColorFormatName(int i);

    private native int _getLoopCount();

    private native Bundle _getMediaMeta();

    private native float _getPropertyFloat(int i, float f);

    private native long _getPropertyLong(int i, long j);

    private native String _getVideoCodecInfo();

    private native void _pause() throws IllegalStateException;

    private native void _prepareAsync() throws IllegalStateException;

    private native void _release();

    private native void _reset();

    private native void _seekTo(long j) throws IllegalStateException;

    private native void _setDataSource(IMediaDataSource iMediaDataSource) throws IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSource(String str, String[] strArr, String[] strArr2) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setDataSourceFd(int i) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    private native void _setLoopCount(int i);

    private native void _setOption(int i, String str, long j);

    private native void _setOption(int i, String str, String str2);

    private native void _setPropertyFloat(int i, float f);

    private native void _setPropertyLong(int i, long j);

    private native void _setStreamSelected(int i, boolean z);

    private native void _setVideoSurface(Surface surface);

    private native void _start() throws IllegalStateException;

    private native void _stop() throws IllegalStateException;

    private native String getCdnIp();

    public static String getSdkVersion() {
        return "2.3.6";
    }

    private native void native_finalize();

    private native void native_init(Object obj);

    private native void native_message_loop(Object obj);

    private static native void native_profileBegin(String str);

    private static native void native_profileEnd();

    private static native void native_setLogLevel(int i);

    private native void native_setup();

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public native int getAudioSessionId();

    public native int getCurrentMediaIndex();

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public native long getCurrentPosition();

    public native int getCurrentVariantIndex();

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public native long getDuration();

    public native String[] getMediaItems();

    public native String[] getVariantInfo();

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public boolean isPlayable() {
        return true;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public native boolean isPlaying();

    public native void selectVariantByIndex(int i);

    public native void setAudioDataCallback(OnReceiveAudioDataCallback onReceiveAudioDataCallback);

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setAudioStreamType(int i) {
    }

    public native void setCustomizedPlayerIdForHLS(String str);

    public native void setCustomizedPlayerKeyForHLS(String str);

    public native void setDecryptTokenForHLS(String str);

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setKeepInBackground(boolean z) {
    }

    protected native void setLocalDecryptKeyForHLS(String str);

    public native boolean setMediaItemIndex(int i);

    public native void setMediaItems(String[] strArr);

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public native void setVolume(float f, float f2);

    public static void setAK(String str) {
        mAK = str;
    }

    public static void setAppId(String str) {
        mAppId = str;
    }

    public static void setCuid(String str) {
        sCuid = str;
        APMEventHandle.setCuid(str);
    }

    public static void setLocalCacheEnabled(boolean z, String str) {
        sEnableCache = z;
        sCacheRootPath = str;
        if (sEnableCache && TextUtils.isEmpty(sCacheRootPath)) {
            throw new IllegalArgumentException("cachePath can not be empty if enabled local cache");
        }
    }

    public static void loadLibrariesOnce(BDCloudLibLoader bDCloudLibLoader) {
        synchronized (BDCloudMediaPlayer.class) {
            if (!mIsLibLoaded) {
                if (bDCloudLibLoader == null) {
                    bDCloudLibLoader = sLocalLibLoader;
                }
                bDCloudLibLoader.loadLibrary("stlport_shared");
                bDCloudLibLoader.loadLibrary("bdsoundutils");
                bDCloudLibLoader.loadLibrary("bdplayer");
                mIsLibLoaded = true;
            }
        }
    }

    public BDCloudMediaPlayer(Context context) {
        this(context, sLocalLibLoader);
    }

    public BDCloudMediaPlayer(Context context, BDCloudLibLoader bDCloudLibLoader) {
        this.mPlayType = 0;
        this.mWakeLock = null;
        this.mNetworkTimeoutInUs = 15000000;
        this.stayInterval = 0;
        this.lastStartPlayTime = 0L;
        this.bufferStatJsonArray = new JSONArray();
        this.latestBufferStartTime = null;
        this.bufferingStartPosition = -1L;
        this.mApmEventPlayerCreated = false;
        this.startPrepareTimeForApm = 0L;
        this.maxCacheSizeForApm = 0;
        this.cachePauseTimeForApm = 0;
        this.firstBufferingTimeForApm = 0;
        this.isEndSended = true;
        this.apmPlayingTimer = null;
        this.appContext = null;
        this.mCustomisedHeaders = null;
        this.decodeMode = 0;
        this.appContext = context.getApplicationContext();
        LicenseManager.init(this.appContext, mAppId);
        APMEventHandle.setUAUploadEnable(false);
        APMEventHandle.setCuid(sCuid);
        this.mApmEventPlayerCreated = true;
        initPlayer(bDCloudLibLoader);
    }

    private void initPlayer(BDCloudLibLoader bDCloudLibLoader) {
        loadLibrariesOnce(bDCloudLibLoader);
        Looper myLooper = Looper.myLooper();
        if (myLooper != null) {
            this.mEventHandler = new EventHandler(this, myLooper);
        } else {
            Looper mainLooper = Looper.getMainLooper();
            if (mainLooper != null) {
                this.mEventHandler = new EventHandler(this, mainLooper);
            } else {
                this.mEventHandler = null;
            }
        }
        native_init(new WeakReference(this));
        setDecodeMode(0);
        setOption(4, "start-on-prepared", 0L);
        setOption(4, "max-fps", 30L);
        setOption(4, "framedrop", 1L);
        setOption(4, "framechasing", 0L);
        setOption(4, "soundtouch", 1L);
        setOption(4, "subtitle", 1L);
        setOption(4, "max-buffer-size", 15728640L);
        setOption(4, "max-buffer-duration", 5000L);
        setOption(4, "enable-accurate-seek", 1L);
        setOption(4, "guess-gop-length-in-ms", 10000L);
        setLogEnabled(false);
    }

    private void setupPlayer() {
        setOption(1, "reconnect", 1L);
        setOption(1, "timeout", this.mNetworkTimeoutInUs);
        native_setup();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        _setVideoSurface(surfaceHolder != null ? surfaceHolder.getSurface() : null);
        updateSurfaceScreenOn();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setSurface(Surface surface) {
        if (this.mScreenOnWhilePlaying && surface != null) {
            DebugLog.m20056w(TAG, "setScreenOnWhilePlaying(true) is ineffective for Surface");
        }
        this.mSurfaceHolder = null;
        _setVideoSurface(surface);
        updateSurfaceScreenOn();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        setDataSource(context, uri, (Map<String, String>) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x007e, code lost:
        if (0 != 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0081, code lost:
        if (0 != 0) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0083, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0086, code lost:
        setDataSource(r9.toString(), r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
        return;
     */
    @Override // com.baidu.cloud.media.player.IMediaPlayer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setDataSource(android.content.Context r8, android.net.Uri r9, java.util.Map<java.lang.String, java.lang.String> r10) throws java.io.IOException, java.lang.IllegalArgumentException, java.lang.SecurityException, java.lang.IllegalStateException {
        /*
            r7 = this;
            java.lang.String r0 = r9.getScheme()
            java.lang.String r1 = "file"
            boolean r1 = r1.equals(r0)
            if (r1 == 0) goto L14
            java.lang.String r8 = r9.getPath()
            r7.setDataSource(r8)
            return
        L14:
            java.lang.String r1 = "content"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L3c
            java.lang.String r0 = "settings"
            java.lang.String r1 = r9.getAuthority()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3c
            int r9 = android.media.RingtoneManager.getDefaultType(r9)
            android.net.Uri r9 = android.media.RingtoneManager.getActualDefaultRingtoneUri(r8, r9)
            if (r9 == 0) goto L34
            goto L3c
        L34:
            java.io.FileNotFoundException r8 = new java.io.FileNotFoundException
            java.lang.String r9 = "Failed to resolve default ringtone"
            r8.<init>(r9)
            throw r8
        L3c:
            r0 = 0
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            java.lang.String r1 = "r"
            android.content.res.AssetFileDescriptor r0 = r8.openAssetFileDescriptor(r9, r1)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            if (r0 != 0) goto L4f
            if (r0 == 0) goto L4e
            r0.close()
        L4e:
            return
        L4f:
            long r1 = r0.getDeclaredLength()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            r3 = 0
            int r8 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r8 >= 0) goto L61
            java.io.FileDescriptor r8 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            r7.setDataSource(r8)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            goto L71
        L61:
            java.io.FileDescriptor r2 = r0.getFileDescriptor()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            long r3 = r0.getStartOffset()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            long r5 = r0.getDeclaredLength()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
            r1 = r7
            r1.setDataSource(r2, r3, r5)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L7e java.lang.SecurityException -> L81
        L71:
            if (r0 == 0) goto L76
            r0.close()
        L76:
            return
        L77:
            r8 = move-exception
            if (r0 == 0) goto L7d
            r0.close()
        L7d:
            throw r8
        L7e:
            if (r0 == 0) goto L86
            goto L83
        L81:
            if (r0 == 0) goto L86
        L83:
            r0.close()
        L86:
            java.lang.String r8 = r9.toString()
            r7.setDataSource(r8, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.cloud.media.player.BDCloudMediaPlayer.setDataSource(android.content.Context, android.net.Uri, java.util.Map):void");
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setDataSource(String str) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        this.mDataSource = str;
        onInitingStat();
        setupPlayer();
        _setDataSource(str, null, null);
        if (isLocalFilePath(str)) {
            String string = this.appContext.getSharedPreferences("__cyberplayer_dl_sec", 0).getString(str.replace("file://", ""), null);
            if (string != null) {
                try {
                    String decryptStr = LocalHlsSec.decryptStr(this.appContext, string);
                    if (decryptStr == null || decryptStr.length() <= 0) {
                        return;
                    }
                    setLocalDecryptKeyForHLS(decryptStr);
                    return;
                } catch (Exception e) {
                    Log.d(TAG, "", e);
                    return;
                }
            }
            return;
        }
        setOption(4, "enable_cache", sEnableCache ? 1L : 0L);
        setOption(1, "cache_dir", sEnableCache ? sCacheRootPath : "");
    }

    public void setDataSource(String str, Map<String, String> map) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(": ");
                if (!TextUtils.isEmpty(entry.getValue())) {
                    sb.append(entry.getValue());
                }
                sb.append("\r\n");
            }
            this.mCustomisedHeaders = sb.toString();
            setOption(1, "headers", this.mCustomisedHeaders);
        } else {
            this.mCustomisedHeaders = null;
        }
        setDataSource(str);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    @TargetApi(13)
    public void setDataSource(FileDescriptor fileDescriptor) throws IOException, IllegalArgumentException, IllegalStateException {
        setupPlayer();
        if (Build.VERSION.SDK_INT < 12) {
            try {
                Field declaredField = fileDescriptor.getClass().getDeclaredField("descriptor");
                declaredField.setAccessible(true);
                _setDataSourceFd(declaredField.getInt(fileDescriptor));
                return;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException(e2);
            }
        }
        ParcelFileDescriptor dup = ParcelFileDescriptor.dup(fileDescriptor);
        try {
            _setDataSourceFd(dup.getFd());
        } finally {
            dup.close();
        }
    }

    private void setDataSource(FileDescriptor fileDescriptor, long j, long j2) throws IOException, IllegalArgumentException, IllegalStateException {
        setDataSource(fileDescriptor);
    }

    @Override // com.baidu.cloud.media.player.AbstractMediaPlayer, com.baidu.cloud.media.player.IMediaPlayer
    public void setDataSource(IMediaDataSource iMediaDataSource) throws IllegalArgumentException, SecurityException, IllegalStateException {
        setupPlayer();
        _setDataSource(iMediaDataSource);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public String getDataSource() {
        return this.mDataSource;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void prepareAsync() throws IllegalStateException {
        if (!LicenseManager.isValid()) {
            notifyOnError(-1000, 0);
            throw new IllegalStateException("Authentication failure, apply fo license please!");
        } else {
            _prepareAsync();
        }
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void start() throws IllegalStateException {
        stayAwake(true);
        onResumeStat();
        _start();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void stop() throws IllegalStateException {
        stayAwake(false);
        onStopStat(0, 0);
        onEndStat();
        _stop();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void pause() throws IllegalStateException {
        stayAwake(false);
        onPauseStat();
        _pause();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    @SuppressLint({"Wakelock"})
    public void setWakeMode(Context context, int i) {
        boolean z;
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (wakeLock.isHeld()) {
                z = true;
                this.mWakeLock.release();
            } else {
                z = false;
            }
            this.mWakeLock = null;
        } else {
            z = false;
        }
        this.mWakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(i | 536870912, BDCloudMediaPlayer.class.getName());
        this.mWakeLock.setReferenceCounted(false);
        if (z) {
            this.mWakeLock.acquire();
        }
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setScreenOnWhilePlaying(boolean z) {
        if (this.mScreenOnWhilePlaying != z) {
            if (z && this.mSurfaceHolder == null) {
                DebugLog.m20056w(TAG, "setScreenOnWhilePlaying(true) is ineffective without a SurfaceHolder");
            }
            this.mScreenOnWhilePlaying = z;
            updateSurfaceScreenOn();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"Wakelock"})
    public void stayAwake(boolean z) {
        PowerManager.WakeLock wakeLock = this.mWakeLock;
        if (wakeLock != null) {
            if (z && !wakeLock.isHeld()) {
                this.mWakeLock.acquire();
            } else if (!z && this.mWakeLock.isHeld()) {
                this.mWakeLock.release();
            }
        }
        this.mStayAwake = z;
        updateSurfaceScreenOn();
    }

    private void updateSurfaceScreenOn() {
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            surfaceHolder.setKeepScreenOn(this.mScreenOnWhilePlaying && this.mStayAwake);
        }
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public BDCloudTrackInfo[] getTrackInfo() {
        BDCloudMediaMeta parse;
        Bundle mediaMeta = getMediaMeta();
        if (mediaMeta == null || (parse = BDCloudMediaMeta.parse(mediaMeta)) == null || parse.mStreams == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<BDCloudMediaMeta.BDCloudStreamMeta> it = parse.mStreams.iterator();
        while (it.hasNext()) {
            BDCloudMediaMeta.BDCloudStreamMeta next = it.next();
            BDCloudTrackInfo bDCloudTrackInfo = new BDCloudTrackInfo(next);
            if (next.mType.equalsIgnoreCase("video")) {
                bDCloudTrackInfo.setTrackType(1);
            } else if (next.mType.equalsIgnoreCase("audio")) {
                bDCloudTrackInfo.setTrackType(2);
            } else if (next.mType.equalsIgnoreCase("timedtext")) {
                bDCloudTrackInfo.setTrackType(3);
            }
            arrayList.add(bDCloudTrackInfo);
        }
        return (BDCloudTrackInfo[]) arrayList.toArray(new BDCloudTrackInfo[arrayList.size()]);
    }

    public int getSelectedTrack(int i) {
        switch (i) {
            case 1:
                return (int) _getPropertyLong(20001, -1L);
            case 2:
                return (int) _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_AUDIO_STREAM, -1L);
            case 3:
                return (int) _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_SELECTED_TIMEDTEXT_STREAM, -1L);
            default:
                return -1;
        }
    }

    public void selectTrack(int i) {
        _setStreamSelected(i, true);
    }

    public void deselectTrack(int i) {
        _setStreamSelected(i, false);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public int getVideoWidth() {
        return this.mVideoWidth;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public int getVideoHeight() {
        return this.mVideoHeight;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public int getVideoSarNum() {
        return this.mVideoSarNum;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public int getVideoSarDen() {
        return this.mVideoSarDen;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void seekTo(long j) throws IllegalStateException {
        onSeekToStat(getCurrentPosition() / 1000, j / 1000);
        _seekTo(j);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void release() {
        stayAwake(false);
        onEndStat();
        updateSurfaceScreenOn();
        resetListeners();
        APMEventHandle.getInstance(this.appContext).release();
        _release();
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void reset() {
        stayAwake(false);
        onEndStat();
        _stop();
        _reset();
        this.mEventHandler.removeCallbacksAndMessages(null);
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setLooping(boolean z) {
        int i = !z ? 1 : 0;
        setOption(4, "loop", i);
        _setLoopCount(i);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public boolean isLooping() {
        return _getLoopCount() != 1;
    }

    @TargetApi(23)
    public void setSpeed(float f) {
        if (f == 0.0f) {
            return;
        }
        _setPropertyFloat(10003, f);
    }

    @TargetApi(23)
    public float getSpeed(float f) {
        return _getPropertyFloat(10003, 0.0f);
    }

    public int getVideoDecoder() {
        return (int) _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_DECODER, 0L);
    }

    public float getVideoOutputFramesPerSecond() {
        return _getPropertyFloat(10002, 0.0f);
    }

    public float getVideoDecodeFramesPerSecond() {
        return _getPropertyFloat(10001, 0.0f);
    }

    public long getVideoCachedDuration() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_DURATION, 0L);
    }

    public long getAudioCachedDuration() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_DURATION, 0L);
    }

    public long getVideoCachedBytes() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_BYTES, 0L);
    }

    public long getAudioCachedBytes() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_BYTES, 0L);
    }

    public long getVideoCachedPackets() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_VIDEO_CACHED_PACKETS, 0L);
    }

    public long getAudioCachedPackets() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_AUDIO_CACHED_PACKETS, 0L);
    }

    public long getAsyncStatisticBufBackwards() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_ASYNC_STATISTIC_BUF_BACKWARDS, 0L);
    }

    public long getAsyncStatisticBufForwards() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_ASYNC_STATISTIC_BUF_FORWARDS, 0L);
    }

    public long getAsyncStatisticBufCapacity() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_ASYNC_STATISTIC_BUF_CAPACITY, 0L);
    }

    public long getTrafficStatisticByteCount() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_TRAFFIC_STATISTIC_BYTE_COUNT, 0L);
    }

    public long getCacheStatisticPhysicalPos() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_CACHE_STATISTIC_PHYSICAL_POS, 0L);
    }

    public long getCacheStatisticBufForwards() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_CACHE_STATISTIC_FILE_FORWARDS, 0L);
    }

    public long getCacheStatisticFilePos() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_CACHE_STATISTIC_FILE_POS, 0L);
    }

    public long getCacheStatisticCountBytes() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_CACHE_STATISTIC_COUNT_BYTES, 0L);
    }

    public long getBitRate() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_BIT_RATE, 0L);
    }

    public long getDownloadSpeed() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_TCP_SPEED, 0L);
    }

    public long getSeekLoadDuration() {
        return _getPropertyLong(IjkMediaPlayer.FFP_PROP_INT64_LATEST_SEEK_LOAD_DURATION, 0L);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo = new MediaInfo();
        mediaInfo.mMediaPlayerName = "bdcloudplayer";
        String _getVideoCodecInfo = _getVideoCodecInfo();
        if (!TextUtils.isEmpty(_getVideoCodecInfo)) {
            String[] split = _getVideoCodecInfo.split(",");
            if (split.length >= 2) {
                mediaInfo.mVideoDecoder = split[0];
                mediaInfo.mVideoDecoderImpl = split[1];
            } else if (split.length >= 1) {
                mediaInfo.mVideoDecoder = split[0];
                mediaInfo.mVideoDecoderImpl = "";
            }
        }
        String _getAudioCodecInfo = _getAudioCodecInfo();
        if (!TextUtils.isEmpty(_getAudioCodecInfo)) {
            String[] split2 = _getAudioCodecInfo.split(",");
            if (split2.length >= 2) {
                mediaInfo.mAudioDecoder = split2[0];
                mediaInfo.mAudioDecoderImpl = split2[1];
            } else if (split2.length >= 1) {
                mediaInfo.mAudioDecoder = split2[0];
                mediaInfo.mAudioDecoderImpl = "";
            }
        }
        try {
            mediaInfo.mMeta = BDCloudMediaMeta.parse(_getMediaMeta());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return mediaInfo;
    }

    public void setMediaInputType(int i) {
        switch (i) {
            case 1:
                setOption(1, "adaptive_demuxer", "adapt_media");
                setOption(1, "get_master_plist", 0L);
                setOption(1, "smooth_switch_seek_flag", 0L);
                return;
            case 2:
                setOption(1, "adaptive_demuxer", "adapt_media");
                setOption(1, "get_master_plist", 1L);
                return;
            default:
                setOption(1, "adaptive_demuxer", (String) null);
                return;
        }
    }

    public void setPlayType(int i) {
        this.mPlayType = i;
        if (this.mPlayType == 1) {
            setOption(1, "livehook_demuxer", "ijklivehook");
        } else {
            setOption(1, "livehook_demuxer", (String) null);
        }
    }

    public boolean selectResolutionByIndex(int i) {
        if (getCurrentVariantIndex() == i) {
            String str = TAG;
            Log.d(str, "currentVariantIndex is equals to index setted~" + i);
            return false;
        } else if (i < 0 || i >= getVariantInfo().length) {
            String str2 = TAG;
            Log.d(str2, "index is not in [0," + getVariantInfo().length + ")");
            return false;
        } else {
            long currentPosition = getCurrentPosition();
            stop();
            native_setup();
            setOption(1, "reconnect", 1L);
            setOption(1, "timeout", this.mNetworkTimeoutInUs);
            setOption(1, "cache_dir", sEnableCache ? sCacheRootPath : "");
            if (!TextUtils.isEmpty(this.mCustomisedHeaders)) {
                setOption(1, "headers", this.mCustomisedHeaders);
            }
            selectVariantByIndex(i);
            setInitPlayPosition(currentPosition);
            prepareAsync();
            return true;
        }
    }

    public boolean selectMediaByIndex(int i) {
        if (getCurrentMediaIndex() == i) {
            String str = TAG;
            Log.d(str, "Is the same index :" + i);
            return false;
        } else if (i < 0 || i >= getMediaItems().length) {
            String str2 = TAG;
            Log.d(str2, "index is not in [0," + getMediaItems().length + ")");
            return false;
        } else {
            return setMediaItemIndex(i);
        }
    }

    public void setInitPlayPosition(long j) {
        setOption(4, "seek-at-start", j);
    }

    public void setDecodeMode(int i) {
        if (i >= 0 && i <= 1) {
            this.decodeMode = i;
            if (this.decodeMode == 0) {
                setOption(4, "mediacodec", 1L);
                setOption(4, "mediacodec-all-videos", 1L);
                setOption(4, "mediacodec-auto-rotate", 1L);
                setOption(4, "mediacodec-handle-resolution-change", 1L);
                return;
            }
            setOption(4, "mediacodec", 0L);
            setOption(4, "mediacodec-all-videos", 0L);
            setOption(4, "mediacodec-auto-rotate", 0L);
            setOption(4, "mediacodec-handle-resolution-change", 0L);
            return;
        }
        DebugLog.m20062e(TAG, "decodeMode shoule be DECODE_AUTO or DECODE_SW");
    }

    public int getDecodeMode() {
        return this.decodeMode;
    }

    public void setMaxCacheDuration(int i) {
        setOption(4, "max-buffer-duration", i);
    }

    public void setMaxCacheSizeInBytes(int i) {
        this.maxCacheSizeForApm = i;
        setOption(4, "max-buffer-size", i);
    }

    public void setBufferTimeInMs(int i) {
        this.cachePauseTimeForApm = i;
        setOption(4, "buffer-time-in-ms", i);
    }

    public void setBufferSizeInBytes(int i) {
        setOption(4, "buffer-size-in-bytes", i);
    }

    public void setMaxProbeTime(int i) {
        this.firstBufferingTimeForApm = i;
        setOption(4, "max-probe-time", i);
    }

    public void setMaxProbeSize(int i) {
        setOption(4, "max-probe-size", i);
    }

    public void setTimeoutInUs(int i) {
        if (i <= 0) {
            i = this.mNetworkTimeoutInUs;
        }
        this.mNetworkTimeoutInUs = i;
        setOption(1, "timeout", this.mNetworkTimeoutInUs);
    }

    public void toggleFrameChasing(boolean z) {
        this.toggleFrameChasingForApm = z;
        setOption(4, "framechasing", z ? 1L : 0L);
    }

    @Override // com.baidu.cloud.media.player.IMediaPlayer
    public void setLogEnabled(boolean z) {
        if (z) {
            native_setLogLevel(1);
            DebugLog.setDebugLogEnabled(true);
            return;
        }
        native_setLogLevel(5);
        DebugLog.setDebugLogEnabled(false);
    }

    public void setOption(int i, String str, String str2) {
        _setOption(i, str, str2);
    }

    public void setOption(int i, String str, long j) {
        _setOption(i, str, j);
    }

    public Bundle getMediaMeta() {
        return _getMediaMeta();
    }

    public static String getColorFormatName(int i) {
        return _getColorFormatName(i);
    }

    protected void finalize() throws Throwable {
        super.finalize();
        native_finalize();
    }

    protected void onErrorStat(int i, int i2) {
        try {
            APMEventHandle.getInstance(this.appContext).onPlayEnd(i, i2, 1);
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "APMEventHandle exception:" + e.getMessage());
        }
    }

    protected void onCompleteStat(int i, int i2) {
        APMEventHandle.getInstance(this.appContext).onPlayEnd(i, i2, 2);
    }

    protected void onStopStat(int i, int i2) {
        APMEventHandle.getInstance(this.appContext).onPlayEnd(i, i2, 3);
    }

    protected void onEndStat() {
        String str;
        if (this.isEndSended) {
            return;
        }
        this.isEndSended = true;
        try {
            if (this.bufferStatJsonArray != null) {
                JSONArray jSONArray = this.bufferStatJsonArray;
                str = !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
                this.bufferStatJsonArray = new JSONArray();
            } else {
                str = null;
            }
            if (this.apmPlayingTimer != null) {
                this.apmPlayingTimer.cancel();
                this.apmPlayingTimer = null;
            }
            if (this.lastStartPlayTime != 0) {
                this.lastStartPlayTime = 0L;
                this.stayInterval += ((int) (System.currentTimeMillis() - this.lastStartPlayTime)) / 1000;
            }
            int i = this.stayInterval;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("playInterval", i);
            jSONObject.put("buffering", new JSONArray(str));
            APMEventHandle.getInstance(this.appContext).onUserPlayEnd(jSONObject);
            this.stayInterval = 0;
        } catch (Exception e) {
            Log.d(TAG, "APMEventHandle exception:" + e.getMessage());
        }
    }

    protected void onInitingStat() {
        this.lastStartPlayTime = System.currentTimeMillis();
        this.startPrepareTimeForApm = System.currentTimeMillis();
        APMEventHandle.getInstance(this.appContext).updateSession(this.mDataSource);
        APMEventHandle.getInstance(this.appContext).setPlayerRelated("2.3.6", "hw", mAK);
    }

    protected void onDnsStat(int i) {
        APMEventHandle.getInstance(this.appContext).onDnsParsed(i);
    }

    protected void onHttpStat(long j) {
        APMEventHandle.getInstance(this.appContext).onHttpConnected(j / 1000);
    }

    protected void onPauseStat() {
        if (this.lastStartPlayTime != 0) {
            this.lastStartPlayTime = 0L;
            this.stayInterval += ((int) (System.currentTimeMillis() - this.lastStartPlayTime)) / 1000;
        }
        try {
            if (this.apmPlayingTimer != null) {
                this.apmPlayingTimer.cancel();
                this.apmPlayingTimer = null;
            }
            APMEventHandle.getInstance(this.appContext).onUserPause(((float) getCurrentPosition()) / 1000.0f);
        } catch (Exception e) {
            Log.d(TAG, "APMEventHandle exception:" + e.getMessage());
        }
    }

    protected void onResumeStat() {
        this.isEndSended = false;
        if (this.lastStartPlayTime == 0) {
            this.lastStartPlayTime = System.currentTimeMillis();
        }
        try {
            if (this.apmPlayingTimer != null) {
                this.apmPlayingTimer.cancel();
                this.apmPlayingTimer = null;
            }
            this.apmPlayingTimer = new Timer();
            this.apmPlayingTimer.schedule(new TimerTask() { // from class: com.baidu.cloud.media.player.BDCloudMediaPlayer.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    try {
                        APMEventHandle.getInstance(BDCloudMediaPlayer.this.appContext).onPlayCount();
                        long downloadSpeed = BDCloudMediaPlayer.this.getDownloadSpeed();
                        if (downloadSpeed > 0) {
                            APMEventHandle.getInstance(BDCloudMediaPlayer.this.appContext).onNetworkSpeedReport((int) downloadSpeed);
                        }
                    } catch (Exception e) {
                        String str = BDCloudMediaPlayer.TAG;
                        Log.d(str, "APMEventHandle exception:" + e.getMessage());
                    }
                }
            }, 0L, 60000L);
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "" + e.getMessage());
        }
        if (this.mApmEventPlayerCreated) {
            APMEventHandle.getInstance(this.appContext).onPlayerCreated();
            this.mApmEventPlayerCreated = false;
        }
    }

    protected void onSeekToStat(long j, long j2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("from", j);
            jSONObject.put("to", j2);
            APMEventHandle.getInstance(this.appContext).onUserSeek(jSONObject);
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "APMEventHandle exception:" + e.getMessage());
        }
    }

    protected void onPreparedStat() {
        try {
            if (this.startPrepareTimeForApm > 0) {
                APMEventHandle.getInstance(this.appContext).onFirstBufferEnd(System.currentTimeMillis() - this.startPrepareTimeForApm);
                this.startPrepareTimeForApm = 0L;
            }
            String cdnIp = getCdnIp();
            if (cdnIp != null && !cdnIp.equals("")) {
                APMEventHandle.getInstance(this.appContext).onUpdateCdn(cdnIp);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("videoUrl", this.mDataSource);
            jSONObject.put("videoWidth", getVideoWidth());
            jSONObject.put("videoHeight", getVideoHeight());
            jSONObject.put("playerWidth", 0);
            jSONObject.put("playerHeight", 0);
            jSONObject.put("duration", getDuration());
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("maxCacheSize", this.maxCacheSizeForApm);
            jSONObject2.put("cachePauseTime", this.cachePauseTimeForApm);
            jSONObject2.put("firstBufferingTime", this.firstBufferingTimeForApm);
            jSONObject2.put("toggleFrameChasing", this.toggleFrameChasingForApm);
            jSONObject2.put("decodeMode", this.decodeMode);
            jSONObject2.put("playbackRate", getSpeed(0.0f));
            jSONObject2.put("enableLooping", isLooping());
            jSONObject.put("settings", jSONObject2);
            APMEventHandle.getInstance(this.appContext).onUserPlay(jSONObject);
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "APMEventHandle exception:" + e.getMessage());
        }
    }

    protected void onMetadataStat(String str) {
        APMEventHandle.getInstance(this.appContext).onUpdateMetadata(str);
    }

    protected void onInfoStat(int i, int i2) {
        try {
            if (i == 701) {
                this.latestBufferStartTime = new Date();
                this.bufferingStartPosition = getCurrentPosition();
                APMEventHandle.getInstance(this.appContext).onBufferingStart();
            } else if (i == 702) {
                APMEventHandle.getInstance(this.appContext).onBufferingEnd();
                if (this.latestBufferStartTime != null) {
                    Date date = new Date();
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("startPosition", ((float) this.bufferingStartPosition) / 1000.0f);
                    jSONObject.put("endPosition", ((float) getCurrentPosition()) / 1000.0f);
                    jSONObject.put("startTimestamp", this.latestBufferStartTime.getTime());
                    jSONObject.put("endTimestamp", date.getTime());
                    this.bufferStatJsonArray.put(jSONObject);
                    this.latestBufferStartTime = null;
                    this.bufferingStartPosition = -1L;
                }
            } else if (i == 3) {
                APMEventHandle.getInstance(this.appContext).onFirstFrameRendered(this.lastStartPlayTime);
            } else if (i == 10003) {
                APMEventHandle.getInstance(this.appContext).onFrameChasingStart();
            } else if (i != 10004) {
            } else {
                APMEventHandle.getInstance(this.appContext).onFrameCharingEnd();
            }
        } catch (Exception e) {
            String str = TAG;
            Log.d(str, "buffer stat exception :" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class EventHandler extends Handler {
        private final WeakReference<BDCloudMediaPlayer> mWeakPlayer;

        public EventHandler(BDCloudMediaPlayer bDCloudMediaPlayer, Looper looper) {
            super(looper);
            this.mWeakPlayer = new WeakReference<>(bDCloudMediaPlayer);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            BDCloudMediaPlayer bDCloudMediaPlayer = this.mWeakPlayer.get();
            if (bDCloudMediaPlayer != null) {
                long j = 0;
                if (bDCloudMediaPlayer.mNativeMediaPlayer != 0) {
                    int i = message.what;
                    if (i == 200) {
                        int i2 = message.arg1;
                        if (i2 != 3) {
                            switch (i2) {
                                case 10005:
                                    DebugLog.m20060i(BDCloudMediaPlayer.TAG, "Info: MEDIA_INFO_MEDIA_CHANGE_START\n");
                                    break;
                                case 10006:
                                    DebugLog.m20060i(BDCloudMediaPlayer.TAG, "Info: MEDIA_INFO_MEDIA_CHANGE_END : " + message.arg1 + "\n");
                                    break;
                            }
                        } else {
                            DebugLog.m20060i(BDCloudMediaPlayer.TAG, "Info: MEDIA_INFO_VIDEO_RENDERING_START\n");
                        }
                        bDCloudMediaPlayer.onInfoStat(message.arg1, message.arg2);
                        bDCloudMediaPlayer.notifyOnInfo(message.arg1, message.arg2);
                        return;
                    } else if (i == 10001) {
                        bDCloudMediaPlayer.mVideoSarNum = message.arg1;
                        bDCloudMediaPlayer.mVideoSarDen = message.arg2;
                        bDCloudMediaPlayer.notifyOnVideoSizeChanged(bDCloudMediaPlayer.mVideoWidth, bDCloudMediaPlayer.mVideoHeight, bDCloudMediaPlayer.mVideoSarNum, bDCloudMediaPlayer.mVideoSarDen);
                        return;
                    } else if (i == 77824) {
                        Bundle bundle = (Bundle) message.obj;
                        bDCloudMediaPlayer.notifyOnMetadata(bundle);
                        for (String str : bundle.keySet()) {
                            if ("playID".equals(str) && !"".equals(bundle.get(str))) {
                                bDCloudMediaPlayer.onMetadataStat(bundle.getString(str));
                                String unused = BDCloudMediaPlayer.playId = bundle.getString(str);
                            }
                        }
                        return;
                    } else if (i == 81920) {
                        bDCloudMediaPlayer.onDnsStat(message.arg1);
                        return;
                    } else if (i != 196610) {
                        switch (i) {
                            case 0:
                                return;
                            case 1:
                                bDCloudMediaPlayer.setOption(4, "seek-at-start", 0L);
                                bDCloudMediaPlayer.onPreparedStat();
                                bDCloudMediaPlayer.notifyOnPrepared();
                                return;
                            case 2:
                                return;
                            case 3:
                                bDCloudMediaPlayer.stayAwake(false);
                                bDCloudMediaPlayer.onCompleteStat(message.arg1, message.arg2);
                                bDCloudMediaPlayer.onEndStat();
                                bDCloudMediaPlayer.notifyOnCompletion();
                                return;
                            case 4:
                                long j2 = message.arg1;
                                if (j2 < 0) {
                                    j2 = 0;
                                }
                                long duration = bDCloudMediaPlayer.getDuration();
                                if (duration > 0) {
                                    if (duration - j2 >= 1000) {
                                        j = (j2 * 100) / duration;
                                    } else {
                                        j = ((j2 + 1000) * 100) / duration;
                                    }
                                }
                                if (j >= 100) {
                                    j = 100;
                                }
                                bDCloudMediaPlayer.notifyOnBufferingUpdate((int) j);
                                return;
                            case 5:
                                bDCloudMediaPlayer.notifyOnSeekComplete();
                                return;
                            case 6:
                                bDCloudMediaPlayer.mVideoWidth = message.arg1;
                                bDCloudMediaPlayer.mVideoHeight = message.arg2;
                                bDCloudMediaPlayer.notifyOnVideoSizeChanged(bDCloudMediaPlayer.mVideoWidth, bDCloudMediaPlayer.mVideoHeight, bDCloudMediaPlayer.mVideoSarNum, bDCloudMediaPlayer.mVideoSarDen);
                                return;
                            default:
                                switch (i) {
                                    case 99:
                                        if (message.obj == null) {
                                            bDCloudMediaPlayer.notifyOnTimedText(null);
                                            return;
                                        } else {
                                            bDCloudMediaPlayer.notifyOnTimedText(new BDTimedText(new Rect(0, 0, 1, 1), (String) message.obj));
                                            return;
                                        }
                                    case 100:
                                        DebugLog.m20062e(BDCloudMediaPlayer.TAG, "Error (" + message.arg1 + "," + message.arg2 + ")");
                                        bDCloudMediaPlayer.onErrorStat(message.arg1, message.arg2);
                                        bDCloudMediaPlayer.onEndStat();
                                        if (!bDCloudMediaPlayer.notifyOnError(message.arg1, message.arg2)) {
                                            bDCloudMediaPlayer.notifyOnCompletion();
                                        }
                                        bDCloudMediaPlayer.stayAwake(false);
                                        return;
                                    default:
                                        DebugLog.m20062e(BDCloudMediaPlayer.TAG, "Unknown message type " + message.what);
                                        return;
                                }
                        }
                    } else {
                        bDCloudMediaPlayer.onHttpStat(message.arg1);
                        return;
                    }
                }
            }
            DebugLog.m20056w(BDCloudMediaPlayer.TAG, "BDCloudMediaPlayer went away with unhandled events");
        }
    }

    @CalledByNative
    private static void postEventFromNative(Object obj, int i, int i2, int i3, Object obj2) {
        BDCloudMediaPlayer bDCloudMediaPlayer;
        if (obj == null || (bDCloudMediaPlayer = (BDCloudMediaPlayer) ((WeakReference) obj).get()) == null) {
            return;
        }
        if (i == 200 && i2 == 2) {
            bDCloudMediaPlayer.start();
        }
        EventHandler eventHandler = bDCloudMediaPlayer.mEventHandler;
        if (eventHandler != null) {
            bDCloudMediaPlayer.mEventHandler.sendMessage(eventHandler.obtainMessage(i, i2, i3, obj2));
        }
    }

    private void setOnControlMessageListener(OnControlMessageListener onControlMessageListener) {
        this.mOnControlMessageListener = onControlMessageListener;
    }

    private void setOnNativeInvokeListener(OnNativeInvokeListener onNativeInvokeListener) {
        this.mOnNativeInvokeListener = onNativeInvokeListener;
    }

    @CalledByNative
    private static boolean onNativeInvoke(Object obj, int i, Bundle bundle) {
        OnControlMessageListener onControlMessageListener;
        DebugLog.ifmt(TAG, "onNativeInvoke %x", Integer.valueOf(i));
        if (obj == null || !(obj instanceof WeakReference)) {
            throw new IllegalStateException("<null weakThiz>.onNativeInvoke()");
        }
        BDCloudMediaPlayer bDCloudMediaPlayer = (BDCloudMediaPlayer) ((WeakReference) obj).get();
        if (bDCloudMediaPlayer == null) {
            throw new IllegalStateException("<null weakPlayer>.onNativeInvoke()");
        }
        OnNativeInvokeListener onNativeInvokeListener = bDCloudMediaPlayer.mOnNativeInvokeListener;
        if (onNativeInvokeListener == null || !onNativeInvokeListener.onNativeInvoke(i, bundle)) {
            if (i == 131079 && (onControlMessageListener = bDCloudMediaPlayer.mOnControlMessageListener) != null) {
                int i2 = bundle.getInt("segment_index", -1);
                if (i2 < 0) {
                    throw new InvalidParameterException("onNativeInvoke(invalid segment index)");
                }
                String onControlResolveSegmentUrl = onControlMessageListener.onControlResolveSegmentUrl(i2);
                if (onControlResolveSegmentUrl == null) {
                    throw new RuntimeException(new IOException("onNativeInvoke() = <NULL newUrl>"));
                }
                bundle.putString("url", onControlResolveSegmentUrl);
                return true;
            }
            return false;
        }
        return true;
    }

    private void setOnMediaCodecSelectListener(OnMediaCodecSelectListener onMediaCodecSelectListener) {
        this.mOnMediaCodecSelectListener = onMediaCodecSelectListener;
    }

    @Override // com.baidu.cloud.media.player.AbstractMediaPlayer
    public void resetListeners() {
        super.resetListeners();
        this.mOnMediaCodecSelectListener = null;
    }

    private boolean isLocalFilePath(String str) {
        if (str == null) {
            return false;
        }
        return str.startsWith("file://") || str.startsWith("/");
    }

    @CalledByNative
    private static String onSelectCodec(Object obj, String str, int i, int i2) {
        BDCloudMediaPlayer bDCloudMediaPlayer;
        if (obj == null || !(obj instanceof WeakReference) || (bDCloudMediaPlayer = (BDCloudMediaPlayer) ((WeakReference) obj).get()) == null) {
            return null;
        }
        OnMediaCodecSelectListener onMediaCodecSelectListener = bDCloudMediaPlayer.mOnMediaCodecSelectListener;
        if (onMediaCodecSelectListener == null) {
            onMediaCodecSelectListener = DefaultMediaCodecSelector.sInstance;
        }
        return onMediaCodecSelectListener.onMediaCodecSelect(bDCloudMediaPlayer, str, i, i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class DefaultMediaCodecSelector implements OnMediaCodecSelectListener {
        public static final DefaultMediaCodecSelector sInstance = new DefaultMediaCodecSelector();

        private DefaultMediaCodecSelector() {
        }

        @Override // com.baidu.cloud.media.player.BDCloudMediaPlayer.OnMediaCodecSelectListener
        @TargetApi(16)
        public String onMediaCodecSelect(IMediaPlayer iMediaPlayer, String str, int i, int i2) {
            String[] supportedTypes;
            BDCloudMediaCodecInfo bDCloudMediaCodecInfo;
            if (Build.VERSION.SDK_INT >= 16 && !TextUtils.isEmpty(str)) {
                int i3 = 2;
                DebugLog.m20060i(BDCloudMediaPlayer.TAG, String.format(Locale.US, "onSelectCodec: mime=%s, profile=%d, level=%d", str, Integer.valueOf(i), Integer.valueOf(i2)));
                ArrayList arrayList = new ArrayList();
                int codecCount = MediaCodecList.getCodecCount();
                int i4 = 0;
                while (i4 < codecCount) {
                    MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i4);
                    DebugLog.m20064d(BDCloudMediaPlayer.TAG, String.format(Locale.US, "  found codec: %s", codecInfoAt.getName()));
                    if (!codecInfoAt.isEncoder() && (supportedTypes = codecInfoAt.getSupportedTypes()) != null) {
                        int length = supportedTypes.length;
                        int i5 = 0;
                        while (i5 < length) {
                            String str2 = supportedTypes[i5];
                            if (!TextUtils.isEmpty(str2)) {
                                DebugLog.m20064d(BDCloudMediaPlayer.TAG, String.format(Locale.US, "    mime: %s", str2));
                                if (str2.equalsIgnoreCase(str) && (bDCloudMediaCodecInfo = BDCloudMediaCodecInfo.setupCandidate(codecInfoAt, str)) != null) {
                                    arrayList.add(bDCloudMediaCodecInfo);
                                    String str3 = BDCloudMediaPlayer.TAG;
                                    Locale locale = Locale.US;
                                    Object[] objArr = new Object[i3];
                                    objArr[0] = codecInfoAt.getName();
                                    objArr[1] = Integer.valueOf(bDCloudMediaCodecInfo.mRank);
                                    DebugLog.m20060i(str3, String.format(locale, "candidate codec: %s rank=%d", objArr));
                                    bDCloudMediaCodecInfo.dumpProfileLevels(str);
                                }
                            }
                            i5++;
                            i3 = 2;
                        }
                    }
                    i4++;
                    i3 = 2;
                }
                if (arrayList.isEmpty()) {
                    return null;
                }
                BDCloudMediaCodecInfo bDCloudMediaCodecInfo2 = (BDCloudMediaCodecInfo) arrayList.get(0);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    BDCloudMediaCodecInfo bDCloudMediaCodecInfo3 = (BDCloudMediaCodecInfo) it.next();
                    if (bDCloudMediaCodecInfo3.mRank > bDCloudMediaCodecInfo2.mRank) {
                        bDCloudMediaCodecInfo2 = bDCloudMediaCodecInfo3;
                    }
                }
                if (bDCloudMediaCodecInfo2.mRank < 600) {
                    Log.w(BDCloudMediaPlayer.TAG, String.format(Locale.US, "unaccetable codec: %s", bDCloudMediaCodecInfo2.mCodecInfo.getName()));
                    return null;
                }
                DebugLog.m20060i(BDCloudMediaPlayer.TAG, String.format(Locale.US, "selected codec: %s rank=%d", bDCloudMediaCodecInfo2.mCodecInfo.getName(), Integer.valueOf(bDCloudMediaCodecInfo2.mRank)));
                return bDCloudMediaCodecInfo2.mCodecInfo.getName();
            }
            return null;
        }
    }
}
