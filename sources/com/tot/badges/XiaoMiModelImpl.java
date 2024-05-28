package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.support.annotation.NonNull;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class XiaoMiModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "Xiaomi phones must send notification";

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        if (notification == null) {
            throw new Exception(NOTIFICATION_ERROR);
        }
        Object obj = notification.getClass().getDeclaredField("extraNotification").get(notification);
        obj.getClass().getDeclaredMethod("setMessageCount", Integer.TYPE).invoke(obj, Integer.valueOf(i));
        return notification;
    }
}
