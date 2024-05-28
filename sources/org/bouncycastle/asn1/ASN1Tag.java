package org.bouncycastle.asn1;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
final class ASN1Tag {
    private final int tagClass;
    private final int tagNumber;

    private ASN1Tag(int i, int i2) {
        this.tagClass = i;
        this.tagNumber = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Tag create(int i, int i2) {
        return new ASN1Tag(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTagClass() {
        return this.tagClass;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getTagNumber() {
        return this.tagNumber;
    }
}
