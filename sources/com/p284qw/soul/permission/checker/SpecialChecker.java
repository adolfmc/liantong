package com.p284qw.soul.permission.checker;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.support.p083v4.app.NotificationManagerCompat;
import com.p284qw.soul.permission.bean.Special;
import com.p284qw.soul.permission.debug.PermissionDebug;

/* renamed from: com.qw.soul.permission.checker.SpecialChecker */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SpecialChecker implements PermissionChecker {
    private static final String TAG = "SpecialChecker";
    private Context context;
    private Special permission;

    public SpecialChecker(Context context, Special special) {
        this.context = context;
        this.permission = special;
    }

    @Override // com.p284qw.soul.permission.checker.PermissionChecker
    public boolean check() {
        try {
            switch (this.permission) {
                case NOTIFICATION:
                    return checkNotification();
                case SYSTEM_ALERT:
                    return checkSystemAlert();
                case UNKNOWN_APP_SOURCES:
                    return checkUnknownSource();
                default:
                    return true;
            }
        } catch (Exception e) {
            PermissionDebug.m8222w(TAG, e.toString());
            return true;
        }
    }

    private boolean checkNotification() {
        return NotificationManagerCompat.from(this.context).areNotificationsEnabled();
    }

    private boolean checkSystemAlert() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Settings.canDrawOverlays(this.context);
        }
        return new AppOpsChecker(this.context).checkOp(24);
    }

    private boolean checkUnknownSource() {
        if (Build.VERSION.SDK_INT >= 26) {
            return this.context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }
}
