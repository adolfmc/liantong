package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.MenuBoxCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.MenuBox_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class MenuBox_ implements EntityInfo<MenuBox> {
    public static final Property<MenuBox>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "MenuBox";
    public static final int __ENTITY_ID = 11;
    public static final String __ENTITY_NAME = "MenuBox";
    public static final Property<MenuBox> __ID_PROPERTY;
    public static final Class<MenuBox> __ENTITY_CLASS = MenuBox.class;
    public static final CursorFactory<MenuBox> __CURSOR_FACTORY = new MenuBoxCursor.Factory();
    @Internal
    static final MenuBoxIdGetter __ID_GETTER = new MenuBoxIdGetter();
    public static final MenuBox_ __INSTANCE = new MenuBox_();

    /* renamed from: id */
    public static final Property<MenuBox> f18391id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<MenuBox> mobile = new Property<>(__INSTANCE, 1, 2, String.class, "mobile");
    public static final Property<MenuBox> proCode = new Property<>(__INSTANCE, 2, 3, String.class, "proCode");
    public static final Property<MenuBox> content = new Property<>(__INSTANCE, 3, 4, String.class, "content");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "MenuBox";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 11;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "MenuBox";
    }

    static {
        Property<MenuBox> property = f18391id;
        __ALL_PROPERTIES = new Property[]{property, mobile, proCode, content};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<MenuBox> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<MenuBox>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<MenuBox> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<MenuBox> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<MenuBox> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.MenuBox_$MenuBoxIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class MenuBoxIdGetter implements IdGetter<MenuBox> {
        MenuBoxIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(MenuBox menuBox) {
            return menuBox.getId();
        }
    }
}
