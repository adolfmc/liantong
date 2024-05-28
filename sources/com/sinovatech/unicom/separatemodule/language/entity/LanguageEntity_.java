package com.sinovatech.unicom.separatemodule.language.entity;

import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LanguageEntity_ implements EntityInfo<LanguageEntity> {
    public static final Property<LanguageEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "LanguageEntity";
    public static final int __ENTITY_ID = 29;
    public static final String __ENTITY_NAME = "LanguageEntity";
    public static final Property<LanguageEntity> __ID_PROPERTY;
    public static final Class<LanguageEntity> __ENTITY_CLASS = LanguageEntity.class;
    public static final CursorFactory<LanguageEntity> __CURSOR_FACTORY = new LanguageEntityCursor.Factory();
    @Internal
    static final LanguageEntityIdGetter __ID_GETTER = new LanguageEntityIdGetter();
    public static final LanguageEntity_ __INSTANCE = new LanguageEntity_();

    /* renamed from: id */
    public static final Property<LanguageEntity> f18544id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<LanguageEntity> languageName = new Property<>(__INSTANCE, 1, 2, String.class, "languageName");
    public static final Property<LanguageEntity> languageCode = new Property<>(__INSTANCE, 2, 3, String.class, "languageCode");
    public static final Property<LanguageEntity> rightToleft = new Property<>(__INSTANCE, 3, 4, String.class, "rightToleft");
    public static final Property<LanguageEntity> isSelect = new Property<>(__INSTANCE, 4, 5, Boolean.TYPE, "isSelect");
    public static final Property<LanguageEntity> url = new Property<>(__INSTANCE, 5, 6, String.class, "url");
    public static final Property<LanguageEntity> ywshow = new Property<>(__INSTANCE, 6, 7, String.class, "ywshow");
    public static final Property<LanguageEntity> languageDesc = new Property<>(__INSTANCE, 7, 8, String.class, "languageDesc");
    public static final Property<LanguageEntity> reminderPop = new Property<>(__INSTANCE, 8, 9, String.class, "reminderPop");
    public static final Property<LanguageEntity> switchLanguagePop = new Property<>(__INSTANCE, 9, 10, String.class, "switchLanguagePop");
    public static final Property<LanguageEntity> cancelBtnPop = new Property<>(__INSTANCE, 10, 11, String.class, "cancelBtnPop");
    public static final Property<LanguageEntity> confirmBtnPop = new Property<>(__INSTANCE, 11, 12, String.class, "confirmBtnPop");
    public static final Property<LanguageEntity> showDirectionFlag = new Property<>(__INSTANCE, 12, 13, String.class, "showDirectionFlag");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "LanguageEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 29;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "LanguageEntity";
    }

    static {
        Property<LanguageEntity> property = f18544id;
        __ALL_PROPERTIES = new Property[]{property, languageName, languageCode, rightToleft, isSelect, url, ywshow, languageDesc, reminderPop, switchLanguagePop, cancelBtnPop, confirmBtnPop, showDirectionFlag};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<LanguageEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LanguageEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<LanguageEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<LanguageEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<LanguageEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class LanguageEntityIdGetter implements IdGetter<LanguageEntity> {
        LanguageEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(LanguageEntity languageEntity) {
            return languageEntity.getId();
        }
    }
}
