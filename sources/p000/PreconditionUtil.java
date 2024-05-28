package p000;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: y */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PreconditionUtil {
    /* renamed from: a */
    public static void m12a(boolean z, @NonNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    @NonNull
    /* renamed from: a */
    public static <T> T m13a(@Nullable T t, @NonNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }
}
