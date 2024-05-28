package com.sinovatech.unicom.separatemodule.audience.socket;

import android.support.p086v7.app.AppCompatActivity;
import com.loopj.android.http.RequestCookie;
import com.loopj.android.http.cookie.PersistentCookieJar;
import com.loopj.android.http.cookie.cache.SetCookieCache;
import com.loopj.android.http.cookie.persistence.SharedPrefsCookiePersistor;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.sinovatech.unicom.basic.eventbus.AudienceLiveInfoEvent;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveMsg;
import io.socket.client.Ack;
import io.socket.client.C12124IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ManagerSocket {
    private static String TAG = "ManagerSocket";
    private AppCompatActivity activityContext;
    private String roomId;
    private Socket socket;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ICallBack {
        void callback(boolean z);
    }

    public ManagerSocket(AppCompatActivity appCompatActivity) {
        this.activityContext = appCompatActivity;
        initSocket();
    }

    public void initSocket() {
        try {
            C12124IO.Options options = new C12124IO.Options();
            if (URLEnvironmentConfig.getHttpPrefix().contains("https")) {
                OkHttpClient.Builder builder = new OkHttpClient.Builder();
                builder.connectTimeout(30L, TimeUnit.SECONDS);
                builder.writeTimeout(30L, TimeUnit.SECONDS);
                builder.readTimeout(30L, TimeUnit.SECONDS);
                OkHttpClient.Builder cookieJar = builder.retryOnConnectionFailure(true).cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this.activityContext), new RequestCookie()));
                OkHttpClient build = !(cookieJar instanceof OkHttpClient.Builder) ? cookieJar.build() : NBSOkHttp3Instrumentation.builderInit(cookieJar);
                C12124IO.setDefaultOkHttpWebSocketFactory(build);
                C12124IO.setDefaultOkHttpCallFactory(build);
                options.callFactory = build;
                options.webSocketFactory = build;
            }
            options.path = URLSet.Audience_SOCKET_PATH();
            this.socket = C12124IO.socket(URLSet.Audience_SOCKET_URL(), options);
            String str = TAG;
            UIUtils.logD(str, "socket地址==" + URLSet.Audience_SOCKET_URL());
            initListener();
        } catch (Exception e) {
            e.printStackTrace();
            String str2 = TAG;
            UIUtils.logD(str2, "socket错误信息==" + e.getMessage());
        }
    }

    public void startConnect() {
        Socket socket = this.socket;
        if (socket != null) {
            socket.connect();
        }
    }

    public void initListener() {
        this.socket.m1930on("connect_timeout", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.1
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到连接超时成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("ping", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.2
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到PING成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("pong", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.3
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到PONG成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on(Socket.EVENT_DISCONNECT, new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.4
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到断开连接成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("message", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.5
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到MESSAGE成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("reconnect_attempt", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.6
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到ATTEMPT成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("reconnect_error", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.7
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到重连错误成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("reconnect_failed", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.8
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到重连失败成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("reconnecting", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.9
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到正在重连成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("connect_error", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.10
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.10.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            String str = ManagerSocket.TAG;
                            UIUtils.logD(str, "监听到连接错误成功" + objArr[i]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("error", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.11
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.11.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            UIUtils.logD(ManagerSocket.TAG, i + "监听到连接错误成功22" + objArr[0]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("reconnect", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.12
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            UIUtils.logD(ManagerSocket.TAG, i + "监听到重新连接啊啊啊" + objArr[0]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on(Socket.EVENT_CONNECTING, new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.13
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.13.1
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            UIUtils.logD(ManagerSocket.TAG, i + "监听到0正在连接" + objArr[0]);
                        }
                    }
                });
            }
        });
        this.socket.m1930on("connect", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.-$$Lambda$ManagerSocket$RPiRV1b7Wga1yzAGPBqS7yoGNOg
            @Override // io.socket.emitter.Emitter.Listener
            public final void call(Object[] objArr) {
                r0.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.14
                    @Override // java.lang.Runnable
                    public void run() {
                        for (int i = 0; i < objArr.length; i++) {
                            UIUtils.logD(ManagerSocket.TAG, i + "重新连接成功" + objArr[0]);
                        }
                        ManagerSocket.this.eventLogin();
                    }
                });
            }
        });
        this.socket.m1930on("chatroom", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.15
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.15.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            JSONObject jSONObject = (JSONObject) objArr[0];
                            if (jSONObject != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("chatroom>>>>>>>>>>");
                                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                UIUtils.logD("chatroom_push_all", sb.toString());
                                LiveMsg liveMsg = new LiveMsg();
                                String optString = jSONObject.optString("type");
                                liveMsg.setType(optString);
                                if ("finish".equals(optString)) {
                                    EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                                }
                                if ("chat".equals(optString)) {
                                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                                    if (optJSONObject != null) {
                                        liveMsg.setMsg(optJSONObject.optString("msg"));
                                        liveMsg.setRid(optJSONObject.optString("rid"));
                                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("member");
                                        if (optJSONObject2 != null) {
                                            liveMsg.setNick(optJSONObject2.optString("nick"));
                                            liveMsg.setLevel(optJSONObject2.optJSONObject("attr").optString("level"));
                                            liveMsg.setMgr(optJSONObject2.optBoolean("mgr"));
                                            liveMsg.setId(optJSONObject2.optString("id"));
                                        }
                                    }
                                    EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                                }
                                if ("join".equals(optString)) {
                                    JSONObject optJSONObject3 = jSONObject.optJSONObject("member");
                                    if (optJSONObject3 != null) {
                                        liveMsg.setNick(optJSONObject3.optString("nick"));
                                        liveMsg.setLevel(optJSONObject3.optJSONObject("attr").optString("level"));
                                        liveMsg.setMgr(optJSONObject3.optBoolean("mgr"));
                                    }
                                    EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                                }
                                if ("atto".equals(optString)) {
                                    JSONObject optJSONObject4 = jSONObject.optJSONObject("data");
                                    if (optJSONObject4 != null) {
                                        liveMsg.setMsg(optJSONObject4.optString("msg"));
                                        liveMsg.setRid(optJSONObject4.optString("rid"));
                                        JSONObject optJSONObject5 = optJSONObject4.optJSONObject("member");
                                        if (optJSONObject5 != null) {
                                            liveMsg.setNick(optJSONObject5.optString("nick"));
                                            liveMsg.setLevel(optJSONObject5.optJSONObject("attr").optString("level"));
                                            liveMsg.setMgr(optJSONObject5.optBoolean("mgr"));
                                        }
                                    }
                                    EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        this.socket.m1930on("chatroom_push_all", new Emitter.Listener() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.16
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.16.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            JSONObject jSONObject = (JSONObject) objArr[0];
                            UIUtils.logD("chatroom_push_all", objArr[0].toString());
                            if (jSONObject != null) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("chatroom_push_all>>>>>>>>>>");
                                sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                UIUtils.logD("chatroom_push_all", sb.toString());
                                String optString = jSONObject.optString("rid");
                                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("any_other_param"));
                                LiveMsg liveMsg = new LiveMsg();
                                liveMsg.setType("push");
                                String optString2 = jSONObject2.optString("msgType");
                                liveMsg.setmType(optString2);
                                liveMsg.setRid(optString);
                                if ("2".equals(optString2) || "3".equals(optString2) || "4".equals(optString2)) {
                                    liveMsg.setLevel(jSONObject2.optString("level"));
                                    liveMsg.setNick(jSONObject2.optString("userNickName"));
                                }
                                if ("6".equals(optString2)) {
                                    liveMsg.setNick(jSONObject2.optString("userNickName"));
                                    liveMsg.setLevel(jSONObject2.optString("level"));
                                    liveMsg.setId(jSONObject2.optString("id"));
                                    liveMsg.setGiftNum(jSONObject2.optString("num"));
                                    liveMsg.setGiftCode(jSONObject2.optString("code"));
                                }
                                if ("14".equals(optString2)) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("chatroom_push_all>>>>14>>>>>>");
                                    sb2.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                    UIUtils.logD("chatroom_push_all", sb2.toString());
                                }
                                if ("16".equals(optString2)) {
                                    StringBuilder sb3 = new StringBuilder();
                                    sb3.append("chatroom_push_all>>>>14>>>>>>");
                                    sb3.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                    UIUtils.logD("chatroom_push_all", sb3.toString());
                                }
                                if ("700".equals(optString2)) {
                                    liveMsg.setPkScoreFirst(jSONObject2.optString("pkScoreFirst"));
                                    liveMsg.setPkScoreSecond(jSONObject2.optString("pkScoreSecond"));
                                }
                                if ("701".equals(optString2)) {
                                    liveMsg.setPkScoreFirst(jSONObject2.optString("pkScoreFirst"));
                                    liveMsg.setPkScoreSecond(jSONObject2.optString("pkScoreSecond"));
                                    liveMsg.setPkWinId(jSONObject2.optString("pkWinId"));
                                }
                                if ("710".equals(optString2)) {
                                    liveMsg.setNick(jSONObject2.optString("userNickName"));
                                    liveMsg.setLevel(jSONObject2.optString("level"));
                                    liveMsg.setId(jSONObject2.optString("id"));
                                    liveMsg.setGoodsId(jSONObject2.optString("goodsId"));
                                }
                                if ("201".equals(optString2)) {
                                    liveMsg.setNick(jSONObject2.optString("fullName"));
                                    liveMsg.setMsg(jSONObject2.optString("prizeName"));
                                    try {
                                        liveMsg.setAcType(Integer.parseInt(jSONObject2.optString("acType")));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                if ("602".equals(optString2)) {
                                    liveMsg.setId(jSONObject2.optString("id"));
                                    liveMsg.setNick(jSONObject2.optString("userImg"));
                                    liveMsg.setLevel(jSONObject2.optString("fansNum"));
                                    liveMsg.setGiftNum(jSONObject2.optString("giftNum"));
                                    liveMsg.setGiftCode(jSONObject2.optString("viewNum"));
                                }
                                EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                            }
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    public void eventLogin() {
        String ecsToken = UserManager.getInstance().getEcsToken(UserManager.getInstance().getUserAccountName());
        HashMap hashMap = new HashMap();
        hashMap.put("env", "an");
        hashMap.put("atype", "uweb");
        hashMap.put("token", ecsToken);
        hashMap.put("mobile", UserManager.getInstance().getCurrentPhoneNumber());
        this.socket.emit("login", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.17
            @Override // io.socket.client.Ack
            public void call(final Object... objArr) {
                ManagerSocket.this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.17.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ManagerSocket.this.evnetJoinRoom();
                        Object[] objArr2 = objArr;
                        JSONObject jSONObject = (JSONObject) objArr2[0];
                        if (jSONObject != null) {
                            String str = ManagerSocket.TAG;
                            StringBuilder sb = new StringBuilder();
                            sb.append("登录成功0：");
                            sb.append(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                            UIUtils.logD(str, sb.toString());
                            LiveMsg liveMsg = new LiveMsg();
                            liveMsg.setType("login");
                            liveMsg.setNick(jSONObject.optString("nick"));
                            JSONObject optJSONObject = jSONObject.optJSONObject("attr");
                            if (optJSONObject != null) {
                                liveMsg.setLevel(optJSONObject.optString("level"));
                            }
                            EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                            return;
                        }
                        JSONObject jSONObject2 = (JSONObject) objArr2[1];
                        if (jSONObject2 != null) {
                            String str2 = ManagerSocket.TAG;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("登录成功1：");
                            sb2.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                            UIUtils.logD(str2, sb2.toString());
                            JSONObject optJSONObject2 = jSONObject2.optJSONObject("account");
                            LiveMsg liveMsg2 = new LiveMsg();
                            liveMsg2.setType("login");
                            liveMsg2.setNick(optJSONObject2.optString("nick"));
                            JSONObject optJSONObject3 = optJSONObject2.optJSONObject("attr");
                            if (optJSONObject3 != null) {
                                liveMsg2.setLevel(optJSONObject3.optString("level"));
                            }
                            EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg2));
                        }
                    }
                });
            }
        });
    }

    public void evnetJoinRoom() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "join");
        hashMap.put("rid", this.roomId);
        String str = TAG;
        UIUtils.logD(str, "chatroom_push_all==重新进入直播间:" + this.roomId);
        this.socket.emit("chatroom", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.18
            @Override // io.socket.client.Ack
            public void call(Object... objArr) {
                String str2 = ManagerSocket.TAG;
                UIUtils.logD(str2, "chatroom_push_all==监听到正在连接" + objArr[1].toString());
                JSONObject jSONObject = (JSONObject) objArr[1];
                if (jSONObject != null) {
                    "9001".equals(jSONObject.optString("errcode"));
                    boolean optBoolean = jSONObject.optBoolean("mgr");
                    LiveMsg liveMsg = new LiveMsg();
                    liveMsg.setType("myJoin");
                    liveMsg.setMgr(optBoolean);
                    EventBusUtils.post(new AudienceLiveInfoEvent(EventBusUtils.EVENT_AUDIENCE_MESSAGE, liveMsg));
                }
            }
        });
    }

    public void evnetJoinRoom(final ICallBack iCallBack) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "join");
        hashMap.put("rid", this.roomId);
        String str = TAG;
        UIUtils.logD(str, "chatroom_push_all==开始加入直播间：" + this.roomId);
        this.socket.emit("chatroom", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.19
            @Override // io.socket.client.Ack
            public void call(Object... objArr) {
                boolean z;
                String str2 = ManagerSocket.TAG;
                UIUtils.logD(str2, "chatroom_push_all==监听到加入直播间回复：" + objArr[1].toString());
                JSONObject jSONObject = (JSONObject) objArr[1];
                if (jSONObject != null) {
                    "9001".equals(jSONObject.optString("errcode"));
                    z = jSONObject.optBoolean("mgr");
                } else {
                    z = false;
                }
                ICallBack iCallBack2 = iCallBack;
                if (iCallBack2 != null) {
                    iCallBack2.callback(z);
                }
            }
        });
    }

    public void eventLeaveRoom() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "leave");
        hashMap.put("rid", this.roomId);
        this.socket.emit("chatroom", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.20
            @Override // io.socket.client.Ack
            public void call(Object... objArr) {
            }
        });
    }

    public void eventFayan(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "chat");
        hashMap.put("mtype", "text");
        hashMap.put("msg", str);
        hashMap.put("rid", this.roomId);
        this.socket.emit("chatroom", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.21
            @Override // io.socket.client.Ack
            public void call(Object... objArr) {
                UIUtils.logD("chatroom", objArr.toString());
            }
        });
    }

    public void eventAtUser(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "atto");
        hashMap.put("mtype", "text");
        hashMap.put("msg", str2);
        hashMap.put("rid", this.roomId);
        hashMap.put("atto", str);
        this.socket.emit("chatroom", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.22
            @Override // io.socket.client.Ack
            public void call(Object... objArr) {
                UIUtils.logD("chatroom", objArr.toString());
            }
        });
    }

    public void eventDianzan() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", "thumb_up");
        hashMap.put("rid", this.roomId);
        this.socket.emit("chatroom", toObj(hashMap), new Ack() { // from class: com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket.23
            @Override // io.socket.client.Ack
            public void call(Object... objArr) {
            }
        });
    }

    public void onSocketStop() {
        Socket socket = this.socket;
        if (socket != null) {
            socket.off();
            this.socket.disconnect();
            UIUtils.logD(TAG, "断开了吗");
        }
    }

    private JSONObject toObj(Map<String, String> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (String str : map.keySet()) {
                jSONObject.put(str, map.get(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }
}
