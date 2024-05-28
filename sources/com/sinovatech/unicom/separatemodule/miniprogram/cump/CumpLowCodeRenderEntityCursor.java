package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpLowCodeRenderEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CumpLowCodeRenderEntityCursor extends Cursor<CumpLowCodeRenderEntity> {
    private static final CumpLowCodeRenderEntity_.CumpLowCodeRenderEntityIdGetter ID_GETTER = CumpLowCodeRenderEntity_.__ID_GETTER;
    private static final int __ID_releaseNotes = CumpLowCodeRenderEntity_.releaseNotes.f24389id;
    private static final int __ID_downloadUrl = CumpLowCodeRenderEntity_.downloadUrl.f24389id;
    private static final int __ID_checksum = CumpLowCodeRenderEntity_.checksum.f24389id;
    private static final int __ID_version = CumpLowCodeRenderEntity_.version.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CumpLowCodeRenderEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CumpLowCodeRenderEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CumpLowCodeRenderEntityCursor(transaction, j, boxStore);
        }
    }

    public CumpLowCodeRenderEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CumpLowCodeRenderEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CumpLowCodeRenderEntity cumpLowCodeRenderEntity) {
        return ID_GETTER.getId(cumpLowCodeRenderEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CumpLowCodeRenderEntity cumpLowCodeRenderEntity) {
        String releaseNotes = cumpLowCodeRenderEntity.getReleaseNotes();
        int i = releaseNotes != null ? __ID_releaseNotes : 0;
        String downloadUrl = cumpLowCodeRenderEntity.getDownloadUrl();
        int i2 = downloadUrl != null ? __ID_downloadUrl : 0;
        String checksum = cumpLowCodeRenderEntity.getChecksum();
        int i3 = checksum != null ? __ID_checksum : 0;
        String version = cumpLowCodeRenderEntity.getVersion();
        long collect400000 = collect400000(this.cursor, cumpLowCodeRenderEntity.getId(), 3, i, releaseNotes, i2, downloadUrl, i3, checksum, version != null ? __ID_version : 0, version);
        cumpLowCodeRenderEntity.setId(collect400000);
        return collect400000;
    }
}
