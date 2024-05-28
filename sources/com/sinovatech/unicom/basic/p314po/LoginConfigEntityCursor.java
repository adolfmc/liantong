package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.LoginConfigEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.LoginConfigEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class LoginConfigEntityCursor extends Cursor<LoginConfigEntity> {
    private static final LoginConfigEntity_.LoginConfigEntityIdGetter ID_GETTER = LoginConfigEntity_.__ID_GETTER;
    private static final int __ID_a_switch = LoginConfigEntity_.a_switch.f24389id;
    private static final int __ID_b_switch = LoginConfigEntity_.b_switch.f24389id;
    private static final int __ID_c_switch = LoginConfigEntity_.c_switch.f24389id;
    private static final int __ID_card_switch = LoginConfigEntity_.card_switch.f24389id;
    private static final int __ID_e_switch = LoginConfigEntity_.e_switch.f24389id;
    private static final int __ID_f_android_switch = LoginConfigEntity_.f_android_switch.f24389id;
    private static final int __ID_f_ios_switch = LoginConfigEntity_.f_ios_switch.f24389id;
    private static final int __ID_f_switch = LoginConfigEntity_.f_switch.f24389id;
    private static final int __ID_flow = LoginConfigEntity_.flow.f24389id;
    private static final int __ID_lg_switch = LoginConfigEntity_.lg_switch.f24389id;
    private static final int __ID_lk_switch = LoginConfigEntity_.lk_switch.f24389id;
    private static final int __ID_lm_switch = LoginConfigEntity_.lm_switch.f24389id;
    private static final int __ID_percent = LoginConfigEntity_.percent.f24389id;
    private static final int __ID_provinceCode = LoginConfigEntity_.provinceCode.f24389id;
    private static final int __ID_s_switch = LoginConfigEntity_.s_switch.f24389id;
    private static final int __ID_strong_percent = LoginConfigEntity_.strong_percent.f24389id;
    private static final int __ID_version = LoginConfigEntity_.version.f24389id;
    private static final int __ID_w_switch = LoginConfigEntity_.w_switch.f24389id;
    private static final int __ID_forgetCodeLen = LoginConfigEntity_.forgetCodeLen.f24389id;
    private static final int __ID_loginCodeLen = LoginConfigEntity_.loginCodeLen.f24389id;
    private static final int __ID_intervalTime = LoginConfigEntity_.intervalTime.f24389id;
    private static final int __ID_start_interva = LoginConfigEntity_.start_interva.f24389id;
    private static final int __ID_dongtaimiyao = LoginConfigEntity_.dongtaimiyao.f24389id;
    private static final int __ID_voiceCountDownLen = LoginConfigEntity_.voiceCountDownLen.f24389id;
    private static final int __ID_entranceSwitch = LoginConfigEntity_.entranceSwitch.f24389id;
    private static final int __ID_popupContent = LoginConfigEntity_.popupContent.f24389id;
    private static final int __ID_entranceContent = LoginConfigEntity_.entranceContent.f24389id;
    private static final int __ID_popupSwitch = LoginConfigEntity_.popupSwitch.f24389id;
    private static final int __ID_popVersion = LoginConfigEntity_.popVersion.f24389id;
    private static final int __ID_failureTime = LoginConfigEntity_.failureTime.f24389id;
    private static final int __ID_sceneType = LoginConfigEntity_.sceneType.f24389id;
    private static final int __ID_delayTime = LoginConfigEntity_.delayTime.f24389id;
    private static final int __ID_payGreenChannelSwitch = LoginConfigEntity_.payGreenChannelSwitch.f24389id;
    private static final int __ID_payTitle = LoginConfigEntity_.payTitle.f24389id;
    private static final int __ID_payContent = LoginConfigEntity_.payContent.f24389id;
    private static final int __ID_payUrl = LoginConfigEntity_.payUrl.f24389id;
    private static final int __ID_privacySwitch = LoginConfigEntity_.privacySwitch.f24389id;
    private static final int __ID_faceLoginSwitch = LoginConfigEntity_.faceLoginSwitch.f24389id;
    private static final int __ID_esimLoginSwitch = LoginConfigEntity_.esimLoginSwitch.f24389id;
    private static final int __ID_esimCertSwitch = LoginConfigEntity_.esimCertSwitch.f24389id;
    private static final int __ID_esimLoginHintText = LoginConfigEntity_.esimLoginHintText.f24389id;
    private static final int __ID_broadLoginSwitch = LoginConfigEntity_.broadLoginSwitch.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.LoginConfigEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<LoginConfigEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<LoginConfigEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new LoginConfigEntityCursor(transaction, j, boxStore);
        }
    }

    public LoginConfigEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, LoginConfigEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(LoginConfigEntity loginConfigEntity) {
        return ID_GETTER.getId(loginConfigEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(LoginConfigEntity loginConfigEntity) {
        String a_switch = loginConfigEntity.getA_switch();
        int i = a_switch != null ? __ID_a_switch : 0;
        String b_switch = loginConfigEntity.getB_switch();
        int i2 = b_switch != null ? __ID_b_switch : 0;
        String c_switch = loginConfigEntity.getC_switch();
        int i3 = c_switch != null ? __ID_c_switch : 0;
        String card_switch = loginConfigEntity.getCard_switch();
        collect400000(this.cursor, 0L, 1, i, a_switch, i2, b_switch, i3, c_switch, card_switch != null ? __ID_card_switch : 0, card_switch);
        String e_switch = loginConfigEntity.getE_switch();
        int i4 = e_switch != null ? __ID_e_switch : 0;
        String f_android_switch = loginConfigEntity.getF_android_switch();
        int i5 = f_android_switch != null ? __ID_f_android_switch : 0;
        String f_ios_switch = loginConfigEntity.getF_ios_switch();
        int i6 = f_ios_switch != null ? __ID_f_ios_switch : 0;
        String f_switch = loginConfigEntity.getF_switch();
        collect400000(this.cursor, 0L, 0, i4, e_switch, i5, f_android_switch, i6, f_ios_switch, f_switch != null ? __ID_f_switch : 0, f_switch);
        String flow = loginConfigEntity.getFlow();
        int i7 = flow != null ? __ID_flow : 0;
        String lg_switch = loginConfigEntity.getLg_switch();
        int i8 = lg_switch != null ? __ID_lg_switch : 0;
        String lk_switch = loginConfigEntity.getLk_switch();
        int i9 = lk_switch != null ? __ID_lk_switch : 0;
        String lm_switch = loginConfigEntity.getLm_switch();
        collect400000(this.cursor, 0L, 0, i7, flow, i8, lg_switch, i9, lk_switch, lm_switch != null ? __ID_lm_switch : 0, lm_switch);
        String percent = loginConfigEntity.getPercent();
        int i10 = percent != null ? __ID_percent : 0;
        String provinceCode = loginConfigEntity.getProvinceCode();
        int i11 = provinceCode != null ? __ID_provinceCode : 0;
        String s_switch = loginConfigEntity.getS_switch();
        int i12 = s_switch != null ? __ID_s_switch : 0;
        String strong_percent = loginConfigEntity.getStrong_percent();
        collect400000(this.cursor, 0L, 0, i10, percent, i11, provinceCode, i12, s_switch, strong_percent != null ? __ID_strong_percent : 0, strong_percent);
        String version = loginConfigEntity.getVersion();
        int i13 = version != null ? __ID_version : 0;
        String w_switch = loginConfigEntity.getW_switch();
        int i14 = w_switch != null ? __ID_w_switch : 0;
        String forgetCodeLen = loginConfigEntity.getForgetCodeLen();
        int i15 = forgetCodeLen != null ? __ID_forgetCodeLen : 0;
        String loginCodeLen = loginConfigEntity.getLoginCodeLen();
        collect400000(this.cursor, 0L, 0, i13, version, i14, w_switch, i15, forgetCodeLen, loginCodeLen != null ? __ID_loginCodeLen : 0, loginCodeLen);
        String intervalTime = loginConfigEntity.getIntervalTime();
        int i16 = intervalTime != null ? __ID_intervalTime : 0;
        String start_interva = loginConfigEntity.getStart_interva();
        int i17 = start_interva != null ? __ID_start_interva : 0;
        String dongtaimiyao = loginConfigEntity.getDongtaimiyao();
        int i18 = dongtaimiyao != null ? __ID_dongtaimiyao : 0;
        String voiceCountDownLen = loginConfigEntity.getVoiceCountDownLen();
        collect400000(this.cursor, 0L, 0, i16, intervalTime, i17, start_interva, i18, dongtaimiyao, voiceCountDownLen != null ? __ID_voiceCountDownLen : 0, voiceCountDownLen);
        String entranceSwitch = loginConfigEntity.getEntranceSwitch();
        int i19 = entranceSwitch != null ? __ID_entranceSwitch : 0;
        String popupContent = loginConfigEntity.getPopupContent();
        int i20 = popupContent != null ? __ID_popupContent : 0;
        String entranceContent = loginConfigEntity.getEntranceContent();
        int i21 = entranceContent != null ? __ID_entranceContent : 0;
        String popupSwitch = loginConfigEntity.getPopupSwitch();
        collect400000(this.cursor, 0L, 0, i19, entranceSwitch, i20, popupContent, i21, entranceContent, popupSwitch != null ? __ID_popupSwitch : 0, popupSwitch);
        String popVersion = loginConfigEntity.getPopVersion();
        int i22 = popVersion != null ? __ID_popVersion : 0;
        String failureTime = loginConfigEntity.getFailureTime();
        int i23 = failureTime != null ? __ID_failureTime : 0;
        String sceneType = loginConfigEntity.getSceneType();
        int i24 = sceneType != null ? __ID_sceneType : 0;
        String delayTime = loginConfigEntity.getDelayTime();
        collect400000(this.cursor, 0L, 0, i22, popVersion, i23, failureTime, i24, sceneType, delayTime != null ? __ID_delayTime : 0, delayTime);
        String payGreenChannelSwitch = loginConfigEntity.getPayGreenChannelSwitch();
        int i25 = payGreenChannelSwitch != null ? __ID_payGreenChannelSwitch : 0;
        String payTitle = loginConfigEntity.getPayTitle();
        int i26 = payTitle != null ? __ID_payTitle : 0;
        String payContent = loginConfigEntity.getPayContent();
        int i27 = payContent != null ? __ID_payContent : 0;
        String payUrl = loginConfigEntity.getPayUrl();
        collect400000(this.cursor, 0L, 0, i25, payGreenChannelSwitch, i26, payTitle, i27, payContent, payUrl != null ? __ID_payUrl : 0, payUrl);
        String privacySwitch = loginConfigEntity.getPrivacySwitch();
        int i28 = privacySwitch != null ? __ID_privacySwitch : 0;
        String faceLoginSwitch = loginConfigEntity.getFaceLoginSwitch();
        int i29 = faceLoginSwitch != null ? __ID_faceLoginSwitch : 0;
        String esimLoginSwitch = loginConfigEntity.getEsimLoginSwitch();
        int i30 = esimLoginSwitch != null ? __ID_esimLoginSwitch : 0;
        String esimCertSwitch = loginConfigEntity.getEsimCertSwitch();
        collect400000(this.cursor, 0L, 0, i28, privacySwitch, i29, faceLoginSwitch, i30, esimLoginSwitch, esimCertSwitch != null ? __ID_esimCertSwitch : 0, esimCertSwitch);
        String esimLoginHintText = loginConfigEntity.getEsimLoginHintText();
        int i31 = esimLoginHintText != null ? __ID_esimLoginHintText : 0;
        String broadLoginSwitch = loginConfigEntity.getBroadLoginSwitch();
        long collect313311 = collect313311(this.cursor, loginConfigEntity.getId(), 2, i31, esimLoginHintText, broadLoginSwitch != null ? __ID_broadLoginSwitch : 0, broadLoginSwitch, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        loginConfigEntity.setId(collect313311);
        return collect313311;
    }
}
