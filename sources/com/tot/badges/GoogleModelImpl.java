package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class GoogleModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "google not support before API O";

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        if (Build.VERSION.SDK_INT < 26) {
            throw new Exception(NOTIFICATION_ERROR);
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i);
        intent.putExtra("badge_count_package_name", application.getPackageName());
        intent.putExtra("badge_count_class_name", Utils.getInstance().getLaunchIntentForPackage(application));
        application.sendBroadcast(intent);
        return notification;
    }
}
