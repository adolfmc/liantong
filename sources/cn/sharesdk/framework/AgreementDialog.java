package cn.sharesdk.framework;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import cn.sharesdk.framework.p094a.p095a.SharePrefrenceUtil;
import com.bytedance.applog.tracker.Tracker;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AgreementDialog extends FakeActivity {
    private Dialog dialog;
    private OnDialogDismiss onDialogDismiss;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface OnDialogDismiss {
        void consent();

        void refuse();
    }

    @Override // com.mob.tools.FakeActivity
    public void setActivity(final Activity activity) {
        super.setActivity(activity);
        try {
            this.dialog = new Dialog(activity, ResHelper.getStyleRes(activity, "mobcommon_DialogStyle"));
            View inflate = LayoutInflater.from(activity).inflate(ResHelper.getLayoutRes(activity, "sharesdk_agreement_dialog"), (ViewGroup) null);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setContentView(inflate);
            Window window = this.dialog.getWindow();
            WindowManager.LayoutParams attributes = window.getAttributes();
            window.setGravity(17);
            window.setAttributes(attributes);
            this.dialog.setCancelable(true);
            inflate.findViewById(ResHelper.getIdRes(activity, "sharesdk_agreement_dialog_reject_tv")).setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.framework.AgreementDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    AgreementDialog.this.refuse();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            inflate.findViewById(ResHelper.getIdRes(activity, "sharesdk_agreement_dialog_accept_tv")).setOnClickListener(new View.OnClickListener() { // from class: cn.sharesdk.framework.AgreementDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (AgreementDialog.this.dialog != null && AgreementDialog.this.dialog.isShowing()) {
                        AgreementDialog.this.dialog.dismiss();
                        if (AgreementDialog.this.onDialogDismiss != null) {
                            AgreementDialog.this.onDialogDismiss.consent();
                        }
                        SharePrefrenceUtil.m21961a().m21955a(true);
                    }
                    activity.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: cn.sharesdk.framework.AgreementDialog.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == 4) {
                        AgreementDialog.this.refuse();
                        return false;
                    }
                    return false;
                }
            });
            this.dialog.show();
        } catch (Throwable unused) {
        }
    }

    public void setShareParam(OnDialogDismiss onDialogDismiss) {
        this.onDialogDismiss = onDialogDismiss;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refuse() {
        Dialog dialog = this.dialog;
        if (dialog != null && dialog.isShowing()) {
            this.dialog.dismiss();
            OnDialogDismiss onDialogDismiss = this.onDialogDismiss;
            if (onDialogDismiss != null) {
                onDialogDismiss.refuse();
            }
        }
        this.activity.finish();
    }
}
