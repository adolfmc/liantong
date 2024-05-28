package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.LoginConfigEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.LoginConfigEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LoginConfigEntity_ implements EntityInfo<LoginConfigEntity> {
    public static final Property<LoginConfigEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "LoginConfigEntity";
    public static final int __ENTITY_ID = 37;
    public static final String __ENTITY_NAME = "LoginConfigEntity";
    public static final Property<LoginConfigEntity> __ID_PROPERTY;
    public static final Class<LoginConfigEntity> __ENTITY_CLASS = LoginConfigEntity.class;
    public static final CursorFactory<LoginConfigEntity> __CURSOR_FACTORY = new LoginConfigEntityCursor.Factory();
    @Internal
    static final LoginConfigEntityIdGetter __ID_GETTER = new LoginConfigEntityIdGetter();
    public static final LoginConfigEntity_ __INSTANCE = new LoginConfigEntity_();

    /* renamed from: id */
    public static final Property<LoginConfigEntity> f18385id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<LoginConfigEntity> a_switch = new Property<>(__INSTANCE, 1, 2, String.class, "a_switch");
    public static final Property<LoginConfigEntity> b_switch = new Property<>(__INSTANCE, 2, 3, String.class, "b_switch");
    public static final Property<LoginConfigEntity> c_switch = new Property<>(__INSTANCE, 3, 4, String.class, "c_switch");
    public static final Property<LoginConfigEntity> card_switch = new Property<>(__INSTANCE, 4, 5, String.class, "card_switch");
    public static final Property<LoginConfigEntity> e_switch = new Property<>(__INSTANCE, 5, 6, String.class, "e_switch");
    public static final Property<LoginConfigEntity> f_android_switch = new Property<>(__INSTANCE, 6, 7, String.class, "f_android_switch");
    public static final Property<LoginConfigEntity> f_ios_switch = new Property<>(__INSTANCE, 7, 8, String.class, "f_ios_switch");
    public static final Property<LoginConfigEntity> f_switch = new Property<>(__INSTANCE, 8, 9, String.class, "f_switch");
    public static final Property<LoginConfigEntity> flow = new Property<>(__INSTANCE, 9, 10, String.class, "flow");
    public static final Property<LoginConfigEntity> lg_switch = new Property<>(__INSTANCE, 10, 11, String.class, "lg_switch");
    public static final Property<LoginConfigEntity> lk_switch = new Property<>(__INSTANCE, 11, 12, String.class, "lk_switch");
    public static final Property<LoginConfigEntity> lm_switch = new Property<>(__INSTANCE, 12, 13, String.class, "lm_switch");
    public static final Property<LoginConfigEntity> percent = new Property<>(__INSTANCE, 13, 14, String.class, "percent");
    public static final Property<LoginConfigEntity> provinceCode = new Property<>(__INSTANCE, 14, 15, String.class, "provinceCode");
    public static final Property<LoginConfigEntity> s_switch = new Property<>(__INSTANCE, 15, 16, String.class, "s_switch");
    public static final Property<LoginConfigEntity> strong_percent = new Property<>(__INSTANCE, 16, 17, String.class, "strong_percent");
    public static final Property<LoginConfigEntity> version = new Property<>(__INSTANCE, 17, 18, String.class, "version");
    public static final Property<LoginConfigEntity> w_switch = new Property<>(__INSTANCE, 18, 19, String.class, "w_switch");
    public static final Property<LoginConfigEntity> forgetCodeLen = new Property<>(__INSTANCE, 19, 20, String.class, "forgetCodeLen");
    public static final Property<LoginConfigEntity> loginCodeLen = new Property<>(__INSTANCE, 20, 21, String.class, "loginCodeLen");
    public static final Property<LoginConfigEntity> intervalTime = new Property<>(__INSTANCE, 21, 23, String.class, "intervalTime");
    public static final Property<LoginConfigEntity> start_interva = new Property<>(__INSTANCE, 22, 24, String.class, "start_interva");
    public static final Property<LoginConfigEntity> dongtaimiyao = new Property<>(__INSTANCE, 23, 31, String.class, "dongtaimiyao");
    public static final Property<LoginConfigEntity> voiceCountDownLen = new Property<>(__INSTANCE, 24, 22, String.class, "voiceCountDownLen");
    public static final Property<LoginConfigEntity> entranceSwitch = new Property<>(__INSTANCE, 25, 25, String.class, "entranceSwitch");
    public static final Property<LoginConfigEntity> popupContent = new Property<>(__INSTANCE, 26, 26, String.class, "popupContent");
    public static final Property<LoginConfigEntity> entranceContent = new Property<>(__INSTANCE, 27, 27, String.class, "entranceContent");
    public static final Property<LoginConfigEntity> popupSwitch = new Property<>(__INSTANCE, 28, 28, String.class, "popupSwitch");
    public static final Property<LoginConfigEntity> popVersion = new Property<>(__INSTANCE, 29, 29, String.class, "popVersion");
    public static final Property<LoginConfigEntity> failureTime = new Property<>(__INSTANCE, 30, 30, String.class, "failureTime");
    public static final Property<LoginConfigEntity> sceneType = new Property<>(__INSTANCE, 31, 32, String.class, "sceneType");
    public static final Property<LoginConfigEntity> delayTime = new Property<>(__INSTANCE, 32, 33, String.class, "delayTime");
    public static final Property<LoginConfigEntity> payGreenChannelSwitch = new Property<>(__INSTANCE, 33, 34, String.class, "payGreenChannelSwitch");
    public static final Property<LoginConfigEntity> payTitle = new Property<>(__INSTANCE, 34, 35, String.class, "payTitle");
    public static final Property<LoginConfigEntity> payContent = new Property<>(__INSTANCE, 35, 36, String.class, "payContent");
    public static final Property<LoginConfigEntity> payUrl = new Property<>(__INSTANCE, 36, 37, String.class, "payUrl");
    public static final Property<LoginConfigEntity> privacySwitch = new Property<>(__INSTANCE, 37, 38, String.class, "privacySwitch");
    public static final Property<LoginConfigEntity> faceLoginSwitch = new Property<>(__INSTANCE, 38, 39, String.class, "faceLoginSwitch");
    public static final Property<LoginConfigEntity> esimLoginSwitch = new Property<>(__INSTANCE, 39, 40, String.class, "esimLoginSwitch");
    public static final Property<LoginConfigEntity> esimCertSwitch = new Property<>(__INSTANCE, 40, 41, String.class, "esimCertSwitch");
    public static final Property<LoginConfigEntity> esimLoginHintText = new Property<>(__INSTANCE, 41, 42, String.class, "esimLoginHintText");
    public static final Property<LoginConfigEntity> broadLoginSwitch = new Property<>(__INSTANCE, 42, 43, String.class, "broadLoginSwitch");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "LoginConfigEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 37;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "LoginConfigEntity";
    }

    static {
        Property<LoginConfigEntity> property = f18385id;
        __ALL_PROPERTIES = new Property[]{property, a_switch, b_switch, c_switch, card_switch, e_switch, f_android_switch, f_ios_switch, f_switch, flow, lg_switch, lk_switch, lm_switch, percent, provinceCode, s_switch, strong_percent, version, w_switch, forgetCodeLen, loginCodeLen, intervalTime, start_interva, dongtaimiyao, voiceCountDownLen, entranceSwitch, popupContent, entranceContent, popupSwitch, popVersion, failureTime, sceneType, delayTime, payGreenChannelSwitch, payTitle, payContent, payUrl, privacySwitch, faceLoginSwitch, esimLoginSwitch, esimCertSwitch, esimLoginHintText, broadLoginSwitch};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<LoginConfigEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LoginConfigEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LoginConfigEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<LoginConfigEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<LoginConfigEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.LoginConfigEntity_$LoginConfigEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class LoginConfigEntityIdGetter implements IdGetter<LoginConfigEntity> {
        LoginConfigEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(LoginConfigEntity loginConfigEntity) {
            return loginConfigEntity.getId();
        }
    }
}
