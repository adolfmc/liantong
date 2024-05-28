package com.p319ss.android.socialbase.downloader.reader;

import com.p319ss.android.socialbase.downloader.segment.Buffer;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.ss.android.socialbase.downloader.reader.SyncStreamReader */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SyncStreamReader implements IStreamReader {
    private final Buffer buffer;
    private final InputStream inputStream;

    @Override // com.p319ss.android.socialbase.downloader.reader.IStreamReader
    public void recycle(Buffer buffer) {
    }

    public SyncStreamReader(InputStream inputStream, int i) {
        this.inputStream = inputStream;
        this.buffer = new Buffer(i);
    }

    @Override // com.p319ss.android.socialbase.downloader.reader.IStreamReader
    public Buffer read() throws IOException {
        Buffer buffer = this.buffer;
        buffer.size = this.inputStream.read(buffer.data);
        return this.buffer;
    }

    @Override // com.p319ss.android.socialbase.downloader.reader.IStreamReader
    public void close() {
        DownloadUtils.safeClose(this.inputStream);
    }
}
