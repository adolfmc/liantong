package com.p284qw.soul.permission.adapter;

import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.adapter.SimplePermissionsAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class SimplePermissionsAdapter implements CheckRequestPermissionsListener {
    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
    public void onAllPermissionOk(Permission[] permissionArr) {
    }

    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionsListener
    public void onPermissionDenied(Permission[] permissionArr) {
    }
}
