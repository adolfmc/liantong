package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.MenuBox_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.MenuBoxCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class MenuBoxCursor extends Cursor<MenuBox> {
    private static final MenuBox_.MenuBoxIdGetter ID_GETTER = MenuBox_.__ID_GETTER;
    private static final int __ID_mobile = MenuBox_.mobile.f24389id;
    private static final int __ID_proCode = MenuBox_.proCode.f24389id;
    private static final int __ID_content = MenuBox_.content.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.MenuBoxCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<MenuBox> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<MenuBox> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new MenuBoxCursor(transaction, j, boxStore);
        }
    }

    public MenuBoxCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, MenuBox_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(MenuBox menuBox) {
        return ID_GETTER.getId(menuBox);
    }

    @Override // io.objectbox.Cursor
    public final long put(MenuBox menuBox) {
        String mobile = menuBox.getMobile();
        int i = mobile != null ? __ID_mobile : 0;
        String proCode = menuBox.getProCode();
        int i2 = proCode != null ? __ID_proCode : 0;
        String content = menuBox.getContent();
        long collect313311 = collect313311(this.cursor, menuBox.getId(), 3, i, mobile, i2, proCode, content != null ? __ID_content : 0, content, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        menuBox.setId(collect313311);
        return collect313311;
    }
}
