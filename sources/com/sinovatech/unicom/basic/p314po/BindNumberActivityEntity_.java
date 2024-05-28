package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.BindNumberActivityEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.BindNumberActivityEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class BindNumberActivityEntity_ implements EntityInfo<BindNumberActivityEntity> {
    public static final Property<BindNumberActivityEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "BindNumberActivityEntity";
    public static final int __ENTITY_ID = 3;
    public static final String __ENTITY_NAME = "BindNumberActivityEntity";
    public static final Property<BindNumberActivityEntity> __ID_PROPERTY;
    public static final Class<BindNumberActivityEntity> __ENTITY_CLASS = BindNumberActivityEntity.class;
    public static final CursorFactory<BindNumberActivityEntity> __CURSOR_FACTORY = new BindNumberActivityEntityCursor.Factory();
    @Internal
    static final BindNumberActivityEntityIdGetter __ID_GETTER = new BindNumberActivityEntityIdGetter();
    public static final BindNumberActivityEntity_ __INSTANCE = new BindNumberActivityEntity_();

    /* renamed from: id */
    public static final Property<BindNumberActivityEntity> f18374id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<BindNumberActivityEntity> mobile = new Property<>(__INSTANCE, 1, 2, String.class, "mobile");
    public static final Property<BindNumberActivityEntity> userNumPriture = new Property<>(__INSTANCE, 2, 3, String.class, "userNumPriture");
    public static final Property<BindNumberActivityEntity> updateTime = new Property<>(__INSTANCE, 3, 4, String.class, "updateTime");
    public static final Property<BindNumberActivityEntity> productUrl = new Property<>(__INSTANCE, 4, 5, String.class, "productUrl");
    public static final Property<BindNumberActivityEntity> clickTimeForCheckUpdated = new Property<>(__INSTANCE, 5, 6, String.class, "clickTimeForCheckUpdated");
    public static final Property<BindNumberActivityEntity> productName = new Property<>(__INSTANCE, 6, 7, String.class, "productName");
    public static final Property<BindNumberActivityEntity> userIdType = new Property<>(__INSTANCE, 7, 8, String.class, "userIdType");
    public static final Property<BindNumberActivityEntity> userNumColor = new Property<>(__INSTANCE, 8, 9, String.class, "userNumColor");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "BindNumberActivityEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 3;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "BindNumberActivityEntity";
    }

    static {
        Property<BindNumberActivityEntity> property = f18374id;
        __ALL_PROPERTIES = new Property[]{property, mobile, userNumPriture, updateTime, productUrl, clickTimeForCheckUpdated, productName, userIdType, userNumColor};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<BindNumberActivityEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<BindNumberActivityEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<BindNumberActivityEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<BindNumberActivityEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<BindNumberActivityEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.BindNumberActivityEntity_$BindNumberActivityEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class BindNumberActivityEntityIdGetter implements IdGetter<BindNumberActivityEntity> {
        BindNumberActivityEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(BindNumberActivityEntity bindNumberActivityEntity) {
            return bindNumberActivityEntity.getId();
        }
    }
}
