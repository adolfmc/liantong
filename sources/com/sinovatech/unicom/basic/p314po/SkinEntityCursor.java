package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.SkinEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.SkinEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SkinEntityCursor extends Cursor<SkinEntity> {
    private static final SkinEntity_.SkinEntityIdGetter ID_GETTER = SkinEntity_.__ID_GETTER;
    private static final int __ID_skinId = SkinEntity_.skinId.f24389id;
    private static final int __ID_userNumPriture = SkinEntity_.userNumPriture.f24389id;
    private static final int __ID_updateTime = SkinEntity_.updateTime.f24389id;
    private static final int __ID_homePicture = SkinEntity_.homePicture.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.SkinEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<SkinEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<SkinEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new SkinEntityCursor(transaction, j, boxStore);
        }
    }

    public SkinEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, SkinEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(SkinEntity skinEntity) {
        return ID_GETTER.getId(skinEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(SkinEntity skinEntity) {
        String skinId = skinEntity.getSkinId();
        int i = skinId != null ? __ID_skinId : 0;
        String userNumPriture = skinEntity.getUserNumPriture();
        int i2 = userNumPriture != null ? __ID_userNumPriture : 0;
        String updateTime = skinEntity.getUpdateTime();
        int i3 = updateTime != null ? __ID_updateTime : 0;
        String homePicture = skinEntity.getHomePicture();
        long collect400000 = collect400000(this.cursor, skinEntity.getId(), 3, i, skinId, i2, userNumPriture, i3, updateTime, homePicture != null ? __ID_homePicture : 0, homePicture);
        skinEntity.setId(collect400000);
        return collect400000;
    }
}
