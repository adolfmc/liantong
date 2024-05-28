package com.android.internal.http.multipart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class MultipartEntity extends AbstractHttpEntity {
    public static final String MULTIPART_BOUNDARY = "http.method.multipart.boundary";
    private static final String MULTIPART_FORM_CONTENT_TYPE = "multipart/form-data";
    private boolean contentConsumed = false;
    private byte[] multipartBoundary;
    private HttpParams params;
    protected Part[] parts;
    private static final InterfaceC13042Log log = LogFactory.getLog(MultipartEntity.class);
    private static byte[] MULTIPART_CHARS = EncodingUtils.getAsciiBytes("-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");

    private static byte[] generateMultipartBoundary() {
        Random random = new Random();
        int nextInt = random.nextInt(11) + 30;
        byte[] bArr = new byte[nextInt];
        for (int i = 0; i < nextInt; i++) {
            byte[] bArr2 = MULTIPART_CHARS;
            bArr[i] = bArr2[random.nextInt(bArr2.length)];
        }
        return bArr;
    }

    public MultipartEntity(Part[] partArr, HttpParams httpParams) {
        if (partArr == null) {
            throw new IllegalArgumentException("parts cannot be null");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("params cannot be null");
        }
        this.parts = partArr;
        this.params = httpParams;
    }

    public MultipartEntity(Part[] partArr) {
        setContentType("multipart/form-data");
        if (partArr == null) {
            throw new IllegalArgumentException("parts cannot be null");
        }
        this.parts = partArr;
        this.params = null;
    }

    protected byte[] getMultipartBoundary() {
        if (this.multipartBoundary == null) {
            String str = null;
            HttpParams httpParams = this.params;
            if (httpParams != null) {
                str = (String) httpParams.getParameter("http.method.multipart.boundary");
            }
            if (str != null) {
                this.multipartBoundary = EncodingUtils.getAsciiBytes(str);
            } else {
                this.multipartBoundary = generateMultipartBoundary();
            }
        }
        return this.multipartBoundary;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        int i = 0;
        while (true) {
            Part[] partArr = this.parts;
            if (i < partArr.length) {
                if (!partArr[i].isRepeatable()) {
                    return false;
                }
                i++;
            } else {
                return true;
            }
        }
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        Part.sendParts(outputStream, this.parts, getMultipartBoundary());
    }

    @Override // org.apache.http.entity.AbstractHttpEntity, org.apache.http.HttpEntity
    public Header getContentType() {
        StringBuffer stringBuffer = new StringBuffer("multipart/form-data");
        stringBuffer.append("; boundary=");
        stringBuffer.append(EncodingUtils.getAsciiString(getMultipartBoundary()));
        return new BasicHeader("Content-Type", stringBuffer.toString());
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        try {
            return Part.getLengthOfParts(this.parts, getMultipartBoundary());
        } catch (Exception e) {
            log.error("An exception occurred while getting the length of the parts", e);
            return 0L;
        }
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        if (!isRepeatable() && this.contentConsumed) {
            throw new IllegalStateException("Content has been consumed");
        }
        this.contentConsumed = true;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Part.sendParts(byteArrayOutputStream, this.parts, this.multipartBoundary);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return false;
    }
}
