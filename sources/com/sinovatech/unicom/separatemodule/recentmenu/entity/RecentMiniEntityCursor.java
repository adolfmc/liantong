package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class RecentMiniEntityCursor extends Cursor<RecentMiniEntity> {
    private static final RecentMiniEntity_.RecentMiniEntityIdGetter ID_GETTER = RecentMiniEntity_.__ID_GETTER;
    private static final int __ID_appId = RecentMiniEntity_.appId.f24389id;
    private static final int __ID_appImg = RecentMiniEntity_.appImg.f24389id;
    private static final int __ID_appName = RecentMiniEntity_.appName.f24389id;
    private static final int __ID_appletUrl = RecentMiniEntity_.appletUrl.f24389id;
    private static final int __ID_isH5 = RecentMiniEntity_.isH5.f24389id;
    private static final int __ID_mobile = RecentMiniEntity_.mobile.f24389id;
    private static final int __ID_isSelect = RecentMiniEntity_.isSelect.f24389id;
    private static final int __ID_timeTemp = RecentMiniEntity_.timeTemp.f24389id;
    private static final int __ID_type = RecentMiniEntity_.type.f24389id;
    private static final int __ID_dateString = RecentMiniEntity_.dateString.f24389id;
    private static final int __ID_productId = RecentMiniEntity_.productId.f24389id;
    private static final int __ID_createTime = RecentMiniEntity_.createTime.f24389id;
    private static final int __ID_state = RecentMiniEntity_.state.f24389id;
    private static final int __ID_appDesc = RecentMiniEntity_.appDesc.f24389id;
    private static final int __ID_isBianJi = RecentMiniEntity_.isBianJi.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<RecentMiniEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<RecentMiniEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new RecentMiniEntityCursor(transaction, j, boxStore);
        }
    }

    public RecentMiniEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, RecentMiniEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(RecentMiniEntity recentMiniEntity) {
        return ID_GETTER.getId(recentMiniEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(RecentMiniEntity recentMiniEntity) {
        String appId = recentMiniEntity.getAppId();
        int i = appId != null ? __ID_appId : 0;
        String appImg = recentMiniEntity.getAppImg();
        int i2 = appImg != null ? __ID_appImg : 0;
        String appName = recentMiniEntity.getAppName();
        int i3 = appName != null ? __ID_appName : 0;
        String appletUrl = recentMiniEntity.getAppletUrl();
        collect400000(this.cursor, 0L, 1, i, appId, i2, appImg, i3, appName, appletUrl != null ? __ID_appletUrl : 0, appletUrl);
        String mobile = recentMiniEntity.getMobile();
        int i4 = mobile != null ? __ID_mobile : 0;
        String dateString = recentMiniEntity.getDateString();
        int i5 = dateString != null ? __ID_dateString : 0;
        String productId = recentMiniEntity.getProductId();
        int i6 = productId != null ? __ID_productId : 0;
        String createTime = recentMiniEntity.getCreateTime();
        collect400000(this.cursor, 0L, 0, i4, mobile, i5, dateString, i6, productId, createTime != null ? __ID_createTime : 0, createTime);
        String state = recentMiniEntity.getState();
        int i7 = state != null ? __ID_state : 0;
        String appDesc = recentMiniEntity.getAppDesc();
        int i8 = appDesc != null ? __ID_appDesc : 0;
        long collect313311 = collect313311(this.cursor, recentMiniEntity.getId(), 2, i7, state, i8, appDesc, 0, null, 0, null, __ID_timeTemp, recentMiniEntity.getTimeTemp(), __ID_type, recentMiniEntity.getType(), __ID_isH5, recentMiniEntity.isH5() ? 1L : 0L, __ID_isSelect, recentMiniEntity.isSelect() ? 1 : 0, __ID_isBianJi, recentMiniEntity.isBianJi() ? 1 : 0, 0, 0, 0, 0.0f, 0, 0.0d);
        recentMiniEntity.setId(collect313311);
        return collect313311;
    }
}
