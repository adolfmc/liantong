package com.huawei.hmf.tasks;

import com.huawei.hmf.tasks.p212a.C4810a;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class TaskExecutors {
    private static final TaskExecutors INSTANCE = new TaskExecutors();
    private final ExecutorService mBackground = C4810a.m15374a();
    private final Executor mImmediate = new ImmediateExecutor();
    private final Executor mUiThread = C4810a.m15373b();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class ImmediateExecutor implements Executor {
        ImmediateExecutor() {
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            runnable.run();
        }
    }

    private TaskExecutors() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ExecutorService background() {
        return INSTANCE.mBackground;
    }

    public static Executor immediate() {
        return INSTANCE.mImmediate;
    }

    public static Executor uiThread() {
        return INSTANCE.mUiThread;
    }
}
