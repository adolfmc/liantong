package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CumpEntityCursor extends Cursor<CumpEntity> {
    private static final CumpEntity_.CumpEntityIdGetter ID_GETTER = CumpEntity_.__ID_GETTER;
    private static final int __ID_appId = CumpEntity_.appId.f24389id;
    private static final int __ID_appName = CumpEntity_.appName.f24389id;
    private static final int __ID_appImg = CumpEntity_.appImg.f24389id;
    private static final int __ID_appHighImg = CumpEntity_.appHighImg.f24389id;
    private static final int __ID_appSecret = CumpEntity_.appSecret.f24389id;
    private static final int __ID_appToken = CumpEntity_.appToken.f24389id;
    private static final int __ID_appDesc = CumpEntity_.appDesc.f24389id;
    private static final int __ID_officialVersion = CumpEntity_.officialVersion.f24389id;
    private static final int __ID_officialPackageUrl = CumpEntity_.officialPackageUrl.f24389id;
    private static final int __ID_officialMd5 = CumpEntity_.officialMd5.f24389id;
    private static final int __ID_timestamp = CumpEntity_.timestamp.f24389id;
    private static final int __ID_createTime = CumpEntity_.createTime.f24389id;
    private static final int __ID_latestUpdateTime = CumpEntity_.latestUpdateTime.f24389id;
    private static final int __ID_realmUrlList = CumpEntity_.realmUrlList.f24389id;
    private static final int __ID_plugCodeList = CumpEntity_.plugCodeList.f24389id;
    private static final int __ID_serverPublishType = CumpEntity_.serverPublishType.f24389id;
    private static final int __ID_desktopIcon = CumpEntity_.desktopIcon.f24389id;
    private static final int __ID_privacyUrl = CumpEntity_.privacyUrl.f24389id;
    private static final int __ID_lastestCollPopupShowTime = CumpEntity_.lastestCollPopupShowTime.f24389id;
    private static final int __ID_lastestBottomPopupShowTime = CumpEntity_.lastestBottomPopupShowTime.f24389id;
    private static final int __ID_publishMethod = CumpEntity_.publishMethod.f24389id;
    private static final int __ID_homePageKey = CumpEntity_.homePageKey.f24389id;
    private static final int __ID_isInnerMiniP = CumpEntity_.isInnerMiniP.f24389id;
    private static final int __ID_oldVersion = CumpEntity_.oldVersion.f24389id;
    private static final int __ID_trialVersionNum = CumpEntity_.trialVersionNum.f24389id;
    private static final int __ID_officialVersionNum = CumpEntity_.officialVersionNum.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CumpEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CumpEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CumpEntityCursor(transaction, j, boxStore);
        }
    }

    public CumpEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CumpEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CumpEntity cumpEntity) {
        return ID_GETTER.getId(cumpEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CumpEntity cumpEntity) {
        String appId = cumpEntity.getAppId();
        int i = appId != null ? __ID_appId : 0;
        String appName = cumpEntity.getAppName();
        int i2 = appName != null ? __ID_appName : 0;
        String appImg = cumpEntity.getAppImg();
        int i3 = appImg != null ? __ID_appImg : 0;
        String appHighImg = cumpEntity.getAppHighImg();
        collect400000(this.cursor, 0L, 1, i, appId, i2, appName, i3, appImg, appHighImg != null ? __ID_appHighImg : 0, appHighImg);
        String appSecret = cumpEntity.getAppSecret();
        int i4 = appSecret != null ? __ID_appSecret : 0;
        String appToken = cumpEntity.getAppToken();
        int i5 = appToken != null ? __ID_appToken : 0;
        String appDesc = cumpEntity.getAppDesc();
        int i6 = appDesc != null ? __ID_appDesc : 0;
        String officialVersion = cumpEntity.getOfficialVersion();
        collect400000(this.cursor, 0L, 0, i4, appSecret, i5, appToken, i6, appDesc, officialVersion != null ? __ID_officialVersion : 0, officialVersion);
        String officialPackageUrl = cumpEntity.getOfficialPackageUrl();
        int i7 = officialPackageUrl != null ? __ID_officialPackageUrl : 0;
        String officialMd5 = cumpEntity.getOfficialMd5();
        int i8 = officialMd5 != null ? __ID_officialMd5 : 0;
        String timestamp = cumpEntity.getTimestamp();
        int i9 = timestamp != null ? __ID_timestamp : 0;
        String createTime = cumpEntity.getCreateTime();
        collect400000(this.cursor, 0L, 0, i7, officialPackageUrl, i8, officialMd5, i9, timestamp, createTime != null ? __ID_createTime : 0, createTime);
        String latestUpdateTime = cumpEntity.getLatestUpdateTime();
        int i10 = latestUpdateTime != null ? __ID_latestUpdateTime : 0;
        String realmUrlList = cumpEntity.getRealmUrlList();
        int i11 = realmUrlList != null ? __ID_realmUrlList : 0;
        String plugCodeList = cumpEntity.getPlugCodeList();
        int i12 = plugCodeList != null ? __ID_plugCodeList : 0;
        String serverPublishType = cumpEntity.getServerPublishType();
        collect400000(this.cursor, 0L, 0, i10, latestUpdateTime, i11, realmUrlList, i12, plugCodeList, serverPublishType != null ? __ID_serverPublishType : 0, serverPublishType);
        String desktopIcon = cumpEntity.getDesktopIcon();
        int i13 = desktopIcon != null ? __ID_desktopIcon : 0;
        String privacyUrl = cumpEntity.getPrivacyUrl();
        int i14 = privacyUrl != null ? __ID_privacyUrl : 0;
        String publishMethod = cumpEntity.getPublishMethod();
        int i15 = publishMethod != null ? __ID_publishMethod : 0;
        String homePageKey = cumpEntity.getHomePageKey();
        collect400000(this.cursor, 0L, 0, i13, desktopIcon, i14, privacyUrl, i15, publishMethod, homePageKey != null ? __ID_homePageKey : 0, homePageKey);
        String oldVersion = cumpEntity.getOldVersion();
        int i16 = oldVersion != null ? __ID_oldVersion : 0;
        String trialVersionNum = cumpEntity.getTrialVersionNum();
        int i17 = trialVersionNum != null ? __ID_trialVersionNum : 0;
        String officialVersionNum = cumpEntity.getOfficialVersionNum();
        long collect313311 = collect313311(this.cursor, cumpEntity.getId(), 2, i16, oldVersion, i17, trialVersionNum, officialVersionNum != null ? __ID_officialVersionNum : 0, officialVersionNum, 0, null, __ID_lastestCollPopupShowTime, cumpEntity.getLastestCollPopupShowTime(), __ID_lastestBottomPopupShowTime, cumpEntity.getLastestBottomPopupShowTime(), __ID_isInnerMiniP, cumpEntity.isInnerMiniP() ? 1L : 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        cumpEntity.setId(collect313311);
        return collect313311;
    }
}
