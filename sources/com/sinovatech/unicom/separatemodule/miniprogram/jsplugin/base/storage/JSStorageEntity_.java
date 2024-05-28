package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage;

import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.JSStorageEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class JSStorageEntity_ implements EntityInfo<JSStorageEntity> {
    public static final Property<JSStorageEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "JSStorageEntity";
    public static final int __ENTITY_ID = 13;
    public static final String __ENTITY_NAME = "JSStorageEntity";
    public static final Property<JSStorageEntity> __ID_PROPERTY;
    public static final Class<JSStorageEntity> __ENTITY_CLASS = JSStorageEntity.class;
    public static final CursorFactory<JSStorageEntity> __CURSOR_FACTORY = new JSStorageEntityCursor.Factory();
    @Internal
    static final JSStorageEntityIdGetter __ID_GETTER = new JSStorageEntityIdGetter();
    public static final JSStorageEntity_ __INSTANCE = new JSStorageEntity_();

    /* renamed from: id */
    public static final Property<JSStorageEntity> f18583id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<JSStorageEntity> key = new Property<>(__INSTANCE, 1, 2, String.class, "key");
    public static final Property<JSStorageEntity> value = new Property<>(__INSTANCE, 2, 3, String.class, "value");
    public static final Property<JSStorageEntity> appId = new Property<>(__INSTANCE, 3, 4, String.class, "appId");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "JSStorageEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 13;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "JSStorageEntity";
    }

    static {
        Property<JSStorageEntity> property = f18583id;
        __ALL_PROPERTIES = new Property[]{property, key, value, appId};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<JSStorageEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<JSStorageEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<JSStorageEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<JSStorageEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<JSStorageEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class JSStorageEntityIdGetter implements IdGetter<JSStorageEntity> {
        JSStorageEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(JSStorageEntity jSStorageEntity) {
            return jSStorageEntity.getId();
        }
    }
}
