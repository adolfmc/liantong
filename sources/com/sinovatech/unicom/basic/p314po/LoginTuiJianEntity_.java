package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.LoginTuiJianEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.LoginTuiJianEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LoginTuiJianEntity_ implements EntityInfo<LoginTuiJianEntity> {
    public static final Property<LoginTuiJianEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "LoginTuiJianEntity";
    public static final int __ENTITY_ID = 18;
    public static final String __ENTITY_NAME = "LoginTuiJianEntity";
    public static final Property<LoginTuiJianEntity> __ID_PROPERTY;
    public static final Class<LoginTuiJianEntity> __ENTITY_CLASS = LoginTuiJianEntity.class;
    public static final CursorFactory<LoginTuiJianEntity> __CURSOR_FACTORY = new LoginTuiJianEntityCursor.Factory();
    @Internal
    static final LoginTuiJianEntityIdGetter __ID_GETTER = new LoginTuiJianEntityIdGetter();
    public static final LoginTuiJianEntity_ __INSTANCE = new LoginTuiJianEntity_();

    /* renamed from: id */
    public static final Property<LoginTuiJianEntity> f18389id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<LoginTuiJianEntity> productRedirecturl = new Property<>(__INSTANCE, 1, 2, String.class, "productRedirecturl");
    public static final Property<LoginTuiJianEntity> productName = new Property<>(__INSTANCE, 2, 3, String.class, "productName");
    public static final Property<LoginTuiJianEntity> productUrl = new Property<>(__INSTANCE, 3, 4, String.class, "productUrl");
    public static final Property<LoginTuiJianEntity> textColor = new Property<>(__INSTANCE, 4, 5, Integer.TYPE, "textColor");
    public static final Property<LoginTuiJianEntity> showTab = new Property<>(__INSTANCE, 5, 6, String.class, "showTab");
    public static final Property<LoginTuiJianEntity> tag = new Property<>(__INSTANCE, 6, 7, String.class, "tag");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "LoginTuiJianEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 18;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "LoginTuiJianEntity";
    }

    static {
        Property<LoginTuiJianEntity> property = f18389id;
        __ALL_PROPERTIES = new Property[]{property, productRedirecturl, productName, productUrl, textColor, showTab, tag};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<LoginTuiJianEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LoginTuiJianEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LoginTuiJianEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<LoginTuiJianEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<LoginTuiJianEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.LoginTuiJianEntity_$LoginTuiJianEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class LoginTuiJianEntityIdGetter implements IdGetter<LoginTuiJianEntity> {
        LoginTuiJianEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(LoginTuiJianEntity loginTuiJianEntity) {
            return loginTuiJianEntity.getId();
        }
    }
}
