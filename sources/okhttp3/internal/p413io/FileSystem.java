package okhttp3.internal.p413io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmPlatformAnnotations;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: FileSystem.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005H&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0004\u001a\u00020\u0005H&\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0015"}, m1890d2 = {"Lokhttp3/internal/io/FileSystem;", "", "appendingSink", "Lokio/Sink;", "file", "Ljava/io/File;", "delete", "", "deleteContents", "directory", "exists", "", "rename", "from", "to", "sink", "size", "", "source", "Lokio/Source;", "Companion", "okhttp"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: okhttp3.internal.io.FileSystem */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface FileSystem {
    public static final Companion Companion = new Companion(null);
    @JvmPlatformAnnotations
    @NotNull
    public static final FileSystem SYSTEM = new FileSystem() { // from class: okhttp3.internal.io.FileSystem$Companion$SYSTEM$1
        @Override // okhttp3.internal.p413io.FileSystem
        @NotNull
        public Source source(@NotNull File file) throws FileNotFoundException {
            Intrinsics.checkParameterIsNotNull(file, "file");
            return Okio.source(file);
        }

        @Override // okhttp3.internal.p413io.FileSystem
        @NotNull
        public Sink sink(@NotNull File file) throws FileNotFoundException {
            Intrinsics.checkParameterIsNotNull(file, "file");
            try {
                return Okio.sink$default(file, false, 1, null);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return Okio.sink$default(file, false, 1, null);
            }
        }

        @Override // okhttp3.internal.p413io.FileSystem
        @NotNull
        public Sink appendingSink(@NotNull File file) throws FileNotFoundException {
            Intrinsics.checkParameterIsNotNull(file, "file");
            try {
                return Okio.appendingSink(file);
            } catch (FileNotFoundException unused) {
                file.getParentFile().mkdirs();
                return Okio.appendingSink(file);
            }
        }

        @Override // okhttp3.internal.p413io.FileSystem
        public void delete(@NotNull File file) throws IOException {
            Intrinsics.checkParameterIsNotNull(file, "file");
            if (file.delete() || !file.exists()) {
                return;
            }
            throw new IOException("failed to delete " + file);
        }

        @Override // okhttp3.internal.p413io.FileSystem
        public boolean exists(@NotNull File file) {
            Intrinsics.checkParameterIsNotNull(file, "file");
            return file.exists();
        }

        @Override // okhttp3.internal.p413io.FileSystem
        public long size(@NotNull File file) {
            Intrinsics.checkParameterIsNotNull(file, "file");
            return file.length();
        }

        @Override // okhttp3.internal.p413io.FileSystem
        public void rename(@NotNull File from, @NotNull File to) throws IOException {
            Intrinsics.checkParameterIsNotNull(from, "from");
            Intrinsics.checkParameterIsNotNull(to, "to");
            delete(to);
            if (from.renameTo(to)) {
                return;
            }
            throw new IOException("failed to rename " + from + " to " + to);
        }

        @Override // okhttp3.internal.p413io.FileSystem
        public void deleteContents(@NotNull File directory) throws IOException {
            Intrinsics.checkParameterIsNotNull(directory, "directory");
            File[] listFiles = directory.listFiles();
            if (listFiles == null) {
                throw new IOException("not a readable directory: " + directory);
            }
            for (File file : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(file, "file");
                if (file.isDirectory()) {
                    deleteContents(file);
                }
                if (!file.delete()) {
                    throw new IOException("failed to delete " + file);
                }
            }
        }
    };

    @NotNull
    Sink appendingSink(@NotNull File file) throws FileNotFoundException;

    void delete(@NotNull File file) throws IOException;

    void deleteContents(@NotNull File file) throws IOException;

    boolean exists(@NotNull File file);

    void rename(@NotNull File file, @NotNull File file2) throws IOException;

    @NotNull
    Sink sink(@NotNull File file) throws FileNotFoundException;

    long size(@NotNull File file);

    @NotNull
    Source source(@NotNull File file) throws FileNotFoundException;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: FileSystem.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000¨\u0006\u0001\u0082\u0002\u0007\n\u0005\b\u0091F0\u0001¨\u0006\u0005"}, m1890d2 = {"Lokhttp3/internal/io/FileSystem$Companion;", "", "()V", "SYSTEM", "Lokhttp3/internal/io/FileSystem;", "okhttp"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* renamed from: okhttp3.internal.io.FileSystem$Companion */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
