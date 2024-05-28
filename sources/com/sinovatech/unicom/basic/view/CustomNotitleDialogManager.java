package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomNotitleDialogManager {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CustomNotitleDialogListener {
        void onClickOk();
    }

    public static void show(Activity activity, String str, String str2, boolean z, String str3, String str4, final CustomNotitleDialogListener customNotitleDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i2;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493389, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomNotitleDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customNotitleDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                button.setBackgroundResource(2131231200);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomNotitleDialogManager.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
