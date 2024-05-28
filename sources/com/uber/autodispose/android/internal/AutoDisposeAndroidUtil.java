package com.uber.autodispose.android.internal;

import android.os.Looper;
import android.support.annotation.RestrictTo;
import com.uber.autodispose.android.AutoDisposeAndroidPlugins;
import io.reactivex.functions.BooleanSupplier;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\11617560_dexfile_execute.dex */
public class AutoDisposeAndroidUtil {
    private static final BooleanSupplier MAIN_THREAD_CHECK = new BooleanSupplier() { // from class: com.uber.autodispose.android.internal.AutoDisposeAndroidUtil.1
        @Override // io.reactivex.functions.BooleanSupplier
        public boolean getAsBoolean() {
            return Looper.myLooper() == Looper.getMainLooper();
        }
    };

    private AutoDisposeAndroidUtil() {
    }

    public static boolean isMainThread() {
        return AutoDisposeAndroidPlugins.onCheckMainThread(MAIN_THREAD_CHECK);
    }
}
