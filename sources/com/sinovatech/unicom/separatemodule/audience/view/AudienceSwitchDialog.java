package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.app.Dialog;
import android.support.p086v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceSwitchDialog;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class AudienceSwitchDialog {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SwitchDialogInterface {
        void onClick(SharpnessEntity.LiveUrlBean liveUrlBean);
    }

    public static void show(AppCompatActivity appCompatActivity, List<SharpnessEntity.LiveUrlBean> list, boolean z, final SwitchDialogInterface switchDialogInterface) {
        final Dialog dialog = new Dialog(appCompatActivity, 2131952244);
        LinearLayout linearLayout = (LinearLayout) appCompatActivity.getLayoutInflater().inflate(z ? 2131493003 : 2131493002, (ViewGroup) null);
        TextView[] textViewArr = {(TextView) linearLayout.findViewById(2131296391), (TextView) linearLayout.findViewById(2131296388), (TextView) linearLayout.findViewById(2131296389), (TextView) linearLayout.findViewById(2131296390)};
        for (TextView textView : textViewArr) {
            textView.setVisibility(4);
            textView.setClickable(false);
        }
        for (int i = 0; i < list.size(); i++) {
            final SharpnessEntity.LiveUrlBean liveUrlBean = list.get(i);
            textViewArr[i].setVisibility(0);
            textViewArr[i].setText(liveUrlBean.getSharpnessName());
            textViewArr[i].setClickable(true);
            textViewArr[i].setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceSwitchDialog$CD1u1RaI5LsHrIRM4H_SK0NfsUw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceSwitchDialog.lambda$show$0(AudienceSwitchDialog.SwitchDialogInterface.this, liveUrlBean, dialog, view);
                }
            });
        }
        dialog.setContentView(linearLayout);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setWindowAnimations(2131952266);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = UIUtils.getScreenWidth((Activity) appCompatActivity);
        attributes.height = -2;
        window.setAttributes(attributes);
        if (UIUtils.isShowDialog(appCompatActivity, dialog)) {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(SwitchDialogInterface switchDialogInterface, SharpnessEntity.LiveUrlBean liveUrlBean, Dialog dialog, View view) {
        switchDialogInterface.onClick(liveUrlBean);
        dialog.dismiss();
    }
}
