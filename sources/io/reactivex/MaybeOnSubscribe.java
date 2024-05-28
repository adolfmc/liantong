package io.reactivex;

import io.reactivex.annotations.NonNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface MaybeOnSubscribe<T> {
    void subscribe(@NonNull MaybeEmitter<T> maybeEmitter) throws Exception;
}
