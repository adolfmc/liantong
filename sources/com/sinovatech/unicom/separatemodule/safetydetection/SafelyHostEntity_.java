package com.sinovatech.unicom.separatemodule.safetydetection;

import com.sinovatech.unicom.separatemodule.safetydetection.SafelyHostEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class SafelyHostEntity_ implements EntityInfo<SafelyHostEntity> {
    public static final Property<SafelyHostEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "SafelyHostEntity";
    public static final int __ENTITY_ID = 42;
    public static final String __ENTITY_NAME = "SafelyHostEntity";
    public static final Property<SafelyHostEntity> __ID_PROPERTY;
    public static final Class<SafelyHostEntity> __ENTITY_CLASS = SafelyHostEntity.class;
    public static final CursorFactory<SafelyHostEntity> __CURSOR_FACTORY = new SafelyHostEntityCursor.Factory();
    @Internal
    static final SafelyHostEntityIdGetter __ID_GETTER = new SafelyHostEntityIdGetter();
    public static final SafelyHostEntity_ __INSTANCE = new SafelyHostEntity_();

    /* renamed from: id */
    public static final Property<SafelyHostEntity> f18605id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<SafelyHostEntity> whiteHosts = new Property<>(__INSTANCE, 1, 2, String.class, "whiteHosts");
    public static final Property<SafelyHostEntity> whiteOpen = new Property<>(__INSTANCE, 2, 3, Boolean.TYPE, "whiteOpen");
    public static final Property<SafelyHostEntity> whiteVersion = new Property<>(__INSTANCE, 3, 4, String.class, "whiteVersion");
    public static final Property<SafelyHostEntity> blackHosts = new Property<>(__INSTANCE, 4, 5, String.class, "blackHosts");
    public static final Property<SafelyHostEntity> blackOpen = new Property<>(__INSTANCE, 5, 6, Boolean.TYPE, "blackOpen");
    public static final Property<SafelyHostEntity> blackVersion = new Property<>(__INSTANCE, 6, 7, String.class, "blackVersion");
    public static final Property<SafelyHostEntity> blackHint = new Property<>(__INSTANCE, 7, 8, String.class, "blackHint");
    public static final Property<SafelyHostEntity> blackHintLink = new Property<>(__INSTANCE, 8, 9, String.class, "blackHintLink");
    public static final Property<SafelyHostEntity> blackCancelName = new Property<>(__INSTANCE, 9, 10, String.class, "blackCancelName");
    public static final Property<SafelyHostEntity> blackConfirmName = new Property<>(__INSTANCE, 10, 11, String.class, "blackConfirmName");
    public static final Property<SafelyHostEntity> grayDialogHint = new Property<>(__INSTANCE, 11, 12, String.class, "grayDialogHint");
    public static final Property<SafelyHostEntity> grayHint = new Property<>(__INSTANCE, 12, 13, String.class, "grayHint");
    public static final Property<SafelyHostEntity> grayHintLink = new Property<>(__INSTANCE, 13, 14, String.class, "grayHintLink");
    public static final Property<SafelyHostEntity> grayOpen = new Property<>(__INSTANCE, 14, 15, Boolean.TYPE, "grayOpen");
    public static final Property<SafelyHostEntity> grayCancelName = new Property<>(__INSTANCE, 15, 16, String.class, "grayCancelName");
    public static final Property<SafelyHostEntity> grayConfimName = new Property<>(__INSTANCE, 16, 17, String.class, "grayConfimName");
    public static final Property<SafelyHostEntity> ecsTokenHosts = new Property<>(__INSTANCE, 17, 18, String.class, "ecsTokenHosts");
    public static final Property<SafelyHostEntity> ecsTokenOpen = new Property<>(__INSTANCE, 18, 19, Boolean.TYPE, "ecsTokenOpen");
    public static final Property<SafelyHostEntity> ecsTokenVersion = new Property<>(__INSTANCE, 19, 20, String.class, "ecsTokenVersion");
    public static final Property<SafelyHostEntity> homeWebBaoGuangStatus = new Property<>(__INSTANCE, 20, 21, String.class, "homeWebBaoGuangStatus");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "SafelyHostEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 42;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "SafelyHostEntity";
    }

    static {
        Property<SafelyHostEntity> property = f18605id;
        __ALL_PROPERTIES = new Property[]{property, whiteHosts, whiteOpen, whiteVersion, blackHosts, blackOpen, blackVersion, blackHint, blackHintLink, blackCancelName, blackConfirmName, grayDialogHint, grayHint, grayHintLink, grayOpen, grayCancelName, grayConfimName, ecsTokenHosts, ecsTokenOpen, ecsTokenVersion, homeWebBaoGuangStatus};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<SafelyHostEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SafelyHostEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<SafelyHostEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<SafelyHostEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<SafelyHostEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class SafelyHostEntityIdGetter implements IdGetter<SafelyHostEntity> {
        SafelyHostEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(SafelyHostEntity safelyHostEntity) {
            return safelyHostEntity.getId();
        }
    }
}
