package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.CustomSkinEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.CustomSkinEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CustomSkinEntity_ implements EntityInfo<CustomSkinEntity> {
    public static final Property<CustomSkinEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CustomSkinEntity";
    public static final int __ENTITY_ID = 5;
    public static final String __ENTITY_NAME = "CustomSkinEntity";
    public static final Property<CustomSkinEntity> __ID_PROPERTY;
    public static final Class<CustomSkinEntity> __ENTITY_CLASS = CustomSkinEntity.class;
    public static final CursorFactory<CustomSkinEntity> __CURSOR_FACTORY = new CustomSkinEntityCursor.Factory();
    @Internal
    static final CustomSkinEntityIdGetter __ID_GETTER = new CustomSkinEntityIdGetter();
    public static final CustomSkinEntity_ __INSTANCE = new CustomSkinEntity_();
    public static final Property<CustomSkinEntity> positionId = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "positionId", true, "positionId");

    /* renamed from: id */
    public static final Property<CustomSkinEntity> f18382id = new Property<>(__INSTANCE, 1, 2, Long.TYPE, "id");
    public static final Property<CustomSkinEntity> skinid = new Property<>(__INSTANCE, 2, 3, String.class, "skinid");
    public static final Property<CustomSkinEntity> type = new Property<>(__INSTANCE, 3, 4, String.class, "type");
    public static final Property<CustomSkinEntity> status = new Property<>(__INSTANCE, 4, 5, String.class, "status");
    public static final Property<CustomSkinEntity> url = new Property<>(__INSTANCE, 5, 6, String.class, "url");
    public static final Property<CustomSkinEntity> thumbUrl = new Property<>(__INSTANCE, 6, 7, String.class, "thumbUrl");
    public static final Property<CustomSkinEntity> mineUrl = new Property<>(__INSTANCE, 7, 8, String.class, "mineUrl");
    public static final Property<CustomSkinEntity> homeSmall = new Property<>(__INSTANCE, 8, 9, String.class, "homeSmall");
    public static final Property<CustomSkinEntity> imgCode = new Property<>(__INSTANCE, 9, 10, String.class, "imgCode");
    public static final Property<CustomSkinEntity> index = new Property<>(__INSTANCE, 10, 11, Integer.TYPE, "index");
    public static final Property<CustomSkinEntity> textColor = new Property<>(__INSTANCE, 11, 12, Integer.TYPE, "textColor");
    public static final Property<CustomSkinEntity> settingImageUrl = new Property<>(__INSTANCE, 12, 13, String.class, "settingImageUrl");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CustomSkinEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 5;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CustomSkinEntity";
    }

    static {
        Property<CustomSkinEntity> property = positionId;
        __ALL_PROPERTIES = new Property[]{property, f18382id, skinid, type, status, url, thumbUrl, mineUrl, homeSmall, imgCode, index, textColor, settingImageUrl};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CustomSkinEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CustomSkinEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CustomSkinEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CustomSkinEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CustomSkinEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.CustomSkinEntity_$CustomSkinEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CustomSkinEntityIdGetter implements IdGetter<CustomSkinEntity> {
        CustomSkinEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CustomSkinEntity customSkinEntity) {
            return customSkinEntity.getPositionId();
        }
    }
}
