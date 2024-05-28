package net.lingala.zip4j.headers;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum VersionNeededToExtract {
    DEFAULT(10),
    DEFLATE_COMPRESSED(20),
    ZIP_64_FORMAT(45),
    AES_ENCRYPTED(51);
    
    private int code;

    VersionNeededToExtract(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
