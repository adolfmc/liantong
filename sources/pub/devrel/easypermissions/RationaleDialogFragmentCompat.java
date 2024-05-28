package pub.devrel.easypermissions;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.p086v7.app.AppCompatDialogFragment;
import pub.devrel.easypermissions.EasyPermissions;

@TargetApi(17)
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class RationaleDialogFragmentCompat extends AppCompatDialogFragment {
    private RationaleDialogClickListener clickListener;
    private RationaleDialogConfig config;
    private EasyPermissions.PermissionCallbacks permissionCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RationaleDialogFragmentCompat newInstance(@StringRes int i, @StringRes int i2, @NonNull String str, int i3, @NonNull String[] strArr) {
        RationaleDialogFragmentCompat rationaleDialogFragmentCompat = new RationaleDialogFragmentCompat();
        rationaleDialogFragmentCompat.setArguments(new RationaleDialogConfig(i, i2, str, i3, strArr).toBundle());
        return rationaleDialogFragmentCompat;
    }

    @Override // android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() != null && (getParentFragment() instanceof EasyPermissions.PermissionCallbacks)) {
            this.permissionCallbacks = (EasyPermissions.PermissionCallbacks) getParentFragment();
        } else if (context instanceof EasyPermissions.PermissionCallbacks) {
            this.permissionCallbacks = (EasyPermissions.PermissionCallbacks) context;
        }
    }

    @Override // android.support.p083v4.app.DialogFragment, android.support.p083v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.permissionCallbacks = null;
    }

    @Override // android.support.p086v7.app.AppCompatDialogFragment, android.support.p083v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        setCancelable(false);
        this.config = new RationaleDialogConfig(getArguments());
        this.clickListener = new RationaleDialogClickListener(this, this.config, this.permissionCallbacks);
        return this.config.createDialog(getContext(), this.clickListener);
    }
}
