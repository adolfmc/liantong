package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.CustomSkinChoseEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.CustomSkinChoseEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CustomSkinChoseEntity_ implements EntityInfo<CustomSkinChoseEntity> {
    public static final Property<CustomSkinChoseEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CustomSkinChoseEntity";
    public static final int __ENTITY_ID = 6;
    public static final String __ENTITY_NAME = "CustomSkinChoseEntity";
    public static final Property<CustomSkinChoseEntity> __ID_PROPERTY;
    public static final Class<CustomSkinChoseEntity> __ENTITY_CLASS = CustomSkinChoseEntity.class;
    public static final CursorFactory<CustomSkinChoseEntity> __CURSOR_FACTORY = new CustomSkinChoseEntityCursor.Factory();
    @Internal
    static final CustomSkinChoseEntityIdGetter __ID_GETTER = new CustomSkinChoseEntityIdGetter();
    public static final CustomSkinChoseEntity_ __INSTANCE = new CustomSkinChoseEntity_();
    public static final Property<CustomSkinChoseEntity> positionId = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "positionId", true, "positionId");

    /* renamed from: id */
    public static final Property<CustomSkinChoseEntity> f18380id = new Property<>(__INSTANCE, 1, 2, Long.TYPE, "id");
    public static final Property<CustomSkinChoseEntity> skinid = new Property<>(__INSTANCE, 2, 3, String.class, "skinid");
    public static final Property<CustomSkinChoseEntity> type = new Property<>(__INSTANCE, 3, 4, String.class, "type");
    public static final Property<CustomSkinChoseEntity> status = new Property<>(__INSTANCE, 4, 5, String.class, "status");
    public static final Property<CustomSkinChoseEntity> url = new Property<>(__INSTANCE, 5, 6, String.class, "url");
    public static final Property<CustomSkinChoseEntity> thumbUrl = new Property<>(__INSTANCE, 6, 7, String.class, "thumbUrl");
    public static final Property<CustomSkinChoseEntity> mineUrl = new Property<>(__INSTANCE, 7, 8, String.class, "mineUrl");
    public static final Property<CustomSkinChoseEntity> homeSmall = new Property<>(__INSTANCE, 8, 9, String.class, "homeSmall");
    public static final Property<CustomSkinChoseEntity> imgCode = new Property<>(__INSTANCE, 9, 10, String.class, "imgCode");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CustomSkinChoseEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 6;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CustomSkinChoseEntity";
    }

    static {
        Property<CustomSkinChoseEntity> property = positionId;
        __ALL_PROPERTIES = new Property[]{property, f18380id, skinid, type, status, url, thumbUrl, mineUrl, homeSmall, imgCode};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CustomSkinChoseEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CustomSkinChoseEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CustomSkinChoseEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CustomSkinChoseEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CustomSkinChoseEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.CustomSkinChoseEntity_$CustomSkinChoseEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CustomSkinChoseEntityIdGetter implements IdGetter<CustomSkinChoseEntity> {
        CustomSkinChoseEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CustomSkinChoseEntity customSkinChoseEntity) {
            return customSkinChoseEntity.getPositionId();
        }
    }
}
