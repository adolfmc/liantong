package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYNetWorkEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class TYNetWorkEntityCursor extends Cursor<TYNetWorkEntity> {
    private static final TYNetWorkEntity_.TYNetWorkEntityIdGetter ID_GETTER = TYNetWorkEntity_.__ID_GETTER;
    private static final int __ID_url = TYNetWorkEntity_.url.f24389id;
    private static final int __ID_isError = TYNetWorkEntity_.isError.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<TYNetWorkEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<TYNetWorkEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new TYNetWorkEntityCursor(transaction, j, boxStore);
        }
    }

    public TYNetWorkEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, TYNetWorkEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(TYNetWorkEntity tYNetWorkEntity) {
        return ID_GETTER.getId(tYNetWorkEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(TYNetWorkEntity tYNetWorkEntity) {
        String url = tYNetWorkEntity.getUrl();
        int i = url != null ? __ID_url : 0;
        String isError = tYNetWorkEntity.getIsError();
        long collect313311 = collect313311(this.cursor, tYNetWorkEntity.getId(), 3, i, url, isError != null ? __ID_isError : 0, isError, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        tYNetWorkEntity.setId(collect313311);
        return collect313311;
    }
}
