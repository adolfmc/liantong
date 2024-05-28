package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectionTabEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CollectionTabEntity_ implements EntityInfo<CollectionTabEntity> {
    public static final Property<CollectionTabEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CollectionTabEntity";
    public static final int __ENTITY_ID = 46;
    public static final String __ENTITY_NAME = "CollectionTabEntity";
    public static final Property<CollectionTabEntity> __ID_PROPERTY;
    public static final Class<CollectionTabEntity> __ENTITY_CLASS = CollectionTabEntity.class;
    public static final CursorFactory<CollectionTabEntity> __CURSOR_FACTORY = new CollectionTabEntityCursor.Factory();
    @Internal
    static final CollectionTabEntityIdGetter __ID_GETTER = new CollectionTabEntityIdGetter();
    public static final CollectionTabEntity_ __INSTANCE = new CollectionTabEntity_();

    /* renamed from: id */
    public static final Property<CollectionTabEntity> f18600id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<CollectionTabEntity> isSelect = new Property<>(__INSTANCE, 1, 2, Boolean.TYPE, "isSelect");
    public static final Property<CollectionTabEntity> categoryName = new Property<>(__INSTANCE, 2, 3, String.class, "categoryName");
    public static final Property<CollectionTabEntity> sort = new Property<>(__INSTANCE, 3, 4, String.class, "sort");
    public static final Property<CollectionTabEntity> categoryId = new Property<>(__INSTANCE, 4, 5, String.class, "categoryId");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CollectionTabEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 46;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CollectionTabEntity";
    }

    static {
        Property<CollectionTabEntity> property = f18600id;
        __ALL_PROPERTIES = new Property[]{property, isSelect, categoryName, sort, categoryId};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CollectionTabEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CollectionTabEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CollectionTabEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CollectionTabEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CollectionTabEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CollectionTabEntityIdGetter implements IdGetter<CollectionTabEntity> {
        CollectionTabEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CollectionTabEntity collectionTabEntity) {
            return collectionTabEntity.getId();
        }
    }
}
