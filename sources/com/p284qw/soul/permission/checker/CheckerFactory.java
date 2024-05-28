package com.p284qw.soul.permission.checker;

import android.content.Context;
import com.p284qw.soul.permission.PermissionTools;
import com.p284qw.soul.permission.bean.Special;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.checker.CheckerFactory */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CheckerFactory {
    public static PermissionChecker create(Context context, Special special) {
        return new SpecialChecker(context, special);
    }

    public static PermissionChecker create(Context context, String str) {
        if (PermissionTools.isOldPermissionSystem(context)) {
            return new AppOpsChecker(context, str);
        }
        return new RunTimePermissionChecker(context, str);
    }
}
