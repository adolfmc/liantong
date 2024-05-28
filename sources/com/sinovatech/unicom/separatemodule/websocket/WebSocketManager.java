package com.sinovatech.unicom.separatemodule.websocket;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.eventbus.EdopXunJianEvent;
import com.sinovatech.unicom.basic.eventbus.NoticShuaxinEvent;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.NoticeWakefulService;
import com.sinovatech.unicom.separatemodule.notice.PushMessageEntity;
import com.sinovatech.unicom.separatemodule.notice.PushServer;
import com.sinovatech.unicom.separatemodule.notice.utils.AesUtil;
import com.sinovatech.unicom.separatemodule.notice.utils.JsonUtil;
import com.sinovatech.unicom.separatemodule.notice.utils.PushReq;
import com.sinovatech.unicom.separatemodule.notice.utils.ReqBody;
import com.sinovatech.unicom.separatemodule.notice.utils.ReqHead;
import com.sinovatech.unicom.separatemodule.webrtc.RtcWebInstance;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class WebSocketManager {
    public static final String PUSHDIANHUA = "PUSH1003";
    public static final String PUSHEDOPXUNJIAN = "PUSH1004";
    public static final String PUSHKEFUXIAOXI = "PUSH0009";
    public static final String PUSHLOCALGAME = "PUSH_localgame";
    public static final String PUSHYINGYETING = "PUSH1002";
    private static MySocketClient socketClient = null;
    public static boolean webSocketIsActive = true;
    private WebSocket connection;
    private Disposable timeDisposable;
    private WebSocketManager webSocketManager;

    public void startConnect() {
        if (UIUtils.isFastClick()) {
            if (!SystemServiceUtils.netIsAvailable()) {
                webSocketIsActive = false;
            } else if (BaseActivity.flag) {
            } else {
                webSocketIsActive = true;
                if (App.hasLogined()) {
                    try {
                        if (this.connection != null && this.connection.isOpen()) {
                            register("PUSH1001");
                            UIUtils.logD("MySocketClient", "startRegister:");
                            return;
                        }
                        socketClient = new MySocketClient(new URI(URLSet.getWebsocketUrl()), new Draft_17());
                        this.connection = socketClient.getConnection();
                        if (this.connection.isConnecting() || this.connection.isOpen()) {
                            return;
                        }
                        socketClient.connect();
                        UIUtils.logD("MySocketClient", "startConnect:");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void closeConnect() {
        try {
            if (this.connection == null || !this.connection.isOpen()) {
                return;
            }
            this.connection.close();
            UIUtils.logD("MySocketClient", "closeConnect：手动结束回调");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isOpen() {
        try {
            return this.connection.isOpen();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String str) {
        try {
            if (this.connection.isClosing() || this.connection.isClosed()) {
                return;
            }
            socketClient.send(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class MySocketClient extends WebSocketClient {
        private Disposable disposable;

        private MySocketClient(URI uri, Draft draft) {
            super(uri, draft);
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onOpen(ServerHandshake serverHandshake) {
            try {
                UIUtils.logD("MySocketClient", "onOpen:" + serverHandshake.getHttpStatusMessage());
                WebSocketManager.this.register("PUSH1001");
                this.disposable = WebSocketManager.this.active();
                WebSocketManager.this.timeDisposable = this.disposable;
                WebSocketManager.webSocketIsActive = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onMessage(String str) {
            try {
                UIUtils.logD("MySocketClient", "onMessage:" + str);
                if (!TextUtils.isEmpty(str) && !str.startsWith("{")) {
                    str = "{" + str + "}";
                }
                WebSocketManager.this.handlerMessage(str);
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onMessage(ByteBuffer byteBuffer) {
            try {
                String string = WebSocketManager.this.getString(byteBuffer);
                if (!TextUtils.isEmpty(string) && !string.startsWith("{")) {
                    string = "{" + string + "}";
                }
                UIUtils.logD("MySocketClient", "onMessage:" + string);
                WebSocketManager.this.handlerMessage(string);
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onClose(int i, String str, boolean z) {
            UIUtils.logD("MySocketClient", "onClose回调:" + i);
            if (i == 1006) {
                WebSocketManager.this.closeConnect();
            }
            WebSocketManager.webSocketIsActive = false;
            try {
                if (this.disposable == null || this.disposable.isDisposed()) {
                    return;
                }
                this.disposable.dispose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // org.java_websocket.client.WebSocketClient
        public void onError(Exception exc) {
            WebSocketManager.webSocketIsActive = false;
            WebSocketManager.this.closeConnect();
            try {
                UIUtils.logD("MySocketClient", "onError" + exc.getMessage());
                if (this.disposable == null || this.disposable.isDisposed()) {
                    return;
                }
                this.disposable.dispose();
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void register(String str) {
        try {
            JSONObject jSONObject = new JSONObject(getRequestJSON(str));
            JSONObject jSONObject2 = jSONObject.getJSONObject("reqBody");
            JSONObject jSONObject3 = new JSONObject();
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            jSONObject3.put("userMobile", currentPhoneNumber);
            jSONObject3.put("token", UserManager.getInstance().getEcsToken(currentPhoneNumber));
            jSONObject2.put("userInfo", jSONObject3);
            jSONObject.put("reqBody", jSONObject2);
            sendMessage(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
            UIUtils.logD("MySocketClient", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Disposable active() {
        return Observable.interval(5000L, 3000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketManager.1
            @Override // io.reactivex.functions.Consumer
            public void accept(Long l) {
                if (App.hasLogined()) {
                    WebSocketManager.this.sendMessage("un");
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketManager.2
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                th.getMessage();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reApplay(String str, JSONArray jSONArray, String str2) {
        if (App.hasLogined()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.put("id", jSONArray);
                JSONObject jSONObject2 = new JSONObject();
                JSONObject optJSONObject = jSONObject.optJSONObject("respHead");
                optJSONObject.put("bipCode", str2);
                jSONObject2.put("reqHead", optJSONObject);
                jSONObject2.put("id", jSONArray);
                sendMessage(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                UIUtils.logD("MySocketClient", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getRequestJSON(String str) {
        String[] split = App.getInstance().getString(2131886969).split("@");
        String flowNumber = getFlowNumber();
        String uuid = getUUID();
        int random = getRandom();
        String aesEncrypt = AesUtil.aesEncrypt(uuid + getRandomMD5Str(random));
        String deviceID = DeviceHelper.getDeviceID(true);
        PushReq pushReq = new PushReq();
        ReqHead reqHead = new ReqHead();
        ReqBody reqBody = new ReqBody();
        reqHead.setProcId(flowNumber);
        reqHead.setSrcCode(uuid);
        reqHead.setAesStr(aesEncrypt);
        reqHead.setAesIndex(String.valueOf(random));
        reqHead.setBipCode(str);
        reqHead.setVersion(App.getInstance().getString(2131886969));
        reqBody.setDeviceId(deviceID);
        reqBody.setClientType(split[0]);
        reqBody.setClientVersion(split[1]);
        reqBody.setPhoneModel(DeviceHelper.getDeviceModel());
        pushReq.setReqHead(reqHead);
        pushReq.setReqBody(reqBody);
        return JsonUtil.getJson(pushReq);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handlerMessage(final String str) {
        try {
            if (App.hasLogined()) {
                String optString = new JSONObject(str).optString("return");
                if ("un_ok".equals(optString)) {
                    return;
                }
                if ("un_faile".equals(optString)) {
                    startConnect();
                } else {
                    Observable.create(new ObservableOnSubscribe<Object>() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketManager.5
                        @Override // io.reactivex.ObservableOnSubscribe
                        public void subscribe(ObservableEmitter<Object> observableEmitter) throws Exception {
                            MsLogUtil.m7979d("handlerMessage", "content:  " + str);
                            JSONObject jSONObject = new JSONObject(str);
                            String optString2 = jSONObject.optJSONObject("respHead").optString("bipCode");
                            JSONArray optJSONArray = jSONObject.optJSONObject("respBody").optJSONArray("messages");
                            if (WebSocketManager.PUSHKEFUXIAOXI.equals(optString2) || WebSocketManager.PUSHYINGYETING.equals(optString2) || WebSocketManager.PUSHDIANHUA.equals(optString2) || (WebSocketManager.PUSHEDOPXUNJIAN.equals(optString2) && optJSONArray.length() > 0)) {
                                if (WebSocketManager.PUSHKEFUXIAOXI.equals(optString2)) {
                                    CacheDataCenter.getInstance().setKefuPushData(str);
                                }
                                Object[] parseBackData = PushServer.parseBackData(str);
                                if (WebSocketManager.PUSHYINGYETING.equals(optString2)) {
                                    NoticeWakefulService.handleResult(App.getInstance(), parseBackData);
                                }
                                parseBackData[0] = optString2;
                                observableEmitter.onNext(parseBackData);
                            }
                            observableEmitter.onComplete();
                        }
                    }).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketManager.3
                        @Override // io.reactivex.functions.Consumer
                        public void accept(Object obj) throws Exception {
                            Object[] objArr = (Object[]) obj;
                            String str2 = (String) objArr[0];
                            List<PushMessageEntity> list = (List) objArr[1];
                            if (list != null) {
                                JSONArray jSONArray = new JSONArray();
                                for (PushMessageEntity pushMessageEntity : list) {
                                    jSONArray.put(pushMessageEntity.getId());
                                }
                                if (!WebSocketManager.PUSHKEFUXIAOXI.equals(str2)) {
                                    WebSocketManager.this.reApplay(str, jSONArray, str2);
                                }
                                if (list.size() > 0) {
                                    PushMessageEntity pushMessageEntity2 = (PushMessageEntity) list.get(0);
                                    if (WebSocketManager.PUSHDIANHUA.equals(str2)) {
                                        RtcWebInstance.getInstance().setContext(SoulPermission.getInstance().getTopActivity());
                                        RtcWebInstance.getInstance().setUrl(pushMessageEntity2.getUrl());
                                    } else if (WebSocketManager.PUSHEDOPXUNJIAN.equals(str2)) {
                                        WebSocketManager.this.getEdopXunJian(pushMessageEntity2);
                                    } else {
                                        WebSocketNoticDialog.show(str2, pushMessageEntity2, list.size() > 1);
                                        EventBusUtils.post(new NoticShuaxinEvent(EventBusUtils.EVENTNOTICNEI_));
                                    }
                                }
                            }
                        }
                    }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.websocket.WebSocketManager.4
                        @Override // io.reactivex.functions.Consumer
                        public void accept(Throwable th) throws Exception {
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getEdopXunJian(PushMessageEntity pushMessageEntity) {
        try {
            String[] split = pushMessageEntity.getContent().split(",");
            if (split.length != 5) {
                return;
            }
            String str = split[0];
            String str2 = split[1];
            String str3 = split[2];
            String str4 = split[3];
            String str5 = split[4];
            long currentTimeMillis = System.currentTimeMillis();
            long parseLong = Long.parseLong(str3);
            long parseLong2 = Long.parseLong(str4);
            if (currentTimeMillis >= parseLong && currentTimeMillis <= parseLong2) {
                EdopXunJianEvent edopXunJianEvent = new EdopXunJianEvent(EventBusUtils.EVENT_EDOP_XUNJIAN);
                edopXunJianEvent.setDecrypt(str5);
                edopXunJianEvent.setEdopAppid(str);
                edopXunJianEvent.setBatchId(str2);
                edopXunJianEvent.setTimeMillis(currentTimeMillis);
                EventBusUtils.post(edopXunJianEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFlowNumber() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return "yh00001" + simpleDateFormat.format(date);
    }

    private String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }

    private int getRandom() {
        return (new Random().nextInt(9) % 10) + 0;
    }

    private String getRandomMD5Str(int i) {
        return new String[]{"CfAaVIBblv+0ZpR4tL96fw==", "ng3qqXjlHhjV7AyQ07Wd6Q==", "1JHdO8EfZz9H6lHir+klAQ==", "fha8y/FXu9WA3XJRTiiHkA==", "aGYYxBVCoiz6J/PlmKAjIQ==", "n5sniU+su4J0s4OqorWhkQ==", "8CtdIe4aN53MJaWVwSPZBg==", "u+D1kao8M8dnxmo6lVgnIg==", "EXrTBaCCXBqN0/WMpLvPkA==", "paAX4WcC5cK7n2Z14C70aw=="}[i];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getString(ByteBuffer byteBuffer) {
        try {
            String replace = Charset.forName("UTF-8").newDecoder().decode(byteBuffer.asReadOnlyBuffer()).toString().replace("\\", "");
            return replace.substring(1, replace.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
