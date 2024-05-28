package org.bouncycastle.asn1.x500;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface X500NameStyle {
    boolean areEqual(X500Name x500Name, X500Name x500Name2);

    ASN1ObjectIdentifier attrNameToOID(String str);

    int calculateHashCode(X500Name x500Name);

    RDN[] fromString(String str);

    String[] oidToAttrNames(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    String oidToDisplayName(ASN1ObjectIdentifier aSN1ObjectIdentifier);

    ASN1Encodable stringToValue(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str);

    String toString(X500Name x500Name);
}
