package com.bytedance.pangle.p180g;

import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.Pair;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(api = 21)
/* renamed from: com.bytedance.pangle.g.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3841c {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.g.c$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3842a extends Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C3844c m16845a(RandomAccessFile randomAccessFile, C3858m c3858m) {
        ArrayMap arrayMap = new ArrayMap();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer m16834a = C3847f.m16834a(c3858m.f9200a);
                int i = 0;
                C3844c c3844c = null;
                while (m16834a.hasRemaining()) {
                    try {
                        c3844c = m16842a(C3847f.m16834a(m16834a), arrayMap, certificateFactory);
                        i++;
                    } catch (C3842a unused) {
                    } catch (IOException e) {
                        e = e;
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    } catch (SecurityException e2) {
                        e = e2;
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    } catch (BufferUnderflowException e3) {
                        e = e3;
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    }
                }
                if (i <= 0 || c3844c == null) {
                    throw new SecurityException("No signers found");
                }
                if (i != 1) {
                    throw new SecurityException("APK Signature Scheme V3 only supports one signer: multiple signers found.");
                }
                if (arrayMap.isEmpty()) {
                    throw new SecurityException("No content digests found");
                }
                C3847f.m16831a(arrayMap, randomAccessFile, c3858m);
                if (arrayMap.containsKey(3)) {
                    c3844c.f9184c = C3847f.m16830a((byte[]) arrayMap.get(3), randomAccessFile.length(), c3858m);
                }
                return c3844c;
            } catch (IOException e4) {
                throw new SecurityException("Failed to read list of signers", e4);
            }
        } catch (CertificateException e5) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e5);
        }
    }

    /* renamed from: a */
    private static C3844c m16842a(ByteBuffer byteBuffer, Map<Integer, byte[]> map, CertificateFactory certificateFactory) {
        int m16838a;
        ByteBuffer m16834a = C3847f.m16834a(byteBuffer);
        int i = byteBuffer.getInt();
        int i2 = byteBuffer.getInt();
        ByteBuffer m16834a2 = C3847f.m16834a(byteBuffer);
        byte[] m16827b = C3847f.m16827b(byteBuffer);
        ArrayList arrayList = new ArrayList();
        int i3 = -1;
        int i4 = 0;
        byte[] bArr = null;
        while (true) {
            int i5 = 8;
            boolean z = true;
            if (!m16834a2.hasRemaining()) {
                if (i3 == -1) {
                    if (i4 == 0) {
                        throw new SecurityException("No signatures found");
                    }
                    throw new SecurityException("No supported signatures found");
                }
                String m16825c = C3847f.m16825c(i3);
                Pair<String, ? extends AlgorithmParameterSpec> m16824d = C3847f.m16824d(i3);
                String str = (String) m16824d.first;
                AlgorithmParameterSpec algorithmParameterSpec = (AlgorithmParameterSpec) m16824d.second;
                try {
                    PublicKey generatePublic = KeyFactory.getInstance(m16825c).generatePublic(new X509EncodedKeySpec(m16827b));
                    Signature signature = Signature.getInstance(str);
                    signature.initVerify(generatePublic);
                    if (algorithmParameterSpec != null) {
                        signature.setParameter(algorithmParameterSpec);
                    }
                    signature.update(m16834a);
                    if (!signature.verify(bArr)) {
                        throw new SecurityException(str + " signature did not verify");
                    }
                    m16834a.clear();
                    ByteBuffer m16834a3 = C3847f.m16834a(m16834a);
                    ArrayList arrayList2 = new ArrayList();
                    int i6 = 0;
                    byte[] bArr2 = null;
                    while (m16834a3.hasRemaining()) {
                        i6++;
                        try {
                            ByteBuffer m16834a4 = C3847f.m16834a(m16834a3);
                            if (m16834a4.remaining() < i5) {
                                throw new IOException("Record too short");
                            }
                            int i7 = m16834a4.getInt();
                            arrayList2.add(Integer.valueOf(i7));
                            if (i7 == i3) {
                                bArr2 = C3847f.m16827b(m16834a4);
                            }
                            i5 = 8;
                        } catch (IOException | BufferUnderflowException e) {
                            throw new IOException("Failed to parse digest record #".concat(String.valueOf(i6)), e);
                        }
                    }
                    if (!arrayList.equals(arrayList2)) {
                        throw new SecurityException("Signature algorithms don't match between digests and signatures records");
                    }
                    byte[] put = map.put(Integer.valueOf(C3847f.m16838a(i3)), bArr2);
                    if (put != null && !MessageDigest.isEqual(put, bArr2)) {
                        throw new SecurityException(C3847f.m16828b(m16838a) + " contents digest does not match the digest specified by a preceding signer");
                    }
                    ByteBuffer m16834a5 = C3847f.m16834a(m16834a);
                    ArrayList arrayList3 = new ArrayList();
                    int i8 = 0;
                    while (m16834a5.hasRemaining()) {
                        i8++;
                        byte[] m16827b2 = C3847f.m16827b(m16834a5);
                        try {
                            arrayList3.add(new C3861p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(m16827b2)), m16827b2));
                        } catch (CertificateException e2) {
                            throw new SecurityException("Failed to decode certificate #".concat(String.valueOf(i8)), e2);
                        }
                    }
                    if (arrayList3.isEmpty()) {
                        throw new SecurityException("No certificates listed");
                    }
                    if (!Arrays.equals(m16827b, ((X509Certificate) arrayList3.get(0)).getPublicKey().getEncoded())) {
                        throw new SecurityException("Public key mismatch between certificate and signature record");
                    }
                    if (m16834a.getInt() != i) {
                        throw new SecurityException("minSdkVersion mismatch between signed and unsigned in v3 signer block.");
                    }
                    if (m16834a.getInt() != i2) {
                        throw new SecurityException("maxSdkVersion mismatch between signed and unsigned in v3 signer block.");
                    }
                    return m16843a(C3847f.m16834a(m16834a), arrayList3, certificateFactory);
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e3) {
                    throw new SecurityException("Failed to verify " + str + " signature", e3);
                }
            }
            i4++;
            try {
                ByteBuffer m16834a6 = C3847f.m16834a(m16834a2);
                if (m16834a6.remaining() < 8) {
                    throw new SecurityException("Signature record too short");
                }
                int i9 = m16834a6.getInt();
                arrayList.add(Integer.valueOf(i9));
                if (i9 != 769 && i9 != 1057 && i9 != 1059 && i9 != 1061) {
                    switch (i9) {
                        case 257:
                        case 258:
                        case 259:
                        case 260:
                            break;
                        default:
                            switch (i9) {
                                case 513:
                                case 514:
                                    break;
                                default:
                                    z = false;
                                    break;
                            }
                    }
                }
                if (z && (i3 == -1 || C3847f.m16837a(i9, i3) > 0)) {
                    bArr = C3847f.m16827b(m16834a6);
                    i3 = i9;
                }
            } catch (IOException | BufferUnderflowException e4) {
                throw new SecurityException("Failed to parse signature record #".concat(String.valueOf(i4)), e4);
            }
        }
    }

    /* renamed from: a */
    private static C3844c m16843a(ByteBuffer byteBuffer, List<X509Certificate> list, CertificateFactory certificateFactory) {
        X509Certificate[] x509CertificateArr = (X509Certificate[]) list.toArray(new X509Certificate[list.size()]);
        C3843b c3843b = null;
        while (byteBuffer.hasRemaining()) {
            ByteBuffer m16834a = C3847f.m16834a(byteBuffer);
            if (m16834a.remaining() < 4) {
                throw new IOException("Remaining buffer too short to contain additional attribute ID. Remaining: " + m16834a.remaining());
            } else if (m16834a.getInt() == 1000370060) {
                if (c3843b != null) {
                    throw new SecurityException("Encountered multiple Proof-of-rotation records when verifying APK Signature Scheme v3 signature");
                }
                c3843b = m16844a(m16834a, certificateFactory);
                try {
                    if (c3843b.f9180a.size() > 0 && !Arrays.equals(c3843b.f9180a.get(c3843b.f9180a.size() - 1).getEncoded(), x509CertificateArr[0].getEncoded())) {
                        throw new SecurityException("Terminal certificate in Proof-of-rotation record does not match APK signing certificate");
                    }
                } catch (CertificateEncodingException e) {
                    throw new SecurityException("Failed to encode certificate when comparing Proof-of-rotation record and signing certificate", e);
                }
            }
        }
        return new C3844c(x509CertificateArr, c3843b);
    }

    /* renamed from: a */
    private static C3843b m16844a(ByteBuffer byteBuffer, CertificateFactory certificateFactory) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        try {
            byteBuffer.getInt();
            HashSet hashSet = new HashSet();
            int i2 = -1;
            C3861p c3861p = null;
            while (byteBuffer.hasRemaining()) {
                i++;
                ByteBuffer m16834a = C3847f.m16834a(byteBuffer);
                ByteBuffer m16834a2 = C3847f.m16834a(m16834a);
                int i3 = m16834a.getInt();
                int i4 = m16834a.getInt();
                byte[] m16827b = C3847f.m16827b(m16834a);
                if (c3861p != null) {
                    Pair<String, ? extends AlgorithmParameterSpec> m16824d = C3847f.m16824d(i2);
                    PublicKey publicKey = c3861p.getPublicKey();
                    Signature signature = Signature.getInstance((String) m16824d.first);
                    signature.initVerify(publicKey);
                    if (m16824d.second != null) {
                        signature.setParameter((AlgorithmParameterSpec) m16824d.second);
                    }
                    signature.update(m16834a2);
                    if (!signature.verify(m16827b)) {
                        throw new SecurityException("Unable to verify signature of certificate #" + i + " using " + ((String) m16824d.first) + " when verifying Proof-of-rotation record");
                    }
                }
                m16834a2.rewind();
                byte[] m16827b2 = C3847f.m16827b(m16834a2);
                int i5 = m16834a2.getInt();
                if (c3861p != null && i2 != i5) {
                    throw new SecurityException("Signing algorithm ID mismatch for certificate #" + i + " when verifying Proof-of-rotation record");
                }
                c3861p = new C3861p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(m16827b2)), m16827b2);
                if (hashSet.contains(c3861p)) {
                    throw new SecurityException("Encountered duplicate entries in Proof-of-rotation record at certificate #" + i + ".  All signing certificates should be unique");
                }
                hashSet.add(c3861p);
                arrayList.add(c3861p);
                arrayList2.add(Integer.valueOf(i3));
                i2 = i4;
            }
            return new C3843b(arrayList, arrayList2);
        } catch (IOException e) {
            e = e;
            throw new IOException("Failed to parse Proof-of-rotation record", e);
        } catch (BufferUnderflowException e2) {
            e = e2;
            throw new IOException("Failed to parse Proof-of-rotation record", e);
        } catch (InvalidAlgorithmParameterException e3) {
            e = e3;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (InvalidKeyException e4) {
            e = e4;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (NoSuchAlgorithmException e5) {
            e = e5;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (SignatureException e6) {
            e = e6;
            throw new SecurityException("Failed to verify signature over signed data for certificate #0 when verifying Proof-of-rotation record", e);
        } catch (CertificateException e7) {
            throw new SecurityException("Failed to decode certificate #0 when verifying Proof-of-rotation record", e7);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.g.c$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3843b {

        /* renamed from: a */
        public final List<X509Certificate> f9180a;

        /* renamed from: b */
        public final List<Integer> f9181b;

        public C3843b(List<X509Certificate> list, List<Integer> list2) {
            this.f9180a = list;
            this.f9181b = list2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.g.c$c */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3844c {

        /* renamed from: a */
        public final X509Certificate[] f9182a;

        /* renamed from: b */
        public final C3843b f9183b;

        /* renamed from: c */
        public byte[] f9184c;

        public C3844c(X509Certificate[] x509CertificateArr, C3843b c3843b) {
            this.f9182a = x509CertificateArr;
            this.f9183b = c3843b;
        }
    }
}
