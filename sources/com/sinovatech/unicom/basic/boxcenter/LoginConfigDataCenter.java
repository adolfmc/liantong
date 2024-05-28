package com.sinovatech.unicom.basic.boxcenter;

import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.p314po.LoginConfigEntity;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EncodeHelper;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import io.objectbox.Box;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginConfigDataCenter {
    private static LoginConfigDataCenter advDataCenter;
    private Box<LoginConfigEntity> box = App.getBoxStore().boxFor(LoginConfigEntity.class);

    public static synchronized LoginConfigDataCenter getInstance() {
        LoginConfigDataCenter loginConfigDataCenter;
        synchronized (LoginConfigDataCenter.class) {
            advDataCenter = new LoginConfigDataCenter();
            loginConfigDataCenter = advDataCenter;
        }
        return loginConfigDataCenter;
    }

    public void queryLoginConfig(Consumer<LoginConfigEntity> consumer) {
        LoginConfigEntity entity = getEntity();
        if (TextUtils.isEmpty(CacheDataCenter.getInstance().getLoginConfigData())) {
            entity = new LoginConfigEntity();
        }
        HashMap hashMap = new HashMap();
        String uuid = uuid();
        String provinceCode = entity.getProvinceCode();
        String version = entity.getVersion();
        String str = System.currentTimeMillis() + "";
        String deviceID = DeviceHelper.getDeviceID(true);
        String str2 = uuid + provinceCode + version + str + deviceID + "202209160919384strong";
        try {
            str2 = EncodeHelper.encoderByMd5(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        hashMap.put("seq", uuid);
        hashMap.put("provinceCode", provinceCode);
        hashMap.put("version", version);
        hashMap.put("timestamp", str);
        hashMap.put("deviceCode", deviceID);
        hashMap.put("sign", str2);
        hashMap.put("appVersion", App.getInstance().getString(2131886969));
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry entry : hashMap.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        App.getAsyncHttpClient().rxPost(URLSet.getLoginSwitch(), !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).subscribeOn(Schedulers.m1934io()).map(new Function<String, LoginConfigEntity>() { // from class: com.sinovatech.unicom.basic.boxcenter.LoginConfigDataCenter.1
            @Override // io.reactivex.functions.Function
            public LoginConfigEntity apply(String str3) throws Exception {
                JSONObject jSONObject2 = new JSONObject(str3);
                String optString = jSONObject2.optString("code");
                JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                LoginConfigEntity loginConfigEntity = new LoginConfigEntity();
                if (!"0000".equals(optString) || optJSONObject == null) {
                    LoginConfigDataCenter.this.box.removeAll();
                } else {
                    String optString2 = optJSONObject.optString("a_switch");
                    String optString3 = optJSONObject.optString("b_switch");
                    String optString4 = optJSONObject.optString("c_switch");
                    String optString5 = optJSONObject.optString("card_switch");
                    String optString6 = optJSONObject.optString("e_switch");
                    String optString7 = optJSONObject.optString("f_android_switch");
                    String optString8 = optJSONObject.optString("f_ios_switch");
                    String optString9 = optJSONObject.optString("f_switch");
                    String optString10 = optJSONObject.optString("flow");
                    String optString11 = optJSONObject.optString("lg_switch");
                    String optString12 = optJSONObject.optString("lk_switch");
                    String optString13 = optJSONObject.optString("lm_switch");
                    String optString14 = optJSONObject.optString("percent");
                    String optString15 = optJSONObject.optString("provinceCode");
                    String optString16 = optJSONObject.optString("s_switch");
                    String optString17 = optJSONObject.optString("strong_percent");
                    String optString18 = optJSONObject.optString("version");
                    String optString19 = optJSONObject.optString("w_switch");
                    String optString20 = optJSONObject.optString("loginCodeLen");
                    String optString21 = optJSONObject.optString("forgetCodeLen");
                    String optString22 = optJSONObject.optString("intervalTime");
                    String optString23 = optJSONObject.optString("start_interval");
                    String optString24 = optJSONObject.optString("voiceCountDownLen");
                    String optString25 = optJSONObject.optString("dyn_switch");
                    loginConfigEntity.setA_switch(optString2);
                    loginConfigEntity.setB_switch(optString3);
                    loginConfigEntity.setC_switch(optString4);
                    loginConfigEntity.setCard_switch(optString5);
                    loginConfigEntity.setE_switch(optString6);
                    loginConfigEntity.setF_android_switch(optString7);
                    loginConfigEntity.setF_ios_switch(optString8);
                    loginConfigEntity.setF_switch(optString9);
                    loginConfigEntity.setFlow(optString10);
                    loginConfigEntity.setLg_switch(optString11);
                    loginConfigEntity.setLk_switch(optString12);
                    loginConfigEntity.setLm_switch(optString13);
                    loginConfigEntity.setPercent(optString14);
                    loginConfigEntity.setProvinceCode(optString15);
                    loginConfigEntity.setS_switch(optString16);
                    loginConfigEntity.setStrong_percent(optString17);
                    loginConfigEntity.setVersion(optString18);
                    loginConfigEntity.setW_switch(optString19);
                    loginConfigEntity.setLoginCodeLen(optString20);
                    loginConfigEntity.setForgetCodeLen(optString21);
                    loginConfigEntity.setIntervalTime(optString22);
                    loginConfigEntity.setStart_interva(optString23);
                    loginConfigEntity.setVoiceCountDownLen(optString24);
                    loginConfigEntity.setDongtaimiyao(optString25);
                    SystemTimeUtil.setSeverTime(optJSONObject.optString("sysTime"));
                    loginConfigEntity.setEntranceSwitch(optJSONObject.optString("entranceSwitch"));
                    loginConfigEntity.setEntranceContent(optJSONObject.optString("entranceContent"));
                    loginConfigEntity.setPopupSwitch(optJSONObject.optString("popupSwitch"));
                    loginConfigEntity.setPopupContent(optJSONObject.optString("popupContent"));
                    loginConfigEntity.setPopVersion(optJSONObject.optString("popVersion"));
                    loginConfigEntity.setFailureTime(optJSONObject.optString("failureTime"));
                    loginConfigEntity.setSceneType(optJSONObject.optString("sceneType"));
                    loginConfigEntity.setDelayTime(optJSONObject.optString("delayTime"));
                    loginConfigEntity.setPayGreenChannelSwitch(optJSONObject.optString("payGreenChannelSwitch"));
                    loginConfigEntity.setPayTitle(optJSONObject.optString("payTitle"));
                    loginConfigEntity.setPayContent(optJSONObject.optString("payContent"));
                    loginConfigEntity.setPayUrl(optJSONObject.optString("payUrl"));
                    loginConfigEntity.setPrivacySwitch(optJSONObject.optString("privacySwitch"));
                    loginConfigEntity.setFaceLoginSwitch(optJSONObject.optString("faceLoginSwitch"));
                    loginConfigEntity.setEsimLoginSwitch(optJSONObject.optString("esimLoginSwitch"));
                    loginConfigEntity.setEsimCertSwitch(optJSONObject.optString("esimCertSwitch"));
                    loginConfigEntity.setEsimLoginHintText(optJSONObject.optString("esimLoginHintText"));
                    loginConfigEntity.setBroadLoginSwitch(optJSONObject.optString("broadLoginSwitch"));
                    LoginConfigDataCenter.this.putEntity(loginConfigEntity);
                    CacheDataCenter.getInstance().setLoginConfigData(str3);
                }
                return loginConfigEntity;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, new Consumer() { // from class: com.sinovatech.unicom.basic.boxcenter.-$$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public LoginConfigEntity getEntity() {
        LoginConfigEntity findFirst = this.box.query().build().findFirst();
        return findFirst == null ? new LoginConfigEntity() : findFirst;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void putEntity(LoginConfigEntity loginConfigEntity) {
        this.box.removeAll();
        this.box.put((Box<LoginConfigEntity>) loginConfigEntity);
    }

    private static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
    }
}
