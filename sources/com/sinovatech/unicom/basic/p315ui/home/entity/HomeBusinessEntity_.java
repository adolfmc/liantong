package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBusinessEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBusinessEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class HomeBusinessEntity_ implements EntityInfo<HomeBusinessEntity> {
    public static final Property<HomeBusinessEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "HomeBusinessEntity";
    public static final int __ENTITY_ID = 26;
    public static final String __ENTITY_NAME = "HomeBusinessEntity";
    public static final Property<HomeBusinessEntity> __ID_PROPERTY;
    public static final Class<HomeBusinessEntity> __ENTITY_CLASS = HomeBusinessEntity.class;
    public static final CursorFactory<HomeBusinessEntity> __CURSOR_FACTORY = new HomeBusinessEntityCursor.Factory();
    @Internal
    static final HomeBusinessEntityIdGetter __ID_GETTER = new HomeBusinessEntityIdGetter();
    public static final HomeBusinessEntity_ __INSTANCE = new HomeBusinessEntity_();

    /* renamed from: id */
    public static final Property<HomeBusinessEntity> f18412id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<HomeBusinessEntity> isLogin = new Property<>(__INSTANCE, 1, 2, String.class, "isLogin");
    public static final Property<HomeBusinessEntity> productDefault = new Property<>(__INSTANCE, 2, 3, String.class, "productDefault");
    public static final Property<HomeBusinessEntity> productDesc = new Property<>(__INSTANCE, 3, 4, String.class, "productDesc");
    public static final Property<HomeBusinessEntity> productName = new Property<>(__INSTANCE, 4, 5, String.class, "productName");
    public static final Property<HomeBusinessEntity> productRedirecturl = new Property<>(__INSTANCE, 5, 6, String.class, "productRedirecturl");
    public static final Property<HomeBusinessEntity> productUrl = new Property<>(__INSTANCE, 6, 7, String.class, "productUrl");
    public static final Property<HomeBusinessEntity> productUrlType = new Property<>(__INSTANCE, 7, 8, String.class, "productUrlType");
    public static final Property<HomeBusinessEntity> ruleId = new Property<>(__INSTANCE, 8, 9, String.class, "ruleId");
    public static final Property<HomeBusinessEntity> specialType = new Property<>(__INSTANCE, 9, 10, String.class, "specialType");
    public static final Property<HomeBusinessEntity> strageId = new Property<>(__INSTANCE, 10, 11, String.class, "strageId");
    public static final Property<HomeBusinessEntity> year = new Property<>(__INSTANCE, 11, 12, String.class, "year");
    public static final Property<HomeBusinessEntity> defImg = new Property<>(__INSTANCE, 12, 13, Integer.TYPE, "defImg");
    public static final Property<HomeBusinessEntity> productCode = new Property<>(__INSTANCE, 13, 14, String.class, "productCode");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "HomeBusinessEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 26;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "HomeBusinessEntity";
    }

    static {
        Property<HomeBusinessEntity> property = f18412id;
        __ALL_PROPERTIES = new Property[]{property, isLogin, productDefault, productDesc, productName, productRedirecturl, productUrl, productUrlType, ruleId, specialType, strageId, year, defImg, productCode};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<HomeBusinessEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeBusinessEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeBusinessEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<HomeBusinessEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<HomeBusinessEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBusinessEntity_$HomeBusinessEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class HomeBusinessEntityIdGetter implements IdGetter<HomeBusinessEntity> {
        HomeBusinessEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(HomeBusinessEntity homeBusinessEntity) {
            return homeBusinessEntity.getId();
        }
    }
}
