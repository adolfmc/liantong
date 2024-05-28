package com.p284qw.soul.permission.checker;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.p083v4.content.ContextCompat;

/* renamed from: com.qw.soul.permission.checker.RunTimePermissionChecker */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
class RunTimePermissionChecker implements PermissionChecker {
    private Context context;
    private String permission;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunTimePermissionChecker(Context context, String str) {
        this.permission = str;
        this.context = context;
    }

    @Override // com.p284qw.soul.permission.checker.PermissionChecker
    @TargetApi(23)
    public boolean check() {
        return ContextCompat.checkSelfPermission(this.context, this.permission) == 0;
    }
}
