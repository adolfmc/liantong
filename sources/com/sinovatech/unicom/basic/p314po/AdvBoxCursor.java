package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.AdvBox_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.AdvBoxCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class AdvBoxCursor extends Cursor<AdvBox> {
    private static final AdvBox_.AdvBoxIdGetter ID_GETTER = AdvBox_.__ID_GETTER;
    private static final int __ID_mobile = AdvBox_.mobile.f24389id;
    private static final int __ID_proCode = AdvBox_.proCode.f24389id;
    private static final int __ID_cityCode = AdvBox_.cityCode.f24389id;
    private static final int __ID_content = AdvBox_.content.f24389id;
    private static final int __ID_ywCode = AdvBox_.ywCode.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.AdvBoxCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<AdvBox> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<AdvBox> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new AdvBoxCursor(transaction, j, boxStore);
        }
    }

    public AdvBoxCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, AdvBox_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(AdvBox advBox) {
        return ID_GETTER.getId(advBox);
    }

    @Override // io.objectbox.Cursor
    public final long put(AdvBox advBox) {
        String mobile = advBox.getMobile();
        int i = mobile != null ? __ID_mobile : 0;
        String proCode = advBox.getProCode();
        int i2 = proCode != null ? __ID_proCode : 0;
        String cityCode = advBox.getCityCode();
        int i3 = cityCode != null ? __ID_cityCode : 0;
        String content = advBox.getContent();
        collect400000(this.cursor, 0L, 1, i, mobile, i2, proCode, i3, cityCode, content != null ? __ID_content : 0, content);
        String ywCode = advBox.getYwCode();
        long collect313311 = collect313311(this.cursor, advBox.getId(), 2, ywCode != null ? __ID_ywCode : 0, ywCode, 0, null, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        advBox.setId(collect313311);
        return collect313311;
    }
}
