package com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin;

import com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin.LowcodeJSStorageEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class LowcodeJSStorageEntityCursor extends Cursor<LowcodeJSStorageEntity> {
    private static final LowcodeJSStorageEntity_.LowcodeJSStorageEntityIdGetter ID_GETTER = LowcodeJSStorageEntity_.__ID_GETTER;
    private static final int __ID_key = LowcodeJSStorageEntity_.key.f24389id;
    private static final int __ID_value = LowcodeJSStorageEntity_.value.f24389id;
    private static final int __ID_appId = LowcodeJSStorageEntity_.appId.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<LowcodeJSStorageEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<LowcodeJSStorageEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new LowcodeJSStorageEntityCursor(transaction, j, boxStore);
        }
    }

    public LowcodeJSStorageEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, LowcodeJSStorageEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(LowcodeJSStorageEntity lowcodeJSStorageEntity) {
        return ID_GETTER.getId(lowcodeJSStorageEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(LowcodeJSStorageEntity lowcodeJSStorageEntity) {
        String key = lowcodeJSStorageEntity.getKey();
        int i = key != null ? __ID_key : 0;
        String value = lowcodeJSStorageEntity.getValue();
        int i2 = value != null ? __ID_value : 0;
        String appId = lowcodeJSStorageEntity.getAppId();
        long collect313311 = collect313311(this.cursor, lowcodeJSStorageEntity.getId(), 3, i, key, i2, value, appId != null ? __ID_appId : 0, appId, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        lowcodeJSStorageEntity.setId(collect313311);
        return collect313311;
    }
}
