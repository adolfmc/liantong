package com.bytedance.sdk.openadsdk.p186mb;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: com.bytedance.sdk.openadsdk.mb.mb */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class C4069mb implements Application.ActivityLifecycleCallbacks {

    /* renamed from: mb */
    private static volatile boolean f9686mb;

    /* renamed from: b */
    private InterfaceC4070mb f9687b;

    /* renamed from: ox */
    private int f9688ox = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.sdk.openadsdk.mb.mb$mb */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC4070mb {
        /* renamed from: mb */
        void mo16349mb();

        /* renamed from: ox */
        void mo16348ox();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    /* renamed from: mb */
    public Boolean m16354mb() {
        return Boolean.valueOf(f9686mb);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        this.f9688ox++;
        f9686mb = false;
        InterfaceC4070mb interfaceC4070mb = this.f9687b;
        if (interfaceC4070mb != null) {
            interfaceC4070mb.mo16348ox();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        this.f9688ox--;
        if (this.f9688ox == 0) {
            f9686mb = true;
            InterfaceC4070mb interfaceC4070mb = this.f9687b;
            if (interfaceC4070mb != null) {
                interfaceC4070mb.mo16349mb();
            }
        }
    }

    /* renamed from: mb */
    public void m16353mb(InterfaceC4070mb interfaceC4070mb) {
        this.f9687b = interfaceC4070mb;
    }
}
