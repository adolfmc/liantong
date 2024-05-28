package com.sinovatech.unicom.separatemodule.tongyicaiji.entity;

import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.separatemodule.tongyicaiji.entity.TYImageDataEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class TYImageDataEntity_ implements EntityInfo<TYImageDataEntity> {
    public static final Property<TYImageDataEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "TYImageDataEntity";
    public static final int __ENTITY_ID = 44;
    public static final String __ENTITY_NAME = "TYImageDataEntity";
    public static final Property<TYImageDataEntity> __ID_PROPERTY;
    public static final Class<TYImageDataEntity> __ENTITY_CLASS = TYImageDataEntity.class;
    public static final CursorFactory<TYImageDataEntity> __CURSOR_FACTORY = new TYImageDataEntityCursor.Factory();
    @Internal
    static final TYImageDataEntityIdGetter __ID_GETTER = new TYImageDataEntityIdGetter();
    public static final TYImageDataEntity_ __INSTANCE = new TYImageDataEntity_();

    /* renamed from: id */
    public static final Property<TYImageDataEntity> f18619id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<TYImageDataEntity> url = new Property<>(__INSTANCE, 1, 2, String.class, "url");
    public static final Property<TYImageDataEntity> isError = new Property<>(__INSTANCE, 2, 3, String.class, "isError");
    public static final Property<TYImageDataEntity> timeStamp = new Property<>(__INSTANCE, 3, 4, String.class, MenuDataCenter.timestamp);
    public static final Property<TYImageDataEntity> businessname = new Property<>(__INSTANCE, 4, 5, String.class, "businessname");
    public static final Property<TYImageDataEntity> businessCode = new Property<>(__INSTANCE, 5, 6, String.class, "businessCode");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "TYImageDataEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 44;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "TYImageDataEntity";
    }

    static {
        Property<TYImageDataEntity> property = f18619id;
        __ALL_PROPERTIES = new Property[]{property, url, isError, timeStamp, businessname, businessCode};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<TYImageDataEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TYImageDataEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<TYImageDataEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<TYImageDataEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<TYImageDataEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class TYImageDataEntityIdGetter implements IdGetter<TYImageDataEntity> {
        TYImageDataEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(TYImageDataEntity tYImageDataEntity) {
            return tYImageDataEntity.getId();
        }
    }
}
