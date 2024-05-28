package com.sinovatech.unicom.basic.p314po;

import android.text.TextUtils;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.InterfaceC12072Id;

@Entity
/* renamed from: com.sinovatech.unicom.basic.po.LoginConfigEntity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LoginConfigEntity {
    private String a_switch;
    private String b_switch;
    private String broadLoginSwitch;
    private String c_switch;
    private String card_switch;
    private String delayTime;
    private String dongtaimiyao;
    private String e_switch;
    private String entranceContent;
    private String entranceSwitch;
    private String esimCertSwitch;
    private String esimLoginHintText;
    private String esimLoginSwitch;
    private String f_android_switch;
    private String f_ios_switch;
    private String f_switch;
    private String faceLoginSwitch;
    private String failureTime;
    private String flow;
    private String forgetCodeLen;
    @InterfaceC12072Id

    /* renamed from: id */
    private long f18384id;
    private String intervalTime;
    private String lg_switch;
    private String lk_switch;
    private String lm_switch;
    private String loginCodeLen;
    private String payContent;
    private String payGreenChannelSwitch;
    private String payTitle;
    private String payUrl;
    private String percent;
    private String popVersion;
    private String popupContent;
    private String popupSwitch;
    private String privacySwitch;
    private String provinceCode;
    private String s_switch;
    private String sceneType;
    private String start_interva;
    private String strong_percent;
    private String version;
    private String voiceCountDownLen;
    private String w_switch;

    public long getId() {
        return this.f18384id;
    }

    public void setId(long j) {
        this.f18384id = j;
    }

    public String getA_switch() {
        return this.a_switch;
    }

    public void setA_switch(String str) {
        this.a_switch = str;
    }

    public String getB_switch() {
        return this.b_switch;
    }

    public void setB_switch(String str) {
        this.b_switch = str;
    }

    public String getC_switch() {
        return this.c_switch;
    }

    public void setC_switch(String str) {
        this.c_switch = str;
    }

    public String getCard_switch() {
        return this.card_switch;
    }

    public void setCard_switch(String str) {
        this.card_switch = str;
    }

    public String getE_switch() {
        return this.e_switch;
    }

    public void setE_switch(String str) {
        this.e_switch = str;
    }

    public String getF_android_switch() {
        return this.f_android_switch;
    }

    public void setF_android_switch(String str) {
        this.f_android_switch = str;
    }

    public String getF_ios_switch() {
        return this.f_ios_switch;
    }

    public void setF_ios_switch(String str) {
        this.f_ios_switch = str;
    }

    public String getF_switch() {
        return this.f_switch;
    }

    public void setF_switch(String str) {
        this.f_switch = str;
    }

    public String getFlow() {
        return this.flow;
    }

    public void setFlow(String str) {
        this.flow = str;
    }

    public String getLg_switch() {
        return this.lg_switch;
    }

    public void setLg_switch(String str) {
        this.lg_switch = str;
    }

    public String getLk_switch() {
        return this.lk_switch;
    }

    public void setLk_switch(String str) {
        this.lk_switch = str;
    }

    public String getLm_switch() {
        return this.lm_switch;
    }

    public void setLm_switch(String str) {
        this.lm_switch = str;
    }

    public String getPercent() {
        return this.percent;
    }

    public void setPercent(String str) {
        this.percent = str;
    }

    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public String getS_switch() {
        return this.s_switch;
    }

    public void setS_switch(String str) {
        this.s_switch = str;
    }

    public String getStrong_percent() {
        return this.strong_percent;
    }

    public void setStrong_percent(String str) {
        this.strong_percent = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getW_switch() {
        return this.w_switch;
    }

    public void setW_switch(String str) {
        this.w_switch = str;
    }

    public String getForgetCodeLen() {
        return this.forgetCodeLen;
    }

    public void setForgetCodeLen(String str) {
        this.forgetCodeLen = str;
    }

    public String getLoginCodeLen() {
        if (TextUtils.isEmpty(this.loginCodeLen)) {
            this.loginCodeLen = "6";
        }
        return this.loginCodeLen;
    }

    public void setLoginCodeLen(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "6";
        }
        this.loginCodeLen = str;
    }

    public String getIntervalTime() {
        return this.intervalTime;
    }

    public void setIntervalTime(String str) {
        this.intervalTime = str;
    }

    public String getStart_interva() {
        return this.start_interva;
    }

    public void setStart_interva(String str) {
        this.start_interva = str;
    }

    public String getVoiceCountDownLen() {
        return this.voiceCountDownLen;
    }

    public long getVoiceCountDownLenLong() {
        try {
            if (!TextUtils.isEmpty(this.voiceCountDownLen)) {
                long parseLong = Long.parseLong(this.voiceCountDownLen) * 1000;
                if (parseLong > 60000 || parseLong < 0) {
                    return 50000L;
                }
                return parseLong;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 50000L;
    }

    public void setVoiceCountDownLen(String str) {
        this.voiceCountDownLen = str;
    }

    public String getEntranceSwitch() {
        return this.entranceSwitch;
    }

    public void setEntranceSwitch(String str) {
        this.entranceSwitch = str;
    }

    public String getPopupContent() {
        return this.popupContent;
    }

    public void setPopupContent(String str) {
        this.popupContent = str;
    }

    public String getEntranceContent() {
        return this.entranceContent;
    }

    public void setEntranceContent(String str) {
        this.entranceContent = str;
    }

    public String getPopupSwitch() {
        return this.popupSwitch;
    }

    public void setPopupSwitch(String str) {
        this.popupSwitch = str;
    }

    public String getPopVersion() {
        return this.popVersion;
    }

    public void setPopVersion(String str) {
        this.popVersion = str;
    }

    public String getFailureTime() {
        return this.failureTime;
    }

    public void setFailureTime(String str) {
        this.failureTime = str;
    }

    public String getDongtaimiyao() {
        return this.dongtaimiyao;
    }

    public void setDongtaimiyao(String str) {
        this.dongtaimiyao = str;
    }

    public String getSceneType() {
        return this.sceneType;
    }

    public void setSceneType(String str) {
        this.sceneType = str;
    }

    public String getDelayTime() {
        return this.delayTime;
    }

    public int getDelayTimeInt() {
        try {
            int parseInt = Integer.parseInt(this.delayTime);
            if (parseInt < 1) {
                parseInt = 1;
            }
            if (parseInt > 59) {
                return 59;
            }
            return parseInt;
        } catch (Exception e) {
            e.printStackTrace();
            return 100;
        }
    }

    public void setDelayTime(String str) {
        this.delayTime = str;
    }

    public String getPayGreenChannelSwitch() {
        return this.payGreenChannelSwitch;
    }

    public void setPayGreenChannelSwitch(String str) {
        this.payGreenChannelSwitch = str;
    }

    public String getPayTitle() {
        return this.payTitle;
    }

    public void setPayTitle(String str) {
        this.payTitle = str;
    }

    public String getPayContent() {
        return this.payContent;
    }

    public void setPayContent(String str) {
        this.payContent = str;
    }

    public String getPayUrl() {
        return this.payUrl;
    }

    public void setPayUrl(String str) {
        this.payUrl = str;
    }

    public String getPrivacySwitch() {
        return this.privacySwitch;
    }

    public void setPrivacySwitch(String str) {
        this.privacySwitch = str;
    }

    public String getFaceLoginSwitch() {
        return this.faceLoginSwitch;
    }

    public void setFaceLoginSwitch(String str) {
        this.faceLoginSwitch = str;
    }

    public String getEsimLoginSwitch() {
        return this.esimLoginSwitch;
    }

    public void setEsimLoginSwitch(String str) {
        this.esimLoginSwitch = str;
    }

    public String getEsimCertSwitch() {
        return this.esimCertSwitch;
    }

    public void setEsimCertSwitch(String str) {
        this.esimCertSwitch = str;
    }

    public String getEsimLoginHintText() {
        return this.esimLoginHintText;
    }

    public void setEsimLoginHintText(String str) {
        this.esimLoginHintText = str;
    }

    public String getBroadLoginSwitch() {
        return this.broadLoginSwitch;
    }

    public void setBroadLoginSwitch(String str) {
        this.broadLoginSwitch = str;
    }
}
