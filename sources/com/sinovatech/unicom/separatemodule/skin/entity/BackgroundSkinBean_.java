package com.sinovatech.unicom.separatemodule.skin.entity;

import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBeanCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class BackgroundSkinBean_ implements EntityInfo<BackgroundSkinBean> {
    public static final Property<BackgroundSkinBean>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "BackgroundSkinBean";
    public static final int __ENTITY_ID = 33;
    public static final String __ENTITY_NAME = "BackgroundSkinBean";
    public static final Property<BackgroundSkinBean> __ID_PROPERTY;
    public static final Class<BackgroundSkinBean> __ENTITY_CLASS = BackgroundSkinBean.class;
    public static final CursorFactory<BackgroundSkinBean> __CURSOR_FACTORY = new BackgroundSkinBeanCursor.Factory();
    @Internal
    static final BackgroundSkinBeanIdGetter __ID_GETTER = new BackgroundSkinBeanIdGetter();
    public static final BackgroundSkinBean_ __INSTANCE = new BackgroundSkinBean_();

    /* renamed from: id */
    public static final Property<BackgroundSkinBean> f18612id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<BackgroundSkinBean> productName = new Property<>(__INSTANCE, 1, 2, String.class, "productName");
    public static final Property<BackgroundSkinBean> productImgUrl = new Property<>(__INSTANCE, 2, 3, String.class, "productImgUrl");
    public static final Property<BackgroundSkinBean> productSubtitle = new Property<>(__INSTANCE, 3, 4, String.class, "productSubtitle");
    public static final Property<BackgroundSkinBean> productDesc = new Property<>(__INSTANCE, 4, 5, String.class, "productDesc");
    public static final Property<BackgroundSkinBean> productId = new Property<>(__INSTANCE, 5, 6, String.class, "productId");
    public static final Property<BackgroundSkinBean> productLinkUrl = new Property<>(__INSTANCE, 6, 7, String.class, "productLinkUrl");
    public static final Property<BackgroundSkinBean> bottomIcon = new Property<>(__INSTANCE, 7, 8, String.class, "bottomIcon");
    public static final Property<BackgroundSkinBean> titleColor = new Property<>(__INSTANCE, 8, 9, String.class, "titleColor");
    public static final Property<BackgroundSkinBean> isCustom = new Property<>(__INSTANCE, 9, 10, String.class, "isCustom");
    public static final Property<BackgroundSkinBean> isDefault = new Property<>(__INSTANCE, 10, 11, String.class, "isDefault");
    public static final Property<BackgroundSkinBean> showType = new Property<>(__INSTANCE, 11, 12, String.class, "showType");
    public static final Property<BackgroundSkinBean> groupName = new Property<>(__INSTANCE, 12, 13, String.class, "groupName");
    public static final Property<BackgroundSkinBean> mobile = new Property<>(__INSTANCE, 13, 14, String.class, "mobile");
    public static final Property<BackgroundSkinBean> isSelect = new Property<>(__INSTANCE, 14, 15, String.class, "isSelect");
    public static final Property<BackgroundSkinBean> textPictureColor = new Property<>(__INSTANCE, 15, 16, String.class, "textPictureColor");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "BackgroundSkinBean";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 33;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "BackgroundSkinBean";
    }

    static {
        Property<BackgroundSkinBean> property = f18612id;
        __ALL_PROPERTIES = new Property[]{property, productName, productImgUrl, productSubtitle, productDesc, productId, productLinkUrl, bottomIcon, titleColor, isCustom, isDefault, showType, groupName, mobile, isSelect, textPictureColor};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<BackgroundSkinBean> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<BackgroundSkinBean>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<BackgroundSkinBean> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<BackgroundSkinBean> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<BackgroundSkinBean> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class BackgroundSkinBeanIdGetter implements IdGetter<BackgroundSkinBean> {
        BackgroundSkinBeanIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(BackgroundSkinBean backgroundSkinBean) {
            return backgroundSkinBean.getId();
        }
    }
}
