package com.p284qw.soul.permission.request.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentActivity;
import com.bytedance.applog.tracker.Tracker;
import com.p284qw.soul.permission.PermissionTools;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.p284qw.soul.permission.callbcak.RequestPermissionListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.p284qw.soul.permission.checker.SpecialChecker;
import com.p284qw.soul.permission.debug.PermissionDebug;
import com.p284qw.soul.permission.request.IPermissionActions;

/* renamed from: com.qw.soul.permission.request.fragment.PermissionSupportFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PermissionSupportFragment extends Fragment implements IPermissionActions {
    private static final String TAG = "PermissionSupportFragment";
    private GoAppDetailCallBack goAppDetailCallBack;
    private RequestPermissionListener runtimeListener;
    private SpecialPermissionListener specialListener;
    private Special specialToRequest;

    @Override // android.support.p083v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        Tracker.onHiddenChanged(this, z);
        super.onHiddenChanged(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onPause() {
        Tracker.onPause(this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onResume() {
        Tracker.onResume(this);
        super.onResume();
    }

    @Override // android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        Tracker.setUserVisibleHint(this, z);
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override // com.p284qw.soul.permission.request.IPermissionActions
    @TargetApi(23)
    public void requestPermissions(String[] strArr, RequestPermissionListener requestPermissionListener) {
        requestPermissions(strArr, 1024);
        this.runtimeListener = requestPermissionListener;
    }

    @Override // android.support.p083v4.app.Fragment
    @TargetApi(23)
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        Permission[] permissionArr = new Permission[strArr.length];
        if (i == 1024) {
            for (int i2 = 0; i2 < strArr.length; i2++) {
                permissionArr[i2] = new Permission(strArr[i2], iArr[i2], shouldShowRequestPermissionRationale(strArr[i2]));
            }
        }
        if (this.runtimeListener == null || !PermissionTools.isActivityAvailable(getActivity())) {
            return;
        }
        this.runtimeListener.onPermissionResult(permissionArr);
    }

    @Override // com.p284qw.soul.permission.request.IPermissionActions
    public void requestSpecialPermission(Special special, SpecialPermissionListener specialPermissionListener) {
        this.specialListener = specialPermissionListener;
        this.specialToRequest = special;
        Intent specialPermissionIntent = PermissionTools.getSpecialPermissionIntent(getActivity(), special);
        if (specialPermissionIntent == null) {
            PermissionDebug.m8222w(TAG, "create intent failed");
            return;
        }
        try {
            startActivityForResult(specialPermissionIntent, 2048);
        } catch (Exception e) {
            e.printStackTrace();
            PermissionDebug.m8225e(TAG, e.toString());
        }
    }

    @Override // com.p284qw.soul.permission.request.IPermissionActions
    public void goAppDetail(@Nullable GoAppDetailCallBack goAppDetailCallBack) {
        this.goAppDetailCallBack = goAppDetailCallBack;
        Intent appManageIntent = PermissionTools.getAppManageIntent(getActivity());
        if (appManageIntent == null) {
            PermissionDebug.m8222w(TAG, "create intent failed");
        } else {
            startActivityForResult(appManageIntent, 4096);
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        GoAppDetailCallBack goAppDetailCallBack;
        Special special;
        super.onActivityResult(i, i2, intent);
        FragmentActivity activity = getActivity();
        if (PermissionTools.isActivityAvailable(activity)) {
            if (i != 2048 || (special = this.specialToRequest) == null || this.specialListener == null) {
                if (i != 4096 || (goAppDetailCallBack = this.goAppDetailCallBack) == null) {
                    return;
                }
                goAppDetailCallBack.onBackFromAppDetail(intent);
            } else if (new SpecialChecker(activity, special).check()) {
                this.specialListener.onGranted(this.specialToRequest);
            } else {
                this.specialListener.onDenied(this.specialToRequest);
            }
        }
    }
}
