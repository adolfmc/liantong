package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJtBoxEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class TYCJtBoxEntityCursor extends Cursor<TYCJtBoxEntity> {
    private static final TYCJtBoxEntity_.TYCJtBoxEntityIdGetter ID_GETTER = TYCJtBoxEntity_.__ID_GETTER;
    private static final int __ID_json = TYCJtBoxEntity_.json.f24389id;
    private static final int __ID_channelTopic = TYCJtBoxEntity_.channelTopic.f24389id;
    private static final int __ID_apiName = TYCJtBoxEntity_.apiName.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<TYCJtBoxEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<TYCJtBoxEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new TYCJtBoxEntityCursor(transaction, j, boxStore);
        }
    }

    public TYCJtBoxEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, TYCJtBoxEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(TYCJtBoxEntity tYCJtBoxEntity) {
        return ID_GETTER.getId(tYCJtBoxEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(TYCJtBoxEntity tYCJtBoxEntity) {
        String json = tYCJtBoxEntity.getJson();
        int i = json != null ? __ID_json : 0;
        String channelTopic = tYCJtBoxEntity.getChannelTopic();
        int i2 = channelTopic != null ? __ID_channelTopic : 0;
        String apiName = tYCJtBoxEntity.getApiName();
        long collect313311 = collect313311(this.cursor, tYCJtBoxEntity.getId(), 3, i, json, i2, channelTopic, apiName != null ? __ID_apiName : 0, apiName, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        tYCJtBoxEntity.setId(collect313311);
        return collect313311;
    }
}
