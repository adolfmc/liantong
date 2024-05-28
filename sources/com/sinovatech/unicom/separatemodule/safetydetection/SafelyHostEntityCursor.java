package com.sinovatech.unicom.separatemodule.safetydetection;

import com.sinovatech.unicom.separatemodule.safetydetection.SafelyHostEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SafelyHostEntityCursor extends Cursor<SafelyHostEntity> {
    private static final SafelyHostEntity_.SafelyHostEntityIdGetter ID_GETTER = SafelyHostEntity_.__ID_GETTER;
    private static final int __ID_whiteHosts = SafelyHostEntity_.whiteHosts.f24389id;
    private static final int __ID_whiteOpen = SafelyHostEntity_.whiteOpen.f24389id;
    private static final int __ID_whiteVersion = SafelyHostEntity_.whiteVersion.f24389id;
    private static final int __ID_blackHosts = SafelyHostEntity_.blackHosts.f24389id;
    private static final int __ID_blackOpen = SafelyHostEntity_.blackOpen.f24389id;
    private static final int __ID_blackVersion = SafelyHostEntity_.blackVersion.f24389id;
    private static final int __ID_blackHint = SafelyHostEntity_.blackHint.f24389id;
    private static final int __ID_blackHintLink = SafelyHostEntity_.blackHintLink.f24389id;
    private static final int __ID_blackCancelName = SafelyHostEntity_.blackCancelName.f24389id;
    private static final int __ID_blackConfirmName = SafelyHostEntity_.blackConfirmName.f24389id;
    private static final int __ID_grayDialogHint = SafelyHostEntity_.grayDialogHint.f24389id;
    private static final int __ID_grayHint = SafelyHostEntity_.grayHint.f24389id;
    private static final int __ID_grayHintLink = SafelyHostEntity_.grayHintLink.f24389id;
    private static final int __ID_grayOpen = SafelyHostEntity_.grayOpen.f24389id;
    private static final int __ID_grayCancelName = SafelyHostEntity_.grayCancelName.f24389id;
    private static final int __ID_grayConfimName = SafelyHostEntity_.grayConfimName.f24389id;
    private static final int __ID_ecsTokenHosts = SafelyHostEntity_.ecsTokenHosts.f24389id;
    private static final int __ID_ecsTokenOpen = SafelyHostEntity_.ecsTokenOpen.f24389id;
    private static final int __ID_ecsTokenVersion = SafelyHostEntity_.ecsTokenVersion.f24389id;
    private static final int __ID_homeWebBaoGuangStatus = SafelyHostEntity_.homeWebBaoGuangStatus.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<SafelyHostEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<SafelyHostEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new SafelyHostEntityCursor(transaction, j, boxStore);
        }
    }

    public SafelyHostEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, SafelyHostEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(SafelyHostEntity safelyHostEntity) {
        return ID_GETTER.getId(safelyHostEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(SafelyHostEntity safelyHostEntity) {
        String whiteHosts = safelyHostEntity.getWhiteHosts();
        int i = whiteHosts != null ? __ID_whiteHosts : 0;
        String whiteVersion = safelyHostEntity.getWhiteVersion();
        int i2 = whiteVersion != null ? __ID_whiteVersion : 0;
        String blackHosts = safelyHostEntity.getBlackHosts();
        int i3 = blackHosts != null ? __ID_blackHosts : 0;
        String blackVersion = safelyHostEntity.getBlackVersion();
        collect400000(this.cursor, 0L, 1, i, whiteHosts, i2, whiteVersion, i3, blackHosts, blackVersion != null ? __ID_blackVersion : 0, blackVersion);
        String blackHint = safelyHostEntity.getBlackHint();
        int i4 = blackHint != null ? __ID_blackHint : 0;
        String blackHintLink = safelyHostEntity.getBlackHintLink();
        int i5 = blackHintLink != null ? __ID_blackHintLink : 0;
        String blackCancelName = safelyHostEntity.getBlackCancelName();
        int i6 = blackCancelName != null ? __ID_blackCancelName : 0;
        String blackConfirmName = safelyHostEntity.getBlackConfirmName();
        collect400000(this.cursor, 0L, 0, i4, blackHint, i5, blackHintLink, i6, blackCancelName, blackConfirmName != null ? __ID_blackConfirmName : 0, blackConfirmName);
        String grayDialogHint = safelyHostEntity.getGrayDialogHint();
        int i7 = grayDialogHint != null ? __ID_grayDialogHint : 0;
        String grayHint = safelyHostEntity.getGrayHint();
        int i8 = grayHint != null ? __ID_grayHint : 0;
        String grayHintLink = safelyHostEntity.getGrayHintLink();
        int i9 = grayHintLink != null ? __ID_grayHintLink : 0;
        String grayCancelName = safelyHostEntity.getGrayCancelName();
        collect400000(this.cursor, 0L, 0, i7, grayDialogHint, i8, grayHint, i9, grayHintLink, grayCancelName != null ? __ID_grayCancelName : 0, grayCancelName);
        String grayConfimName = safelyHostEntity.getGrayConfimName();
        int i10 = grayConfimName != null ? __ID_grayConfimName : 0;
        String ecsTokenHosts = safelyHostEntity.getEcsTokenHosts();
        int i11 = ecsTokenHosts != null ? __ID_ecsTokenHosts : 0;
        String ecsTokenVersion = safelyHostEntity.getEcsTokenVersion();
        int i12 = ecsTokenVersion != null ? __ID_ecsTokenVersion : 0;
        String homeWebBaoGuangStatus = safelyHostEntity.getHomeWebBaoGuangStatus();
        collect400000(this.cursor, 0L, 0, i10, grayConfimName, i11, ecsTokenHosts, i12, ecsTokenVersion, homeWebBaoGuangStatus != null ? __ID_homeWebBaoGuangStatus : 0, homeWebBaoGuangStatus);
        long collect004000 = collect004000(this.cursor, safelyHostEntity.getId(), 2, __ID_whiteOpen, safelyHostEntity.isWhiteOpen() ? 1L : 0L, __ID_blackOpen, safelyHostEntity.isBlackOpen() ? 1L : 0L, __ID_grayOpen, safelyHostEntity.isGrayOpen() ? 1L : 0L, __ID_ecsTokenOpen, safelyHostEntity.isEcsTokenOpen() ? 1L : 0L);
        safelyHostEntity.setId(collect004000);
        return collect004000;
    }
}
