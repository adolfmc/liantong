package com.liulishuo.okdownload.core.file;

import android.content.Context;
import android.net.Uri;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface DownloadOutputStream {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Factory {
        DownloadOutputStream create(Context context, Uri uri, int i) throws FileNotFoundException;

        DownloadOutputStream create(Context context, File file, int i) throws FileNotFoundException;

        boolean supportSeek();
    }

    void close() throws IOException;

    void flushAndSync() throws IOException;

    void seek(long j) throws IOException;

    void setLength(long j) throws IOException;

    void write(byte[] bArr, int i, int i2) throws IOException;
}
