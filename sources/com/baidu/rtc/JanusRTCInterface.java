package com.baidu.rtc;

import com.baidu.rtc.BaiduRtcRoom;
import com.baidu.rtc.CommonDefine;
import java.math.BigInteger;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface JanusRTCInterface {
    void onAddedStreams(BigInteger bigInteger, ArrayList<CommonDefine.StreamInfo> arrayList);

    void onAttribute(BigInteger bigInteger, String str);

    void onComing(BigInteger bigInteger, String str);

    void onConnectClosed();

    void onCreatedHandle(BigInteger bigInteger, long j);

    void onError(ErrorInfo errorInfo);

    void onHangUp(BigInteger bigInteger);

    void onLeaving(BigInteger bigInteger, BigInteger bigInteger2);

    void onLivePublishFailed(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode, String str);

    void onLivePublishInterrupted(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode, String str);

    void onLivePublishSucceed(BaiduRtcRoom.RtcLiveTransferMode rtcLiveTransferMode, String str);

    void onLoginError();

    void onLoginTimeout();

    void onMediaStreamingEvent(BigInteger bigInteger, int i, boolean z);

    void onMessage(BigInteger bigInteger, String str);

    void onPublisherJoined(BigInteger bigInteger, long j);

    void onPublisherRemoteJsep(BigInteger bigInteger, JSONObject jSONObject);

    void onRemoteGone(BigInteger bigInteger);

    void onRemovedStreams(BigInteger bigInteger, BigInteger bigInteger2, ArrayList<CommonDefine.StreamInfo> arrayList);

    void onRoomDisbanded();

    void onServerAckTimeout();

    void onSignalErrorInfo(long j, int i, String str);

    void onSlowLink(boolean z, int i);

    void onStartMediaRelayFailed(String str, long j);

    void onStartMediaRelaySucceed(String str, long j);

    void onStopMediaRelaySucceed();

    void onSubscribeUser(BigInteger bigInteger, BigInteger bigInteger2);

    void onSubscribeUserStreamLeave(BigInteger bigInteger);

    void onUserDisShutUp(long j);

    void onUserJoinedRoom(BigInteger bigInteger, String str, String str2);

    void onUserKickOff(long j);

    void onUserLeavingRoom(BigInteger bigInteger);

    void onUserShutUp(long j);

    void onWebSocketClosing(String str);

    void onWebSocketConnectError(String str);

    void onWebSocketOpened();

    void onWebrtcUp(BigInteger bigInteger, String str);

    void subscriberHandleRemoteJsep(BigInteger bigInteger, JSONObject jSONObject);
}
