package org.bouncycastle.asn1;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ASN1GeneralizedTime extends ASN1Primitive {
    static final ASN1UniversalType TYPE = new ASN1UniversalType(ASN1GeneralizedTime.class, 24) { // from class: org.bouncycastle.asn1.ASN1GeneralizedTime.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive fromImplicitPrimitive(DEROctetString dEROctetString) {
            return ASN1GeneralizedTime.createPrimitive(dEROctetString.getOctets());
        }
    };
    final byte[] contents;

    public ASN1GeneralizedTime(String str) {
        this.contents = Strings.toByteArray(str);
        try {
            getDate();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    public ASN1GeneralizedTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", DateUtil.EN_Locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.contents = Strings.toByteArray(simpleDateFormat.format(date));
    }

    public ASN1GeneralizedTime(Date date, Locale locale) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'", locale);
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.contents = Strings.toByteArray(simpleDateFormat.format(date));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1GeneralizedTime(byte[] bArr) {
        if (bArr.length < 4) {
            throw new IllegalArgumentException("GeneralizedTime string too short");
        }
        this.contents = bArr;
        if (!isDigit(0) || !isDigit(1) || !isDigit(2) || !isDigit(3)) {
            throw new IllegalArgumentException("illegal characters in GeneralizedTime string");
        }
    }

    private SimpleDateFormat calculateGMTDateFormat() {
        SimpleDateFormat simpleDateFormat = hasFractionalSeconds() ? new SimpleDateFormat("yyyyMMddHHmmss.SSSz") : hasSeconds() ? new SimpleDateFormat("yyyyMMddHHmmssz") : hasMinutes() ? new SimpleDateFormat("yyyyMMddHHmmz") : new SimpleDateFormat("yyyyMMddHHz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat;
    }

    private String calculateGMTOffset(String str) {
        String str2 = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            str2 = "-";
            rawOffset = -rawOffset;
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime()) {
                if (hasFractionalSeconds()) {
                    str = pruneFractionalSeconds(str);
                }
                SimpleDateFormat calculateGMTDateFormat = calculateGMTDateFormat();
                if (timeZone.inDaylightTime(calculateGMTDateFormat.parse(str + "GMT" + str2 + convert(i) + ":" + convert(i2)))) {
                    i += str2.equals("+") ? 1 : -1;
                }
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str2 + convert(i) + ":" + convert(i2);
    }

    private String convert(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return Integer.toString(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1GeneralizedTime createPrimitive(byte[] bArr) {
        return new ASN1GeneralizedTime(bArr);
    }

    public static ASN1GeneralizedTime getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralizedTime)) {
            return (ASN1GeneralizedTime) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1GeneralizedTime) {
                return (ASN1GeneralizedTime) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1GeneralizedTime) TYPE.fromByteArray((byte[]) obj);
        } catch (Exception e) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
        }
    }

    public static ASN1GeneralizedTime getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1GeneralizedTime) TYPE.getContextInstance(aSN1TaggedObject, z);
    }

    private boolean isDigit(int i) {
        byte[] bArr = this.contents;
        return bArr.length > i && bArr[i] >= 48 && bArr[i] <= 57;
    }

    private String pruneFractionalSeconds(String str) {
        String str2;
        StringBuilder sb;
        char charAt;
        String substring = str.substring(14);
        int i = 1;
        while (i < substring.length() && '0' <= (charAt = substring.charAt(i)) && charAt <= '9') {
            i++;
        }
        int i2 = i - 1;
        if (i2 > 3) {
            str2 = substring.substring(0, 4) + substring.substring(i);
            sb = new StringBuilder();
        } else if (i2 == 1) {
            str2 = substring.substring(0, i) + "00" + substring.substring(i);
            sb = new StringBuilder();
        } else if (i2 != 2) {
            return str;
        } else {
            str2 = substring.substring(0, i) + "0" + substring.substring(i);
            sb = new StringBuilder();
        }
        sb.append(str.substring(0, 14));
        sb.append(str2);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean asn1Equals(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1GeneralizedTime) {
            return Arrays.areEqual(this.contents, ((ASN1GeneralizedTime) aSN1Primitive).contents);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        aSN1OutputStream.writeEncodingDL(z, 24, this.contents);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean encodeConstructed() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength(boolean z) {
        return ASN1OutputStream.getLengthOfEncodingDL(z, this.contents.length);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.Date getDate() throws java.text.ParseException {
        /*
            r5 = this;
            byte[] r0 = r5.contents
            java.lang.String r0 = org.bouncycastle.util.Strings.fromByteArray(r0)
            java.lang.String r1 = "Z"
            boolean r1 = r0.endsWith(r1)
            r2 = 0
            if (r1 == 0) goto L4b
            boolean r1 = r5.hasFractionalSeconds()
            if (r1 == 0) goto L1d
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHHmmss.SSS'Z'"
            r1.<init>(r3)
            goto L40
        L1d:
            boolean r1 = r5.hasSeconds()
            if (r1 == 0) goto L2b
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHHmmss'Z'"
            r1.<init>(r3)
            goto L40
        L2b:
            boolean r1 = r5.hasMinutes()
            if (r1 == 0) goto L39
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHHmm'Z'"
            r1.<init>(r3)
            goto L40
        L39:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHH'Z'"
            r1.<init>(r3)
        L40:
            java.util.SimpleTimeZone r3 = new java.util.SimpleTimeZone
            java.lang.String r4 = "Z"
            r3.<init>(r2, r4)
        L47:
            r1.setTimeZone(r3)
            goto La3
        L4b:
            r1 = 45
            int r1 = r0.indexOf(r1)
            if (r1 > 0) goto L9b
            r1 = 43
            int r1 = r0.indexOf(r1)
            if (r1 <= 0) goto L5c
            goto L9b
        L5c:
            boolean r1 = r5.hasFractionalSeconds()
            if (r1 == 0) goto L6a
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHHmmss.SSS"
            r1.<init>(r3)
            goto L8d
        L6a:
            boolean r1 = r5.hasSeconds()
            if (r1 == 0) goto L78
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHHmmss"
            r1.<init>(r3)
            goto L8d
        L78:
            boolean r1 = r5.hasMinutes()
            if (r1 == 0) goto L86
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHHmm"
            r1.<init>(r3)
            goto L8d
        L86:
            java.text.SimpleDateFormat r1 = new java.text.SimpleDateFormat
            java.lang.String r3 = "yyyyMMddHH"
            r1.<init>(r3)
        L8d:
            java.util.SimpleTimeZone r3 = new java.util.SimpleTimeZone
            java.util.TimeZone r4 = java.util.TimeZone.getDefault()
            java.lang.String r4 = r4.getID()
            r3.<init>(r2, r4)
            goto L47
        L9b:
            java.lang.String r0 = r5.getTime()
            java.text.SimpleDateFormat r1 = r5.calculateGMTDateFormat()
        La3:
            boolean r2 = r5.hasFractionalSeconds()
            if (r2 == 0) goto Lad
            java.lang.String r0 = r5.pruneFractionalSeconds(r0)
        Lad:
            java.util.Date r0 = r1.parse(r0)
            java.util.Date r0 = org.bouncycastle.asn1.DateUtil.epochAdjust(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.asn1.ASN1GeneralizedTime.getDate():java.util.Date");
    }

    public String getTime() {
        int length;
        String fromByteArray = Strings.fromByteArray(this.contents);
        if (fromByteArray.charAt(fromByteArray.length() - 1) == 'Z') {
            return fromByteArray.substring(0, fromByteArray.length() - 1) + "GMT+00:00";
        }
        int length2 = fromByteArray.length() - 6;
        char charAt = fromByteArray.charAt(length2);
        if ((charAt == '-' || charAt == '+') && fromByteArray.indexOf("GMT") == length2 - 3) {
            return fromByteArray;
        }
        int length3 = fromByteArray.length() - 5;
        char charAt2 = fromByteArray.charAt(length3);
        if (charAt2 == '-' || charAt2 == '+') {
            StringBuilder sb = new StringBuilder();
            sb.append(fromByteArray.substring(0, length3));
            sb.append("GMT");
            int i = length3 + 3;
            sb.append(fromByteArray.substring(length3, i));
            sb.append(":");
            sb.append(fromByteArray.substring(i));
            return sb.toString();
        }
        char charAt3 = fromByteArray.charAt(fromByteArray.length() - 3);
        if (charAt3 != '-' && charAt3 != '+') {
            return fromByteArray + calculateGMTOffset(fromByteArray);
        }
        return fromByteArray.substring(0, length) + "GMT" + fromByteArray.substring(length) + ":00";
    }

    public String getTimeString() {
        return Strings.fromByteArray(this.contents);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasFractionalSeconds() {
        int i = 0;
        while (true) {
            byte[] bArr = this.contents;
            if (i == bArr.length) {
                return false;
            }
            if (bArr[i] == 46 && i == 14) {
                return true;
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasMinutes() {
        return isDigit(10) && isDigit(11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean hasSeconds() {
        return isDigit(12) && isDigit(13);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.contents);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive toDERObject() {
        return new DERGeneralizedTime(this.contents);
    }
}
