package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CollectionTabEntityCursor extends Cursor<CollectionTabEntity> {
    private static final CollectionTabEntity_.CollectionTabEntityIdGetter ID_GETTER = CollectionTabEntity_.__ID_GETTER;
    private static final int __ID_isSelect = CollectionTabEntity_.isSelect.f24389id;
    private static final int __ID_categoryName = CollectionTabEntity_.categoryName.f24389id;
    private static final int __ID_sort = CollectionTabEntity_.sort.f24389id;
    private static final int __ID_categoryId = CollectionTabEntity_.categoryId.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CollectionTabEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CollectionTabEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CollectionTabEntityCursor(transaction, j, boxStore);
        }
    }

    public CollectionTabEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CollectionTabEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CollectionTabEntity collectionTabEntity) {
        return ID_GETTER.getId(collectionTabEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CollectionTabEntity collectionTabEntity) {
        String categoryName = collectionTabEntity.getCategoryName();
        int i = categoryName != null ? __ID_categoryName : 0;
        String sort = collectionTabEntity.getSort();
        int i2 = sort != null ? __ID_sort : 0;
        String categoryId = collectionTabEntity.getCategoryId();
        long collect313311 = collect313311(this.cursor, collectionTabEntity.getId(), 3, i, categoryName, i2, sort, categoryId != null ? __ID_categoryId : 0, categoryId, 0, null, __ID_isSelect, collectionTabEntity.isSelect() ? 1L : 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        collectionTabEntity.setId(collect313311);
        return collect313311;
    }
}
