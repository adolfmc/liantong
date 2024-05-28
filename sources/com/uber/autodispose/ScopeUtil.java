package com.uber.autodispose;

import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.concurrent.Callable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ScopeUtil {
    private static final Function<Object, LifecycleEndNotification> TRANSFORM_TO_END = new Function<Object, LifecycleEndNotification>() { // from class: com.uber.autodispose.ScopeUtil.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.reactivex.functions.Function
        public LifecycleEndNotification apply(Object obj) throws Exception {
            return LifecycleEndNotification.INSTANCE;
        }
    };
    private static final Predicate<Boolean> IDENTITY_BOOLEAN_PREDICATE = new Predicate<Boolean>() { // from class: com.uber.autodispose.ScopeUtil.2
        @Override // io.reactivex.functions.Predicate
        public boolean test(Boolean bool) throws Exception {
            return bool.booleanValue();
        }
    };

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum LifecycleEndNotification {
        INSTANCE
    }

    private ScopeUtil() {
        throw new InstantiationError();
    }

    public static <E> Maybe<LifecycleEndNotification> deferredResolvedLifecycle(LifecycleScopeProvider<E> lifecycleScopeProvider) {
        return deferredResolvedLifecycle(lifecycleScopeProvider, true, true);
    }

    public static <E> Maybe<LifecycleEndNotification> deferredResolvedLifecycle(final LifecycleScopeProvider<E> lifecycleScopeProvider, final boolean z, final boolean z2) {
        return Maybe.defer(new Callable<MaybeSource<? extends LifecycleEndNotification>>() { // from class: com.uber.autodispose.ScopeUtil.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public MaybeSource<? extends LifecycleEndNotification> call() throws Exception {
                Object peekLifecycle = LifecycleScopeProvider.this.peekLifecycle();
                if (z && peekLifecycle == null) {
                    LifecycleNotStartedException lifecycleNotStartedException = new LifecycleNotStartedException();
                    Consumer<? super OutsideLifecycleException> outsideLifecycleHandler = AutoDisposePlugins.getOutsideLifecycleHandler();
                    if (outsideLifecycleHandler != null) {
                        outsideLifecycleHandler.accept(lifecycleNotStartedException);
                        return Maybe.just(LifecycleEndNotification.INSTANCE);
                    }
                    throw lifecycleNotStartedException;
                }
                try {
                    return ScopeUtil.resolveScopeFromLifecycle(LifecycleScopeProvider.this.lifecycle(), LifecycleScopeProvider.this.correspondingEvents().apply(peekLifecycle));
                } catch (Exception e) {
                    if (z2 && (e instanceof LifecycleEndedException)) {
                        Consumer<? super OutsideLifecycleException> outsideLifecycleHandler2 = AutoDisposePlugins.getOutsideLifecycleHandler();
                        if (outsideLifecycleHandler2 != null) {
                            outsideLifecycleHandler2.accept((LifecycleEndedException) e);
                            return Maybe.just(LifecycleEndNotification.INSTANCE);
                        }
                        throw e;
                    }
                    return Maybe.error(e);
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Maybe<LifecycleEndNotification> resolveScopeFromLifecycle(Observable<E> observable, final E e) {
        Function function;
        if (e instanceof Comparable) {
            function = (Function<Comparable<E>, Boolean>) new Function<Comparable<E>, Boolean>() { // from class: com.uber.autodispose.ScopeUtil.4
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Boolean apply(Object obj) throws Exception {
                    return apply((Comparable) ((Comparable) obj));
                }

                /* JADX WARN: Multi-variable type inference failed */
                public Boolean apply(Comparable<E> comparable) {
                    return Boolean.valueOf(comparable.compareTo(e) >= 0);
                }
            };
        } else {
            function = (Function<E, Boolean>) new Function<E, Boolean>() { // from class: com.uber.autodispose.ScopeUtil.5
                /* JADX WARN: Multi-variable type inference failed */
                @Override // io.reactivex.functions.Function
                public /* bridge */ /* synthetic */ Boolean apply(Object obj) throws Exception {
                    return apply((C105225<E>) obj);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // io.reactivex.functions.Function
                public Boolean apply(E e2) {
                    return Boolean.valueOf(e2.equals(e));
                }
            };
        }
        return observable.skip(1L).map(function).filter(IDENTITY_BOOLEAN_PREDICATE).map(TRANSFORM_TO_END).firstElement();
    }
}
