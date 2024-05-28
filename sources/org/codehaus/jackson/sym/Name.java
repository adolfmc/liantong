package org.codehaus.jackson.sym;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Name {
    protected final int _hashCode;
    protected final String _name;

    public abstract boolean equals(int i);

    public abstract boolean equals(int i, int i2);

    public boolean equals(Object obj) {
        return obj == this;
    }

    public abstract boolean equals(int[] iArr, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public Name(String str, int i) {
        this._name = str;
        this._hashCode = i;
    }

    public String getName() {
        return this._name;
    }

    public String toString() {
        return this._name;
    }

    public final int hashCode() {
        return this._hashCode;
    }
}
