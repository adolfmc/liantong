package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpCollectionEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CumpCollectionEntityCursor extends Cursor<CumpCollectionEntity> {
    private static final CumpCollectionEntity_.CumpCollectionEntityIdGetter ID_GETTER = CumpCollectionEntity_.__ID_GETTER;
    private static final int __ID_appId = CumpCollectionEntity_.appId.f24389id;
    private static final int __ID_appName = CumpCollectionEntity_.appName.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CumpCollectionEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CumpCollectionEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CumpCollectionEntityCursor(transaction, j, boxStore);
        }
    }

    public CumpCollectionEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CumpCollectionEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CumpCollectionEntity cumpCollectionEntity) {
        return ID_GETTER.getId(cumpCollectionEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CumpCollectionEntity cumpCollectionEntity) {
        String appId = cumpCollectionEntity.getAppId();
        int i = appId != null ? __ID_appId : 0;
        String appName = cumpCollectionEntity.getAppName();
        long collect313311 = collect313311(this.cursor, cumpCollectionEntity.getId(), 3, i, appId, appName != null ? __ID_appName : 0, appName, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        cumpCollectionEntity.setId(collect313311);
        return collect313311;
    }
}
