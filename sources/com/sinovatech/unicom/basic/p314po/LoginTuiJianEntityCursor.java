package com.sinovatech.unicom.basic.p314po;

import com.sinovatech.unicom.basic.p314po.LoginTuiJianEntity_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* renamed from: com.sinovatech.unicom.basic.po.LoginTuiJianEntityCursor */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class LoginTuiJianEntityCursor extends Cursor<LoginTuiJianEntity> {
    private static final LoginTuiJianEntity_.LoginTuiJianEntityIdGetter ID_GETTER = LoginTuiJianEntity_.__ID_GETTER;
    private static final int __ID_productRedirecturl = LoginTuiJianEntity_.productRedirecturl.f24389id;
    private static final int __ID_productName = LoginTuiJianEntity_.productName.f24389id;
    private static final int __ID_productUrl = LoginTuiJianEntity_.productUrl.f24389id;
    private static final int __ID_textColor = LoginTuiJianEntity_.textColor.f24389id;
    private static final int __ID_showTab = LoginTuiJianEntity_.showTab.f24389id;
    private static final int __ID_tag = LoginTuiJianEntity_.tag.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* renamed from: com.sinovatech.unicom.basic.po.LoginTuiJianEntityCursor$Factory */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<LoginTuiJianEntity> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<LoginTuiJianEntity> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new LoginTuiJianEntityCursor(transaction, j, boxStore);
        }
    }

    public LoginTuiJianEntityCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, LoginTuiJianEntity_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(LoginTuiJianEntity loginTuiJianEntity) {
        return ID_GETTER.getId(loginTuiJianEntity);
    }

    @Override // io.objectbox.Cursor
    public final long put(LoginTuiJianEntity loginTuiJianEntity) {
        String productRedirecturl = loginTuiJianEntity.getProductRedirecturl();
        int i = productRedirecturl != null ? __ID_productRedirecturl : 0;
        String productName = loginTuiJianEntity.getProductName();
        int i2 = productName != null ? __ID_productName : 0;
        String productUrl = loginTuiJianEntity.getProductUrl();
        int i3 = productUrl != null ? __ID_productUrl : 0;
        String showTab = loginTuiJianEntity.getShowTab();
        collect400000(this.cursor, 0L, 1, i, productRedirecturl, i2, productName, i3, productUrl, showTab != null ? __ID_showTab : 0, showTab);
        String tag = loginTuiJianEntity.getTag();
        long collect313311 = collect313311(this.cursor, loginTuiJianEntity.getId(), 2, tag != null ? __ID_tag : 0, tag, 0, null, 0, null, 0, null, __ID_textColor, loginTuiJianEntity.getTextColor(), 0, 0L, 0, 0L, 0, 0, 0, 0, 0, 0, 0, 0.0f, 0, 0.0d);
        loginTuiJianEntity.setId(collect313311);
        return collect313311;
    }
}
