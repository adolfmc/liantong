package com.sinovatech.unicom.separatemodule.search;

import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class SearchHuoDongEntity_ implements EntityInfo<SearchHuoDongEntity> {
    public static final Property<SearchHuoDongEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "SearchHuoDongEntity";
    public static final int __ENTITY_ID = 16;
    public static final String __ENTITY_NAME = "SearchHuoDongEntity";
    public static final Property<SearchHuoDongEntity> __ID_PROPERTY;
    public static final Class<SearchHuoDongEntity> __ENTITY_CLASS = SearchHuoDongEntity.class;
    public static final CursorFactory<SearchHuoDongEntity> __CURSOR_FACTORY = new SearchHuoDongEntityCursor.Factory();
    @Internal
    static final SearchHuoDongEntityIdGetter __ID_GETTER = new SearchHuoDongEntityIdGetter();
    public static final SearchHuoDongEntity_ __INSTANCE = new SearchHuoDongEntity_();

    /* renamed from: id */
    public static final Property<SearchHuoDongEntity> f18609id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<SearchHuoDongEntity> actType = new Property<>(__INSTANCE, 1, 2, String.class, "actType");
    public static final Property<SearchHuoDongEntity> hallId = new Property<>(__INSTANCE, 2, 3, String.class, "hallId");
    public static final Property<SearchHuoDongEntity> huodongId = new Property<>(__INSTANCE, 3, 4, String.class, "huodongId");
    public static final Property<SearchHuoDongEntity> isNeedLogin = new Property<>(__INSTANCE, 4, 5, String.class, "isNeedLogin");
    public static final Property<SearchHuoDongEntity> linkUrl = new Property<>(__INSTANCE, 5, 6, String.class, "linkUrl");
    public static final Property<SearchHuoDongEntity> searchWordName = new Property<>(__INSTANCE, 6, 7, String.class, "searchWordName");
    public static final Property<SearchHuoDongEntity> time = new Property<>(__INSTANCE, 7, 8, String.class, "time");
    public static final Property<SearchHuoDongEntity> mobile = new Property<>(__INSTANCE, 8, 9, String.class, "mobile");
    public static final Property<SearchHuoDongEntity> proviceCode = new Property<>(__INSTANCE, 9, 10, String.class, "proviceCode");
    public static final Property<SearchHuoDongEntity> cityCode = new Property<>(__INSTANCE, 10, 11, String.class, "cityCode");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "SearchHuoDongEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 16;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "SearchHuoDongEntity";
    }

    static {
        Property<SearchHuoDongEntity> property = f18609id;
        __ALL_PROPERTIES = new Property[]{property, actType, hallId, huodongId, isNeedLogin, linkUrl, searchWordName, time, mobile, proviceCode, cityCode};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<SearchHuoDongEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SearchHuoDongEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SearchHuoDongEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<SearchHuoDongEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<SearchHuoDongEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class SearchHuoDongEntityIdGetter implements IdGetter<SearchHuoDongEntity> {
        SearchHuoDongEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(SearchHuoDongEntity searchHuoDongEntity) {
            return searchHuoDongEntity.getId();
        }
    }
}
