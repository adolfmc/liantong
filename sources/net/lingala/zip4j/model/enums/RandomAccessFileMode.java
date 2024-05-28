package net.lingala.zip4j.model.enums;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum RandomAccessFileMode {
    READ("r"),
    WRITE("rw");
    
    private String value;

    RandomAccessFileMode(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
