package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanWhiteEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class AnquanWhiteEntityCursor extends Cursor<AnquanWhiteEntity> {
    private static final AnquanWhiteEntity_.AnquanWhiteEntityIdGetter ID_GETTER = AnquanWhiteEntity_.__ID_GETTER;
    private static final int __ID_code = AnquanWhiteEntity_.code.f24389id;
    private static final int __ID_name = AnquanWhiteEntity_.name.f24389id;
    private static final int __ID_selected = AnquanWhiteEntity_.selected.f24389id;
    private static final int __ID_mobile = AnquanWhiteEntity_.mobile.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<AnquanWhiteEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<AnquanWhiteEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new AnquanWhiteEntityCursor(transaction, j, boxStore);
        }
    }

    public AnquanWhiteEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, AnquanWhiteEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(AnquanWhiteEntity anquanWhiteEntity) {
        return ID_GETTER.getId(anquanWhiteEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(AnquanWhiteEntity anquanWhiteEntity) {
        String code = anquanWhiteEntity.getCode();
        int i = code != null ? __ID_code : 0;
        String name = anquanWhiteEntity.getName();
        int i2 = name != null ? __ID_name : 0;
        String mobile = anquanWhiteEntity.getMobile();
        long collect313311 = collect313311(this.cursor, anquanWhiteEntity.getId(), 3, i, code, i2, name, mobile != null ? __ID_mobile : 0, mobile, 0, null, __ID_selected, anquanWhiteEntity.isSelected() ? 1L : 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        anquanWhiteEntity.setId(collect313311);
        return collect313311;
    }
}
