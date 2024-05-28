package org.bouncycastle.pqc.math.linearalgebra;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Vector {
    protected int length;

    public abstract Vector add(Vector vector);

    public abstract boolean equals(Object obj);

    public abstract byte[] getEncoded();

    public final int getLength() {
        return this.length;
    }

    public abstract int hashCode();

    public abstract boolean isZero();

    public abstract Vector multiply(Permutation permutation);

    public abstract String toString();
}
