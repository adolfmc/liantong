package com.jakewharton.rxbinding2.internal;

import android.os.Looper;
import android.support.annotation.RestrictTo;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposables;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Preconditions {
    public static void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static boolean checkMainThread(Observer<?> observer) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            observer.onSubscribe(Disposables.empty());
            observer.onError(new IllegalStateException("Expected to be called on the main thread but was " + Thread.currentThread().getName()));
            return false;
        }
        return true;
    }

    private Preconditions() {
        throw new AssertionError("No instances.");
    }
}
