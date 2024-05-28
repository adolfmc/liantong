package com.huawei.hms.p224ui;

import android.content.Context;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* renamed from: com.huawei.hms.ui.AbstractPromptDialog */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractPromptDialog extends AbstractDialog {
    @Override // com.huawei.hms.p224ui.AbstractDialog
    protected String onGetNegativeButtonString(Context context) {
        return null;
    }

    @Override // com.huawei.hms.p224ui.AbstractDialog
    protected String onGetTitleString(Context context) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context);
        }
        return ResourceLoaderUtil.getString("hms_bindfaildlg_title");
    }
}
