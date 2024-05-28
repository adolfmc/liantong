package org.apache.http.entity.mime;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.content.ContentBody;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MultipartEntity implements HttpEntity {
    private static final char[] MULTIPART_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private final MultipartEntityBuilder builder;
    private volatile MultipartFormEntity entity;

    public MultipartEntity(HttpMultipartMode httpMultipartMode, String str, Charset charset) {
        this.builder = new MultipartEntityBuilder().setMode(httpMultipartMode).setCharset(charset).setBoundary(str);
        this.entity = null;
    }

    public MultipartEntity(HttpMultipartMode httpMultipartMode) {
        this(httpMultipartMode, null, null);
    }

    public MultipartEntity() {
        this(HttpMultipartMode.STRICT, null, null);
    }

    protected String generateContentType(String str, Charset charset) {
        StringBuilder sb = new StringBuilder();
        sb.append("multipart/form-data; boundary=");
        sb.append(str);
        if (charset != null) {
            sb.append("; charset=");
            sb.append(charset.name());
        }
        return sb.toString();
    }

    protected String generateBoundary() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int nextInt = random.nextInt(11) + 30;
        for (int i = 0; i < nextInt; i++) {
            char[] cArr = MULTIPART_CHARS;
            sb.append(cArr[random.nextInt(cArr.length)]);
        }
        return sb.toString();
    }

    private MultipartFormEntity getEntity() {
        if (this.entity == null) {
            this.entity = this.builder.buildEntity();
        }
        return this.entity;
    }

    public void addPart(FormBodyPart formBodyPart) {
        this.builder.addPart(formBodyPart);
        this.entity = null;
    }

    public void addPart(String str, ContentBody contentBody) {
        addPart(new FormBodyPart(str, contentBody));
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return getEntity().isRepeatable();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isChunked() {
        return getEntity().isChunked();
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return getEntity().isStreaming();
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        return getEntity().getContentLength();
    }

    @Override // org.apache.http.HttpEntity
    public org.apache.http.Header getContentType() {
        return getEntity().getContentType();
    }

    @Override // org.apache.http.HttpEntity
    public org.apache.http.Header getContentEncoding() {
        return getEntity().getContentEncoding();
    }

    @Override // org.apache.http.HttpEntity
    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Multipart form entity does not implement #getContent()");
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        getEntity().writeTo(outputStream);
    }
}
