package com.megvii.livenesslib.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.megvii.livenesslib.C5351R;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FlodDialogManager {
    private static Dialog dialog;

    public static void show(Activity activity, String str, String str2, String str3) {
        try {
            dialog = new Dialog(activity, C5351R.C5356style.FoldCustomDialog);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(C5351R.C5354layout.flod_custom_dialog, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(C5351R.C5353id.custom_dialog_title_textview);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(C5351R.C5353id.custom_dialog_message_textview);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(C5351R.C5353id.custom_dialog_ok_button);
            if (!TextUtils.isEmpty(str3)) {
                button.setText(str3);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.megvii.livenesslib.util.FlodDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (FlodDialogManager.dialog != null) {
                        FlodDialogManager.dialog.cancel();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.megvii.livenesslib.util.FlodDialogManager.2
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    return i2 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            if (activity.isFinishing()) {
                return;
            }
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void hideDialog() {
        try {
            if (dialog == null || !dialog.isShowing()) {
                return;
            }
            dialog.cancel();
            dialog = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
