package com.p319ss.android.socialbase.downloader.reader;

import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.segment.Buffer;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.reader.IStreamReader */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IStreamReader {
    void close();

    Buffer read() throws IOException, BaseException, InterruptedException;

    void recycle(Buffer buffer);
}
