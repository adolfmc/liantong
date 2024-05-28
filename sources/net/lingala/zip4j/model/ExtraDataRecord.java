package net.lingala.zip4j.model;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ExtraDataRecord extends ZipHeader {
    private byte[] data;
    private long header;
    private int sizeOfData;

    public long getHeader() {
        return this.header;
    }

    public void setHeader(long j) {
        this.header = j;
    }

    public int getSizeOfData() {
        return this.sizeOfData;
    }

    public void setSizeOfData(int i) {
        this.sizeOfData = i;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] bArr) {
        this.data = bArr;
    }
}
