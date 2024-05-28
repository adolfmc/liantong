package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanAuthCodeEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class AnquanAuthCodeEntity_ implements EntityInfo<AnquanAuthCodeEntity> {
    public static final Property<AnquanAuthCodeEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "AnquanAuthCodeEntity";
    public static final int __ENTITY_ID = 31;
    public static final String __ENTITY_NAME = "AnquanAuthCodeEntity";
    public static final Property<AnquanAuthCodeEntity> __ID_PROPERTY;
    public static final Class<AnquanAuthCodeEntity> __ENTITY_CLASS = AnquanAuthCodeEntity.class;
    public static final CursorFactory<AnquanAuthCodeEntity> __CURSOR_FACTORY = new AnquanAuthCodeEntityCursor.Factory();
    @Internal
    static final AnquanAuthCodeEntityIdGetter __ID_GETTER = new AnquanAuthCodeEntityIdGetter();
    public static final AnquanAuthCodeEntity_ __INSTANCE = new AnquanAuthCodeEntity_();

    /* renamed from: id */
    public static final Property<AnquanAuthCodeEntity> f18458id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<AnquanAuthCodeEntity> isUserChecked = new Property<>(__INSTANCE, 1, 2, Boolean.TYPE, "isUserChecked");
    public static final Property<AnquanAuthCodeEntity> authCanUsed = new Property<>(__INSTANCE, 2, 3, Boolean.TYPE, "authCanUsed");
    public static final Property<AnquanAuthCodeEntity> gesture = new Property<>(__INSTANCE, 3, 4, Integer.TYPE, "gesture");
    public static final Property<AnquanAuthCodeEntity> touchId = new Property<>(__INSTANCE, 4, 5, Integer.TYPE, "touchId");
    public static final Property<AnquanAuthCodeEntity> faceId = new Property<>(__INSTANCE, 5, 6, Integer.TYPE, "faceId");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "AnquanAuthCodeEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 31;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "AnquanAuthCodeEntity";
    }

    static {
        Property<AnquanAuthCodeEntity> property = f18458id;
        __ALL_PROPERTIES = new Property[]{property, isUserChecked, authCanUsed, gesture, touchId, faceId};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<AnquanAuthCodeEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AnquanAuthCodeEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AnquanAuthCodeEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<AnquanAuthCodeEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<AnquanAuthCodeEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class AnquanAuthCodeEntityIdGetter implements IdGetter<AnquanAuthCodeEntity> {
        AnquanAuthCodeEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(AnquanAuthCodeEntity anquanAuthCodeEntity) {
            return anquanAuthCodeEntity.getId();
        }
    }
}
