package com.baidu.rtc.signalling.play;

import com.webrtc.Logging;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class RemoteSdpResponse {
    private static final String TAG = "RemoteSdpRequest";
    private String appId;
    private String feed;
    private String remoteHandleId;
    private String roomId;
    private String roomName;
    private String sdp;
    private String sessionId;
    private String userId;

    public static RemoteSdpResponse newFromJsonStr(String str) {
        RemoteSdpResponse remoteSdpResponse = new RemoteSdpResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            remoteSdpResponse.sdp = jSONObject.optString("sdp");
            remoteSdpResponse.remoteHandleId = jSONObject.optString("handleid");
            remoteSdpResponse.sessionId = jSONObject.optString("sessionid");
            JSONObject jSONObject2 = new JSONObject(jSONObject.optString("data"));
            remoteSdpResponse.appId = jSONObject2.optString("appid");
            remoteSdpResponse.roomId = jSONObject2.optString("room");
            remoteSdpResponse.roomName = jSONObject2.optString("roomname");
            remoteSdpResponse.userId = jSONObject2.optString("id");
            remoteSdpResponse.feed = jSONObject2.optString("feed");
        } catch (JSONException e) {
            Logging.m5304e("RemoteSdpRequest", "Caught error while play response parse" + e.getMessage());
        }
        return remoteSdpResponse;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getFeed() {
        return this.feed;
    }

    public String getRemoteHandleId() {
        return this.remoteHandleId;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public String getSdp() {
        return this.sdp;
    }

    public String getSessionId() {
        return this.sessionId;
    }
}
