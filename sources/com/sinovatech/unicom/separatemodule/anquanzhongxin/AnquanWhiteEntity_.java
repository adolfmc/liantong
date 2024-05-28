package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanWhiteEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class AnquanWhiteEntity_ implements EntityInfo<AnquanWhiteEntity> {
    public static final Property<AnquanWhiteEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "AnquanWhiteEntity";
    public static final int __ENTITY_ID = 30;
    public static final String __ENTITY_NAME = "AnquanWhiteEntity";
    public static final Property<AnquanWhiteEntity> __ID_PROPERTY;
    public static final Class<AnquanWhiteEntity> __ENTITY_CLASS = AnquanWhiteEntity.class;
    public static final CursorFactory<AnquanWhiteEntity> __CURSOR_FACTORY = new AnquanWhiteEntityCursor.Factory();
    @Internal
    static final AnquanWhiteEntityIdGetter __ID_GETTER = new AnquanWhiteEntityIdGetter();
    public static final AnquanWhiteEntity_ __INSTANCE = new AnquanWhiteEntity_();

    /* renamed from: id */
    public static final Property<AnquanWhiteEntity> f18460id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<AnquanWhiteEntity> code = new Property<>(__INSTANCE, 1, 2, String.class, "code");
    public static final Property<AnquanWhiteEntity> name = new Property<>(__INSTANCE, 2, 3, String.class, "name");
    public static final Property<AnquanWhiteEntity> selected = new Property<>(__INSTANCE, 3, 4, Boolean.TYPE, "selected");
    public static final Property<AnquanWhiteEntity> mobile = new Property<>(__INSTANCE, 4, 5, String.class, "mobile");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "AnquanWhiteEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 30;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "AnquanWhiteEntity";
    }

    static {
        Property<AnquanWhiteEntity> property = f18460id;
        __ALL_PROPERTIES = new Property[]{property, code, name, selected, mobile};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<AnquanWhiteEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AnquanWhiteEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AnquanWhiteEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<AnquanWhiteEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<AnquanWhiteEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class AnquanWhiteEntityIdGetter implements IdGetter<AnquanWhiteEntity> {
        AnquanWhiteEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(AnquanWhiteEntity anquanWhiteEntity) {
            return anquanWhiteEntity.getId();
        }
    }
}
