package com.sinovatech.unicom.separatemodule.gamedistribution;

import com.sinovatech.unicom.separatemodule.gamedistribution.GameEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class GameEntityCursor extends Cursor<GameEntity> {
    private static final GameEntity_.GameEntityIdGetter ID_GETTER = GameEntity_.__ID_GETTER;
    private static final int __ID_gameId = GameEntity_.gameId.f24389id;
    private static final int __ID_gameName = GameEntity_.gameName.f24389id;
    private static final int __ID_gameUrl = GameEntity_.gameUrl.f24389id;
    private static final int __ID_gameIcon = GameEntity_.gameIcon.f24389id;
    private static final int __ID_gamePackageName = GameEntity_.gamePackageName.f24389id;
    private static final int __ID_gameDetailsUrl = GameEntity_.gameDetailsUrl.f24389id;
    private static final int __ID_gameCentreDetailsUrl = GameEntity_.gameCentreDetailsUrl.f24389id;
    private static final int __ID_gameSubtitle = GameEntity_.gameSubtitle.f24389id;
    private static final int __ID_gameSize = GameEntity_.gameSize.f24389id;
    private static final int __ID_gameDownloadStatus = GameEntity_.gameDownloadStatus.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<GameEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<GameEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new GameEntityCursor(transaction, j, boxStore);
        }
    }

    public GameEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, GameEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(GameEntity gameEntity) {
        return ID_GETTER.getId(gameEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(GameEntity gameEntity) {
        String gameId = gameEntity.getGameId();
        int i = gameId != null ? __ID_gameId : 0;
        String gameName = gameEntity.getGameName();
        int i2 = gameName != null ? __ID_gameName : 0;
        String gameUrl = gameEntity.getGameUrl();
        int i3 = gameUrl != null ? __ID_gameUrl : 0;
        String gameIcon = gameEntity.getGameIcon();
        collect400000(this.cursor, 0L, 1, i, gameId, i2, gameName, i3, gameUrl, gameIcon != null ? __ID_gameIcon : 0, gameIcon);
        String gamePackageName = gameEntity.getGamePackageName();
        int i4 = gamePackageName != null ? __ID_gamePackageName : 0;
        String gameDetailsUrl = gameEntity.getGameDetailsUrl();
        int i5 = gameDetailsUrl != null ? __ID_gameDetailsUrl : 0;
        String gameCentreDetailsUrl = gameEntity.getGameCentreDetailsUrl();
        int i6 = gameCentreDetailsUrl != null ? __ID_gameCentreDetailsUrl : 0;
        String gameSubtitle = gameEntity.getGameSubtitle();
        collect400000(this.cursor, 0L, 0, i4, gamePackageName, i5, gameDetailsUrl, i6, gameCentreDetailsUrl, gameSubtitle != null ? __ID_gameSubtitle : 0, gameSubtitle);
        String gameSize = gameEntity.getGameSize();
        int i7 = gameSize != null ? __ID_gameSize : 0;
        String gameDownloadStatus = gameEntity.getGameDownloadStatus();
        long collect313311 = collect313311(this.cursor, gameEntity.getId(), 2, i7, gameSize, gameDownloadStatus != null ? __ID_gameDownloadStatus : 0, gameDownloadStatus, 0, null, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        gameEntity.setId(collect313311);
        return collect313311;
    }
}
