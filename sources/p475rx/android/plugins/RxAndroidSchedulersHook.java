package p475rx.android.plugins;

import p475rx.Scheduler;
import p475rx.functions.Action0;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.android.plugins.RxAndroidSchedulersHook */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RxAndroidSchedulersHook {
    private static final RxAndroidSchedulersHook DEFAULT_INSTANCE = new RxAndroidSchedulersHook();

    public Scheduler getMainThreadScheduler() {
        return null;
    }

    public Action0 onSchedule(Action0 action0) {
        return action0;
    }

    public static RxAndroidSchedulersHook getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }
}
