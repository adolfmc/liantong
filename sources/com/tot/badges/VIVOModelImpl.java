package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;
import android.support.annotation.NonNull;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VIVOModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "not support : vivo";

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        try {
            Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
            intent.putExtra("packageName", application.getPackageName());
            intent.putExtra("className", application.getPackageManager().getLaunchIntentForPackage(application.getPackageName()).getComponent().getClassName());
            intent.putExtra("notificationNum", i);
            application.sendBroadcast(intent);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
