package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class UserAuthRecordEntityCursor extends Cursor<UserAuthRecordEntity> {
    private static final UserAuthRecordEntity_.UserAuthRecordEntityIdGetter ID_GETTER = UserAuthRecordEntity_.__ID_GETTER;
    private static final int __ID_userAccount = UserAuthRecordEntity_.userAccount.f24389id;
    private static final int __ID_key = UserAuthRecordEntity_.key.f24389id;
    private static final int __ID_scope = UserAuthRecordEntity_.scope.f24389id;
    private static final int __ID_time = UserAuthRecordEntity_.time.f24389id;
    private static final int __ID_isGrant = UserAuthRecordEntity_.isGrant.f24389id;
    private static final int __ID_scene = UserAuthRecordEntity_.scene.f24389id;
    private static final int __ID_reason = UserAuthRecordEntity_.reason.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<UserAuthRecordEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<UserAuthRecordEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new UserAuthRecordEntityCursor(transaction, j, boxStore);
        }
    }

    public UserAuthRecordEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, UserAuthRecordEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(UserAuthRecordEntity userAuthRecordEntity) {
        return ID_GETTER.getId(userAuthRecordEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(UserAuthRecordEntity userAuthRecordEntity) {
        String userAccount = userAuthRecordEntity.getUserAccount();
        int i = userAccount != null ? __ID_userAccount : 0;
        String key = userAuthRecordEntity.getKey();
        int i2 = key != null ? __ID_key : 0;
        String scope = userAuthRecordEntity.getScope();
        int i3 = scope != null ? __ID_scope : 0;
        String time = userAuthRecordEntity.getTime();
        collect400000(this.cursor, 0L, 1, i, userAccount, i2, key, i3, scope, time != null ? __ID_time : 0, time);
        String scene = userAuthRecordEntity.getScene();
        int i4 = scene != null ? __ID_scene : 0;
        String reason = userAuthRecordEntity.getReason();
        long collect313311 = collect313311(this.cursor, userAuthRecordEntity.getId(), 2, i4, scene, reason != null ? __ID_reason : 0, reason, 0, null, 0, null, __ID_isGrant, userAuthRecordEntity.isGrant() ? 1L : 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        userAuthRecordEntity.setId(collect313311);
        return collect313311;
    }
}
