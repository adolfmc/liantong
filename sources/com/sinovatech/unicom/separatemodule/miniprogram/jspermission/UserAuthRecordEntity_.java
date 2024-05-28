package com.sinovatech.unicom.separatemodule.miniprogram.jspermission;

import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class UserAuthRecordEntity_ implements EntityInfo<UserAuthRecordEntity> {
    public static final Property<UserAuthRecordEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "UserAuthRecordEntity";
    public static final int __ENTITY_ID = 24;
    public static final String __ENTITY_NAME = "UserAuthRecordEntity";
    public static final Property<UserAuthRecordEntity> __ID_PROPERTY;
    public static final Class<UserAuthRecordEntity> __ENTITY_CLASS = UserAuthRecordEntity.class;
    public static final CursorFactory<UserAuthRecordEntity> __CURSOR_FACTORY = new UserAuthRecordEntityCursor.Factory();
    @Internal
    static final UserAuthRecordEntityIdGetter __ID_GETTER = new UserAuthRecordEntityIdGetter();
    public static final UserAuthRecordEntity_ __INSTANCE = new UserAuthRecordEntity_();

    /* renamed from: id */
    public static final Property<UserAuthRecordEntity> f18568id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<UserAuthRecordEntity> userAccount = new Property<>(__INSTANCE, 1, 2, String.class, "userAccount");
    public static final Property<UserAuthRecordEntity> key = new Property<>(__INSTANCE, 2, 3, String.class, "key");
    public static final Property<UserAuthRecordEntity> scope = new Property<>(__INSTANCE, 3, 4, String.class, "scope");
    public static final Property<UserAuthRecordEntity> time = new Property<>(__INSTANCE, 4, 5, String.class, "time");
    public static final Property<UserAuthRecordEntity> isGrant = new Property<>(__INSTANCE, 5, 6, Boolean.TYPE, "isGrant");
    public static final Property<UserAuthRecordEntity> scene = new Property<>(__INSTANCE, 6, 7, String.class, "scene");
    public static final Property<UserAuthRecordEntity> reason = new Property<>(__INSTANCE, 7, 8, String.class, "reason");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "UserAuthRecordEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 24;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "UserAuthRecordEntity";
    }

    static {
        Property<UserAuthRecordEntity> property = f18568id;
        __ALL_PROPERTIES = new Property[]{property, userAccount, key, scope, time, isGrant, scene, reason};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<UserAuthRecordEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<UserAuthRecordEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<UserAuthRecordEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<UserAuthRecordEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<UserAuthRecordEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class UserAuthRecordEntityIdGetter implements IdGetter<UserAuthRecordEntity> {
        UserAuthRecordEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(UserAuthRecordEntity userAuthRecordEntity) {
            return userAuthRecordEntity.getId();
        }
    }
}
