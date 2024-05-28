package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.LoginMemberEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.LoginMemberEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LoginMemberEntity_ implements EntityInfo<LoginMemberEntity> {
    public static final Property<LoginMemberEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "LoginMemberEntity";
    public static final int __ENTITY_ID = 17;
    public static final String __ENTITY_NAME = "LoginMemberEntity";
    public static final Property<LoginMemberEntity> __ID_PROPERTY;
    public static final Class<LoginMemberEntity> __ENTITY_CLASS = LoginMemberEntity.class;
    public static final CursorFactory<LoginMemberEntity> __CURSOR_FACTORY = new LoginMemberEntityCursor.Factory();
    @Internal
    static final LoginMemberEntityIdGetter __ID_GETTER = new LoginMemberEntityIdGetter();
    public static final LoginMemberEntity_ __INSTANCE = new LoginMemberEntity_();

    /* renamed from: id */
    public static final Property<LoginMemberEntity> f18387id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<LoginMemberEntity> typeName = new Property<>(__INSTANCE, 1, 2, String.class, "typeName");
    public static final Property<LoginMemberEntity> mainFlag = new Property<>(__INSTANCE, 2, 3, String.class, "mainFlag");
    public static final Property<LoginMemberEntity> encryption = new Property<>(__INSTANCE, 3, 4, String.class, "encryption");
    public static final Property<LoginMemberEntity> num = new Property<>(__INSTANCE, 4, 5, String.class, "num");
    public static final Property<LoginMemberEntity> type = new Property<>(__INSTANCE, 5, 6, String.class, "type");
    public static final Property<LoginMemberEntity> currentNum = new Property<>(__INSTANCE, 6, 7, String.class, "currentNum");
    public static final Property<LoginMemberEntity> origin = new Property<>(__INSTANCE, 7, 8, String.class, "origin");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "LoginMemberEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 17;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "LoginMemberEntity";
    }

    static {
        Property<LoginMemberEntity> property = f18387id;
        __ALL_PROPERTIES = new Property[]{property, typeName, mainFlag, encryption, num, type, currentNum, origin};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<LoginMemberEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LoginMemberEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LoginMemberEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<LoginMemberEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<LoginMemberEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.LoginMemberEntity_$LoginMemberEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class LoginMemberEntityIdGetter implements IdGetter<LoginMemberEntity> {
        LoginMemberEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(LoginMemberEntity loginMemberEntity) {
            return loginMemberEntity.getId();
        }
    }
}
