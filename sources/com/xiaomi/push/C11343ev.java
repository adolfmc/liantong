package com.xiaomi.push;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ev */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11343ev {

    /* renamed from: a */
    public static final byte[] f22209a = {80, 85, 83, 72};

    /* renamed from: a */
    private byte f22210a;

    /* renamed from: a */
    private int f22211a;

    /* renamed from: a */
    private short f22212a;

    /* renamed from: b */
    private byte[] f22213b;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ev$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11345b {
        /* renamed from: a */
        byte mo3918a();

        /* renamed from: a */
        byte[] mo3917a(byte[] bArr, int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ev$c */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class C11346c {
    }

    protected C11343ev(byte b, int i, byte[] bArr) {
        this((short) 1, b, i, bArr);
    }

    protected C11343ev(short s, byte b, int i, byte[] bArr) {
        this.f22212a = (short) 1;
        this.f22212a = s;
        this.f22210a = b;
        this.f22211a = i;
        this.f22213b = bArr;
    }

    /* renamed from: a */
    public static C11343ev m3928a(byte b, int i, byte[] bArr) {
        return new C11343ev(b, i, bArr);
    }

    /* renamed from: a */
    public static C11343ev m3924a(short s, byte b, int i, byte[] bArr) {
        return new C11343ev(s, b, i, bArr);
    }

    /* renamed from: a */
    public static C11343ev m3923a(byte[] bArr) {
        if (m3922a(bArr)) {
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN);
            order.getInt();
            short s = order.getShort();
            byte b = order.get();
            int i = order.getInt();
            byte[] bArr2 = new byte[order.getInt()];
            order.get(bArr2);
            return m3924a(s, b, i, bArr2);
        }
        return m3928a((byte) 0, bArr.length, bArr);
    }

    /* renamed from: a */
    public static boolean m3922a(byte[] bArr) {
        byte[] bArr2 = f22209a;
        return m3921a(bArr2, bArr, bArr2.length);
    }

    /* renamed from: a */
    public static boolean m3921a(byte[] bArr, byte[] bArr2, int i) {
        if (bArr.length < i || bArr2.length < i) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: com.xiaomi.push.ev$d */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static final class C11347d implements InterfaceC11345b {
        @Override // com.xiaomi.push.C11343ev.InterfaceC11345b
        /* renamed from: a */
        public byte mo3918a() {
            return (byte) 2;
        }

        @Override // com.xiaomi.push.C11343ev.InterfaceC11345b
        /* renamed from: a */
        public byte[] mo3917a(byte[] bArr, int i) {
            GZIPInputStream gZIPInputStream;
            GZIPInputStream gZIPInputStream2 = null;
            try {
                gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr), i);
            } catch (IOException unused) {
            } catch (Throwable th) {
                th = th;
            }
            try {
                byte[] bArr2 = new byte[i];
                gZIPInputStream.read(bArr2);
                try {
                    gZIPInputStream.close();
                } catch (IOException unused2) {
                }
                return bArr2;
            } catch (IOException unused3) {
                gZIPInputStream2 = gZIPInputStream;
                if (gZIPInputStream2 != null) {
                    try {
                        gZIPInputStream2.close();
                    } catch (IOException unused4) {
                    }
                }
                return bArr;
            } catch (Throwable th2) {
                th = th2;
                gZIPInputStream2 = gZIPInputStream;
                if (gZIPInputStream2 != null) {
                    try {
                        gZIPInputStream2.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ev$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11344a {

        /* renamed from: a */
        public static final C11346c f22214a = new C11346c();

        /* renamed from: a */
        public static final C11347d f22215a = new C11347d();

        /* renamed from: a */
        public static byte[] m3920a(byte[] bArr) {
            return m3919a(bArr, f22215a);
        }

        /* renamed from: a */
        public static byte[] m3919a(byte[] bArr, InterfaceC11345b interfaceC11345b) {
            if (C11343ev.m3922a(bArr)) {
                C11343ev m3923a = C11343ev.m3923a(bArr);
                return (m3923a.f22210a == 0 || m3923a.f22210a != interfaceC11345b.mo3918a()) ? m3923a.f22213b : interfaceC11345b.mo3917a(m3923a.f22213b, m3923a.f22211a);
            }
            return bArr;
        }
    }
}
