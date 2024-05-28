package com.tot.badges;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import java.util.List;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Utils {
    public static final String UNABLE_TO_RESOLVE_INTENT_ERROR_ = "unable to resolve intent: ";
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }

    public boolean canResolveBroadcast(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        return queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0;
    }

    public String getLaunchIntentForPackage(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
    }
}
