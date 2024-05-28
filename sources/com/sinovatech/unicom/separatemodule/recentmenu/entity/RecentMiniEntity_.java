package com.sinovatech.unicom.separatemodule.recentmenu.entity;

import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class RecentMiniEntity_ implements EntityInfo<RecentMiniEntity> {
    public static final Property<RecentMiniEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "RecentMiniEntity";
    public static final int __ENTITY_ID = 45;
    public static final String __ENTITY_NAME = "RecentMiniEntity";
    public static final Property<RecentMiniEntity> __ID_PROPERTY;
    public static final Class<RecentMiniEntity> __ENTITY_CLASS = RecentMiniEntity.class;
    public static final CursorFactory<RecentMiniEntity> __CURSOR_FACTORY = new RecentMiniEntityCursor.Factory();
    @Internal
    static final RecentMiniEntityIdGetter __ID_GETTER = new RecentMiniEntityIdGetter();
    public static final RecentMiniEntity_ __INSTANCE = new RecentMiniEntity_();

    /* renamed from: id */
    public static final Property<RecentMiniEntity> f18602id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<RecentMiniEntity> appId = new Property<>(__INSTANCE, 1, 2, String.class, "appId");
    public static final Property<RecentMiniEntity> appImg = new Property<>(__INSTANCE, 2, 3, String.class, "appImg");
    public static final Property<RecentMiniEntity> appName = new Property<>(__INSTANCE, 3, 4, String.class, "appName");
    public static final Property<RecentMiniEntity> appletUrl = new Property<>(__INSTANCE, 4, 5, String.class, "appletUrl");
    public static final Property<RecentMiniEntity> isH5 = new Property<>(__INSTANCE, 5, 6, Boolean.TYPE, "isH5");
    public static final Property<RecentMiniEntity> mobile = new Property<>(__INSTANCE, 6, 7, String.class, "mobile");
    public static final Property<RecentMiniEntity> isSelect = new Property<>(__INSTANCE, 7, 8, Boolean.TYPE, "isSelect");
    public static final Property<RecentMiniEntity> timeTemp = new Property<>(__INSTANCE, 8, 9, Long.TYPE, "timeTemp");
    public static final Property<RecentMiniEntity> type = new Property<>(__INSTANCE, 9, 10, Integer.TYPE, "type");
    public static final Property<RecentMiniEntity> dateString = new Property<>(__INSTANCE, 10, 11, String.class, "dateString");
    public static final Property<RecentMiniEntity> productId = new Property<>(__INSTANCE, 11, 12, String.class, "productId");
    public static final Property<RecentMiniEntity> createTime = new Property<>(__INSTANCE, 12, 13, String.class, "createTime");
    public static final Property<RecentMiniEntity> state = new Property<>(__INSTANCE, 13, 14, String.class, "state");
    public static final Property<RecentMiniEntity> appDesc = new Property<>(__INSTANCE, 14, 15, String.class, "appDesc");
    public static final Property<RecentMiniEntity> isBianJi = new Property<>(__INSTANCE, 15, 16, Boolean.TYPE, "isBianJi");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "RecentMiniEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 45;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "RecentMiniEntity";
    }

    static {
        Property<RecentMiniEntity> property = f18602id;
        __ALL_PROPERTIES = new Property[]{property, appId, appImg, appName, appletUrl, isH5, mobile, isSelect, timeTemp, type, dateString, productId, createTime, state, appDesc, isBianJi};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<RecentMiniEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RecentMiniEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RecentMiniEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<RecentMiniEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<RecentMiniEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class RecentMiniEntityIdGetter implements IdGetter<RecentMiniEntity> {
        RecentMiniEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(RecentMiniEntity recentMiniEntity) {
            return recentMiniEntity.getId();
        }
    }
}
