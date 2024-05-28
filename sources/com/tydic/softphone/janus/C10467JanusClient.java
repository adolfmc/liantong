package com.tydic.softphone.janus;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.tydic.softphone.CallActivity;
import com.tydic.softphone.LogUtils;
import com.tydic.softphone.janus.WebSocketChannel;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

@NBSInstrumented
/* renamed from: com.tydic.softphone.janus.JanusClient */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10467JanusClient implements WebSocketChannel.WebSocketCallback {
    private static final String TAG = "JanusClient";
    private static Context context;
    private static Handler mHandler;
    public String authUser;
    public String displayName;
    private volatile boolean isKeepAliveRunning;
    private JanusCallback janusCallback;
    private String janusUrl;
    private Thread keepAliveThread;
    public String proxy;
    public String secret;
    public String userName;
    private ConcurrentHashMap<BigInteger, PluginHandle> attachedPlugins = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Transaction> transactions = new ConcurrentHashMap<>();
    private BigInteger sessionId = null;
    private WebSocketChannel webSocketChannel = new WebSocketChannel();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tydic.softphone.janus.JanusClient$JanusCallback */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface JanusCallback {
        void onAttached(BigInteger bigInteger);

        void onCreateSession(BigInteger bigInteger);

        void onDestroySession(BigInteger bigInteger);

        void onDetached(BigInteger bigInteger);

        void onError(String str);

        void onHangup(BigInteger bigInteger);

        void onIceCandidate(BigInteger bigInteger, JSONObject jSONObject);

        void onMessage(BigInteger bigInteger, BigInteger bigInteger2, JSONObject jSONObject, JSONObject jSONObject2);

        void onSubscribeAttached(BigInteger bigInteger, BigInteger bigInteger2);
    }

    @Override // com.tydic.softphone.janus.WebSocketChannel.WebSocketCallback
    public void onFailure() {
    }

    public C10467JanusClient(String str, Context context2, Handler handler) {
        this.janusUrl = str;
        this.webSocketChannel.setWebSocketCallback(this);
        context = context2;
        mHandler = handler;
    }

    public void setWebSocketChannelNull() {
        this.webSocketChannel = null;
    }

    public void setJanusCallback(JanusCallback janusCallback) {
        this.janusCallback = janusCallback;
    }

    public void connect() {
        this.webSocketChannel.connect(this.janusUrl);
    }

    public void disConnect() {
        stopKeepAliveTimer();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.close();
            this.webSocketChannel = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createSession() {
        String randomString = randomString(12);
        this.transactions.put(randomString, new Transaction(randomString) { // from class: com.tydic.softphone.janus.JanusClient.1
            @Override // com.tydic.softphone.janus.Transaction
            public void onSuccess(JSONObject jSONObject) throws Exception {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                C10467JanusClient.this.sessionId = new BigInteger(jSONObject2.getString("id"));
                LogUtils.log("create session success : " + C10467JanusClient.this.sessionId);
                C10467JanusClient.this.startKeepAliveTimer();
                if (C10467JanusClient.this.janusCallback != null) {
                    C10467JanusClient.this.janusCallback.onCreateSession(C10467JanusClient.this.sessionId);
                }
            }
        });
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("janus", "create");
            jSONObject.put("transaction", randomString);
            webSocketIsNull();
            if (this.webSocketChannel != null) {
                this.webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroySession() {
        String randomString = randomString(12);
        this.transactions.put(randomString, new Transaction(randomString) { // from class: com.tydic.softphone.janus.JanusClient.2
            @Override // com.tydic.softphone.janus.Transaction
            public void onSuccess(JSONObject jSONObject) throws Exception {
                Log.i("tydiccancletalk", "取消通话成功");
                C10467JanusClient.this.stopKeepAliveTimer();
                if (C10467JanusClient.this.janusCallback != null) {
                    C10467JanusClient.this.janusCallback.onDestroySession(C10467JanusClient.this.sessionId);
                }
            }
        });
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("janus", "destroy");
            jSONObject.put("transaction", randomString);
            jSONObject.put("session_id", this.sessionId);
            webSocketIsNull();
            if (this.webSocketChannel != null) {
                this.webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void attachPlugin(String str) {
        String randomString = randomString(12);
        this.transactions.put(randomString, new Transaction(randomString) { // from class: com.tydic.softphone.janus.JanusClient.3
            @Override // com.tydic.softphone.janus.Transaction
            public void onSuccess(JSONObject jSONObject) throws Exception {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                LogUtils.log("on attach plugin success : " + jSONObject2);
                BigInteger bigInteger = new BigInteger(jSONObject2.getString("id"));
                if (C10467JanusClient.this.janusCallback != null) {
                    C10467JanusClient.this.janusCallback.onAttached(bigInteger);
                }
                C10467JanusClient.this.attachedPlugins.put(bigInteger, new PluginHandle(bigInteger));
            }
        });
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("janus", "attach");
            jSONObject.put("transaction", randomString);
            jSONObject.put("plugin", str);
            jSONObject.put("opaque_id", "sipplugin-9ZE4tB1S46dv");
            jSONObject.put("session_id", this.sessionId);
            webSocketIsNull();
            if (this.webSocketChannel != null) {
                this.webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void subscribeAttach(BigInteger bigInteger) {
        String randomString = randomString(12);
        this.transactions.put(randomString, new Transaction(randomString, bigInteger) { // from class: com.tydic.softphone.janus.JanusClient.4
            @Override // com.tydic.softphone.janus.Transaction
            public void onSuccess(JSONObject jSONObject, BigInteger bigInteger2) throws Exception {
                BigInteger bigInteger3 = new BigInteger(jSONObject.getJSONObject("data").getString("id"));
                if (C10467JanusClient.this.janusCallback != null) {
                    C10467JanusClient.this.janusCallback.onSubscribeAttached(bigInteger3, bigInteger2);
                }
                C10467JanusClient.this.attachedPlugins.put(bigInteger3, new PluginHandle(bigInteger3));
            }
        });
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("janus", "attach");
            jSONObject.put("transaction", randomString);
            jSONObject.put("plugin", "janus.plugin.sip");
            jSONObject.put("session_id", this.sessionId);
            webSocketIsNull();
            if (this.webSocketChannel != null) {
                this.webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createOffer(BigInteger bigInteger, SessionDescription sessionDescription) {
        JSONObject jSONObject = new JSONObject();
        PrintStream printStream = System.out;
        printStream.println("create offer in janus ? " + bigInteger + " " + this.proxy);
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("request", "call");
            jSONObject2.putOpt("uri", "sip:10000218811658912@10.127.13.175:58060");
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("type", sessionDescription.type);
            jSONObject3.putOpt("sdp", sessionDescription.description);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("body", jSONObject2);
            jSONObject.putOpt("jsep", jSONObject3);
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public void subscriptionStart(BigInteger bigInteger, int i, SessionDescription sessionDescription) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("request", "start");
            jSONObject2.putOpt("room", Integer.valueOf(i));
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("body", jSONObject2);
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
            if (sessionDescription != null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.putOpt("type", sessionDescription.type);
                jSONObject3.putOpt("sdp", sessionDescription.description);
                jSONObject.putOpt("jsep", jSONObject3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public void call(BigInteger bigInteger, SessionDescription sessionDescription, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("request", "call");
            jSONObject2.putOpt("uri", str);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("type", sessionDescription.type);
            jSONObject3.putOpt("sdp", sessionDescription.description);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("body", jSONObject2);
            jSONObject.putOpt("jsep", jSONObject3);
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public void publish(BigInteger bigInteger, SessionDescription sessionDescription) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("request", "publish");
            jSONObject2.putOpt("audio", true);
            jSONObject2.putOpt("video", true);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.putOpt("type", sessionDescription.type);
            jSONObject3.putOpt("sdp", sessionDescription.description);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("body", jSONObject2);
            jSONObject.putOpt("jsep", jSONObject3);
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public void subscribe(BigInteger bigInteger, int i, BigInteger bigInteger2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("ptype", "subscriber");
            jSONObject2.putOpt("request", "join");
            jSONObject2.putOpt("room", Integer.valueOf(i));
            jSONObject2.putOpt("feed", bigInteger2);
            jSONObject.put("body", jSONObject2);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public void trickleCandidate(BigInteger bigInteger, IceCandidate iceCandidate) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("candidate", iceCandidate.sdp);
            jSONObject.putOpt("sdpMid", iceCandidate.sdpMid);
            jSONObject.putOpt("sdpMLineIndex", Integer.valueOf(iceCandidate.sdpMLineIndex));
            jSONObject2.putOpt("janus", "trickle");
            jSONObject2.putOpt("candidate", jSONObject);
            jSONObject2.putOpt("transaction", randomString(12));
            jSONObject2.putOpt("session_id", this.sessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        }
    }

    public void trickleCandidateComplete(BigInteger bigInteger) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt("completed", true);
            jSONObject2.putOpt("janus", "trickle");
            jSONObject2.putOpt("candidate", jSONObject);
            jSONObject2.putOpt("transaction", randomString(12));
            jSONObject2.putOpt("session_id", this.sessionId);
            jSONObject2.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        }
    }

    public void joinRoom(BigInteger bigInteger, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("display", str);
            jSONObject2.putOpt("ptype", "publisher");
            jSONObject2.putOpt("request", "join");
            jSONObject2.putOpt("room", Integer.valueOf(i));
            jSONObject.put("body", jSONObject2);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.tydic.softphone.janus.JanusClient$5] */
    @Override // com.tydic.softphone.janus.WebSocketChannel.WebSocketCallback
    public void onOpen() {
        Message message = new Message();
        message.what = 5;
        message.setData(new Bundle());
        mHandler.handleMessage(message);
        new Thread() { // from class: com.tydic.softphone.janus.JanusClient.5
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    Thread.sleep(100L);
                    C10467JanusClient.this.createSession();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.tydic.softphone.janus.WebSocketChannel.WebSocketCallback
    public void onMessage(String str) {
        Transaction transaction;
        Transaction transaction2;
        Log.d(TAG, "收到消息》》》" + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            JanusMessageType fromString = JanusMessageType.fromString(jSONObject.getString("janus"));
            String string = jSONObject.has("transaction") ? jSONObject.getString("transaction") : null;
            BigInteger bigInteger = jSONObject.has("sender") ? new BigInteger(jSONObject.getString("sender")) : null;
            PluginHandle pluginHandle = bigInteger != null ? this.attachedPlugins.get(bigInteger) : null;
            switch (fromString) {
                case keepalive:
                case ack:
                case hangup:
                    return;
                case success:
                    if (string == null || (transaction = this.transactions.get(string)) == null) {
                        return;
                    }
                    try {
                        if (transaction.getFeedId() != null) {
                            transaction.onSuccess(jSONObject, transaction.getFeedId());
                        } else {
                            transaction.onSuccess(jSONObject);
                        }
                        this.transactions.remove(string);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                case error:
                    if (string == null || (transaction2 = this.transactions.get(string)) == null) {
                        return;
                    }
                    transaction2.onError();
                    this.transactions.remove(string);
                    return;
                case detached:
                    if (pluginHandle == null || this.janusCallback == null) {
                        return;
                    }
                    this.janusCallback.onDetached(pluginHandle.getHandleId());
                    return;
                case event:
                    if (pluginHandle != null) {
                        JSONObject jSONObject2 = jSONObject.has("plugindata") ? jSONObject.getJSONObject("plugindata") : null;
                        if (jSONObject2 != null) {
                            JSONObject jSONObject3 = jSONObject2.has("data") ? jSONObject2.getJSONObject("data") : null;
                            JSONObject jSONObject4 = jSONObject.has("jsep") ? jSONObject.getJSONObject("jsep") : null;
                            if (this.janusCallback != null) {
                                this.janusCallback.onMessage(bigInteger, pluginHandle.getHandleId(), jSONObject3, jSONObject4);
                                break;
                            }
                        }
                    }
                    break;
                case trickle:
                    break;
                case destroy:
                    if (this.janusCallback != null) {
                        this.janusCallback.onDestroySession(this.sessionId);
                        return;
                    }
                    return;
                default:
                    return;
            }
            if (pluginHandle == null || !jSONObject.has("candidate")) {
                return;
            }
            JSONObject jSONObject5 = jSONObject.getJSONObject("candidate");
            if (this.janusCallback != null) {
                this.janusCallback.onIceCandidate(pluginHandle.getHandleId(), jSONObject5);
            }
        } catch (JSONException e2) {
            JanusCallback janusCallback = this.janusCallback;
            if (janusCallback != null) {
                janusCallback.onError(e2.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startKeepAliveTimer() {
        this.isKeepAliveRunning = true;
        this.keepAliveThread = new Thread(new Runnable() { // from class: com.tydic.softphone.janus.JanusClient.6
            @Override // java.lang.Runnable
            public void run() {
                while (C10467JanusClient.this.isKeepAliveRunning && !Thread.interrupted()) {
                    try {
                        if (CallActivity.isFinishCall) {
                            C10467JanusClient.this.stopKeepAliveTimer();
                        }
                        Thread.sleep(25000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (C10467JanusClient.this.webSocketChannel != null && C10467JanusClient.this.webSocketChannel.isConnected()) {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("janus", "keepalive");
                                jSONObject.put("session_id", C10467JanusClient.this.sessionId);
                                jSONObject.put("transaction", C10467JanusClient.this.randomString(12));
                                Log.i("keepAlive", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                C10467JanusClient.this.webSocketIsNull();
                                if (C10467JanusClient.this.webSocketChannel != null) {
                                    C10467JanusClient.this.webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
                                }
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            Log.e(C10467JanusClient.TAG, "keepAlive failed websocket is null or not connected");
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
                Log.d(C10467JanusClient.TAG, "keepAlive thread stopped");
            }
        }, "KeepAlive");
        this.keepAliveThread.start();
    }

    public void stopKeepAliveTimer() {
        Log.i("tydic555", "stopKeepAliveTimer");
        this.isKeepAliveRunning = false;
        Log.i("tydic555", String.valueOf(this.keepAliveThread));
        Thread thread = this.keepAliveThread;
        if (thread != null) {
            thread.interrupt();
        }
    }

    @Override // com.tydic.softphone.janus.WebSocketChannel.WebSocketCallback
    public void onClosed() {
        stopKeepAliveTimer();
    }

    public void webSocketIsNull() {
        if (this.webSocketChannel == null) {
            onFailure();
        }
    }

    public String randomString(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt(62)));
        }
        return sb.toString();
    }

    public void unregisterUser(BigInteger bigInteger) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("request", "unregister");
            jSONObject.put("body", jSONObject2);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    public void registerUser(BigInteger bigInteger, String str, String str2, String str3, String str4) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("request", "register");
            jSONObject2.putOpt("display", this.displayName);
            jSONObject2.putOpt("proxy", str);
            jSONObject2.putOpt("username", str3);
            jSONObject2.putOpt("authuser", str2);
            jSONObject2.putOpt("secret", str4);
            jSONObject.put("body", jSONObject2);
            jSONObject.putOpt("janus", "message");
            jSONObject.putOpt("transaction", randomString(12));
            jSONObject.putOpt("session_id", this.sessionId);
            jSONObject.putOpt("handle_id", bigInteger);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        boolean z = jSONObject instanceof JSONObject;
        Log.i("tydic", !z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        webSocketIsNull();
        WebSocketChannel webSocketChannel = this.webSocketChannel;
        if (webSocketChannel != null) {
            webSocketChannel.sendMessage(!z ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }
}
