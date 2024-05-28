package pub.devrel.easypermissions;

import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.bytedance.applog.tracker.Tracker;
import java.util.Arrays;
import pub.devrel.easypermissions.EasyPermissions;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
class RationaleDialogClickListener implements DialogInterface.OnClickListener {
    private EasyPermissions.PermissionCallbacks mCallbacks;
    private RationaleDialogConfig mConfig;
    private Object mHost;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RationaleDialogClickListener(RationaleDialogFragmentCompat rationaleDialogFragmentCompat, RationaleDialogConfig rationaleDialogConfig, EasyPermissions.PermissionCallbacks permissionCallbacks) {
        Object activity;
        if (rationaleDialogFragmentCompat.getParentFragment() != null) {
            activity = rationaleDialogFragmentCompat.getParentFragment();
        } else {
            activity = rationaleDialogFragmentCompat.getActivity();
        }
        this.mHost = activity;
        this.mConfig = rationaleDialogConfig;
        this.mCallbacks = permissionCallbacks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 11)
    public RationaleDialogClickListener(RationaleDialogFragment rationaleDialogFragment, RationaleDialogConfig rationaleDialogConfig, EasyPermissions.PermissionCallbacks permissionCallbacks) {
        Object activity;
        if (Build.VERSION.SDK_INT >= 17) {
            if (rationaleDialogFragment.getParentFragment() != null) {
                activity = rationaleDialogFragment.getParentFragment();
            } else {
                activity = rationaleDialogFragment.getActivity();
            }
            this.mHost = activity;
        } else {
            this.mHost = rationaleDialogFragment.getActivity();
        }
        this.mConfig = rationaleDialogConfig;
        this.mCallbacks = permissionCallbacks;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        Tracker.onClick(dialogInterface, i);
        if (i == -1) {
            EasyPermissions.executePermissionsRequest(this.mHost, this.mConfig.permissions, this.mConfig.requestCode);
        } else {
            notifyPermissionDenied();
        }
    }

    private void notifyPermissionDenied() {
        EasyPermissions.PermissionCallbacks permissionCallbacks = this.mCallbacks;
        if (permissionCallbacks != null) {
            permissionCallbacks.onPermissionsDenied(this.mConfig.requestCode, Arrays.asList(this.mConfig.permissions));
        }
    }
}
