package com.tot.badges;

import android.app.Application;
import android.app.Notification;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class HuaWeiModelImpl implements IconBadgeNumModel {
    @Override // com.tot.badges.IconBadgeNumModel
    public Notification setIconBadgeNum(@NonNull Application application, Notification notification, int i) throws Exception {
        Bundle bundle = new Bundle();
        bundle.putString("package", application.getPackageName());
        bundle.putString("class", Utils.getInstance().getLaunchIntentForPackage(application));
        bundle.putInt("badgenumber", i);
        application.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        return null;
    }
}
