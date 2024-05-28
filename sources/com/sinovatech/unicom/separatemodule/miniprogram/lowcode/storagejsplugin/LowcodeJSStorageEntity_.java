package com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin;

import com.sinovatech.unicom.separatemodule.miniprogram.lowcode.storagejsplugin.LowcodeJSStorageEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LowcodeJSStorageEntity_ implements EntityInfo<LowcodeJSStorageEntity> {
    public static final Property<LowcodeJSStorageEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "LowcodeJSStorageEntity";
    public static final int __ENTITY_ID = 47;
    public static final String __ENTITY_NAME = "LowcodeJSStorageEntity";
    public static final Property<LowcodeJSStorageEntity> __ID_PROPERTY;
    public static final Class<LowcodeJSStorageEntity> __ENTITY_CLASS = LowcodeJSStorageEntity.class;
    public static final CursorFactory<LowcodeJSStorageEntity> __CURSOR_FACTORY = new LowcodeJSStorageEntityCursor.Factory();
    @Internal
    static final LowcodeJSStorageEntityIdGetter __ID_GETTER = new LowcodeJSStorageEntityIdGetter();
    public static final LowcodeJSStorageEntity_ __INSTANCE = new LowcodeJSStorageEntity_();

    /* renamed from: id */
    public static final Property<LowcodeJSStorageEntity> f18587id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<LowcodeJSStorageEntity> key = new Property<>(__INSTANCE, 1, 2, String.class, "key");
    public static final Property<LowcodeJSStorageEntity> value = new Property<>(__INSTANCE, 2, 3, String.class, "value");
    public static final Property<LowcodeJSStorageEntity> appId = new Property<>(__INSTANCE, 3, 4, String.class, "appId");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "LowcodeJSStorageEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 47;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "LowcodeJSStorageEntity";
    }

    static {
        Property<LowcodeJSStorageEntity> property = f18587id;
        __ALL_PROPERTIES = new Property[]{property, key, value, appId};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<LowcodeJSStorageEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LowcodeJSStorageEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LowcodeJSStorageEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<LowcodeJSStorageEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<LowcodeJSStorageEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class LowcodeJSStorageEntityIdGetter implements IdGetter<LowcodeJSStorageEntity> {
        LowcodeJSStorageEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(LowcodeJSStorageEntity lowcodeJSStorageEntity) {
            return lowcodeJSStorageEntity.getId();
        }
    }
}
