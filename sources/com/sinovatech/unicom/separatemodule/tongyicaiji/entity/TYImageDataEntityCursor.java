package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYImageDataEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class TYImageDataEntityCursor extends Cursor<TYImageDataEntity> {
    private static final TYImageDataEntity_.TYImageDataEntityIdGetter ID_GETTER = TYImageDataEntity_.__ID_GETTER;
    private static final int __ID_url = TYImageDataEntity_.url.f24389id;
    private static final int __ID_isError = TYImageDataEntity_.isError.f24389id;
    private static final int __ID_timeStamp = TYImageDataEntity_.timeStamp.f24389id;
    private static final int __ID_businessname = TYImageDataEntity_.businessname.f24389id;
    private static final int __ID_businessCode = TYImageDataEntity_.businessCode.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<TYImageDataEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<TYImageDataEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new TYImageDataEntityCursor(transaction, j, boxStore);
        }
    }

    public TYImageDataEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, TYImageDataEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(TYImageDataEntity tYImageDataEntity) {
        return ID_GETTER.getId(tYImageDataEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(TYImageDataEntity tYImageDataEntity) {
        String url = tYImageDataEntity.getUrl();
        int i = url != null ? __ID_url : 0;
        String isError = tYImageDataEntity.getIsError();
        int i2 = isError != null ? __ID_isError : 0;
        String timeStamp = tYImageDataEntity.getTimeStamp();
        int i3 = timeStamp != null ? __ID_timeStamp : 0;
        String businessname = tYImageDataEntity.getBusinessname();
        collect400000(this.cursor, 0L, 1, i, url, i2, isError, i3, timeStamp, businessname != null ? __ID_businessname : 0, businessname);
        String businessCode = tYImageDataEntity.getBusinessCode();
        long collect313311 = collect313311(this.cursor, tYImageDataEntity.getId(), 2, businessCode != null ? __ID_businessCode : 0, businessCode, 0, null, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        tYImageDataEntity.setId(collect313311);
        return collect313311;
    }
}
