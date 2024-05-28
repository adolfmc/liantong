package net.lingala.zip4j.headers;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum VersionMadeBy {
    SPECIFICATION_VERSION((byte) 51),
    WINDOWS((byte) 0),
    UNIX((byte) 3);
    
    private byte code;

    VersionMadeBy(byte b) {
        this.code = b;
    }

    public byte getCode() {
        return this.code;
    }
}
