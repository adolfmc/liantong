package com.sinovatech.unicom.basic.p315ui.home.entity;

import com.sinovatech.unicom.basic.p315ui.home.entity.HomeMergeFuChuangEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeMergeFuChuangEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class HomeMergeFuChuangEntity_ implements EntityInfo<HomeMergeFuChuangEntity> {
    public static final Property<HomeMergeFuChuangEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "HomeMergeFuChuangEntity";
    public static final int __ENTITY_ID = 35;
    public static final String __ENTITY_NAME = "HomeMergeFuChuangEntity";
    public static final Property<HomeMergeFuChuangEntity> __ID_PROPERTY;
    public static final Class<HomeMergeFuChuangEntity> __ENTITY_CLASS = HomeMergeFuChuangEntity.class;
    public static final CursorFactory<HomeMergeFuChuangEntity> __CURSOR_FACTORY = new HomeMergeFuChuangEntityCursor.Factory();
    @Internal
    static final HomeMergeFuChuangEntityIdGetter __ID_GETTER = new HomeMergeFuChuangEntityIdGetter();
    public static final HomeMergeFuChuangEntity_ __INSTANCE = new HomeMergeFuChuangEntity_();

    /* renamed from: id */
    public static final Property<HomeMergeFuChuangEntity> f18418id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<HomeMergeFuChuangEntity> titleColor = new Property<>(__INSTANCE, 1, 2, Integer.TYPE, "titleColor");
    public static final Property<HomeMergeFuChuangEntity> advertiseTitle = new Property<>(__INSTANCE, 2, 3, String.class, "advertiseTitle");
    public static final Property<HomeMergeFuChuangEntity> advertiseTargetURL = new Property<>(__INSTANCE, 3, 4, String.class, "advertiseTargetURL");
    public static final Property<HomeMergeFuChuangEntity> advertiseImageURL = new Property<>(__INSTANCE, 4, 5, String.class, "advertiseImageURL");
    public static final Property<HomeMergeFuChuangEntity> advertiseTargetType = new Property<>(__INSTANCE, 5, 6, String.class, "advertiseTargetType");
    public static final Property<HomeMergeFuChuangEntity> idx = new Property<>(__INSTANCE, 6, 7, String.class, "idx");
    public static final Property<HomeMergeFuChuangEntity> advertiseIndex = new Property<>(__INSTANCE, 7, 8, String.class, "advertiseIndex");
    public static final Property<HomeMergeFuChuangEntity> isNeedLogin = new Property<>(__INSTANCE, 8, 9, Boolean.TYPE, "isNeedLogin");
    public static final Property<HomeMergeFuChuangEntity> advertiseBackMode = new Property<>(__INSTANCE, 9, 10, String.class, "advertiseBackMode");
    public static final Property<HomeMergeFuChuangEntity> advJson = new Property<>(__INSTANCE, 10, 11, String.class, "advJson");
    public static final Property<HomeMergeFuChuangEntity> viceTitle = new Property<>(__INSTANCE, 11, 12, String.class, "viceTitle");
    public static final Property<HomeMergeFuChuangEntity> handleNumber = new Property<>(__INSTANCE, 12, 13, String.class, "handleNumber");
    public static final Property<HomeMergeFuChuangEntity> rightImgSrc = new Property<>(__INSTANCE, 13, 14, String.class, "rightImgSrc");
    public static final Property<HomeMergeFuChuangEntity> imageSrcVII = new Property<>(__INSTANCE, 14, 15, String.class, "imageSrcVII");
    public static final Property<HomeMergeFuChuangEntity> imageSrcVIIChecked = new Property<>(__INSTANCE, 15, 16, String.class, "imageSrcVIIChecked");
    public static final Property<HomeMergeFuChuangEntity> ywCode = new Property<>(__INSTANCE, 16, 17, String.class, "ywCode");
    public static final Property<HomeMergeFuChuangEntity> cityCode = new Property<>(__INSTANCE, 17, 18, String.class, "cityCode");
    public static final Property<HomeMergeFuChuangEntity> provinceCode = new Property<>(__INSTANCE, 18, 19, String.class, "provinceCode");
    public static final Property<HomeMergeFuChuangEntity> goodsId = new Property<>(__INSTANCE, 19, 20, String.class, "goodsId");
    public static final Property<HomeMergeFuChuangEntity> actType = new Property<>(__INSTANCE, 20, 21, String.class, "actType");
    public static final Property<HomeMergeFuChuangEntity> actId = new Property<>(__INSTANCE, 21, 22, String.class, "actId");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "HomeMergeFuChuangEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 35;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "HomeMergeFuChuangEntity";
    }

    static {
        Property<HomeMergeFuChuangEntity> property = f18418id;
        __ALL_PROPERTIES = new Property[]{property, titleColor, advertiseTitle, advertiseTargetURL, advertiseImageURL, advertiseTargetType, idx, advertiseIndex, isNeedLogin, advertiseBackMode, advJson, viceTitle, handleNumber, rightImgSrc, imageSrcVII, imageSrcVIIChecked, ywCode, cityCode, provinceCode, goodsId, actType, actId};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<HomeMergeFuChuangEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeMergeFuChuangEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<HomeMergeFuChuangEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<HomeMergeFuChuangEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<HomeMergeFuChuangEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.ui.home.entity.HomeMergeFuChuangEntity_$HomeMergeFuChuangEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class HomeMergeFuChuangEntityIdGetter implements IdGetter<HomeMergeFuChuangEntity> {
        HomeMergeFuChuangEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(HomeMergeFuChuangEntity homeMergeFuChuangEntity) {
            return homeMergeFuChuangEntity.getId();
        }
    }
}
