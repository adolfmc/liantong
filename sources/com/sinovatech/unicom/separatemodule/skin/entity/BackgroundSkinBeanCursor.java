package com.sinovatech.unicom.separatemodule.skin.entity;

import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class BackgroundSkinBeanCursor extends Cursor<BackgroundSkinBean> {
    private static final BackgroundSkinBean_.BackgroundSkinBeanIdGetter ID_GETTER = BackgroundSkinBean_.__ID_GETTER;
    private static final int __ID_productName = BackgroundSkinBean_.productName.f24389id;
    private static final int __ID_productImgUrl = BackgroundSkinBean_.productImgUrl.f24389id;
    private static final int __ID_productSubtitle = BackgroundSkinBean_.productSubtitle.f24389id;
    private static final int __ID_productDesc = BackgroundSkinBean_.productDesc.f24389id;
    private static final int __ID_productId = BackgroundSkinBean_.productId.f24389id;
    private static final int __ID_productLinkUrl = BackgroundSkinBean_.productLinkUrl.f24389id;
    private static final int __ID_bottomIcon = BackgroundSkinBean_.bottomIcon.f24389id;
    private static final int __ID_titleColor = BackgroundSkinBean_.titleColor.f24389id;
    private static final int __ID_isCustom = BackgroundSkinBean_.isCustom.f24389id;
    private static final int __ID_isDefault = BackgroundSkinBean_.isDefault.f24389id;
    private static final int __ID_showType = BackgroundSkinBean_.showType.f24389id;
    private static final int __ID_groupName = BackgroundSkinBean_.groupName.f24389id;
    private static final int __ID_mobile = BackgroundSkinBean_.mobile.f24389id;
    private static final int __ID_isSelect = BackgroundSkinBean_.isSelect.f24389id;
    private static final int __ID_textPictureColor = BackgroundSkinBean_.textPictureColor.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<BackgroundSkinBean> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<BackgroundSkinBean> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new BackgroundSkinBeanCursor(transaction, j, boxStore);
        }
    }

    public BackgroundSkinBeanCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, BackgroundSkinBean_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(BackgroundSkinBean backgroundSkinBean) {
        return ID_GETTER.getId(backgroundSkinBean);
    }

    @Override // io.objectbox.Cursor
    public final long put(BackgroundSkinBean backgroundSkinBean) {
        String productName = backgroundSkinBean.getProductName();
        int i = productName != null ? __ID_productName : 0;
        String productImgUrl = backgroundSkinBean.getProductImgUrl();
        int i2 = productImgUrl != null ? __ID_productImgUrl : 0;
        String productSubtitle = backgroundSkinBean.getProductSubtitle();
        int i3 = productSubtitle != null ? __ID_productSubtitle : 0;
        String productDesc = backgroundSkinBean.getProductDesc();
        collect400000(this.cursor, 0L, 1, i, productName, i2, productImgUrl, i3, productSubtitle, productDesc != null ? __ID_productDesc : 0, productDesc);
        String productId = backgroundSkinBean.getProductId();
        int i4 = productId != null ? __ID_productId : 0;
        String productLinkUrl = backgroundSkinBean.getProductLinkUrl();
        int i5 = productLinkUrl != null ? __ID_productLinkUrl : 0;
        String bottomIcon = backgroundSkinBean.getBottomIcon();
        int i6 = bottomIcon != null ? __ID_bottomIcon : 0;
        String titleColor = backgroundSkinBean.getTitleColor();
        collect400000(this.cursor, 0L, 0, i4, productId, i5, productLinkUrl, i6, bottomIcon, titleColor != null ? __ID_titleColor : 0, titleColor);
        String isCustom = backgroundSkinBean.getIsCustom();
        int i7 = isCustom != null ? __ID_isCustom : 0;
        String isDefault = backgroundSkinBean.getIsDefault();
        int i8 = isDefault != null ? __ID_isDefault : 0;
        String showType = backgroundSkinBean.getShowType();
        int i9 = showType != null ? __ID_showType : 0;
        String groupName = backgroundSkinBean.getGroupName();
        collect400000(this.cursor, 0L, 0, i7, isCustom, i8, isDefault, i9, showType, groupName != null ? __ID_groupName : 0, groupName);
        String mobile = backgroundSkinBean.getMobile();
        int i10 = mobile != null ? __ID_mobile : 0;
        String isSelect = backgroundSkinBean.getIsSelect();
        int i11 = isSelect != null ? __ID_isSelect : 0;
        String textPictureColor = backgroundSkinBean.getTextPictureColor();
        long collect313311 = collect313311(this.cursor, backgroundSkinBean.getId(), 2, i10, mobile, i11, isSelect, textPictureColor != null ? __ID_textPictureColor : 0, textPictureColor, 0, null, 0, 0L, 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        backgroundSkinBean.setId(collect313311);
        return collect313311;
    }
}
