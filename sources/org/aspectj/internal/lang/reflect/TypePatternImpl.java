package org.aspectj.internal.lang.reflect;

import org.aspectj.lang.reflect.TypePattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TypePatternImpl implements TypePattern {
    private String typePattern;

    public TypePatternImpl(String str) {
        this.typePattern = str;
    }

    @Override // org.aspectj.lang.reflect.TypePattern
    public String asString() {
        return this.typePattern;
    }

    public String toString() {
        return asString();
    }
}
