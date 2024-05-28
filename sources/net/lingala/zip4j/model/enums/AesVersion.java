package net.lingala.zip4j.model.enums;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum AesVersion {
    ONE(1),
    TWO(2);
    
    private int versionNumber;

    AesVersion(int i) {
        this.versionNumber = i;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public static AesVersion getFromVersionNumber(int i) {
        AesVersion[] values;
        for (AesVersion aesVersion : values()) {
            if (aesVersion.versionNumber == i) {
                return aesVersion;
            }
        }
        throw new IllegalArgumentException("Unsupported Aes version");
    }
}
