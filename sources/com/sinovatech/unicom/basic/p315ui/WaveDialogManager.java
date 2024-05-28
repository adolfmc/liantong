package com.sinovatech.unicom.basic.p315ui;

import android.app.Activity;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.WaveDialogManager */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class WaveDialogManager {
    private static CustomePorgressDialog dialog;

    public static void show(Activity activity) {
        dialog = new CustomePorgressDialog(activity);
        dialog.show();
    }

    public static void disMiss() {
        CustomePorgressDialog customePorgressDialog = dialog;
        if (customePorgressDialog == null || !customePorgressDialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }
}
