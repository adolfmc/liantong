package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.content.Intent;
import android.support.annotation.NonNull;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SamsungModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "not support : samsung";

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i);
        intent.putExtra("badge_count_package_name", application.getPackageName());
        intent.putExtra("badge_count_class_name", Utils.getInstance().getLaunchIntentForPackage(application));
        if (Utils.getInstance().canResolveBroadcast(application, intent)) {
            application.sendBroadcast(intent);
            return null;
        }
        throw new Exception(Utils.UNABLE_TO_RESOLVE_INTENT_ERROR_ + intent.toString());
    }
}
