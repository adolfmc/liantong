package p475rx.android.schedulers;

import android.os.Handler;
import android.os.Looper;
import p475rx.Scheduler;
import p475rx.android.plugins.RxAndroidPlugins;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.android.schedulers.AndroidSchedulers */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class AndroidSchedulers {
    private AndroidSchedulers() {
        throw new AssertionError("No instances");
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.android.schedulers.AndroidSchedulers$MainThreadSchedulerHolder */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class MainThreadSchedulerHolder {
        static final Scheduler MAIN_THREAD_SCHEDULER = new HandlerScheduler(new Handler(Looper.getMainLooper()));

        private MainThreadSchedulerHolder() {
        }
    }

    public static Scheduler mainThread() {
        Scheduler mainThreadScheduler = RxAndroidPlugins.getInstance().getSchedulersHook().getMainThreadScheduler();
        return mainThreadScheduler != null ? mainThreadScheduler : MainThreadSchedulerHolder.MAIN_THREAD_SCHEDULER;
    }
}
