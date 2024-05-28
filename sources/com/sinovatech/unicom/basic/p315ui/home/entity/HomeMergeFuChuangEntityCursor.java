package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeMergeFuChuangEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HomeMergeFuChuangEntityCursor extends Cursor<HomeMergeFuChuangEntity> {
    private static final HomeMergeFuChuangEntity_.HomeMergeFuChuangEntityIdGetter ID_GETTER = HomeMergeFuChuangEntity_.__ID_GETTER;
    private static final int __ID_titleColor = HomeMergeFuChuangEntity_.titleColor.f24389id;
    private static final int __ID_advertiseTitle = HomeMergeFuChuangEntity_.advertiseTitle.f24389id;
    private static final int __ID_advertiseTargetURL = HomeMergeFuChuangEntity_.advertiseTargetURL.f24389id;
    private static final int __ID_advertiseImageURL = HomeMergeFuChuangEntity_.advertiseImageURL.f24389id;
    private static final int __ID_advertiseTargetType = HomeMergeFuChuangEntity_.advertiseTargetType.f24389id;
    private static final int __ID_idx = HomeMergeFuChuangEntity_.idx.f24389id;
    private static final int __ID_advertiseIndex = HomeMergeFuChuangEntity_.advertiseIndex.f24389id;
    private static final int __ID_isNeedLogin = HomeMergeFuChuangEntity_.isNeedLogin.f24389id;
    private static final int __ID_advertiseBackMode = HomeMergeFuChuangEntity_.advertiseBackMode.f24389id;
    private static final int __ID_advJson = HomeMergeFuChuangEntity_.advJson.f24389id;
    private static final int __ID_viceTitle = HomeMergeFuChuangEntity_.viceTitle.f24389id;
    private static final int __ID_handleNumber = HomeMergeFuChuangEntity_.handleNumber.f24389id;
    private static final int __ID_rightImgSrc = HomeMergeFuChuangEntity_.rightImgSrc.f24389id;
    private static final int __ID_imageSrcVII = HomeMergeFuChuangEntity_.imageSrcVII.f24389id;
    private static final int __ID_imageSrcVIIChecked = HomeMergeFuChuangEntity_.imageSrcVIIChecked.f24389id;
    private static final int __ID_ywCode = HomeMergeFuChuangEntity_.ywCode.f24389id;
    private static final int __ID_cityCode = HomeMergeFuChuangEntity_.cityCode.f24389id;
    private static final int __ID_provinceCode = HomeMergeFuChuangEntity_.provinceCode.f24389id;
    private static final int __ID_goodsId = HomeMergeFuChuangEntity_.goodsId.f24389id;
    private static final int __ID_actType = HomeMergeFuChuangEntity_.actType.f24389id;
    private static final int __ID_actId = HomeMergeFuChuangEntity_.actId.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeMergeFuChuangEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<HomeMergeFuChuangEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<HomeMergeFuChuangEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new HomeMergeFuChuangEntityCursor(transaction, j, boxStore);
        }
    }

    public HomeMergeFuChuangEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, HomeMergeFuChuangEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(HomeMergeFuChuangEntity homeMergeFuChuangEntity) {
        return ID_GETTER.getId(homeMergeFuChuangEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(HomeMergeFuChuangEntity homeMergeFuChuangEntity) {
        String str = homeMergeFuChuangEntity.advertiseTitle;
        int i = str != null ? __ID_advertiseTitle : 0;
        String str2 = homeMergeFuChuangEntity.advertiseTargetURL;
        int i2 = str2 != null ? __ID_advertiseTargetURL : 0;
        String str3 = homeMergeFuChuangEntity.advertiseImageURL;
        int i3 = str3 != null ? __ID_advertiseImageURL : 0;
        String str4 = homeMergeFuChuangEntity.advertiseTargetType;
        collect400000(this.cursor, 0L, 1, i, str, i2, str2, i3, str3, str4 != null ? __ID_advertiseTargetType : 0, str4);
        String str5 = homeMergeFuChuangEntity.idx;
        int i4 = str5 != null ? __ID_idx : 0;
        String advertiseIndex = homeMergeFuChuangEntity.getAdvertiseIndex();
        int i5 = advertiseIndex != null ? __ID_advertiseIndex : 0;
        String advertiseBackMode = homeMergeFuChuangEntity.getAdvertiseBackMode();
        int i6 = advertiseBackMode != null ? __ID_advertiseBackMode : 0;
        String advJson = homeMergeFuChuangEntity.getAdvJson();
        collect400000(this.cursor, 0L, 0, i4, str5, i5, advertiseIndex, i6, advertiseBackMode, advJson != null ? __ID_advJson : 0, advJson);
        String viceTitle = homeMergeFuChuangEntity.getViceTitle();
        int i7 = viceTitle != null ? __ID_viceTitle : 0;
        String handleNumber = homeMergeFuChuangEntity.getHandleNumber();
        int i8 = handleNumber != null ? __ID_handleNumber : 0;
        String rightImgSrc = homeMergeFuChuangEntity.getRightImgSrc();
        int i9 = rightImgSrc != null ? __ID_rightImgSrc : 0;
        String imageSrcVII = homeMergeFuChuangEntity.getImageSrcVII();
        collect400000(this.cursor, 0L, 0, i7, viceTitle, i8, handleNumber, i9, rightImgSrc, imageSrcVII != null ? __ID_imageSrcVII : 0, imageSrcVII);
        String imageSrcVIIChecked = homeMergeFuChuangEntity.getImageSrcVIIChecked();
        int i10 = imageSrcVIIChecked != null ? __ID_imageSrcVIIChecked : 0;
        String ywCode = homeMergeFuChuangEntity.getYwCode();
        int i11 = ywCode != null ? __ID_ywCode : 0;
        String cityCode = homeMergeFuChuangEntity.getCityCode();
        int i12 = cityCode != null ? __ID_cityCode : 0;
        String provinceCode = homeMergeFuChuangEntity.getProvinceCode();
        collect400000(this.cursor, 0L, 0, i10, imageSrcVIIChecked, i11, ywCode, i12, cityCode, provinceCode != null ? __ID_provinceCode : 0, provinceCode);
        String goodsId = homeMergeFuChuangEntity.getGoodsId();
        int i13 = goodsId != null ? __ID_goodsId : 0;
        String actType = homeMergeFuChuangEntity.getActType();
        int i14 = actType != null ? __ID_actType : 0;
        String actId = homeMergeFuChuangEntity.getActId();
        long collect313311 = collect313311(this.cursor, homeMergeFuChuangEntity.getId(), 2, i13, goodsId, i14, actType, actId != null ? __ID_actId : 0, actId, 0, null, __ID_titleColor, homeMergeFuChuangEntity.titleColor, __ID_isNeedLogin, homeMergeFuChuangEntity.isNeedLogin ? 1L : 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        homeMergeFuChuangEntity.setId(collect313311);
        return collect313311;
    }
}
