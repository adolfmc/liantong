package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.support.annotation.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MeizuModelImpl implements IconBadgeNumModel {
    private static final String NOTIFICATION_ERROR = "not support : meizu";

    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        throw new Exception("not support : meizu");
    }
}
