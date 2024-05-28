package com.baidu.rtc.logreport;

import android.os.Build;
import android.text.TextUtils;
import com.baidu.rtc.config.Constraints;
import com.cjt2325.cameralibrary.CameraInterface;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import org.json.JSONArray;
import org.json.JSONObject;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcLogCapturer {
    public static final int LENGTH_SUBSTRING_ERROR_MSG = 70;
    private static final String REQUEST_URL = "https://rtc-log.cdn.bcebos.com/collect";
    private static int index = 0;
    private static String lastEvent = null;
    private static long publisherHandleId = 0;
    private static boolean sdkSettingEnableReport = true;
    private static long subscriberHandleId = 0;
    private static long userId = 0;
    private static boolean userSettingEnableReport = true;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class LogParams {
        public String appId;
        public long roomId;
        public String roomName;

        public LogParams(String str, long j, String str2) {
            this.appId = str;
            this.roomId = j;
            this.roomName = str2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum Role {
        PUBLISHER(0),
        LISTENER(1);
        
        int value;

        Role(int i) {
            this.value = i;
        }
    }

    private static void setReportEnable() {
        BdLogReport.setReportEnable(userSettingEnableReport && sdkSettingEnableReport);
    }

    public static boolean isEnableReport() {
        return userSettingEnableReport && sdkSettingEnableReport;
    }

    public static void init(String str) {
        BdLogReport.init(str, 0L, "", "https://rtc-log.cdn.bcebos.com/collect");
        BdLogReport.setConsoleEnable(false);
        setReportEnable();
    }

    public static void setUserReportEnable(boolean z) {
        userSettingEnableReport = z;
        setReportEnable();
    }

    public static void setSdkReportEnable(boolean z) {
        sdkSettingEnableReport = z;
        setReportEnable();
    }

    public static void setConsoleEnable(boolean z) {
        BdLogReport.setConsoleEnable(z);
    }

    public static void setParams(LogParams logParams) {
        if (logParams != null) {
            BdLogReport.setParams(logParams.appId, logParams.roomId, logParams.roomName);
        }
    }

    public static void setPublisherHandleId(long j) {
        publisherHandleId = j;
    }

    public static void setSubscriberHandleId(long j) {
        subscriberHandleId = j;
    }

    public static void stopReport() {
        BdLogReport.stopReport();
    }

    public static void release() {
        BdLogReport.release();
        userId = 0L;
        index = 0;
        publisherHandleId = 0L;
        subscriberHandleId = 0L;
    }

    public static void setUserId(long j) {
        userId = j;
    }

    public static void reportLog(RtcLogEvent rtcLogEvent, Object... objArr) {
        reportEvent(rtcLogEvent, 0L, objArr);
    }

    public static void reportEvent(RtcLogEvent rtcLogEvent, long j, Object... objArr) {
        if (isEnableReport() && objArr != null && objArr.length >= 1 && rtcLogEvent.level == 1) {
            try {
                JSONObject jSONObject = new JSONObject();
                if (rtcLogEvent == RtcLogEvent.INFO_EVENT) {
                    jSONObject.putOpt("function", objArr[0]);
                    jSONObject.putOpt("info", objArr[1]);
                } else if (rtcLogEvent == RtcLogEvent.DEBUG_EVENT) {
                    jSONObject.putOpt("function", objArr[0]);
                    jSONObject.putOpt("info", objArr[1]);
                } else if (rtcLogEvent == RtcLogEvent.TRACE_EVENT) {
                    jSONObject.putOpt("function", objArr[0]);
                    jSONObject.putOpt("info", objArr[1]);
                } else if (rtcLogEvent == RtcLogEvent.ERROR_EVENT) {
                    jSONObject.putOpt("function", objArr[0]);
                    jSONObject.putOpt("errorCode", objArr[1]);
                    jSONObject.putOpt("errorInfo", objArr[2]);
                } else {
                    innerReportLog(rtcLogEvent, jSONObject, objArr);
                }
                reportEvent(System.currentTimeMillis(), rtcLogEvent.value, jSONObject, j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00b8 A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:4:0x0003, B:6:0x0012, B:13:0x002b, B:15:0x005b, B:17:0x008c, B:19:0x0094, B:22:0x009d, B:24:0x00b4, B:26:0x00b8, B:28:0x00c3, B:27:0x00bd, B:23:0x00a9, B:16:0x0074, B:7:0x0016, B:9:0x001a, B:11:0x0020, B:12:0x0025), top: B:33:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00bd A[Catch: Exception -> 0x00c7, TryCatch #0 {Exception -> 0x00c7, blocks: (B:4:0x0003, B:6:0x0012, B:13:0x002b, B:15:0x005b, B:17:0x008c, B:19:0x0094, B:22:0x009d, B:24:0x00b4, B:26:0x00b8, B:28:0x00c3, B:27:0x00bd, B:23:0x00a9, B:16:0x0074, B:7:0x0016, B:9:0x001a, B:11:0x0020, B:12:0x0025), top: B:33:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void reportEvent(long r2, java.lang.String r4, java.lang.Object r5, long r6) {
        /*
            if (r5 != 0) goto L3
            return
        L3:
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: java.lang.Exception -> Lc7
            r2.<init>()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "event"
            r2.putOpt(r3, r4)     // Catch: java.lang.Exception -> Lc7
            r3 = 0
            boolean r0 = r5 instanceof java.lang.String     // Catch: java.lang.Exception -> Lc7
            if (r0 == 0) goto L16
            r3 = r5
            java.lang.String r3 = (java.lang.String) r3     // Catch: java.lang.Exception -> Lc7
            goto L2b
        L16:
            boolean r0 = r5 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> Lc7
            if (r0 == 0) goto L2b
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch: java.lang.Exception -> Lc7
            boolean r3 = r5 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> Lc7
            if (r3 != 0) goto L25
            java.lang.String r3 = r5.toString()     // Catch: java.lang.Exception -> Lc7
            goto L2b
        L25:
            org.json.JSONObject r5 = (org.json.JSONObject) r5     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r5)     // Catch: java.lang.Exception -> Lc7
        L2b:
            java.lang.String r5 = "extra"
            r2.putOpt(r5, r3)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "timestamp"
            long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Exception -> Lc7
            java.lang.Long r5 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "userId"
            long r0 = com.baidu.rtc.logreport.RtcLogCapturer.userId     // Catch: java.lang.Exception -> Lc7
            java.lang.Long r5 = java.lang.Long.valueOf(r0)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "feedId"
            java.lang.Long r5 = java.lang.Long.valueOf(r6)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "SUBSCRIBE"
            boolean r3 = r4.contains(r3)     // Catch: java.lang.Exception -> Lc7
            if (r3 == 0) goto L74
            java.lang.String r3 = "handleId"
            long r5 = com.baidu.rtc.logreport.RtcLogCapturer.subscriberHandleId     // Catch: java.lang.Exception -> Lc7
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "role"
            com.baidu.rtc.logreport.RtcLogCapturer$Role r5 = com.baidu.rtc.logreport.RtcLogCapturer.Role.LISTENER     // Catch: java.lang.Exception -> Lc7
            int r5 = r5.value     // Catch: java.lang.Exception -> Lc7
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
            goto L8c
        L74:
            java.lang.String r3 = "handleId"
            long r5 = com.baidu.rtc.logreport.RtcLogCapturer.publisherHandleId     // Catch: java.lang.Exception -> Lc7
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = "role"
            com.baidu.rtc.logreport.RtcLogCapturer$Role r5 = com.baidu.rtc.logreport.RtcLogCapturer.Role.PUBLISHER     // Catch: java.lang.Exception -> Lc7
            int r5 = r5.value     // Catch: java.lang.Exception -> Lc7
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r5)     // Catch: java.lang.Exception -> Lc7
        L8c:
            java.lang.String r3 = "ERROR"
            boolean r3 = r4.contains(r3)     // Catch: java.lang.Exception -> Lc7
            if (r3 != 0) goto La9
            java.lang.String r3 = "FAIL"
            boolean r3 = r4.contains(r3)     // Catch: java.lang.Exception -> Lc7
            if (r3 == 0) goto L9d
            goto La9
        L9d:
            java.lang.String r3 = "status"
            r4 = 0
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r4)     // Catch: java.lang.Exception -> Lc7
            goto Lb4
        La9:
            java.lang.String r3 = "status"
            r4 = 1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> Lc7
            r2.putOpt(r3, r4)     // Catch: java.lang.Exception -> Lc7
        Lb4:
            boolean r3 = r2 instanceof org.json.JSONObject     // Catch: java.lang.Exception -> Lc7
            if (r3 != 0) goto Lbd
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lc7
            goto Lc3
        Lbd:
            org.json.JSONObject r2 = (org.json.JSONObject) r2     // Catch: java.lang.Exception -> Lc7
            java.lang.String r2 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r2)     // Catch: java.lang.Exception -> Lc7
        Lc3:
            com.baidu.rtc.logreport.BdLogReport.reportEvent(r2)     // Catch: java.lang.Exception -> Lc7
            goto Lcb
        Lc7:
            r2 = move-exception
            r2.printStackTrace()
        Lcb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.rtc.logreport.RtcLogCapturer.reportEvent(long, java.lang.String, java.lang.Object, long):void");
    }

    private static void innerReportLog(RtcLogEvent rtcLogEvent, JSONObject jSONObject, Object... objArr) throws Exception {
        if (rtcLogEvent == null || objArr[0] == null) {
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        if (objArr[0].equals(lastEvent)) {
            index++;
        } else {
            index = 0;
        }
        if (index >= 10) {
            return;
        }
        lastEvent = (String) objArr[0];
        switch (C32871.$SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[rtcLogEvent.ordinal()]) {
            case 1:
                jSONObject.putOpt("appId", objArr[1]);
                jSONObject.putOpt("cputOptype", objArr[2]);
                jSONObject.putOpt("isEnableSoLaterLoad", objArr[3]);
                jSONObject.putOpt("platform", "android");
                jSONObject.putOpt("version", Constraints.sdkVersion());
                jSONObject.putOpt("model", Build.MODEL);
                jSONObject.putOpt("sdkVersion", Integer.valueOf(Build.VERSION.SDK_INT));
                return;
            case 2:
                jSONObject.putOpt("autoPublish", objArr[1]);
                jSONObject.putOpt("autoSubscribe", objArr[2]);
                jSONObject.putOpt("EnableMultistream", objArr[3]);
                jSONObject.putOpt("VideoMaxkbps", objArr[4]);
                jSONObject.putOpt("VideoRenderMode", objArr[5]);
                return;
            case 3:
                jSONObject.putOpt("rooName", objArr[1]);
                jSONObject.putOpt("userId", objArr[2]);
                return;
            case 4:
            case 5:
            case 7:
            case 11:
            case 14:
            case 15:
            case 22:
            case 51:
            case 57:
            case 64:
            case 65:
            case 73:
            case 79:
            case 81:
            case 86:
            case 87:
            case 89:
            case 90:
            case 92:
            case 95:
            case 96:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 115:
            case 116:
            case 118:
            case 119:
            case 121:
            case 122:
            case 124:
            case 125:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 144:
            case 148:
            case 150:
            case 153:
            case 156:
            case 158:
            case 159:
            case 169:
            case 176:
            case 179:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 195:
            case 197:
            case 199:
            case 201:
            case 202:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 219:
            default:
                return;
            case 6:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 8:
                jSONObject.putOpt("userIds", objArr[1]);
                return;
            case 9:
                jSONObject.putOpt("userIds", objArr[1]);
                return;
            case 10:
                jSONObject.putOpt("muted", objArr[1]);
                return;
            case 12:
                jSONObject.putOpt("View", objArr[1]);
                return;
            case 13:
                jSONObject.putOpt("View", objArr[1]);
                return;
            case 16:
                jSONObject.putOpt("muted", objArr[1]);
                return;
            case 17:
                jSONObject.putOpt("opend", objArr[1]);
                return;
            case 18:
                jSONObject.putOpt("opend", objArr[1]);
                return;
            case 19:
                jSONObject.putOpt("muted", objArr[1]);
                return;
            case 20:
                jSONObject.putOpt("destRoomName", objArr[1]);
                jSONObject.putOpt("userId", objArr[2]);
                return;
            case 21:
                jSONObject.putOpt("destRoomName", objArr[1]);
                jSONObject.putOpt("userId", objArr[2]);
                return;
            case 23:
                jSONObject.putOpt("isEnable", objArr[1]);
                return;
            case 24:
                jSONObject.putOpt("isEnable", objArr[1]);
                return;
            case 25:
                jSONObject.putOpt("roomId", objArr[1]);
                return;
            case 26:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 27:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 28:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 29:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("display", objArr[2]);
                return;
            case 30:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("display", objArr[2]);
                return;
            case 31:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("display", objArr[2]);
                jSONObject.putOpt("streams", objArr[3]);
                return;
            case 32:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("mid", objArr[2]);
                jSONObject.putOpt("ssrc", objArr[3]);
                return;
            case 33:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("mid", objArr[2]);
                jSONObject.putOpt("ssrc", objArr[3]);
                return;
            case 34:
                jSONObject.putOpt("userId", objArr[1]);
                return;
            case 35:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("mid", objArr[2]);
                jSONObject.putOpt("ssrc", objArr[3]);
                return;
            case 36:
                jSONObject.putOpt("userId", objArr[1]);
                jSONObject.putOpt("mid", objArr[2]);
                jSONObject.putOpt("ssrc", objArr[3]);
                return;
            case 37:
                jSONObject.putOpt("userId", objArr[1]);
                return;
            case 38:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 39:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 40:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 41:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 42:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 43:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 44:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 45:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 46:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 47:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 48:
                jSONObject.putOpt("destRoomName", objArr[1]);
                jSONObject.putOpt("userId", objArr[2]);
                return;
            case 49:
                jSONObject.putOpt("destRoomName", objArr[1]);
                jSONObject.putOpt("userId", objArr[2]);
                jSONObject.putOpt("errorInfo", objArr[4]);
                return;
            case 50:
                jSONObject.putOpt("url", objArr[1]);
                return;
            case 52:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 53:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 54:
                jSONObject.putOpt("userevent", objArr[1]);
                jSONObject.putOpt("sessionevent", objArr[2]);
                jSONObject.putOpt("partialsdp", objArr[3]);
                jSONObject.putOpt("videocodec", objArr[4]);
                jSONObject.putOpt("audiocodec", objArr[5]);
                jSONObject.putOpt("audioLevelEnable", objArr[6]);
                jSONObject.putOpt("audioLevelTopcount", objArr[7]);
                return;
            case 55:
                jSONObject.putOpt("sessionId", objArr[1]);
                jSONObject.putOpt("handleId", objArr[2]);
                jSONObject.putOpt("roomId", objArr[3]);
                jSONObject.putOpt("publishers", objArr[4]);
                return;
            case 56:
                jSONObject.putOpt("streams", objArr[1]);
                jSONObject.putOpt("type", objArr[2]);
                return;
            case 58:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 59:
                jSONObject.putOpt("type", objArr[1]);
                jSONObject.putOpt("receiving", objArr[2]);
                return;
            case 60:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 61:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 62:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 63:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 66:
                jSONObject.putOpt("publishers", objArr[1]);
                return;
            case 67:
                jSONObject.putOpt("leavingId", objArr[1]);
                return;
            case 68:
                jSONObject.putOpt("unpublishId", objArr[1]);
                return;
            case 69:
                jSONObject.putOpt("feeds", objArr[1]);
                return;
            case 70:
                jSONObject.putOpt("handleId", objArr[1]);
                jSONObject.putOpt("streams", objArr[2]);
                return;
            case 71:
                jSONObject.putOpt("feeds", objArr[1]);
                return;
            case 72:
                jSONObject.putOpt("partial", objArr[1]);
                return;
            case 74:
                jSONObject.putOpt("started", objArr[1]);
                return;
            case 75:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 76:
                jSONObject.putOpt("feeds", objArr[1]);
                return;
            case 77:
                jSONObject.putOpt("unsubscribed", objArr[1]);
                return;
            case 78:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 80:
                jSONObject.putOpt("enableFec", objArr[1]);
                return;
            case 82:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 83:
                jSONObject.putOpt("enableDtlsSrtp", objArr[1]);
                return;
            case 84:
                jSONObject.putOpt("sdpSemantics", objArr[1]);
                return;
            case 85:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 88:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 91:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 93:
                jSONObject.putOpt("other", objArr[1]);
                return;
            case 94:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 97:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 98:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 99:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 100:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 101:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 113:
                jSONObject.putOpt("sdpSemantics", objArr[1]);
                return;
            case 114:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 117:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 120:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 123:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 126:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 127:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 128:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 129:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 130:
                jSONObject.putOpt("handleId", objArr[1]);
                return;
            case 141:
                jSONObject.putOpt("camera", objArr[1]);
                jSONObject.putOpt("isCamera2", objArr[2]);
                return;
            case 142:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 143:
                jSONObject.putOpt("fps", objArr[1]);
                jSONObject.putOpt("width", objArr[2]);
                jSONObject.putOpt("height", objArr[3]);
                return;
            case CameraInterface.TYPE_CAPTURE /* 145 */:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 146:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 147:
                jSONObject.putOpt("noDataTime", objArr[1]);
                return;
            case 149:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 151:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 152:
                jSONObject.putOpt("sampleRate", objArr[1]);
                jSONObject.putOpt("channel", objArr[2]);
                return;
            case 154:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 155:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 157:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case C0567f.f1819h /* 160 */:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 161:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 162:
                jSONObject.putOpt("noDataTime", objArr[1]);
                return;
            case 163:
                jSONObject.putOpt("type", objArr[1]);
                return;
            case 164:
                jSONObject.putOpt("type", objArr[1]);
                return;
            case 165:
                jSONObject.putOpt("type", objArr[1]);
                return;
            case 166:
                jSONObject.putOpt("useWebRtcAGC", objArr[1]);
                jSONObject.putOpt("useWebRtcAEC", objArr[2]);
                jSONObject.putOpt("useWebRtcNS", objArr[3]);
                jSONObject.putOpt("useWebBdAGC", objArr[4]);
                jSONObject.putOpt("useWebBdAEC", objArr[5]);
                jSONObject.putOpt("useWebBdNS", objArr[6]);
                return;
            case 167:
                jSONObject.putOpt("h264Codec", objArr[1]);
                return;
            case 168:
                jSONObject.putOpt("codecName", objArr[1]);
                jSONObject.putOpt("targetBitrate", objArr[2]);
                jSONObject.putOpt("colorFormat", objArr[3]);
                jSONObject.putOpt("frameRate", objArr[4]);
                jSONObject.putOpt("IInterval", objArr[5]);
                jSONObject.putOpt("profile", objArr[6]);
                return;
            case 170:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 171:
                jSONObject.putOpt("bitrate", objArr[1]);
                return;
            case 172:
                jSONObject.putOpt("frameRate", objArr[1]);
                return;
            case 173:
                jSONObject.putOpt("width", objArr[1]);
                jSONObject.putOpt("height", objArr[2]);
                return;
            case 174:
                jSONObject.putOpt("width", objArr[1]);
                jSONObject.putOpt("height", objArr[2]);
                return;
            case 175:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 177:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 178:
                jSONObject.putOpt("limitTime", objArr[1]);
                return;
            case 180:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 194:
                jSONObject.putOpt("codecName", objArr[1]);
                jSONObject.putOpt("codecType", objArr[2]);
                jSONObject.putOpt("width", objArr[1]);
                jSONObject.putOpt("height", objArr[2]);
                return;
            case 196:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 198:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 200:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 203:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 218:
                jSONObject.putOpt("errorInfo", objArr[2]);
                return;
            case 220:
                jSONObject.putOpt("feedId", objArr[1]);
                return;
            case 221:
                jSONObject.putOpt("feedId", objArr[1]);
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.rtc.logreport.RtcLogCapturer$1 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static /* synthetic */ class C32871 {
        static final /* synthetic */ int[] $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent = new int[RtcLogEvent.values().length];

        static {
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_SDK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SET_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.LOGIN_RTC_ROOM.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.LOGOUT_RTC_ROOM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.DE_INIT_SDK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_PUBLISH.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_PUBLISH.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBE_STREAM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_SUBSCRIBE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.MUTE_CAMERA.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_PREVIEW.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SET_LOCAL_DISPLAY.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SET_REMOTE_DISPLAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_PREVIEW.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SWITCH_CAMERA.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.MUTE_MICRO.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SWITCH_LOUND_SPEAKER.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PRESET_LOUD_SPEAKER.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.MUTE_SPEAKER.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_ROOM_MEDIA_RELAY.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_ROOM_MEDIA_RELAY.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_ROOM_MEDIA_RELAY_ALL.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SET_VIDEO_EXTERNAL_CAPTURE.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SET_AUDIO_EXTERNAL_CAPTURE.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_LOGIN_OK.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_LOGIN_TIMEOUT.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_LOGIN_ERROR.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_CONNECTION_LOST.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_JOINED.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_LEAVING.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_STREAM_JOINED.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_VIDEO_ARRIVED.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_VIDEO_REMOVED.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_VIDEO_RENDERING.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_AUDIO_ARRIVED.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_AUDIO_REMOVED.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USER_AUDIO_PLAYING.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_SO_DOWNLOAD_FAIL.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_USERD_EXIST_ERROR.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_KEEPALIVE_TIMEOUT_ERROR.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_SIGNAL_ERROR.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_PEER_CONNECTION_ERROR.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_STREAM_UP.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_SENDING_VIDEO_OK.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_SENDING_AUDIO_OK.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_SENDING_VIDEO_DOWN.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_SENDING_AUDIO_DOWN.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_ROOM_MEDIA_RELAY_SUCCESS.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RTC_CALLBACK_ROOM_MEDIA_RELAY_ERROR.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CONNECT_WS_SERVER.ordinal()] = 50;
            } catch (NoSuchFieldError unused50) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CONNECT_WS_SERVER_SUCCESS.ordinal()] = 51;
            } catch (NoSuchFieldError unused51) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CONNECT_WS_SERVER_FAIL.ordinal()] = 52;
            } catch (NoSuchFieldError unused52) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CONNECT_WS_SERVER_TIMEOUT.ordinal()] = 53;
            } catch (NoSuchFieldError unused53) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SEND_PUBLISHER_SETUP.ordinal()] = 54;
            } catch (NoSuchFieldError unused54) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_PUBLISHER_SETUPED.ordinal()] = 55;
            } catch (NoSuchFieldError unused55) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SEND_PUBLISHER_CONFIGURE.ordinal()] = 56;
            } catch (NoSuchFieldError unused56) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_PUBLISHER_ANSWER.ordinal()] = 57;
            } catch (NoSuchFieldError unused57) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_WEBRTCUP.ordinal()] = 58;
            } catch (NoSuchFieldError unused58) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_SEND_MEDIA_EVENT.ordinal()] = 59;
            } catch (NoSuchFieldError unused59) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_HANGUP.ordinal()] = 60;
            } catch (NoSuchFieldError unused60) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_DETACHED.ordinal()] = 61;
            } catch (NoSuchFieldError unused61) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_UBPUBLISHED_SELF.ordinal()] = 62;
            } catch (NoSuchFieldError unused62) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_ERROR_EVENT.ordinal()] = 63;
            } catch (NoSuchFieldError unused63) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_HEART_TIMEOUT.ordinal()] = 64;
            } catch (NoSuchFieldError unused64) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SERVER_TIMEOUT.ordinal()] = 65;
            } catch (NoSuchFieldError unused65) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_PUBLISHEREVENT_JOINED.ordinal()] = 66;
            } catch (NoSuchFieldError unused66) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_PUBLISHEREVENT_LEAVING.ordinal()] = 67;
            } catch (NoSuchFieldError unused67) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_PUBLISHEREVENT_UNPUBLISHED.ordinal()] = 68;
            } catch (NoSuchFieldError unused68) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SEND_SUBSCRIBER_SETUP.ordinal()] = 69;
            } catch (NoSuchFieldError unused69) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_SUBSCRIBER_SETUPED.ordinal()] = 70;
            } catch (NoSuchFieldError unused70) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SEND_SUBSCRIBE_USER.ordinal()] = 71;
            } catch (NoSuchFieldError unused71) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_SUBSCRIBER_OFFER.ordinal()] = 72;
            } catch (NoSuchFieldError unused72) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SEND_SUBSCRIBER_ANSWER.ordinal()] = 73;
            } catch (NoSuchFieldError unused73) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_SUBSCRIBER_ANSWER_SUCCESS.ordinal()] = 74;
            } catch (NoSuchFieldError unused74) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_SUBSCRIBER_ANSWER_FAIL.ordinal()] = 75;
            } catch (NoSuchFieldError unused75) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_SEND_UN_SUBSCRIBE.ordinal()] = 76;
            } catch (NoSuchFieldError unused76) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_UN_SUBSCRIBE_SUCCESS.ordinal()] = 77;
            } catch (NoSuchFieldError unused77) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.WS_RECV_UN_SUBSCRIBE_FAIL.ordinal()] = 78;
            } catch (NoSuchFieldError unused78) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_PEERCONNECTION_FACTORY_START.ordinal()] = 79;
            } catch (NoSuchFieldError unused79) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SET_PEERECONNECTION_FACTORY_PARAM.ordinal()] = 80;
            } catch (NoSuchFieldError unused80) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_PEERCONNECTION_FACTORY_SUCCESS.ordinal()] = 81;
            } catch (NoSuchFieldError unused81) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_PEERCONNECTION_FACTORY_FAILED.ordinal()] = 82;
            } catch (NoSuchFieldError unused82) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_PUBLISHER_PEERCONNECTION_START.ordinal()] = 83;
            } catch (NoSuchFieldError unused83) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_PUBLISHER_PEERCONNECTION_SUCCESS.ordinal()] = 84;
            } catch (NoSuchFieldError unused84) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_PUBLISHER_PEERCONNECTION_FAILED.ordinal()] = 85;
            } catch (NoSuchFieldError unused85) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_CREATER_OFFER_START.ordinal()] = 86;
            } catch (NoSuchFieldError unused86) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_CREATER_OFFER_SUCCESS.ordinal()] = 87;
            } catch (NoSuchFieldError unused87) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_CREATER_OFFER_FAILED.ordinal()] = 88;
            } catch (NoSuchFieldError unused88) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_START.ordinal()] = 89;
            } catch (NoSuchFieldError unused89) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_SUCCESS.ordinal()] = 90;
            } catch (NoSuchFieldError unused90) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SET_LOCAL_SDP_FAILED.ordinal()] = 91;
            } catch (NoSuchFieldError unused91) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_START.ordinal()] = 92;
            } catch (NoSuchFieldError unused92) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_SUCCESS.ordinal()] = 93;
            } catch (NoSuchFieldError unused93) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_CREATE_REMOTE_SDP_FAILED.ordinal()] = 94;
            } catch (NoSuchFieldError unused94) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SET_REMOTE_SDP_START.ordinal()] = 95;
            } catch (NoSuchFieldError unused95) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SET_REMOTE_SDP_SUCCESS.ordinal()] = 96;
            } catch (NoSuchFieldError unused96) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SET_REMOTE_SDP_FAILED.ordinal()] = 97;
            } catch (NoSuchFieldError unused97) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_CONNECTED.ordinal()] = 98;
            } catch (NoSuchFieldError unused98) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_DISCONNECTED.ordinal()] = 99;
            } catch (NoSuchFieldError unused99) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_FAILED.ordinal()] = 100;
            } catch (NoSuchFieldError unused100) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_CLOSED.ordinal()] = 101;
            } catch (NoSuchFieldError unused101) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_SELECTED_CANDIDATE.ordinal()] = 102;
            } catch (NoSuchFieldError unused102) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_DTLS_SEND_CLIENT_HELLO.ordinal()] = 103;
            } catch (NoSuchFieldError unused103) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_DTLS_RECV_SERVER_HELLO.ordinal()] = 104;
            } catch (NoSuchFieldError unused104) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_ICE_DTLS_FINISHED.ordinal()] = 105;
            } catch (NoSuchFieldError unused105) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SEND_FIRST_IDR.ordinal()] = 106;
            } catch (NoSuchFieldError unused106) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SEND_FIRST_VIDEO_PACKET.ordinal()] = 107;
            } catch (NoSuchFieldError unused107) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SEND_FIRST_AUDIO_PACKET.ordinal()] = 108;
            } catch (NoSuchFieldError unused108) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_NO_VIDEO_PACKET_ERROR.ordinal()] = 109;
            } catch (NoSuchFieldError unused109) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_NO_AUDIO_PACKET_ERROR.ordinal()] = 110;
            } catch (NoSuchFieldError unused110) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.PUBLISHER_SEND_STUN_ERROR.ordinal()] = 111;
            } catch (NoSuchFieldError unused111) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_SUBSCRIBER_PEERCONNECTION_START.ordinal()] = 112;
            } catch (NoSuchFieldError unused112) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_SUBSCRIBER_PEERCONNECTION_SUCCESS.ordinal()] = 113;
            } catch (NoSuchFieldError unused113) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CREATE_SUBSCRIBER_PEERCONNECTION_FAILED.ordinal()] = 114;
            } catch (NoSuchFieldError unused114) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBER_CREATER_OFFER_START.ordinal()] = 115;
            } catch (NoSuchFieldError unused115) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBER_CREATER_OFFER_SUCCESS.ordinal()] = 116;
            } catch (NoSuchFieldError unused116) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBER_CREATER_OFFER_FAILED.ordinal()] = 117;
            } catch (NoSuchFieldError unused117) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBER_CREATER_ANSWER_START.ordinal()] = 118;
            } catch (NoSuchFieldError unused118) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBER_CREATER_ANSWER_SUCCESS.ordinal()] = 119;
            } catch (NoSuchFieldError unused119) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBER_CREATER_ANSWER_FAILED.ordinal()] = 120;
            } catch (NoSuchFieldError unused120) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SET_LOCAL_SDP_START.ordinal()] = 121;
            } catch (NoSuchFieldError unused121) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SET_LOCAL_SDP_SUCCESS.ordinal()] = 122;
            } catch (NoSuchFieldError unused122) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SET_LOCAL_SDP_FAILED.ordinal()] = 123;
            } catch (NoSuchFieldError unused123) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SET_REMOTE_SDP_START.ordinal()] = 124;
            } catch (NoSuchFieldError unused124) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SET_REMOTE_SDP_SUCCESS.ordinal()] = 125;
            } catch (NoSuchFieldError unused125) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SET_REMOTE_SDP_FAILED.ordinal()] = 126;
            } catch (NoSuchFieldError unused126) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_CONNECTED.ordinal()] = 127;
            } catch (NoSuchFieldError unused127) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_DISCONNECTED.ordinal()] = 128;
            } catch (NoSuchFieldError unused128) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_FAILED.ordinal()] = 129;
            } catch (NoSuchFieldError unused129) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_CLOSED.ordinal()] = 130;
            } catch (NoSuchFieldError unused130) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_SELECTED_CANDIDATE.ordinal()] = 131;
            } catch (NoSuchFieldError unused131) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_DTLS_SEND_CLIENT_HELLO.ordinal()] = 132;
            } catch (NoSuchFieldError unused132) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_DTLS_RECV_SERVER_HELLO.ordinal()] = 133;
            } catch (NoSuchFieldError unused133) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_ICE_DTLS_FINISHED.ordinal()] = 134;
            } catch (NoSuchFieldError unused134) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_RECV_FIRST_IDR.ordinal()] = 135;
            } catch (NoSuchFieldError unused135) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_RECV_FIRST_VIDEO_PACKET.ordinal()] = 136;
            } catch (NoSuchFieldError unused136) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_RECV_FIRST_AUDIO_PACKET.ordinal()] = 137;
            } catch (NoSuchFieldError unused137) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_NO_VIDEO_PACKET_ERROR.ordinal()] = 138;
            } catch (NoSuchFieldError unused138) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_NO_AUDIO_PACKET_ERROR.ordinal()] = 139;
            } catch (NoSuchFieldError unused139) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBCRIBER_SEND_STUN_ERROR.ordinal()] = 140;
            } catch (NoSuchFieldError unused140) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_CAMERA_SUCCESS.ordinal()] = 141;
            } catch (NoSuchFieldError unused141) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_CAMERA_FAILED.ordinal()] = 142;
            } catch (NoSuchFieldError unused142) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_CAMERA_START.ordinal()] = 143;
            } catch (NoSuchFieldError unused143) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_CAMERA_SUCCESS.ordinal()] = 144;
            } catch (NoSuchFieldError unused144) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_CAMERA_FAILED.ordinal()] = 145;
            } catch (NoSuchFieldError unused145) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CAMERA_CAPTURE_ERROR.ordinal()] = 146;
            } catch (NoSuchFieldError unused146) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.CAMERA_CAPTURE_NO_DATA.ordinal()] = 147;
            } catch (NoSuchFieldError unused147) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_ADM_SUCCESS.ordinal()] = 148;
            } catch (NoSuchFieldError unused148) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_ADM_FAILED.ordinal()] = 149;
            } catch (NoSuchFieldError unused149) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_RECORD_SUCCESS.ordinal()] = 150;
            } catch (NoSuchFieldError unused150) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_RECORD_FAILED.ordinal()] = 151;
            } catch (NoSuchFieldError unused151) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_AUDIO_RECORD_START.ordinal()] = 152;
            } catch (NoSuchFieldError unused152) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_AUDIO_RECORD_SUCCESS.ordinal()] = 153;
            } catch (NoSuchFieldError unused153) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_AUDIO_RECORD_FAILED.ordinal()] = 154;
            } catch (NoSuchFieldError unused154) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_RECORD_ERROR.ordinal()] = 155;
            } catch (NoSuchFieldError unused155) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_TRACK_SUCCESS.ordinal()] = 156;
            } catch (NoSuchFieldError unused156) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_TRACK_FAILED.ordinal()] = 157;
            } catch (NoSuchFieldError unused157) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_AUDIO_TRACK_START.ordinal()] = 158;
            } catch (NoSuchFieldError unused158) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_AUDIO_TRACK_SUCCESS.ordinal()] = 159;
            } catch (NoSuchFieldError unused159) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.OPEN_AUDIO_TRACK_FAILED.ordinal()] = 160;
            } catch (NoSuchFieldError unused160) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_TRACK_ERROR.ordinal()] = 161;
            } catch (NoSuchFieldError unused161) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_TRACK_NO_DATA.ordinal()] = 162;
            } catch (NoSuchFieldError unused162) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENABLE_AUDIO_AEC.ordinal()] = 163;
            } catch (NoSuchFieldError unused163) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENABLE_AUDIO_AGC.ordinal()] = 164;
            } catch (NoSuchFieldError unused164) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENABLE_AUDIO_ANS.ordinal()] = 165;
            } catch (NoSuchFieldError unused165) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENABLE_AUDIO_3A.ordinal()] = 166;
            } catch (NoSuchFieldError unused166) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.GET_ENCODER_SUPPORT_FORMAT.ordinal()] = 167;
            } catch (NoSuchFieldError unused167) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_ENCODER_START.ordinal()] = 168;
            } catch (NoSuchFieldError unused168) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_ENCODER_SUCCESS.ordinal()] = 169;
            } catch (NoSuchFieldError unused169) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_ENCODER_FAILED.ordinal()] = 170;
            } catch (NoSuchFieldError unused170) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RESET_ENCODER_BITRATE.ordinal()] = 171;
            } catch (NoSuchFieldError unused171) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RESET_ENCODER_FRAME.ordinal()] = 172;
            } catch (NoSuchFieldError unused172) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.RESET_ENCODER_SOLUTION.ordinal()] = 173;
            } catch (NoSuchFieldError unused173) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_ENCODER.ordinal()] = 174;
            } catch (NoSuchFieldError unused174) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_ENCODER_FAILED.ordinal()] = 175;
            } catch (NoSuchFieldError unused175) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENCODER_FIRST_IDR.ordinal()] = 176;
            } catch (NoSuchFieldError unused176) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENCODER_ERROR.ordinal()] = 177;
            } catch (NoSuchFieldError unused177) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.ENCODER_TIME_EXCEED_LIMIT.ordinal()] = 178;
            } catch (NoSuchFieldError unused178) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_ENCODER.ordinal()] = 179;
            } catch (NoSuchFieldError unused179) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_ENCODER_FAILED.ordinal()] = 180;
            } catch (NoSuchFieldError unused180) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.GET_AUDIO_ENCODER_SUPPORT_FORMAT.ordinal()] = 181;
            } catch (NoSuchFieldError unused181) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_ENCODER_START.ordinal()] = 182;
            } catch (NoSuchFieldError unused182) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_ENCODER_SUCCESS.ordinal()] = 183;
            } catch (NoSuchFieldError unused183) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_ENCODER_FAILED.ordinal()] = 184;
            } catch (NoSuchFieldError unused184) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_AUDIO_ENCODER.ordinal()] = 185;
            } catch (NoSuchFieldError unused185) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_AUDIO_ENCODER_FAILED.ordinal()] = 186;
            } catch (NoSuchFieldError unused186) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_ENCODER_FIRST_PACKET.ordinal()] = 187;
            } catch (NoSuchFieldError unused187) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_ENCODER_ERROR.ordinal()] = 188;
            } catch (NoSuchFieldError unused188) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_ENCODER_TIME_EXCEED_LIMIT.ordinal()] = 189;
            } catch (NoSuchFieldError unused189) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_AUDIO_ENCODER.ordinal()] = 190;
            } catch (NoSuchFieldError unused190) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_AUDIO_ENCODER_FAILED.ordinal()] = 191;
            } catch (NoSuchFieldError unused191) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_DECODER_FAILED.ordinal()] = 192;
            } catch (NoSuchFieldError unused192) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.GET_DECODER_SUPPORT_FORMAT.ordinal()] = 193;
            } catch (NoSuchFieldError unused193) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_DECODER_START.ordinal()] = 194;
            } catch (NoSuchFieldError unused194) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_DECODER_SUCCESS.ordinal()] = 195;
            } catch (NoSuchFieldError unused195) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_DECODER_FAILED.ordinal()] = 196;
            } catch (NoSuchFieldError unused196) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_DECODER.ordinal()] = 197;
            } catch (NoSuchFieldError unused197) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_DECODER_FAILED.ordinal()] = 198;
            } catch (NoSuchFieldError unused198) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.DECODER_FIRST_FRAME.ordinal()] = 199;
            } catch (NoSuchFieldError unused199) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.DECODER_ERROR.ordinal()] = 200;
            } catch (NoSuchFieldError unused200) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.DECODER_TIME_EXCEED_LIMIT.ordinal()] = 201;
            } catch (NoSuchFieldError unused201) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_DECODER.ordinal()] = 202;
            } catch (NoSuchFieldError unused202) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_DECODER_FAILED.ordinal()] = 203;
            } catch (NoSuchFieldError unused203) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_AUDIO_DECODER.ordinal()] = 204;
            } catch (NoSuchFieldError unused204) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.START_AUDIO_DECODER_FAILED.ordinal()] = 205;
            } catch (NoSuchFieldError unused205) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_DECODER_FIRST_FRAME.ordinal()] = 206;
            } catch (NoSuchFieldError unused206) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_DECODER_ERROR.ordinal()] = 207;
            } catch (NoSuchFieldError unused207) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_DECODER_TIME_EXCEED_LIMIT.ordinal()] = 208;
            } catch (NoSuchFieldError unused208) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_AUDIO_DECODER.ordinal()] = 209;
            } catch (NoSuchFieldError unused209) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.STOP_AUDIO_DECODER_FAILED.ordinal()] = 210;
            } catch (NoSuchFieldError unused210) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_AUDIO_MIX.ordinal()] = 211;
            } catch (NoSuchFieldError unused211) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_MIX_START.ordinal()] = 212;
            } catch (NoSuchFieldError unused212) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_MIX_END.ordinal()] = 213;
            } catch (NoSuchFieldError unused213) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_MIX_ERROR.ordinal()] = 214;
            } catch (NoSuchFieldError unused214) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_MIX_OUTPUT_PARAM.ordinal()] = 215;
            } catch (NoSuchFieldError unused215) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.FIRST_FRAME_RENDER.ordinal()] = 216;
            } catch (NoSuchFieldError unused216) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.INIT_VIDEO_RENDER.ordinal()] = 217;
            } catch (NoSuchFieldError unused217) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.VIDEO_RENDER_ERROR.ordinal()] = 218;
            } catch (NoSuchFieldError unused218) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.AUDIO_TRACK_FIRST_PACKET.ordinal()] = 219;
            } catch (NoSuchFieldError unused219) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBED_USER_STREAM_LEAVE.ordinal()] = 220;
            } catch (NoSuchFieldError unused220) {
            }
            try {
                $SwitchMap$com$baidu$rtc$logreport$RtcLogEvent[RtcLogEvent.SUBSCRIBED_USER_SET_POSITION.ordinal()] = 221;
            } catch (NoSuchFieldError unused221) {
            }
        }
    }

    public static JSONObject getNewJSONObj(Object... objArr) {
        if (objArr == null || objArr.length <= 1) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < objArr.length; i += 2) {
            try {
                int i2 = i + 1;
                if (i2 < objArr.length) {
                    jSONObject.putOpt((String) objArr[i], objArr[i2]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return jSONObject;
    }

    public static JSONArray getNewJSONArray(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : objArr) {
            jSONArray.put(obj);
        }
        return jSONArray;
    }

    public static String getErrorInfo(String str) {
        return TextUtils.isEmpty(str) ? "" : str.length() > 70 ? str.substring(0, 70) : str;
    }
}
