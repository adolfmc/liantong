package com.uber.autodispose;

import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class AutoDisposeUtil {
    private AutoDisposeUtil() {
        throw new InstantiationError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T checkNotNull(@Nullable T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
