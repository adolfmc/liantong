package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYCJtBoxEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class TYCJtBoxEntity_ implements EntityInfo<TYCJtBoxEntity> {
    public static final Property<TYCJtBoxEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "TYCJtBoxEntity";
    public static final int __ENTITY_ID = 41;
    public static final String __ENTITY_NAME = "TYCJtBoxEntity";
    public static final Property<TYCJtBoxEntity> __ID_PROPERTY;
    public static final Class<TYCJtBoxEntity> __ENTITY_CLASS = TYCJtBoxEntity.class;
    public static final CursorFactory<TYCJtBoxEntity> __CURSOR_FACTORY = new TYCJtBoxEntityCursor.Factory();
    @Internal
    static final TYCJtBoxEntityIdGetter __ID_GETTER = new TYCJtBoxEntityIdGetter();
    public static final TYCJtBoxEntity_ __INSTANCE = new TYCJtBoxEntity_();

    /* renamed from: id */
    public static final Property<TYCJtBoxEntity> f18617id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<TYCJtBoxEntity> json = new Property<>(__INSTANCE, 1, 2, String.class, "json");
    public static final Property<TYCJtBoxEntity> channelTopic = new Property<>(__INSTANCE, 2, 3, String.class, "channelTopic");
    public static final Property<TYCJtBoxEntity> apiName = new Property<>(__INSTANCE, 3, 4, String.class, "apiName");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "TYCJtBoxEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 41;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "TYCJtBoxEntity";
    }

    static {
        Property<TYCJtBoxEntity> property = f18617id;
        __ALL_PROPERTIES = new Property[]{property, json, channelTopic, apiName};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<TYCJtBoxEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TYCJtBoxEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TYCJtBoxEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<TYCJtBoxEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<TYCJtBoxEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class TYCJtBoxEntityIdGetter implements IdGetter<TYCJtBoxEntity> {
        TYCJtBoxEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(TYCJtBoxEntity tYCJtBoxEntity) {
            return tYCJtBoxEntity.getId();
        }
    }
}
