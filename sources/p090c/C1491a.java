package p090c;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.p083v4.app.FragmentActivity;

/* renamed from: c.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class C1491a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    public InterfaceC1492b f2504a;

    /* renamed from: b */
    public C1501j f2505b;

    public C1491a(InterfaceC1492b interfaceC1492b) {
        this.f2505b = new C1501j(interfaceC1492b);
        this.f2504a = interfaceC1492b;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (C1497g.m22187c().f2525n && (activity instanceof FragmentActivity)) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.f2505b, true);
        }
        InterfaceC1492b interfaceC1492b = this.f2504a;
        if (interfaceC1492b != null) {
            interfaceC1492b.mo22185a(activity, activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        InterfaceC1492b interfaceC1492b = this.f2504a;
        if (interfaceC1492b != null) {
            interfaceC1492b.mo22185a(activity, activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
