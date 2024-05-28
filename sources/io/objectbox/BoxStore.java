package io.objectbox;

import io.objectbox.annotation.apihint.Experimental;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.converter.PropertyConverter;
import io.objectbox.exception.DbException;
import io.objectbox.exception.DbExceptionListener;
import io.objectbox.exception.DbSchemaException;
import io.objectbox.internal.NativeLibraryLoader;
import io.objectbox.internal.ObjectBoxThreadPool;
import io.objectbox.reactive.SubscriptionBuilder;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import org.greenrobot.essentials.collections.LongHashMap;

@ThreadSafe
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BoxStore implements Closeable {
    private static final String VERSION = "2.2.0-2018-09-27";
    private static BoxStore defaultStore;
    private static final Set<String> openFiles = new HashSet();
    private final int[] allEntityTypeIds;
    private final String canonicalPath;
    private boolean closed;
    volatile int commitCount;
    final boolean debugRelations;
    final boolean debugTxRead;
    final boolean debugTxWrite;
    private final File directory;
    private final TxCallback failedReadTxAttemptCallback;
    private final long handle;
    private int objectBrowserPort;
    private final ObjectClassPublisher objectClassPublisher;
    private final int queryAttempts;
    private final Map<Class, String> dbNameByClass = new HashMap();
    private final Map<Class, Integer> entityTypeIdByClass = new HashMap();
    private final Map<Class, EntityInfo> propertiesByClass = new HashMap();
    private final LongHashMap<Class> classByEntityTypeId = new LongHashMap<>();
    private final Map<Class, Box> boxes = new ConcurrentHashMap();
    private final Set<Transaction> transactions = Collections.newSetFromMap(new WeakHashMap());
    private final ExecutorService threadPool = new ObjectBoxThreadPool(this);
    final ThreadLocal<Transaction> activeTx = new ThreadLocal<>();
    final Object txCommitCountLock = new Object();

    public static String getVersion() {
        return VERSION;
    }

    static native long nativeBeginReadTx(long j);

    static native long nativeBeginTx(long j);

    static native int nativeCleanStaleReadTransactions(long j);

    static native long nativeCreate(String str, long j, int i, byte[] bArr);

    static native void nativeDelete(long j);

    static native String nativeDiagnose(long j);

    static native void nativeDropAllData(long j);

    static native String nativeGetVersion();

    static native boolean nativeIsObjectBrowserAvailable();

    static native void nativeRegisterCustomType(long j, int i, int i2, String str, Class<? extends PropertyConverter> cls, Class cls2);

    static native int nativeRegisterEntityClass(long j, String str, Class cls);

    static native void nativeSetDbExceptionListener(long j, DbExceptionListener dbExceptionListener);

    static native void nativeSetDebugFlags(long j, int i);

    static native String nativeStartObjectBrowser(long j, @Nullable String str, int i);

    public static native void testUnalignedMemoryAccess();

    native long nativePanicModeRemoveAllObjects(long j, int i);

    public static synchronized BoxStore getDefault() {
        BoxStore boxStore;
        synchronized (BoxStore.class) {
            if (defaultStore == null) {
                throw new IllegalStateException("Please call buildDefault() before calling this method");
            }
            boxStore = defaultStore;
        }
        return boxStore;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setDefault(BoxStore boxStore) {
        synchronized (BoxStore.class) {
            if (defaultStore != null) {
                throw new IllegalStateException("Default store was already built before. ");
            }
            defaultStore = boxStore;
        }
    }

    public static synchronized boolean clearDefaultStore() {
        boolean z;
        synchronized (BoxStore.class) {
            z = defaultStore != null;
            defaultStore = null;
        }
        return z;
    }

    public static String getVersionNative() {
        NativeLibraryLoader.ensureLoaded();
        return nativeGetVersion();
    }

    public static boolean isObjectBrowserAvailable() {
        NativeLibraryLoader.ensureLoaded();
        return nativeIsObjectBrowserAvailable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoxStore(BoxStoreBuilder boxStoreBuilder) {
        Property[] allProperties;
        NativeLibraryLoader.ensureLoaded();
        this.directory = boxStoreBuilder.directory;
        this.canonicalPath = getCanonicalPath(this.directory);
        verifyNotAlreadyOpen(this.canonicalPath);
        this.handle = nativeCreate(this.canonicalPath, boxStoreBuilder.maxSizeInKByte, boxStoreBuilder.maxReaders, boxStoreBuilder.model);
        int i = boxStoreBuilder.debugFlags;
        if (i != 0) {
            nativeSetDebugFlags(this.handle, i);
            this.debugTxRead = (i & 1) != 0;
            this.debugTxWrite = (i & 2) != 0;
        } else {
            this.debugTxWrite = false;
            this.debugTxRead = false;
        }
        this.debugRelations = boxStoreBuilder.debugRelations;
        for (EntityInfo entityInfo : boxStoreBuilder.entityInfoList) {
            try {
                this.dbNameByClass.put(entityInfo.getEntityClass(), entityInfo.getDbName());
                int nativeRegisterEntityClass = nativeRegisterEntityClass(this.handle, entityInfo.getDbName(), entityInfo.getEntityClass());
                this.entityTypeIdByClass.put(entityInfo.getEntityClass(), Integer.valueOf(nativeRegisterEntityClass));
                this.classByEntityTypeId.put(nativeRegisterEntityClass, entityInfo.getEntityClass());
                this.propertiesByClass.put(entityInfo.getEntityClass(), entityInfo);
                for (Property property : entityInfo.getAllProperties()) {
                    if (property.customType != null) {
                        if (property.converterClass == null) {
                            throw new RuntimeException("No converter class for custom type of " + property);
                        }
                        nativeRegisterCustomType(this.handle, nativeRegisterEntityClass, 0, property.dbName, property.converterClass, property.customType);
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException("Could not setup up entity " + entityInfo.getEntityClass(), e);
            }
        }
        int size = this.classByEntityTypeId.size();
        this.allEntityTypeIds = new int[size];
        long[] keys = this.classByEntityTypeId.keys();
        for (int i2 = 0; i2 < size; i2++) {
            this.allEntityTypeIds[i2] = (int) keys[i2];
        }
        this.objectClassPublisher = new ObjectClassPublisher(this);
        this.failedReadTxAttemptCallback = boxStoreBuilder.failedReadTxAttemptCallback;
        this.queryAttempts = boxStoreBuilder.queryAttempts >= 1 ? boxStoreBuilder.queryAttempts : 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getCanonicalPath(File file) {
        if (file.exists()) {
            if (!file.isDirectory()) {
                throw new DbException("Is not a directory: " + file.getAbsolutePath());
            }
        } else if (!file.mkdirs()) {
            throw new DbException("Could not create directory: " + file.getAbsolutePath());
        }
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            throw new DbException("Could not verify dir", e);
        }
    }

    private static void verifyNotAlreadyOpen(String str) {
        synchronized (openFiles) {
            isFileOpen(str);
            if (!openFiles.add(str)) {
                throw new DbException("Another BoxStore is still open for this directory: " + str + ". Hint: for most apps it's recommended to keep a BoxStore for the app's life time.");
            }
        }
    }

    private static boolean isFileOpen(String str) {
        boolean contains;
        synchronized (openFiles) {
            int i = 0;
            while (i < 5) {
                if (!openFiles.contains(str)) {
                    break;
                }
                i++;
                System.gc();
                System.runFinalization();
                System.gc();
                System.runFinalization();
                try {
                    openFiles.wait(100L);
                } catch (InterruptedException unused) {
                }
            }
            contains = openFiles.contains(str);
        }
        return contains;
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    private void checkOpen() {
        if (this.closed) {
            throw new IllegalStateException("Store is closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getDbName(Class cls) {
        return this.dbNameByClass.get(cls);
    }

    Integer getEntityTypeId(Class cls) {
        return this.entityTypeIdByClass.get(cls);
    }

    @Internal
    public int getEntityTypeIdOrThrow(Class cls) {
        Integer num = this.entityTypeIdByClass.get(cls);
        if (num == null) {
            throw new DbSchemaException("No entity registered for " + cls);
        }
        return num.intValue();
    }

    public Collection<Class> getAllEntityClasses() {
        return this.dbNameByClass.keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public int[] getAllEntityTypeIds() {
        return this.allEntityTypeIds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public Class getEntityClassOrThrow(int i) {
        Class cls = this.classByEntityTypeId.get(i);
        if (cls != null) {
            return cls;
        }
        throw new DbSchemaException("No entity registered for type ID " + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public EntityInfo getEntityInfo(Class cls) {
        return this.propertiesByClass.get(cls);
    }

    @Internal
    public Transaction beginTx() {
        checkOpen();
        int i = this.commitCount;
        if (this.debugTxWrite) {
            PrintStream printStream = System.out;
            printStream.println("Begin TX with commit count " + i);
        }
        Transaction transaction = new Transaction(this, nativeBeginTx(this.handle), i);
        synchronized (this.transactions) {
            this.transactions.add(transaction);
        }
        return transaction;
    }

    @Internal
    public Transaction beginReadTx() {
        checkOpen();
        int i = this.commitCount;
        if (this.debugTxRead) {
            PrintStream printStream = System.out;
            printStream.println("Begin read TX with commit count " + i);
        }
        Transaction transaction = new Transaction(this, nativeBeginReadTx(this.handle), i);
        synchronized (this.transactions) {
            this.transactions.add(transaction);
        }
        return transaction;
    }

    public boolean isClosed() {
        return this.closed;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        boolean z;
        ArrayList<Transaction> arrayList;
        synchronized (this) {
            z = this.closed;
            if (!this.closed) {
                this.closed = true;
                synchronized (this.transactions) {
                    arrayList = new ArrayList(this.transactions);
                }
                for (Transaction transaction : arrayList) {
                    transaction.close();
                }
                if (this.handle != 0) {
                    nativeDelete(this.handle);
                }
                this.threadPool.shutdown();
                checkThreadTermination();
            }
        }
        if (z) {
            return;
        }
        synchronized (openFiles) {
            openFiles.remove(this.canonicalPath);
            openFiles.notifyAll();
        }
    }

    private void checkThreadTermination() {
        try {
            if (this.threadPool.awaitTermination(1L, TimeUnit.SECONDS)) {
                return;
            }
            int activeCount = Thread.activeCount();
            System.err.println("Thread pool not terminated in time; printing stack traces...");
            Thread[] threadArr = new Thread[activeCount + 2];
            int enumerate = Thread.enumerate(threadArr);
            for (int i = 0; i < enumerate; i++) {
                PrintStream printStream = System.err;
                printStream.println("Thread: " + threadArr[i].getName());
                Thread.dumpStack();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAllFiles() {
        if (!this.closed) {
            throw new IllegalStateException("Store must be closed");
        }
        return deleteAllFiles(this.directory);
    }

    public static boolean deleteAllFiles(File file) {
        if (file.exists()) {
            if (isFileOpen(getCanonicalPath(file))) {
                throw new IllegalStateException("Cannot delete files: store is still open");
            }
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return false;
            }
            for (File file2 : listFiles) {
                if (!file2.delete() && file2.exists()) {
                    return false;
                }
            }
            return file.delete();
        }
        return true;
    }

    public static boolean deleteAllFiles(Object obj, @Nullable String str) {
        return deleteAllFiles(BoxStoreBuilder.getAndroidDbDir(obj, str));
    }

    public static boolean deleteAllFiles(@Nullable File file, @Nullable String str) {
        return deleteAllFiles(BoxStoreBuilder.getDbDir(file, str));
    }

    @Internal
    public void unregisterTransaction(Transaction transaction) {
        synchronized (this.transactions) {
            this.transactions.remove(transaction);
        }
    }

    void dropAllData() {
        nativeDropAllData(this.handle);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void txCommitted(Transaction transaction, @Nullable int[] iArr) {
        synchronized (this.txCommitCountLock) {
            this.commitCount++;
            if (this.debugTxWrite) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("TX committed. New commit count: ");
                sb.append(this.commitCount);
                sb.append(", entity types affected: ");
                sb.append(iArr != null ? iArr.length : 0);
                printStream.println(sb.toString());
            }
        }
        for (Box box : this.boxes.values()) {
            box.txCommitted(transaction);
        }
        if (iArr != null) {
            this.objectClassPublisher.publish(iArr);
        }
    }

    public <T> Box<T> boxFor(Class<T> cls) {
        Box<T> box;
        Box<T> box2 = this.boxes.get(cls);
        if (box2 == null) {
            if (!this.dbNameByClass.containsKey(cls)) {
                throw new IllegalArgumentException(cls + " is not a known entity. Please add it and trigger generation again.");
            }
            synchronized (this.boxes) {
                box = this.boxes.get(cls);
                if (box == null) {
                    box = new Box<>(this, cls);
                    this.boxes.put(cls, box);
                }
            }
            return box;
        }
        return box2;
    }

    public void runInTx(Runnable runnable) {
        Transaction transaction = this.activeTx.get();
        if (transaction == null) {
            Transaction beginTx = beginTx();
            this.activeTx.set(beginTx);
            try {
                runnable.run();
                beginTx.commit();
            } finally {
                this.activeTx.remove();
                beginTx.close();
            }
        } else if (transaction.isReadOnly()) {
            throw new IllegalStateException("Cannot start a transaction while a read only transaction is active");
        } else {
            runnable.run();
        }
    }

    public void runInReadTx(Runnable runnable) {
        if (this.activeTx.get() == null) {
            Transaction beginReadTx = beginReadTx();
            this.activeTx.set(beginReadTx);
            try {
                runnable.run();
                return;
            } finally {
                this.activeTx.remove();
                for (Box next : this.boxes.values()) {
                    next.readTxFinished(beginReadTx);
                }
                beginReadTx.close();
            }
        }
        runnable.run();
    }

    @Experimental
    public <T> T callInReadTxWithRetry(Callable<T> callable, int i, int i2, boolean z) {
        if (i == 1) {
            return (T) callInReadTx(callable);
        }
        if (i < 1) {
            throw new IllegalArgumentException("Illegal value of attempts: " + i);
        }
        long j = i2;
        DbException e = null;
        for (int i3 = 1; i3 <= i; i3++) {
            try {
                return (T) callInReadTx(callable);
            } catch (DbException e2) {
                e = e2;
                String diagnose = diagnose();
                String str = i3 + " of " + i + " attempts of calling a read TX failed:";
                if (z) {
                    System.err.println(str);
                    e.printStackTrace();
                    System.err.println(diagnose);
                    System.err.flush();
                    System.gc();
                    System.runFinalization();
                    cleanStaleReadTransactions();
                }
                TxCallback txCallback = this.failedReadTxAttemptCallback;
                if (txCallback != null) {
                    txCallback.txFinished(null, new DbException(str + " \n" + diagnose, e));
                }
                try {
                    Thread.sleep(j);
                    j *= 2;
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                    throw e;
                }
            }
        }
        throw e;
    }

    public <T> T callInReadTx(Callable<T> callable) {
        if (this.activeTx.get() == null) {
            Transaction beginReadTx = beginReadTx();
            this.activeTx.set(beginReadTx);
            try {
                try {
                    return callable.call();
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException("Callable threw exception", e2);
                }
            } finally {
                this.activeTx.remove();
                for (Box next : this.boxes.values()) {
                    next.readTxFinished(beginReadTx);
                }
                beginReadTx.close();
            }
        }
        try {
            return callable.call();
        } catch (Exception e3) {
            throw new RuntimeException("Callable threw exception", e3);
        }
    }

    public <R> R callInTx(Callable<R> callable) throws Exception {
        Transaction transaction = this.activeTx.get();
        if (transaction == null) {
            Transaction beginTx = beginTx();
            this.activeTx.set(beginTx);
            try {
                R call = callable.call();
                beginTx.commit();
                return call;
            } finally {
                this.activeTx.remove();
                beginTx.close();
            }
        } else if (transaction.isReadOnly()) {
            throw new IllegalStateException("Cannot start a transaction while a read only transaction is active");
        } else {
            return callable.call();
        }
    }

    public <R> R callInTxNoException(Callable<R> callable) {
        try {
            return (R) callInTx(callable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runInTxAsync(final Runnable runnable, @Nullable final TxCallback<Void> txCallback) {
        this.threadPool.submit(new Runnable() { // from class: io.objectbox.BoxStore.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BoxStore.this.runInTx(runnable);
                    if (txCallback != null) {
                        txCallback.txFinished(null, null);
                    }
                } catch (Throwable th) {
                    TxCallback txCallback2 = txCallback;
                    if (txCallback2 != null) {
                        txCallback2.txFinished(null, th);
                    }
                }
            }
        });
    }

    public <R> void callInTxAsync(final Callable<R> callable, @Nullable final TxCallback<R> txCallback) {
        this.threadPool.submit(new Runnable() { // from class: io.objectbox.BoxStore.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Object callInTx = BoxStore.this.callInTx(callable);
                    if (txCallback != null) {
                        txCallback.txFinished(callInTx, null);
                    }
                } catch (Throwable th) {
                    TxCallback txCallback2 = txCallback;
                    if (txCallback2 != null) {
                        txCallback2.txFinished(null, th);
                    }
                }
            }
        });
    }

    public String diagnose() {
        return nativeDiagnose(this.handle);
    }

    public int cleanStaleReadTransactions() {
        return nativeCleanStaleReadTransactions(this.handle);
    }

    public void closeThreadResources() {
        for (Box box : this.boxes.values()) {
            box.closeThreadResources();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Internal
    public long internalHandle() {
        return this.handle;
    }

    public SubscriptionBuilder<Class> subscribe() {
        return new SubscriptionBuilder<>(this.objectClassPublisher, null, this.threadPool);
    }

    @Experimental
    @Nullable
    public String startObjectBrowser() {
        String startObjectBrowser;
        verifyObjectBrowserNotRunning();
        for (int i = 8090; i < 8100; i++) {
            try {
                startObjectBrowser = startObjectBrowser(i);
            } catch (DbException e) {
                if (e.getMessage() == null || !e.getMessage().contains("port")) {
                    throw e;
                }
            }
            if (startObjectBrowser != null) {
                return startObjectBrowser;
            }
        }
        return null;
    }

    @Experimental
    @Nullable
    public String startObjectBrowser(int i) {
        verifyObjectBrowserNotRunning();
        String nativeStartObjectBrowser = nativeStartObjectBrowser(this.handle, null, i);
        if (nativeStartObjectBrowser != null) {
            this.objectBrowserPort = i;
        }
        return nativeStartObjectBrowser;
    }

    @Experimental
    public int getObjectBrowserPort() {
        return this.objectBrowserPort;
    }

    private void verifyObjectBrowserNotRunning() {
        if (this.objectBrowserPort == 0) {
            return;
        }
        throw new DbException("ObjectBrowser is already running at port " + this.objectBrowserPort);
    }

    public void setDbExceptionListener(DbExceptionListener dbExceptionListener) {
        nativeSetDbExceptionListener(this.handle, dbExceptionListener);
    }

    public <T> SubscriptionBuilder<Class<T>> subscribe(Class<T> cls) {
        return new SubscriptionBuilder<>(this.objectClassPublisher, cls, this.threadPool);
    }

    @Internal
    public Future internalScheduleThread(Runnable runnable) {
        return this.threadPool.submit(runnable);
    }

    @Internal
    public ExecutorService internalThreadPool() {
        return this.threadPool;
    }

    @Internal
    public boolean isDebugRelations() {
        return this.debugRelations;
    }

    @Internal
    public int internalQueryAttempts() {
        return this.queryAttempts;
    }

    @Internal
    public TxCallback internalFailedReadTxAttemptCallback() {
        return this.failedReadTxAttemptCallback;
    }

    void setDebugFlags(int i) {
        nativeSetDebugFlags(this.handle, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long panicModeRemoveAllObjects(int i) {
        return nativePanicModeRemoveAllObjects(this.handle, i);
    }
}
