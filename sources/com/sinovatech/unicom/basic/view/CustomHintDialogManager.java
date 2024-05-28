package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomHintDialogManager {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CustomeDialogListener {
        void onBackKeyDown();

        void onCancel();

        void onCheckBoxStatusChanged(boolean z);

        void onClickCancel();

        void onClickOk();

        void onShow();
    }

    public static void show(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, final boolean z2, final CustomeDialogListener customeDialogListener) {
        final Dialog dialog = new Dialog(activity, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        int i2 = displayMetrics.densityDpi;
        float f2 = displayMetrics.xdpi;
        float f3 = displayMetrics.ydpi;
        int i3 = displayMetrics.widthPixels;
        int i4 = displayMetrics.heightPixels;
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        double d = i3;
        attributes.width = (int) (0.6d * d);
        attributes.height = -2;
        dialog.getWindow().setAttributes(attributes);
        View inflate = activity.getLayoutInflater().inflate(2131493074, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(2131296788);
        if (!TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
        TextView textView2 = (TextView) inflate.findViewById(2131296785);
        textView2.setGravity(i);
        if (!TextUtils.isEmpty(str2)) {
            textView2.setText(str2);
        }
        Button button = (Button) inflate.findViewById(2131296787);
        if (!TextUtils.isEmpty(str4)) {
            button.setText(str4);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomHintDialogManager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.cancel();
                customeDialogListener.onClickOk();
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
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomHintDialogManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogListener.onClickCancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } else {
            button2.setVisibility(8);
            button.setBackgroundResource(2131231203);
        }
        ((CheckBox) inflate.findViewById(2131296782)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.view.CustomHintDialogManager.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                Tracker.onCheckedChanged(compoundButton, z3);
                CustomeDialogListener.this.onCheckBoxStatusChanged(z3);
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.CustomHintDialogManager.4
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                CustomeDialogListener.this.onShow();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.view.CustomHintDialogManager.5
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                CustomeDialogListener.this.onCancel();
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomHintDialogManager.6
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i5, KeyEvent keyEvent) {
                if (i5 == 4 && keyEvent.getRepeatCount() == 0) {
                    if (z2) {
                        dialogInterface.cancel();
                    }
                    customeDialogListener.onBackKeyDown();
                    return true;
                }
                return false;
            }
        });
        dialog.show();
        dialog.getWindow().setLayout((int) (d * 0.8d), -2);
    }
}
