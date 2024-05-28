package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.BindNumberActivityEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.BindNumberActivityEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class BindNumberActivityEntityCursor extends Cursor<BindNumberActivityEntity> {
    private static final BindNumberActivityEntity_.BindNumberActivityEntityIdGetter ID_GETTER = BindNumberActivityEntity_.__ID_GETTER;
    private static final int __ID_mobile = BindNumberActivityEntity_.mobile.f24389id;
    private static final int __ID_userNumPriture = BindNumberActivityEntity_.userNumPriture.f24389id;
    private static final int __ID_updateTime = BindNumberActivityEntity_.updateTime.f24389id;
    private static final int __ID_productUrl = BindNumberActivityEntity_.productUrl.f24389id;
    private static final int __ID_clickTimeForCheckUpdated = BindNumberActivityEntity_.clickTimeForCheckUpdated.f24389id;
    private static final int __ID_productName = BindNumberActivityEntity_.productName.f24389id;
    private static final int __ID_userIdType = BindNumberActivityEntity_.userIdType.f24389id;
    private static final int __ID_userNumColor = BindNumberActivityEntity_.userNumColor.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.BindNumberActivityEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<BindNumberActivityEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<BindNumberActivityEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new BindNumberActivityEntityCursor(transaction, j, boxStore);
        }
    }

    public BindNumberActivityEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, BindNumberActivityEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(BindNumberActivityEntity bindNumberActivityEntity) {
        return ID_GETTER.getId(bindNumberActivityEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(BindNumberActivityEntity bindNumberActivityEntity) {
        String mobile = bindNumberActivityEntity.getMobile();
        int i = mobile != null ? __ID_mobile : 0;
        String userNumPriture = bindNumberActivityEntity.getUserNumPriture();
        int i2 = userNumPriture != null ? __ID_userNumPriture : 0;
        String updateTime = bindNumberActivityEntity.getUpdateTime();
        int i3 = updateTime != null ? __ID_updateTime : 0;
        String productUrl = bindNumberActivityEntity.getProductUrl();
        collect400000(this.cursor, 0L, 1, i, mobile, i2, userNumPriture, i3, updateTime, productUrl != null ? __ID_productUrl : 0, productUrl);
        String clickTimeForCheckUpdated = bindNumberActivityEntity.getClickTimeForCheckUpdated();
        int i4 = clickTimeForCheckUpdated != null ? __ID_clickTimeForCheckUpdated : 0;
        String productName = bindNumberActivityEntity.getProductName();
        int i5 = productName != null ? __ID_productName : 0;
        String userIdType = bindNumberActivityEntity.getUserIdType();
        int i6 = userIdType != null ? __ID_userIdType : 0;
        String userNumColor = bindNumberActivityEntity.getUserNumColor();
        long collect400000 = collect400000(this.cursor, bindNumberActivityEntity.getId(), 2, i4, clickTimeForCheckUpdated, i5, productName, i6, userIdType, userNumColor != null ? __ID_userNumColor : 0, userNumColor);
        bindNumberActivityEntity.setId(collect400000);
        return collect400000;
    }
}
