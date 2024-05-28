package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.SkinEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.SkinEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class SkinEntity_ implements EntityInfo<SkinEntity> {
    public static final Property<SkinEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "SkinEntity";
    public static final int __ENTITY_ID = 4;
    public static final String __ENTITY_NAME = "SkinEntity";
    public static final Property<SkinEntity> __ID_PROPERTY;
    public static final Class<SkinEntity> __ENTITY_CLASS = SkinEntity.class;
    public static final CursorFactory<SkinEntity> __CURSOR_FACTORY = new SkinEntityCursor.Factory();
    @Internal
    static final SkinEntityIdGetter __ID_GETTER = new SkinEntityIdGetter();
    public static final SkinEntity_ __INSTANCE = new SkinEntity_();

    /* renamed from: id */
    public static final Property<SkinEntity> f18398id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<SkinEntity> skinId = new Property<>(__INSTANCE, 1, 2, String.class, "skinId");
    public static final Property<SkinEntity> userNumPriture = new Property<>(__INSTANCE, 2, 3, String.class, "userNumPriture");
    public static final Property<SkinEntity> updateTime = new Property<>(__INSTANCE, 3, 4, String.class, "updateTime");
    public static final Property<SkinEntity> homePicture = new Property<>(__INSTANCE, 4, 5, String.class, "homePicture");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "SkinEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 4;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "SkinEntity";
    }

    static {
        Property<SkinEntity> property = f18398id;
        __ALL_PROPERTIES = new Property[]{property, skinId, userNumPriture, updateTime, homePicture};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<SkinEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SkinEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SkinEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<SkinEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<SkinEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.SkinEntity_$SkinEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class SkinEntityIdGetter implements IdGetter<SkinEntity> {
        SkinEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(SkinEntity skinEntity) {
            return skinEntity.getId();
        }
    }
}
