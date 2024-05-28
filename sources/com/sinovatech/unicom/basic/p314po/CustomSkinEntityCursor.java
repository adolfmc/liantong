package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.CustomSkinEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.CustomSkinEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class CustomSkinEntityCursor extends Cursor<CustomSkinEntity> {
    private static final CustomSkinEntity_.CustomSkinEntityIdGetter ID_GETTER = CustomSkinEntity_.__ID_GETTER;
    private static final int __ID_id = CustomSkinEntity_.f18382id.f24389id;
    private static final int __ID_skinid = CustomSkinEntity_.skinid.f24389id;
    private static final int __ID_type = CustomSkinEntity_.type.f24389id;
    private static final int __ID_status = CustomSkinEntity_.status.f24389id;
    private static final int __ID_url = CustomSkinEntity_.url.f24389id;
    private static final int __ID_thumbUrl = CustomSkinEntity_.thumbUrl.f24389id;
    private static final int __ID_mineUrl = CustomSkinEntity_.mineUrl.f24389id;
    private static final int __ID_homeSmall = CustomSkinEntity_.homeSmall.f24389id;
    private static final int __ID_imgCode = CustomSkinEntity_.imgCode.f24389id;
    private static final int __ID_index = CustomSkinEntity_.index.f24389id;
    private static final int __ID_textColor = CustomSkinEntity_.textColor.f24389id;
    private static final int __ID_settingImageUrl = CustomSkinEntity_.settingImageUrl.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.CustomSkinEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<CustomSkinEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<CustomSkinEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new CustomSkinEntityCursor(transaction, j, boxStore);
        }
    }

    public CustomSkinEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, CustomSkinEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(CustomSkinEntity customSkinEntity) {
        return ID_GETTER.getId(customSkinEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(CustomSkinEntity customSkinEntity) {
        String skinid = customSkinEntity.getSkinid();
        int i = skinid != null ? __ID_skinid : 0;
        String type = customSkinEntity.getType();
        int i2 = type != null ? __ID_type : 0;
        String status = customSkinEntity.getStatus();
        int i3 = status != null ? __ID_status : 0;
        String url = customSkinEntity.getUrl();
        collect400000(this.cursor, 0L, 1, i, skinid, i2, type, i3, status, url != null ? __ID_url : 0, url);
        String thumbUrl = customSkinEntity.getThumbUrl();
        int i4 = thumbUrl != null ? __ID_thumbUrl : 0;
        String mineUrl = customSkinEntity.getMineUrl();
        int i5 = mineUrl != null ? __ID_mineUrl : 0;
        String homeSmall = customSkinEntity.getHomeSmall();
        int i6 = homeSmall != null ? __ID_homeSmall : 0;
        String imgCode = customSkinEntity.getImgCode();
        collect400000(this.cursor, 0L, 0, i4, thumbUrl, i5, mineUrl, i6, homeSmall, imgCode != null ? __ID_imgCode : 0, imgCode);
        String settingImageUrl = customSkinEntity.getSettingImageUrl();
        long collect313311 = collect313311(this.cursor, customSkinEntity.getPositionId(), 2, settingImageUrl != null ? __ID_settingImageUrl : 0, settingImageUrl, 0, null, 0, null, 0, null, __ID_id, customSkinEntity.getId(), __ID_index, customSkinEntity.getIndex(), __ID_textColor, customSkinEntity.getTextColor(), 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        customSkinEntity.setPositionId(collect313311);
        return collect313311;
    }
}
