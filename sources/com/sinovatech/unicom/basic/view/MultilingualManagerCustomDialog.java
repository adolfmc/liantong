package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.common.UIUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MultilingualManagerCustomDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CustomeDialogListener {
        void isChecked(boolean z);

        void onClickCancel();

        void onClickOk();

        void onShow();
    }

    public static Dialog create(final Activity activity, String str, boolean z, String str2, String str3, String str4, boolean z2, final CustomeDialogListener customeDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
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
            View inflate = activity.getLayoutInflater().inflate(2131493445, (ViewGroup) null);
            UIUtils.setGJR(inflate);
            TextView textView = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str3)) {
                button.setText(str3);
            }
            CheckBox checkBox = (CheckBox) inflate.findViewById(2131298584);
            TextView textView2 = (TextView) inflate.findViewById(2131296614);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131296609);
            if (z2 && !TextUtils.isEmpty(str4)) {
                textView2.setText(str4);
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z3) {
                    Tracker.onCheckedChanged(compoundButton, z3);
                    try {
                        if (CustomeDialogListener.this != null) {
                            CustomeDialogListener.this.isChecked(z3);
                        }
                    } catch (Exception unused) {
                    }
                }
            });
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (UIUtils.isDismissDialog(activity, dialog)) {
                        dialog.cancel();
                    }
                    customeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                button.setBackgroundResource(2131231200);
                if (!TextUtils.isEmpty(str2)) {
                    button2.setText(str2);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        if (UIUtils.isDismissDialog(activity, dialog)) {
                            dialog.cancel();
                        }
                        customeDialogListener.onClickCancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.MultilingualManagerCustomDialog.4
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    CustomeDialogListener customeDialogListener2 = CustomeDialogListener.this;
                    if (customeDialogListener2 != null) {
                        customeDialogListener2.onShow();
                    }
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
            return dialog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
