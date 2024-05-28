package com.huawei.hms.p224ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import com.bytedance.applog.tracker.Tracker;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.UIUtil;

/* renamed from: com.huawei.hms.ui.AbstractDialog */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractDialog {

    /* renamed from: a */
    private Activity f11772a;

    /* renamed from: b */
    private AlertDialog f11773b;

    /* renamed from: c */
    private Callback f11774c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.ui.AbstractDialog$Callback */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Callback {
        void onCancel(AbstractDialog abstractDialog);

        void onDoWork(AbstractDialog abstractDialog);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.ui.AbstractDialog$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class DialogInterface$OnClickListenerC5080a implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC5080a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            AbstractDialog.this.fireDoWork();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.ui.AbstractDialog$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class DialogInterface$OnClickListenerC5081b implements DialogInterface.OnClickListener {
        DialogInterface$OnClickListenerC5081b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Tracker.onClick(dialogInterface, i);
            AbstractDialog.this.cancel();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.ui.AbstractDialog$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class DialogInterface$OnCancelListenerC5082c implements DialogInterface.OnCancelListener {
        DialogInterface$OnCancelListenerC5082c() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AbstractDialog.this.fireCancel();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.ui.AbstractDialog$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class DialogInterface$OnKeyListenerC5083d implements DialogInterface.OnKeyListener {
        DialogInterface$OnKeyListenerC5083d() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            if (4 == i && keyEvent.getAction() == 1) {
                AbstractDialog.this.cancel();
                return true;
            }
            return false;
        }
    }

    public void cancel() {
        AlertDialog alertDialog = this.f11773b;
        if (alertDialog != null) {
            alertDialog.cancel();
        }
    }

    public void dismiss() {
        AlertDialog alertDialog = this.f11773b;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    protected void fireCancel() {
        Callback callback = this.f11774c;
        if (callback != null) {
            callback.onCancel(this);
        }
    }

    protected void fireDoWork() {
        Callback callback = this.f11774c;
        if (callback != null) {
            callback.onDoWork(this);
        }
    }

    protected Activity getActivity() {
        return this.f11772a;
    }

    protected int getDialogThemeId() {
        return UIUtil.getDialogThemeId(getActivity());
    }

    protected AlertDialog onCreateDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getDialogThemeId());
        String onGetTitleString = onGetTitleString(activity);
        if (onGetTitleString != null) {
            builder.setTitle(onGetTitleString);
        }
        String onGetMessageString = onGetMessageString(activity);
        if (onGetMessageString != null) {
            builder.setMessage(onGetMessageString);
        }
        String onGetPositiveButtonString = onGetPositiveButtonString(activity);
        if (onGetPositiveButtonString != null) {
            builder.setPositiveButton(onGetPositiveButtonString, new DialogInterface$OnClickListenerC5080a());
        }
        String onGetNegativeButtonString = onGetNegativeButtonString(activity);
        if (onGetNegativeButtonString != null) {
            builder.setNegativeButton(onGetNegativeButtonString, new DialogInterface$OnClickListenerC5081b());
        }
        return builder.create();
    }

    protected abstract String onGetMessageString(Context context);

    protected abstract String onGetNegativeButtonString(Context context);

    protected abstract String onGetPositiveButtonString(Context context);

    protected abstract String onGetTitleString(Context context);

    public void setMessage(CharSequence charSequence) {
        AlertDialog alertDialog = this.f11773b;
        if (alertDialog != null) {
            alertDialog.setMessage(charSequence);
        }
    }

    public void setTitle(CharSequence charSequence) {
        AlertDialog alertDialog = this.f11773b;
        if (alertDialog != null) {
            alertDialog.setTitle(charSequence);
        }
    }

    public void show(Activity activity, Callback callback) {
        this.f11772a = activity;
        this.f11774c = callback;
        if (activity != null && !activity.isFinishing()) {
            AlertDialog onCreateDialog = onCreateDialog(this.f11772a);
            this.f11773b = onCreateDialog;
            onCreateDialog.setCanceledOnTouchOutside(false);
            this.f11773b.setOnCancelListener(new DialogInterface$OnCancelListenerC5082c());
            this.f11773b.setOnKeyListener(new DialogInterface$OnKeyListenerC5083d());
            this.f11773b.show();
            return;
        }
        HMSLog.m14112e("AbstractDialog", "In show, The activity is null or finishing.");
    }
}
