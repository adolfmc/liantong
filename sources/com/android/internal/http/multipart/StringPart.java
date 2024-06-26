package com.android.internal.http.multipart;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.logging.InterfaceC13042Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.EncodingUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class StringPart extends PartBase {
    public static final String DEFAULT_CHARSET = "US-ASCII";
    public static final String DEFAULT_CONTENT_TYPE = "text/plain";
    public static final String DEFAULT_TRANSFER_ENCODING = "8bit";
    private static final InterfaceC13042Log LOG = LogFactory.getLog(StringPart.class);
    private byte[] content;
    private String value;

    public StringPart(String str, String str2, String str3) {
        super(str, "text/plain", str3 == null ? "US-ASCII" : str3, "8bit");
        if (str2 == null) {
            throw new IllegalArgumentException("Value may not be null");
        }
        if (str2.indexOf(0) != -1) {
            throw new IllegalArgumentException("NULs may not be present in string parts");
        }
        this.value = str2;
    }

    public StringPart(String str, String str2) {
        this(str, str2, null);
    }

    private byte[] getContent() {
        if (this.content == null) {
            this.content = EncodingUtils.getBytes(this.value, getCharSet());
        }
        return this.content;
    }

    @Override // com.android.internal.http.multipart.Part
    protected void sendData(OutputStream outputStream) throws IOException {
        LOG.trace("enter sendData(OutputStream)");
        outputStream.write(getContent());
    }

    @Override // com.android.internal.http.multipart.Part
    protected long lengthOfData() {
        LOG.trace("enter lengthOfData()");
        return getContent().length;
    }

    @Override // com.android.internal.http.multipart.PartBase
    public void setCharSet(String str) {
        super.setCharSet(str);
        this.content = null;
    }
}
