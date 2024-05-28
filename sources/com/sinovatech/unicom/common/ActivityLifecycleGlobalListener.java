package com.sinovatech.unicom.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import com.sinovatech.unicom.basic.p315ui.activity.WelcomeClient;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayConfig;
import com.sinovatech.unicom.separatemodule.unicompay.UnicomPayUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ActivityLifecycleGlobalListener implements Application.ActivityLifecycleCallbacks {
    private int lastPausedActivityHashCode;
    private String lastPausedActivityName;
    private long lastPausedTime;
    private int foregroundActivityCount = 0;
    private boolean isChangingConfigActivity = false;
    private boolean willSwitchToForeground = false;
    private boolean isForegroundNow = false;
    private long appUseReduceTime = 0;
    private final String TAG = getClass().getSimpleName();

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        String str = this.TAG;
        MsLogUtil.m7979d(str, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivityCreated");
        if (activity instanceof WelcomeClient) {
            MsLogUtil.m7979d(this.TAG, "启动页或者支付不适配");
            return;
        }
        MsLogUtil.m7979d("适配", "开始适配");
        CustomDensityHandler.setCustomDensity(activity, activity.getApplication());
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        MsLogUtil.m7979d(this.TAG, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivityStarted");
        if (this.foregroundActivityCount == 0 || !this.isForegroundNow) {
            this.willSwitchToForeground = true;
            App.isTopProcess = true;
            MsLogUtil.m7979d("后台判断", "onActivityStarted: 前台了");
            try {
                if (activity.getClass().getName().startsWith(UnicomPayConfig.paySdkPackage)) {
                    UnicomPayUtils.getInstance(activity).refreshToken(activity);
                }
            } catch (Exception e) {
                e.printStackTrace();
                MsLogUtil.m7979d(this.TAG, "统一支付sdk刷新token异常" + e.getMessage());
            }
        }
        if (this.isChangingConfigActivity) {
            this.isChangingConfigActivity = false;
        } else {
            this.foregroundActivityCount++;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        String str = this.TAG;
        MsLogUtil.m7979d(str, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivityResumed");
        if (activity instanceof WelcomeClient) {
            MsLogUtil.m7979d(this.TAG, "onResume 启动页不适配|支付不适配");
            return;
        }
        MsLogUtil.m7979d("适配", "开始适配");
        CustomDensityHandler.setCustomDensity(activity, activity.getApplication());
        addAppUseReduceTimeIfNeeded(activity);
        if (this.willSwitchToForeground && isInteractive(activity)) {
            this.isForegroundNow = true;
            Log.i("TAG", "switch to foreground");
        }
        if (this.isForegroundNow) {
            this.willSwitchToForeground = false;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        String str = this.TAG;
        MsLogUtil.m7979d(str, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivityPaused");
        this.lastPausedActivityName = getActivityName(activity);
        this.lastPausedActivityHashCode = activity.hashCode();
        this.lastPausedTime = System.currentTimeMillis();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        MsLogUtil.m7979d(this.TAG, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivityStopped");
        addAppUseReduceTimeIfNeeded(activity);
        if (activity.isChangingConfigurations()) {
            this.isChangingConfigActivity = true;
            return;
        }
        this.foregroundActivityCount--;
        if (this.foregroundActivityCount == 0) {
            this.isForegroundNow = false;
            App.isTopProcess = false;
            MsLogUtil.m7979d("后台判断", "onActivityStopped: 后台了");
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        String str = this.TAG;
        MsLogUtil.m7979d(str, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivitySaveInstanceState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        String str = this.TAG;
        MsLogUtil.m7979d(str, "Activity生命周期监听：" + activity.getClass().getSimpleName() + " >> onActivityDestroyed");
    }

    private void addAppUseReduceTimeIfNeeded(Activity activity) {
        if (getActivityName(activity).equals(this.lastPausedActivityName) && activity.hashCode() == this.lastPausedActivityHashCode) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.lastPausedTime;
            if (currentTimeMillis - j > 1000) {
                this.appUseReduceTime += currentTimeMillis - j;
            }
        }
        this.lastPausedActivityHashCode = -1;
        this.lastPausedActivityName = null;
        this.lastPausedTime = 0L;
    }

    private boolean isInteractive(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (Build.VERSION.SDK_INT >= 20) {
            return powerManager.isInteractive();
        }
        return powerManager.isScreenOn();
    }

    private String getActivityName(Activity activity) {
        return activity.getClass().getCanonicalName();
    }
}
