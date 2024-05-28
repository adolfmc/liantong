package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBusinessEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBusinessEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HomeBusinessEntityCursor extends Cursor<HomeBusinessEntity> {
    private static final HomeBusinessEntity_.HomeBusinessEntityIdGetter ID_GETTER = HomeBusinessEntity_.__ID_GETTER;
    private static final int __ID_isLogin = HomeBusinessEntity_.isLogin.f24389id;
    private static final int __ID_productDefault = HomeBusinessEntity_.productDefault.f24389id;
    private static final int __ID_productDesc = HomeBusinessEntity_.productDesc.f24389id;
    private static final int __ID_productName = HomeBusinessEntity_.productName.f24389id;
    private static final int __ID_productRedirecturl = HomeBusinessEntity_.productRedirecturl.f24389id;
    private static final int __ID_productUrl = HomeBusinessEntity_.productUrl.f24389id;
    private static final int __ID_productUrlType = HomeBusinessEntity_.productUrlType.f24389id;
    private static final int __ID_ruleId = HomeBusinessEntity_.ruleId.f24389id;
    private static final int __ID_specialType = HomeBusinessEntity_.specialType.f24389id;
    private static final int __ID_strageId = HomeBusinessEntity_.strageId.f24389id;
    private static final int __ID_year = HomeBusinessEntity_.year.f24389id;
    private static final int __ID_defImg = HomeBusinessEntity_.defImg.f24389id;
    private static final int __ID_productCode = HomeBusinessEntity_.productCode.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBusinessEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<HomeBusinessEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<HomeBusinessEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new HomeBusinessEntityCursor(transaction, j, boxStore);
        }
    }

    public HomeBusinessEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, HomeBusinessEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(HomeBusinessEntity homeBusinessEntity) {
        return ID_GETTER.getId(homeBusinessEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(HomeBusinessEntity homeBusinessEntity) {
        String isLogin = homeBusinessEntity.getIsLogin();
        int i = isLogin != null ? __ID_isLogin : 0;
        String productDefault = homeBusinessEntity.getProductDefault();
        int i2 = productDefault != null ? __ID_productDefault : 0;
        String productDesc = homeBusinessEntity.getProductDesc();
        int i3 = productDesc != null ? __ID_productDesc : 0;
        String productName = homeBusinessEntity.getProductName();
        collect400000(this.cursor, 0L, 1, i, isLogin, i2, productDefault, i3, productDesc, productName != null ? __ID_productName : 0, productName);
        String productRedirecturl = homeBusinessEntity.getProductRedirecturl();
        int i4 = productRedirecturl != null ? __ID_productRedirecturl : 0;
        String productUrl = homeBusinessEntity.getProductUrl();
        int i5 = productUrl != null ? __ID_productUrl : 0;
        String productUrlType = homeBusinessEntity.getProductUrlType();
        int i6 = productUrlType != null ? __ID_productUrlType : 0;
        String ruleId = homeBusinessEntity.getRuleId();
        collect400000(this.cursor, 0L, 0, i4, productRedirecturl, i5, productUrl, i6, productUrlType, ruleId != null ? __ID_ruleId : 0, ruleId);
        String specialType = homeBusinessEntity.getSpecialType();
        int i7 = specialType != null ? __ID_specialType : 0;
        String strageId = homeBusinessEntity.getStrageId();
        int i8 = strageId != null ? __ID_strageId : 0;
        String year = homeBusinessEntity.getYear();
        int i9 = year != null ? __ID_year : 0;
        String productCode = homeBusinessEntity.getProductCode();
        collect400000(this.cursor, 0L, 0, i7, specialType, i8, strageId, i9, year, productCode != null ? __ID_productCode : 0, productCode);
        long collect004000 = collect004000(this.cursor, homeBusinessEntity.getId(), 2, __ID_defImg, homeBusinessEntity.getDefImg(), 0, 0L, 0, 0L, 0, 0L);
        homeBusinessEntity.setId(collect004000);
        return collect004000;
    }
}
