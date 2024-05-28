package org.apache.http.entity;

import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractHttpEntity implements HttpEntity {
    protected boolean chunked;
    protected Header contentEncoding;
    protected Header contentType;

    @Override // org.apache.http.HttpEntity
    public Header getContentType() {
        return this.contentType;
    }

    @Override // org.apache.http.HttpEntity
    public Header getContentEncoding() {
        return this.contentEncoding;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return this.chunked;
    }

    public void setContentType(Header header) {
        this.contentType = header;
    }

    public void setContentType(String str) {
        BasicHeader basicHeader;
        if (str == null) {
            basicHeader = null;
        } else {
            basicHeader = new BasicHeader("Content-Type", str);
        }
        setContentType(basicHeader);
    }

    public void setContentEncoding(Header header) {
        this.contentEncoding = header;
    }

    public void setContentEncoding(String str) {
        BasicHeader basicHeader;
        if (str == null) {
            basicHeader = null;
        } else {
            basicHeader = new BasicHeader("Content-Encoding", str);
        }
        setContentEncoding(basicHeader);
    }

    public void setChunked(boolean z) {
        this.chunked = z;
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("streaming entity does not implement consumeContent()");
        }
    }
}
