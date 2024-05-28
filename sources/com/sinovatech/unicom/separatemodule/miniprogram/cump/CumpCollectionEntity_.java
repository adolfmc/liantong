package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpCollectionEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CumpCollectionEntity_ implements EntityInfo<CumpCollectionEntity> {
    public static final Property<CumpCollectionEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CumpCollectionEntity";
    public static final int __ENTITY_ID = 20;
    public static final String __ENTITY_NAME = "CumpCollectionEntity";
    public static final Property<CumpCollectionEntity> __ID_PROPERTY;
    public static final Class<CumpCollectionEntity> __ENTITY_CLASS = CumpCollectionEntity.class;
    public static final CursorFactory<CumpCollectionEntity> __CURSOR_FACTORY = new CumpCollectionEntityCursor.Factory();
    @Internal
    static final CumpCollectionEntityIdGetter __ID_GETTER = new CumpCollectionEntityIdGetter();
    public static final CumpCollectionEntity_ __INSTANCE = new CumpCollectionEntity_();

    /* renamed from: id */
    public static final Property<CumpCollectionEntity> f18560id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<CumpCollectionEntity> appId = new Property<>(__INSTANCE, 1, 2, String.class, "appId");
    public static final Property<CumpCollectionEntity> appName = new Property<>(__INSTANCE, 2, 3, String.class, "appName");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CumpCollectionEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 20;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CumpCollectionEntity";
    }

    static {
        Property<CumpCollectionEntity> property = f18560id;
        __ALL_PROPERTIES = new Property[]{property, appId, appName};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CumpCollectionEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpCollectionEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpCollectionEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CumpCollectionEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CumpCollectionEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CumpCollectionEntityIdGetter implements IdGetter<CumpCollectionEntity> {
        CumpCollectionEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CumpCollectionEntity cumpCollectionEntity) {
            return cumpCollectionEntity.getId();
        }
    }
}
