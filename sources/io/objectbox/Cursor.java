package io.objectbox;

import io.objectbox.annotation.apihint.Beta;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.annotation.apihint.Temporary;
import io.objectbox.relation.ToMany;
import java.io.Closeable;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@Beta
@Internal
@NotThreadSafe
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class Cursor<T> implements Closeable {
    @Internal
    static boolean LOG_READ_NOT_CLOSED = false;
    protected static final int PUT_FLAG_COMPLETE = 2;
    protected static final int PUT_FLAG_FIRST = 1;
    @Internal
    static boolean TRACK_CREATION_STACK;
    protected final BoxStore boxStoreForEntities;
    protected boolean closed;
    private final Throwable creationThrowable;
    protected final long cursor;
    protected final EntityInfo entityInfo;
    protected final boolean readOnly;

    /* renamed from: tx */
    protected final Transaction f24386tx;

    protected static native long collect002033(long j, long j2, int i, int i2, long j3, int i3, long j4, int i4, float f, int i5, float f2, int i6, float f3, int i7, double d, int i8, double d2, int i9, double d3);

    /* JADX INFO: Access modifiers changed from: protected */
    public static native long collect004000(long j, long j2, int i, int i2, long j3, int i3, long j4, int i4, long j5, int i5, long j6);

    /* JADX INFO: Access modifiers changed from: protected */
    public static native long collect313311(long j, long j2, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, @Nullable String str3, int i5, @Nullable byte[] bArr, int i6, long j3, int i7, long j4, int i8, long j5, int i9, int i10, int i11, int i12, int i13, int i14, int i15, float f, int i16, double d);

    /* JADX INFO: Access modifiers changed from: protected */
    public static native long collect400000(long j, long j2, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, @Nullable String str3, int i5, @Nullable String str4);

    protected static native long collect430000(long j, long j2, int i, int i2, @Nullable String str, int i3, @Nullable String str2, int i4, @Nullable String str3, int i5, @Nullable String str4, int i6, @Nullable byte[] bArr, int i7, @Nullable byte[] bArr2, int i8, @Nullable byte[] bArr3);

    static native long nativeCount(long j, long j2);

    static native void nativeDeleteAll(long j);

    static native void nativeDeleteEntity(long j, long j2);

    static native void nativeDestroy(long j);

    static native List nativeFindScalarPropertyId(long j, int i, long j2);

    static native List nativeFindStringPropertyId(long j, int i, String str);

    static native Object nativeFirstEntity(long j);

    static native Object nativeGetAllEntities(long j);

    static native List nativeGetBacklinkEntities(long j, int i, int i2, long j2);

    static native Object nativeGetEntity(long j, long j2);

    static native long nativeGetKey(long j);

    static native List nativeGetRelationEntities(long j, int i, int i2, long j2, boolean z);

    static native long nativeLookupKeyUsingIndex(long j, int i, String str);

    static native void nativeModifyRelations(long j, int i, long j2, long[] jArr, boolean z);

    static native void nativeModifyRelationsSingle(long j, int i, long j2, long j3, boolean z);

    static native Object nativeNextEntity(long j);

    static native int nativePropertyId(long j, String str);

    static native long nativeRenew(long j);

    static native boolean nativeSeek(long j, long j2);

    static native void nativeSetBoxStoreForEntities(long j, Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract long getId(T t);

    public abstract long put(T t);

    /* JADX INFO: Access modifiers changed from: protected */
    public Cursor(Transaction transaction, long j, EntityInfo entityInfo, BoxStore boxStore) {
        Property<T>[] allProperties;
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction is null");
        }
        this.f24386tx = transaction;
        this.readOnly = transaction.isReadOnly();
        this.cursor = j;
        this.entityInfo = entityInfo;
        this.boxStoreForEntities = boxStore;
        for (Property<T> property : entityInfo.getAllProperties()) {
            if (!property.isIdVerified()) {
                property.verifyId(getPropertyId(property.dbName));
            }
        }
        this.creationThrowable = TRACK_CREATION_STACK ? new Throwable() : null;
        nativeSetBoxStoreForEntities(j, boxStore);
    }

    protected void finalize() throws Throwable {
        if (this.closed) {
            return;
        }
        if (!this.readOnly || LOG_READ_NOT_CLOSED) {
            System.err.println("Cursor was not closed.");
            if (this.creationThrowable != null) {
                System.err.println("Cursor was initially created here:");
                this.creationThrowable.printStackTrace();
            }
            System.err.flush();
        }
        close();
        super.finalize();
    }

    public EntityInfo getEntityInfo() {
        return this.entityInfo;
    }

    public T get(long j) {
        return (T) nativeGetEntity(this.cursor, j);
    }

    public T next() {
        return (T) nativeNextEntity(this.cursor);
    }

    public T first() {
        return (T) nativeFirstEntity(this.cursor);
    }

    public List<T> getAll() {
        return (List) nativeGetAllEntities(this.cursor);
    }

    public void deleteEntity(long j) {
        nativeDeleteEntity(this.cursor, j);
    }

    public void deleteAll() {
        nativeDeleteAll(this.cursor);
    }

    public long getKey() {
        return nativeGetKey(this.cursor);
    }

    public boolean seek(long j) {
        return nativeSeek(this.cursor, j);
    }

    public long count(long j) {
        return nativeCount(this.cursor, j);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.closed) {
            this.closed = true;
            if (this.f24386tx != null && !this.f24386tx.getStore().isClosed()) {
                nativeDestroy(this.cursor);
            }
        }
    }

    public int getPropertyId(String str) {
        return nativePropertyId(this.cursor, str);
    }

    @Temporary
    public List<T> find(Property property, long j) {
        return nativeFindScalarPropertyId(this.cursor, property.f24389id, j);
    }

    @Temporary
    public List<T> find(Property property, String str) {
        return nativeFindStringPropertyId(this.cursor, property.f24389id, str);
    }

    long lookupKeyUsingIndex(int i, String str) {
        return nativeLookupKeyUsingIndex(this.cursor, i, str);
    }

    public Transaction getTx() {
        return this.f24386tx;
    }

    public boolean isObsolete() {
        return this.f24386tx.isObsolete();
    }

    public boolean isClosed() {
        return this.closed;
    }

    protected <TARGET> Cursor<TARGET> getRelationTargetCursor(Class<TARGET> cls) {
        return this.f24386tx.createCursor(cls);
    }

    public void renew() {
        nativeRenew(this.cursor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public long internalHandle() {
        return this.cursor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public List<T> getBacklinkEntities(int i, Property property, long j) {
        try {
            return nativeGetBacklinkEntities(this.cursor, i, property.getId(), j);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please check if the given property belongs to a valid @Relation: " + property, e);
        }
    }

    @Internal
    public List<T> getRelationEntities(int i, int i2, long j, boolean z) {
        return nativeGetRelationEntities(this.cursor, i, i2, j, z);
    }

    @Internal
    public void modifyRelations(int i, long j, long[] jArr, boolean z) {
        nativeModifyRelations(this.cursor, i, j, jArr, z);
    }

    @Internal
    public void modifyRelationsSingle(int i, long j, long j2, boolean z) {
        nativeModifyRelationsSingle(this.cursor, i, j, j2, z);
    }

    protected <TARGET> void checkApplyToManyToDb(List<TARGET> list, Class<TARGET> cls) {
        if (list instanceof ToMany) {
            ToMany toMany = (ToMany) list;
            if (toMany.internalCheckApplyToDbRequired()) {
                Cursor<TARGET> relationTargetCursor = getRelationTargetCursor(cls);
                try {
                    toMany.internalApplyToDb(this, relationTargetCursor);
                } finally {
                    relationTargetCursor.close();
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cursor ");
        sb.append(Long.toString(this.cursor, 16));
        sb.append(isClosed() ? "(closed)" : "");
        return sb.toString();
    }
}
