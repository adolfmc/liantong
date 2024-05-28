package com.p284qw.soul.permission.request.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.callbcak.GoAppDetailCallBack;
import com.p284qw.soul.permission.callbcak.RequestPermissionListener;
import com.p284qw.soul.permission.callbcak.SpecialPermissionListener;
import com.p284qw.soul.permission.debug.PermissionDebug;
import com.p284qw.soul.permission.request.IPermissionActions;

/* renamed from: com.qw.soul.permission.request.fragment.FragmentProxy */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FragmentProxy implements IPermissionActions {
    private static final String TAG = "FragmentProxy";
    private IPermissionActions fragmentImp;

    public FragmentProxy(IPermissionActions iPermissionActions) {
        this.fragmentImp = iPermissionActions;
    }

    @Override // com.p284qw.soul.permission.request.IPermissionActions
    public void requestPermissions(@NonNull String[] strArr, RequestPermissionListener requestPermissionListener) {
        this.fragmentImp.requestPermissions(strArr, requestPermissionListener);
        String str = TAG;
        PermissionDebug.m8226d(str, this.fragmentImp.getClass().getSimpleName() + " request:" + hashCode());
    }

    @Override // com.p284qw.soul.permission.request.IPermissionActions
    public void requestSpecialPermission(Special special, SpecialPermissionListener specialPermissionListener) {
        this.fragmentImp.requestSpecialPermission(special, specialPermissionListener);
        String str = TAG;
        PermissionDebug.m8226d(str, this.fragmentImp.getClass().getSimpleName() + " requestSpecial:" + hashCode());
    }

    @Override // com.p284qw.soul.permission.request.IPermissionActions
    public void goAppDetail(@Nullable GoAppDetailCallBack goAppDetailCallBack) {
        this.fragmentImp.goAppDetail(goAppDetailCallBack);
        String str = TAG;
        PermissionDebug.m8226d(str, this.fragmentImp.getClass().getSimpleName() + " goAppDetail:" + hashCode());
    }
}
