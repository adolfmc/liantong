package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ToastDialogManager {
    private Activity context;
    private Dialog dialog;
    private Handler handler = new Handler();

    public ToastDialogManager(Activity activity) {
        this.context = activity;
    }

    public void show(String str) {
        this.dialog = new Dialog(this.context, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        int i = displayMetrics.densityDpi;
        float f2 = displayMetrics.xdpi;
        float f3 = displayMetrics.ydpi;
        int i2 = displayMetrics.widthPixels;
        int i3 = displayMetrics.heightPixels;
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        attributes.width = (int) (i2 * 0.6d);
        attributes.height = -2;
        attributes.gravity = 48;
        Window window = this.dialog.getWindow();
        window.setAttributes(attributes);
        window.addFlags(2);
        window.setDimAmount(0.0f);
        View inflate = this.context.getLayoutInflater().inflate(2131493004, (ViewGroup) null);
        ((TextView) inflate.findViewById(2131296443)).setText(str);
        this.dialog.setContentView(inflate);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.show();
        this.handler.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$ToastDialogManager$PH6tC1RuC16kZLpGYjqWrBK0KQs
            @Override // java.lang.Runnable
            public final void run() {
                ToastDialogManager.lambda$show$0(ToastDialogManager.this);
            }
        }, 3000L);
    }

    public static /* synthetic */ void lambda$show$0(ToastDialogManager toastDialogManager) {
        try {
            if (toastDialogManager.dialog == null || !toastDialogManager.dialog.isShowing()) {
                return;
            }
            toastDialogManager.dialog.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancel() {
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.dialog.cancel();
    }
}
