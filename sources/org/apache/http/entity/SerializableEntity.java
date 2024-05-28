package org.apache.http.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class SerializableEntity extends AbstractHttpEntity {
    private Serializable objRef;
    private byte[] objSer;

    public SerializableEntity(Serializable serializable, boolean z) throws IOException {
        if (serializable == null) {
            throw new IllegalArgumentException("Source object may not be null");
        }
        if (z) {
            createBytes(serializable);
        } else {
            this.objRef = serializable;
        }
    }

    private void createBytes(Serializable serializable) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.flush();
        this.objSer = byteArrayOutputStream.toByteArray();
    }

    @Override // org.apache.http.HttpEntity
    public InputStream getContent() throws IOException, IllegalStateException {
        if (this.objSer == null) {
            createBytes(this.objRef);
        }
        return new ByteArrayInputStream(this.objSer);
    }

    @Override // org.apache.http.HttpEntity
    public long getContentLength() {
        byte[] bArr = this.objSer;
        if (bArr == null) {
            return -1L;
        }
        return bArr.length;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isRepeatable() {
        return true;
    }

    @Override // org.apache.http.HttpEntity
    public boolean isStreaming() {
        return this.objSer == null;
    }

    @Override // org.apache.http.HttpEntity
    public void writeTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("Output stream may not be null");
        }
        byte[] bArr = this.objSer;
        if (bArr == null) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(this.objRef);
            objectOutputStream.flush();
            return;
        }
        outputStream.write(bArr);
        outputStream.flush();
    }
}
