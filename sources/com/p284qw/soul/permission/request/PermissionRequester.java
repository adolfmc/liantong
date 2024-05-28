package com.p284qw.soul.permission.request;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.annotation.Nullable;
import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.p284qw.soul.permission.callbcak.RequestPermissionListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.p284qw.soul.permission.request.fragment.FragmentProxy;

/* renamed from: com.qw.soul.permission.request.PermissionRequester */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PermissionRequester {
    private FragmentProxy permissionFragment;
    private Special permissionSpecial;
    private String[] permissions;

    public PermissionRequester(Activity activity) {
        this.permissionFragment = new FragmentProxy(PermissionFragmentFactory.create(activity));
    }

    public PermissionRequester withPermission(String... strArr) {
        this.permissions = strArr;
        return this;
    }

    public PermissionRequester withPermission(Permission... permissionArr) {
        this.permissions = new String[permissionArr.length];
        int length = permissionArr.length;
        for (int i = 0; i < length; i++) {
            this.permissions[i] = permissionArr[i].permissionName;
        }
        return this;
    }

    public PermissionRequester withPermission(Special special) {
        this.permissionSpecial = special;
        return this;
    }

    @TargetApi(23)
    public void request(RequestPermissionListener requestPermissionListener) {
        String[] strArr;
        FragmentProxy fragmentProxy = this.permissionFragment;
        if (fragmentProxy == null || (strArr = this.permissions) == null) {
            throw new IllegalArgumentException("fragment or params permission is null");
        }
        fragmentProxy.requestPermissions(strArr, requestPermissionListener);
    }

    public void request(SpecialPermissionListener specialPermissionListener) {
        Special special;
        FragmentProxy fragmentProxy = this.permissionFragment;
        if (fragmentProxy == null || (special = this.permissionSpecial) == null) {
            throw new IllegalArgumentException("fragment or params special permission is null");
        }
        fragmentProxy.requestSpecialPermission(special, specialPermissionListener);
    }

    public void goAppDetail(@Nullable GoAppDetailCallBack goAppDetailCallBack) {
        this.permissionFragment.goAppDetail(goAppDetailCallBack);
    }
}
