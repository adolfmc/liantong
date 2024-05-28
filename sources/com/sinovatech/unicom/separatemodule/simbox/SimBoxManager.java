package com.sinovatech.unicom.separatemodule.simbox;

import android.text.TextUtils;
import android.util.Base64;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.p201hb.oma.encryp.AESUtils;
import com.p201hb.oma.encryp.ShaEncrypt;
import com.p201hb.omapi.union.sim.SmartCard;
import com.p201hb.omapi.union.sim.bean.ErrCode;
import com.p201hb.omapi.union.sim.bean.SIMInfo;
import com.p201hb.omapi.union.sim.listener.ConnectListener;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RSACryptos;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.simbox.bean.C9463B1;
import com.sinovatech.unicom.separatemodule.simbox.bean.C9464B2;
import com.sinovatech.unicom.separatemodule.simbox.bean.C9465B3;
import com.sinovatech.unicom.separatemodule.simbox.bean.C9466B4;
import com.sinovatech.unicom.separatemodule.simbox.encryp.ResultExt;
import io.socket.client.Socket;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SimBoxManager.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0012\u0010\u001c\u001a\u00020\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\u0012\u0010\u001f\u001a\u00020\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u001eH\u0002J\b\u0010!\u001a\u00020\u001aH\u0002J\b\u0010\"\u001a\u00020\u001aH\u0002J\u0006\u0010#\u001a\u00020\u001aJ\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'H\u0016J\u0012\u0010(\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\b\u0010)\u001a\u00020\u001aH\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0018\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u00062\u0006\u0010-\u001a\u00020\u0006H\u0002J\u0006\u0010.\u001a\u00020\u001aJ\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u0006H\u0002J\u0010\u00100\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u0006H\u0002J\u0010\u00101\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0010\u00102\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020\u0006H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\u0006X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\b\"\u0004\b\u000f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0004R\u001a\u0010\u0013\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\b\"\u0004\b\u0015\u0010\nR\u001a\u0010\u0016\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\b\"\u0004\b\u0018\u0010\n¨\u00063"}, m1890d2 = {"Lcom/sinovatech/unicom/separatemodule/simbox/SimBoxManager;", "Lcom/hb/omapi/union/sim/listener/ConnectListener;", "jsPlugin", "Lcom/sinovatech/unicom/separatemodule/simbox/SimBoxJSPlugin;", "(Lcom/sinovatech/unicom/separatemodule/simbox/SimBoxJSPlugin;)V", "iccid", "", "getIccid", "()Ljava/lang/String;", "setIccid", "(Ljava/lang/String;)V", "idcard", "getIdcard", "imsi", "getImsi", "setImsi", "getJsPlugin", "()Lcom/sinovatech/unicom/separatemodule/simbox/SimBoxJSPlugin;", "setJsPlugin", "key", "getKey", "setKey", "simNumber", "getSimNumber", "setSimNumber", "appendItem", "", "type", "callbackFail", "s", "", "callbackSuccess", "jsonObject", "checkKey", "close", "connect", "delSecurityInformation", "finish", "err", "", "getJiamiStr", "getSIMState", "getSecurityInformation", "modifyPassword", "oldPin", "pin", "onExec", "resetPassword", "setPassword", "updateItem", "verifyPassword", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SimBoxManager implements ConnectListener {
    @NotNull
    private String iccid;
    @NotNull
    private final String idcard;
    @NotNull
    private String imsi;
    @NotNull
    private SimBoxJSPlugin jsPlugin;
    @NotNull
    private String key;
    @NotNull
    private String simNumber;

    public SimBoxManager(@NotNull SimBoxJSPlugin jsPlugin) {
        Intrinsics.checkParameterIsNotNull(jsPlugin, "jsPlugin");
        this.jsPlugin = jsPlugin;
        this.key = "";
        this.iccid = "";
        this.imsi = "";
        this.simNumber = "1";
        this.idcard = "TMbysCou8fcZSbmfNqdFQu/Wmmvrxzvgox5Emso26xXsWd2pDuc/J7dlre8w2Ct023caQK8jq4Yz34GVQQEdSH11BbMuBx5Q3gD9/6JNhSqWzMw6q+Hy4gAayvXKCHzejgys94LJ8tAiJUNcbgb7BFk1dTMa9xFTEtZs4YR0xOo=";
    }

    @NotNull
    public final SimBoxJSPlugin getJsPlugin() {
        return this.jsPlugin;
    }

    public final void setJsPlugin(@NotNull SimBoxJSPlugin simBoxJSPlugin) {
        Intrinsics.checkParameterIsNotNull(simBoxJSPlugin, "<set-?>");
        this.jsPlugin = simBoxJSPlugin;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    public final void setKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.key = str;
    }

    @NotNull
    public final String getIccid() {
        return this.iccid;
    }

    public final void setIccid(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.iccid = str;
    }

    @NotNull
    public final String getImsi() {
        return this.imsi;
    }

    public final void setImsi(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.imsi = str;
    }

    @NotNull
    public final String getSimNumber() {
        return this.simNumber;
    }

    public final void setSimNumber(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.simNumber = str;
    }

    public final void onExec() {
        checkKey();
        String optString = this.jsPlugin.parameterJO.optString("type");
        String passWord = this.jsPlugin.parameterJO.optString("passWord");
        String oldPassWord = this.jsPlugin.parameterJO.optString("oldPassWord");
        String secondType = this.jsPlugin.parameterJO.optString("secondeType");
        if (optString == null) {
            return;
        }
        switch (optString.hashCode()) {
            case -2139709107:
                if (optString.equals("appendItem")) {
                    Intrinsics.checkExpressionValueIsNotNull(secondType, "secondType");
                    appendItem(secondType);
                    return;
                }
                return;
            case -1089614531:
                if (optString.equals("setPassWord")) {
                    Intrinsics.checkExpressionValueIsNotNull(passWord, "passWord");
                    setPassword(passWord);
                    return;
                }
                return;
            case -1009432720:
                if (optString.equals("getSIMState")) {
                    getSIMState();
                    return;
                }
                return;
            case -916030826:
                if (optString.equals("getSecurityInformation")) {
                    Intrinsics.checkExpressionValueIsNotNull(secondType, "secondType");
                    getSecurityInformation(secondType);
                    return;
                }
                return;
            case -393737262:
                if (optString.equals("closeConnect")) {
                    close();
                    return;
                }
                return;
            case -296248452:
                if (optString.equals("updateItem")) {
                    Intrinsics.checkExpressionValueIsNotNull(secondType, "secondType");
                    updateItem(secondType);
                    return;
                }
                return;
            case -121202751:
                if (optString.equals("delSecurityInformation")) {
                    Intrinsics.checkExpressionValueIsNotNull(secondType, "secondType");
                    delSecurityInformation(secondType);
                    return;
                }
                return;
            case -24412918:
                if (optString.equals("resetPassword")) {
                    Intrinsics.checkExpressionValueIsNotNull(passWord, "passWord");
                    resetPassword(passWord);
                    return;
                }
                return;
            case 530405532:
                if (optString.equals(Socket.EVENT_DISCONNECT)) {
                    SmartCard.Companion.close();
                    return;
                }
                return;
            case 951351530:
                if (optString.equals("connect")) {
                    connect();
                    return;
                }
                return;
            case 1601191285:
                if (optString.equals("modifyPassword")) {
                    Intrinsics.checkExpressionValueIsNotNull(oldPassWord, "oldPassWord");
                    Intrinsics.checkExpressionValueIsNotNull(passWord, "passWord");
                    modifyPassword(oldPassWord, passWord);
                    return;
                }
                return;
            case 1792351796:
                if (optString.equals("verifyPassword")) {
                    Intrinsics.checkExpressionValueIsNotNull(passWord, "passWord");
                    verifyPassword(passWord);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void connect() {
        UIUtils.logD("SimboxTime", "000--->" + System.currentTimeMillis());
        SmartCard.Companion.connect(this);
    }

    private final void getSIMState() {
        String optString = this.jsPlugin.parameterJO.optString("simNumber");
        Intrinsics.checkExpressionValueIsNotNull(optString, "jsPlugin.parameterJO.optString(\"simNumber\")");
        this.simNumber = optString;
        List<SIMInfo> support = SmartCard.Companion.getSupport();
        if (support.size() > 0) {
            SIMInfo sIMInfo = support.get(0);
            if (Intrinsics.areEqual("2", this.simNumber) && support.size() > 1) {
                sIMInfo = support.get(1);
            }
            SmartCard.Companion.setSIM(sIMInfo.getName());
            HashMap<String, Object> applicationInformation = SmartCard.Companion.getApplicationInformation();
            Object obj = applicationInformation.get("CODE");
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
            }
            if (((Integer) obj).intValue() == ErrCode.Companion.getError_Success()) {
                Object obj2 = applicationInformation.get("ISPSW");
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                String str = (String) obj2;
                String valueOf = String.valueOf(StringsKt.first(str));
                String valueOf2 = String.valueOf(StringsKt.last(str));
                Object obj3 = applicationInformation.get("ICCID");
                if (obj3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                this.iccid = (String) obj3;
                Object obj4 = applicationInformation.get("IMSI");
                if (obj4 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                this.imsi = (String) obj4;
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("passwordState", valueOf);
                jSONObject.put("leftChance", valueOf2);
                jSONObject.put("is5G", sIMInfo.is5G());
                jSONObject.put("size", support.size());
                jSONObject.put("iccId", this.iccid);
                jSONObject.put("imsi", this.imsi);
                jSONObject.put("imei", DeviceHelper.getDeviceID(true));
                callbackSuccess(jSONObject);
                return;
            }
            callbackFail("连接失败" + applicationInformation.get("msg"));
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("is5G", false);
        jSONObject2.put("passwordState", "0");
        jSONObject2.put("leftChance", "0");
        callbackSuccess(jSONObject2);
    }

    @Override // com.p201hb.omapi.union.sim.listener.ConnectListener
    public void finish(int i) {
        UIUtils.logD("SimboxTime", "111--->" + System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackSuccess(Object obj) {
        this.jsPlugin.callbackSuccess(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void callbackFail(Object obj) {
        this.jsPlugin.callbackFail(obj);
    }

    private final void setPassword(String str) {
        String encrypt = AESUtils.INSTANCE.encrypt(this.key, str);
        if (encrypt != null) {
            int password = SmartCard.Companion.setPassword(encrypt);
            if (password == ErrCode.Companion.getError_Success()) {
                callbackSuccess("设置密码成功");
                return;
            }
            callbackFail("设置密码失败" + password);
        }
    }

    private final void verifyPassword(String str) {
        String encrypt = AESUtils.INSTANCE.encrypt(this.key, str);
        if (encrypt != null) {
            int verifyPassword = SmartCard.Companion.verifyPassword(encrypt);
            if (verifyPassword == ErrCode.Companion.getError_Success()) {
                callbackSuccess("校验密码成功");
                return;
            }
            callbackFail("校验密码失败" + verifyPassword);
        }
    }

    private final void modifyPassword(String str, String str2) {
        String encrypt = AESUtils.INSTANCE.encrypt(this.key, str);
        String encrypt2 = AESUtils.INSTANCE.encrypt(this.key, str2);
        if (encrypt != null && encrypt2 != null) {
            int modifyPassword = SmartCard.Companion.modifyPassword(encrypt, encrypt2);
            if (modifyPassword == ErrCode.Companion.getError_Success()) {
                callbackSuccess("修改密码成功");
                return;
            }
            callbackFail("修改密码失败" + modifyPassword);
            return;
        }
        callbackFail("修改密码失败,数据有误");
    }

    private final void resetPassword(final String str) {
        if (TextUtils.isEmpty(this.imsi) || TextUtils.isEmpty(this.iccid)) {
            UIUtils.toastCenterLong("获取SIM卡状态失败，请退出重试");
            return;
        }
        String optString = this.jsPlugin.parameterJO.optString("name");
        String idCard = this.jsPlugin.parameterJO.optString("idCard");
        String mobile = this.jsPlugin.parameterJO.optString("mobile");
        String optString2 = this.jsPlugin.parameterJO.optString("smsCode");
        RequestParams requestParams = new RequestParams();
        requestParams.put("name", optString);
        Intrinsics.checkExpressionValueIsNotNull(idCard, "idCard");
        requestParams.put("idCard", getJiamiStr(idCard));
        Intrinsics.checkExpressionValueIsNotNull(mobile, "mobile");
        requestParams.put("mobile", getJiamiStr(mobile));
        requestParams.put("smsCode", optString2);
        requestParams.put("iccid", getJiamiStr(this.iccid));
        requestParams.put("imsi", getJiamiStr(this.imsi));
        requestParams.put("imei", DeviceHelper.getDeviceID(true));
        App.getAsyncHttpClient().post(URLSet.getSimboxUrl(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.simbox.SimBoxManager$resetPassword$1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, @Nullable String str2) {
                super.onSuccess(i, str2);
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    String optString3 = jSONObject.optString("code");
                    String optString4 = jSONObject.optString("desc");
                    if (!"0000".equals(optString3)) {
                        SimBoxManager.this.callbackFail(str2);
                        return;
                    }
                    HashMap<String, Object> applicationInformation = SmartCard.Companion.getApplicationInformation();
                    Object obj = applicationInformation.get("ICCID");
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    byte[] bytes = ResultExt.toBytes((String) obj);
                    Object obj2 = applicationInformation.get("IMSI");
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                    }
                    byte[] bytes2 = ResultExt.toBytes((String) obj2);
                    String hex = ResultExt.toHex(ArraysKt.plus(ArraysKt.copyOfRange(bytes, bytes.length - 3, bytes.length), ArraysKt.copyOfRange(bytes2, bytes2.length - 3, bytes2.length)));
                    String encrypt = AESUtils.INSTANCE.encrypt(SimBoxManager.this.getKey(), str);
                    String encrypt2 = AESUtils.INSTANCE.encrypt(SimBoxManager.this.getKey(), hex);
                    if (encrypt == null || encrypt2 == null) {
                        SimBoxManager.this.callbackFail(optString4);
                        return;
                    }
                    int resetPassword = SmartCard.Companion.resetPassword(encrypt, encrypt2);
                    if (resetPassword == ErrCode.Companion.getError_Success()) {
                        SimBoxManager.this.callbackSuccess(str2);
                        return;
                    }
                    SimBoxManager simBoxManager = SimBoxManager.this;
                    simBoxManager.callbackFail("重置密码失败" + resetPassword);
                } catch (Exception e) {
                    SimBoxManager simBoxManager2 = SimBoxManager.this;
                    simBoxManager2.callbackFail("异常" + e.getMessage());
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(@Nullable Throwable th, @Nullable String str2) {
                super.onFailure(th, str2);
                SimBoxManager simBoxManager = SimBoxManager.this;
                StringBuilder sb = new StringBuilder();
                sb.append("请求失败");
                sb.append(th != null ? th.getMessage() : null);
                simBoxManager.callbackFail(sb.toString());
            }
        });
    }

    private final void getSecurityInformation(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            HashMap<String, Object> securityInformation = SmartCard.Companion.getSecurityInformation(str);
            try {
                Object obj = securityInformation.get("msg");
                if (obj == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                String str2 = (String) obj;
                if (str2 == null || "".equals(str2)) {
                    callbackFail(jSONArray);
                    return;
                }
                AESUtils aESUtils = AESUtils.INSTANCE;
                String str3 = this.key;
                Object obj2 = securityInformation.get("msg");
                if (obj2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
                String decrypt = aESUtils.decrypt(str3, (String) obj2);
                if (decrypt == null) {
                    callbackFail(jSONArray);
                    return;
                }
                switch (str.hashCode()) {
                    case 2095:
                        if (str.equals("B1")) {
                            List<C9463B1> listB1 = C9463B1.Companion.getListB1(decrypt);
                            for (C9463B1 c9463b1 : listB1) {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("title", c9463b1.getTitle());
                                jSONObject.put("user", c9463b1.getUser());
                                jSONObject.put("psw", c9463b1.getPsw());
                                jSONObject.put("queryPsw", "");
                                jSONObject.put("phoneOsw", "");
                                jSONObject.put("address", c9463b1.getAddress());
                                jSONObject.put("remark", c9463b1.getRemark());
                                jSONObject.put("recId", c9463b1.getRecId());
                                jSONObject.put("time", c9463b1.getTime());
                                jSONArray.put(jSONObject);
                            }
                            UIUtils.logD("SimBoxManager", listB1.toString());
                            break;
                        }
                        break;
                    case 2096:
                        if (str.equals("B2")) {
                            for (C9464B2 c9464b2 : C9464B2.Companion.getListB2(decrypt)) {
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("title", c9464b2.getTitle());
                                jSONObject2.put("user", c9464b2.getAccount());
                                jSONObject2.put("psw", c9464b2.getWithdrawalPsw());
                                jSONObject2.put("queryPsw", c9464b2.getQueryPsw());
                                jSONObject2.put("phoneOsw", c9464b2.getPhonePsw());
                                jSONObject2.put("address", "");
                                jSONObject2.put("remark", c9464b2.getRemark());
                                jSONObject2.put("recId", c9464b2.getRecId());
                                jSONObject2.put("time", c9464b2.getTime());
                                jSONArray.put(jSONObject2);
                            }
                            break;
                        }
                        break;
                    case 2097:
                        if (str.equals("B3")) {
                            for (C9465B3 c9465b3 : C9465B3.Companion.getListB3(decrypt)) {
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("title", c9465b3.getTitle());
                                jSONObject3.put("user", c9465b3.getWifi());
                                jSONObject3.put("psw", c9465b3.getPsw());
                                jSONObject3.put("queryPsw", "");
                                jSONObject3.put("phoneOsw", "");
                                jSONObject3.put("address", "");
                                jSONObject3.put("remark", c9465b3.getRemark());
                                jSONObject3.put("recId", c9465b3.getRecId());
                                jSONObject3.put("time", c9465b3.getTime());
                                jSONArray.put(jSONObject3);
                            }
                            break;
                        }
                        break;
                    case 2098:
                        if (str.equals("B4")) {
                            for (C9466B4 c9466b4 : C9466B4.Companion.getListB4(decrypt)) {
                                JSONObject jSONObject4 = new JSONObject();
                                jSONObject4.put("title", c9466b4.getTitle());
                                jSONObject4.put("user", "");
                                jSONObject4.put("psw", "");
                                jSONObject4.put("queryPsw", "");
                                jSONObject4.put("phoneOsw", "");
                                jSONObject4.put("address", "");
                                jSONObject4.put("remark", c9466b4.getRemark());
                                jSONObject4.put("recId", c9466b4.getRecId());
                                jSONObject4.put("time", c9466b4.getTime());
                                jSONArray.put(jSONObject4);
                            }
                            break;
                        }
                        break;
                }
                if (jSONArray.length() > 0) {
                    callbackSuccess(jSONArray);
                } else {
                    callbackFail(jSONArray);
                }
            } catch (Exception unused) {
                callbackFail(jSONArray);
            }
        } catch (Exception unused2) {
            callbackFail(jSONArray);
        }
    }

    private final void appendItem(String str) {
        String str2 = null;
        String title = this.jsPlugin.parameterJO.optString("title");
        String user = this.jsPlugin.parameterJO.optString("user");
        String psw = this.jsPlugin.parameterJO.optString("psw");
        String queryPsw = this.jsPlugin.parameterJO.optString("queryPsw");
        String phoneOsw = this.jsPlugin.parameterJO.optString("phoneOsw");
        String address = this.jsPlugin.parameterJO.optString("address");
        String remark = this.jsPlugin.parameterJO.optString("remark");
        switch (str.hashCode()) {
            case 2095:
                if (str.equals("B1")) {
                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                    Intrinsics.checkExpressionValueIsNotNull(user, "user");
                    Intrinsics.checkExpressionValueIsNotNull(psw, "psw");
                    Intrinsics.checkExpressionValueIsNotNull(address, "address");
                    Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                    str2 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(new C9463B1(title, user, psw, address, remark).getB1Bytes()));
                    break;
                }
                break;
            case 2096:
                if (str.equals("B2")) {
                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                    Intrinsics.checkExpressionValueIsNotNull(user, "user");
                    Intrinsics.checkExpressionValueIsNotNull(address, "address");
                    Intrinsics.checkExpressionValueIsNotNull(psw, "psw");
                    Intrinsics.checkExpressionValueIsNotNull(queryPsw, "queryPsw");
                    Intrinsics.checkExpressionValueIsNotNull(phoneOsw, "phoneOsw");
                    Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                    str2 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(new C9464B2(title, user, address, psw, queryPsw, phoneOsw, remark).getB2Bytes()));
                    break;
                }
                break;
            case 2097:
                if (str.equals("B3")) {
                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                    Intrinsics.checkExpressionValueIsNotNull(user, "user");
                    Intrinsics.checkExpressionValueIsNotNull(psw, "psw");
                    Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                    str2 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(new C9465B3(title, user, psw, remark).getB3Bytes()));
                    break;
                }
                break;
            case 2098:
                if (str.equals("B4")) {
                    Intrinsics.checkExpressionValueIsNotNull(title, "title");
                    Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                    str2 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(new C9466B4(title, remark).getB4Bytes()));
                    break;
                }
                break;
        }
        if (str2 == null) {
            return;
        }
        int appendItem = SmartCard.Companion.appendItem(str, str2);
        if (appendItem == ErrCode.Companion.getError_Success()) {
            callbackSuccess("添加成功");
            return;
        }
        callbackFail("添加失败" + appendItem);
    }

    private final void delSecurityInformation(String str) {
        String recId = this.jsPlugin.parameterJO.optString("recId");
        String time = this.jsPlugin.parameterJO.optString("time");
        try {
            SmartCard.Companion companion = SmartCard.Companion;
            Intrinsics.checkExpressionValueIsNotNull(recId, "recId");
            Intrinsics.checkExpressionValueIsNotNull(time, "time");
            int delSecurityInformation = companion.delSecurityInformation(recId, str, time);
            if (delSecurityInformation == ErrCode.Companion.getError_Success()) {
                callbackSuccess("删除成功");
            } else {
                callbackFail("删除失败" + delSecurityInformation);
            }
        } catch (Exception e) {
            callbackFail("删除失败" + e.getMessage());
        }
    }

    private final void updateItem(String str) {
        String title = this.jsPlugin.parameterJO.optString("title");
        String user = this.jsPlugin.parameterJO.optString("user");
        String psw = this.jsPlugin.parameterJO.optString("psw");
        String queryPsw = this.jsPlugin.parameterJO.optString("queryPsw");
        String phoneOsw = this.jsPlugin.parameterJO.optString("phoneOsw");
        String address = this.jsPlugin.parameterJO.optString("address");
        String remark = this.jsPlugin.parameterJO.optString("remark");
        String recId = this.jsPlugin.parameterJO.optString("recId");
        String time = this.jsPlugin.parameterJO.optString("time");
        int i = 0;
        try {
            switch (str.hashCode()) {
                case 2095:
                    if (str.equals("B1")) {
                        Intrinsics.checkExpressionValueIsNotNull(title, "title");
                        Intrinsics.checkExpressionValueIsNotNull(user, "user");
                        Intrinsics.checkExpressionValueIsNotNull(psw, "psw");
                        Intrinsics.checkExpressionValueIsNotNull(address, "address");
                        Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                        C9463B1 c9463b1 = new C9463B1(title, user, psw, address, remark);
                        Intrinsics.checkExpressionValueIsNotNull(recId, "recId");
                        c9463b1.setRecId(recId);
                        Intrinsics.checkExpressionValueIsNotNull(time, "time");
                        c9463b1.setTime(time);
                        String encrypt = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(c9463b1.getB1Update()));
                        if (encrypt == null) {
                            callbackFail("更新失败0");
                            return;
                        }
                        i = SmartCard.Companion.updateItem(recId, "B1", encrypt);
                        break;
                    }
                    break;
                case 2096:
                    if (str.equals("B2")) {
                        Intrinsics.checkExpressionValueIsNotNull(title, "title");
                        Intrinsics.checkExpressionValueIsNotNull(user, "user");
                        Intrinsics.checkExpressionValueIsNotNull(address, "address");
                        Intrinsics.checkExpressionValueIsNotNull(psw, "psw");
                        Intrinsics.checkExpressionValueIsNotNull(queryPsw, "queryPsw");
                        Intrinsics.checkExpressionValueIsNotNull(phoneOsw, "phoneOsw");
                        Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                        C9464B2 c9464b2 = new C9464B2(title, user, address, psw, queryPsw, phoneOsw, remark);
                        Intrinsics.checkExpressionValueIsNotNull(recId, "recId");
                        c9464b2.setRecId(recId);
                        Intrinsics.checkExpressionValueIsNotNull(time, "time");
                        c9464b2.setTime(time);
                        String encrypt2 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(c9464b2.getB2Update()));
                        if (encrypt2 == null) {
                            callbackFail("更新失败0");
                            return;
                        }
                        i = SmartCard.Companion.updateItem(recId, "B2", encrypt2);
                        break;
                    }
                    break;
                case 2097:
                    if (str.equals("B3")) {
                        Intrinsics.checkExpressionValueIsNotNull(title, "title");
                        Intrinsics.checkExpressionValueIsNotNull(user, "user");
                        Intrinsics.checkExpressionValueIsNotNull(psw, "psw");
                        Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                        C9465B3 c9465b3 = new C9465B3(title, user, psw, remark);
                        Intrinsics.checkExpressionValueIsNotNull(recId, "recId");
                        c9465b3.setRecId(recId);
                        Intrinsics.checkExpressionValueIsNotNull(time, "time");
                        c9465b3.setTime(time);
                        String encrypt3 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(c9465b3.getB3Update()));
                        if (encrypt3 == null) {
                            callbackFail("更新失败0");
                            return;
                        }
                        i = SmartCard.Companion.updateItem(recId, "B3", encrypt3);
                        break;
                    }
                    break;
                case 2098:
                    if (str.equals("B4")) {
                        Intrinsics.checkExpressionValueIsNotNull(title, "title");
                        Intrinsics.checkExpressionValueIsNotNull(remark, "remark");
                        C9466B4 c9466b4 = new C9466B4(title, remark);
                        Intrinsics.checkExpressionValueIsNotNull(recId, "recId");
                        c9466b4.setRecId(recId);
                        Intrinsics.checkExpressionValueIsNotNull(time, "time");
                        c9466b4.setTime(time);
                        String encrypt4 = AESUtils.INSTANCE.encrypt(this.key, ResultExt.toHex(c9466b4.getB4Update()));
                        if (encrypt4 == null) {
                            callbackFail("更新失败0");
                            return;
                        }
                        i = SmartCard.Companion.updateItem(recId, "B4", encrypt4);
                        break;
                    }
                    break;
            }
            if (i == ErrCode.Companion.getError_Success()) {
                callbackSuccess("更新成功");
                return;
            }
            callbackFail("更新失败" + ErrCode.Companion.getErrMsg(i));
        } catch (Exception e) {
            callbackFail("更新失败" + e.getMessage());
        }
    }

    private final void close() {
        SmartCard.Companion.close();
    }

    private final void checkKey() {
        String SHA256;
        String str = this.key;
        if ((str == null || "".equals(str)) && (SHA256 = ShaEncrypt.INSTANCE.SHA256(SmartCard.Companion.getRandom(1, 8))) != null) {
            this.key = ShaEncrypt.INSTANCE.getSecretkey(SHA256);
        }
    }

    private final String getJiamiStr(String str) {
        try {
            Charset forName = Charset.forName("UTF-8");
            Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(\"UTF-8\")");
            if (str != null) {
                byte[] bytes = str.getBytes(forName);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                return Base64.encodeToString(RSACryptos.encryptByPublicKey(bytes, Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", 2)), 2);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    @NotNull
    public final String getIdcard() {
        return this.idcard;
    }
}
