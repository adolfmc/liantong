package com.p284qw.soul.permission.request;

import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.app.FragmentManager;
import com.p284qw.soul.permission.debug.PermissionDebug;
import com.p284qw.soul.permission.request.fragment.PermissionFragment;
import com.p284qw.soul.permission.request.fragment.PermissionSupportFragment;
import java.lang.reflect.Field;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.request.PermissionFragmentFactory */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class PermissionFragmentFactory {
    private static final String FRAGMENT_TAG = "permission_fragment_tag";
    private static final String TAG = "PermissionFragmentFactory";

    PermissionFragmentFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static IPermissionActions create(Activity activity) {
        if (activity instanceof FragmentActivity) {
            FragmentManager supportFragmentManager = getSupportFragmentManager((FragmentActivity) activity);
            PermissionSupportFragment permissionSupportFragment = (PermissionSupportFragment) supportFragmentManager.findFragmentByTag("permission_fragment_tag");
            if (permissionSupportFragment == null) {
                PermissionSupportFragment permissionSupportFragment2 = new PermissionSupportFragment();
                supportFragmentManager.beginTransaction().add(permissionSupportFragment2, "permission_fragment_tag").commitNowAllowingStateLoss();
                return permissionSupportFragment2;
            }
            return permissionSupportFragment;
        }
        android.app.FragmentManager fragmentManager = getFragmentManager(activity);
        PermissionFragment permissionFragment = (PermissionFragment) fragmentManager.findFragmentByTag("permission_fragment_tag");
        if (permissionFragment == null) {
            PermissionFragment permissionFragment2 = new PermissionFragment();
            fragmentManager.beginTransaction().add(permissionFragment2, "permission_fragment_tag").commitAllowingStateLoss();
            fragmentManager.executePendingTransactions();
            return permissionFragment2;
        }
        return permissionFragment;
    }

    private static FragmentManager getSupportFragmentManager(FragmentActivity fragmentActivity) {
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        return (supportFragmentManager.getFragments().size() <= 0 || supportFragmentManager.getFragments().get(0) == null) ? supportFragmentManager : supportFragmentManager.getFragments().get(0).getChildFragmentManager();
    }

    private static android.app.FragmentManager getFragmentManager(Activity activity) {
        android.app.FragmentManager fragmentManager = activity.getFragmentManager();
        if (Build.VERSION.SDK_INT >= 26) {
            if (fragmentManager.getFragments().size() > 0 && fragmentManager.getFragments().get(0) != null) {
                return fragmentManager.getFragments().get(0).getChildFragmentManager();
            }
        } else {
            try {
                Field declaredField = Class.forName("android.app.FragmentManagerImpl").getDeclaredField("mAdded");
                declaredField.setAccessible(true);
                List list = (List) declaredField.get(fragmentManager);
                if (Build.VERSION.SDK_INT >= 17 && list.size() > 0 && list.get(0) != null) {
                    PermissionDebug.m8226d(TAG, "reflect get child fragmentManager success");
                    return ((Fragment) list.get(0)).getChildFragmentManager();
                }
            } catch (Exception e) {
                String str = TAG;
                PermissionDebug.m8222w(str, "try to get childFragmentManager failed " + e.toString());
                e.printStackTrace();
            }
        }
        return fragmentManager;
    }
}
