package android.support.design.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatDialogFragment;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    @Override // android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new BottomSheetDialog(getContext(), getTheme());
    }
}
