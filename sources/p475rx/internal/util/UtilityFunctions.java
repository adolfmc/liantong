package p475rx.internal.util;

import p475rx.functions.Func0;
import p475rx.functions.Func1;
import p475rx.functions.Func2;
import p475rx.functions.Func3;
import p475rx.functions.Func4;
import p475rx.functions.Func5;
import p475rx.functions.Func6;
import p475rx.functions.Func7;
import p475rx.functions.Func8;
import p475rx.functions.Func9;
import p475rx.functions.FuncN;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.internal.util.UtilityFunctions */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class UtilityFunctions {
    private static final NullFunction NULL_FUNCTION = new NullFunction();

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return new Func1<T, T>() { // from class: rx.internal.util.UtilityFunctions.1
            @Override // p475rx.functions.Func1
            public T call(T t) {
                return t;
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.UtilityFunctions$AlwaysTrue */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // p475rx.functions.Func1
        public Boolean call(Object obj) {
            return true;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.UtilityFunctions$AlwaysFalse */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    enum AlwaysFalse implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // p475rx.functions.Func1
        public Boolean call(Object obj) {
            return false;
        }
    }

    public static <T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> NullFunction<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> returnNull() {
        return NULL_FUNCTION;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: rx.internal.util.UtilityFunctions$NullFunction */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static final class NullFunction<T0, T1, T2, T3, T4, T5, T6, T7, T8, T9, R> implements Func0<R>, Func1<T0, R>, Func2<T0, T1, R>, Func3<T0, T1, T2, R>, Func4<T0, T1, T2, T3, R>, Func5<T0, T1, T2, T3, T4, R>, Func6<T0, T1, T2, T3, T4, T5, R>, Func7<T0, T1, T2, T3, T4, T5, T6, R>, Func8<T0, T1, T2, T3, T4, T5, T6, T7, R>, Func9<T0, T1, T2, T3, T4, T5, T6, T7, T8, R>, FuncN<R> {
        @Override // p475rx.functions.Func0, java.util.concurrent.Callable
        public R call() {
            return null;
        }

        @Override // p475rx.functions.Func1
        public R call(T0 t0) {
            return null;
        }

        @Override // p475rx.functions.Func2
        public R call(T0 t0, T1 t1) {
            return null;
        }

        @Override // p475rx.functions.Func3
        public R call(T0 t0, T1 t1, T2 t2) {
            return null;
        }

        @Override // p475rx.functions.Func4
        public R call(T0 t0, T1 t1, T2 t2, T3 t3) {
            return null;
        }

        @Override // p475rx.functions.Func5
        public R call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4) {
            return null;
        }

        @Override // p475rx.functions.Func6
        public R call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
            return null;
        }

        @Override // p475rx.functions.Func7
        public R call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
            return null;
        }

        @Override // p475rx.functions.Func8
        public R call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
            return null;
        }

        @Override // p475rx.functions.Func9
        public R call(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
            return null;
        }

        @Override // p475rx.functions.FuncN
        public R call(Object... objArr) {
            return null;
        }

        NullFunction() {
        }
    }
}
