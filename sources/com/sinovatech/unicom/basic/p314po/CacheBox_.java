package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.CacheBoxCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.CacheBox_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CacheBox_ implements EntityInfo<CacheBox> {
    public static final Property<CacheBox>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CacheBox";
    public static final int __ENTITY_ID = 9;
    public static final String __ENTITY_NAME = "CacheBox";
    public static final Property<CacheBox> __ID_PROPERTY;
    public static final Class<CacheBox> __ENTITY_CLASS = CacheBox.class;
    public static final CursorFactory<CacheBox> __CURSOR_FACTORY = new CacheBoxCursor.Factory();
    @Internal
    static final CacheBoxIdGetter __ID_GETTER = new CacheBoxIdGetter();
    public static final CacheBox_ __INSTANCE = new CacheBox_();

    /* renamed from: id */
    public static final Property<CacheBox> f18376id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<CacheBox> key = new Property<>(__INSTANCE, 1, 2, String.class, "key");
    public static final Property<CacheBox> content = new Property<>(__INSTANCE, 2, 3, String.class, "content");
    public static final Property<CacheBox> userMobile = new Property<>(__INSTANCE, 3, 4, String.class, "userMobile");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CacheBox";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 9;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CacheBox";
    }

    static {
        Property<CacheBox> property = f18376id;
        __ALL_PROPERTIES = new Property[]{property, key, content, userMobile};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CacheBox> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CacheBox>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CacheBox> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CacheBox> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CacheBox> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.CacheBox_$CacheBoxIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CacheBoxIdGetter implements IdGetter<CacheBox> {
        CacheBoxIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CacheBox cacheBox) {
            return cacheBox.getId();
        }
    }
}
