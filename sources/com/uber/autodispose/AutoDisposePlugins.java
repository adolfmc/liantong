package com.uber.autodispose;

import io.reactivex.functions.Consumer;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class AutoDisposePlugins {
    private static volatile boolean fillInOutsideLifecycleExceptionStacktraces;
    static volatile boolean lockdown;
    @Nullable
    private static volatile Consumer<? super OutsideLifecycleException> outsideLifecycleHandler;

    private AutoDisposePlugins() {
    }

    public static void lockdown() {
        lockdown = true;
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static boolean getFillInOutsideLifecycleExceptionStacktraces() {
        return fillInOutsideLifecycleExceptionStacktraces;
    }

    @Nullable
    public static Consumer<? super OutsideLifecycleException> getOutsideLifecycleHandler() {
        return outsideLifecycleHandler;
    }

    public static void setOutsideLifecycleHandler(@Nullable Consumer<? super OutsideLifecycleException> consumer) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        outsideLifecycleHandler = consumer;
    }

    public static void setFillInOutsideLifecycleExceptionStacktraces(boolean z) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        fillInOutsideLifecycleExceptionStacktraces = z;
    }

    public static void reset() {
        setOutsideLifecycleHandler(null);
    }
}
