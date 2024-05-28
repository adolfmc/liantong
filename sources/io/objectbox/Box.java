package io.objectbox;

import io.objectbox.annotation.apihint.Beta;
import io.objectbox.annotation.apihint.Experimental;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.annotation.apihint.Temporary;
import io.objectbox.exception.DbException;
import io.objectbox.internal.CallWithHandle;
import io.objectbox.internal.IdGetter;
import io.objectbox.internal.ReflectionCache;
import io.objectbox.query.QueryBuilder;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@Beta
@ThreadSafe
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Box<T> {
    private volatile Field boxStoreField;
    private final Class<T> entityClass;
    private EntityInfo entityInfo;
    private final IdGetter<T> idGetter;
    private final BoxStore store;
    final ThreadLocal<Cursor<T>> activeTxCursor = new ThreadLocal<>();
    private final ThreadLocal<Cursor<T>> threadLocalReader = new ThreadLocal<>();

    private boolean isChanged(T t) {
        return false;
    }

    private boolean isEmpty() {
        return false;
    }

    private boolean putIfChanged(T t) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Box(BoxStore boxStore, Class<T> cls) {
        this.store = boxStore;
        this.entityClass = cls;
        this.idGetter = boxStore.getEntityInfo(cls).getIdGetter();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor<T> getReader() {
        Cursor<T> activeTxCursor = getActiveTxCursor();
        if (activeTxCursor != null) {
            return activeTxCursor;
        }
        Cursor<T> cursor = this.threadLocalReader.get();
        if (cursor != null) {
            Transaction transaction = cursor.f24386tx;
            if (transaction.isClosed() || !transaction.isRecycled()) {
                throw new IllegalStateException("Illegal reader TX state");
            }
            transaction.renew();
            cursor.renew();
            return cursor;
        }
        Cursor<T> createCursor = this.store.beginReadTx().createCursor(this.entityClass);
        this.threadLocalReader.set(createCursor);
        return createCursor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor<T> getActiveTxCursor() {
        Transaction transaction = this.store.activeTx.get();
        if (transaction != null) {
            if (transaction.isClosed()) {
                throw new IllegalStateException("Active TX is closed");
            }
            Cursor<T> cursor = this.activeTxCursor.get();
            if (cursor == null || cursor.getTx().isClosed()) {
                Cursor<T> createCursor = transaction.createCursor(this.entityClass);
                this.activeTxCursor.set(createCursor);
                return createCursor;
            }
            return cursor;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cursor<T> getWriter() {
        Cursor<T> activeTxCursor = getActiveTxCursor();
        if (activeTxCursor != null) {
            return activeTxCursor;
        }
        Transaction beginTx = this.store.beginTx();
        try {
            return beginTx.createCursor(this.entityClass);
        } catch (RuntimeException e) {
            beginTx.close();
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commitWriter(Cursor<T> cursor) {
        if (this.activeTxCursor.get() == null) {
            cursor.close();
            cursor.getTx().commitAndClose();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseWriter(Cursor<T> cursor) {
        if (this.activeTxCursor.get() == null) {
            Transaction tx = cursor.getTx();
            if (tx.isClosed()) {
                return;
            }
            cursor.close();
            tx.abort();
            tx.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseReader(Cursor<T> cursor) {
        if (this.activeTxCursor.get() == null) {
            Transaction tx = cursor.getTx();
            if (tx.isClosed() || tx.isRecycled() || !tx.isReadOnly()) {
                throw new IllegalStateException("Illegal reader TX state");
            }
            tx.recycle();
        }
    }

    public void closeThreadResources() {
        Cursor<T> cursor = this.threadLocalReader.get();
        if (cursor != null) {
            cursor.close();
            this.threadLocalReader.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void txCommitted(Transaction transaction) {
        Cursor<T> cursor = this.activeTxCursor.get();
        if (cursor != null) {
            this.activeTxCursor.remove();
            cursor.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void readTxFinished(Transaction transaction) {
        Cursor<T> cursor = this.activeTxCursor.get();
        if (cursor == null || cursor.getTx() != transaction) {
            return;
        }
        this.activeTxCursor.remove();
        cursor.close();
    }

    int getPropertyId(String str) {
        Cursor<T> reader = getReader();
        try {
            return reader.getPropertyId(str);
        } finally {
            releaseReader(reader);
        }
    }

    @Internal
    public long getId(T t) {
        return this.idGetter.getId(t);
    }

    public T get(long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.get(j);
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> get(Iterable<Long> iterable) {
        ArrayList arrayList = new ArrayList();
        Cursor<T> reader = getReader();
        try {
            for (Long l : iterable) {
                T t = reader.get(l.longValue());
                if (t != null) {
                    arrayList.add(t);
                }
            }
            return arrayList;
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> get(long[] jArr) {
        ArrayList arrayList = new ArrayList(jArr.length);
        Cursor<T> reader = getReader();
        try {
            for (long j : jArr) {
                T t = reader.get(Long.valueOf(j).longValue());
                if (t != null) {
                    arrayList.add(t);
                }
            }
            return arrayList;
        } finally {
            releaseReader(reader);
        }
    }

    public Map<Long, T> getMap(Iterable<Long> iterable) {
        HashMap hashMap = new HashMap();
        Cursor<T> reader = getReader();
        try {
            for (Long l : iterable) {
                hashMap.put(l, reader.get(l.longValue()));
            }
            return hashMap;
        } finally {
            releaseReader(reader);
        }
    }

    public long count() {
        return count(0L);
    }

    public long count(long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.count(j);
        } finally {
            releaseReader(reader);
        }
    }

    @Temporary
    public List<T> find(Property property, String str) {
        Cursor<T> reader = getReader();
        try {
            return reader.find(property, str);
        } finally {
            releaseReader(reader);
        }
    }

    @Temporary
    public List<T> find(Property property, long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.find(property, j);
        } finally {
            releaseReader(reader);
        }
    }

    public List<T> getAll() {
        Cursor<T> reader = getReader();
        try {
            T first = reader.first();
            if (first == null) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(first);
            while (true) {
                T next = reader.next();
                if (next == null) {
                    return arrayList;
                }
                arrayList.add(next);
            }
        } finally {
            releaseReader(reader);
        }
    }

    @Temporary
    public List<T> getAll2() {
        Cursor<T> reader = getReader();
        try {
            return reader.getAll();
        } finally {
            releaseReader(reader);
        }
    }

    public long put(T t) {
        Cursor<T> writer = getWriter();
        try {
            long put = writer.put(t);
            commitWriter(writer);
            return put;
        } finally {
            releaseWriter(writer);
        }
    }

    public void put(@Nullable T... tArr) {
        if (tArr == null || tArr.length == 0) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (T t : tArr) {
                writer.put(t);
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void put(@Nullable Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (T t : collection) {
                writer.put(t);
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(long j) {
        Cursor<T> writer = getWriter();
        try {
            writer.deleteEntity(j);
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(@Nullable long... jArr) {
        if (jArr == null || jArr.length == 0) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (long j : jArr) {
                writer.deleteEntity(j);
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void removeByKeys(@Nullable Collection<Long> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (Long l : collection) {
                writer.deleteEntity(l.longValue());
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(T t) {
        Cursor<T> writer = getWriter();
        try {
            writer.deleteEntity(writer.getId(t));
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(@Nullable T... tArr) {
        if (tArr == null || tArr.length == 0) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (T t : tArr) {
                writer.deleteEntity(writer.getId(t));
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void remove(@Nullable Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return;
        }
        Cursor<T> writer = getWriter();
        try {
            for (T t : collection) {
                writer.deleteEntity(writer.getId(t));
            }
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    public void removeAll() {
        Cursor<T> writer = getWriter();
        try {
            writer.deleteAll();
            commitWriter(writer);
        } finally {
            releaseWriter(writer);
        }
    }

    @Experimental
    public long panicModeRemoveAll() {
        return this.store.panicModeRemoveAllObjects(getEntityInfo().getEntityId());
    }

    public QueryBuilder<T> query() {
        return new QueryBuilder<>(this, this.store.internalHandle(), this.store.getDbName(this.entityClass));
    }

    public BoxStore getStore() {
        return this.store;
    }

    public synchronized EntityInfo getEntityInfo() {
        if (this.entityInfo == null) {
            Cursor<T> reader = getReader();
            this.entityInfo = reader.getEntityInfo();
            releaseReader(reader);
        }
        return this.entityInfo;
    }

    @Beta
    public void attach(T t) {
        if (this.boxStoreField == null) {
            try {
                this.boxStoreField = ReflectionCache.getInstance().getField(this.entityClass, "__boxStore");
            } catch (Exception e) {
                throw new DbException("Entity cannot be attached - only active entities with relationships support attaching (class has no __boxStore field(?)) : " + this.entityClass, e);
            }
        }
        try {
            this.boxStoreField.set(t, this.store);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    public Class<T> getEntityClass() {
        return this.entityClass;
    }

    @Internal
    public List<T> internalGetBacklinkEntities(int i, Property property, long j) {
        Cursor<T> reader = getReader();
        try {
            return reader.getBacklinkEntities(i, property, j);
        } finally {
            releaseReader(reader);
        }
    }

    @Internal
    public List<T> internalGetRelationEntities(int i, int i2, long j, boolean z) {
        Cursor<T> reader = getReader();
        try {
            return reader.getRelationEntities(i, i2, j, z);
        } finally {
            releaseReader(reader);
        }
    }

    @Internal
    public <RESULT> RESULT internalCallWithReaderHandle(CallWithHandle<RESULT> callWithHandle) {
        Cursor<T> reader = getReader();
        try {
            return callWithHandle.call(reader.internalHandle());
        } finally {
            releaseReader(reader);
        }
    }

    @Internal
    public <RESULT> RESULT internalCallWithWriterHandle(CallWithHandle<RESULT> callWithHandle) {
        Cursor<T> writer = getWriter();
        try {
            RESULT call = callWithHandle.call(writer.internalHandle());
            commitWriter(writer);
            return call;
        } finally {
            releaseWriter(writer);
        }
    }

    public String getReaderDebugInfo() {
        Cursor<T> reader = getReader();
        try {
            return reader + " with " + reader.getTx() + "; store's commit count: " + getStore().commitCount;
        } finally {
            releaseReader(reader);
        }
    }
}
