package com.baidu.p122b.p125c.p129d;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.util.Locale;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.d.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2410c {

    /* renamed from: a */
    private static final byte[] f4240a = new byte[0];

    /* renamed from: b */
    private int f4241b;

    /* renamed from: d */
    private C2413f f4243d;

    /* renamed from: f */
    private byte[] f4245f;

    /* renamed from: g */
    private int f4246g;

    /* renamed from: h */
    private int f4247h;

    /* renamed from: i */
    private InterfaceC2411d f4248i;

    /* renamed from: e */
    private OAEPParameterSpec f4244e = null;

    /* renamed from: j */
    private String f4249j = "SHA-1";

    /* renamed from: c */
    private String f4242c = "PKCS1Padding";

    /* JADX WARN: Code restructure failed: missing block: B:30:0x006a, code lost:
        if (r6 != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009f, code lost:
        if (r6 != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a1, code lost:
        r6 = new byte[r5.f4243d.m20225a()];
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m20231a(int r6, com.baidu.p122b.p125c.p129d.InterfaceC2411d r7, java.security.SecureRandom r8, java.security.spec.AlgorithmParameterSpec r9) {
        /*
            r5 = this;
            r0 = 0
            r1 = 1
            switch(r6) {
                case 1: goto L1e;
                case 2: goto L1c;
                case 3: goto L1e;
                case 4: goto L1c;
                default: goto L5;
            }
        L5:
            java.security.InvalidKeyException r7 = new java.security.InvalidKeyException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Unknown mode: "
            r8.append(r9)
            r8.append(r6)
            java.lang.String r6 = r8.toString()
            r7.<init>(r6)
            throw r7
        L1c:
            r6 = r0
            goto L1f
        L1e:
            r6 = r1
        L1f:
            boolean r2 = r7 instanceof com.baidu.p122b.p125c.p129d.InterfaceC2411d
            if (r2 == 0) goto Lb3
            r2 = 4
            if (r6 == 0) goto L28
            r3 = r1
            goto L29
        L28:
            r3 = r2
        L29:
            r5.f4241b = r3
            r5.f4248i = r7
            com.baidu.b.c.d.d r7 = r5.f4248i
            java.math.BigInteger r7 = r7.mo20227a()
            int r7 = com.baidu.p122b.p125c.p129d.C2409b.m20239a(r7)
            r5.f4247h = r7
            r5.f4246g = r0
            java.lang.String r0 = r5.f4242c
            java.lang.String r3 = "NoPadding"
            r4 = 3
            if (r0 != r3) goto L57
            if (r9 != 0) goto L4f
            com.baidu.b.c.d.f r6 = com.baidu.p122b.p125c.p129d.C2413f.m20224a(r4, r7, r8)
            r5.f4243d = r6
        L4a:
            byte[] r6 = new byte[r7]
        L4c:
            r5.f4245f = r6
            goto Laa
        L4f:
            java.security.InvalidAlgorithmParameterException r6 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r7 = "Parameters not supported"
            r6.<init>(r7)
            throw r6
        L57:
            java.lang.String r3 = "PKCS1Padding"
            if (r0 != r3) goto L75
            if (r9 != 0) goto L6d
            int r9 = r5.f4241b
            r0 = 2
            if (r9 > r0) goto L63
            goto L64
        L63:
            r0 = r1
        L64:
            com.baidu.b.c.d.f r8 = com.baidu.p122b.p125c.p129d.C2413f.m20224a(r0, r7, r8)
            r5.f4243d = r8
            if (r6 == 0) goto L4a
            goto La1
        L6d:
            java.security.InvalidAlgorithmParameterException r6 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r7 = "Parameters not supported"
            r6.<init>(r7)
            throw r6
        L75:
            int r0 = r5.f4241b
            if (r0 == r4) goto Lab
            if (r0 == r2) goto Lab
            if (r9 == 0) goto L8c
            boolean r0 = r9 instanceof javax.crypto.spec.OAEPParameterSpec
            if (r0 == 0) goto L84
            javax.crypto.spec.OAEPParameterSpec r9 = (javax.crypto.spec.OAEPParameterSpec) r9
            goto L99
        L84:
            java.security.InvalidAlgorithmParameterException r6 = new java.security.InvalidAlgorithmParameterException
            java.lang.String r7 = "Wrong Parameters for OAEP Padding"
            r6.<init>(r7)
            throw r6
        L8c:
            javax.crypto.spec.OAEPParameterSpec r9 = new javax.crypto.spec.OAEPParameterSpec
            java.lang.String r0 = r5.f4249j
            java.lang.String r1 = "MGF1"
            java.security.spec.MGF1ParameterSpec r3 = java.security.spec.MGF1ParameterSpec.SHA1
            javax.crypto.spec.PSource$PSpecified r4 = javax.crypto.spec.PSource.PSpecified.DEFAULT
            r9.<init>(r0, r1, r3, r4)
        L99:
            com.baidu.b.c.d.f r8 = com.baidu.p122b.p125c.p129d.C2413f.m20223a(r2, r7, r8, r9)
            r5.f4243d = r8
            if (r6 == 0) goto L4a
        La1:
            com.baidu.b.c.d.f r6 = r5.f4243d
            int r6 = r6.m20225a()
            byte[] r6 = new byte[r6]
            goto L4c
        Laa:
            return
        Lab:
            java.security.InvalidKeyException r6 = new java.security.InvalidKeyException
            java.lang.String r7 = "OAEP cannot be used to sign or verify signatures"
            r6.<init>(r7)
            throw r6
        Lb3:
            java.security.InvalidKeyException r6 = new java.security.InvalidKeyException
            java.lang.String r7 = "only support helios key"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p122b.p125c.p129d.C2410c.m20231a(int, com.baidu.b.c.d.d, java.security.SecureRandom, java.security.spec.AlgorithmParameterSpec):void");
    }

    /* renamed from: a */
    private byte[] m20233a() {
        int i = this.f4246g;
        byte[] bArr = this.f4245f;
        if (i > bArr.length) {
            throw new IllegalBlockSizeException("Data must not be longer than " + this.f4245f.length + " bytes");
        }
        try {
            switch (this.f4241b) {
                case 1:
                    return C2409b.m20236a(this.f4243d.m20220a(bArr, 0, i), this.f4248i);
                case 2:
                    throw new UnsupportedOperationException("only verify supported");
                case 3:
                    throw new UnsupportedOperationException("only verify supported");
                case 4:
                    return this.f4243d.m20218b(C2409b.m20236a(C2409b.m20237a(bArr, 0, i), this.f4248i));
                default:
                    throw new AssertionError("Internal error");
            }
        } finally {
            this.f4246g = 0;
        }
    }

    /* renamed from: b */
    private void m20228b(byte[] bArr, int i, int i2) {
        int i3;
        if (i2 == 0 || bArr == null) {
            return;
        }
        int i4 = this.f4246g;
        int i5 = i4 + i2;
        byte[] bArr2 = this.f4245f;
        if (i5 > bArr2.length) {
            i3 = bArr2.length + 1;
        } else {
            System.arraycopy(bArr, i, bArr2, i4, i2);
            i3 = this.f4246g + i2;
        }
        this.f4246g = i3;
    }

    /* renamed from: a */
    public void m20232a(int i, InterfaceC2411d interfaceC2411d, SecureRandom secureRandom) {
        try {
            m20231a(i, interfaceC2411d, secureRandom, null);
        } catch (InvalidAlgorithmParameterException e) {
            InvalidKeyException invalidKeyException = new InvalidKeyException("Wrong parameters");
            invalidKeyException.initCause(e);
            throw invalidKeyException;
        }
    }

    /* renamed from: a */
    public void m20230a(String str) {
        String str2;
        if (str.equalsIgnoreCase("NoPadding")) {
            str2 = "NoPadding";
        } else if (str.equalsIgnoreCase("PKCS1Padding")) {
            str2 = "PKCS1Padding";
        } else {
            String lowerCase = str.toLowerCase(Locale.ENGLISH);
            if (!lowerCase.equals("oaeppadding")) {
                if (!lowerCase.startsWith("oaepwith") || !lowerCase.endsWith("andmgf1padding")) {
                    throw new NoSuchPaddingException("Padding " + str + " not supported");
                }
                this.f4242c = "OAEP";
                this.f4249j = str.substring(8, str.length() - 14);
                throw new NoSuchPaddingException("MessageDigest not available for " + str);
            }
            str2 = "OAEP";
        }
        this.f4242c = str2;
    }

    /* renamed from: a */
    public byte[] m20229a(byte[] bArr, int i, int i2) {
        m20228b(bArr, i, i2);
        return m20233a();
    }
}
