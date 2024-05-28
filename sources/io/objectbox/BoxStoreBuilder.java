package io.objectbox;

import io.objectbox.annotation.apihint.Experimental;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.exception.DbException;
import io.objectbox.ideasonly.ModelUpdate;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.greenrobot.essentials.p468io.IoUtils;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BoxStoreBuilder {
    public static final int DEFAULT_MAX_DB_SIZE_KBYTE = 1048576;
    public static final String DEFAULT_NAME = "objectbox";

    /* renamed from: android  reason: collision with root package name */
    private boolean f27865android;
    private File baseDirectory;
    int debugFlags;
    boolean debugRelations;
    File directory;
    final List<EntityInfo> entityInfoList;
    TxCallback failedReadTxAttemptCallback;
    private Factory<InputStream> initialDbFileFactory;
    int maxReaders;
    long maxSizeInKByte;
    final byte[] model;
    ModelUpdate modelUpdate;
    private String name;
    int queryAttempts;

    private static String dbName(@Nullable String str) {
        return str != null ? str : DEFAULT_NAME;
    }

    public static BoxStoreBuilder createDebugWithoutModel() {
        return new BoxStoreBuilder();
    }

    private BoxStoreBuilder() {
        this.maxSizeInKByte = 1048576L;
        this.entityInfoList = new ArrayList();
        this.model = null;
    }

    @Internal
    public BoxStoreBuilder(byte[] bArr) {
        this.maxSizeInKByte = 1048576L;
        this.entityInfoList = new ArrayList();
        this.model = bArr;
        if (bArr == null) {
            throw new IllegalArgumentException("Model may not be null");
        }
    }

    public BoxStoreBuilder name(String str) {
        if (this.directory != null) {
            throw new IllegalArgumentException("Already has directory, cannot assign name");
        }
        if (str.contains("/") || str.contains("\\")) {
            throw new IllegalArgumentException("Name may not contain (back) slashes. Use baseDirectory() or directory() to configure alternative directories");
        }
        this.name = str;
        return this;
    }

    public BoxStoreBuilder directory(File file) {
        if (this.name != null) {
            throw new IllegalArgumentException("Already has name, cannot assign directory");
        }
        if (!this.f27865android && this.baseDirectory != null) {
            throw new IllegalArgumentException("Already has base directory, cannot assign directory");
        }
        this.directory = file;
        return this;
    }

    public BoxStoreBuilder baseDirectory(File file) {
        if (this.directory != null) {
            throw new IllegalArgumentException("Already has directory, cannot assign base directory");
        }
        this.baseDirectory = file;
        return this;
    }

    public BoxStoreBuilder androidContext(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Context may not be null");
        }
        File androidBaseDir = getAndroidBaseDir(obj);
        if (!androidBaseDir.exists()) {
            androidBaseDir.mkdir();
            if (!androidBaseDir.exists()) {
                throw new RuntimeException("Could not init Android base dir at " + androidBaseDir.getAbsolutePath());
            }
        }
        if (!androidBaseDir.isDirectory()) {
            throw new RuntimeException("Android base dir is not a dir: " + androidBaseDir.getAbsolutePath());
        }
        this.baseDirectory = androidBaseDir;
        this.f27865android = true;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File getAndroidDbDir(Object obj, @Nullable String str) {
        return new File(getAndroidBaseDir(obj), dbName(str));
    }

    static File getAndroidBaseDir(Object obj) {
        return new File(getAndroidFilesDir(obj), DEFAULT_NAME);
    }

    @Nonnull
    private static File getAndroidFilesDir(Object obj) {
        try {
            Method method = obj.getClass().getMethod("getFilesDir", new Class[0]);
            File file = (File) method.invoke(obj, new Object[0]);
            if (file == null) {
                System.err.println("getFilesDir() returned null - retrying once...");
                file = (File) method.invoke(obj, new Object[0]);
            }
            if (file == null) {
                throw new IllegalStateException("Android files dir is null");
            }
            if (file.exists()) {
                return file;
            }
            throw new IllegalStateException("Android files dir does not exist");
        } catch (Exception e) {
            throw new RuntimeException("Could not init with given Android context (must be sub class of android.content.Context)", e);
        }
    }

    public BoxStoreBuilder maxReaders(int i) {
        this.maxReaders = i;
        return this;
    }

    @Internal
    public void entity(EntityInfo entityInfo) {
        this.entityInfoList.add(entityInfo);
    }

    BoxStoreBuilder modelUpdate(ModelUpdate modelUpdate) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public BoxStoreBuilder maxSizeInKByte(long j) {
        this.maxSizeInKByte = j;
        return this;
    }

    @Deprecated
    public BoxStoreBuilder debugTransactions() {
        this.debugFlags |= 3;
        return this;
    }

    public BoxStoreBuilder debugFlags(int i) {
        this.debugFlags = i;
        return this;
    }

    public BoxStoreBuilder debugRelations() {
        this.debugRelations = true;
        return this;
    }

    @Experimental
    public BoxStoreBuilder queryAttempts(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Query attempts must >= 1");
        }
        this.queryAttempts = i;
        return this;
    }

    @Experimental
    public BoxStoreBuilder failedReadTxAttemptCallback(TxCallback txCallback) {
        this.failedReadTxAttemptCallback = txCallback;
        return this;
    }

    @Experimental
    public BoxStoreBuilder initialDbFile(final File file) {
        return initialDbFile(new Factory<InputStream>() { // from class: io.objectbox.BoxStoreBuilder.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.objectbox.Factory
            public InputStream provide() throws FileNotFoundException {
                return new FileInputStream(file);
            }
        });
    }

    @Experimental
    public BoxStoreBuilder initialDbFile(Factory<InputStream> factory) {
        this.initialDbFileFactory = factory;
        return this;
    }

    public BoxStore build() {
        if (this.directory == null) {
            this.name = dbName(this.name);
            this.directory = getDbDir(this.baseDirectory, this.name);
        }
        checkProvisionInitialDbFile();
        return new BoxStore(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.io.InputStream] */
    private void checkProvisionInitialDbFile() {
        InputStream inputStream;
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        BufferedOutputStream bufferedOutputStream3;
        if (this.initialDbFileFactory == null) {
            return;
        }
        File file = new File(BoxStore.getCanonicalPath(this.directory), "data.mdb");
        if (file.exists()) {
            return;
        }
        Closeable closeable = null;
        try {
            inputStream = this.initialDbFileFactory.provide();
            try {
                if (inputStream == null) {
                    throw new DbException("Factory did not provide a resource");
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(file));
                } catch (Exception e) {
                    e = e;
                    bufferedOutputStream2 = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    IoUtils.copyAllBytes(bufferedInputStream, bufferedOutputStream3);
                    IoUtils.safeClose(bufferedOutputStream3);
                    IoUtils.safeClose(bufferedInputStream);
                } catch (Exception e2) {
                    e = e2;
                    bufferedOutputStream2 = bufferedOutputStream3;
                    closeable = bufferedInputStream;
                    bufferedOutputStream = bufferedOutputStream2;
                    try {
                        throw new DbException("Could not provision initial data file", e);
                    } catch (Throwable th2) {
                        th = th2;
                        BufferedOutputStream bufferedOutputStream4 = bufferedOutputStream;
                        inputStream = closeable;
                        closeable = bufferedOutputStream4;
                        IoUtils.safeClose(closeable);
                        IoUtils.safeClose(inputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable = bufferedOutputStream3;
                    inputStream = bufferedInputStream;
                    IoUtils.safeClose(closeable);
                    IoUtils.safeClose(inputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                bufferedOutputStream = null;
                closeable = inputStream;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedOutputStream = null;
        } catch (Throwable th5) {
            th = th5;
            inputStream = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File getDbDir(@Nullable File file, @Nullable String str) {
        String dbName = dbName(str);
        if (file != null) {
            return new File(file, dbName);
        }
        return new File(dbName);
    }

    public BoxStore buildDefault() {
        BoxStore build = build();
        BoxStore.setDefault(build);
        return build;
    }
}
