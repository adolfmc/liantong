package com.sinovatech.unicom.separatemodule.baidumap.entity;

import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiduSearchEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class BaiduSearchEntity_ implements EntityInfo<BaiduSearchEntity> {
    public static final Property<BaiduSearchEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "BaiduSearchEntity";
    public static final int __ENTITY_ID = 7;
    public static final String __ENTITY_NAME = "BaiduSearchEntity";
    public static final Property<BaiduSearchEntity> __ID_PROPERTY;
    public static final Class<BaiduSearchEntity> __ENTITY_CLASS = BaiduSearchEntity.class;
    public static final CursorFactory<BaiduSearchEntity> __CURSOR_FACTORY = new BaiduSearchEntityCursor.Factory();
    @Internal
    static final BaiduSearchEntityIdGetter __ID_GETTER = new BaiduSearchEntityIdGetter();
    public static final BaiduSearchEntity_ __INSTANCE = new BaiduSearchEntity_();

    /* renamed from: id */
    public static final Property<BaiduSearchEntity> f18501id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<BaiduSearchEntity> mobile = new Property<>(__INSTANCE, 1, 2, String.class, "mobile");
    public static final Property<BaiduSearchEntity> proviceCode = new Property<>(__INSTANCE, 2, 3, String.class, "proviceCode");
    public static final Property<BaiduSearchEntity> cityCode = new Property<>(__INSTANCE, 3, 4, String.class, "cityCode");
    public static final Property<BaiduSearchEntity> searchId = new Property<>(__INSTANCE, 4, 5, String.class, "searchId");
    public static final Property<BaiduSearchEntity> title = new Property<>(__INSTANCE, 5, 6, String.class, "title");
    public static final Property<BaiduSearchEntity> url = new Property<>(__INSTANCE, 6, 7, String.class, "url");
    public static final Property<BaiduSearchEntity> imageURL = new Property<>(__INSTANCE, 7, 8, String.class, "imageURL");
    public static final Property<BaiduSearchEntity> desc = new Property<>(__INSTANCE, 8, 9, String.class, "desc");
    public static final Property<BaiduSearchEntity> price = new Property<>(__INSTANCE, 9, 10, String.class, "price");
    public static final Property<BaiduSearchEntity> needLogin = new Property<>(__INSTANCE, 10, 11, String.class, "needLogin");
    public static final Property<BaiduSearchEntity> backMode = new Property<>(__INSTANCE, 11, 12, String.class, "backMode");
    public static final Property<BaiduSearchEntity> isGroup = new Property<>(__INSTANCE, 12, 13, Boolean.TYPE, "isGroup");
    public static final Property<BaiduSearchEntity> sectionsTitle = new Property<>(__INSTANCE, 13, 14, String.class, "sectionsTitle");
    public static final Property<BaiduSearchEntity> sectionsIndex = new Property<>(__INSTANCE, 14, 15, Integer.TYPE, "sectionsIndex");
    public static final Property<BaiduSearchEntity> type = new Property<>(__INSTANCE, 15, 16, String.class, "type");
    public static final Property<BaiduSearchEntity> isFooter = new Property<>(__INSTANCE, 16, 17, Boolean.TYPE, "isFooter");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "BaiduSearchEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 7;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "BaiduSearchEntity";
    }

    static {
        Property<BaiduSearchEntity> property = f18501id;
        __ALL_PROPERTIES = new Property[]{property, mobile, proviceCode, cityCode, searchId, title, url, imageURL, desc, price, needLogin, backMode, isGroup, sectionsTitle, sectionsIndex, type, isFooter};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<BaiduSearchEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<BaiduSearchEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<BaiduSearchEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<BaiduSearchEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<BaiduSearchEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class BaiduSearchEntityIdGetter implements IdGetter<BaiduSearchEntity> {
        BaiduSearchEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(BaiduSearchEntity baiduSearchEntity) {
            return baiduSearchEntity.getId();
        }
    }
}
