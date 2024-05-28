package net.lingala.zip4j.model;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class DataDescriptor extends ZipHeader {
    private long compressedSize;
    private long crc;
    private long uncompressedSize;

    public long getCrc() {
        return this.crc;
    }

    public void setCrc(long j) {
        this.crc = j;
    }

    public long getCompressedSize() {
        return this.compressedSize;
    }

    public void setCompressedSize(long j) {
        this.compressedSize = j;
    }

    public long getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void setUncompressedSize(long j) {
        this.uncompressedSize = j;
    }
}
