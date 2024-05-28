package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.AdvBoxCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.AdvBox_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class AdvBox_ implements EntityInfo<AdvBox> {
    public static final Property<AdvBox>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "AdvBox";
    public static final int __ENTITY_ID = 8;
    public static final String __ENTITY_NAME = "AdvBox";
    public static final Property<AdvBox> __ID_PROPERTY;
    public static final Class<AdvBox> __ENTITY_CLASS = AdvBox.class;
    public static final CursorFactory<AdvBox> __CURSOR_FACTORY = new AdvBoxCursor.Factory();
    @Internal
    static final AdvBoxIdGetter __ID_GETTER = new AdvBoxIdGetter();
    public static final AdvBox_ __INSTANCE = new AdvBox_();

    /* renamed from: id */
    public static final Property<AdvBox> f18372id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<AdvBox> mobile = new Property<>(__INSTANCE, 1, 2, String.class, "mobile");
    public static final Property<AdvBox> proCode = new Property<>(__INSTANCE, 2, 3, String.class, "proCode");
    public static final Property<AdvBox> cityCode = new Property<>(__INSTANCE, 3, 4, String.class, "cityCode");
    public static final Property<AdvBox> content = new Property<>(__INSTANCE, 4, 5, String.class, "content");
    public static final Property<AdvBox> ywCode = new Property<>(__INSTANCE, 5, 6, String.class, "ywCode");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "AdvBox";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 8;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "AdvBox";
    }

    static {
        Property<AdvBox> property = f18372id;
        __ALL_PROPERTIES = new Property[]{property, mobile, proCode, cityCode, content, ywCode};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<AdvBox> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AdvBox>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AdvBox> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<AdvBox> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<AdvBox> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.AdvBox_$AdvBoxIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class AdvBoxIdGetter implements IdGetter<AdvBox> {
        AdvBoxIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(AdvBox advBox) {
            return advBox.getId();
        }
    }
}
