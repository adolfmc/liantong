package com.bytedance.pangle.res.p181a;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.logging.Logger;

/* renamed from: com.bytedance.pangle.res.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3906a {

    /* renamed from: f */
    private static final Logger f9292f = Logger.getLogger(C3906a.class.getName());

    /* renamed from: a */
    private final byte[] f9293a;

    /* renamed from: b */
    private final InterfaceC3916h f9294b;

    /* renamed from: c */
    private final C3915g f9295c;

    /* renamed from: d */
    private final C3913e f9296d;

    /* renamed from: e */
    private C3907a f9297e;

    public C3906a(byte[] bArr, InterfaceC3916h interfaceC3916h) {
        C3913e c3913e = new C3913e(new ByteArrayInputStream(bArr));
        this.f9296d = c3913e;
        this.f9295c = new C3915g(new C3917i(c3913e));
        this.f9293a = bArr;
        this.f9294b = interfaceC3916h;
    }

    /* renamed from: b */
    private void m16724b() {
        m16723b(515);
        int readInt = this.f9295c.readInt();
        for (int i = 0; i < readInt; i++) {
            this.f9295c.readInt();
            this.f9295c.skipBytes(256);
        }
        while (m16715j().f9298a == 513) {
            m16722c();
        }
    }

    /* renamed from: c */
    private void m16722c() {
        m16721d();
        short s = m16715j().f9298a;
        while (s == 514) {
            m16721d();
            s = m16715j().f9298a;
        }
        while (s == 513) {
            m16720e();
            if (this.f9296d.m16701a() < this.f9297e.f9302e) {
                Logger logger = f9292f;
                logger.warning("Unknown data detected. Skipping: " + (this.f9297e.f9302e - this.f9296d.m16701a()) + " byte(s)");
                this.f9296d.skip((long) (this.f9297e.f9302e - this.f9296d.m16701a()));
            }
            s = m16715j().f9298a;
        }
    }

    /* renamed from: d */
    private void m16721d() {
        m16723b(514);
        this.f9295c.readUnsignedByte();
        this.f9295c.skipBytes(3);
        this.f9295c.skipBytes(this.f9295c.readInt() * 4);
    }

    /* renamed from: e */
    private void m16720e() {
        m16723b(513);
        this.f9295c.readUnsignedByte();
        this.f9295c.readByte();
        this.f9295c.skipBytes(2);
        int readInt = this.f9295c.readInt();
        int readInt2 = this.f9295c.readInt();
        m16716i();
        int i = (this.f9297e.f9301d + readInt2) - (readInt * 4);
        if (i != this.f9296d.m16701a()) {
            f9292f.warning("Invalid data detected. Skipping: " + (i - this.f9296d.m16701a()) + " byte(s)");
            this.f9295c.skipBytes(i - this.f9296d.m16701a());
        }
        int[] m16698a = this.f9295c.m16698a(readInt);
        HashSet hashSet = new HashSet();
        for (int i2 : m16698a) {
            if (i2 != -1 && !hashSet.contains(Integer.valueOf(i2))) {
                m16719f();
                hashSet.add(Integer.valueOf(i2));
            }
        }
    }

    /* renamed from: f */
    private void m16719f() {
        if (this.f9295c.readShort() < 0) {
            throw new RuntimeException("Entry size is under 0 bytes.");
        }
        short readShort = this.f9295c.readShort();
        this.f9295c.readInt();
        if ((readShort & 1) == 0) {
            m16717h();
        } else {
            m16718g();
        }
    }

    /* renamed from: g */
    private void m16718g() {
        int m16687a = C3919k.m16687a(this.f9295c);
        C3919k.m16686a(this.f9293a, this.f9295c.readInt(), m16687a, this.f9294b);
        int readInt = this.f9295c.readInt();
        for (int i = 0; i < readInt; i++) {
            int m16687a2 = C3919k.m16687a(this.f9295c);
            C3919k.m16686a(this.f9293a, this.f9295c.readInt(), m16687a2, this.f9294b);
            m16717h();
        }
    }

    /* renamed from: h */
    private void m16717h() {
        this.f9295c.m16699a();
        this.f9295c.m16697b();
        byte readByte = this.f9295c.readByte();
        int m16687a = C3919k.m16687a(this.f9295c);
        int readInt = this.f9295c.readInt();
        if (readByte == 1) {
            C3919k.m16686a(this.f9293a, readInt, m16687a, this.f9294b);
        }
        if (readByte == 2) {
            C3919k.m16686a(this.f9293a, readInt, m16687a, this.f9294b);
        }
    }

    /* renamed from: i */
    private void m16716i() {
        int readInt = this.f9295c.readInt();
        int i = 28;
        if (readInt < 28) {
            throw new RuntimeException("Config size < 28");
        }
        this.f9295c.readShort();
        this.f9295c.readShort();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.readUnsignedShort();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.readByte();
        this.f9295c.skipBytes(1);
        this.f9295c.readShort();
        this.f9295c.readShort();
        this.f9295c.readShort();
        this.f9295c.skipBytes(2);
        if (readInt >= 32) {
            this.f9295c.readByte();
            this.f9295c.readByte();
            this.f9295c.readShort();
            i = 32;
        }
        if (readInt >= 36) {
            this.f9295c.readShort();
            this.f9295c.readShort();
            i = 36;
        }
        if (readInt >= 48) {
            m16725a(4).toCharArray();
            m16725a(8).toCharArray();
            i = 48;
        }
        if (readInt >= 52) {
            this.f9295c.readByte();
            this.f9295c.readByte();
            this.f9295c.skipBytes(2);
            i = 52;
        }
        if (readInt >= 56) {
            this.f9295c.skipBytes(4);
            i = 56;
        }
        int i2 = readInt - 56;
        if (i2 > 0) {
            byte[] bArr = new byte[i2];
            i += i2;
            this.f9295c.readFully(bArr);
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.equals(BigInteger.ZERO)) {
                f9292f.fine(String.format("Config flags size > %d, but exceeding bytes are all zero, so it should be ok.", 56));
            } else {
                f9292f.warning(String.format("Config flags size > %d. Size = %d. Exceeding bytes: 0x%X.", 56, Integer.valueOf(readInt), bigInteger));
            }
        }
        int i3 = readInt - i;
        if (i3 > 0) {
            this.f9295c.skipBytes(i3);
        }
    }

    /* renamed from: a */
    private String m16725a(int i) {
        int i2;
        short s;
        StringBuilder sb = new StringBuilder(16);
        while (true) {
            i2 = i - 1;
            if (i == 0 || this.f9295c.readByte() == 0) {
                break;
            }
            sb.append((char) s);
            i = i2;
        }
        this.f9295c.skipBytes(i2);
        return sb.toString();
    }

    /* renamed from: j */
    private C3907a m16715j() {
        C3907a m16714a = C3907a.m16714a(this.f9295c, this.f9296d);
        this.f9297e = m16714a;
        return m16714a;
    }

    /* renamed from: b */
    private void m16723b(int i) {
        if (this.f9297e.f9298a != i) {
            throw new RuntimeException(String.format("Invalid chunk type: expected=0x%08x, got=0x%08x", Integer.valueOf(i), Short.valueOf(this.f9297e.f9298a)));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.bytedance.pangle.res.a.a$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C3907a {

        /* renamed from: a */
        public final short f9298a;

        /* renamed from: b */
        public final int f9299b;

        /* renamed from: c */
        public final int f9300c;

        /* renamed from: d */
        public final int f9301d;

        /* renamed from: e */
        public final int f9302e;

        private C3907a(short s, int i, int i2, int i3) {
            this.f9298a = s;
            this.f9299b = i;
            this.f9300c = i2;
            this.f9301d = i3;
            this.f9302e = i3 + i2;
        }

        /* renamed from: a */
        public static C3907a m16714a(C3915g c3915g, C3913e c3913e) {
            int m16701a = c3913e.m16701a();
            try {
                return new C3907a(c3915g.readShort(), c3915g.readShort(), c3915g.readInt(), m16701a);
            } catch (EOFException unused) {
                return new C3907a((short) -1, 0, 0, c3913e.m16701a());
            }
        }
    }

    /* renamed from: a */
    public final void m16726a() {
        m16715j();
        m16723b(2);
        int readInt = this.f9295c.readInt();
        C3920l.m16684a(this.f9295c);
        m16715j();
        for (int i = 0; i < readInt; i++) {
            m16723b(512);
            this.f9295c.readInt();
            this.f9295c.skipBytes(256);
            this.f9295c.skipBytes(4);
            this.f9295c.skipBytes(4);
            this.f9295c.skipBytes(4);
            this.f9295c.skipBytes(4);
            if (this.f9297e.f9299b == 288 && this.f9295c.readInt() > 0) {
                throw new RuntimeException("don't support");
            }
            C3920l.m16684a(this.f9295c);
            C3920l.m16684a(this.f9295c);
            m16715j();
            boolean z = true;
            while (z) {
                switch (this.f9297e.f9298a) {
                    case 514:
                        m16722c();
                        break;
                    case 515:
                        m16724b();
                        break;
                    default:
                        z = false;
                        break;
                }
            }
        }
    }
}
