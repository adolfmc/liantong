package com.sinovatech.unicom.separatemodule.miniprogram.h5auth;

import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5RegisterEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class H5RegisterEntityCursor extends Cursor<H5RegisterEntity> {
    private static final H5RegisterEntity_.H5RegisterEntityIdGetter ID_GETTER = H5RegisterEntity_.__ID_GETTER;
    private static final int __ID_appId = H5RegisterEntity_.appId.f24389id;
    private static final int __ID_appName = H5RegisterEntity_.appName.f24389id;
    private static final int __ID_h5Urls = H5RegisterEntity_.h5Urls.f24389id;
    private static final int __ID_plugCodes = H5RegisterEntity_.plugCodes.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<H5RegisterEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<H5RegisterEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new H5RegisterEntityCursor(transaction, j, boxStore);
        }
    }

    public H5RegisterEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, H5RegisterEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(H5RegisterEntity h5RegisterEntity) {
        return ID_GETTER.getId(h5RegisterEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(H5RegisterEntity h5RegisterEntity) {
        String appId = h5RegisterEntity.getAppId();
        int i = appId != null ? __ID_appId : 0;
        String appName = h5RegisterEntity.getAppName();
        int i2 = appName != null ? __ID_appName : 0;
        String h5Urls = h5RegisterEntity.getH5Urls();
        int i3 = h5Urls != null ? __ID_h5Urls : 0;
        String plugCodes = h5RegisterEntity.getPlugCodes();
        long collect400000 = collect400000(this.cursor, h5RegisterEntity.getId(), 3, i, appId, i2, appName, i3, h5Urls, plugCodes != null ? __ID_plugCodes : 0, plugCodes);
        h5RegisterEntity.setId(collect400000);
        return collect400000;
    }
}
