package com.yhao.floatwindow;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class FloatLifecycle extends BroadcastReceiver implements Application.ActivityLifecycleCallbacks {
    private static final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";
    private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
    private static final long delay = 300;
    private static int num;
    private static ResumedListener sResumedListener;
    private Class[] activities;
    private boolean appBackground;
    private Handler mHandler;
    private LifecycleListener mLifecycleListener;
    private int resumeCount;
    private boolean showFlag;
    private int startCount;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FloatLifecycle(Context context, boolean z, Class[] clsArr, LifecycleListener lifecycleListener) {
        this.showFlag = z;
        this.activities = clsArr;
        num++;
        this.mLifecycleListener = lifecycleListener;
        this.mHandler = new Handler();
        ((Application) context).registerActivityLifecycleCallbacks(this);
        context.registerReceiver(this, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }

    public static void setResumedListener(ResumedListener resumedListener) {
        sResumedListener = resumedListener;
    }

    private boolean needShow(Activity activity) {
        Class[] clsArr = this.activities;
        if (clsArr == null) {
            return true;
        }
        for (Class cls : clsArr) {
            if (cls.isInstance(activity)) {
                return this.showFlag;
            }
        }
        return !this.showFlag;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        ResumedListener resumedListener = sResumedListener;
        if (resumedListener != null) {
            num--;
            if (num == 0) {
                resumedListener.onResumed();
                sResumedListener = null;
            }
        }
        this.resumeCount++;
        if (needShow(activity)) {
            this.mLifecycleListener.onShow();
        } else {
            this.mLifecycleListener.onHide();
        }
        if (this.appBackground) {
            this.appBackground = false;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        this.resumeCount--;
        this.mHandler.postDelayed(new Runnable() { // from class: com.yhao.floatwindow.FloatLifecycle.1
            @Override // java.lang.Runnable
            public void run() {
                if (FloatLifecycle.this.resumeCount == 0) {
                    FloatLifecycle.this.appBackground = true;
                    FloatLifecycle.this.mLifecycleListener.onBackToDesktop();
                }
            }
        }, 300L);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.startCount++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.startCount--;
        if (this.startCount == 0) {
            this.mLifecycleListener.onBackToDesktop();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action != null && action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS") && SYSTEM_DIALOG_REASON_HOME_KEY.equals(intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY))) {
            this.mLifecycleListener.onBackToDesktop();
        }
    }
}
