package com.baidu.rtc;

import android.content.Intent;
import com.baidu.rtc.config.BRTCScreenShareParams;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcParameterSettings {
    public Intent screenIntentData;
    public BRTCScreenShareParams screenShareParams;
    public Map<String, RtcVideoEncodeParams> videoEncodeParams;
    public boolean HasVideo = true;
    public boolean HasAudio = true;
    public boolean HasScreen = false;
    public boolean HasData = false;
    public String VideoCodec = "h264";
    public String AudioCodec = "opus";
    public int AudioFrequency = 48000;
    public int outputAudioChannel = 1;
    public int inputAudioChannel = 1;
    public String VideoResolution = "640x480";
    public int VideoWidth = 640;
    public int VideoHeight = 480;
    public int VideoFps = 20;
    public int VideoMaxkbps = 1000;
    public int VideoMinkbps = 1000;
    public int AudioMaxkbps = -1;
    public RtcAudioBitrateMode audioBitrateMode = RtcAudioBitrateMode.RTC_AUDIO_BITRATE_CBR;
    public RtcAudioChannel transportAudioChannel = RtcAudioChannel.RTC_AUDIO_MONO;
    public int AudioSource = 7;
    public int AudioBufferPackets = 50;
    public int AudioCodecComplex = -1;
    public int AudioPlayoutDelay = -1;
    public int ConnectionTimeoutMs = 5000;
    public int ReadTimeoutMs = 5000;
    public boolean AutoPublish = true;
    public boolean AutoSubScribe = true;
    public int audioContentType = 1;
    public RtcVideoRenderMode VideoRenderMode = RtcVideoRenderMode.RTC_VIDEO_RENDER_MODE_INTERNAL;
    public boolean MicPhoneMuted = false;
    public boolean CameraMuted = false;
    public boolean EnableFixedResolution = false;
    public boolean EnableRequiredResolutionAligment32 = false;
    public boolean DisableExtraCamera = true;
    public boolean EnableHighProfile = false;
    public boolean DisableBuiltInAEC = false;
    public boolean EnableHisiH264HW = true;
    public boolean EnableMTKH264Decode = true;
    public boolean EnableAacCodec = false;
    public boolean enableJitterRetransmission = false;
    public String MediaServerIP = "";
    public RtcSignalChannelMode signalChannelMode = RtcSignalChannelMode.RTC_SIGNAL_CHANNEL_MODE_TCP;
    public String kcpServerDomain = "";
    public int kcpServerPort = 0;
    public boolean kcpEnableCrypto = true;
    public int EncodeBitrateMode = RtcVideoBitrateMode.RTC_VIDEO_CONTROLRATECONSTANT.ordinal();
    public boolean EnableAudioLevel = true;
    public int AudioLevelTopCount = 3;
    public boolean EnableMultistream = true;
    public boolean forceSoftwareEncoder = false;
    public boolean forceSoftwareDecoder = false;
    public boolean enableLowLatencyMode = false;
    public boolean disableDropFrame = false;
    public int weakNetworkPolicy = RtcWeakNetworkPolicy.RTC_WEAK_NETWORK_POLICY_ADAPTIVE.ordinal();
    public boolean enableReportAudioLevel = true;
    public boolean disableBluetoothSocMode = false;
    public boolean enableEncrypt = true;
    @Deprecated
    public boolean enableBeauty = false;
    public boolean HasRemoteVideo = true;
    public boolean HasRemoteAudio = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class AudioCodecId {
        public static final String G722 = "g722";
        public static final String ISCA16 = "isac16";
        public static final String ISCA32 = "isac32";
        public static final String OPUS = "opus";
        public static final String PCMA = "pcma";
        public static final String PCMU = "pcmu";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcAudioBitrateMode {
        RTC_AUDIO_BITRATE_CBR,
        RTC_AUDIO_BITRATE_VBR
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcAudioChannel {
        RTC_AUDIO_MONO,
        RTC_AUDIO_STEREO
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface RtcMediaTarget {
        public static final String TARGET_AUDIO_DEFAULT = "audio";
        public static final String TARGET_VIDEO_DEFAULT = "video";
        public static final String TARGET_VIDEO_SCREEN = "video_screen";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcParamSettingType {
        RTC_AUDIO_PARAM_SETTINGS_ONLY_AUDIO,
        RTC_AUDIO_PARAM_SETTINGS_AEC_DUMP,
        RTC_AUDIO_PARAM_SETTINGS_LEVEL_CONTROL,
        RTC_AUDIO_PARAM_SETTINGS_MANUAL_CONFIG,
        RTC_AUDIO_PARAM_SETTINGS_EXPORT_RECORD,
        RTC_AUDIO_PARAM_SETTINGS_EXPORT_PLAYOUT,
        RTC_AUDIO_PARAM_SETTINGS_EXPORT_RECORD_PLAYOUT_MIX,
        RTC_VIDEO_PARAM_SETTINGS_FPS,
        RTC_VIDEO_PARAM_SETTINGS_RESOLUTION,
        RTC_VIDEO_PARAM_SETTINGS_BITRATE,
        RTC_VIDEO_PARAM_SETTINGS_CODECTYPE,
        RTC_VIDEO_PARAM_SETTINGS_SESSION_MODE,
        RTC_VIDEO_PARAM_SETTINGS_CAPTURE_MODE,
        RTC_VIDEO_PARAM_SETTINGS_RENDER_MODE,
        RTC_PARAM_SETTINGS_ALL,
        RTC_PARAM_SETTINGS_OTHERS,
        RTC_PARAM_SETTINGS_SHARE_SCREEN
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcSignalChannelMode {
        RTC_SIGNAL_CHANNEL_MODE_TCP,
        RTC_SIGNAL_CHANNEL_MODE_KCP
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcVideoBitrateMode {
        RTC_VIDEO_CONTROLRATEDISABLE,
        RTC_VIDEO_CONTROLRATEVARIABLE,
        RTC_VIDEO_CONTROLRATECONSTANT
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RtcVideoEncodeParams {
        public String videoResolution = "640x480";
        public int videoWidth = 640;
        public int videoHeight = 480;
        public int videoFps = 20;
        public int videoMaxkbps = 1000;
        public int videoMinkbps = 1000;
        public int encodeBitrateMode = RtcVideoBitrateMode.RTC_VIDEO_CONTROLRATECONSTANT.ordinal();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcVideoRenderMode {
        RTC_VIDEO_RENDER_MODE_INTERNAL,
        RTC_VIDEO_RENDER_MODE_EXTERNAL
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcWeakNetworkPolicy {
        RTC_WEAK_NETWORK_POLICY_ADAPTIVE,
        RTC_WEAK_NETWORK_POLICY_GROWTH,
        RTC_WEAK_NETWORK_POLICY_HOLDING
    }

    public static RtcParameterSettings getDefaultSettings() {
        return new RtcParameterSettings();
    }
}
