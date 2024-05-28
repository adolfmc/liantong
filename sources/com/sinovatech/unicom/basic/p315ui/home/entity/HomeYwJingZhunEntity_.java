package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeYwJingZhunEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeYwJingZhunEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class HomeYwJingZhunEntity_ implements EntityInfo<HomeYwJingZhunEntity> {
    public static final Property<HomeYwJingZhunEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "HomeYwJingZhunEntity";
    public static final int __ENTITY_ID = 27;
    public static final String __ENTITY_NAME = "HomeYwJingZhunEntity";
    public static final Property<HomeYwJingZhunEntity> __ID_PROPERTY;
    public static final Class<HomeYwJingZhunEntity> __ENTITY_CLASS = HomeYwJingZhunEntity.class;
    public static final CursorFactory<HomeYwJingZhunEntity> __CURSOR_FACTORY = new HomeYwJingZhunEntityCursor.Factory();
    @Internal
    static final HomeYwJingZhunEntityIdGetter __ID_GETTER = new HomeYwJingZhunEntityIdGetter();
    public static final HomeYwJingZhunEntity_ __INSTANCE = new HomeYwJingZhunEntity_();

    /* renamed from: id */
    public static final Property<HomeYwJingZhunEntity> f18420id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<HomeYwJingZhunEntity> isLogin = new Property<>(__INSTANCE, 1, 2, String.class, "isLogin");
    public static final Property<HomeYwJingZhunEntity> productDefault = new Property<>(__INSTANCE, 2, 3, String.class, "productDefault");
    public static final Property<HomeYwJingZhunEntity> productDesc = new Property<>(__INSTANCE, 3, 4, String.class, "productDesc");
    public static final Property<HomeYwJingZhunEntity> productName = new Property<>(__INSTANCE, 4, 5, String.class, "productName");
    public static final Property<HomeYwJingZhunEntity> productRedirecturl = new Property<>(__INSTANCE, 5, 6, String.class, "productRedirecturl");
    public static final Property<HomeYwJingZhunEntity> productUrl = new Property<>(__INSTANCE, 6, 7, String.class, "productUrl");
    public static final Property<HomeYwJingZhunEntity> productUrlType = new Property<>(__INSTANCE, 7, 8, String.class, "productUrlType");
    public static final Property<HomeYwJingZhunEntity> ruleId = new Property<>(__INSTANCE, 8, 9, String.class, "ruleId");
    public static final Property<HomeYwJingZhunEntity> specialType = new Property<>(__INSTANCE, 9, 10, String.class, "specialType");
    public static final Property<HomeYwJingZhunEntity> strageId = new Property<>(__INSTANCE, 10, 11, String.class, "strageId");
    public static final Property<HomeYwJingZhunEntity> year = new Property<>(__INSTANCE, 11, 12, String.class, "year");
    public static final Property<HomeYwJingZhunEntity> mobile = new Property<>(__INSTANCE, 12, 14, String.class, "mobile");
    public static final Property<HomeYwJingZhunEntity> defImg = new Property<>(__INSTANCE, 13, 13, Integer.TYPE, "defImg");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "HomeYwJingZhunEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 27;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "HomeYwJingZhunEntity";
    }

    static {
        Property<HomeYwJingZhunEntity> property = f18420id;
        __ALL_PROPERTIES = new Property[]{property, isLogin, productDefault, productDesc, productName, productRedirecturl, productUrl, productUrlType, ruleId, specialType, strageId, year, mobile, defImg};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<HomeYwJingZhunEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeYwJingZhunEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeYwJingZhunEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<HomeYwJingZhunEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<HomeYwJingZhunEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeYwJingZhunEntity_$HomeYwJingZhunEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class HomeYwJingZhunEntityIdGetter implements IdGetter<HomeYwJingZhunEntity> {
        HomeYwJingZhunEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(HomeYwJingZhunEntity homeYwJingZhunEntity) {
            return homeYwJingZhunEntity.getId();
        }
    }
}
