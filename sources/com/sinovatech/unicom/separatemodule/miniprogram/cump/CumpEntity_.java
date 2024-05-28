package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CumpEntity_ implements EntityInfo<CumpEntity> {
    public static final Property<CumpEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CumpEntity";
    public static final int __ENTITY_ID = 21;
    public static final String __ENTITY_NAME = "CumpEntity";
    public static final Property<CumpEntity> __ID_PROPERTY;
    public static final Class<CumpEntity> __ENTITY_CLASS = CumpEntity.class;
    public static final CursorFactory<CumpEntity> __CURSOR_FACTORY = new CumpEntityCursor.Factory();
    @Internal
    static final CumpEntityIdGetter __ID_GETTER = new CumpEntityIdGetter();
    public static final CumpEntity_ __INSTANCE = new CumpEntity_();

    /* renamed from: id */
    public static final Property<CumpEntity> f18562id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<CumpEntity> appId = new Property<>(__INSTANCE, 1, 2, String.class, "appId");
    public static final Property<CumpEntity> appName = new Property<>(__INSTANCE, 2, 3, String.class, "appName");
    public static final Property<CumpEntity> appImg = new Property<>(__INSTANCE, 3, 4, String.class, "appImg");
    public static final Property<CumpEntity> appHighImg = new Property<>(__INSTANCE, 4, 5, String.class, "appHighImg");
    public static final Property<CumpEntity> appSecret = new Property<>(__INSTANCE, 5, 6, String.class, "appSecret");
    public static final Property<CumpEntity> appToken = new Property<>(__INSTANCE, 6, 7, String.class, "appToken");
    public static final Property<CumpEntity> appDesc = new Property<>(__INSTANCE, 7, 8, String.class, "appDesc");
    public static final Property<CumpEntity> officialVersion = new Property<>(__INSTANCE, 8, 9, String.class, "officialVersion");
    public static final Property<CumpEntity> officialPackageUrl = new Property<>(__INSTANCE, 9, 10, String.class, "officialPackageUrl");
    public static final Property<CumpEntity> officialMd5 = new Property<>(__INSTANCE, 10, 11, String.class, "officialMd5");
    public static final Property<CumpEntity> timestamp = new Property<>(__INSTANCE, 11, 12, String.class, "timestamp");
    public static final Property<CumpEntity> createTime = new Property<>(__INSTANCE, 12, 13, String.class, "createTime");
    public static final Property<CumpEntity> latestUpdateTime = new Property<>(__INSTANCE, 13, 14, String.class, "latestUpdateTime");
    public static final Property<CumpEntity> realmUrlList = new Property<>(__INSTANCE, 14, 17, String.class, "realmUrlList");
    public static final Property<CumpEntity> plugCodeList = new Property<>(__INSTANCE, 15, 18, String.class, "plugCodeList");
    public static final Property<CumpEntity> serverPublishType = new Property<>(__INSTANCE, 16, 19, String.class, "serverPublishType");
    public static final Property<CumpEntity> desktopIcon = new Property<>(__INSTANCE, 17, 20, String.class, "desktopIcon");
    public static final Property<CumpEntity> privacyUrl = new Property<>(__INSTANCE, 18, 21, String.class, "privacyUrl");
    public static final Property<CumpEntity> lastestCollPopupShowTime = new Property<>(__INSTANCE, 19, 22, Long.TYPE, "lastestCollPopupShowTime");
    public static final Property<CumpEntity> lastestBottomPopupShowTime = new Property<>(__INSTANCE, 20, 23, Long.TYPE, "lastestBottomPopupShowTime");
    public static final Property<CumpEntity> publishMethod = new Property<>(__INSTANCE, 21, 24, String.class, "publishMethod");
    public static final Property<CumpEntity> homePageKey = new Property<>(__INSTANCE, 22, 25, String.class, "homePageKey");
    public static final Property<CumpEntity> isInnerMiniP = new Property<>(__INSTANCE, 23, 26, Boolean.TYPE, "isInnerMiniP");
    public static final Property<CumpEntity> oldVersion = new Property<>(__INSTANCE, 24, 27, String.class, "oldVersion");
    public static final Property<CumpEntity> trialVersionNum = new Property<>(__INSTANCE, 25, 28, String.class, "trialVersionNum");
    public static final Property<CumpEntity> officialVersionNum = new Property<>(__INSTANCE, 26, 29, String.class, "officialVersionNum");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CumpEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 21;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CumpEntity";
    }

    static {
        Property<CumpEntity> property = f18562id;
        __ALL_PROPERTIES = new Property[]{property, appId, appName, appImg, appHighImg, appSecret, appToken, appDesc, officialVersion, officialPackageUrl, officialMd5, timestamp, createTime, latestUpdateTime, realmUrlList, plugCodeList, serverPublishType, desktopIcon, privacyUrl, lastestCollPopupShowTime, lastestBottomPopupShowTime, publishMethod, homePageKey, isInnerMiniP, oldVersion, trialVersionNum, officialVersionNum};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CumpEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CumpEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CumpEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CumpEntityIdGetter implements IdGetter<CumpEntity> {
        CumpEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CumpEntity cumpEntity) {
            return cumpEntity.getId();
        }
    }
}
