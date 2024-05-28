package com.android.internal.http.multipart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.EncodingUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class Part {
    protected static final String BOUNDARY = "----------------314159265358979323846";
    protected static final byte[] BOUNDARY_BYTES;
    protected static final String CHARSET = "; charset=";
    protected static final byte[] CHARSET_BYTES;
    protected static final String CONTENT_DISPOSITION = "Content-Disposition: form-data; name=";
    protected static final byte[] CONTENT_DISPOSITION_BYTES;
    protected static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding: ";
    protected static final byte[] CONTENT_TRANSFER_ENCODING_BYTES;
    protected static final String CONTENT_TYPE = "Content-Type: ";
    protected static final byte[] CONTENT_TYPE_BYTES;
    protected static final String CRLF = "\r\n";
    protected static final byte[] CRLF_BYTES;
    private static final byte[] DEFAULT_BOUNDARY_BYTES;
    protected static final String EXTRA = "--";
    protected static final byte[] EXTRA_BYTES;
    private static final InterfaceC13042Log LOG = LogFactory.getLog(Part.class);
    protected static final String QUOTE = "\"";
    protected static final byte[] QUOTE_BYTES;
    private byte[] boundaryBytes;

    public abstract String getCharSet();

    public abstract String getContentType();

    public abstract String getName();

    public abstract String getTransferEncoding();

    protected abstract long lengthOfData() throws IOException;

    protected abstract void sendData(OutputStream outputStream) throws IOException;

    static {
        byte[] asciiBytes = EncodingUtils.getAsciiBytes("----------------314159265358979323846");
        BOUNDARY_BYTES = asciiBytes;
        DEFAULT_BOUNDARY_BYTES = asciiBytes;
        CRLF_BYTES = EncodingUtils.getAsciiBytes("\r\n");
        QUOTE_BYTES = EncodingUtils.getAsciiBytes("\"");
        EXTRA_BYTES = EncodingUtils.getAsciiBytes("--");
        CONTENT_DISPOSITION_BYTES = EncodingUtils.getAsciiBytes("Content-Disposition: form-data; name=");
        CONTENT_TYPE_BYTES = EncodingUtils.getAsciiBytes("Content-Type: ");
        CHARSET_BYTES = EncodingUtils.getAsciiBytes("; charset=");
        CONTENT_TRANSFER_ENCODING_BYTES = EncodingUtils.getAsciiBytes("Content-Transfer-Encoding: ");
    }

    public static String getBoundary() {
        return "----------------314159265358979323846";
    }

    protected byte[] getPartBoundary() {
        byte[] bArr = this.boundaryBytes;
        if (bArr == null) {
            return DEFAULT_BOUNDARY_BYTES;
        }
        return bArr;
    }

    void setPartBoundary(byte[] bArr) {
        this.boundaryBytes = bArr;
    }

    public boolean isRepeatable() {
        return true;
    }

    protected void sendStart(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendStart(OutputStream out)");
        outputStream.write(EXTRA_BYTES);
        outputStream.write(getPartBoundary());
        outputStream.write(CRLF_BYTES);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sendDispositionHeader(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendDispositionHeader(OutputStream out)");
        outputStream.write(CONTENT_DISPOSITION_BYTES);
        byte[] bArr = QUOTE_BYTES;
        outputStream.write(bArr);
        outputStream.write(EncodingUtils.getAsciiBytes(getName()));
        outputStream.write(bArr);
    }

    protected void sendContentTypeHeader(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendContentTypeHeader(OutputStream out)");
        String contentType = getContentType();
        if (contentType != null) {
            outputStream.write(CRLF_BYTES);
            outputStream.write(CONTENT_TYPE_BYTES);
            outputStream.write(EncodingUtils.getAsciiBytes(contentType));
            String charSet = getCharSet();
            if (charSet != null) {
                outputStream.write(CHARSET_BYTES);
                outputStream.write(EncodingUtils.getAsciiBytes(charSet));
            }
        }
    }

    protected void sendTransferEncodingHeader(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendTransferEncodingHeader(OutputStream out)");
        String transferEncoding = getTransferEncoding();
        if (transferEncoding != null) {
            outputStream.write(CRLF_BYTES);
            outputStream.write(CONTENT_TRANSFER_ENCODING_BYTES);
            outputStream.write(EncodingUtils.getAsciiBytes(transferEncoding));
        }
    }

    protected void sendEndOfHeader(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendEndOfHeader(OutputStream out)");
        byte[] bArr = CRLF_BYTES;
        outputStream.write(bArr);
        outputStream.write(bArr);
    }

    protected void sendEnd(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendEnd(OutputStream out)");
        outputStream.write(CRLF_BYTES);
    }

    public void send(OutputStream outputStream) throws IOException {
        LOG.trace("enter send(OutputStream out)");
        sendStart(outputStream);
        sendDispositionHeader(outputStream);
        sendContentTypeHeader(outputStream);
        sendTransferEncodingHeader(outputStream);
        sendEndOfHeader(outputStream);
        sendData(outputStream);
        sendEnd(outputStream);
    }

    public long length() throws IOException {
        LOG.trace("enter length()");
        if (lengthOfData() < 0) {
            return -1L;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        sendStart(byteArrayOutputStream);
        sendDispositionHeader(byteArrayOutputStream);
        sendContentTypeHeader(byteArrayOutputStream);
        sendTransferEncodingHeader(byteArrayOutputStream);
        sendEndOfHeader(byteArrayOutputStream);
        sendEnd(byteArrayOutputStream);
        return byteArrayOutputStream.size() + lengthOfData();
    }

    public String toString() {
        return getName();
    }

    public static void sendParts(OutputStream outputStream, Part[] partArr) throws IOException {
        sendParts(outputStream, partArr, DEFAULT_BOUNDARY_BYTES);
    }

    public static void sendParts(OutputStream outputStream, Part[] partArr, byte[] bArr) throws IOException {
        if (partArr == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        if (bArr == null || bArr.length == 0) {
            throw new IllegalArgumentException("partBoundary may not be empty");
        }
        for (int i = 0; i < partArr.length; i++) {
            partArr[i].setPartBoundary(bArr);
            partArr[i].send(outputStream);
        }
        byte[] bArr2 = EXTRA_BYTES;
        outputStream.write(bArr2);
        outputStream.write(bArr);
        outputStream.write(bArr2);
        outputStream.write(CRLF_BYTES);
    }

    public static long getLengthOfParts(Part[] partArr) throws IOException {
        return getLengthOfParts(partArr, DEFAULT_BOUNDARY_BYTES);
    }

    public static long getLengthOfParts(Part[] partArr, byte[] bArr) throws IOException {
        LOG.trace("getLengthOfParts(Parts[])");
        if (partArr == null) {
            throw new IllegalArgumentException("Parts may not be null");
        }
        long j = 0;
        for (int i = 0; i < partArr.length; i++) {
            partArr[i].setPartBoundary(bArr);
            long length = partArr[i].length();
            if (length < 0) {
                return -1L;
            }
            j += length;
        }
        byte[] bArr2 = EXTRA_BYTES;
        return j + bArr2.length + bArr.length + bArr2.length + CRLF_BYTES.length;
    }
}
