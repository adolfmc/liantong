package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYNetWorkEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class TYNetWorkEntity_ implements EntityInfo<TYNetWorkEntity> {
    public static final Property<TYNetWorkEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "TYNetWorkEntity";
    public static final int __ENTITY_ID = 43;
    public static final String __ENTITY_NAME = "TYNetWorkEntity";
    public static final Property<TYNetWorkEntity> __ID_PROPERTY;
    public static final Class<TYNetWorkEntity> __ENTITY_CLASS = TYNetWorkEntity.class;
    public static final CursorFactory<TYNetWorkEntity> __CURSOR_FACTORY = new TYNetWorkEntityCursor.Factory();
    @Internal
    static final TYNetWorkEntityIdGetter __ID_GETTER = new TYNetWorkEntityIdGetter();
    public static final TYNetWorkEntity_ __INSTANCE = new TYNetWorkEntity_();

    /* renamed from: id */
    public static final Property<TYNetWorkEntity> f18621id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<TYNetWorkEntity> url = new Property<>(__INSTANCE, 1, 2, String.class, "url");
    public static final Property<TYNetWorkEntity> isError = new Property<>(__INSTANCE, 2, 3, String.class, "isError");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "TYNetWorkEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 43;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "TYNetWorkEntity";
    }

    static {
        Property<TYNetWorkEntity> property = f18621id;
        __ALL_PROPERTIES = new Property[]{property, url, isError};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<TYNetWorkEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TYNetWorkEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TYNetWorkEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<TYNetWorkEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<TYNetWorkEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class TYNetWorkEntityIdGetter implements IdGetter<TYNetWorkEntity> {
        TYNetWorkEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(TYNetWorkEntity tYNetWorkEntity) {
            return tYNetWorkEntity.getId();
        }
    }
}
