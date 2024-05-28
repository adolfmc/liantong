package p472q0;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

/* renamed from: q0.p */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13694p implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    public Activity f27561a;

    /* renamed from: b */
    public Application f27562b;

    /* renamed from: c */
    public C13686k f27563c;

    public C13694p(Activity activity) {
        this.f27561a = activity;
    }

    public C13694p(Application application) {
        this.f27562b = application;
    }

    /* renamed from: a */
    public final WindowManager m134a() {
        Activity activity = this.f27561a;
        if (activity != null) {
            if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
                return this.f27561a.getWindowManager();
            }
            return null;
        }
        Application application = this.f27562b;
        if (application != null) {
            return (WindowManager) application.getSystemService("window");
        }
        return null;
    }

    /* renamed from: a */
    public final void m133a(C13686k c13686k) {
        this.f27563c = c13686k;
        Activity activity = this.f27561a;
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            activity.registerActivityLifecycleCallbacks(this);
        } else {
            activity.getApplication().registerActivityLifecycleCallbacks(this);
        }
    }

    /* renamed from: b */
    public final void m132b() {
        this.f27563c = null;
        Activity activity = this.f27561a;
        if (activity == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            activity.unregisterActivityLifecycleCallbacks(this);
        } else {
            activity.getApplication().unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.f27561a != activity) {
            return;
        }
        C13686k c13686k = this.f27563c;
        if (c13686k != null) {
            c13686k.m140a();
        }
        m132b();
        this.f27561a = null;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        C13686k c13686k;
        if (this.f27561a == activity && (c13686k = this.f27563c) != null) {
            c13686k.m140a();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
