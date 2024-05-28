package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeCardBgEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeCardBgEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class HomeCardBgEntity_ implements EntityInfo<HomeCardBgEntity> {
    public static final Property<HomeCardBgEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "HomeCardBgEntity";
    public static final int __ENTITY_ID = 28;
    public static final String __ENTITY_NAME = "HomeCardBgEntity";
    public static final Property<HomeCardBgEntity> __ID_PROPERTY;
    public static final Class<HomeCardBgEntity> __ENTITY_CLASS = HomeCardBgEntity.class;
    public static final CursorFactory<HomeCardBgEntity> __CURSOR_FACTORY = new HomeCardBgEntityCursor.Factory();
    @Internal
    static final HomeCardBgEntityIdGetter __ID_GETTER = new HomeCardBgEntityIdGetter();
    public static final HomeCardBgEntity_ __INSTANCE = new HomeCardBgEntity_();

    /* renamed from: id */
    public static final Property<HomeCardBgEntity> f18416id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<HomeCardBgEntity> isLogin = new Property<>(__INSTANCE, 1, 2, String.class, "isLogin");
    public static final Property<HomeCardBgEntity> productDefault = new Property<>(__INSTANCE, 2, 3, String.class, "productDefault");
    public static final Property<HomeCardBgEntity> productDesc = new Property<>(__INSTANCE, 3, 4, String.class, "productDesc");
    public static final Property<HomeCardBgEntity> productName = new Property<>(__INSTANCE, 4, 5, String.class, "productName");
    public static final Property<HomeCardBgEntity> productRedirecturl = new Property<>(__INSTANCE, 5, 6, String.class, "productRedirecturl");
    public static final Property<HomeCardBgEntity> productUrl = new Property<>(__INSTANCE, 6, 7, String.class, "productUrl");
    public static final Property<HomeCardBgEntity> productUrlType = new Property<>(__INSTANCE, 7, 8, String.class, "productUrlType");
    public static final Property<HomeCardBgEntity> ruleId = new Property<>(__INSTANCE, 8, 9, String.class, "ruleId");
    public static final Property<HomeCardBgEntity> specialType = new Property<>(__INSTANCE, 9, 10, String.class, "specialType");
    public static final Property<HomeCardBgEntity> strageId = new Property<>(__INSTANCE, 10, 11, String.class, "strageId");
    public static final Property<HomeCardBgEntity> year = new Property<>(__INSTANCE, 11, 12, String.class, "year");
    public static final Property<HomeCardBgEntity> cornerDesc = new Property<>(__INSTANCE, 12, 14, String.class, "cornerDesc");
    public static final Property<HomeCardBgEntity> productCode = new Property<>(__INSTANCE, 13, 15, String.class, "productCode");
    public static final Property<HomeCardBgEntity> mobile = new Property<>(__INSTANCE, 14, 16, String.class, "mobile");
    public static final Property<HomeCardBgEntity> cacheTime = new Property<>(__INSTANCE, 15, 17, String.class, "cacheTime");
    public static final Property<HomeCardBgEntity> backgroundPriority = new Property<>(__INSTANCE, 16, 18, String.class, "backgroundPriority");
    public static final Property<HomeCardBgEntity> goodsId = new Property<>(__INSTANCE, 17, 19, String.class, "goodsId");
    public static final Property<HomeCardBgEntity> actId = new Property<>(__INSTANCE, 18, 20, String.class, "actId");
    public static final Property<HomeCardBgEntity> actType = new Property<>(__INSTANCE, 19, 21, String.class, "actType");
    public static final Property<HomeCardBgEntity> imgDynamic = new Property<>(__INSTANCE, 20, 22, String.class, "imgDynamic");
    public static final Property<HomeCardBgEntity> textPictureColor = new Property<>(__INSTANCE, 21, 23, String.class, "textPictureColor");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "HomeCardBgEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 28;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "HomeCardBgEntity";
    }

    static {
        Property<HomeCardBgEntity> property = f18416id;
        __ALL_PROPERTIES = new Property[]{property, isLogin, productDefault, productDesc, productName, productRedirecturl, productUrl, productUrlType, ruleId, specialType, strageId, year, cornerDesc, productCode, mobile, cacheTime, backgroundPriority, goodsId, actId, actType, imgDynamic, textPictureColor};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<HomeCardBgEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeCardBgEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeCardBgEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<HomeCardBgEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<HomeCardBgEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeCardBgEntity_$HomeCardBgEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class HomeCardBgEntityIdGetter implements IdGetter<HomeCardBgEntity> {
        HomeCardBgEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(HomeCardBgEntity homeCardBgEntity) {
            return homeCardBgEntity.getId();
        }
    }
}
