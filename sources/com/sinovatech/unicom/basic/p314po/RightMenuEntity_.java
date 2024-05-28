package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.RightMenuEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.po.RightMenuEntity_ */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class RightMenuEntity_ implements EntityInfo<RightMenuEntity> {
    public static final Property<RightMenuEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "RightMenuEntity";
    public static final int __ENTITY_ID = 2;
    public static final String __ENTITY_NAME = "RightMenuEntity";
    public static final Property<RightMenuEntity> __ID_PROPERTY;
    public static final Class<RightMenuEntity> __ENTITY_CLASS = RightMenuEntity.class;
    public static final CursorFactory<RightMenuEntity> __CURSOR_FACTORY = new RightMenuEntityCursor.Factory();
    @Internal
    static final RightMenuEntityIdGetter __ID_GETTER = new RightMenuEntityIdGetter();
    public static final RightMenuEntity_ __INSTANCE = new RightMenuEntity_();

    /* renamed from: id */
    public static final Property<RightMenuEntity> f18396id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<RightMenuEntity> title = new Property<>(__INSTANCE, 1, 2, String.class, "title");
    public static final Property<RightMenuEntity> url = new Property<>(__INSTANCE, 2, 3, String.class, "url");
    public static final Property<RightMenuEntity> desc_info = new Property<>(__INSTANCE, 3, 4, String.class, "desc_info");
    public static final Property<RightMenuEntity> icon_url = new Property<>(__INSTANCE, 4, 5, String.class, "icon_url");
    public static final Property<RightMenuEntity> unchecked_url = new Property<>(__INSTANCE, 5, 12, String.class, "unchecked_url");
    public static final Property<RightMenuEntity> typeCode = new Property<>(__INSTANCE, 6, 6, String.class, "typeCode");
    public static final Property<RightMenuEntity> cid = new Property<>(__INSTANCE, 7, 7, String.class, "cid");
    public static final Property<RightMenuEntity> interfaceUrl = new Property<>(__INSTANCE, 8, 8, String.class, "interfaceUrl");
    public static final Property<RightMenuEntity> isVideo = new Property<>(__INSTANCE, 9, 9, String.class, "isVideo");
    public static final Property<RightMenuEntity> state = new Property<>(__INSTANCE, 10, 10, Integer.TYPE, "state");
    public static final Property<RightMenuEntity> isNeed = new Property<>(__INSTANCE, 11, 11, Boolean.TYPE, "isNeed");
    public static final Property<RightMenuEntity> mobile = new Property<>(__INSTANCE, 12, 13, String.class, "mobile");
    public static final Property<RightMenuEntity> classifyCode = new Property<>(__INSTANCE, 13, 14, String.class, "classifyCode");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "RightMenuEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 2;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "RightMenuEntity";
    }

    static {
        Property<RightMenuEntity> property = f18396id;
        __ALL_PROPERTIES = new Property[]{property, title, url, desc_info, icon_url, unchecked_url, typeCode, cid, interfaceUrl, isVideo, state, isNeed, mobile, classifyCode};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<RightMenuEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RightMenuEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<RightMenuEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<RightMenuEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<RightMenuEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.RightMenuEntity_$RightMenuEntityIdGetter */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class RightMenuEntityIdGetter implements IdGetter<RightMenuEntity> {
        RightMenuEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(RightMenuEntity rightMenuEntity) {
            return rightMenuEntity.getId();
        }
    }
}
