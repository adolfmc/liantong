package android.support.p083v4.content.p084pm;

import android.annotation.SuppressLint;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.content.pm.PermissionInfoCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class PermissionInfoCompat {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* renamed from: android.support.v4.content.pm.PermissionInfoCompat$Protection */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface Protection {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* renamed from: android.support.v4.content.pm.PermissionInfoCompat$ProtectionFlags */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface ProtectionFlags {
    }

    private PermissionInfoCompat() {
    }

    @SuppressLint({"WrongConstant"})
    public static int getProtection(@NonNull PermissionInfo permissionInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return permissionInfo.getProtection();
        }
        return permissionInfo.protectionLevel & 15;
    }

    @SuppressLint({"WrongConstant"})
    public static int getProtectionFlags(@NonNull PermissionInfo permissionInfo) {
        if (Build.VERSION.SDK_INT >= 28) {
            return permissionInfo.getProtectionFlags();
        }
        return permissionInfo.protectionLevel & (-16);
    }
}
