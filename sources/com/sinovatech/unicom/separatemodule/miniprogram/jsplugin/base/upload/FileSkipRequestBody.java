package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FileSkipRequestBody extends RequestBody {
    private final long bytesCount;
    private final File file;
    private final MediaType mediaType;
    private final long skipSize;

    public FileSkipRequestBody(File file, long j, long j2, @Nullable MediaType mediaType) {
        if (j < 0) {
            throw new IllegalArgumentException("skipSize >= 0 required but it was " + j);
        }
        long j3 = j + j2;
        if (j3 > file.length()) {
            throw new IllegalArgumentException("skipSize+bytesCount cannot be larger than the file length. The file length is " + file.length() + ", but it was " + j3);
        }
        this.file = file;
        this.skipSize = j;
        this.bytesCount = j2;
        this.mediaType = mediaType;
    }

    public FileSkipRequestBody(File file, @Nullable MediaType mediaType) {
        this.file = file;
        this.skipSize = 0L;
        this.bytesCount = file.length();
        this.mediaType = mediaType;
    }

    @Override // okhttp3.RequestBody
    @Nullable
    public MediaType contentType() {
        return this.mediaType;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.bytesCount;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.file);
        long skip = fileInputStream.skip(this.skipSize);
        if (skip != this.skipSize) {
            throw new IllegalArgumentException("Expected to skip " + this.skipSize + " bytes, actually skipped " + skip + " bytes");
        }
        bufferedSink.write(Okio.source(fileInputStream), this.bytesCount);
    }
}
