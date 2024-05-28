package org.aspectj.lang.reflect;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NoSuchPointcutException extends Exception {
    private static final long serialVersionUID = 3256444698657634352L;
    private String name;

    public NoSuchPointcutException(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
