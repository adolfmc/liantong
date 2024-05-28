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
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(api = 21)
/* renamed from: com.bytedance.pangle.g.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3839b {
    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C3840a m16848a(RandomAccessFile randomAccessFile, C3858m c3858m) {
        ArrayMap arrayMap = new ArrayMap();
        ArrayList arrayList = new ArrayList();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            try {
                ByteBuffer m16834a = C3847f.m16834a(c3858m.f9200a);
                int i = 0;
                while (m16834a.hasRemaining()) {
                    i++;
                    try {
                        arrayList.add(m16846a(C3847f.m16834a(m16834a), arrayMap, certificateFactory));
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        throw new SecurityException("Failed to parse/verify signer #" + i + " block", e);
                    }
                }
                if (i <= 0) {
                    throw new SecurityException("No signers found");
                }
                if (arrayMap.isEmpty()) {
                    throw new SecurityException("No content digests found");
                }
                C3847f.m16831a(arrayMap, randomAccessFile, c3858m);
                return new C3840a((X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()]), arrayMap.containsKey(3) ? C3847f.m16830a((byte[]) arrayMap.get(3), randomAccessFile.length(), c3858m) : null);
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }

    /* renamed from: a */
    private static X509Certificate[] m16846a(ByteBuffer byteBuffer, Map<Integer, byte[]> map, CertificateFactory certificateFactory) {
        int m16838a;
        ByteBuffer m16834a = C3847f.m16834a(byteBuffer);
        ByteBuffer m16834a2 = C3847f.m16834a(byteBuffer);
        byte[] m16827b = C3847f.m16827b(byteBuffer);
        ArrayList arrayList = new ArrayList();
        byte[] bArr = null;
        int i = -1;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (!m16834a2.hasRemaining()) {
                if (i == -1) {
                    if (i2 == 0) {
                        throw new SecurityException("No signatures found");
                    }
                    throw new SecurityException("No supported signatures found");
                }
                String m16825c = C3847f.m16825c(i);
                Pair<String, ? extends AlgorithmParameterSpec> m16824d = C3847f.m16824d(i);
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
                    byte[] bArr2 = null;
                    int i3 = 0;
                    while (m16834a3.hasRemaining()) {
                        i3++;
                        try {
                            ByteBuffer m16834a4 = C3847f.m16834a(m16834a3);
                            if (m16834a4.remaining() < 8) {
                                throw new IOException("Record too short");
                            }
                            int i4 = m16834a4.getInt();
                            arrayList2.add(Integer.valueOf(i4));
                            if (i4 == i) {
                                bArr2 = C3847f.m16827b(m16834a4);
                            }
                        } catch (IOException | BufferUnderflowException e) {
                            throw new IOException("Failed to parse digest record #".concat(String.valueOf(i3)), e);
                        }
                    }
                    if (!arrayList.equals(arrayList2)) {
                        throw new SecurityException("Signature algorithms don't match between digests and signatures records");
                    }
                    byte[] put = map.put(Integer.valueOf(C3847f.m16838a(i)), bArr2);
                    if (put != null && !MessageDigest.isEqual(put, bArr2)) {
                        throw new SecurityException(C3847f.m16828b(m16838a) + " contents digest does not match the digest specified by a preceding signer");
                    }
                    ByteBuffer m16834a5 = C3847f.m16834a(m16834a);
                    ArrayList arrayList3 = new ArrayList();
                    int i5 = 0;
                    while (m16834a5.hasRemaining()) {
                        i5++;
                        byte[] m16827b2 = C3847f.m16827b(m16834a5);
                        try {
                            arrayList3.add(new C3861p((X509Certificate) certificateFactory.generateCertificate(new ByteArrayInputStream(m16827b2)), m16827b2));
                        } catch (CertificateException e2) {
                            throw new SecurityException("Failed to decode certificate #".concat(String.valueOf(i5)), e2);
                        }
                    }
                    if (arrayList3.isEmpty()) {
                        throw new SecurityException("No certificates listed");
                    }
                    if (!Arrays.equals(m16827b, ((X509Certificate) arrayList3.get(0)).getPublicKey().getEncoded())) {
                        throw new SecurityException("Public key mismatch between certificate and signature record");
                    }
                    m16847a(C3847f.m16834a(m16834a));
                    return (X509Certificate[]) arrayList3.toArray(new X509Certificate[arrayList3.size()]);
                } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e3) {
                    throw new SecurityException("Failed to verify " + str + " signature", e3);
                }
            }
            i2++;
            try {
                ByteBuffer m16834a6 = C3847f.m16834a(m16834a2);
                if (m16834a6.remaining() < 8) {
                    throw new SecurityException("Signature record too short");
                }
                int i6 = m16834a6.getInt();
                arrayList.add(Integer.valueOf(i6));
                if (i6 != 769 && i6 != 1057 && i6 != 1059 && i6 != 1061) {
                    switch (i6) {
                        case 257:
                        case 258:
                        case 259:
                        case 260:
                            break;
                        default:
                            switch (i6) {
                                case 513:
                                case 514:
                                    break;
                                default:
                                    z = false;
                                    break;
                            }
                    }
                }
                if (z && (i == -1 || C3847f.m16837a(i6, i) > 0)) {
                    bArr = C3847f.m16827b(m16834a6);
                    i = i6;
                }
            } catch (IOException | BufferUnderflowException e4) {
                throw new SecurityException("Failed to parse signature record #".concat(String.valueOf(i2)), e4);
            }
        }
    }

    /* renamed from: a */
    private static void m16847a(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining()) {
            ByteBuffer m16834a = C3847f.m16834a(byteBuffer);
            if (m16834a.remaining() < 4) {
                throw new IOException("Remaining buffer too short to contain additional attribute ID. Remaining: " + m16834a.remaining());
            } else if (m16834a.getInt() == -1091571699) {
                if (m16834a.remaining() < 4) {
                    throw new IOException("V2 Signature Scheme Stripping Protection Attribute  value too small. Expected 4 bytes, but found " + m16834a.remaining());
                } else if (m16834a.getInt() == 3) {
                    throw new SecurityException("V2 signature indicates APK is signed using APK Signature Scheme v3, but none was found. Signature stripped?");
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.g.b$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3840a {

        /* renamed from: a */
        public final X509Certificate[][] f9178a;

        /* renamed from: b */
        public final byte[] f9179b;

        public C3840a(X509Certificate[][] x509CertificateArr, byte[] bArr) {
            this.f9178a = x509CertificateArr;
            this.f9179b = bArr;
        }
    }
}
