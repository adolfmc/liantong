package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanAuthCodeEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class AnquanAuthCodeEntityCursor extends Cursor<AnquanAuthCodeEntity> {
    private static final AnquanAuthCodeEntity_.AnquanAuthCodeEntityIdGetter ID_GETTER = AnquanAuthCodeEntity_.__ID_GETTER;
    private static final int __ID_isUserChecked = AnquanAuthCodeEntity_.isUserChecked.f24389id;
    private static final int __ID_authCanUsed = AnquanAuthCodeEntity_.authCanUsed.f24389id;
    private static final int __ID_gesture = AnquanAuthCodeEntity_.gesture.f24389id;
    private static final int __ID_touchId = AnquanAuthCodeEntity_.touchId.f24389id;
    private static final int __ID_faceId = AnquanAuthCodeEntity_.faceId.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<AnquanAuthCodeEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<AnquanAuthCodeEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new AnquanAuthCodeEntityCursor(transaction, j, boxStore);
        }
    }

    public AnquanAuthCodeEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, AnquanAuthCodeEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(AnquanAuthCodeEntity anquanAuthCodeEntity) {
        return ID_GETTER.getId(anquanAuthCodeEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(AnquanAuthCodeEntity anquanAuthCodeEntity) {
        int i = __ID_isUserChecked;
        boolean isUserChecked = anquanAuthCodeEntity.isUserChecked();
        long collect313311 = collect313311(this.cursor, anquanAuthCodeEntity.getId(), 3, 0, null, 0, null, 0, null, 0, null, __ID_gesture, anquanAuthCodeEntity.getGesture(), __ID_touchId, anquanAuthCodeEntity.getTouchId(), __ID_faceId, anquanAuthCodeEntity.getFaceId(), i, isUserChecked ? 1 : 0, __ID_authCanUsed, anquanAuthCodeEntity.isAuthCanUsed() ? 1 : 0, 0, 0, 0, 0.0f, 0, 0.0d);
        anquanAuthCodeEntity.setId(collect313311);
        return collect313311;
    }
}
