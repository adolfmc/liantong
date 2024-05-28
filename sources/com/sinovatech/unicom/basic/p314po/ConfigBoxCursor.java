package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.ConfigBox_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.sinovatech.unicom.basic.po.ConfigBoxCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class ConfigBoxCursor extends Cursor<ConfigBox> {
    private static final ConfigBox_.ConfigBoxIdGetter ID_GETTER = ConfigBox_.__ID_GETTER;
    private static final int __ID_adMaxTime = ConfigBox_.adMaxTime.f24389id;
    private static final int __ID_adPushTimeInterval = ConfigBox_.adPushTimeInterval.f24389id;
    private static final int __ID_bindAccountNewOldColor = ConfigBox_.bindAccountNewOldColor.f24389id;
    private static final int __ID_bindAccountNewUserColor = ConfigBox_.bindAccountNewUserColor.f24389id;
    private static final int __ID_bindNumber = ConfigBox_.bindNumber.f24389id;
    private static final int __ID_birthdayPlayTime = ConfigBox_.birthdayPlayTime.f24389id;
    private static final int __ID_broadbandLand = ConfigBox_.broadbandLand.f24389id;
    private static final int __ID_clientBusinessClickCountUrl = ConfigBox_.clientBusinessClickCountUrl.f24389id;
    private static final int __ID_clientClickLogUpload = ConfigBox_.clientClickLogUpload.f24389id;
    private static final int __ID_coinAccessAdress = ConfigBox_.coinAccessAdress.f24389id;
    private static final int __ID_configuringVoiceSearch = ConfigBox_.configuringVoiceSearch.f24389id;
    private static final int __ID_createTime = ConfigBox_.createTime.f24389id;
    private static final int __ID_creator = ConfigBox_.creator.f24389id;
    private static final int __ID_creatorProvinceCode = ConfigBox_.creatorProvinceCode.f24389id;
    private static final int __ID_customHint = ConfigBox_.customHint.f24389id;
    private static final int __ID_dailyLotteryIsShow = ConfigBox_.dailyLotteryIsShow.f24389id;
    private static final int __ID_diffNetFlag = ConfigBox_.diffNetFlag.f24389id;
    private static final int __ID_errorLogUploadAddress = ConfigBox_.errorLogUploadAddress.f24389id;
    private static final int __ID_findUrl = ConfigBox_.findUrl.f24389id;
    private static final int __ID_fixedLand = ConfigBox_.fixedLand.f24389id;
    private static final int __ID_getMessageURL = ConfigBox_.getMessageURL.f24389id;
    private static final int __ID_hmuOpenFlag = ConfigBox_.hmuOpenFlag.f24389id;
    private static final int __ID_homeNewsRemindShowTime = ConfigBox_.homeNewsRemindShowTime.f24389id;
    private static final int __ID_homePopUpUrl = ConfigBox_.homePopUpUrl.f24389id;
    private static final int __ID_id = ConfigBox_.f18378id.f24389id;
    private static final int __ID_idLand = ConfigBox_.idLand.f24389id;
    private static final int __ID_ipvCopyWriting = ConfigBox_.ipvCopyWriting.f24389id;
    private static final int __ID_isEnabledNoCache = ConfigBox_.isEnabledNoCache.f24389id;
    private static final int __ID_isIfStartLink = ConfigBox_.isIfStartLink.f24389id;
    private static final int __ID_isOpenClickCount = ConfigBox_.isOpenClickCount.f24389id;
    private static final int __ID_isOpenHttps = ConfigBox_.isOpenHttps.f24389id;
    private static final int __ID_isOpenMetchColor = ConfigBox_.isOpenMetchColor.f24389id;
    private static final int __ID_isSysScreen = ConfigBox_.isSysScreen.f24389id;
    private static final int __ID_loginClickTimeInterval = ConfigBox_.loginClickTimeInterval.f24389id;
    private static final int __ID_loginRetryCount = ConfigBox_.loginRetryCount.f24389id;
    private static final int __ID_loginRetryTimeInterval = ConfigBox_.loginRetryTimeInterval.f24389id;
    private static final int __ID_logupload = ConfigBox_.logupload.f24389id;
    private static final int __ID_menuName1st = ConfigBox_.menuName1st.f24389id;
    private static final int __ID_menuName2nd = ConfigBox_.menuName2nd.f24389id;
    private static final int __ID_menuName3rd = ConfigBox_.menuName3rd.f24389id;
    private static final int __ID_menuName4th = ConfigBox_.menuName4th.f24389id;
    private static final int __ID_menuUrl1st = ConfigBox_.menuUrl1st.f24389id;
    private static final int __ID_menuUrl2nd = ConfigBox_.menuUrl2nd.f24389id;
    private static final int __ID_menuUrl3rd = ConfigBox_.menuUrl3rd.f24389id;
    private static final int __ID_menuUrl4th = ConfigBox_.menuUrl4th.f24389id;
    private static final int __ID_messagePushTimeInterval = ConfigBox_.messagePushTimeInterval.f24389id;
    private static final int __ID_myUnicomUrl = ConfigBox_.myUnicomUrl.f24389id;
    private static final int __ID_numberLand = ConfigBox_.numberLand.f24389id;
    private static final int __ID_openEmptyCardCheck = ConfigBox_.openEmptyCardCheck.f24389id;
    private static final int __ID_orderNumber = ConfigBox_.orderNumber.f24389id;
    private static final int __ID_popupDelayTime = ConfigBox_.popupDelayTime.f24389id;
    private static final int __ID_popupNotPopped = ConfigBox_.popupNotPopped.f24389id;
    private static final int __ID_registerURL = ConfigBox_.registerURL.f24389id;
    private static final int __ID_relationAccountNewUserColor = ConfigBox_.relationAccountNewUserColor.f24389id;
    private static final int __ID_relationAccountOldUserColor = ConfigBox_.relationAccountOldUserColor.f24389id;
    private static final int __ID_relationClickColor = ConfigBox_.relationClickColor.f24389id;
    private static final int __ID_reloadHint = ConfigBox_.reloadHint.f24389id;
    private static final int __ID_rightSlipHint = ConfigBox_.rightSlipHint.f24389id;
    private static final int __ID_searchConfiguration = ConfigBox_.searchConfiguration.f24389id;
    private static final int __ID_searchScrollInterval = ConfigBox_.searchScrollInterval.f24389id;
    private static final int __ID_sharePoliteUrl = ConfigBox_.sharePoliteUrl.f24389id;
    private static final int __ID_smsSwitch = ConfigBox_.smsSwitch.f24389id;
    private static final int __ID_smsValidateCode = ConfigBox_.smsValidateCode.f24389id;
    private static final int __ID_startMaxTime = ConfigBox_.startMaxTime.f24389id;
    private static final int __ID_status = ConfigBox_.status.f24389id;
    private static final int __ID_trustedCNS = ConfigBox_.trustedCNS.f24389id;
    private static final int __ID_uploadImageA = ConfigBox_.uploadImageA.f24389id;
    private static final int __ID_uploadImageB = ConfigBox_.uploadImageB.f24389id;
    private static final int __ID_whetherSpinner = ConfigBox_.whetherSpinner.f24389id;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x040c: FILL_ARRAY  (r86 I:??[]), data: null, expected to be less than 76
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    @Override // io.objectbox.Cursor
    public final long put(com.sinovatech.unicom.basic.p314po.ConfigBox r75) {
        /*
            Method dump skipped, instructions count: 1129
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p314po.ConfigBoxCursor.put(com.sinovatech.unicom.basic.po.ConfigBox):long");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.ConfigBoxCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class Factory implements CursorFactory<ConfigBox> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<ConfigBox> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new ConfigBoxCursor(transaction, j, boxStore);
        }
    }

    public ConfigBoxCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, ConfigBox_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(ConfigBox configBox) {
        return ID_GETTER.getId(configBox);
    }
}
