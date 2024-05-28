package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NameConstraints extends ASN1Object {
    private GeneralSubtree[] excluded;
    private GeneralSubtree[] permitted;

    private NameConstraints(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(objects.nextElement());
            switch (aSN1TaggedObject.getTagNo()) {
                case 0:
                    this.permitted = createArray(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                    break;
                case 1:
                    this.excluded = createArray(ASN1Sequence.getInstance(aSN1TaggedObject, false));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown tag encountered: " + aSN1TaggedObject.getTagNo());
            }
        }
    }

    public NameConstraints(GeneralSubtree[] generalSubtreeArr, GeneralSubtree[] generalSubtreeArr2) {
        this.permitted = cloneSubtree(generalSubtreeArr);
        this.excluded = cloneSubtree(generalSubtreeArr2);
    }

    private static GeneralSubtree[] cloneSubtree(GeneralSubtree[] generalSubtreeArr) {
        if (generalSubtreeArr != null) {
            GeneralSubtree[] generalSubtreeArr2 = new GeneralSubtree[generalSubtreeArr.length];
            System.arraycopy(generalSubtreeArr, 0, generalSubtreeArr2, 0, generalSubtreeArr2.length);
            return generalSubtreeArr2;
        }
        return null;
    }

    private GeneralSubtree[] createArray(ASN1Sequence aSN1Sequence) {
        GeneralSubtree[] generalSubtreeArr = new GeneralSubtree[aSN1Sequence.size()];
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            generalSubtreeArr[i] = GeneralSubtree.getInstance(aSN1Sequence.getObjectAt(i));
        }
        return generalSubtreeArr;
    }

    public static NameConstraints getInstance(Object obj) {
        if (obj instanceof NameConstraints) {
            return (NameConstraints) obj;
        }
        if (obj != null) {
            return new NameConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralSubtree[] getExcludedSubtrees() {
        return cloneSubtree(this.excluded);
    }

    public GeneralSubtree[] getPermittedSubtrees() {
        return cloneSubtree(this.permitted);
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        GeneralSubtree[] generalSubtreeArr = this.permitted;
        if (generalSubtreeArr != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) new DERSequence(generalSubtreeArr)));
        }
        GeneralSubtree[] generalSubtreeArr2 = this.excluded;
        if (generalSubtreeArr2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) new DERSequence(generalSubtreeArr2)));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
