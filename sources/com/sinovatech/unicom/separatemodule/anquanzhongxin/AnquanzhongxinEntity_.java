package com.sinovatech.unicom.separatemodule.anquanzhongxin;

import com.sinovatech.unicom.separatemodule.anquanzhongxin.AnquanzhongxinEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class AnquanzhongxinEntity_ implements EntityInfo<AnquanzhongxinEntity> {
    public static final Property<AnquanzhongxinEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "AnquanzhongxinEntity";
    public static final int __ENTITY_ID = 32;
    public static final String __ENTITY_NAME = "AnquanzhongxinEntity";
    public static final Property<AnquanzhongxinEntity> __ID_PROPERTY;
    public static final Class<AnquanzhongxinEntity> __ENTITY_CLASS = AnquanzhongxinEntity.class;
    public static final CursorFactory<AnquanzhongxinEntity> __CURSOR_FACTORY = new AnquanzhongxinEntityCursor.Factory();
    @Internal
    static final AnquanzhongxinEntityIdGetter __ID_GETTER = new AnquanzhongxinEntityIdGetter();
    public static final AnquanzhongxinEntity_ __INSTANCE = new AnquanzhongxinEntity_();

    /* renamed from: id */
    public static final Property<AnquanzhongxinEntity> f18462id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<AnquanzhongxinEntity> mobile = new Property<>(__INSTANCE, 1, 2, String.class, "mobile");
    public static final Property<AnquanzhongxinEntity> groupChooseNumber = new Property<>(__INSTANCE, 2, 3, Integer.TYPE, "groupChooseNumber");
    public static final Property<AnquanzhongxinEntity> chooseGroup1 = new Property<>(__INSTANCE, 3, 4, Boolean.TYPE, "chooseGroup1");
    public static final Property<AnquanzhongxinEntity> chooseGroup2 = new Property<>(__INSTANCE, 4, 5, Boolean.TYPE, "chooseGroup2");
    public static final Property<AnquanzhongxinEntity> chooseGroup3 = new Property<>(__INSTANCE, 5, 6, Boolean.TYPE, "chooseGroup3");
    public static final Property<AnquanzhongxinEntity> selected1 = new Property<>(__INSTANCE, 6, 7, Boolean.TYPE, "selected1");
    public static final Property<AnquanzhongxinEntity> selected2 = new Property<>(__INSTANCE, 7, 8, Boolean.TYPE, "selected2");
    public static final Property<AnquanzhongxinEntity> selected3 = new Property<>(__INSTANCE, 8, 9, Boolean.TYPE, "selected3");
    public static final Property<AnquanzhongxinEntity> selected4 = new Property<>(__INSTANCE, 9, 10, Boolean.TYPE, "selected4");
    public static final Property<AnquanzhongxinEntity> selected5 = new Property<>(__INSTANCE, 10, 11, Boolean.TYPE, "selected5");
    public static final Property<AnquanzhongxinEntity> sign = new Property<>(__INSTANCE, 11, 12, String.class, "sign");
    public static final Property<AnquanzhongxinEntity> signWay = new Property<>(__INSTANCE, 12, 13, Integer.TYPE, "signWay");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "AnquanzhongxinEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 32;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "AnquanzhongxinEntity";
    }

    static {
        Property<AnquanzhongxinEntity> property = f18462id;
        __ALL_PROPERTIES = new Property[]{property, mobile, groupChooseNumber, chooseGroup1, chooseGroup2, chooseGroup3, selected1, selected2, selected3, selected4, selected5, sign, signWay};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<AnquanzhongxinEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AnquanzhongxinEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<AnquanzhongxinEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<AnquanzhongxinEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<AnquanzhongxinEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class AnquanzhongxinEntityIdGetter implements IdGetter<AnquanzhongxinEntity> {
        AnquanzhongxinEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(AnquanzhongxinEntity anquanzhongxinEntity) {
            return anquanzhongxinEntity.getId();
        }
    }
}
