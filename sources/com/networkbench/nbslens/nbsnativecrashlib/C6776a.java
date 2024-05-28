package com.networkbench.nbslens.nbsnativecrashlib;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
class C6776a {

    /* renamed from: a */
    private static final C6776a f17551a = new C6776a();

    /* renamed from: d */
    private static final int f17552d = 100;

    /* renamed from: b */
    private LinkedList<Activity> f17553b = null;

    /* renamed from: c */
    private boolean f17554c = false;

    private C6776a() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C6776a m8468a() {
        return f17551a;
    }

    /* renamed from: a */
    void m8467a(Application application) {
        this.f17553b = new LinkedList<>();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.networkbench.nbslens.nbsnativecrashlib.a.1

            /* renamed from: b */
            private int f17556b = 0;

            /* renamed from: c */
            private boolean f17557c = false;

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
            public void onActivityCreated(Activity activity, Bundle bundle) {
                C6776a.this.f17553b.addFirst(activity);
                if (C6776a.this.f17553b.size() > 100) {
                    C6776a.this.f17553b.removeLast();
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                int i = this.f17556b + 1;
                this.f17556b = i;
                if (i != 1 || this.f17557c) {
                    return;
                }
                C6776a.this.f17554c = true;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                this.f17557c = activity.isChangingConfigurations();
                int i = this.f17556b - 1;
                this.f17556b = i;
                if (i != 0 || this.f17557c) {
                    return;
                }
                C6776a.this.f17554c = false;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                C6776a.this.f17553b.remove(activity);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m8464b() {
        LinkedList<Activity> linkedList = this.f17553b;
        if (linkedList != null) {
            Iterator<Activity> it = linkedList.iterator();
            while (it.hasNext()) {
                it.next().finish();
            }
            this.f17553b.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m8463c() {
        return this.f17554c;
    }
}
