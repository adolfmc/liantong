package io.objectbox.query;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.InternalAccess;
import io.objectbox.Property;
import io.objectbox.internal.CallWithHandle;
import io.objectbox.reactive.DataSubscriptionList;
import io.objectbox.reactive.SubscriptionBuilder;
import io.objectbox.relation.RelationInfo;
import io.objectbox.relation.ToOne;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Query<T> {
    final Box<T> box;
    private final Comparator<T> comparator;
    private final List<EagerRelation> eagerRelations;

    /* renamed from: filter  reason: collision with root package name */
    private final QueryFilter<T> f27867filter;
    long handle;
    private final boolean hasOrder;
    private final int initialRetryBackOffInMs = 10;
    private final QueryPublisher<T> publisher;
    private final int queryAttempts;
    private final BoxStore store;

    native long nativeCount(long j, long j2);

    native void nativeDestroy(long j);

    native List nativeFind(long j, long j2, long j3, long j4);

    native Object nativeFindFirst(long j, long j2);

    native long[] nativeFindKeysUnordered(long j, long j2);

    native Object nativeFindUnique(long j, long j2);

    native long nativeRemove(long j, long j2);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, double d);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, long j2);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, String str2);

    native void nativeSetParameter(long j, int i, int i2, @Nullable String str, byte[] bArr);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, double d, double d2);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, long j2, long j3);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, int[] iArr);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, long[] jArr);

    native void nativeSetParameters(long j, int i, int i2, @Nullable String str, String[] strArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Query(Box<T> box, long j, boolean z, List<EagerRelation> list, QueryFilter<T> queryFilter, Comparator<T> comparator) {
        this.box = box;
        this.store = box.getStore();
        this.queryAttempts = this.store.internalQueryAttempts();
        this.handle = j;
        this.hasOrder = z;
        this.publisher = new QueryPublisher<>(this, box);
        this.eagerRelations = list;
        this.f27867filter = queryFilter;
        this.comparator = comparator;
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public synchronized void close() {
        if (this.handle != 0) {
            nativeDestroy(this.handle);
            this.handle = 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long cursorHandle() {
        return InternalAccess.getActiveTxCursorHandle(this.box);
    }

    @Nullable
    public T findFirst() {
        ensureNoFilterNoComparator();
        return (T) callInReadTx((Callable<T>) new Callable<T>() { // from class: io.objectbox.query.Query.1
            @Override // java.util.concurrent.Callable
            public T call() {
                Query query = Query.this;
                T t = (T) query.nativeFindFirst(query.handle, Query.this.cursorHandle());
                Query.this.resolveEagerRelation(t);
                return t;
            }
        });
    }

    private void ensureNoFilterNoComparator() {
        if (this.f27867filter != null) {
            throw new UnsupportedOperationException("Does not yet work with a filter yet. At this point, only find() and forEach() are supported with filters.");
        }
        ensureNoComparator();
    }

    private void ensureNoComparator() {
        if (this.comparator != null) {
            throw new UnsupportedOperationException("Does not yet work with a sorting comparator yet. At this point, only find() is supported with sorting comparators.");
        }
    }

    @Nullable
    public T findUnique() {
        ensureNoFilterNoComparator();
        return (T) callInReadTx((Callable<T>) new Callable<T>() { // from class: io.objectbox.query.Query.2
            @Override // java.util.concurrent.Callable
            public T call() {
                Query query = Query.this;
                T t = (T) query.nativeFindUnique(query.handle, Query.this.cursorHandle());
                Query.this.resolveEagerRelation(t);
                return t;
            }
        });
    }

    @Nonnull
    public List<T> find() {
        return (List) callInReadTx((Callable<List<T>>) new Callable<List<T>>() { // from class: io.objectbox.query.Query.3
            @Override // java.util.concurrent.Callable
            public List<T> call() throws Exception {
                Query query = Query.this;
                List<T> nativeFind = query.nativeFind(query.handle, Query.this.cursorHandle(), 0L, 0L);
                if (Query.this.f27867filter != null) {
                    Iterator<T> it = nativeFind.iterator();
                    while (it.hasNext()) {
                        if (!Query.this.f27867filter.keep(it.next())) {
                            it.remove();
                        }
                    }
                }
                Query.this.resolveEagerRelations(nativeFind);
                if (Query.this.comparator != null) {
                    Collections.sort(nativeFind, Query.this.comparator);
                }
                return nativeFind;
            }
        });
    }

    @Nonnull
    public List<T> find(final long j, final long j2) {
        ensureNoFilterNoComparator();
        return (List) callInReadTx((Callable<List<T>>) new Callable<List<T>>() { // from class: io.objectbox.query.Query.4
            @Override // java.util.concurrent.Callable
            public List<T> call() {
                Query query = Query.this;
                List<T> nativeFind = query.nativeFind(query.handle, Query.this.cursorHandle(), j, j2);
                Query.this.resolveEagerRelations(nativeFind);
                return nativeFind;
            }
        });
    }

    @Nonnull
    public long[] findIds() {
        if (this.hasOrder) {
            throw new UnsupportedOperationException("This method is currently only available for unordered queries");
        }
        return (long[]) this.box.internalCallWithReaderHandle(new CallWithHandle<long[]>() { // from class: io.objectbox.query.Query.5
            @Override // io.objectbox.internal.CallWithHandle
            public long[] call(long j) {
                Query query = Query.this;
                return query.nativeFindKeysUnordered(query.handle, j);
            }
        });
    }

    public LazyList<T> findLazy() {
        ensureNoFilterNoComparator();
        return new LazyList<>(this.box, findIds(), false);
    }

    public PropertyQuery property(Property property) {
        return new PropertyQuery(this, property);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <R> R callInReadTx(Callable<R> callable) {
        return (R) this.store.callInReadTxWithRetry(callable, this.queryAttempts, 10, true);
    }

    public void forEach(final QueryConsumer<T> queryConsumer) {
        ensureNoComparator();
        this.box.getStore().runInReadTx(new Runnable() { // from class: io.objectbox.query.Query.6
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                LazyList lazyList = new LazyList(Query.this.box, Query.this.findIds(), false);
                int size = lazyList.size();
                for (int i = 0; i < size; i++) {
                    Object obj = lazyList.get(i);
                    if (obj != null) {
                        if (Query.this.f27867filter == null || Query.this.f27867filter.keep(obj)) {
                            if (Query.this.eagerRelations != null) {
                                Query.this.resolveEagerRelationForNonNullEagerRelations(obj, i);
                            }
                            try {
                                queryConsumer.accept(obj);
                            } catch (BreakForEach unused) {
                                return;
                            }
                        }
                    } else {
                        throw new IllegalStateException("Internal error: data object was null");
                    }
                }
            }
        });
    }

    @Nonnull
    public LazyList<T> findLazyCached() {
        ensureNoFilterNoComparator();
        return new LazyList<>(this.box, findIds(), true);
    }

    void resolveEagerRelations(List list) {
        if (this.eagerRelations != null) {
            int i = 0;
            for (Object obj : list) {
                resolveEagerRelationForNonNullEagerRelations(obj, i);
                i++;
            }
        }
    }

    void resolveEagerRelationForNonNullEagerRelations(@Nonnull Object obj, int i) {
        for (EagerRelation eagerRelation : this.eagerRelations) {
            if (eagerRelation.limit == 0 || i < eagerRelation.limit) {
                resolveEagerRelation(obj, eagerRelation);
            }
        }
    }

    void resolveEagerRelation(@Nullable Object obj) {
        List<EagerRelation> list = this.eagerRelations;
        if (list == null || obj == null) {
            return;
        }
        for (EagerRelation eagerRelation : list) {
            resolveEagerRelation(obj, eagerRelation);
        }
    }

    void resolveEagerRelation(@Nonnull Object obj, EagerRelation eagerRelation) {
        if (this.eagerRelations != null) {
            RelationInfo relationInfo = eagerRelation.relationInfo;
            if (relationInfo.toOneGetter != null) {
                ToOne toOne = relationInfo.toOneGetter.getToOne(obj);
                if (toOne != null) {
                    toOne.getTarget();
                }
            } else if (relationInfo.toManyGetter == null) {
                throw new IllegalStateException("Relation info without relation getter: " + relationInfo);
            } else {
                List toMany = relationInfo.toManyGetter.getToMany(obj);
                if (toMany != null) {
                    toMany.size();
                }
            }
        }
    }

    public long count() {
        return ((Long) this.box.internalCallWithReaderHandle(new CallWithHandle<Long>() { // from class: io.objectbox.query.Query.7
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.objectbox.internal.CallWithHandle
            public Long call(long j) {
                Query query = Query.this;
                return Long.valueOf(query.nativeCount(query.handle, j));
            }
        })).longValue();
    }

    public Query<T> setParameter(Property property, String str) {
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, str);
        return this;
    }

    public Query<T> setParameter(String str, String str2) {
        nativeSetParameter(this.handle, 0, 0, str, str2);
        return this;
    }

    public Query<T> setParameter(Property property, long j) {
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, j);
        return this;
    }

    public Query<T> setParameter(String str, long j) {
        nativeSetParameter(this.handle, 0, 0, str, j);
        return this;
    }

    public Query<T> setParameter(Property property, double d) {
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, d);
        return this;
    }

    public Query<T> setParameter(String str, double d) {
        nativeSetParameter(this.handle, 0, 0, str, d);
        return this;
    }

    public Query<T> setParameter(Property property, Date date) {
        return setParameter(property, date.getTime());
    }

    public Query<T> setParameter(String str, Date date) {
        return setParameter(str, date.getTime());
    }

    public Query<T> setParameter(Property property, boolean z) {
        return setParameter(property, z ? 1L : 0L);
    }

    public Query<T> setParameter(String str, boolean z) {
        return setParameter(str, z ? 1L : 0L);
    }

    public Query<T> setParameters(Property property, long j, long j2) {
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, j, j2);
        return this;
    }

    public Query<T> setParameters(String str, long j, long j2) {
        nativeSetParameters(this.handle, 0, 0, str, j, j2);
        return this;
    }

    public Query<T> setParameters(Property property, int[] iArr) {
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, iArr);
        return this;
    }

    public Query<T> setParameters(String str, int[] iArr) {
        nativeSetParameters(this.handle, 0, 0, str, iArr);
        return this;
    }

    public Query<T> setParameters(Property property, long[] jArr) {
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, jArr);
        return this;
    }

    public Query<T> setParameters(String str, long[] jArr) {
        nativeSetParameters(this.handle, 0, 0, str, jArr);
        return this;
    }

    public Query<T> setParameters(Property property, double d, double d2) {
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, d, d2);
        return this;
    }

    public Query<T> setParameters(String str, double d, double d2) {
        nativeSetParameters(this.handle, 0, 0, str, d, d2);
        return this;
    }

    public Query<T> setParameters(Property property, String[] strArr) {
        nativeSetParameters(this.handle, property.getEntityId(), property.getId(), (String) null, strArr);
        return this;
    }

    public Query<T> setParameters(String str, String[] strArr) {
        nativeSetParameters(this.handle, 0, 0, str, strArr);
        return this;
    }

    public Query<T> setParameter(Property property, byte[] bArr) {
        nativeSetParameter(this.handle, property.getEntityId(), property.getId(), (String) null, bArr);
        return this;
    }

    public Query<T> setParameter(String str, byte[] bArr) {
        nativeSetParameter(this.handle, 0, 0, str, bArr);
        return this;
    }

    public long remove() {
        return ((Long) this.box.internalCallWithWriterHandle(new CallWithHandle<Long>() { // from class: io.objectbox.query.Query.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.objectbox.internal.CallWithHandle
            public Long call(long j) {
                Query query = Query.this;
                return Long.valueOf(query.nativeRemove(query.handle, j));
            }
        })).longValue();
    }

    public SubscriptionBuilder<List<T>> subscribe() {
        return new SubscriptionBuilder<>(this.publisher, null, this.box.getStore().internalThreadPool());
    }

    public SubscriptionBuilder<List<T>> subscribe(DataSubscriptionList dataSubscriptionList) {
        SubscriptionBuilder<List<T>> subscribe = subscribe();
        subscribe.dataSubscriptionList(dataSubscriptionList);
        return subscribe;
    }

    public void publish() {
        this.publisher.publish();
    }
}
