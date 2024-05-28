package com.jakewharton.rxbinding2.internal;

import android.support.annotation.RestrictTo;
import io.reactivex.functions.Predicate;
import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class Functions {
    private static final Always ALWAYS_TRUE = new Always(true);
    public static final Callable<Boolean> CALLABLE_ALWAYS_TRUE;
    public static final Predicate<Object> PREDICATE_ALWAYS_TRUE;

    static {
        Always always = ALWAYS_TRUE;
        CALLABLE_ALWAYS_TRUE = always;
        PREDICATE_ALWAYS_TRUE = always;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static final class Always implements Predicate<Object>, Callable<Boolean> {
        private final Boolean value;

        Always(Boolean bool) {
            this.value = bool;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public Boolean call() {
            return this.value;
        }

        @Override // io.reactivex.functions.Predicate
        public boolean test(Object obj) throws Exception {
            return this.value.booleanValue();
        }
    }

    private Functions() {
        throw new AssertionError("No instances.");
    }
}
