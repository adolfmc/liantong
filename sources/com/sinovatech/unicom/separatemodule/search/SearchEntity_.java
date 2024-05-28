package com.sinovatech.unicom.separatemodule.search;

import com.sinovatech.unicom.separatemodule.search.SearchEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class SearchEntity_ implements EntityInfo<SearchEntity> {
    public static final Property<SearchEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "SearchEntity";
    public static final int __ENTITY_ID = 15;
    public static final String __ENTITY_NAME = "SearchEntity";
    public static final Property<SearchEntity> __ID_PROPERTY;
    public static final Class<SearchEntity> __ENTITY_CLASS = SearchEntity.class;
    public static final CursorFactory<SearchEntity> __CURSOR_FACTORY = new SearchEntityCursor.Factory();
    @Internal
    static final SearchEntityIdGetter __ID_GETTER = new SearchEntityIdGetter();
    public static final SearchEntity_ __INSTANCE = new SearchEntity_();

    /* renamed from: id */
    public static final Property<SearchEntity> f18607id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<SearchEntity> mobile = new Property<>(__INSTANCE, 1, 2, String.class, "mobile");
    public static final Property<SearchEntity> commandCode = new Property<>(__INSTANCE, 2, 3, String.class, "commandCode");
    public static final Property<SearchEntity> proviceCode = new Property<>(__INSTANCE, 3, 4, String.class, "proviceCode");
    public static final Property<SearchEntity> cityCode = new Property<>(__INSTANCE, 4, 5, String.class, "cityCode");
    public static final Property<SearchEntity> searchId = new Property<>(__INSTANCE, 5, 6, String.class, "searchId");
    public static final Property<SearchEntity> title = new Property<>(__INSTANCE, 6, 7, String.class, "title");
    public static final Property<SearchEntity> url = new Property<>(__INSTANCE, 7, 8, String.class, "url");
    public static final Property<SearchEntity> imageURL = new Property<>(__INSTANCE, 8, 9, String.class, "imageURL");
    public static final Property<SearchEntity> desc = new Property<>(__INSTANCE, 9, 10, String.class, "desc");
    public static final Property<SearchEntity> price = new Property<>(__INSTANCE, 10, 11, String.class, "price");
    public static final Property<SearchEntity> needLogin = new Property<>(__INSTANCE, 11, 12, String.class, "needLogin");
    public static final Property<SearchEntity> backMode = new Property<>(__INSTANCE, 12, 13, String.class, "backMode");
    public static final Property<SearchEntity> isGroup = new Property<>(__INSTANCE, 13, 14, Boolean.TYPE, "isGroup");
    public static final Property<SearchEntity> sectionsTitle = new Property<>(__INSTANCE, 14, 15, String.class, "sectionsTitle");
    public static final Property<SearchEntity> sectionsIndex = new Property<>(__INSTANCE, 15, 16, Integer.TYPE, "sectionsIndex");
    public static final Property<SearchEntity> type = new Property<>(__INSTANCE, 16, 17, String.class, "type");
    public static final Property<SearchEntity> isFooter = new Property<>(__INSTANCE, 17, 18, Boolean.TYPE, "isFooter");
    public static final Property<SearchEntity> hisBg = new Property<>(__INSTANCE, 18, 19, Integer.TYPE, "hisBg");
    public static final Property<SearchEntity> goodsImage = new Property<>(__INSTANCE, 19, 20, String.class, "goodsImage");
    public static final Property<SearchEntity> goodsName = new Property<>(__INSTANCE, 20, 21, String.class, "goodsName");
    public static final Property<SearchEntity> goodsPrice = new Property<>(__INSTANCE, 21, 22, String.class, "goodsPrice");
    public static final Property<SearchEntity> goodsUrl = new Property<>(__INSTANCE, 22, 23, String.class, "goodsUrl");
    public static final Property<SearchEntity> activityName = new Property<>(__INSTANCE, 23, 24, String.class, "activityName");
    public static final Property<SearchEntity> NAME = new Property<>(__INSTANCE, 24, 25, String.class, "NAME");
    public static final Property<SearchEntity> IS_BEST = new Property<>(__INSTANCE, 25, 26, String.class, "IS_BEST");
    public static final Property<SearchEntity> IS_HOT = new Property<>(__INSTANCE, 26, 27, String.class, "IS_HOT");
    public static final Property<SearchEntity> WOZHIDAOURL = new Property<>(__INSTANCE, 27, 28, String.class, "WOZHIDAOURL");
    public static final Property<SearchEntity> floorPrice = new Property<>(__INSTANCE, 28, 29, String.class, "floorPrice");
    public static final Property<SearchEntity> marketPrice = new Property<>(__INSTANCE, 29, 30, String.class, "marketPrice");
    public static final Property<SearchEntity> subtitle = new Property<>(__INSTANCE, 30, 31, String.class, "subtitle");
    public static final Property<SearchEntity> best_img = new Property<>(__INSTANCE, 31, 32, String.class, "best_img");
    public static final Property<SearchEntity> hot_img = new Property<>(__INSTANCE, 32, 33, String.class, "hot_img");
    public static final Property<SearchEntity> templateTitle = new Property<>(__INSTANCE, 33, 34, String.class, "templateTitle");
    public static final Property<SearchEntity> searchKey = new Property<>(__INSTANCE, 34, 35, String.class, "searchKey");
    public static final Property<SearchEntity> describeInfo = new Property<>(__INSTANCE, 35, 36, String.class, "describeInfo");
    public static final Property<SearchEntity> templateName = new Property<>(__INSTANCE, 36, 37, String.class, "templateName");
    public static final Property<SearchEntity> dataId = new Property<>(__INSTANCE, 37, 38, String.class, "dataId");
    public static final Property<SearchEntity> gameType = new Property<>(__INSTANCE, 38, 39, String.class, "gameType");
    public static final Property<SearchEntity> clientVersion = new Property<>(__INSTANCE, 39, 40, String.class, "clientVersion");
    public static final Property<SearchEntity> huodong_time = new Property<>(__INSTANCE, 40, 43, String.class, "huodong_time");
    public static final Property<SearchEntity> huodong_id = new Property<>(__INSTANCE, 41, 44, String.class, "huodong_id");
    public static final Property<SearchEntity> huodong_hallId = new Property<>(__INSTANCE, 42, 46, String.class, "huodong_hallId");
    public static final Property<SearchEntity> actType = new Property<>(__INSTANCE, 43, 45, String.class, "actType");
    public static final Property<SearchEntity> browseNum = new Property<>(__INSTANCE, 44, 41, String.class, "browseNum");
    public static final Property<SearchEntity> author = new Property<>(__INSTANCE, 45, 42, String.class, "author");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "SearchEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 15;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "SearchEntity";
    }

    static {
        Property<SearchEntity> property = f18607id;
        __ALL_PROPERTIES = new Property[]{property, mobile, commandCode, proviceCode, cityCode, searchId, title, url, imageURL, desc, price, needLogin, backMode, isGroup, sectionsTitle, sectionsIndex, type, isFooter, hisBg, goodsImage, goodsName, goodsPrice, goodsUrl, activityName, NAME, IS_BEST, IS_HOT, WOZHIDAOURL, floorPrice, marketPrice, subtitle, best_img, hot_img, templateTitle, searchKey, describeInfo, templateName, dataId, gameType, clientVersion, huodong_time, huodong_id, huodong_hallId, actType, browseNum, author};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<SearchEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SearchEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SearchEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<SearchEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<SearchEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class SearchEntityIdGetter implements IdGetter<SearchEntity> {
        SearchEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(SearchEntity searchEntity) {
            return searchEntity.getId();
        }
    }
}
