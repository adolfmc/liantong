package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class AnquanzhongxinEntityCursor extends Cursor<AnquanzhongxinEntity> {
    private static final AnquanzhongxinEntity_.AnquanzhongxinEntityIdGetter ID_GETTER = AnquanzhongxinEntity_.__ID_GETTER;
    private static final int __ID_mobile = AnquanzhongxinEntity_.mobile.f24389id;
    private static final int __ID_groupChooseNumber = AnquanzhongxinEntity_.groupChooseNumber.f24389id;
    private static final int __ID_chooseGroup1 = AnquanzhongxinEntity_.chooseGroup1.f24389id;
    private static final int __ID_chooseGroup2 = AnquanzhongxinEntity_.chooseGroup2.f24389id;
    private static final int __ID_chooseGroup3 = AnquanzhongxinEntity_.chooseGroup3.f24389id;
    private static final int __ID_selected1 = AnquanzhongxinEntity_.selected1.f24389id;
    private static final int __ID_selected2 = AnquanzhongxinEntity_.selected2.f24389id;
    private static final int __ID_selected3 = AnquanzhongxinEntity_.selected3.f24389id;
    private static final int __ID_selected4 = AnquanzhongxinEntity_.selected4.f24389id;
    private static final int __ID_selected5 = AnquanzhongxinEntity_.selected5.f24389id;
    private static final int __ID_sign = AnquanzhongxinEntity_.sign.f24389id;
    private static final int __ID_signWay = AnquanzhongxinEntity_.signWay.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<AnquanzhongxinEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<AnquanzhongxinEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new AnquanzhongxinEntityCursor(transaction, j, boxStore);
        }
    }

    public AnquanzhongxinEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, AnquanzhongxinEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(AnquanzhongxinEntity anquanzhongxinEntity) {
        return ID_GETTER.getId(anquanzhongxinEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(AnquanzhongxinEntity anquanzhongxinEntity) {
        String mobile = anquanzhongxinEntity.getMobile();
        int i = mobile != null ? __ID_mobile : 0;
        String sign = anquanzhongxinEntity.getSign();
        int i2 = sign != null ? __ID_sign : 0;
        collect313311(this.cursor, 0L, 1, i, mobile, i2, sign, 0, null, 0, null, __ID_groupChooseNumber, anquanzhongxinEntity.getGroupChooseNumber(), __ID_signWay, anquanzhongxinEntity.getSignWay(), __ID_chooseGroup1, anquanzhongxinEntity.isChooseGroup1() ? 1L : 0L, __ID_chooseGroup2, anquanzhongxinEntity.isChooseGroup2() ? 1 : 0, __ID_chooseGroup3, anquanzhongxinEntity.isChooseGroup3() ? 1 : 0, __ID_selected1, anquanzhongxinEntity.isSelected1() ? 1 : 0, 0, 0.0f, 0, 0.0d);
        long collect004000 = collect004000(this.cursor, anquanzhongxinEntity.getId(), 2, __ID_selected2, anquanzhongxinEntity.isSelected2() ? 1L : 0L, __ID_selected3, anquanzhongxinEntity.isSelected3() ? 1L : 0L, __ID_selected4, anquanzhongxinEntity.isSelected4() ? 1L : 0L, __ID_selected5, anquanzhongxinEntity.isSelected5() ? 1L : 0L);
        anquanzhongxinEntity.setId(collect004000);
        return collect004000;
    }
}
