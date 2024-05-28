package org.bouncycastle.asn1.x509;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x500.style.RFC4519Style;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PKIXNameConstraintValidator implements NameConstraintValidator {
    private Set permittedSubtreesDN;
    private Set permittedSubtreesDNS;
    private Set permittedSubtreesEmail;
    private Set permittedSubtreesIP;
    private Set permittedSubtreesOtherName;
    private Set permittedSubtreesURI;
    private Set excludedSubtreesDN = new HashSet();
    private Set excludedSubtreesDNS = new HashSet();
    private Set excludedSubtreesEmail = new HashSet();
    private Set excludedSubtreesURI = new HashSet();
    private Set excludedSubtreesIP = new HashSet();
    private Set excludedSubtreesOtherName = new HashSet();

    private final void addLine(StringBuilder sb, String str) {
        sb.append(str);
        sb.append(Strings.lineSeparator());
    }

    private void checkExcludedDN(Set set, ASN1Sequence aSN1Sequence) throws NameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (withinDNSubtree(aSN1Sequence, (ASN1Sequence) it.next())) {
                throw new NameConstraintValidatorException("Subject distinguished name is from an excluded subtree");
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkExcludedDNS(java.util.Set r3, java.lang.String r4) throws org.bouncycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r2 = this;
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L7
            return
        L7:
            java.util.Iterator r3 = r3.iterator()
        Lb:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L2c
            java.lang.Object r0 = r3.next()
            java.lang.String r0 = (java.lang.String) r0
            boolean r1 = r2.withinDomain(r4, r0)
            if (r1 != 0) goto L24
            boolean r0 = r4.equalsIgnoreCase(r0)
            if (r0 != 0) goto L24
            goto Lb
        L24:
            org.bouncycastle.asn1.x509.NameConstraintValidatorException r3 = new org.bouncycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r4 = "DNS is from an excluded subtree."
            r3.<init>(r4)
            throw r3
        L2c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.x509.PKIXNameConstraintValidator.checkExcludedDNS(java.util.Set, java.lang.String):void");
    }

    private void checkExcludedEmail(Set set, String str) throws NameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (emailIsConstrained(str, (String) it.next())) {
                throw new NameConstraintValidatorException("Email address is from an excluded subtree.");
            }
        }
    }

    private void checkExcludedIP(Set set, byte[] bArr) throws NameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isIPConstrained(bArr, (byte[]) it.next())) {
                throw new NameConstraintValidatorException("IP is from an excluded subtree.");
            }
        }
    }

    private void checkExcludedOtherName(Set set, OtherName otherName) throws NameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        for (Object obj : set) {
            if (otherNameIsConstrained(otherName, OtherName.getInstance(obj))) {
                throw new NameConstraintValidatorException("OtherName is from an excluded subtree.");
            }
        }
    }

    private void checkExcludedURI(Set set, String str) throws NameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isUriConstrained(str, (String) it.next())) {
                throw new NameConstraintValidatorException("URI is from an excluded subtree.");
            }
        }
    }

    private void checkPermittedDN(Set set, ASN1Sequence aSN1Sequence) throws NameConstraintValidatorException {
        if (set == null) {
            return;
        }
        if (set.isEmpty() && aSN1Sequence.size() == 0) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (withinDNSubtree(aSN1Sequence, (ASN1Sequence) it.next())) {
                return;
            }
        }
        throw new NameConstraintValidatorException("Subject distinguished name is not from a permitted subtree");
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void checkPermittedDNS(java.util.Set r4, java.lang.String r5) throws org.bouncycastle.asn1.x509.NameConstraintValidatorException {
        /*
            r3 = this;
            if (r4 != 0) goto L3
            return
        L3:
            java.util.Iterator r0 = r4.iterator()
        L7:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L20
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r3.withinDomain(r5, r1)
            if (r2 != 0) goto L1f
            boolean r1 = r5.equalsIgnoreCase(r1)
            if (r1 == 0) goto L7
        L1f:
            return
        L20:
            int r5 = r5.length()
            if (r5 != 0) goto L2d
            int r4 = r4.size()
            if (r4 != 0) goto L2d
            return
        L2d:
            org.bouncycastle.asn1.x509.NameConstraintValidatorException r4 = new org.bouncycastle.asn1.x509.NameConstraintValidatorException
            java.lang.String r5 = "DNS is not from a permitted subtree."
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.x509.PKIXNameConstraintValidator.checkPermittedDNS(java.util.Set, java.lang.String):void");
    }

    private void checkPermittedEmail(Set set, String str) throws NameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (emailIsConstrained(str, (String) it.next())) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new NameConstraintValidatorException("Subject email address is not from a permitted subtree.");
        }
    }

    private void checkPermittedIP(Set set, byte[] bArr) throws NameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isIPConstrained(bArr, (byte[]) it.next())) {
                return;
            }
        }
        if (bArr.length != 0 || set.size() != 0) {
            throw new NameConstraintValidatorException("IP is not from a permitted subtree.");
        }
    }

    private void checkPermittedOtherName(Set set, OtherName otherName) throws NameConstraintValidatorException {
        if (set == null) {
            return;
        }
        for (Object obj : set) {
            if (otherNameIsConstrained(otherName, OtherName.getInstance(obj))) {
                return;
            }
        }
        throw new NameConstraintValidatorException("Subject OtherName is not from a permitted subtree.");
    }

    private void checkPermittedURI(Set set, String str) throws NameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isUriConstrained(str, (String) it.next())) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new NameConstraintValidatorException("URI is not from a permitted subtree.");
        }
    }

    private boolean collectionsAreEqual(Collection collection, Collection collection2) {
        boolean z;
        if (collection == collection2) {
            return true;
        }
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        for (Object obj : collection) {
            Iterator it = collection2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (equals(obj, it.next())) {
                        z = true;
                        continue;
                        break;
                    }
                } else {
                    z = false;
                    continue;
                    break;
                }
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static int compareTo(byte[] bArr, byte[] bArr2) {
        if (Arrays.areEqual(bArr, bArr2)) {
            return 0;
        }
        return Arrays.areEqual(max(bArr, bArr2), bArr) ? 1 : -1;
    }

    private boolean emailIsConstrained(String str, String str2) {
        String substring = str.substring(str.indexOf(64) + 1);
        if (str2.indexOf(64) != -1) {
            if (str.equalsIgnoreCase(str2) || substring.equalsIgnoreCase(str2.substring(1))) {
                return true;
            }
        } else if (str2.charAt(0) != '.') {
            if (substring.equalsIgnoreCase(str2)) {
                return true;
            }
        } else if (withinDomain(substring, str2)) {
            return true;
        }
        return false;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return ((obj instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.areEqual((byte[]) obj, (byte[]) obj2) : obj.equals(obj2);
    }

    private static String extractHostFromURL(String str) {
        String substring = str.substring(str.indexOf(58) + 1);
        if (substring.indexOf("//") != -1) {
            substring = substring.substring(substring.indexOf("//") + 2);
        }
        if (substring.lastIndexOf(58) != -1) {
            substring = substring.substring(0, substring.lastIndexOf(58));
        }
        String substring2 = substring.substring(substring.indexOf(58) + 1);
        String substring3 = substring2.substring(substring2.indexOf(64) + 1);
        return substring3.indexOf(47) != -1 ? substring3.substring(0, substring3.indexOf(47)) : substring3;
    }

    private byte[][] extractIPsAndSubnetMasks(byte[] bArr, byte[] bArr2) {
        int length = bArr.length / 2;
        byte[] bArr3 = new byte[length];
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr, length, bArr4, 0, length);
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        System.arraycopy(bArr2, 0, bArr5, 0, length);
        System.arraycopy(bArr2, length, bArr6, 0, length);
        return new byte[][]{bArr3, bArr4, bArr5, bArr6};
    }

    private String extractNameAsString(GeneralName generalName) {
        return ASN1IA5String.getInstance(generalName.getName()).getString();
    }

    private int hashCollection(Collection collection) {
        int i = 0;
        if (collection == null) {
            return 0;
        }
        for (Object obj : collection) {
            i += obj instanceof byte[] ? Arrays.hashCode((byte[]) obj) : obj.hashCode();
        }
        return i;
    }

    private Set intersectDN(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(((GeneralSubtree) it.next()).getBase().getName().toASN1Primitive());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) it2.next();
                    if (withinDNSubtree(aSN1Sequence, aSN1Sequence2)) {
                        hashSet.add(aSN1Sequence);
                    } else if (withinDNSubtree(aSN1Sequence2, aSN1Sequence)) {
                        hashSet.add(aSN1Sequence2);
                    }
                }
            } else if (aSN1Sequence != null) {
                hashSet.add(aSN1Sequence);
            }
        }
        return hashSet;
    }

    private Set intersectDNS(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String extractNameAsString = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    String str = (String) it2.next();
                    if (withinDomain(str, extractNameAsString)) {
                        hashSet.add(str);
                    } else if (withinDomain(extractNameAsString, str)) {
                        hashSet.add(extractNameAsString);
                    }
                }
            } else if (extractNameAsString != null) {
                hashSet.add(extractNameAsString);
            }
        }
        return hashSet;
    }

    private Set intersectEmail(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String extractNameAsString = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    intersectEmail(extractNameAsString, (String) it2.next(), hashSet);
                }
            } else if (extractNameAsString != null) {
                hashSet.add(extractNameAsString);
            }
        }
        return hashSet;
    }

    private void intersectEmail(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (!str.equalsIgnoreCase(str2)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!withinDomain(substring, str2)) {
                    return;
                }
            } else if (!substring.equalsIgnoreCase(str2)) {
                return;
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (!withinDomain(str2.substring(str.indexOf(64) + 1), str)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!withinDomain(str, str2) && !str.equalsIgnoreCase(str2)) {
                    if (!withinDomain(str2, str)) {
                        return;
                    }
                }
            } else if (!withinDomain(str2, str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.indexOf(64) != -1) {
            if (!str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.startsWith(".")) {
            if (!withinDomain(str, str2)) {
                return;
            }
        } else if (!str.equalsIgnoreCase(str2)) {
            return;
        }
        set.add(str);
    }

    private Set intersectIP(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            byte[] octets = ASN1OctetString.getInstance(((GeneralSubtree) it.next()).getBase().getName()).getOctets();
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    hashSet.addAll(intersectIPRange((byte[]) it2.next(), octets));
                }
            } else if (octets != null) {
                hashSet.add(octets);
            }
        }
        return hashSet;
    }

    private Set intersectIPRange(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return Collections.EMPTY_SET;
        }
        byte[][] extractIPsAndSubnetMasks = extractIPsAndSubnetMasks(bArr, bArr2);
        byte[] bArr3 = extractIPsAndSubnetMasks[0];
        byte[] bArr4 = extractIPsAndSubnetMasks[1];
        byte[] bArr5 = extractIPsAndSubnetMasks[2];
        byte[] bArr6 = extractIPsAndSubnetMasks[3];
        byte[][] minMaxIPs = minMaxIPs(bArr3, bArr4, bArr5, bArr6);
        return compareTo(max(minMaxIPs[0], minMaxIPs[2]), min(minMaxIPs[1], minMaxIPs[3])) == 1 ? Collections.EMPTY_SET : Collections.singleton(ipWithSubnetMask(m365or(minMaxIPs[0], minMaxIPs[2]), m365or(bArr4, bArr6)));
    }

    private Set intersectOtherName(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            OtherName otherName = OtherName.getInstance(((GeneralSubtree) it.next()).getBase().getName());
            if (set != null) {
                for (Object obj : set) {
                    intersectOtherName(otherName, OtherName.getInstance(obj), hashSet);
                }
            } else if (otherName != null) {
                hashSet.add(otherName);
            }
        }
        return hashSet;
    }

    private void intersectOtherName(OtherName otherName, OtherName otherName2, Set set) {
        if (otherName.equals(otherName2)) {
            set.add(otherName);
        }
    }

    private Set intersectURI(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String extractNameAsString = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    intersectURI((String) it2.next(), extractNameAsString, hashSet);
                }
            } else if (extractNameAsString != null) {
                hashSet.add(extractNameAsString);
            }
        }
        return hashSet;
    }

    private void intersectURI(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String substring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (!str.equalsIgnoreCase(str2)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!withinDomain(substring, str2)) {
                    return;
                }
            } else if (!substring.equalsIgnoreCase(str2)) {
                return;
            }
        } else if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (!withinDomain(str2.substring(str.indexOf(64) + 1), str)) {
                    return;
                }
            } else if (str2.startsWith(".")) {
                if (!withinDomain(str, str2) && !str.equalsIgnoreCase(str2)) {
                    if (!withinDomain(str2, str)) {
                        return;
                    }
                }
            } else if (!withinDomain(str2, str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.indexOf(64) != -1) {
            if (!str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                return;
            }
            set.add(str2);
            return;
        } else if (str2.startsWith(".")) {
            if (!withinDomain(str, str2)) {
                return;
            }
        } else if (!str.equalsIgnoreCase(str2)) {
            return;
        }
        set.add(str);
    }

    private byte[] ipWithSubnetMask(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length * 2];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr2, 0, bArr3, length, length);
        return bArr3;
    }

    private boolean isIPConstrained(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length != bArr2.length / 2) {
            return false;
        }
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr2, length, bArr3, 0, length);
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr4[i] = (byte) (bArr2[i] & bArr3[i]);
            bArr5[i] = (byte) (bArr[i] & bArr3[i]);
        }
        return Arrays.areEqual(bArr4, bArr5);
    }

    private boolean isUriConstrained(String str, String str2) {
        String extractHostFromURL = extractHostFromURL(str);
        return !str2.startsWith(".") ? extractHostFromURL.equalsIgnoreCase(str2) : withinDomain(extractHostFromURL, str2);
    }

    private static byte[] max(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) > (65535 & bArr2[i])) {
                return bArr;
            }
        }
        return bArr2;
    }

    private static byte[] min(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) < (65535 & bArr2[i])) {
                return bArr;
            }
        }
        return bArr2;
    }

    private byte[][] minMaxIPs(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int length = bArr.length;
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        byte[] bArr7 = new byte[length];
        byte[] bArr8 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr5[i] = (byte) (bArr[i] & bArr2[i]);
            bArr6[i] = (byte) ((bArr[i] & bArr2[i]) | (~bArr2[i]));
            bArr7[i] = (byte) (bArr3[i] & bArr4[i]);
            bArr8[i] = (byte) ((bArr3[i] & bArr4[i]) | (~bArr4[i]));
        }
        return new byte[][]{bArr5, bArr6, bArr7, bArr8};
    }

    /* renamed from: or */
    private static byte[] m365or(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] | bArr2[i]);
        }
        return bArr3;
    }

    private boolean otherNameIsConstrained(OtherName otherName, OtherName otherName2) {
        return otherName2.equals(otherName);
    }

    private String stringifyIP(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length / 2; i++) {
            if (sb.length() > 0) {
                sb.append(".");
            }
            sb.append(Integer.toString(bArr[i] & 255));
        }
        sb.append("/");
        boolean z = true;
        for (int length = bArr.length / 2; length < bArr.length; length++) {
            if (z) {
                z = false;
            } else {
                sb.append(".");
            }
            sb.append(Integer.toString(bArr[length] & 255));
        }
        return sb.toString();
    }

    private String stringifyIPCollection(Set set) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            sb.append(stringifyIP((byte[]) it.next()));
        }
        sb.append("]");
        return sb.toString();
    }

    private String stringifyOtherNameCollection(Set set) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : set) {
            if (sb.length() > 1) {
                sb.append(",");
            }
            OtherName otherName = OtherName.getInstance(obj);
            sb.append(otherName.getTypeID().getId());
            sb.append(":");
            try {
                sb.append(Hex.toHexString(otherName.getValue().toASN1Primitive().getEncoded()));
            } catch (IOException e) {
                sb.append(e.toString());
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private Set unionDN(Set set, ASN1Sequence aSN1Sequence) {
        if (set.isEmpty()) {
            if (aSN1Sequence == null) {
                return set;
            }
            set.add(aSN1Sequence);
            return set;
        }
        HashSet hashSet = new HashSet();
        for (Object obj : set) {
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(obj);
            if (withinDNSubtree(aSN1Sequence, aSN1Sequence2)) {
                hashSet.add(aSN1Sequence2);
            } else {
                if (!withinDNSubtree(aSN1Sequence2, aSN1Sequence)) {
                    hashSet.add(aSN1Sequence2);
                }
                hashSet.add(aSN1Sequence);
            }
        }
        return hashSet;
    }

    private Set unionDNS(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (!withinDomain(str2, str)) {
                if (withinDomain(str, str2)) {
                    hashSet.add(str2);
                } else {
                    hashSet.add(str2);
                }
            }
            hashSet.add(str);
        }
        return hashSet;
    }

    private Set unionEmail(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            unionEmail((String) it.next(), str, hashSet);
        }
        return hashSet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
        if (withinDomain(r5.substring(r4.indexOf(64) + 1), r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0071, code lost:
        if (withinDomain(r5, r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0078, code lost:
        if (withinDomain(r5, r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008f, code lost:
        if (r5.substring(r4.indexOf(64) + 1).equalsIgnoreCase(r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009e, code lost:
        if (withinDomain(r4, r5) != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a5, code lost:
        if (r4.equalsIgnoreCase(r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a7, code lost:
        r6.add(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
        if (r4.equalsIgnoreCase(r5) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void unionEmail(java.lang.String r4, java.lang.String r5, java.util.Set r6) {
        /*
            r3 = this;
            r0 = 64
            int r1 = r4.indexOf(r0)
            r2 = -1
            if (r1 == r2) goto L39
            int r1 = r4.indexOf(r0)
            int r1 = r1 + 1
            java.lang.String r1 = r4.substring(r1)
            int r0 = r5.indexOf(r0)
            if (r0 == r2) goto L21
            boolean r0 = r4.equalsIgnoreCase(r5)
            if (r0 == 0) goto Lab
            goto La7
        L21:
            java.lang.String r0 = "."
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L31
            boolean r0 = r3.withinDomain(r1, r5)
            if (r0 == 0) goto Lab
            goto La0
        L31:
            boolean r0 = r1.equalsIgnoreCase(r5)
            if (r0 == 0) goto Lab
            goto La0
        L39:
            java.lang.String r1 = "."
            boolean r1 = r4.startsWith(r1)
            if (r1 == 0) goto L7b
            int r1 = r5.indexOf(r0)
            if (r1 == r2) goto L58
            int r0 = r4.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r5.substring(r0)
            boolean r0 = r3.withinDomain(r0, r4)
            if (r0 == 0) goto Lab
            goto La7
        L58:
            java.lang.String r0 = "."
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L74
            boolean r0 = r3.withinDomain(r4, r5)
            if (r0 != 0) goto Lae
            boolean r0 = r4.equalsIgnoreCase(r5)
            if (r0 == 0) goto L6d
            goto Lae
        L6d:
            boolean r0 = r3.withinDomain(r5, r4)
            if (r0 == 0) goto Lab
            goto La7
        L74:
            boolean r0 = r3.withinDomain(r5, r4)
            if (r0 == 0) goto Lab
            goto La7
        L7b:
            int r1 = r5.indexOf(r0)
            if (r1 == r2) goto L92
            int r0 = r4.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r5.substring(r0)
            boolean r0 = r0.equalsIgnoreCase(r4)
            if (r0 == 0) goto Lab
            goto La7
        L92:
            java.lang.String r0 = "."
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto La1
            boolean r0 = r3.withinDomain(r4, r5)
            if (r0 == 0) goto Lab
        La0:
            goto Lae
        La1:
            boolean r0 = r4.equalsIgnoreCase(r5)
            if (r0 == 0) goto Lab
        La7:
            r6.add(r4)
            goto Lb1
        Lab:
            r6.add(r4)
        Lae:
            r6.add(r5)
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.x509.PKIXNameConstraintValidator.unionEmail(java.lang.String, java.lang.String, java.util.Set):void");
    }

    private Set unionIP(Set set, byte[] bArr) {
        if (set.isEmpty()) {
            if (bArr == null) {
                return set;
            }
            set.add(bArr);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.addAll(unionIPRange((byte[]) it.next(), bArr));
        }
        return hashSet;
    }

    private Set unionIPRange(byte[] bArr, byte[] bArr2) {
        HashSet hashSet = new HashSet();
        if (Arrays.areEqual(bArr, bArr2)) {
            hashSet.add(bArr);
        } else {
            hashSet.add(bArr);
            hashSet.add(bArr2);
        }
        return hashSet;
    }

    private Set unionOtherName(Set set, OtherName otherName) {
        HashSet hashSet = set != null ? new HashSet(set) : new HashSet();
        hashSet.add(otherName);
        return hashSet;
    }

    private Set unionURI(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            unionURI((String) it.next(), str, hashSet);
        }
        return hashSet;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0055, code lost:
        if (withinDomain(r5.substring(r4.indexOf(64) + 1), r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0071, code lost:
        if (withinDomain(r5, r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0078, code lost:
        if (withinDomain(r5, r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008f, code lost:
        if (r5.substring(r4.indexOf(64) + 1).equalsIgnoreCase(r4) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009e, code lost:
        if (withinDomain(r4, r5) != false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a5, code lost:
        if (r4.equalsIgnoreCase(r5) != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00a7, code lost:
        r6.add(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001d, code lost:
        if (r4.equalsIgnoreCase(r5) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void unionURI(java.lang.String r4, java.lang.String r5, java.util.Set r6) {
        /*
            r3 = this;
            r0 = 64
            int r1 = r4.indexOf(r0)
            r2 = -1
            if (r1 == r2) goto L39
            int r1 = r4.indexOf(r0)
            int r1 = r1 + 1
            java.lang.String r1 = r4.substring(r1)
            int r0 = r5.indexOf(r0)
            if (r0 == r2) goto L21
            boolean r0 = r4.equalsIgnoreCase(r5)
            if (r0 == 0) goto Lab
            goto La7
        L21:
            java.lang.String r0 = "."
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L31
            boolean r0 = r3.withinDomain(r1, r5)
            if (r0 == 0) goto Lab
            goto La0
        L31:
            boolean r0 = r1.equalsIgnoreCase(r5)
            if (r0 == 0) goto Lab
            goto La0
        L39:
            java.lang.String r1 = "."
            boolean r1 = r4.startsWith(r1)
            if (r1 == 0) goto L7b
            int r1 = r5.indexOf(r0)
            if (r1 == r2) goto L58
            int r0 = r4.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r5.substring(r0)
            boolean r0 = r3.withinDomain(r0, r4)
            if (r0 == 0) goto Lab
            goto La7
        L58:
            java.lang.String r0 = "."
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto L74
            boolean r0 = r3.withinDomain(r4, r5)
            if (r0 != 0) goto Lae
            boolean r0 = r4.equalsIgnoreCase(r5)
            if (r0 == 0) goto L6d
            goto Lae
        L6d:
            boolean r0 = r3.withinDomain(r5, r4)
            if (r0 == 0) goto Lab
            goto La7
        L74:
            boolean r0 = r3.withinDomain(r5, r4)
            if (r0 == 0) goto Lab
            goto La7
        L7b:
            int r1 = r5.indexOf(r0)
            if (r1 == r2) goto L92
            int r0 = r4.indexOf(r0)
            int r0 = r0 + 1
            java.lang.String r0 = r5.substring(r0)
            boolean r0 = r0.equalsIgnoreCase(r4)
            if (r0 == 0) goto Lab
            goto La7
        L92:
            java.lang.String r0 = "."
            boolean r0 = r5.startsWith(r0)
            if (r0 == 0) goto La1
            boolean r0 = r3.withinDomain(r4, r5)
            if (r0 == 0) goto Lab
        La0:
            goto Lae
        La1:
            boolean r0 = r4.equalsIgnoreCase(r5)
            if (r0 == 0) goto Lab
        La7:
            r6.add(r4)
            goto Lb1
        Lab:
            r6.add(r4)
        Lae:
            r6.add(r5)
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.x509.PKIXNameConstraintValidator.unionURI(java.lang.String, java.lang.String, java.util.Set):void");
    }

    private static boolean withinDNSubtree(ASN1Sequence aSN1Sequence, ASN1Sequence aSN1Sequence2) {
        if (aSN1Sequence2.size() >= 1 && aSN1Sequence2.size() <= aSN1Sequence.size()) {
            RDN rdn = RDN.getInstance(aSN1Sequence2.getObjectAt(0));
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= aSN1Sequence.size()) {
                    i = i2;
                    break;
                } else if (IETFUtils.rDNAreEqual(rdn, RDN.getInstance(aSN1Sequence.getObjectAt(i)))) {
                    break;
                } else {
                    i2 = i;
                    i++;
                }
            }
            if (aSN1Sequence2.size() > aSN1Sequence.size() - i) {
                return false;
            }
            for (int i3 = 0; i3 < aSN1Sequence2.size(); i3++) {
                RDN rdn2 = RDN.getInstance(aSN1Sequence2.getObjectAt(i3));
                RDN rdn3 = RDN.getInstance(aSN1Sequence.getObjectAt(i + i3));
                if (rdn2.size() != rdn3.size() || !rdn2.getFirst().getType().equals((ASN1Primitive) rdn3.getFirst().getType())) {
                    return false;
                }
                if (rdn2.size() == 1 && rdn2.getFirst().getType().equals((ASN1Primitive) RFC4519Style.serialNumber)) {
                    if (!rdn3.getFirst().getValue().toString().startsWith(rdn2.getFirst().getValue().toString())) {
                        return false;
                    }
                } else if (!IETFUtils.rDNAreEqual(rdn2, rdn3)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean withinDomain(String str, String str2) {
        if (str2.startsWith(".")) {
            str2 = str2.substring(1);
        }
        String[] split = Strings.split(str2, '.');
        String[] split2 = Strings.split(str, '.');
        if (split2.length <= split.length) {
            return false;
        }
        int length = split2.length - split.length;
        for (int i = -1; i < split.length; i++) {
            if (i == -1) {
                if (split2[i + length].equals("")) {
                    return false;
                }
            } else if (!split[i].equalsIgnoreCase(split2[i + length])) {
                return false;
            }
        }
        return true;
    }

    @Override // org.bouncycastle.asn1.x509.NameConstraintValidator
    public void addExcludedSubtree(GeneralSubtree generalSubtree) {
        GeneralName base = generalSubtree.getBase();
        switch (base.getTagNo()) {
            case 0:
                this.excludedSubtreesOtherName = unionOtherName(this.excludedSubtreesOtherName, OtherName.getInstance(base.getName()));
                return;
            case 1:
                this.excludedSubtreesEmail = unionEmail(this.excludedSubtreesEmail, extractNameAsString(base));
                return;
            case 2:
                this.excludedSubtreesDNS = unionDNS(this.excludedSubtreesDNS, extractNameAsString(base));
                return;
            case 3:
            case 5:
            default:
                throw new IllegalStateException("Unknown tag encountered: " + base.getTagNo());
            case 4:
                this.excludedSubtreesDN = unionDN(this.excludedSubtreesDN, (ASN1Sequence) base.getName().toASN1Primitive());
                return;
            case 6:
                this.excludedSubtreesURI = unionURI(this.excludedSubtreesURI, extractNameAsString(base));
                return;
            case 7:
                this.excludedSubtreesIP = unionIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(base.getName()).getOctets());
                return;
        }
    }

    @Override // org.bouncycastle.asn1.x509.NameConstraintValidator
    public void checkExcluded(GeneralName generalName) throws NameConstraintValidatorException {
        switch (generalName.getTagNo()) {
            case 0:
                checkExcludedOtherName(this.excludedSubtreesOtherName, OtherName.getInstance(generalName.getName()));
                return;
            case 1:
                checkExcludedEmail(this.excludedSubtreesEmail, extractNameAsString(generalName));
                return;
            case 2:
                checkExcludedDNS(this.excludedSubtreesDNS, extractNameAsString(generalName));
                return;
            case 3:
            case 5:
            default:
                return;
            case 4:
                checkExcludedDN(X500Name.getInstance(generalName.getName()));
                return;
            case 6:
                checkExcludedURI(this.excludedSubtreesURI, extractNameAsString(generalName));
                return;
            case 7:
                checkExcludedIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(generalName.getName()).getOctets());
                return;
        }
    }

    public void checkExcludedDN(X500Name x500Name) throws NameConstraintValidatorException {
        checkExcludedDN(this.excludedSubtreesDN, ASN1Sequence.getInstance(x500Name));
    }

    @Override // org.bouncycastle.asn1.x509.NameConstraintValidator
    public void checkPermitted(GeneralName generalName) throws NameConstraintValidatorException {
        switch (generalName.getTagNo()) {
            case 0:
                checkPermittedOtherName(this.permittedSubtreesOtherName, OtherName.getInstance(generalName.getName()));
                return;
            case 1:
                checkPermittedEmail(this.permittedSubtreesEmail, extractNameAsString(generalName));
                return;
            case 2:
                checkPermittedDNS(this.permittedSubtreesDNS, extractNameAsString(generalName));
                return;
            case 3:
            case 5:
            default:
                return;
            case 4:
                checkPermittedDN(X500Name.getInstance(generalName.getName()));
                return;
            case 6:
                checkPermittedURI(this.permittedSubtreesURI, extractNameAsString(generalName));
                return;
            case 7:
                checkPermittedIP(this.permittedSubtreesIP, ASN1OctetString.getInstance(generalName.getName()).getOctets());
                return;
        }
    }

    public void checkPermittedDN(X500Name x500Name) throws NameConstraintValidatorException {
        checkPermittedDN(this.permittedSubtreesDN, ASN1Sequence.getInstance(x500Name.toASN1Primitive()));
    }

    public boolean equals(Object obj) {
        if (obj instanceof PKIXNameConstraintValidator) {
            PKIXNameConstraintValidator pKIXNameConstraintValidator = (PKIXNameConstraintValidator) obj;
            return collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesDN, this.excludedSubtreesDN) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesDNS, this.excludedSubtreesDNS) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesEmail, this.excludedSubtreesEmail) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesIP, this.excludedSubtreesIP) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesURI, this.excludedSubtreesURI) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesOtherName, this.excludedSubtreesOtherName) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesDN, this.permittedSubtreesDN) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesDNS, this.permittedSubtreesDNS) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesEmail, this.permittedSubtreesEmail) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesIP, this.permittedSubtreesIP) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesURI, this.permittedSubtreesURI) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesOtherName, this.permittedSubtreesOtherName);
        }
        return false;
    }

    public int hashCode() {
        return hashCollection(this.excludedSubtreesDN) + hashCollection(this.excludedSubtreesDNS) + hashCollection(this.excludedSubtreesEmail) + hashCollection(this.excludedSubtreesIP) + hashCollection(this.excludedSubtreesURI) + hashCollection(this.excludedSubtreesOtherName) + hashCollection(this.permittedSubtreesDN) + hashCollection(this.permittedSubtreesDNS) + hashCollection(this.permittedSubtreesEmail) + hashCollection(this.permittedSubtreesIP) + hashCollection(this.permittedSubtreesURI) + hashCollection(this.permittedSubtreesOtherName);
    }

    @Override // org.bouncycastle.asn1.x509.NameConstraintValidator
    public void intersectEmptyPermittedSubtree(int i) {
        switch (i) {
            case 0:
                this.permittedSubtreesOtherName = new HashSet();
                return;
            case 1:
                this.permittedSubtreesEmail = new HashSet();
                return;
            case 2:
                this.permittedSubtreesDNS = new HashSet();
                return;
            case 3:
            case 5:
            default:
                throw new IllegalStateException("Unknown tag encountered: " + i);
            case 4:
                this.permittedSubtreesDN = new HashSet();
                return;
            case 6:
                this.permittedSubtreesURI = new HashSet();
                return;
            case 7:
                this.permittedSubtreesIP = new HashSet();
                return;
        }
    }

    @Override // org.bouncycastle.asn1.x509.NameConstraintValidator
    public void intersectPermittedSubtree(GeneralSubtree generalSubtree) {
        intersectPermittedSubtree(new GeneralSubtree[]{generalSubtree});
    }

    @Override // org.bouncycastle.asn1.x509.NameConstraintValidator
    public void intersectPermittedSubtree(GeneralSubtree[] generalSubtreeArr) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            GeneralSubtree generalSubtree = generalSubtreeArr[i];
            Integer valueOf = Integers.valueOf(generalSubtree.getBase().getTagNo());
            if (hashMap.get(valueOf) == null) {
                hashMap.put(valueOf, new HashSet());
            }
            ((Set) hashMap.get(valueOf)).add(generalSubtree);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            switch (intValue) {
                case 0:
                    this.permittedSubtreesOtherName = intersectOtherName(this.permittedSubtreesOtherName, (Set) entry.getValue());
                    break;
                case 1:
                    this.permittedSubtreesEmail = intersectEmail(this.permittedSubtreesEmail, (Set) entry.getValue());
                    break;
                case 2:
                    this.permittedSubtreesDNS = intersectDNS(this.permittedSubtreesDNS, (Set) entry.getValue());
                    break;
                case 3:
                case 5:
                default:
                    throw new IllegalStateException("Unknown tag encountered: " + intValue);
                case 4:
                    this.permittedSubtreesDN = intersectDN(this.permittedSubtreesDN, (Set) entry.getValue());
                    break;
                case 6:
                    this.permittedSubtreesURI = intersectURI(this.permittedSubtreesURI, (Set) entry.getValue());
                    break;
                case 7:
                    this.permittedSubtreesIP = intersectIP(this.permittedSubtreesIP, (Set) entry.getValue());
                    break;
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        addLine(sb, "permitted:");
        if (this.permittedSubtreesDN != null) {
            addLine(sb, "DN:");
            addLine(sb, this.permittedSubtreesDN.toString());
        }
        if (this.permittedSubtreesDNS != null) {
            addLine(sb, "DNS:");
            addLine(sb, this.permittedSubtreesDNS.toString());
        }
        if (this.permittedSubtreesEmail != null) {
            addLine(sb, "Email:");
            addLine(sb, this.permittedSubtreesEmail.toString());
        }
        if (this.permittedSubtreesURI != null) {
            addLine(sb, "URI:");
            addLine(sb, this.permittedSubtreesURI.toString());
        }
        if (this.permittedSubtreesIP != null) {
            addLine(sb, "IP:");
            addLine(sb, stringifyIPCollection(this.permittedSubtreesIP));
        }
        if (this.permittedSubtreesOtherName != null) {
            addLine(sb, "OtherName:");
            addLine(sb, stringifyOtherNameCollection(this.permittedSubtreesOtherName));
        }
        addLine(sb, "excluded:");
        if (!this.excludedSubtreesDN.isEmpty()) {
            addLine(sb, "DN:");
            addLine(sb, this.excludedSubtreesDN.toString());
        }
        if (!this.excludedSubtreesDNS.isEmpty()) {
            addLine(sb, "DNS:");
            addLine(sb, this.excludedSubtreesDNS.toString());
        }
        if (!this.excludedSubtreesEmail.isEmpty()) {
            addLine(sb, "Email:");
            addLine(sb, this.excludedSubtreesEmail.toString());
        }
        if (!this.excludedSubtreesURI.isEmpty()) {
            addLine(sb, "URI:");
            addLine(sb, this.excludedSubtreesURI.toString());
        }
        if (!this.excludedSubtreesIP.isEmpty()) {
            addLine(sb, "IP:");
            addLine(sb, stringifyIPCollection(this.excludedSubtreesIP));
        }
        if (!this.excludedSubtreesOtherName.isEmpty()) {
            addLine(sb, "OtherName:");
            addLine(sb, stringifyOtherNameCollection(this.excludedSubtreesOtherName));
        }
        return sb.toString();
    }
}
