package com.baidu.rtc.logreport;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ErrorInfoReport {
    private static ErrorInfoReport instance;
    private String appId;
    private String clientIp;
    private int code;
    private String device;
    private String env;
    private String message;
    private long publisherHandleId;
    private long roomId;
    private long sessionId;
    private long userId;
    private long value;
    private String version;
    private String remoteIp = "";
    private Map<BigInteger, String> serverIpMap = new ConcurrentHashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum ErrorCode {
        LOGIN_ERROR(102, "LOGIN_ERROR"),
        LOGIN_TIMEOUT(102, "LOGIN_TIMEOUT"),
        SIGNAL_CHANNEL_CONNECTION_LOST(103, "SIGNAL_CHANNEL_CONNECTION_LOST"),
        ROOM_LIVE_PUBLISH_FAIL(117, "ROOM_LIVE_PUBLISH_FAIL"),
        ANCHOR_LIVE_PUBLISH_FAIL(117, "ANCHOR_LIVE_PUBLISH_FAIL"),
        ROOM_LIVE_INTRERRUPT(118, "ROOM_LIVE_INTRERRUPT"),
        ANCHOR_LIVE_INTRERRUPT(118, "ANCHOR_LIVE_INTRERRUPT"),
        VIDEO_SENDING_MEDIA_FAILED(201, "VIDEO_SENDING_MEDIA_FAILED"),
        AUDIO_SENDING_MEDIA_FAILED(201, "AUDIO_SENDING_MEDIA_FAILED"),
        PEERCONNECTION_CREATE_ERROR(202, "PEERCONNECTION_CREATE_ERROR"),
        MEDIA_CHANNEL_CONNECTION_LOST(203, "MEDIA_CHANNEL_CONNECTION_LOST"),
        SO_LATER_DOWNLOADING_FAIL(400, "SO_LATER_DOWNLOADING_FAIL"),
        SO_LATER_LOADING_FAIL(401, "SO_LATER_LOADING_FAIL"),
        KEEPALIVE_TIMEOUT(402, "KEEPALIVE_TIMEOUT"),
        USR_ALREADY_EXIST(436, "USR_ALREADY_EXIST"),
        HANG_UP(440, "HANG_UP"),
        AUDIO_RECORD_START_ERROR(501, "AUDIO_RECORD_START_ERROR"),
        AUDIO_RECORD_ERROR(502, "AUDIO_RECORD_ERROR"),
        AUDIO_RECORD_GENERIC_ERROR(503, "AUDIO_RECORD_ERROR"),
        AUDIO_RECORD_BAD_VALUE_ERROR(504, "AUDIO_RECORD_ERROR"),
        AUDIO_RECORD_READ_OBJECT_ERROR(505, "AUDIO_RECORD_ERROR"),
        AUDIO_RECORD_INVALID_OPERATION_ERROR(506, "AUDIO_RECORD_ERROR"),
        SET_EXTERNAL_SURFACE_ERROR(600, "SET_EXTERNAL_SURFACE_ERROR"),
        OTHER_ERROR(700, "OTHER_ERROR"),
        ENTER_FAILED(7000, "ENTER_FAILED"),
        ENTER_TIMEOUT(7000, "ENTER_TIMEOUT"),
        AUDIO_STUCK(7001, "AUDIO_STUCK"),
        VIDEO_STUCK(7002, "VIDEO_STUCK"),
        BAD_END_TO_END_DELAY(7003, "BAD_END_TO_END_DELAY"),
        BAD_FIRST_FRAME_TIME(7004, "BAD_FIRST_FRAME_TIME");
        
        private int code;
        private String message;

        ErrorCode(int i, String str) {
            this.code = i;
            this.message = str;
        }

        public int getErrorCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public static ErrorInfoReport getInstance() {
        if (instance == null) {
            instance = new ErrorInfoReport();
        }
        return instance;
    }

    public void putServerMap(BigInteger bigInteger, String str) {
        this.serverIpMap.put(bigInteger, str);
    }

    public String getEnv() {
        return this.env;
    }

    public void setEnv(String str) {
        this.env = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getDevice() {
        return this.device;
    }

    public void setDevice(String str) {
        this.device = str;
    }

    public String getRemoteIp() {
        return this.remoteIp;
    }

    public String getClientIp() {
        return this.clientIp;
    }

    public void setClientIp(String str) {
        this.clientIp = "Unknown";
    }

    public void setRemoteIp(String str) {
        this.remoteIp = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public long getRoomId() {
        return this.roomId;
    }

    public void setRoomId(long j) {
        this.roomId = j;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public long getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(long j) {
        this.sessionId = j;
    }

    public long getPublishHandleId() {
        return this.publisherHandleId;
    }

    public void setPublishHandleId(long j) {
        this.publisherHandleId = j;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public long getValue() {
        return this.value;
    }

    public void setValue(long j) {
        this.value = j;
    }

    public void resetParam() {
        this.env = "";
        this.version = "";
        this.device = "";
        this.clientIp = "";
        this.appId = "";
        this.roomId = -1L;
        this.userId = -1L;
        this.sessionId = -1L;
        this.publisherHandleId = -1L;
        this.code = 700;
        this.message = "";
        this.value = -1L;
        this.serverIpMap.clear();
    }

    public void reportErrorInfo(ErrorCode errorCode, long j, BigInteger bigInteger, BigInteger bigInteger2) {
        reportErrorInfo(errorCode, j, this.serverIpMap.containsKey(bigInteger) ? this.serverIpMap.get(bigInteger) : "", bigInteger2.longValue(), bigInteger.longValue());
    }

    public void reportErrorInfo(ErrorCode errorCode) {
        reportErrorInfo(errorCode, -1L, this.serverIpMap.get(BigInteger.valueOf(this.publisherHandleId)), -1L, this.publisherHandleId);
    }

    public void reportErrorInfo(ErrorCode errorCode, long j, String str, long j2, long j3) {
        reportErrorInfo(errorCode.getErrorCode(), errorCode.getMessage(), j, str, j2, j3);
    }

    public void reportErrorInfo(int i, String str, long j, long j2) {
        reportErrorInfo(i, str, -1L, getRemoteIp(), j, j2);
    }

    public void reportErrorInfo(int i, String str, long j, String str2, long j2, long j3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("env", this.env);
            jSONObject.put("timestamp", System.currentTimeMillis());
            jSONObject.put("version", this.version);
            jSONObject.put("device", RtcLogReport.getDeviceModel());
            jSONObject.put("clientIp", this.clientIp);
            jSONObject.put("serverIp", str2);
            jSONObject.put("appId", this.appId);
            jSONObject.put("roomId", this.roomId);
            jSONObject.put("userId", this.userId);
            if (j2 > 0) {
                jSONObject.put("feedId", j2);
            }
            jSONObject.put("sessionId", this.sessionId);
            jSONObject.put("handleId", j3);
            jSONObject.put("code", i);
            if (!TextUtils.isEmpty(str)) {
                jSONObject.put("message", str);
            }
            if (j > 0) {
                jSONObject.put("value", j);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RtcLogReport.getInstance().report(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), 4);
    }
}
