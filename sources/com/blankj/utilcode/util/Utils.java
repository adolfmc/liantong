package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.blankj.utilcode.util.ThreadUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Utils {
    @SuppressLint({"StaticFieldLeak"})
    private static Application sApp;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Consumer<T> {
        void accept(T t);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Func1<Ret, Par> {
        Ret call(Par par);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnAppStatusChangedListener {
        void onBackground(Activity activity);

        void onForeground(Activity activity);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Supplier<T> {
        T get();
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Application application) {
        if (application == null) {
            Log.e("Utils", "app is null.");
            return;
        }
        Application application2 = sApp;
        if (application2 == null) {
            sApp = application;
            UtilsBridge.init(sApp);
            UtilsBridge.preLoad();
        } else if (application2.equals(application)) {
        } else {
            UtilsBridge.unInit(sApp);
            sApp = application;
            UtilsBridge.init(sApp);
        }
    }

    public static Application getApp() {
        Application application = sApp;
        if (application != null) {
            return application;
        }
        init(UtilsBridge.getApplicationByReflect());
        if (sApp == null) {
            throw new NullPointerException("reflect failed.");
        }
        Log.i("Utils", UtilsBridge.getCurrentProcessName() + " reflect app success.");
        return sApp;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static abstract class Task<Result> extends ThreadUtils.SimpleTask<Result> {
        private Consumer<Result> mConsumer;

        public Task(Consumer<Result> consumer) {
            this.mConsumer = consumer;
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onSuccess(Result result) {
            Consumer<Result> consumer = this.mConsumer;
            if (consumer != null) {
                consumer.accept(result);
            }
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class ActivityLifecycleCallbacks {
        public void onActivityCreated(@NonNull Activity activity) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onActivityStarted(@NonNull Activity activity) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onActivityResumed(@NonNull Activity activity) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onActivityPaused(@NonNull Activity activity) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onActivityStopped(@NonNull Activity activity) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onActivityDestroyed(@NonNull Activity activity) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }

        public void onLifecycleChanged(@NonNull Activity activity, Lifecycle.Event event) {
            if (activity == null) {
                throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
            }
        }
    }
}
