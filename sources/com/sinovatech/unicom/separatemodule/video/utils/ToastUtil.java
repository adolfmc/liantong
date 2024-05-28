package com.sinovatech.unicom.separatemodule.video.utils;

import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ToastUtil {
    public static void showToast(String str) {
        UIUtils.toast(str);
    }

    public static void showToast(int i) {
        showToast(App.getInstance().getString(i));
    }
}
