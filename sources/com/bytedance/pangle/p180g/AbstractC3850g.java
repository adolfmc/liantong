package com.bytedance.pangle.p180g;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.g.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
abstract class AbstractC3850g {

    /* renamed from: a */
    private static final byte[] f9187a = new byte[8];

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.g.g$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C3851a {

        /* renamed from: a */
        public final ByteBuffer f9188a;

        /* renamed from: b */
        public final byte[] f9189b;

        C3851a(ByteBuffer byteBuffer, byte[] bArr) {
            this.f9188a = byteBuffer;
            this.f9189b = bArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C3851a m16820a(RandomAccessFile randomAccessFile, C3858m c3858m, InterfaceC3854i interfaceC3854i) {
        int[] m16822a = m16822a(randomAccessFile.length() - (c3858m.f9202c - c3858m.f9201b));
        int i = m16822a[m16822a.length - 1];
        int i2 = i + 4096;
        ByteBuffer mo16812a = interfaceC3854i.mo16812a(i2);
        mo16812a.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer m16817a = m16817a(mo16812a, 0, i);
        int i3 = i + 64;
        ByteBuffer m16817a2 = m16817a(mo16812a, i, i3);
        ByteBuffer m16817a3 = m16817a(mo16812a, i3, i2);
        byte[] bArr = new byte[32];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        if (c3858m.f9201b % 4096 != 0) {
            throw new IllegalArgumentException("APK Signing Block does not start at the page  boundary: " + c3858m.f9201b);
        } else if ((c3858m.f9202c - c3858m.f9201b) % 4096 == 0) {
            long j = c3858m.f9202c - c3858m.f9201b;
            int[] m16822a2 = m16822a(randomAccessFile.length() - j);
            if (m16817a != null) {
                byte[] m16819a = m16819a(randomAccessFile, c3858m, f9187a, m16822a2, m16817a);
                if (wrap != null) {
                    wrap.put(m16819a);
                    wrap.flip();
                }
            }
            if (m16817a2 != null) {
                m16817a2.order(ByteOrder.LITTLE_ENDIAN);
                long length = randomAccessFile.length();
                byte[] bArr2 = f9187a;
                if (bArr2.length != 8) {
                    throw new IllegalArgumentException("salt is not 8 bytes long");
                }
                m16817a2.put("TrueBrew".getBytes());
                m16817a2.put((byte) 1);
                m16817a2.put((byte) 0);
                m16817a2.put((byte) 12);
                m16817a2.put((byte) 7);
                m16817a2.putShort((short) 1);
                m16817a2.putShort((short) 1);
                m16817a2.putInt(0);
                m16817a2.putInt(0);
                m16817a2.putLong(length);
                m16817a2.put((byte) 2);
                m16817a2.put((byte) 0);
                m16817a2.put(bArr2);
                m16818a(m16817a2, 22);
                m16817a2.flip();
            }
            if (m16817a3 != null) {
                m16817a3.order(ByteOrder.LITTLE_ENDIAN);
                long j2 = c3858m.f9201b;
                long j3 = c3858m.f9203d;
                m16817a3.putInt(24);
                m16817a3.putShort((short) 1);
                m16818a(m16817a3, 2);
                m16817a3.putLong(j2);
                m16817a3.putLong(j);
                m16817a3.putInt(20);
                m16817a3.putShort((short) 2);
                m16818a(m16817a3, 2);
                m16817a3.putLong(j3 + 16);
                m16817a3.putInt(m16815c(j2));
                m16818a(m16817a3, 4);
                m16817a3.flip();
            }
            mo16812a.position(i3 + m16817a3.limit());
            mo16812a.putInt(m16817a3.limit() + 64 + 4);
            mo16812a.flip();
            return new C3851a(mo16812a, bArr);
        } else {
            throw new IllegalArgumentException("Size of APK Signing Block is not a multiple of 4096: " + (c3858m.f9202c - c3858m.f9201b));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.pangle.g.g$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3852b implements InterfaceC3855j {

        /* renamed from: a */
        private int f9190a;

        /* renamed from: b */
        private final ByteBuffer f9191b;

        /* renamed from: c */
        private final MessageDigest f9192c;

        /* renamed from: d */
        private final byte[] f9193d;

        /* renamed from: e */
        private final byte[] f9194e;

        /* synthetic */ C3852b(byte[] bArr, ByteBuffer byteBuffer, byte b) {
            this(bArr, byteBuffer);
        }

        private C3852b(byte[] bArr, ByteBuffer byteBuffer) {
            this.f9193d = new byte[32];
            this.f9194e = bArr;
            this.f9191b = byteBuffer.slice();
            this.f9192c = MessageDigest.getInstance("SHA-256");
            this.f9192c.update(this.f9194e);
            this.f9190a = 0;
        }

        @Override // com.bytedance.pangle.p180g.InterfaceC3855j
        /* renamed from: a */
        public final void mo16811a(ByteBuffer byteBuffer) {
            byteBuffer.position();
            int remaining = byteBuffer.remaining();
            while (remaining > 0) {
                int min = Math.min(remaining, 4096 - this.f9190a);
                byteBuffer.limit(byteBuffer.position() + min);
                this.f9192c.update(byteBuffer);
                remaining -= min;
                this.f9190a += min;
                if (this.f9190a == 4096) {
                    MessageDigest messageDigest = this.f9192c;
                    byte[] bArr = this.f9193d;
                    messageDigest.digest(bArr, 0, bArr.length);
                    this.f9191b.put(this.f9193d);
                    this.f9192c.update(this.f9194e);
                    this.f9190a = 0;
                }
            }
        }

        /* renamed from: a */
        public final void m16814a() {
            if (this.f9190a == 0) {
                return;
            }
            throw new IllegalStateException("Buffer is not empty: " + this.f9190a);
        }

        /* renamed from: b */
        final void m16813b() {
            int position = this.f9191b.position() % 4096;
            if (position == 0) {
                return;
            }
            this.f9191b.put(ByteBuffer.allocate(4096 - position));
        }
    }

    /* renamed from: a */
    private static void m16821a(InterfaceC3855j interfaceC3855j, InterfaceC3856k interfaceC3856k, int i) {
        long mo16810a = interfaceC3856k.mo16810a();
        long j = 0;
        while (mo16810a > 0) {
            int min = (int) Math.min(mo16810a, i);
            interfaceC3856k.mo16809a(interfaceC3855j, j, min);
            long j2 = min;
            j += j2;
            mo16810a -= j2;
        }
    }

    /* renamed from: a */
    private static byte[] m16819a(RandomAccessFile randomAccessFile, C3858m c3858m, byte[] bArr, int[] iArr, ByteBuffer byteBuffer) {
        C3852b c3852b = new C3852b(bArr, m16817a(byteBuffer, iArr[iArr.length - 2], iArr[iArr.length - 1]), (byte) 0);
        m16821a(c3852b, new C3857l(randomAccessFile.getFD(), 0L, c3858m.f9201b), 1048576);
        long j = c3858m.f9203d + 16;
        m16821a(c3852b, new C3857l(randomAccessFile.getFD(), c3858m.f9202c, j - c3858m.f9202c), 1048576);
        ByteBuffer order = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        order.putInt(m16815c(c3858m.f9201b));
        order.flip();
        c3852b.mo16811a(order);
        long j2 = j + 4;
        m16821a(c3852b, new C3857l(randomAccessFile.getFD(), j2, randomAccessFile.length() - j2), 1048576);
        int length = (int) (randomAccessFile.length() % 4096);
        if (length != 0) {
            c3852b.mo16811a(ByteBuffer.allocate(4096 - length));
        }
        c3852b.m16814a();
        c3852b.m16813b();
        for (int length2 = iArr.length - 3; length2 >= 0; length2--) {
            int i = length2 + 1;
            ByteBuffer m16817a = m16817a(byteBuffer, iArr[i], iArr[length2 + 2]);
            ByteBuffer m16817a2 = m16817a(byteBuffer, iArr[length2], iArr[i]);
            C3853h c3853h = new C3853h(m16817a);
            C3852b c3852b2 = new C3852b(bArr, m16817a2, (byte) 0);
            m16821a(c3852b2, c3853h, 4096);
            c3852b2.m16814a();
            c3852b2.m16813b();
        }
        byte[] bArr2 = new byte[32];
        C3852b c3852b3 = new C3852b(bArr, ByteBuffer.wrap(bArr2), (byte) 0);
        c3852b3.mo16811a(m16817a(byteBuffer, 0, 4096));
        c3852b3.m16814a();
        return bArr2;
    }

    /* renamed from: a */
    private static int[] m16822a(long j) {
        ArrayList arrayList = new ArrayList();
        do {
            j = m16816b(j) * 32;
            arrayList.add(Long.valueOf(m16816b(j) * 4096));
        } while (j > 4096);
        int[] iArr = new int[arrayList.size() + 1];
        int i = 0;
        iArr[0] = 0;
        while (i < arrayList.size()) {
            int i2 = i + 1;
            iArr[i2] = iArr[i] + m16815c(((Long) arrayList.get((arrayList.size() - i) - 1)).longValue());
            i = i2;
        }
        return iArr;
    }

    /* renamed from: a */
    private static ByteBuffer m16817a(ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position(0);
        duplicate.limit(i2);
        duplicate.position(i);
        return duplicate.slice();
    }

    /* renamed from: a */
    private static void m16818a(ByteBuffer byteBuffer, int i) {
        byteBuffer.position(byteBuffer.position() + i);
    }

    /* renamed from: b */
    private static long m16816b(long j) {
        return ((j + 4096) - 1) / 4096;
    }

    /* renamed from: c */
    private static int m16815c(long j) {
        int i = (int) j;
        if (i == j) {
            return i;
        }
        throw new ArithmeticException("integer overflow");
    }
}
