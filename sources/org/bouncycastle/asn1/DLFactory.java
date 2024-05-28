package org.bouncycastle.asn1;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DLFactory {
    static final DLSequence EMPTY_SEQUENCE = new DLSequence();
    static final DLSet EMPTY_SET = new DLSet();

    DLFactory() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DLSequence createSequence(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? EMPTY_SEQUENCE : new DLSequence(aSN1EncodableVector);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DLSet createSet(ASN1EncodableVector aSN1EncodableVector) {
        return aSN1EncodableVector.size() < 1 ? EMPTY_SET : new DLSet(aSN1EncodableVector);
    }
}
