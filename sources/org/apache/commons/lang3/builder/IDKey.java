package org.apache.commons.lang3.builder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class IDKey {

    /* renamed from: id */
    private final int f26259id;
    private final Object value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public IDKey(Object obj) {
        this.f26259id = System.identityHashCode(obj);
        this.value = obj;
    }

    public int hashCode() {
        return this.f26259id;
    }

    public boolean equals(Object obj) {
        if (obj instanceof IDKey) {
            IDKey iDKey = (IDKey) obj;
            return this.f26259id == iDKey.f26259id && this.value == iDKey.value;
        }
        return false;
    }
}
