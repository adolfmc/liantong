package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBwJingZhunEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBwJingZhunEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HomeBwJingZhunEntityCursor extends Cursor<HomeBwJingZhunEntity> {
    private static final HomeBwJingZhunEntity_.HomeBwJingZhunEntityIdGetter ID_GETTER = HomeBwJingZhunEntity_.__ID_GETTER;
    private static final int __ID_productActualType = HomeBwJingZhunEntity_.productActualType.f24389id;
    private static final int __ID_source = HomeBwJingZhunEntity_.source.f24389id;
    private static final int __ID_defaultRecommend = HomeBwJingZhunEntity_.defaultRecommend.f24389id;
    private static final int __ID_viceTitle = HomeBwJingZhunEntity_.viceTitle.f24389id;
    private static final int __ID_beanId = HomeBwJingZhunEntity_.beanId.f24389id;
    private static final int __ID_ruleId = HomeBwJingZhunEntity_.ruleId.f24389id;
    private static final int __ID_goodsType = HomeBwJingZhunEntity_.goodsType.f24389id;
    private static final int __ID_goodsUrl = HomeBwJingZhunEntity_.goodsUrl.f24389id;
    private static final int __ID_goodsId = HomeBwJingZhunEntity_.goodsId.f24389id;
    private static final int __ID_title = HomeBwJingZhunEntity_.title.f24389id;
    private static final int __ID_actType = HomeBwJingZhunEntity_.actType.f24389id;
    private static final int __ID_isOtherNet = HomeBwJingZhunEntity_.isOtherNet.f24389id;
    private static final int __ID_needLogin = HomeBwJingZhunEntity_.needLogin.f24389id;
    private static final int __ID_updateUser = HomeBwJingZhunEntity_.updateUser.f24389id;
    private static final int __ID_updateTime = HomeBwJingZhunEntity_.updateTime.f24389id;
    private static final int __ID_goodsStatus = HomeBwJingZhunEntity_.goodsStatus.f24389id;
    private static final int __ID_createTime = HomeBwJingZhunEntity_.createTime.f24389id;
    private static final int __ID_createUser = HomeBwJingZhunEntity_.createUser.f24389id;
    private static final int __ID_businessType = HomeBwJingZhunEntity_.businessType.f24389id;
    private static final int __ID_imgSrc = HomeBwJingZhunEntity_.imgSrc.f24389id;
    private static final int __ID_actId = HomeBwJingZhunEntity_.actId.f24389id;
    private static final int __ID_mobile = HomeBwJingZhunEntity_.mobile.f24389id;
    private static final int __ID_defImg = HomeBwJingZhunEntity_.defImg.f24389id;
    private static final int __ID_isCustomData = HomeBwJingZhunEntity_.isCustomData.f24389id;
    private static final int __ID_wxId = HomeBwJingZhunEntity_.wxId.f24389id;
    private static final int __ID_position = HomeBwJingZhunEntity_.position.f24389id;
    private static final int __ID_isMarketLineFlag = HomeBwJingZhunEntity_.isMarketLineFlag.f24389id;
    private static final int __ID_marketCapsuleTitle = HomeBwJingZhunEntity_.marketCapsuleTitle.f24389id;
    private static final int __ID_marketCapsuleImgSrc = HomeBwJingZhunEntity_.marketCapsuleImgSrc.f24389id;
    private static final int __ID_imgType = HomeBwJingZhunEntity_.imgType.f24389id;
    private static final int __ID_fontNumberMark = HomeBwJingZhunEntity_.fontNumberMark.f24389id;
    private static final int __ID_fontNumber = HomeBwJingZhunEntity_.fontNumber.f24389id;
    private static final int __ID_fontNumberUnit = HomeBwJingZhunEntity_.fontNumberUnit.f24389id;
    private static final int __ID_basemap = HomeBwJingZhunEntity_.basemap.f24389id;
    private static final int __ID_basemapColor = HomeBwJingZhunEntity_.basemapColor.f24389id;
    private static final int __ID_speed = HomeBwJingZhunEntity_.speed.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBwJingZhunEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<HomeBwJingZhunEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<HomeBwJingZhunEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new HomeBwJingZhunEntityCursor(transaction, j, boxStore);
        }
    }

    public HomeBwJingZhunEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, HomeBwJingZhunEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(HomeBwJingZhunEntity homeBwJingZhunEntity) {
        return ID_GETTER.getId(homeBwJingZhunEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(HomeBwJingZhunEntity homeBwJingZhunEntity) {
        String productActualType = homeBwJingZhunEntity.getProductActualType();
        int i = productActualType != null ? __ID_productActualType : 0;
        String source = homeBwJingZhunEntity.getSource();
        int i2 = source != null ? __ID_source : 0;
        String defaultRecommend = homeBwJingZhunEntity.getDefaultRecommend();
        int i3 = defaultRecommend != null ? __ID_defaultRecommend : 0;
        String viceTitle = homeBwJingZhunEntity.getViceTitle();
        collect400000(this.cursor, 0L, 1, i, productActualType, i2, source, i3, defaultRecommend, viceTitle != null ? __ID_viceTitle : 0, viceTitle);
        String beanId = homeBwJingZhunEntity.getBeanId();
        int i4 = beanId != null ? __ID_beanId : 0;
        String ruleId = homeBwJingZhunEntity.getRuleId();
        int i5 = ruleId != null ? __ID_ruleId : 0;
        String goodsType = homeBwJingZhunEntity.getGoodsType();
        int i6 = goodsType != null ? __ID_goodsType : 0;
        String goodsUrl = homeBwJingZhunEntity.getGoodsUrl();
        collect400000(this.cursor, 0L, 0, i4, beanId, i5, ruleId, i6, goodsType, goodsUrl != null ? __ID_goodsUrl : 0, goodsUrl);
        String goodsId = homeBwJingZhunEntity.getGoodsId();
        int i7 = goodsId != null ? __ID_goodsId : 0;
        String title = homeBwJingZhunEntity.getTitle();
        int i8 = title != null ? __ID_title : 0;
        String actType = homeBwJingZhunEntity.getActType();
        int i9 = actType != null ? __ID_actType : 0;
        String isOtherNet = homeBwJingZhunEntity.getIsOtherNet();
        collect400000(this.cursor, 0L, 0, i7, goodsId, i8, title, i9, actType, isOtherNet != null ? __ID_isOtherNet : 0, isOtherNet);
        String needLogin = homeBwJingZhunEntity.getNeedLogin();
        int i10 = needLogin != null ? __ID_needLogin : 0;
        String updateUser = homeBwJingZhunEntity.getUpdateUser();
        int i11 = updateUser != null ? __ID_updateUser : 0;
        String updateTime = homeBwJingZhunEntity.getUpdateTime();
        int i12 = updateTime != null ? __ID_updateTime : 0;
        String goodsStatus = homeBwJingZhunEntity.getGoodsStatus();
        collect400000(this.cursor, 0L, 0, i10, needLogin, i11, updateUser, i12, updateTime, goodsStatus != null ? __ID_goodsStatus : 0, goodsStatus);
        String createTime = homeBwJingZhunEntity.getCreateTime();
        int i13 = createTime != null ? __ID_createTime : 0;
        String createUser = homeBwJingZhunEntity.getCreateUser();
        int i14 = createUser != null ? __ID_createUser : 0;
        String businessType = homeBwJingZhunEntity.getBusinessType();
        int i15 = businessType != null ? __ID_businessType : 0;
        String imgSrc = homeBwJingZhunEntity.getImgSrc();
        collect400000(this.cursor, 0L, 0, i13, createTime, i14, createUser, i15, businessType, imgSrc != null ? __ID_imgSrc : 0, imgSrc);
        String actId = homeBwJingZhunEntity.getActId();
        int i16 = actId != null ? __ID_actId : 0;
        String mobile = homeBwJingZhunEntity.getMobile();
        int i17 = mobile != null ? __ID_mobile : 0;
        String wxId = homeBwJingZhunEntity.getWxId();
        int i18 = wxId != null ? __ID_wxId : 0;
        String position = homeBwJingZhunEntity.getPosition();
        collect400000(this.cursor, 0L, 0, i16, actId, i17, mobile, i18, wxId, position != null ? __ID_position : 0, position);
        String isMarketLineFlag = homeBwJingZhunEntity.getIsMarketLineFlag();
        int i19 = isMarketLineFlag != null ? __ID_isMarketLineFlag : 0;
        String marketCapsuleTitle = homeBwJingZhunEntity.getMarketCapsuleTitle();
        int i20 = marketCapsuleTitle != null ? __ID_marketCapsuleTitle : 0;
        String marketCapsuleImgSrc = homeBwJingZhunEntity.getMarketCapsuleImgSrc();
        int i21 = marketCapsuleImgSrc != null ? __ID_marketCapsuleImgSrc : 0;
        String imgType = homeBwJingZhunEntity.getImgType();
        collect400000(this.cursor, 0L, 0, i19, isMarketLineFlag, i20, marketCapsuleTitle, i21, marketCapsuleImgSrc, imgType != null ? __ID_imgType : 0, imgType);
        String fontNumberMark = homeBwJingZhunEntity.getFontNumberMark();
        int i22 = fontNumberMark != null ? __ID_fontNumberMark : 0;
        String fontNumber = homeBwJingZhunEntity.getFontNumber();
        int i23 = fontNumber != null ? __ID_fontNumber : 0;
        String fontNumberUnit = homeBwJingZhunEntity.getFontNumberUnit();
        int i24 = fontNumberUnit != null ? __ID_fontNumberUnit : 0;
        String basemap = homeBwJingZhunEntity.getBasemap();
        collect400000(this.cursor, 0L, 0, i22, fontNumberMark, i23, fontNumber, i24, fontNumberUnit, basemap != null ? __ID_basemap : 0, basemap);
        String basemapColor = homeBwJingZhunEntity.getBasemapColor();
        int i25 = basemapColor != null ? __ID_basemapColor : 0;
        String speed = homeBwJingZhunEntity.getSpeed();
        long collect313311 = collect313311(this.cursor, homeBwJingZhunEntity.getId(), 2, i25, basemapColor, speed != null ? __ID_speed : 0, speed, 0, null, 0, null, __ID_defImg, homeBwJingZhunEntity.getDefImg(), __ID_isCustomData, homeBwJingZhunEntity.isCustomData() ? 1L : 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        homeBwJingZhunEntity.setId(collect313311);
        return collect313311;
    }
}
