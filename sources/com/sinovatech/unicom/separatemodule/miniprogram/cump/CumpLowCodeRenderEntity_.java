package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLowCodeRenderEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CumpLowCodeRenderEntity_ implements EntityInfo<CumpLowCodeRenderEntity> {
    public static final Property<CumpLowCodeRenderEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CumpLowCodeRenderEntity";
    public static final int __ENTITY_ID = 48;
    public static final String __ENTITY_NAME = "CumpLowCodeRenderEntity";
    public static final Property<CumpLowCodeRenderEntity> __ID_PROPERTY;
    public static final Class<CumpLowCodeRenderEntity> __ENTITY_CLASS = CumpLowCodeRenderEntity.class;
    public static final CursorFactory<CumpLowCodeRenderEntity> __CURSOR_FACTORY = new CumpLowCodeRenderEntityCursor.Factory();
    @Internal
    static final CumpLowCodeRenderEntityIdGetter __ID_GETTER = new CumpLowCodeRenderEntityIdGetter();
    public static final CumpLowCodeRenderEntity_ __INSTANCE = new CumpLowCodeRenderEntity_();

    /* renamed from: id */
    public static final Property<CumpLowCodeRenderEntity> f18564id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<CumpLowCodeRenderEntity> releaseNotes = new Property<>(__INSTANCE, 1, 2, String.class, "releaseNotes");
    public static final Property<CumpLowCodeRenderEntity> downloadUrl = new Property<>(__INSTANCE, 2, 3, String.class, "downloadUrl");
    public static final Property<CumpLowCodeRenderEntity> checksum = new Property<>(__INSTANCE, 3, 4, String.class, "checksum");
    public static final Property<CumpLowCodeRenderEntity> version = new Property<>(__INSTANCE, 4, 5, String.class, "version");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CumpLowCodeRenderEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 48;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CumpLowCodeRenderEntity";
    }

    static {
        Property<CumpLowCodeRenderEntity> property = f18564id;
        __ALL_PROPERTIES = new Property[]{property, releaseNotes, downloadUrl, checksum, version};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CumpLowCodeRenderEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpLowCodeRenderEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpLowCodeRenderEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CumpLowCodeRenderEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CumpLowCodeRenderEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CumpLowCodeRenderEntityIdGetter implements IdGetter<CumpLowCodeRenderEntity> {
        CumpLowCodeRenderEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CumpLowCodeRenderEntity cumpLowCodeRenderEntity) {
            return cumpLowCodeRenderEntity.getId();
        }
    }
}
