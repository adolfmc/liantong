package com.bytedance.pangle.p180g;

import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import android.util.Pair;
import android.util.SparseArray;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.g.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class C3847f {

    /* renamed from: a */
    static final HashMap<String, SparseArray<C3858m>> f9185a = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(api = 21)
    /* renamed from: a */
    public static void m16831a(Map<Integer, byte[]> map, RandomAccessFile randomAccessFile, C3858m c3858m) {
        if (map.isEmpty()) {
            throw new SecurityException("No digests provided");
        }
        ArrayMap arrayMap = new ArrayMap();
        boolean z = true;
        if (map.containsKey(1)) {
            arrayMap.put(1, map.get(1));
        }
        if (map.containsKey(2)) {
            arrayMap.put(2, map.get(2));
        }
        if (!arrayMap.isEmpty()) {
            try {
                m16832a(arrayMap, randomAccessFile.getFD(), c3858m);
                z = false;
            } catch (IOException e) {
                throw new SecurityException("Cannot get FD", e);
            }
        }
        if (map.containsKey(3)) {
            try {
                if (!Arrays.equals(m16830a(map.get(3), randomAccessFile.length(), c3858m), AbstractC3850g.m16820a(randomAccessFile, c3858m, new InterfaceC3854i() { // from class: com.bytedance.pangle.g.f.1
                    @Override // com.bytedance.pangle.p180g.InterfaceC3854i
                    /* renamed from: a */
                    public final ByteBuffer mo16812a(int i) {
                        return ByteBuffer.allocate(i);
                    }
                }).f9189b)) {
                    throw new SecurityException("APK verity digest of contents did not verify");
                }
                z = false;
            } catch (IOException | DigestException | NoSuchAlgorithmException e2) {
                throw new SecurityException("Error during verification", e2);
            }
        }
        if (z) {
            throw new SecurityException("No known digest exists for integrity check");
        }
    }

    /* renamed from: a */
    private static void m16832a(Map<Integer, byte[]> map, FileDescriptor fileDescriptor, C3858m c3858m) {
        int i;
        C3857l c3857l = new C3857l(fileDescriptor, 0L, c3858m.f9201b);
        C3857l c3857l2 = new C3857l(fileDescriptor, c3858m.f9202c, c3858m.f9203d - c3858m.f9202c);
        ByteBuffer duplicate = c3858m.f9204e.duplicate();
        duplicate.order(ByteOrder.LITTLE_ENDIAN);
        long j = c3858m.f9201b;
        AbstractC3864s.m16804a(duplicate);
        int position = duplicate.position() + 16;
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("uint32 value of out range: ".concat(String.valueOf(j)));
        }
        duplicate.putInt(duplicate.position() + position, (int) j);
        C3853h c3853h = new C3853h(duplicate);
        int[] iArr = new int[map.size()];
        int i2 = 0;
        for (Integer num : map.keySet()) {
            iArr[i2] = num.intValue();
            i2++;
        }
        try {
            byte[][] m16829a = m16829a(iArr, new InterfaceC3856k[]{c3857l, c3857l2, c3853h});
            for (int i3 = 0; i3 < iArr.length; i3++) {
                if (!MessageDigest.isEqual(map.get(Integer.valueOf(iArr[i3])), m16829a[i3])) {
                    throw new SecurityException(m16828b(i) + " digest of contents did not verify");
                }
            }
        } catch (DigestException e) {
            throw new SecurityException("Failed to compute digest(s) of contents", e);
        }
    }

    /* renamed from: a */
    private static byte[][] m16829a(int[] iArr, InterfaceC3856k[] interfaceC3856kArr) {
        int i;
        long j;
        MessageDigest messageDigest;
        long j2 = 0;
        int i2 = 0;
        long j3 = 0;
        int i3 = 0;
        while (true) {
            i = 3;
            j = 1048576;
            if (i3 >= 3) {
                break;
            }
            j3 += ((interfaceC3856kArr[i3].mo16810a() + 1048576) - 1) / 1048576;
            i3++;
        }
        if (j3 >= 2097151) {
            throw new DigestException("Too many chunks: ".concat(String.valueOf(j3)));
        }
        int i4 = (int) j3;
        byte[][] bArr = new byte[iArr.length];
        for (int i5 = 0; i5 < iArr.length; i5++) {
            byte[] bArr2 = new byte[(m16823e(iArr[i5]) * i4) + 5];
            bArr2[0] = 90;
            m16836a(i4, bArr2);
            bArr[i5] = bArr2;
        }
        byte[] bArr3 = new byte[5];
        bArr3[0] = -91;
        MessageDigest[] messageDigestArr = new MessageDigest[iArr.length];
        for (int i6 = 0; i6 < iArr.length; i6++) {
            String m16828b = m16828b(iArr[i6]);
            try {
                messageDigestArr[i6] = MessageDigest.getInstance(m16828b);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(m16828b + " digest not supported", e);
            }
        }
        C3849a c3849a = new C3849a(messageDigestArr);
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 < i) {
            InterfaceC3856k interfaceC3856k = interfaceC3856kArr[i7];
            long j4 = j2;
            int i10 = i7;
            long mo16810a = interfaceC3856k.mo16810a();
            while (mo16810a > j2) {
                int min = (int) Math.min(mo16810a, j);
                m16836a(min, bArr3);
                for (int i11 = i2; i11 < messageDigestArr.length; i11++) {
                    messageDigestArr[i11].update(bArr3);
                }
                long j5 = j4;
                try {
                    interfaceC3856k.mo16809a(c3849a, j5, min);
                    int i12 = 0;
                    while (i12 < iArr.length) {
                        int i13 = iArr[i12];
                        byte[] bArr4 = bArr[i12];
                        int m16823e = m16823e(i13);
                        byte[] bArr5 = bArr3;
                        MessageDigest[] messageDigestArr2 = messageDigestArr;
                        int digest = messageDigestArr[i12].digest(bArr4, (i9 * m16823e) + 5, m16823e);
                        if (digest != m16823e) {
                            throw new RuntimeException("Unexpected output size of " + messageDigest.getAlgorithm() + " digest: " + digest);
                        }
                        i12++;
                        bArr3 = bArr5;
                        messageDigestArr = messageDigestArr2;
                    }
                    MessageDigest[] messageDigestArr3 = messageDigestArr;
                    long j6 = min;
                    long j7 = j5 + j6;
                    mo16810a -= j6;
                    i9++;
                    messageDigestArr = messageDigestArr3;
                    i2 = 0;
                    j = 1048576;
                    j4 = j7;
                    j2 = 0;
                } catch (IOException e2) {
                    throw new DigestException("Failed to digest chunk #" + i9 + " of section #" + i8, e2);
                }
            }
            i8++;
            i7 = i10 + 1;
            j2 = 0;
            i2 = 0;
            i = 3;
            j = 1048576;
        }
        byte[][] bArr6 = new byte[iArr.length];
        for (int i14 = 0; i14 < iArr.length; i14++) {
            int i15 = iArr[i14];
            byte[] bArr7 = bArr[i14];
            String m16828b2 = m16828b(i15);
            try {
                bArr6[i14] = MessageDigest.getInstance(m16828b2).digest(bArr7);
            } catch (NoSuchAlgorithmException e3) {
                throw new RuntimeException(m16828b2 + " digest not supported", e3);
            }
        }
        return bArr6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static byte[] m16830a(byte[] bArr, long j, C3858m c3858m) {
        if (bArr.length != 40) {
            throw new SecurityException("Verity digest size is wrong: " + bArr.length);
        }
        ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        order.position(32);
        if (order.getLong() != j - (c3858m.f9202c - c3858m.f9201b)) {
            throw new SecurityException("APK content size did not verify");
        }
        return Arrays.copyOfRange(bArr, 0, 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m16837a(int i, int i2) {
        int m16838a = m16838a(i);
        int m16838a2 = m16838a(i2);
        switch (m16838a) {
            case 1:
                switch (m16838a2) {
                    case 1:
                        return 0;
                    case 2:
                    case 3:
                        return -1;
                    default:
                        throw new IllegalArgumentException("Unknown digestAlgorithm2: ".concat(String.valueOf(m16838a2)));
                }
            case 2:
                switch (m16838a2) {
                    case 1:
                    case 3:
                        return 1;
                    case 2:
                        return 0;
                    default:
                        throw new IllegalArgumentException("Unknown digestAlgorithm2: ".concat(String.valueOf(m16838a2)));
                }
            case 3:
                switch (m16838a2) {
                    case 1:
                        return 1;
                    case 2:
                        return -1;
                    case 3:
                        return 0;
                    default:
                        throw new IllegalArgumentException("Unknown digestAlgorithm2: ".concat(String.valueOf(m16838a2)));
                }
            default:
                throw new IllegalArgumentException("Unknown digestAlgorithm1: ".concat(String.valueOf(m16838a)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m16838a(int i) {
        if (i != 769) {
            if (i == 1057 || i == 1059 || i == 1061) {
                return 3;
            }
            switch (i) {
                case 257:
                case 259:
                    return 1;
                case 258:
                case 260:
                    return 2;
                default:
                    switch (i) {
                        case 513:
                            return 1;
                        case 514:
                            return 2;
                        default:
                            throw new IllegalArgumentException("Unknown signature algorithm: 0x" + Long.toHexString(i & (-1)));
                    }
            }
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static String m16828b(int i) {
        switch (i) {
            case 1:
            case 3:
                return "SHA-256";
            case 2:
                return "SHA-512";
            default:
                throw new IllegalArgumentException("Unknown content digest algorthm: ".concat(String.valueOf(i)));
        }
    }

    /* renamed from: e */
    private static int m16823e(int i) {
        switch (i) {
            case 1:
            case 3:
                return 32;
            case 2:
                return 64;
            default:
                throw new IllegalArgumentException("Unknown content digest algorthm: ".concat(String.valueOf(i)));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static String m16825c(int i) {
        if (i != 769) {
            if (i != 1057) {
                if (i != 1059) {
                    if (i != 1061) {
                        switch (i) {
                            case 257:
                            case 258:
                            case 259:
                            case 260:
                                return "RSA";
                            default:
                                switch (i) {
                                    case 513:
                                    case 514:
                                        return "EC";
                                    default:
                                        throw new IllegalArgumentException("Unknown signature algorithm: 0x" + Long.toHexString(i & (-1)));
                                }
                        }
                    }
                    return "DSA";
                }
                return "EC";
            }
            return "RSA";
        }
        return "DSA";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static Pair<String, ? extends AlgorithmParameterSpec> m16824d(int i) {
        if (i != 769) {
            if (i != 1057) {
                if (i != 1059) {
                    if (i != 1061) {
                        switch (i) {
                            case 257:
                                return Pair.create("SHA256withRSA/PSS", new PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                            case 258:
                                return Pair.create("SHA512withRSA/PSS", new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1));
                            case 259:
                                break;
                            case 260:
                                return Pair.create("SHA512withRSA", null);
                            default:
                                switch (i) {
                                    case 513:
                                        break;
                                    case 514:
                                        return Pair.create("SHA512withECDSA", null);
                                    default:
                                        throw new IllegalArgumentException("Unknown signature algorithm: 0x" + Long.toHexString(i & (-1)));
                                }
                        }
                    }
                }
                return Pair.create("SHA256withECDSA", null);
            }
            return Pair.create("SHA256withRSA", null);
        }
        return Pair.create("SHA256withDSA", null);
    }

    /* renamed from: a */
    private static ByteBuffer m16833a(ByteBuffer byteBuffer, int i) {
        if (i < 8) {
            throw new IllegalArgumentException("end < start: " + i + " < 8");
        }
        int capacity = byteBuffer.capacity();
        if (i > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i + " > " + capacity);
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i);
            byteBuffer.position(8);
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            return slice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(limit);
            byteBuffer.position(position);
        }
    }

    /* renamed from: b */
    private static ByteBuffer m16826b(ByteBuffer byteBuffer, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("size: ".concat(String.valueOf(i)));
        }
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (i2 < position || i2 > limit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i2);
        try {
            ByteBuffer slice = byteBuffer.slice();
            slice.order(byteBuffer.order());
            byteBuffer.position(i2);
            return slice;
        } finally {
            byteBuffer.limit(limit);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static ByteBuffer m16834a(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 4) {
            throw new IOException("Remaining buffer too short to contain length of length-prefixed field. Remaining: " + byteBuffer.remaining());
        }
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new IllegalArgumentException("Negative length");
        }
        if (i > byteBuffer.remaining()) {
            throw new IOException("Length-prefixed field longer than remaining buffer. Field length: " + i + ", remaining: " + byteBuffer.remaining());
        }
        return m16826b(byteBuffer, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static byte[] m16827b(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new IOException("Negative length");
        }
        if (i > byteBuffer.remaining()) {
            throw new IOException("Underflow while reading length-prefixed value. Length: " + i + ", available: " + byteBuffer.remaining());
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    /* renamed from: a */
    private static void m16836a(int i, byte[] bArr) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >>> 8) & 255);
        bArr[3] = (byte) ((i >>> 16) & 255);
        bArr[4] = (byte) ((i >>> 24) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0248  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m16835a(java.lang.String r23, java.io.RandomAccessFile r24, int... r25) {
        /*
            Method dump skipped, instructions count: 600
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p180g.C3847f.m16835a(java.lang.String, java.io.RandomAccessFile, int[]):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.g.f$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3849a implements InterfaceC3855j {

        /* renamed from: a */
        private final MessageDigest[] f9186a;

        C3849a(MessageDigest[] messageDigestArr) {
            this.f9186a = messageDigestArr;
        }

        @Override // com.bytedance.pangle.p180g.InterfaceC3855j
        /* renamed from: a */
        public final void mo16811a(ByteBuffer byteBuffer) {
            MessageDigest[] messageDigestArr;
            ByteBuffer slice = byteBuffer.slice();
            for (MessageDigest messageDigest : this.f9186a) {
                slice.position(0);
                messageDigest.update(slice);
            }
        }
    }
}
