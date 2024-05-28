package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeBwJingZhunEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBwJingZhunEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class HomeBwJingZhunEntity_ implements EntityInfo<HomeBwJingZhunEntity> {
    public static final Property<HomeBwJingZhunEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "HomeBwJingZhunEntity";
    public static final int __ENTITY_ID = 25;
    public static final String __ENTITY_NAME = "HomeBwJingZhunEntity";
    public static final Property<HomeBwJingZhunEntity> __ID_PROPERTY;
    public static final Class<HomeBwJingZhunEntity> __ENTITY_CLASS = HomeBwJingZhunEntity.class;
    public static final CursorFactory<HomeBwJingZhunEntity> __CURSOR_FACTORY = new HomeBwJingZhunEntityCursor.Factory();
    @Internal
    static final HomeBwJingZhunEntityIdGetter __ID_GETTER = new HomeBwJingZhunEntityIdGetter();
    public static final HomeBwJingZhunEntity_ __INSTANCE = new HomeBwJingZhunEntity_();

    /* renamed from: id */
    public static final Property<HomeBwJingZhunEntity> f18414id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<HomeBwJingZhunEntity> productActualType = new Property<>(__INSTANCE, 1, 2, String.class, "productActualType");
    public static final Property<HomeBwJingZhunEntity> source = new Property<>(__INSTANCE, 2, 3, String.class, "source");
    public static final Property<HomeBwJingZhunEntity> defaultRecommend = new Property<>(__INSTANCE, 3, 4, String.class, "defaultRecommend");
    public static final Property<HomeBwJingZhunEntity> viceTitle = new Property<>(__INSTANCE, 4, 5, String.class, "viceTitle");
    public static final Property<HomeBwJingZhunEntity> beanId = new Property<>(__INSTANCE, 5, 6, String.class, "beanId");
    public static final Property<HomeBwJingZhunEntity> ruleId = new Property<>(__INSTANCE, 6, 7, String.class, "ruleId");
    public static final Property<HomeBwJingZhunEntity> goodsType = new Property<>(__INSTANCE, 7, 8, String.class, "goodsType");
    public static final Property<HomeBwJingZhunEntity> goodsUrl = new Property<>(__INSTANCE, 8, 9, String.class, "goodsUrl");
    public static final Property<HomeBwJingZhunEntity> goodsId = new Property<>(__INSTANCE, 9, 10, String.class, "goodsId");
    public static final Property<HomeBwJingZhunEntity> title = new Property<>(__INSTANCE, 10, 11, String.class, "title");
    public static final Property<HomeBwJingZhunEntity> actType = new Property<>(__INSTANCE, 11, 12, String.class, "actType");
    public static final Property<HomeBwJingZhunEntity> isOtherNet = new Property<>(__INSTANCE, 12, 13, String.class, "isOtherNet");
    public static final Property<HomeBwJingZhunEntity> needLogin = new Property<>(__INSTANCE, 13, 14, String.class, "needLogin");
    public static final Property<HomeBwJingZhunEntity> updateUser = new Property<>(__INSTANCE, 14, 15, String.class, "updateUser");
    public static final Property<HomeBwJingZhunEntity> updateTime = new Property<>(__INSTANCE, 15, 16, String.class, "updateTime");
    public static final Property<HomeBwJingZhunEntity> goodsStatus = new Property<>(__INSTANCE, 16, 17, String.class, "goodsStatus");
    public static final Property<HomeBwJingZhunEntity> createTime = new Property<>(__INSTANCE, 17, 18, String.class, "createTime");
    public static final Property<HomeBwJingZhunEntity> createUser = new Property<>(__INSTANCE, 18, 19, String.class, "createUser");
    public static final Property<HomeBwJingZhunEntity> businessType = new Property<>(__INSTANCE, 19, 20, String.class, "businessType");
    public static final Property<HomeBwJingZhunEntity> imgSrc = new Property<>(__INSTANCE, 20, 21, String.class, "imgSrc");
    public static final Property<HomeBwJingZhunEntity> actId = new Property<>(__INSTANCE, 21, 22, String.class, "actId");
    public static final Property<HomeBwJingZhunEntity> mobile = new Property<>(__INSTANCE, 22, 23, String.class, "mobile");
    public static final Property<HomeBwJingZhunEntity> defImg = new Property<>(__INSTANCE, 23, 24, Integer.TYPE, "defImg");
    public static final Property<HomeBwJingZhunEntity> isCustomData = new Property<>(__INSTANCE, 24, 25, Boolean.TYPE, "isCustomData");
    public static final Property<HomeBwJingZhunEntity> wxId = new Property<>(__INSTANCE, 25, 27, String.class, "wxId");
    public static final Property<HomeBwJingZhunEntity> position = new Property<>(__INSTANCE, 26, 28, String.class, "position");
    public static final Property<HomeBwJingZhunEntity> isMarketLineFlag = new Property<>(__INSTANCE, 27, 29, String.class, "isMarketLineFlag");
    public static final Property<HomeBwJingZhunEntity> marketCapsuleTitle = new Property<>(__INSTANCE, 28, 30, String.class, "marketCapsuleTitle");
    public static final Property<HomeBwJingZhunEntity> marketCapsuleImgSrc = new Property<>(__INSTANCE, 29, 31, String.class, "marketCapsuleImgSrc");
    public static final Property<HomeBwJingZhunEntity> imgType = new Property<>(__INSTANCE, 30, 32, String.class, "imgType");
    public static final Property<HomeBwJingZhunEntity> fontNumberMark = new Property<>(__INSTANCE, 31, 33, String.class, "fontNumberMark");
    public static final Property<HomeBwJingZhunEntity> fontNumber = new Property<>(__INSTANCE, 32, 34, String.class, "fontNumber");
    public static final Property<HomeBwJingZhunEntity> fontNumberUnit = new Property<>(__INSTANCE, 33, 35, String.class, "fontNumberUnit");
    public static final Property<HomeBwJingZhunEntity> basemap = new Property<>(__INSTANCE, 34, 36, String.class, "basemap");
    public static final Property<HomeBwJingZhunEntity> basemapColor = new Property<>(__INSTANCE, 35, 37, String.class, "basemapColor");
    public static final Property<HomeBwJingZhunEntity> speed = new Property<>(__INSTANCE, 36, 38, String.class, "speed");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "HomeBwJingZhunEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 25;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "HomeBwJingZhunEntity";
    }

    static {
        Property<HomeBwJingZhunEntity> property = f18414id;
        __ALL_PROPERTIES = new Property[]{property, productActualType, source, defaultRecommend, viceTitle, beanId, ruleId, goodsType, goodsUrl, goodsId, title, actType, isOtherNet, needLogin, updateUser, updateTime, goodsStatus, createTime, createUser, businessType, imgSrc, actId, mobile, defImg, isCustomData, wxId, position, isMarketLineFlag, marketCapsuleTitle, marketCapsuleImgSrc, imgType, fontNumberMark, fontNumber, fontNumberUnit, basemap, basemapColor, speed};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<HomeBwJingZhunEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeBwJingZhunEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeBwJingZhunEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<HomeBwJingZhunEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<HomeBwJingZhunEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeBwJingZhunEntity_$HomeBwJingZhunEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class HomeBwJingZhunEntityIdGetter implements IdGetter<HomeBwJingZhunEntity> {
        HomeBwJingZhunEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(HomeBwJingZhunEntity homeBwJingZhunEntity) {
            return homeBwJingZhunEntity.getId();
        }
    }
}
