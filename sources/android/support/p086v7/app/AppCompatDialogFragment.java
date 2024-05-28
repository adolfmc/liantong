package android.support.p086v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.p083v4.app.DialogFragment;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v7.app.AppCompatDialogFragment */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AppCompatDialogFragment extends DialogFragment {
    @Override // android.support.p083v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new AppCompatDialog(getContext(), getTheme());
    }

    @Override // android.support.p083v4.app.DialogFragment
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setupDialog(Dialog dialog, int i) {
        if (dialog instanceof AppCompatDialog) {
            AppCompatDialog appCompatDialog = (AppCompatDialog) dialog;
            switch (i) {
                case 1:
                case 2:
                    break;
                default:
                    return;
                case 3:
                    dialog.getWindow().addFlags(24);
                    break;
            }
            appCompatDialog.supportRequestWindowFeature(1);
            return;
        }
        super.setupDialog(dialog, i);
    }
}