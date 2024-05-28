package io.objectbox.internal;

import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface CursorFactory<T> {
    Cursor<T> createCursor(Transaction transaction, long j, @Nullable BoxStore boxStore);
}
