package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.CustomSkinChoseEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.CustomSkinChoseEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CustomSkinChoseEntityCursor extends Cursor<CustomSkinChoseEntity> {
    private static final CustomSkinChoseEntity_.CustomSkinChoseEntityIdGetter ID_GETTER = CustomSkinChoseEntity_.__ID_GETTER;
    private static final int __ID_id = CustomSkinChoseEntity_.f18380id.f24389id;
    private static final int __ID_skinid = CustomSkinChoseEntity_.skinid.f24389id;
    private static final int __ID_type = CustomSkinChoseEntity_.type.f24389id;
    private static final int __ID_status = CustomSkinChoseEntity_.status.f24389id;
    private static final int __ID_url = CustomSkinChoseEntity_.url.f24389id;
    private static final int __ID_thumbUrl = CustomSkinChoseEntity_.thumbUrl.f24389id;
    private static final int __ID_mineUrl = CustomSkinChoseEntity_.mineUrl.f24389id;
    private static final int __ID_homeSmall = CustomSkinChoseEntity_.homeSmall.f24389id;
    private static final int __ID_imgCode = CustomSkinChoseEntity_.imgCode.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.CustomSkinChoseEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CustomSkinChoseEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CustomSkinChoseEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CustomSkinChoseEntityCursor(transaction, j, boxStore);
        }
    }

    public CustomSkinChoseEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CustomSkinChoseEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CustomSkinChoseEntity customSkinChoseEntity) {
        return ID_GETTER.getId(customSkinChoseEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CustomSkinChoseEntity customSkinChoseEntity) {
        String skinid = customSkinChoseEntity.getSkinid();
        int i = skinid != null ? __ID_skinid : 0;
        String type = customSkinChoseEntity.getType();
        int i2 = type != null ? __ID_type : 0;
        String status = customSkinChoseEntity.getStatus();
        int i3 = status != null ? __ID_status : 0;
        String url = customSkinChoseEntity.getUrl();
        collect400000(this.cursor, 0L, 1, i, skinid, i2, type, i3, status, url != null ? __ID_url : 0, url);
        String thumbUrl = customSkinChoseEntity.getThumbUrl();
        int i4 = thumbUrl != null ? __ID_thumbUrl : 0;
        String mineUrl = customSkinChoseEntity.getMineUrl();
        int i5 = mineUrl != null ? __ID_mineUrl : 0;
        String homeSmall = customSkinChoseEntity.getHomeSmall();
        int i6 = homeSmall != null ? __ID_homeSmall : 0;
        String imgCode = customSkinChoseEntity.getImgCode();
        collect400000(this.cursor, 0L, 0, i4, thumbUrl, i5, mineUrl, i6, homeSmall, imgCode != null ? __ID_imgCode : 0, imgCode);
        long collect004000 = collect004000(this.cursor, customSkinChoseEntity.getPositionId(), 2, __ID_id, customSkinChoseEntity.getId(), 0, 0L, 0, 0L, 0, 0L);
        customSkinChoseEntity.setPositionId(collect004000);
        return collect004000;
    }
}
