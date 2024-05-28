package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.p317ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.ui.CircularLoading */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CircularLoading {
    public static Dialog showLoadDialog(Context context, String str, boolean z) {
        View inflate = LayoutInflater.from(context).inflate(2131493036, (ViewGroup) null);
        ((TextView) inflate.findViewById(2131298210)).setText(str);
        Dialog dialog = new Dialog(context, 2131952068);
        dialog.setContentView((RelativeLayout) inflate.findViewById(2131296863));
        dialog.setCancelable(z);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        window.setGravity(17);
        window.setAttributes(attributes);
        window.setWindowAnimations(2131951891);
        return dialog;
    }

    public static void closeDialog(Activity activity, Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        UIUtils.dismissDialog(activity, dialog);
    }
}
