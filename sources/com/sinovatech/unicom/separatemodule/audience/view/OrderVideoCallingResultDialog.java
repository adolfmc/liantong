package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OrderVideoCallingResultDialog {
    public static void show(AppCompatActivity appCompatActivity, String str) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        appCompatActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        View inflate = LayoutInflater.from(appCompatActivity).inflate(2131493539, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131297701);
        LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(2131297702);
        TextView textView = (TextView) inflate.findViewById(2131299031);
        TextView textView2 = (TextView) inflate.findViewById(2131299057);
        TextView textView3 = (TextView) inflate.findViewById(2131298999);
        if (TextUtils.isEmpty(str)) {
            linearLayout2.setVisibility(0);
            linearLayout.setVisibility(8);
        } else {
            linearLayout2.setVisibility(8);
            linearLayout.setVisibility(0);
        }
        textView2.setText("《" + str + "》");
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoCallingResultDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.OrderVideoCallingResultDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -2;
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.show();
        dialog.getWindow().setLayout((int) (i * 0.8d), -2);
    }
}
