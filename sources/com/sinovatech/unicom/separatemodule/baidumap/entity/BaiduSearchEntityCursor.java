package com.sinovatech.unicom.separatemodule.baidumap.entity;

import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiduSearchEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class BaiduSearchEntityCursor extends Cursor<BaiduSearchEntity> {
    private static final BaiduSearchEntity_.BaiduSearchEntityIdGetter ID_GETTER = BaiduSearchEntity_.__ID_GETTER;
    private static final int __ID_mobile = BaiduSearchEntity_.mobile.f24389id;
    private static final int __ID_proviceCode = BaiduSearchEntity_.proviceCode.f24389id;
    private static final int __ID_cityCode = BaiduSearchEntity_.cityCode.f24389id;
    private static final int __ID_searchId = BaiduSearchEntity_.searchId.f24389id;
    private static final int __ID_title = BaiduSearchEntity_.title.f24389id;
    private static final int __ID_url = BaiduSearchEntity_.url.f24389id;
    private static final int __ID_imageURL = BaiduSearchEntity_.imageURL.f24389id;
    private static final int __ID_desc = BaiduSearchEntity_.desc.f24389id;
    private static final int __ID_price = BaiduSearchEntity_.price.f24389id;
    private static final int __ID_needLogin = BaiduSearchEntity_.needLogin.f24389id;
    private static final int __ID_backMode = BaiduSearchEntity_.backMode.f24389id;
    private static final int __ID_isGroup = BaiduSearchEntity_.isGroup.f24389id;
    private static final int __ID_sectionsTitle = BaiduSearchEntity_.sectionsTitle.f24389id;
    private static final int __ID_sectionsIndex = BaiduSearchEntity_.sectionsIndex.f24389id;
    private static final int __ID_type = BaiduSearchEntity_.type.f24389id;
    private static final int __ID_isFooter = BaiduSearchEntity_.isFooter.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<BaiduSearchEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<BaiduSearchEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new BaiduSearchEntityCursor(transaction, j, boxStore);
        }
    }

    public BaiduSearchEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, BaiduSearchEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(BaiduSearchEntity baiduSearchEntity) {
        return ID_GETTER.getId(baiduSearchEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(BaiduSearchEntity baiduSearchEntity) {
        String mobile = baiduSearchEntity.getMobile();
        int i = mobile != null ? __ID_mobile : 0;
        String proviceCode = baiduSearchEntity.getProviceCode();
        int i2 = proviceCode != null ? __ID_proviceCode : 0;
        String cityCode = baiduSearchEntity.getCityCode();
        int i3 = cityCode != null ? __ID_cityCode : 0;
        String searchId = baiduSearchEntity.getSearchId();
        collect400000(this.cursor, 0L, 1, i, mobile, i2, proviceCode, i3, cityCode, searchId != null ? __ID_searchId : 0, searchId);
        String title = baiduSearchEntity.getTitle();
        int i4 = title != null ? __ID_title : 0;
        String url = baiduSearchEntity.getUrl();
        int i5 = url != null ? __ID_url : 0;
        String imageURL = baiduSearchEntity.getImageURL();
        int i6 = imageURL != null ? __ID_imageURL : 0;
        String desc = baiduSearchEntity.getDesc();
        collect400000(this.cursor, 0L, 0, i4, title, i5, url, i6, imageURL, desc != null ? __ID_desc : 0, desc);
        String price = baiduSearchEntity.getPrice();
        int i7 = price != null ? __ID_price : 0;
        String needLogin = baiduSearchEntity.getNeedLogin();
        int i8 = needLogin != null ? __ID_needLogin : 0;
        String str = baiduSearchEntity.backMode;
        int i9 = str != null ? __ID_backMode : 0;
        String str2 = baiduSearchEntity.sectionsTitle;
        collect400000(this.cursor, 0L, 0, i7, price, i8, needLogin, i9, str, str2 != null ? __ID_sectionsTitle : 0, str2);
        String type = baiduSearchEntity.getType();
        long collect313311 = collect313311(this.cursor, baiduSearchEntity.getId(), 2, type != null ? __ID_type : 0, type, 0, null, 0, null, 0, null, __ID_sectionsIndex, baiduSearchEntity.sectionsIndex, __ID_isGroup, baiduSearchEntity.isGroup ? 1L : 0L, __ID_isFooter, baiduSearchEntity.isFooter() ? 1L : 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        baiduSearchEntity.setId(collect313311);
        return collect313311;
    }
}
