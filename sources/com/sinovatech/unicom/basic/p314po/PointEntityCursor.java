package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.PointEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.PointEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class PointEntityCursor extends Cursor<PointEntity> {
    private static final PointEntity_.PointEntityIdGetter ID_GETTER = PointEntity_.__ID_GETTER;
    private static final int __ID_menuId = PointEntity_.menuId.f24389id;
    private static final int __ID_tag = PointEntity_.tag.f24389id;
    private static final int __ID_type = PointEntity_.type.f24389id;
    private static final int __ID_mobile = PointEntity_.mobile.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.PointEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<PointEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<PointEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new PointEntityCursor(transaction, j, boxStore);
        }
    }

    public PointEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, PointEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(PointEntity pointEntity) {
        return ID_GETTER.getId(pointEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(PointEntity pointEntity) {
        String menuId = pointEntity.getMenuId();
        int i = menuId != null ? __ID_menuId : 0;
        String tag = pointEntity.getTag();
        int i2 = tag != null ? __ID_tag : 0;
        String type = pointEntity.getType();
        int i3 = type != null ? __ID_type : 0;
        String mobile = pointEntity.getMobile();
        long collect400000 = collect400000(this.cursor, pointEntity.getId(), 3, i, menuId, i2, tag, i3, type, mobile != null ? __ID_mobile : 0, mobile);
        pointEntity.setId(collect400000);
        return collect400000;
    }
}
