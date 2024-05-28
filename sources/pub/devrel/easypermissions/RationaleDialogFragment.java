package pub.devrel.easypermissions;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import pub.devrel.easypermissions.EasyPermissions;

@RequiresApi(11)
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class RationaleDialogFragment extends DialogFragment {
    private RationaleDialogClickListener clickListener;
    private RationaleDialogConfig config;
    private EasyPermissions.PermissionCallbacks permissionCallbacks;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RationaleDialogFragment newInstance(@StringRes int i, @StringRes int i2, @NonNull String str, int i3, @NonNull String[] strArr) {
        RationaleDialogFragment rationaleDialogFragment = new RationaleDialogFragment();
        rationaleDialogFragment.setArguments(new RationaleDialogConfig(i, i2, str, i3, strArr).toBundle());
        return rationaleDialogFragment;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    @SuppressLint({"NewApi"})
    public void onAttach(Context context) {
        super.onAttach(context);
        if ((Build.VERSION.SDK_INT >= 17) && getParentFragment() != null && (getParentFragment() instanceof EasyPermissions.PermissionCallbacks)) {
            this.permissionCallbacks = (EasyPermissions.PermissionCallbacks) getParentFragment();
        } else if (context instanceof EasyPermissions.PermissionCallbacks) {
            this.permissionCallbacks = (EasyPermissions.PermissionCallbacks) context;
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.permissionCallbacks = null;
    }

    @Override // android.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        setCancelable(false);
        this.config = new RationaleDialogConfig(getArguments());
        this.clickListener = new RationaleDialogClickListener(this, this.config, this.permissionCallbacks);
        return this.config.createDialog(getActivity(), this.clickListener);
    }
}
