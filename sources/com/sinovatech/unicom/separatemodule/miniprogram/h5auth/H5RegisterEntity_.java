package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5RegisterEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class H5RegisterEntity_ implements EntityInfo<H5RegisterEntity> {
    public static final Property<H5RegisterEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "H5RegisterEntity";
    public static final int __ENTITY_ID = 36;
    public static final String __ENTITY_NAME = "H5RegisterEntity";
    public static final Property<H5RegisterEntity> __ID_PROPERTY;
    public static final Class<H5RegisterEntity> __ENTITY_CLASS = H5RegisterEntity.class;
    public static final CursorFactory<H5RegisterEntity> __CURSOR_FACTORY = new H5RegisterEntityCursor.Factory();
    @Internal
    static final H5RegisterEntityIdGetter __ID_GETTER = new H5RegisterEntityIdGetter();
    public static final H5RegisterEntity_ __INSTANCE = new H5RegisterEntity_();

    /* renamed from: id */
    public static final Property<H5RegisterEntity> f18566id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<H5RegisterEntity> appId = new Property<>(__INSTANCE, 1, 2, String.class, "appId");
    public static final Property<H5RegisterEntity> appName = new Property<>(__INSTANCE, 2, 3, String.class, "appName");
    public static final Property<H5RegisterEntity> h5Urls = new Property<>(__INSTANCE, 3, 4, String.class, "h5Urls");
    public static final Property<H5RegisterEntity> plugCodes = new Property<>(__INSTANCE, 4, 5, String.class, "plugCodes");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "H5RegisterEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 36;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "H5RegisterEntity";
    }

    static {
        Property<H5RegisterEntity> property = f18566id;
        __ALL_PROPERTIES = new Property[]{property, appId, appName, h5Urls, plugCodes};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<H5RegisterEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<H5RegisterEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<H5RegisterEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<H5RegisterEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<H5RegisterEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class H5RegisterEntityIdGetter implements IdGetter<H5RegisterEntity> {
        H5RegisterEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(H5RegisterEntity h5RegisterEntity) {
            return h5RegisterEntity.getId();
        }
    }
}
