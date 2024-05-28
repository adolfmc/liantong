package io.objectbox;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface TxCallback<T> {
    void txFinished(@Nullable T t, @Nullable Throwable th);
}
