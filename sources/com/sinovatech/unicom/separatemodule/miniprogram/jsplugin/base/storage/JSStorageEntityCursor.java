package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.JSStorageEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class JSStorageEntityCursor extends Cursor<JSStorageEntity> {
    private static final JSStorageEntity_.JSStorageEntityIdGetter ID_GETTER = JSStorageEntity_.__ID_GETTER;
    private static final int __ID_key = JSStorageEntity_.key.f24389id;
    private static final int __ID_value = JSStorageEntity_.value.f24389id;
    private static final int __ID_appId = JSStorageEntity_.appId.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<JSStorageEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<JSStorageEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new JSStorageEntityCursor(transaction, j, boxStore);
        }
    }

    public JSStorageEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, JSStorageEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(JSStorageEntity jSStorageEntity) {
        return ID_GETTER.getId(jSStorageEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(JSStorageEntity jSStorageEntity) {
        String key = jSStorageEntity.getKey();
        int i = key != null ? __ID_key : 0;
        String value = jSStorageEntity.getValue();
        int i2 = value != null ? __ID_value : 0;
        String appId = jSStorageEntity.getAppId();
        long collect313311 = collect313311(this.cursor, jSStorageEntity.getId(), 3, i, key, i2, value, appId != null ? __ID_appId : 0, appId, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        jSStorageEntity.setId(collect313311);
        return collect313311;
    }
}
