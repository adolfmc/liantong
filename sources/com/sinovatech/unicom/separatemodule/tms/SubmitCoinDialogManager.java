package com.sinovatech.unicom.separatemodule.tms;

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
public class SubmitCoinDialogManager {
    private Activity context;
    private Dialog dialog;
    private Handler handler = new Handler();

    public SubmitCoinDialogManager(Activity activity) {
        this.context = activity;
    }

    public void show(int i) {
        this.dialog = new Dialog(this.context, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        int i2 = displayMetrics.densityDpi;
        float f2 = displayMetrics.xdpi;
        float f3 = displayMetrics.ydpi;
        int i3 = displayMetrics.widthPixels;
        WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
        attributes.width = (int) (i3 * 0.6d);
        attributes.height = -2;
        Window window = this.dialog.getWindow();
        window.setAttributes(attributes);
        window.setDimAmount(0.6f);
        window.addFlags(2);
        View inflate = this.context.getLayoutInflater().inflate(2131493105, (ViewGroup) null);
        ((TextView) inflate.findViewById(2131296680)).setText(i + "");
        this.dialog.setContentView(inflate);
        this.dialog.setCancelable(true);
        this.dialog.setCanceledOnTouchOutside(false);
        this.dialog.show();
        this.dialog.getWindow().setLayout(-2, -2);
        this.handler.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.tms.SubmitCoinDialogManager.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (SubmitCoinDialogManager.this.dialog == null || !SubmitCoinDialogManager.this.dialog.isShowing()) {
                        return;
                    }
                    SubmitCoinDialogManager.this.dialog.cancel();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 4000L);
    }

    public void cancel() {
        Dialog dialog = this.dialog;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        this.dialog.cancel();
    }
}
