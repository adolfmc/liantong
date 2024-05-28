package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeCardBgEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeCardBgEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HomeCardBgEntityCursor extends Cursor<HomeCardBgEntity> {
    private static final HomeCardBgEntity_.HomeCardBgEntityIdGetter ID_GETTER = HomeCardBgEntity_.__ID_GETTER;
    private static final int __ID_isLogin = HomeCardBgEntity_.isLogin.f24389id;
    private static final int __ID_productDefault = HomeCardBgEntity_.productDefault.f24389id;
    private static final int __ID_productDesc = HomeCardBgEntity_.productDesc.f24389id;
    private static final int __ID_productName = HomeCardBgEntity_.productName.f24389id;
    private static final int __ID_productRedirecturl = HomeCardBgEntity_.productRedirecturl.f24389id;
    private static final int __ID_productUrl = HomeCardBgEntity_.productUrl.f24389id;
    private static final int __ID_productUrlType = HomeCardBgEntity_.productUrlType.f24389id;
    private static final int __ID_ruleId = HomeCardBgEntity_.ruleId.f24389id;
    private static final int __ID_specialType = HomeCardBgEntity_.specialType.f24389id;
    private static final int __ID_strageId = HomeCardBgEntity_.strageId.f24389id;
    private static final int __ID_year = HomeCardBgEntity_.year.f24389id;
    private static final int __ID_cornerDesc = HomeCardBgEntity_.cornerDesc.f24389id;
    private static final int __ID_productCode = HomeCardBgEntity_.productCode.f24389id;
    private static final int __ID_mobile = HomeCardBgEntity_.mobile.f24389id;
    private static final int __ID_cacheTime = HomeCardBgEntity_.cacheTime.f24389id;
    private static final int __ID_backgroundPriority = HomeCardBgEntity_.backgroundPriority.f24389id;
    private static final int __ID_goodsId = HomeCardBgEntity_.goodsId.f24389id;
    private static final int __ID_actId = HomeCardBgEntity_.actId.f24389id;
    private static final int __ID_actType = HomeCardBgEntity_.actType.f24389id;
    private static final int __ID_imgDynamic = HomeCardBgEntity_.imgDynamic.f24389id;
    private static final int __ID_textPictureColor = HomeCardBgEntity_.textPictureColor.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeCardBgEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<HomeCardBgEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<HomeCardBgEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new HomeCardBgEntityCursor(transaction, j, boxStore);
        }
    }

    public HomeCardBgEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, HomeCardBgEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(HomeCardBgEntity homeCardBgEntity) {
        return ID_GETTER.getId(homeCardBgEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(HomeCardBgEntity homeCardBgEntity) {
        String isLogin = homeCardBgEntity.getIsLogin();
        int i = isLogin != null ? __ID_isLogin : 0;
        String productDefault = homeCardBgEntity.getProductDefault();
        int i2 = productDefault != null ? __ID_productDefault : 0;
        String productDesc = homeCardBgEntity.getProductDesc();
        int i3 = productDesc != null ? __ID_productDesc : 0;
        String productName = homeCardBgEntity.getProductName();
        collect400000(this.cursor, 0L, 1, i, isLogin, i2, productDefault, i3, productDesc, productName != null ? __ID_productName : 0, productName);
        String productRedirecturl = homeCardBgEntity.getProductRedirecturl();
        int i4 = productRedirecturl != null ? __ID_productRedirecturl : 0;
        String productUrl = homeCardBgEntity.getProductUrl();
        int i5 = productUrl != null ? __ID_productUrl : 0;
        String productUrlType = homeCardBgEntity.getProductUrlType();
        int i6 = productUrlType != null ? __ID_productUrlType : 0;
        String ruleId = homeCardBgEntity.getRuleId();
        collect400000(this.cursor, 0L, 0, i4, productRedirecturl, i5, productUrl, i6, productUrlType, ruleId != null ? __ID_ruleId : 0, ruleId);
        String specialType = homeCardBgEntity.getSpecialType();
        int i7 = specialType != null ? __ID_specialType : 0;
        String strageId = homeCardBgEntity.getStrageId();
        int i8 = strageId != null ? __ID_strageId : 0;
        String year = homeCardBgEntity.getYear();
        int i9 = year != null ? __ID_year : 0;
        String cornerDesc = homeCardBgEntity.getCornerDesc();
        collect400000(this.cursor, 0L, 0, i7, specialType, i8, strageId, i9, year, cornerDesc != null ? __ID_cornerDesc : 0, cornerDesc);
        String productCode = homeCardBgEntity.getProductCode();
        int i10 = productCode != null ? __ID_productCode : 0;
        String mobile = homeCardBgEntity.getMobile();
        int i11 = mobile != null ? __ID_mobile : 0;
        String cacheTime = homeCardBgEntity.getCacheTime();
        int i12 = cacheTime != null ? __ID_cacheTime : 0;
        String backgroundPriority = homeCardBgEntity.getBackgroundPriority();
        collect400000(this.cursor, 0L, 0, i10, productCode, i11, mobile, i12, cacheTime, backgroundPriority != null ? __ID_backgroundPriority : 0, backgroundPriority);
        String goodsId = homeCardBgEntity.getGoodsId();
        int i13 = goodsId != null ? __ID_goodsId : 0;
        String actId = homeCardBgEntity.getActId();
        int i14 = actId != null ? __ID_actId : 0;
        String actType = homeCardBgEntity.getActType();
        int i15 = actType != null ? __ID_actType : 0;
        String imgDynamic = homeCardBgEntity.getImgDynamic();
        collect400000(this.cursor, 0L, 0, i13, goodsId, i14, actId, i15, actType, imgDynamic != null ? __ID_imgDynamic : 0, imgDynamic);
        String textPictureColor = homeCardBgEntity.getTextPictureColor();
        long collect313311 = collect313311(this.cursor, homeCardBgEntity.getId(), 2, textPictureColor != null ? __ID_textPictureColor : 0, textPictureColor, 0, null, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        homeCardBgEntity.setId(collect313311);
        return collect313311;
    }
}
