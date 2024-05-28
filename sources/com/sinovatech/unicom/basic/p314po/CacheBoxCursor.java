package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.CacheBox_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.CacheBoxCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CacheBoxCursor extends Cursor<CacheBox> {
    private static final CacheBox_.CacheBoxIdGetter ID_GETTER = CacheBox_.__ID_GETTER;
    private static final int __ID_key = CacheBox_.key.f24389id;
    private static final int __ID_content = CacheBox_.content.f24389id;
    private static final int __ID_userMobile = CacheBox_.userMobile.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.CacheBoxCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CacheBox> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CacheBox> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CacheBoxCursor(transaction, j, boxStore);
        }
    }

    public CacheBoxCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CacheBox_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CacheBox cacheBox) {
        return ID_GETTER.getId(cacheBox);
    }

    @Override // io.objectbox.Cursor
    public final long put(CacheBox cacheBox) {
        String key = cacheBox.getKey();
        int i = key != null ? __ID_key : 0;
        String content = cacheBox.getContent();
        int i2 = content != null ? __ID_content : 0;
        String userMobile = cacheBox.getUserMobile();
        long collect313311 = collect313311(this.cursor, cacheBox.getId(), 3, i, key, i2, content, userMobile != null ? __ID_userMobile : 0, userMobile, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        cacheBox.setId(collect313311);
        return collect313311;
    }
}
