package com.huawei.hms.common;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import com.huawei.hms.common.internal.Preconditions;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ErrorDialogFragment extends DialogFragment {

    /* renamed from: a */
    private Dialog f11067a = null;

    /* renamed from: b */
    private DialogInterface.OnCancelListener f11068b = null;

    public static ErrorDialogFragment newInstance(Dialog dialog) {
        return newInstance(dialog, null);
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        DialogInterface.OnCancelListener onCancelListener = this.f11068b;
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialogInterface);
        }
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        if (this.f11067a == null) {
            setShowsDialog(false);
        }
        return this.f11067a;
    }

    @Override // android.app.DialogFragment
    public void show(FragmentManager fragmentManager, String str) {
        Preconditions.checkNotNull(fragmentManager, "FragmentManager cannot be null!");
        super.show(fragmentManager, str);
    }

    public static ErrorDialogFragment newInstance(Dialog dialog, DialogInterface.OnCancelListener onCancelListener) {
        Preconditions.checkNotNull(dialog, "Dialog cannot be null!");
        ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
        errorDialogFragment.f11067a = dialog;
        dialog.setOnCancelListener(null);
        errorDialogFragment.f11067a.setOnDismissListener(null);
        if (onCancelListener != null) {
            errorDialogFragment.f11068b = onCancelListener;
        }
        return errorDialogFragment;
    }
}
