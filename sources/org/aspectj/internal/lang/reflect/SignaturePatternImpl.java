package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.SignaturePattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SignaturePatternImpl implements SignaturePattern {
    private String sigPattern;

    public SignaturePatternImpl(String str) {
        this.sigPattern = str;
    }

    @Override // org.aspectj.lang.reflect.SignaturePattern
    public String asString() {
        return this.sigPattern;
    }

    public String toString() {
        return asString();
    }
}
