package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.ConfigBoxCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.ConfigBox_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class ConfigBox_ implements EntityInfo<ConfigBox> {
    public static final Property<ConfigBox>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "ConfigBox";
    public static final int __ENTITY_ID = 10;
    public static final String __ENTITY_NAME = "ConfigBox";
    public static final Property<ConfigBox> __ID_PROPERTY;
    public static final Class<ConfigBox> __ENTITY_CLASS = ConfigBox.class;
    public static final CursorFactory<ConfigBox> __CURSOR_FACTORY = new ConfigBoxCursor.Factory();
    @Internal
    static final ConfigBoxIdGetter __ID_GETTER = new ConfigBoxIdGetter();
    public static final ConfigBox_ __INSTANCE = new ConfigBox_();
    public static final Property<ConfigBox> provateid = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "provateid", true, "provateid");
    public static final Property<ConfigBox> adMaxTime = new Property<>(__INSTANCE, 1, 2, String.class, "adMaxTime");
    public static final Property<ConfigBox> adPushTimeInterval = new Property<>(__INSTANCE, 2, 3, String.class, "adPushTimeInterval");
    public static final Property<ConfigBox> bindAccountNewOldColor = new Property<>(__INSTANCE, 3, 4, String.class, "bindAccountNewOldColor");
    public static final Property<ConfigBox> bindAccountNewUserColor = new Property<>(__INSTANCE, 4, 5, String.class, "bindAccountNewUserColor");
    public static final Property<ConfigBox> bindNumber = new Property<>(__INSTANCE, 5, 6, String.class, "bindNumber");
    public static final Property<ConfigBox> birthdayPlayTime = new Property<>(__INSTANCE, 6, 7, String.class, "birthdayPlayTime");
    public static final Property<ConfigBox> broadbandLand = new Property<>(__INSTANCE, 7, 8, String.class, "broadbandLand");
    public static final Property<ConfigBox> clientBusinessClickCountUrl = new Property<>(__INSTANCE, 8, 9, String.class, "clientBusinessClickCountUrl");
    public static final Property<ConfigBox> clientClickLogUpload = new Property<>(__INSTANCE, 9, 10, String.class, "clientClickLogUpload");
    public static final Property<ConfigBox> coinAccessAdress = new Property<>(__INSTANCE, 10, 11, String.class, "coinAccessAdress");
    public static final Property<ConfigBox> configuringVoiceSearch = new Property<>(__INSTANCE, 11, 12, String.class, "configuringVoiceSearch");
    public static final Property<ConfigBox> createTime = new Property<>(__INSTANCE, 12, 13, String.class, "createTime");
    public static final Property<ConfigBox> creator = new Property<>(__INSTANCE, 13, 14, String.class, "creator");
    public static final Property<ConfigBox> creatorProvinceCode = new Property<>(__INSTANCE, 14, 15, String.class, "creatorProvinceCode");
    public static final Property<ConfigBox> customHint = new Property<>(__INSTANCE, 15, 16, String.class, "customHint");
    public static final Property<ConfigBox> dailyLotteryIsShow = new Property<>(__INSTANCE, 16, 17, String.class, "dailyLotteryIsShow");
    public static final Property<ConfigBox> diffNetFlag = new Property<>(__INSTANCE, 17, 18, String.class, "diffNetFlag");
    public static final Property<ConfigBox> errorLogUploadAddress = new Property<>(__INSTANCE, 18, 19, String.class, "errorLogUploadAddress");
    public static final Property<ConfigBox> findUrl = new Property<>(__INSTANCE, 19, 20, String.class, "findUrl");
    public static final Property<ConfigBox> fixedLand = new Property<>(__INSTANCE, 20, 21, String.class, "fixedLand");
    public static final Property<ConfigBox> getMessageURL = new Property<>(__INSTANCE, 21, 22, String.class, "getMessageURL");
    public static final Property<ConfigBox> hmuOpenFlag = new Property<>(__INSTANCE, 22, 23, String.class, "hmuOpenFlag");
    public static final Property<ConfigBox> homeNewsRemindShowTime = new Property<>(__INSTANCE, 23, 24, String.class, "homeNewsRemindShowTime");
    public static final Property<ConfigBox> homePopUpUrl = new Property<>(__INSTANCE, 24, 25, String.class, "homePopUpUrl");

    /* renamed from: id */
    public static final Property<ConfigBox> f18378id = new Property<>(__INSTANCE, 25, 26, String.class, "id");
    public static final Property<ConfigBox> idLand = new Property<>(__INSTANCE, 26, 27, String.class, "idLand");
    public static final Property<ConfigBox> ipvCopyWriting = new Property<>(__INSTANCE, 27, 28, String.class, "ipvCopyWriting");
    public static final Property<ConfigBox> isEnabledNoCache = new Property<>(__INSTANCE, 28, 29, String.class, "isEnabledNoCache");
    public static final Property<ConfigBox> isIfStartLink = new Property<>(__INSTANCE, 29, 30, String.class, "isIfStartLink");
    public static final Property<ConfigBox> isOpenClickCount = new Property<>(__INSTANCE, 30, 31, String.class, "isOpenClickCount");
    public static final Property<ConfigBox> isOpenHttps = new Property<>(__INSTANCE, 31, 32, String.class, "isOpenHttps");
    public static final Property<ConfigBox> isOpenMetchColor = new Property<>(__INSTANCE, 32, 33, String.class, "isOpenMetchColor");
    public static final Property<ConfigBox> isSysScreen = new Property<>(__INSTANCE, 33, 34, String.class, "isSysScreen");
    public static final Property<ConfigBox> loginClickTimeInterval = new Property<>(__INSTANCE, 34, 35, String.class, "loginClickTimeInterval");
    public static final Property<ConfigBox> loginRetryCount = new Property<>(__INSTANCE, 35, 36, String.class, "loginRetryCount");
    public static final Property<ConfigBox> loginRetryTimeInterval = new Property<>(__INSTANCE, 36, 37, String.class, "loginRetryTimeInterval");
    public static final Property<ConfigBox> logupload = new Property<>(__INSTANCE, 37, 38, String.class, "logupload");
    public static final Property<ConfigBox> menuName1st = new Property<>(__INSTANCE, 38, 39, String.class, "menuName1st");
    public static final Property<ConfigBox> menuName2nd = new Property<>(__INSTANCE, 39, 40, String.class, "menuName2nd");
    public static final Property<ConfigBox> menuName3rd = new Property<>(__INSTANCE, 40, 41, String.class, "menuName3rd");
    public static final Property<ConfigBox> menuName4th = new Property<>(__INSTANCE, 41, 42, String.class, "menuName4th");
    public static final Property<ConfigBox> menuUrl1st = new Property<>(__INSTANCE, 42, 43, String.class, "menuUrl1st");
    public static final Property<ConfigBox> menuUrl2nd = new Property<>(__INSTANCE, 43, 44, String.class, "menuUrl2nd");
    public static final Property<ConfigBox> menuUrl3rd = new Property<>(__INSTANCE, 44, 45, String.class, "menuUrl3rd");
    public static final Property<ConfigBox> menuUrl4th = new Property<>(__INSTANCE, 45, 46, String.class, "menuUrl4th");
    public static final Property<ConfigBox> messagePushTimeInterval = new Property<>(__INSTANCE, 46, 47, String.class, "messagePushTimeInterval");
    public static final Property<ConfigBox> myUnicomUrl = new Property<>(__INSTANCE, 47, 48, String.class, "myUnicomUrl");
    public static final Property<ConfigBox> numberLand = new Property<>(__INSTANCE, 48, 49, String.class, "numberLand");
    public static final Property<ConfigBox> openEmptyCardCheck = new Property<>(__INSTANCE, 49, 50, String.class, "openEmptyCardCheck");
    public static final Property<ConfigBox> orderNumber = new Property<>(__INSTANCE, 50, 51, String.class, "orderNumber");
    public static final Property<ConfigBox> popupDelayTime = new Property<>(__INSTANCE, 51, 52, String.class, "popupDelayTime");
    public static final Property<ConfigBox> popupNotPopped = new Property<>(__INSTANCE, 52, 53, String.class, "popupNotPopped");
    public static final Property<ConfigBox> registerURL = new Property<>(__INSTANCE, 53, 54, String.class, "registerURL");
    public static final Property<ConfigBox> relationAccountNewUserColor = new Property<>(__INSTANCE, 54, 55, String.class, "relationAccountNewUserColor");
    public static final Property<ConfigBox> relationAccountOldUserColor = new Property<>(__INSTANCE, 55, 56, String.class, "relationAccountOldUserColor");
    public static final Property<ConfigBox> relationClickColor = new Property<>(__INSTANCE, 56, 57, String.class, "relationClickColor");
    public static final Property<ConfigBox> reloadHint = new Property<>(__INSTANCE, 57, 58, String.class, "reloadHint");
    public static final Property<ConfigBox> rightSlipHint = new Property<>(__INSTANCE, 58, 59, String.class, "rightSlipHint");
    public static final Property<ConfigBox> searchConfiguration = new Property<>(__INSTANCE, 59, 60, String.class, "searchConfiguration");
    public static final Property<ConfigBox> searchScrollInterval = new Property<>(__INSTANCE, 60, 61, String.class, "searchScrollInterval");
    public static final Property<ConfigBox> sharePoliteUrl = new Property<>(__INSTANCE, 61, 62, String.class, "sharePoliteUrl");
    public static final Property<ConfigBox> smsSwitch = new Property<>(__INSTANCE, 62, 63, String.class, "smsSwitch");
    public static final Property<ConfigBox> smsValidateCode = new Property<>(__INSTANCE, 63, 64, String.class, "smsValidateCode");
    public static final Property<ConfigBox> startMaxTime = new Property<>(__INSTANCE, 64, 65, String.class, "startMaxTime");
    public static final Property<ConfigBox> status = new Property<>(__INSTANCE, 65, 66, String.class, "status");
    public static final Property<ConfigBox> trustedCNS = new Property<>(__INSTANCE, 66, 67, String.class, "trustedCNS");
    public static final Property<ConfigBox> uploadImageA = new Property<>(__INSTANCE, 67, 68, String.class, "uploadImageA");
    public static final Property<ConfigBox> uploadImageB = new Property<>(__INSTANCE, 68, 69, String.class, "uploadImageB");
    public static final Property<ConfigBox> whetherSpinner = new Property<>(__INSTANCE, 69, 70, String.class, "whetherSpinner");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "ConfigBox";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 10;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "ConfigBox";
    }

    static {
        Property<ConfigBox> property = provateid;
        __ALL_PROPERTIES = new Property[]{property, adMaxTime, adPushTimeInterval, bindAccountNewOldColor, bindAccountNewUserColor, bindNumber, birthdayPlayTime, broadbandLand, clientBusinessClickCountUrl, clientClickLogUpload, coinAccessAdress, configuringVoiceSearch, createTime, creator, creatorProvinceCode, customHint, dailyLotteryIsShow, diffNetFlag, errorLogUploadAddress, findUrl, fixedLand, getMessageURL, hmuOpenFlag, homeNewsRemindShowTime, homePopUpUrl, f18378id, idLand, ipvCopyWriting, isEnabledNoCache, isIfStartLink, isOpenClickCount, isOpenHttps, isOpenMetchColor, isSysScreen, loginClickTimeInterval, loginRetryCount, loginRetryTimeInterval, logupload, menuName1st, menuName2nd, menuName3rd, menuName4th, menuUrl1st, menuUrl2nd, menuUrl3rd, menuUrl4th, messagePushTimeInterval, myUnicomUrl, numberLand, openEmptyCardCheck, orderNumber, popupDelayTime, popupNotPopped, registerURL, relationAccountNewUserColor, relationAccountOldUserColor, relationClickColor, reloadHint, rightSlipHint, searchConfiguration, searchScrollInterval, sharePoliteUrl, smsSwitch, smsValidateCode, startMaxTime, status, trustedCNS, uploadImageA, uploadImageB, whetherSpinner};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<ConfigBox> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<ConfigBox>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<ConfigBox> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<ConfigBox> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<ConfigBox> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.ConfigBox_$ConfigBoxIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class ConfigBoxIdGetter implements IdGetter<ConfigBox> {
        @Override // io.objectbox.internal.IdGetter
        public long getId(ConfigBox configBox) {
            return configBox.getProvateid();
        }
    }
}
