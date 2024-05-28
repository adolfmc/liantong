package com.sinovatech.unicom.separatemodule.miniprogram.log;

import com.sinovatech.unicom.separatemodule.miniprogram.log.CumpLogEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class CumpLogEntity_ implements EntityInfo<CumpLogEntity> {
    public static final Property<CumpLogEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "CumpLogEntity";
    public static final int __ENTITY_ID = 22;
    public static final String __ENTITY_NAME = "CumpLogEntity";
    public static final Property<CumpLogEntity> __ID_PROPERTY;
    public static final Class<CumpLogEntity> __ENTITY_CLASS = CumpLogEntity.class;
    public static final CursorFactory<CumpLogEntity> __CURSOR_FACTORY = new CumpLogEntityCursor.Factory();
    @Internal
    static final CumpLogEntityIdGetter __ID_GETTER = new CumpLogEntityIdGetter();
    public static final CumpLogEntity_ __INSTANCE = new CumpLogEntity_();

    /* renamed from: id */
    public static final Property<CumpLogEntity> f18585id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<CumpLogEntity> sceneNumber = new Property<>(__INSTANCE, 1, 2, String.class, "sceneNumber");
    public static final Property<CumpLogEntity> subSceneNumber = new Property<>(__INSTANCE, 2, 3, String.class, "subSceneNumber");
    public static final Property<CumpLogEntity> stepNumber = new Property<>(__INSTANCE, 3, 4, String.class, "stepNumber");
    public static final Property<CumpLogEntity> serialNumber = new Property<>(__INSTANCE, 4, 5, String.class, "serialNumber");
    public static final Property<CumpLogEntity> startTime = new Property<>(__INSTANCE, 5, 6, String.class, "startTime");
    public static final Property<CumpLogEntity> endTime = new Property<>(__INSTANCE, 6, 7, String.class, "endTime");
    public static final Property<CumpLogEntity> takeupTime = new Property<>(__INSTANCE, 7, 8, String.class, "takeupTime");
    public static final Property<CumpLogEntity> edopAppId = new Property<>(__INSTANCE, 8, 9, String.class, "edopAppId");
    public static final Property<CumpLogEntity> edopAppName = new Property<>(__INSTANCE, 9, 10, String.class, "edopAppName");
    public static final Property<CumpLogEntity> url = new Property<>(__INSTANCE, 10, 11, String.class, "url");
    public static final Property<CumpLogEntity> title = new Property<>(__INSTANCE, 11, 12, String.class, "title");
    public static final Property<CumpLogEntity> response = new Property<>(__INSTANCE, 12, 13, String.class, "response");
    public static final Property<CumpLogEntity> status = new Property<>(__INSTANCE, 13, 14, String.class, "status");
    public static final Property<CumpLogEntity> oldEdopAppVersion = new Property<>(__INSTANCE, 14, 15, String.class, "oldEdopAppVersion");
    public static final Property<CumpLogEntity> newEdopAppVersion = new Property<>(__INSTANCE, 15, 16, String.class, "newEdopAppVersion");
    public static final Property<CumpLogEntity> authFunctionCode = new Property<>(__INSTANCE, 16, 17, String.class, "authFunctionCode");
    public static final Property<CumpLogEntity> jsFunction = new Property<>(__INSTANCE, 17, 28, String.class, "jsFunction");
    public static final Property<CumpLogEntity> interceptStatus = new Property<>(__INSTANCE, 18, 18, String.class, "interceptStatus");
    public static final Property<CumpLogEntity> mobile = new Property<>(__INSTANCE, 19, 19, String.class, "mobile");
    public static final Property<CumpLogEntity> provinceCode = new Property<>(__INSTANCE, 20, 20, String.class, "provinceCode");
    public static final Property<CumpLogEntity> cityCode = new Property<>(__INSTANCE, 21, 21, String.class, "cityCode");
    public static final Property<CumpLogEntity> deviceId = new Property<>(__INSTANCE, 22, 22, String.class, "deviceId");
    public static final Property<CumpLogEntity> osType = new Property<>(__INSTANCE, 23, 23, String.class, "osType");
    public static final Property<CumpLogEntity> osVersion = new Property<>(__INSTANCE, 24, 24, String.class, "osVersion");
    public static final Property<CumpLogEntity> clientVersion = new Property<>(__INSTANCE, 25, 25, String.class, "clientVersion");
    public static final Property<CumpLogEntity> deviceBrand = new Property<>(__INSTANCE, 26, 26, String.class, "deviceBrand");
    public static final Property<CumpLogEntity> deviceModel = new Property<>(__INSTANCE, 27, 27, String.class, "deviceModel");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "CumpLogEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 22;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "CumpLogEntity";
    }

    static {
        Property<CumpLogEntity> property = f18585id;
        __ALL_PROPERTIES = new Property[]{property, sceneNumber, subSceneNumber, stepNumber, serialNumber, startTime, endTime, takeupTime, edopAppId, edopAppName, url, title, response, status, oldEdopAppVersion, newEdopAppVersion, authFunctionCode, jsFunction, interceptStatus, mobile, provinceCode, cityCode, deviceId, osType, osVersion, clientVersion, deviceBrand, deviceModel};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<CumpLogEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpLogEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<CumpLogEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<CumpLogEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<CumpLogEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class CumpLogEntityIdGetter implements IdGetter<CumpLogEntity> {
        CumpLogEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(CumpLogEntity cumpLogEntity) {
            return cumpLogEntity.getId();
        }
    }
}
