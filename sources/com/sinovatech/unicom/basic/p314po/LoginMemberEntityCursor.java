package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.LoginMemberEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.LoginMemberEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class LoginMemberEntityCursor extends Cursor<LoginMemberEntity> {
    private static final LoginMemberEntity_.LoginMemberEntityIdGetter ID_GETTER = LoginMemberEntity_.__ID_GETTER;
    private static final int __ID_typeName = LoginMemberEntity_.typeName.f24389id;
    private static final int __ID_mainFlag = LoginMemberEntity_.mainFlag.f24389id;
    private static final int __ID_encryption = LoginMemberEntity_.encryption.f24389id;
    private static final int __ID_num = LoginMemberEntity_.num.f24389id;
    private static final int __ID_type = LoginMemberEntity_.type.f24389id;
    private static final int __ID_currentNum = LoginMemberEntity_.currentNum.f24389id;
    private static final int __ID_origin = LoginMemberEntity_.origin.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.LoginMemberEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<LoginMemberEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<LoginMemberEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new LoginMemberEntityCursor(transaction, j, boxStore);
        }
    }

    public LoginMemberEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, LoginMemberEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(LoginMemberEntity loginMemberEntity) {
        return ID_GETTER.getId(loginMemberEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(LoginMemberEntity loginMemberEntity) {
        String typeName = loginMemberEntity.getTypeName();
        int i = typeName != null ? __ID_typeName : 0;
        String mainFlag = loginMemberEntity.getMainFlag();
        int i2 = mainFlag != null ? __ID_mainFlag : 0;
        String encryption = loginMemberEntity.getEncryption();
        int i3 = encryption != null ? __ID_encryption : 0;
        String num = loginMemberEntity.getNum();
        collect400000(this.cursor, 0L, 1, i, typeName, i2, mainFlag, i3, encryption, num != null ? __ID_num : 0, num);
        String type = loginMemberEntity.getType();
        int i4 = type != null ? __ID_type : 0;
        String currentNum = loginMemberEntity.getCurrentNum();
        int i5 = currentNum != null ? __ID_currentNum : 0;
        String origin = loginMemberEntity.getOrigin();
        long collect313311 = collect313311(this.cursor, loginMemberEntity.getId(), 2, i4, type, i5, currentNum, origin != null ? __ID_origin : 0, origin, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        loginMemberEntity.setId(collect313311);
        return collect313311;
    }
}
