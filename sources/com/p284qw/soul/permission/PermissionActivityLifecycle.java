package com.p284qw.soul.permission;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.p284qw.soul.permission.debug.PermissionDebug;
import com.p284qw.soul.permission.exception.ContainerStatusException;
import com.p284qw.soul.permission.exception.InitException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.qw.soul.permission.PermissionActivityLifecycle */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PermissionActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "PermissionActivityLifecycle";
    private List<Activity> activities = new ArrayList();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Activity getActivity() {
        List<Activity> list = this.activities;
        if (list == null || list.size() == 0) {
            throw new InitException();
        }
        PermissionDebug.m8226d(TAG, "current activity stack:" + this.activities.toString());
        for (int size = this.activities.size() + (-1); size >= 0; size--) {
            Activity activity = this.activities.get(size);
            if (PermissionTools.isActivityAvailable(activity)) {
                PermissionDebug.m8226d(TAG, "top available activity is :" + activity.getClass().getSimpleName());
                return activity;
            }
        }
        throw new ContainerStatusException();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.activities.add(activity);
        String str = TAG;
        PermissionDebug.m8226d(str, "stack added:" + activity.getClass().getSimpleName());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.activities.remove(activity);
        String str = TAG;
        PermissionDebug.m8226d(str, "stack removed:" + activity.getClass().getSimpleName());
    }
}
