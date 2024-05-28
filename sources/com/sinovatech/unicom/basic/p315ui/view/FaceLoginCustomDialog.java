package com.sinovatech.unicom.basic.p315ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.view.FaceLoginCustomDialog */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FaceLoginCustomDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.view.FaceLoginCustomDialog$UpdateCustomDialogListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface UpdateCustomDialogListener {
        void onCancel();

        void onClickOk();
    }

    public static void show(final Activity activity, final UpdateCustomDialogListener updateCustomDialogListener) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493116, (ViewGroup) null);
            ConfigManager configManager = new ConfigManager(App.getInstance());
            TextView textView = (TextView) inflate.findViewById(2131296785);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(2131296783);
            TextView textView2 = (TextView) inflate.findViewById(2131296784);
            String sweepFaceSafetyDesc = configManager.getSweepFaceSafetyDesc();
            if (sweepFaceSafetyDesc.contains("/n")) {
                sweepFaceSafetyDesc = sweepFaceSafetyDesc.replace("/n", "\n");
            }
            textView.setText(sweepFaceSafetyDesc);
            final TextView textView3 = (TextView) inflate.findViewById(2131296787);
            TextView textView4 = (TextView) inflate.findViewById(2131296781);
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.-$$Lambda$FaceLoginCustomDialog$LIxcRQCMBOXlz8uGk7fThPMEo84
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FaceLoginCustomDialog.lambda$show$0(checkBox, dialog, updateCustomDialogListener, view);
                }
            });
            textView4.setVisibility(0);
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.-$$Lambda$FaceLoginCustomDialog$JRYSs6Qnn-vZ5YMTYJ7XlgKymq4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FaceLoginCustomDialog.lambda$show$1(dialog, updateCustomDialogListener, view);
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.ui.view.-$$Lambda$FaceLoginCustomDialog$7o-ca5YPgJTPrvqDRlU3rddXImU
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    FaceLoginCustomDialog.lambda$show$2(textView3, compoundButton, z);
                }
            });
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.-$$Lambda$FaceLoginCustomDialog$57scIPUpFswvppSxcTPiB1-phiE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IntentManager.gotoWebViewActivity(activity, URLSet.getLoginFaceUrl(), "");
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.85d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(CheckBox checkBox, Dialog dialog, UpdateCustomDialogListener updateCustomDialogListener, View view) {
        if (checkBox.isChecked()) {
            dialog.cancel();
            updateCustomDialogListener.onClickOk();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$1(Dialog dialog, UpdateCustomDialogListener updateCustomDialogListener, View view) {
        dialog.cancel();
        updateCustomDialogListener.onCancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$2(TextView textView, CompoundButton compoundButton, boolean z) {
        if (z) {
            textView.setClickable(true);
            textView.setBackgroundResource(2131232468);
            return;
        }
        textView.setClickable(false);
        textView.setBackgroundResource(2131231288);
    }
}
