package com.p284qw.soul.permission.adapter;

import com.p284qw.soul.permission.bean.Permission;
import com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.adapter.SimplePermissionAdapter */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class SimplePermissionAdapter implements CheckRequestPermissionListener {
    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
    public void onPermissionDenied(Permission permission) {
    }

    @Override // com.p284qw.soul.permission.callbcak.CheckRequestPermissionListener
    public void onPermissionOk(Permission permission) {
    }
}
