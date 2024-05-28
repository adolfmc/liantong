package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ProgressRequestBody extends RequestBody {
    private final ProgressCallback callback;
    private final RequestBody requestBody;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ProgressCallback {
        void onProgress(long j, long j2);
    }

    public ProgressRequestBody(RequestBody requestBody, ProgressCallback progressCallback) {
        this.requestBody = requestBody;
        this.callback = progressCallback;
    }

    public RequestBody getRequestBody() {
        return this.requestBody;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.requestBody.contentType();
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.requestBody.contentLength();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if ((bufferedSink instanceof Buffer) || bufferedSink.toString().contains("com.android.tools.profiler.support.network.HttpTracker$OutputStreamTracker")) {
            this.requestBody.writeTo(bufferedSink);
            return;
        }
        BufferedSink buffer = Okio.buffer(sink(bufferedSink));
        this.requestBody.writeTo(buffer);
        buffer.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.upload.ProgressRequestBody.1
            long bytesWritten = 0;
            long contentLength = 0;

            @Override // okio.ForwardingSink, okio.Sink
            public void write(Buffer buffer, long j) throws IOException {
                super.write(buffer, j);
                if (this.contentLength == 0) {
                    this.contentLength = ProgressRequestBody.this.contentLength();
                }
                this.bytesWritten += j;
                if (ProgressRequestBody.this.callback != null) {
                    ProgressRequestBody.this.callback.onProgress(this.bytesWritten, this.contentLength);
                }
            }
        };
    }
}
