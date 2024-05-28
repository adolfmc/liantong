package com.sinovatech.unicom.separatemodule.live.config;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class Constraints {
    public static final String DEFAULT_CONVERSATION_HOST = "ws://smartvideo.exp.bcelive.com:9999/call";
    public static final String DEFAULT_LIVE_APP = "test";
    public static final String DEFAULT_LIVE_PUSH_RTMP_PREFIX = "rtmp://";
    public static final int DEFAULT_LIVE_VIDEO_BITRATE = 1000000;
    public static final int DEFAULT_LIVE_VIDEO_FPS = 18;
    public static final int DEFAULT_LIVE_VIDEO_HEIGHT = 960;
    public static final int DEFAULT_LIVE_VIDEO_MAX_BITRATE = 1500000;
    public static final int DEFAULT_LIVE_VIDEO_MIN_BITRATE = 300000;
    public static final int DEFAULT_LIVE_VIDEO_WIDTH = 544;
    public static final String DEFAULT_PLAY_HTTP_PREFIX = "http://";
    public static final String DEFAULT_PLAY_RTMP_PREFIX = "rtmp://";
    public static String DEFAULT_RTC_APPID = "appkemim3uui3ja";
    public static String DEFAULT_RTC_MEDIA_SERVER = "wss://rtc2.exp.bcelive.com/janus";
    public static final String DEFAULT_RTC_MIX_TEMPLATE = "custom_layout-v:720p_4_3";
    public static final String DEFAULT_RTC_MIX_TEMPLATE_MORE = "-ar:44100-wd:{1:s:r_x:0_y:0_w:480_h:720,2:s:r_x:480_y:0_w:480_h:720}";
    public static String DEFAULT_RTC_SIGNALLING_SERVER = "wss://rtc.exp.bcelive.com:8989/janus";
    public static String DEFAULT_RTC_TOKEN = "";
    public static String DEFAULT_RTC_VIDEO_RESOLUTION = "544x960-1000kbps";
    public static final int MODE_LIVE_AR = 105;
    public static final int MODE_LIVE_COMMON = 100;
    public static final int MODE_LIVE_INTERACT = 102;
    public static final int MODE_LIVE_INTERACT_TRANSFORM = 103;
    public static final int MODE_LIVE_INTERACT_TRANSFORM_AUDIO = 104;
    public static final int MODE_LIVE_MAKEUP = 101;
    public static final int MODE_PLAY_COMMON = 1001;
    public static final int MODE_PLAY_INTERACT = 1003;
    public static final int MODE_PLAY_INTERACT_AUDIO = 1004;
    public static final int MODE_PLAY_MAKEUP = 1002;
    public static final int MSG_BGM_FINISHED = 3;
    public static final int MSG_FAILED = 1;
    public static final int MSG_FAILED_ARG1_REASON_AUDIO_MIC = 16;
    public static final int MSG_FAILED_ARG1_REASON_BGM_DEVICE = 111;
    public static final int MSG_FAILED_ARG1_REASON_CAMERA = 15;
    public static final int MSG_FAILED_ARG1_REASON_DEVICE = 11;
    public static final int MSG_FAILED_ARG1_REASON_ENCODER = 13;
    public static final int MSG_FAILED_ARG1_REASON_FILTER = 12;
    public static final int MSG_FAILED_ARG1_REASON_MUXER = 14;
    public static final int MSG_SUCCESS = 2;
    public static final int MSG_TO_PAUSE = 42;
    public static final int MSG_TO_RELEASE = 45;
    public static final int MSG_TO_RESUME = 43;
    public static final int MSG_TO_START = 41;
    public static final int MSG_TO_STOP = 44;
    public static final int PLAY_MEDIA_FORMAT_FLV = 0;
    public static final int PLAY_MEDIA_FORMAT_HLS = 1;
    public static final int PLAY_MEDIA_FORMAT_RTMP = 2;
    public static final int RECONNECTION_INTERVAL_IN_MS = 2000;
    public static final String ROOM_NAME = "999";
}
