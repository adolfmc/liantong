package org.codehaus.jackson.sym;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class NameN extends Name {
    final int mQuadLen;
    final int[] mQuads;

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int i) {
        return false;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int i, int i2) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NameN(String str, int i, int[] iArr, int i2) {
        super(str, i);
        if (i2 < 3) {
            throw new IllegalArgumentException("Qlen must >= 3");
        }
        this.mQuads = iArr;
        this.mQuadLen = i2;
    }

    @Override // org.codehaus.jackson.sym.Name
    public boolean equals(int[] iArr, int i) {
        if (i != this.mQuadLen) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (iArr[i2] != this.mQuads[i2]) {
                return false;
            }
        }
        return true;
    }
}
