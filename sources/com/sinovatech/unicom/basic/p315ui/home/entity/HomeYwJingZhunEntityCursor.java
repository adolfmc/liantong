package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeYwJingZhunEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeYwJingZhunEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HomeYwJingZhunEntityCursor extends Cursor<HomeYwJingZhunEntity> {
    private static final HomeYwJingZhunEntity_.HomeYwJingZhunEntityIdGetter ID_GETTER = HomeYwJingZhunEntity_.__ID_GETTER;
    private static final int __ID_isLogin = HomeYwJingZhunEntity_.isLogin.f24389id;
    private static final int __ID_productDefault = HomeYwJingZhunEntity_.productDefault.f24389id;
    private static final int __ID_productDesc = HomeYwJingZhunEntity_.productDesc.f24389id;
    private static final int __ID_productName = HomeYwJingZhunEntity_.productName.f24389id;
    private static final int __ID_productRedirecturl = HomeYwJingZhunEntity_.productRedirecturl.f24389id;
    private static final int __ID_productUrl = HomeYwJingZhunEntity_.productUrl.f24389id;
    private static final int __ID_productUrlType = HomeYwJingZhunEntity_.productUrlType.f24389id;
    private static final int __ID_ruleId = HomeYwJingZhunEntity_.ruleId.f24389id;
    private static final int __ID_specialType = HomeYwJingZhunEntity_.specialType.f24389id;
    private static final int __ID_strageId = HomeYwJingZhunEntity_.strageId.f24389id;
    private static final int __ID_year = HomeYwJingZhunEntity_.year.f24389id;
    private static final int __ID_mobile = HomeYwJingZhunEntity_.mobile.f24389id;
    private static final int __ID_defImg = HomeYwJingZhunEntity_.defImg.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeYwJingZhunEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<HomeYwJingZhunEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<HomeYwJingZhunEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new HomeYwJingZhunEntityCursor(transaction, j, boxStore);
        }
    }

    public HomeYwJingZhunEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, HomeYwJingZhunEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(HomeYwJingZhunEntity homeYwJingZhunEntity) {
        return ID_GETTER.getId(homeYwJingZhunEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(HomeYwJingZhunEntity homeYwJingZhunEntity) {
        String isLogin = homeYwJingZhunEntity.getIsLogin();
        int i = isLogin != null ? __ID_isLogin : 0;
        String productDefault = homeYwJingZhunEntity.getProductDefault();
        int i2 = productDefault != null ? __ID_productDefault : 0;
        String productDesc = homeYwJingZhunEntity.getProductDesc();
        int i3 = productDesc != null ? __ID_productDesc : 0;
        String productName = homeYwJingZhunEntity.getProductName();
        collect400000(this.cursor, 0L, 1, i, isLogin, i2, productDefault, i3, productDesc, productName != null ? __ID_productName : 0, productName);
        String productRedirecturl = homeYwJingZhunEntity.getProductRedirecturl();
        int i4 = productRedirecturl != null ? __ID_productRedirecturl : 0;
        String productUrl = homeYwJingZhunEntity.getProductUrl();
        int i5 = productUrl != null ? __ID_productUrl : 0;
        String productUrlType = homeYwJingZhunEntity.getProductUrlType();
        int i6 = productUrlType != null ? __ID_productUrlType : 0;
        String ruleId = homeYwJingZhunEntity.getRuleId();
        collect400000(this.cursor, 0L, 0, i4, productRedirecturl, i5, productUrl, i6, productUrlType, ruleId != null ? __ID_ruleId : 0, ruleId);
        String specialType = homeYwJingZhunEntity.getSpecialType();
        int i7 = specialType != null ? __ID_specialType : 0;
        String strageId = homeYwJingZhunEntity.getStrageId();
        int i8 = strageId != null ? __ID_strageId : 0;
        String year = homeYwJingZhunEntity.getYear();
        int i9 = year != null ? __ID_year : 0;
        String mobile = homeYwJingZhunEntity.getMobile();
        collect400000(this.cursor, 0L, 0, i7, specialType, i8, strageId, i9, year, mobile != null ? __ID_mobile : 0, mobile);
        long collect004000 = collect004000(this.cursor, homeYwJingZhunEntity.getId(), 2, __ID_defImg, homeYwJingZhunEntity.getDefImg(), 0, 0L, 0, 0L, 0, 0L);
        homeYwJingZhunEntity.setId(collect004000);
        return collect004000;
    }
}
