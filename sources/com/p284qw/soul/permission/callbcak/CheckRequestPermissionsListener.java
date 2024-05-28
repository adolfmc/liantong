package com.p284qw.soul.permission.callbcak;

import com.p284qw.soul.permission.bean.Permission;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.callbcak.CheckRequestPermissionsListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface CheckRequestPermissionsListener {
    void onAllPermissionOk(Permission[] permissionArr);

    void onPermissionDenied(Permission[] permissionArr);
}
