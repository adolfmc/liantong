package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.PointEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.PointEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class PointEntity_ implements EntityInfo<PointEntity> {
    public static final Property<PointEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "PointEntity";
    public static final int __ENTITY_ID = 12;
    public static final String __ENTITY_NAME = "PointEntity";
    public static final Property<PointEntity> __ID_PROPERTY;
    public static final Class<PointEntity> __ENTITY_CLASS = PointEntity.class;
    public static final CursorFactory<PointEntity> __CURSOR_FACTORY = new PointEntityCursor.Factory();
    @Internal
    static final PointEntityIdGetter __ID_GETTER = new PointEntityIdGetter();
    public static final PointEntity_ __INSTANCE = new PointEntity_();

    /* renamed from: id */
    public static final Property<PointEntity> f18393id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<PointEntity> menuId = new Property<>(__INSTANCE, 1, 2, String.class, "menuId");
    public static final Property<PointEntity> tag = new Property<>(__INSTANCE, 2, 3, String.class, "tag");
    public static final Property<PointEntity> type = new Property<>(__INSTANCE, 3, 4, String.class, "type");
    public static final Property<PointEntity> mobile = new Property<>(__INSTANCE, 4, 5, String.class, "mobile");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "PointEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 12;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "PointEntity";
    }

    static {
        Property<PointEntity> property = f18393id;
        __ALL_PROPERTIES = new Property[]{property, menuId, tag, type, mobile};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<PointEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<PointEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<PointEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<PointEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<PointEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.PointEntity_$PointEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class PointEntityIdGetter implements IdGetter<PointEntity> {
        PointEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(PointEntity pointEntity) {
            return pointEntity.getId();
        }
    }
}
