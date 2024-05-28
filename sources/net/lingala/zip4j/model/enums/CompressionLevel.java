package net.lingala.zip4j.model.enums;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum CompressionLevel {
    FASTEST(1),
    FAST(3),
    NORMAL(5),
    MAXIMUM(7),
    ULTRA(9);
    
    private int level;

    CompressionLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }
}
