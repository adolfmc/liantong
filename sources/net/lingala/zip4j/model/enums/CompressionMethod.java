package net.lingala.zip4j.model.enums;

import net.lingala.zip4j.exception.ZipException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum CompressionMethod {
    STORE(0),
    DEFLATE(8),
    AES_INTERNAL_ONLY(99);
    
    private int code;

    CompressionMethod(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public static CompressionMethod getCompressionMethodFromCode(int i) throws ZipException {
        CompressionMethod[] values;
        for (CompressionMethod compressionMethod : values()) {
            if (compressionMethod.getCode() == i) {
                return compressionMethod;
            }
        }
        throw new ZipException("Unknown compression method", ZipException.Type.UNKNOWN_COMPRESSION_METHOD);
    }
}
