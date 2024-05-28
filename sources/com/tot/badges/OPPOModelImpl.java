package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class OPPOModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "not support : oppo";

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        if (i == 0) {
            i = -1;
        }
        try {
            Intent intent = new Intent("com.oppo.unsettledevent");
            intent.putExtra("pakeageName", application.getPackageName());
            intent.putExtra("number", i);
            intent.putExtra("upgradeNumber", i);
            if (canResolveBroadcast(application, intent)) {
                application.sendBroadcast(intent);
            } else {
                Bundle bundle = new Bundle();
                bundle.putInt("app_badge_count", i);
                application.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String) null, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean canResolveBroadcast(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        return queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0;
    }
}
