package org.bouncycastle.asn1.p454bc;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.asn1.bc.LinkedCertificate */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LinkedCertificate extends ASN1Object {
    private GeneralNames cACerts;
    private X500Name certIssuer;
    private final GeneralName certLocation;
    private final DigestInfo digest;

    private LinkedCertificate(ASN1Sequence aSN1Sequence) {
        this.digest = DigestInfo.getInstance(aSN1Sequence.getObjectAt(0));
        this.certLocation = GeneralName.getInstance(aSN1Sequence.getObjectAt(1));
        if (aSN1Sequence.size() > 2) {
            for (int i = 2; i != aSN1Sequence.size(); i++) {
                ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(aSN1Sequence.getObjectAt(i));
                switch (aSN1TaggedObject.getTagNo()) {
                    case 0:
                        this.certIssuer = X500Name.getInstance(aSN1TaggedObject, false);
                        break;
                    case 1:
                        this.cACerts = GeneralNames.getInstance(aSN1TaggedObject, false);
                        break;
                    default:
                        throw new IllegalArgumentException("unknown tag in tagged field");
                }
            }
        }
    }

    public LinkedCertificate(DigestInfo digestInfo, GeneralName generalName) {
        this(digestInfo, generalName, null, null);
    }

    public LinkedCertificate(DigestInfo digestInfo, GeneralName generalName, X500Name x500Name, GeneralNames generalNames) {
        this.digest = digestInfo;
        this.certLocation = generalName;
        this.certIssuer = x500Name;
        this.cACerts = generalNames;
    }

    public static LinkedCertificate getInstance(Object obj) {
        if (obj instanceof LinkedCertificate) {
            return (LinkedCertificate) obj;
        }
        if (obj != null) {
            return new LinkedCertificate(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralNames getCACerts() {
        return this.cACerts;
    }

    public X500Name getCertIssuer() {
        return this.certIssuer;
    }

    public GeneralName getCertLocation() {
        return this.certLocation;
    }

    public DigestInfo getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        aSN1EncodableVector.add(this.digest);
        aSN1EncodableVector.add(this.certLocation);
        X500Name x500Name = this.certIssuer;
        if (x500Name != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) x500Name));
        }
        GeneralNames generalNames = this.cACerts;
        if (generalNames != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) generalNames));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
