package p472q0;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: q0.a */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13676a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    public Activity f27515a;

    /* renamed from: a */
    public static C13676a m141a(Application application) {
        C13676a c13676a = new C13676a();
        application.registerActivityLifecycleCallbacks(c13676a);
        return c13676a;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (this.f27515a != activity) {
            return;
        }
        this.f27515a = null;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        this.f27515a = activity;
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
