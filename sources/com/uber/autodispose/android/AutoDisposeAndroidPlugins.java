package com.uber.autodispose.android;

import android.support.annotation.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BooleanSupplier;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class AutoDisposeAndroidPlugins {
    static volatile boolean lockdown;
    @Nullable
    private static volatile BooleanSupplier onCheckMainThread;

    private AutoDisposeAndroidPlugins() {
    }

    public static void lockdown() {
        lockdown = true;
    }

    public static boolean isLockdown() {
        return lockdown;
    }

    public static void setOnCheckMainThread(@Nullable BooleanSupplier booleanSupplier) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        onCheckMainThread = booleanSupplier;
    }

    public static boolean onCheckMainThread(BooleanSupplier booleanSupplier) {
        if (booleanSupplier == null) {
            throw new NullPointerException("defaultChecker == null");
        }
        BooleanSupplier booleanSupplier2 = onCheckMainThread;
        try {
            if (booleanSupplier2 == null) {
                return booleanSupplier.getAsBoolean();
            }
            return booleanSupplier2.getAsBoolean();
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }

    public static void reset() {
        setOnCheckMainThread(null);
    }
}
