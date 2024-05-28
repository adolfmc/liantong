package com.bytedance.pangle.fragment;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.Fragment;
import com.bytedance.pangle.log.ZeusLogger;

/* renamed from: com.bytedance.pangle.fragment.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
final class C3835b implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    Fragment f9170a;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(@NonNull Activity activity) {
    }

    public C3835b(Fragment fragment) {
        this.f9170a = fragment;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
        if (this.f9170a.getActivity() == activity && activity.getClassLoader() != this.f9170a.getClass().getClassLoader()) {
            try {
                Object obj = bundle.get("androidx.lifecycle.BundlableSavedStateRegistry.key");
                if (obj != null) {
                    ((Bundle) obj).remove("android:support:fragments");
                }
                bundle.remove("android:support:fragments");
                ZeusLogger.m16794d("Zeus/activity_pangle", "clear fragment, Activity:".concat(String.valueOf(activity)));
            } catch (Exception unused) {
            }
        }
    }
}
