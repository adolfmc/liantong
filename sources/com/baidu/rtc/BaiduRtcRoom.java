package com.baidu.rtc;

import android.content.Context;
import android.view.Surface;
import com.baidu.cloud.rtcbridge.frameprocessor.BRTCEffectParams;
import com.baidu.cloud.rtcbridge.frameprocessor.BRTCWatermarkParams;
import com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager;
import com.baidu.cloud.rtcbridge.frameprocessor.IRtcFrameProcessorManager;
import com.baidu.rtc.CommonDefine;
import com.baidu.rtc.RTCAudioSamples;
import com.baidu.rtc.RtcParameterSettings;
import com.baidu.rtc.audio.BRTCAudioProfileType;
import com.baidu.rtc.audio.BRTCAudioScenario;
import com.baidu.rtc.config.BRTCScreenShareParams;
import com.baidu.rtc.config.Constraints;
import com.baidu.rtc.internal.BaiduRtcRoomImp;
import com.baidu.rtc.logreport.HUDStatistics;
import com.baidu.rtc.logreport.RtcLogCapturer;
import com.baidu.rtc.logreport.RtcLogEvent;
import com.baidu.rtc.recorder.IRtcMediaRecorder;
import com.baidu.rtc.utils.BRtcPhoneStateManager;
import com.webrtc.Loggable;
import com.webrtc.Logging;
import com.webrtc.VideoCapturer;
import com.webrtc.VideoProcessor;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BaiduRtcRoom {
    private static ArrayList<BaiduRtcRoomImp> mInstanceList = new ArrayList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface BaiduRtcRoomDelegate {
        public static final int RTC_ROOM_CAPTURE_AUDIO_ERROR = 40000;
        public static final int RTC_ROOM_CAPTURE_SCREEN_ON_STOP = 50000;
        public static final int RTC_ROOM_CONNECTION_CLOSED = 20000;
        public static final int RTC_ROOM_CONNECTION_CLOSING = 20001;
        public static final int RTC_ROOM_EVENTS_DISBAND_ROOM = 112;
        public static final int RTC_ROOM_EVENTS_EXCEED_PUBLISHERS_LIMIT_ERROR = 450;
        public static final int RTC_ROOM_EVENTS_EXCEED_SUBSCRIBERS_LIMIT_ERROR = 449;
        public static final int RTC_ROOM_EVENTS_LIVE_INTRERRUPT = 118;
        public static final int RTC_ROOM_EVENTS_LIVE_PUBLISH_FAIL = 117;
        public static final int RTC_ROOM_EVENTS_LIVE_PUBLISH_SUCCESS = 116;
        public static final int RTC_ROOM_EVENTS_PUBLISHER_PARAMS_INCORRECT_ERROR = 451;
        public static final int RTC_ROOM_EVENTS_PUBLISHER_REPEAT_PUBLISH_ERROR = 453;
        public static final int RTC_ROOM_EVENTS_PUBLISHER_USER_NOT_EXISTS_ERROR = 452;
        public static final int RTC_ROOM_EVENTS_REPEAT_SUBSCRIBE_ERROR = 456;
        public static final int RTC_ROOM_EVENTS_SOMEBODY_DISSHUTUPED = 114;
        public static final int RTC_ROOM_EVENTS_SOMEBODY_KICKOFFED = 115;
        public static final int RTC_ROOM_EVENTS_SOMEBODY_SHUTUPED = 113;
        public static final int RTC_ROOM_EVENTS_START_MEDIA_RELAY_FAIL = 120;
        public static final int RTC_ROOM_EVENTS_START_MEDIA_RELAY_SUCCESS = 119;
        public static final int RTC_ROOM_EVENTS_STOP_MEDIA_RELAY_FAIL = 122;
        public static final int RTC_ROOM_EVENTS_STOP_MEDIA_RELAY_SUCCESS = 121;
        public static final int RTC_ROOM_EVENTS_SUBSCRIBER_PARAMS_INCORRECT_ERROR = 455;
        public static final int RTC_ROOM_EVENTS_SUBSCRIBER_USER_NOT_EXISTS_ERROR = 454;
        public static final int RTC_ROOM_EVENTS_SUBSCRIBER_USER_STATE_ERROR = 460;
        public static final int RTC_ROOM_EVENTS_UNPUBLISH_USER_NOT_EXISTS_ERROR = 461;
        public static final int RTC_ROOM_EVENTS_UNSUBSCRIBE_PARAMS_INCORRECT_ERROR = 458;
        public static final int RTC_ROOM_EVENTS_UNSUBSCRIBE_USER_NOT_EXISTS_ERROR = 457;
        public static final int RTC_ROOM_EVENTS_UNSUBSCRIBE_USER_NOT_SUBSCRIBED_ERROR = 459;
        public static final int RTC_ROOM_EVENT_AVAILABLE_SEND_BITRATE = 200;
        public static final int RTC_ROOM_EVENT_AVAILABLE_SEND_PACKET_LOSS = 201;
        public static final int RTC_ROOM_EVENT_CONNECTION_LOST = 103;
        public static final int RTC_ROOM_EVENT_LOGIN_ERROR = 102;
        public static final int RTC_ROOM_EVENT_LOGIN_OK = 100;
        public static final int RTC_ROOM_EVENT_LOGIN_TIMEOUT = 101;
        public static final int RTC_ROOM_EVENT_ON_USER_ATTRIBUTE = 303;
        public static final int RTC_ROOM_EVENT_ON_USER_JOINED_ROOM = 300;
        public static final int RTC_ROOM_EVENT_ON_USER_LEAVING_ROOM = 301;
        public static final int RTC_ROOM_EVENT_ON_USER_MESSAGE = 302;
        public static final int RTC_ROOM_EVENT_REMOTE_ADDED_STREAM = 123;
        public static final int RTC_ROOM_EVENT_REMOTE_AUDIO_ARRIVED = 108;
        public static final int RTC_ROOM_EVENT_REMOTE_AUDIO_REMOVED = 109;
        public static final int RTC_ROOM_EVENT_REMOTE_COMING = 104;
        public static final int RTC_ROOM_EVENT_REMOTE_GONE = 107;
        public static final int RTC_ROOM_EVENT_REMOTE_LEAVING = 105;
        public static final int RTC_ROOM_EVENT_REMOTE_REMOVED_STREAM = 124;
        public static final int RTC_ROOM_EVENT_REMOTE_RENDERING = 106;
        public static final int RTC_ROOM_EVENT_REMOTE_VIDEO_SIZE_CHANGE = 500;
        public static final int RTC_ROOM_EVENT_SHARE_SCREEN_START = 125;
        public static final int RTC_ROOM_EVENT_SHARE_SCREEN_STOP = 126;
        public static final int RTC_ROOM_EVENT_STATS_UPDATED = 202;
        public static final int RTC_ROOM_PEER_CONNECTION_ERROR = 10000;
        public static final int RTC_ROOM_PHONE_STATE_CHANGE = 60000;
        public static final int RTC_ROOM_SERVER_KEEPALIVE_TIMEOUT_ERROR = 402;
        public static final int RTC_ROOM_SERVER_SIGNAL_ERROR = 403;
        public static final int RTC_ROOM_SET_EXTERNAL_SURFACE_ERROR = 600;
        public static final int RTC_ROOM_SO_LATER_DOWNLOADING_FAIL = 400;
        public static final int RTC_ROOM_SO_LATER_LOADING_FAIL = 401;
        public static final int RTC_ROOM_STATE_SUCCESS = 0;
        public static final int RTC_ROOM_SUBSCRIBE_USER_STREAM_LEAVE = 30000;
        public static final int RTC_ROOM_USERID_ALREADY_EXIST_ERROR = 436;
        public static final int RTC_STATE_ICE_CONNECTED = 2004;
        public static final int RTC_STATE_ICE_DISCONNECTED = 2005;
        public static final int RTC_STATE_PUBLISHER_STREAM_DOWN = 2006;
        public static final int RTC_STATE_SENDING_MEDIA_FAILED = 2002;
        public static final int RTC_STATE_SENDING_MEDIA_OK = 2001;
        public static final int RTC_STATE_STREAM_CLOSED = 2008;
        public static final int RTC_STATE_STREAM_DOWN = 2003;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL0 = 2100;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL1 = 2101;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL2 = 2102;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL3 = 2103;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL4 = 2104;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL5 = 2105;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL6 = 2106;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL7 = 2107;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL8 = 2108;
        public static final int RTC_STATE_STREAM_SLOW_LINK_LEVEL9 = 2109;
        public static final int RTC_STATE_STREAM_UP = 2000;
        public static final int RTC_STATE_SUBSCRIBER_STREAM_DOWN = 2007;

        void onEngineStatisticsInfo(BigInteger bigInteger, Object obj);

        void onErrorInfoUpdate(int i);

        void onPeerConnectStateUpdate(int i);

        void onRoomDataMessage(ByteBuffer byteBuffer);

        void onRoomEventUpdate(int i, long j, String str);

        void onStreamInfoUpdate(String[] strArr);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface BaiduRtcRoomVideoObserver {
        void getInputBuffer(int i, int i2, RtcVideoFormat rtcVideoFormat);

        void onVideoFrame(RTCVideoFrame rTCVideoFrame, long j);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum KeyAgreementProtocol {
        BRTC_DLTS,
        BRTC_SDES
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RoomInfo {
        public String MediaServerURL = "";
        public String RoomID = "";
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcLiveTransferMode {
        RTC_LIVE_TRANSFER_MODE_ROOM_TRANSMISSION,
        RTC_LIVE_TRANSFER_MODE_ANCHOR_TRASNSMISSION
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RtcRoomAudioLevel {
        public long userId = 0;
        public String nicName = "";
        public int volumeLevel = 0;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RtcRoomUserInfo {
        public String attribute;
        public float distance;
        public float horiAngle;
        public int mixerVol;
        public int nearVol;
        public int role;
        public String ssrc;
        public long userId;
        public String userName;
        public float vertAngle;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RtcRoomVideoDimension {
        public int videoHeight;
        public int videoRotation;
        public int videoWidth;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcSoundMode {
        RTC_SOUND_MODE_SPEAKER,
        RTC_SOUND_MODE_EAR
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum RtcVideoFormat {
        RTC_VIDEO_FORMAT_YUV420,
        RTC_VIDEO_FORMAT_RGBA
    }

    public abstract void addExternalRenderer(long j, RTCVideoExternalRender rTCVideoExternalRender);

    public abstract void cameraFocusWithPoint(float f, float f2);

    @Deprecated
    public abstract void cameraFocusWithPoint(int i, int i2);

    public abstract void changeSurfaceSize(long j, int i, int i2);

    public abstract boolean configLiveServerWithUrl(String str, boolean z, boolean z2, String str2, RtcLiveTransferMode rtcLiveTransferMode);

    public abstract void destroyExternalSurface(long j, Surface surface);

    public abstract void disbandRoom();

    public abstract void enableAec(boolean z);

    public abstract void enableAgc(boolean z);

    public abstract void enableAns(boolean z);

    public abstract void enableAudioMix(boolean z);

    public abstract void enableBdAEC(boolean z);

    public abstract void enableBdAGC(boolean z);

    public abstract void enableBdANS(boolean z);

    public abstract void enableErrorInfoToServer(boolean z, String str);

    public abstract void enableExternalAudioRecord(boolean z);

    public abstract void enableExternalVideoCapturer(boolean z);

    public abstract void enableHumanSeg(boolean z, BRTCEffectParams bRTCEffectParams);

    public abstract void enableLaterLoadSo(String str);

    public abstract void enableMicCapture(boolean z);

    public abstract void enableStatsToServer(boolean z, String str);

    public abstract void enableStatsToServer(boolean z, boolean z2, String str);

    public abstract void enableTraceInfoToServer(boolean z, String str);

    public abstract void enableVoiceChange(boolean z);

    public abstract void enableWatermark(boolean z, BRTCWatermarkParams bRTCWatermarkParams);

    public abstract IRtcMediaRecorder getBRTCMediaRecorder();

    public abstract ArrayList<CommonDefine.StreamInfo> getComingStreams(long j);

    public abstract RTCAudioSamples.RTCExternalSamplesReadyCallback getExternalAudioSamplesCallback();

    public abstract float getMaxCameraZoom();

    public abstract ArrayList<CommonDefine.StreamInfo> getOperatingStreams(long j);

    public abstract RtcRoomAudioLevel[] getRemoteAudioLevels();

    public abstract RtcRoomVideoDimension getRemoteVideoDimension(long j);

    public abstract IRtcBeautyManager getRtcBeautyManager();

    public abstract IRtcFrameProcessorManager getRtcFrameProcessorManager();

    public abstract void getUserAttribute(long j);

    public abstract RtcRoomUserInfo[] getUserListOfRoom();

    public abstract void injectLoggable(Loggable loggable, Logging.Severity severity);

    public abstract boolean isCameraZoomSupported();

    public abstract boolean isExternalAudioRecord();

    public abstract boolean isFocusSupported();

    public abstract boolean isIsPeerConnectionFactoryReady();

    public abstract boolean isSpeakerOn();

    public abstract void kickOffUserWithId(long j);

    public abstract boolean loginRtcRoomWithRoomName(String str, long j, String str2);

    public abstract boolean loginRtcRoomWithRoomName(String str, long j, String str2, boolean z);

    public abstract boolean loginRtcRoomWithRoomName(String str, long j, String str2, boolean z, boolean z2);

    public abstract boolean logoutRtcRoom();

    public abstract void muteCamera(boolean z);

    public abstract void muteMicphone(boolean z);

    public abstract void muteSpeaker(boolean z);

    public abstract void presetLoudSpeaker(boolean z);

    public abstract void publishStreaming();

    public abstract HUDStatistics queryEngineStatisticsInfo(long j);

    @Deprecated
    public abstract UserList queryUserListOfRoom();

    public abstract void removeExternalRenderer(long j);

    public abstract void removeLocalDisplay();

    public abstract void removeRemoteDisplay(long j);

    public abstract void removeRemoteDisplay(long j, String str);

    public abstract void sendData(ByteBuffer byteBuffer);

    public abstract void sendMessageToUser(String str, long j);

    public abstract void setAudioAecSamplesReadyCallback(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback);

    public abstract int setAudioProfile(BRTCAudioProfileType bRTCAudioProfileType, BRTCAudioScenario bRTCAudioScenario);

    public abstract void setAudioRecordDelegate(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback);

    public abstract void setAudioSampleStatusCallback(RTCAudioSamples.RTCSampleStatusCallback rTCSampleStatusCallback);

    public abstract void setAuidoSamplesReadyCallback(RTCAudioSamples.RTCSamplesReadyCallback rTCSamplesReadyCallback);

    public void setBaiduRtcAppID(String str, String str2) {
    }

    public abstract boolean setBaiduRtcRoomDelegate(BaiduRtcRoomDelegate baiduRtcRoomDelegate);

    public abstract void setBuffingFactor(float f);

    public abstract void setCameraID(int i);

    public abstract void setCameraZoom(float f);

    public abstract void setDisplayEnableHardwareScaler(boolean z);

    public abstract void setEngineStateStatistics(boolean z);

    public abstract void setExternalMixAudio(ByteBuffer byteBuffer, int i);

    public abstract void setExternalSurface(long j, Surface surface);

    public abstract void setKeyAgreementProtocol(KeyAgreementProtocol keyAgreementProtocol);

    public abstract boolean setLiveStreamingMix(boolean z);

    public abstract boolean setLiveStreamingRole(String str);

    public abstract boolean setLiveStreamingURL(String str);

    public abstract void setLocalDisplay(RTCVideoView rTCVideoView);

    public abstract void setMediaServerURL(String str);

    public abstract boolean setMixLayoutPositionIndex(String str);

    public abstract void setMixedAudioSamplesCallback(RTCAudioSamples.RTCMixedSamplesReadyCallback rTCMixedSamplesReadyCallback);

    public abstract void setParamSettings(RtcParameterSettings rtcParameterSettings, RtcParameterSettings.RtcParamSettingType rtcParamSettingType);

    public abstract boolean setRecording(boolean z);

    public abstract void setRedenFactor(float f);

    public abstract void setRemoteAudioPlayState(boolean z, long j);

    public abstract void setRemoteAudioSamplesCallback(RTCAudioSamples.RTCRemoteSamplesReadyCallback rTCRemoteSamplesReadyCallback);

    public abstract void setRemoteDisplay(RTCVideoView rTCVideoView);

    public abstract void setRemoteDisplay(RTCVideoView rTCVideoView, long j);

    public abstract void setRemoteDisplay(RTCVideoView rTCVideoView, long j, String str);

    public abstract void setRemoteDisplayGroup(RTCVideoView[] rTCVideoViewArr);

    public abstract void setRemoteVideoPlayState(boolean z, long j);

    public abstract void setRoomMode(BdRtcRoomMode bdRtcRoomMode);

    public abstract void setSoLaterLoadUrl(String str);

    public abstract void setSoundMod(RtcSoundMode rtcSoundMode);

    public abstract void setUserAttribute(String str);

    public abstract void setUserPlaybackVolume(long j, int i);

    public abstract void setVideoCapture(VideoCapturer videoCapturer);

    public abstract void setVideoProcessor(VideoProcessor videoProcessor);

    public abstract void setVoiceChangeType(BdRTCVoiceChangeType bdRTCVoiceChangeType);

    public abstract void setWhitenFactor(float f);

    public abstract void shutUpUserWithId(long j);

    public abstract void shutUpUserWithId(long j, boolean z);

    public abstract boolean startLiveServerStreaming(String str, boolean z, boolean z2, String str2, RtcLiveTransferMode rtcLiveTransferMode);

    public abstract void startPreview();

    public abstract void startPublish();

    public abstract void startRoomMediaRelay(String str, long j, String str2);

    public abstract boolean startShareScreen();

    public abstract boolean stopLiveServerStreaming(RtcLiveTransferMode rtcLiveTransferMode);

    public abstract void stopPreview();

    public abstract void stopPublish();

    public abstract void stopRoomMediaRelay(String str, long j);

    public abstract void stopRoomMediaRelayAll();

    public abstract boolean stopShareScreen();

    public abstract void stopSubscribeAllRemoteAudioStreams();

    public abstract void stopSubscribeAllRemoteVideoStreams();

    public abstract void stopSubscribeAudioStreaming(long j);

    public abstract void stopSubscribeStreaming(long j);

    public abstract void stopSubscribeVideoStreaming(long j);

    public abstract void subscribeAllRemoteAudioStreams();

    public abstract void subscribeAllRemoteVideoStreams();

    public abstract void subscribeAudioStreaming(long j);

    public abstract void subscribeStreaming(int i, long j);

    public abstract void subscribeStreaming(int i, long j, float f, float f2, float f3, int i2, int i3);

    public abstract void subscribeStreaming(long j);

    public abstract void subscribeStreaming(long j, float f, float f2, float f3, int i, int i2);

    public abstract void subscribeVideoStreaming(long j);

    public abstract void switchCamera();

    public abstract void switchLoundSpeaker();

    public abstract void upLoadLog();

    public abstract void updateScreenShareParams(BRTCScreenShareParams bRTCScreenShareParams);

    public abstract void updateStreaming(long j, float f, float f2, float f3, int i, int i2);

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum BdRtcRoomMode {
        BDRTC_ROOM_NORMAL(0),
        BDRTC_ROOM_BIG_ROOM(1);

        BdRtcRoomMode(int i) {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum BdRTCVoiceChangeType {
        AUDIO_EFFECT_ORIGIN(0),
        AUDIO_EFFECT_LUOLI(1),
        AUDIO_EFFECT_DASHU(2),
        AUDIO_EFFECT_ZHENGTAI(3),
        AUDIO_EFFECT_FEIZAI(4),
        AUDIO_EFFECT_KTV(5),
        AUDIO_EFFECT_FOZU(6),
        AUDIO_EFFECT_SINGER_FINE(7);

        BdRTCVoiceChangeType(int i) {
        }
    }

    public static synchronized BaiduRtcRoom initWithAppID(Context context, String str, String str2, String str3, boolean z, boolean z2) {
        synchronized (BaiduRtcRoom.class) {
            if (context != null) {
                BaiduRtcRoomImp baiduRtcRoomImp = new BaiduRtcRoomImp(context, str, str2, str3, z, z2);
                mInstanceList.add(baiduRtcRoomImp);
                RtcLogCapturer.init(str);
                RtcLogCapturer.reportLog(RtcLogEvent.INIT_SDK, "initWithAppiD", str, str3, Boolean.valueOf(z));
                return baiduRtcRoomImp;
            }
            return null;
        }
    }

    public static synchronized BaiduRtcRoom initWithAppID(Context context, String str, String str2, String str3, boolean z) {
        BaiduRtcRoom initWithAppID;
        synchronized (BaiduRtcRoom.class) {
            initWithAppID = initWithAppID(context, str, str2, str3, z, true);
        }
        return initWithAppID;
    }

    public static synchronized BaiduRtcRoom initWithAppID(Context context, String str, String str2) {
        BaiduRtcRoom initWithAppID;
        synchronized (BaiduRtcRoom.class) {
            initWithAppID = initWithAppID(context, str, str2, "", false);
        }
        return initWithAppID;
    }

    public synchronized void destroy() {
        if (mInstanceList.size() > 0) {
            if (this instanceof BaiduRtcRoomImp) {
                ((BaiduRtcRoomImp) this).doDestroy();
            }
            mInstanceList.remove(this);
            System.gc();
        }
    }

    public static boolean isPhoneUsing(Context context) {
        return BRtcPhoneStateManager.isPhoneUsing(context);
    }

    public static String version() {
        return Constraints.version();
    }

    public static void setUseTestEnv(boolean z) {
        BaiduRtcRoomImp.setUseTestEnv(z);
    }

    public static void setVerbose(boolean z) {
        BaiduRtcRoomImp.setVerbose(z);
    }

    public static RoomInfo getRoomInfofromPlatformServer(String str, String str2, String str3, String str4) {
        return BaiduRtcRoomImp.getRoomInfofromPlatformServer(str, str2, str3, str4);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class UserList {
        public long[] listeners;
        public long[] publishers;

        public UserList(int i, int i2) {
            if (i > 0) {
                this.publishers = new long[i];
            } else {
                this.publishers = null;
            }
            if (i2 > 0) {
                this.listeners = new long[i2];
            } else {
                this.listeners = null;
            }
        }
    }
}
