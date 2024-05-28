package org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Exceptions {
    public static IllegalArgumentException illegalArgumentException(String str, Throwable th) {
        return new IllegalArgumentException(str, th);
    }

    public static IllegalStateException illegalStateException(String str, Throwable th) {
        return new IllegalStateException(str, th);
    }
}
