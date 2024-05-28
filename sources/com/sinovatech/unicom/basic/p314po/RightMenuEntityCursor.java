package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.RightMenuEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.RightMenuEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class RightMenuEntityCursor extends Cursor<RightMenuEntity> {
    private static final RightMenuEntity_.RightMenuEntityIdGetter ID_GETTER = RightMenuEntity_.__ID_GETTER;
    private static final int __ID_title = RightMenuEntity_.title.f24389id;
    private static final int __ID_url = RightMenuEntity_.url.f24389id;
    private static final int __ID_desc_info = RightMenuEntity_.desc_info.f24389id;
    private static final int __ID_icon_url = RightMenuEntity_.icon_url.f24389id;
    private static final int __ID_unchecked_url = RightMenuEntity_.unchecked_url.f24389id;
    private static final int __ID_typeCode = RightMenuEntity_.typeCode.f24389id;
    private static final int __ID_cid = RightMenuEntity_.cid.f24389id;
    private static final int __ID_interfaceUrl = RightMenuEntity_.interfaceUrl.f24389id;
    private static final int __ID_isVideo = RightMenuEntity_.isVideo.f24389id;
    private static final int __ID_state = RightMenuEntity_.state.f24389id;
    private static final int __ID_isNeed = RightMenuEntity_.isNeed.f24389id;
    private static final int __ID_mobile = RightMenuEntity_.mobile.f24389id;
    private static final int __ID_classifyCode = RightMenuEntity_.classifyCode.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.RightMenuEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<RightMenuEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<RightMenuEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new RightMenuEntityCursor(transaction, j, boxStore);
        }
    }

    public RightMenuEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, RightMenuEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(RightMenuEntity rightMenuEntity) {
        return ID_GETTER.getId(rightMenuEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(RightMenuEntity rightMenuEntity) {
        String title = rightMenuEntity.getTitle();
        int i = title != null ? __ID_title : 0;
        String url = rightMenuEntity.getUrl();
        int i2 = url != null ? __ID_url : 0;
        String desc_info = rightMenuEntity.getDesc_info();
        int i3 = desc_info != null ? __ID_desc_info : 0;
        String icon_url = rightMenuEntity.getIcon_url();
        collect400000(this.cursor, 0L, 1, i, title, i2, url, i3, desc_info, icon_url != null ? __ID_icon_url : 0, icon_url);
        String unchecked_url = rightMenuEntity.getUnchecked_url();
        int i4 = unchecked_url != null ? __ID_unchecked_url : 0;
        String typeCode = rightMenuEntity.getTypeCode();
        int i5 = typeCode != null ? __ID_typeCode : 0;
        String cid = rightMenuEntity.getCid();
        int i6 = cid != null ? __ID_cid : 0;
        String interfaceUrl = rightMenuEntity.getInterfaceUrl();
        collect400000(this.cursor, 0L, 0, i4, unchecked_url, i5, typeCode, i6, cid, interfaceUrl != null ? __ID_interfaceUrl : 0, interfaceUrl);
        String isVideo = rightMenuEntity.getIsVideo();
        int i7 = isVideo != null ? __ID_isVideo : 0;
        String mobile = rightMenuEntity.getMobile();
        int i8 = mobile != null ? __ID_mobile : 0;
        String classifyCode = rightMenuEntity.getClassifyCode();
        long collect313311 = collect313311(this.cursor, rightMenuEntity.getId(), 2, i7, isVideo, i8, mobile, classifyCode != null ? __ID_classifyCode : 0, classifyCode, 0, null, __ID_state, rightMenuEntity.getState(), __ID_isNeed, rightMenuEntity.isNeed() ? 1L : 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        rightMenuEntity.setId(collect313311);
        return collect313311;
    }
}
