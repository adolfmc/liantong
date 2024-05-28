package com.sinovatech.unicom.separatemodule.gamedistribution;

import com.sinovatech.unicom.separatemodule.gamedistribution.GameEntityCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class GameEntity_ implements EntityInfo<GameEntity> {
    public static final Property<GameEntity>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "GameEntity";
    public static final int __ENTITY_ID = 19;
    public static final String __ENTITY_NAME = "GameEntity";
    public static final Property<GameEntity> __ID_PROPERTY;
    public static final Class<GameEntity> __ENTITY_CLASS = GameEntity.class;
    public static final CursorFactory<GameEntity> __CURSOR_FACTORY = new GameEntityCursor.Factory();
    @Internal
    static final GameEntityIdGetter __ID_GETTER = new GameEntityIdGetter();
    public static final GameEntity_ __INSTANCE = new GameEntity_();

    /* renamed from: id */
    public static final Property<GameEntity> f18536id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<GameEntity> gameId = new Property<>(__INSTANCE, 1, 2, String.class, "gameId");
    public static final Property<GameEntity> gameName = new Property<>(__INSTANCE, 2, 3, String.class, "gameName");
    public static final Property<GameEntity> gameUrl = new Property<>(__INSTANCE, 3, 4, String.class, "gameUrl");
    public static final Property<GameEntity> gameIcon = new Property<>(__INSTANCE, 4, 5, String.class, "gameIcon");
    public static final Property<GameEntity> gamePackageName = new Property<>(__INSTANCE, 5, 6, String.class, "gamePackageName");
    public static final Property<GameEntity> gameDetailsUrl = new Property<>(__INSTANCE, 6, 7, String.class, "gameDetailsUrl");
    public static final Property<GameEntity> gameCentreDetailsUrl = new Property<>(__INSTANCE, 7, 8, String.class, "gameCentreDetailsUrl");
    public static final Property<GameEntity> gameSubtitle = new Property<>(__INSTANCE, 8, 9, String.class, "gameSubtitle");
    public static final Property<GameEntity> gameSize = new Property<>(__INSTANCE, 9, 10, String.class, "gameSize");
    public static final Property<GameEntity> gameDownloadStatus = new Property<>(__INSTANCE, 10, 11, String.class, "gameDownloadStatus");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "GameEntity";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 19;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "GameEntity";
    }

    static {
        Property<GameEntity> property = f18536id;
        __ALL_PROPERTIES = new Property[]{property, gameId, gameName, gameUrl, gameIcon, gamePackageName, gameDetailsUrl, gameCentreDetailsUrl, gameSubtitle, gameSize, gameDownloadStatus};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<GameEntity> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<GameEntity>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<GameEntity> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<GameEntity> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<GameEntity> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class GameEntityIdGetter implements IdGetter<GameEntity> {
        GameEntityIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(GameEntity gameEntity) {
            return gameEntity.getId();
        }
    }
}
