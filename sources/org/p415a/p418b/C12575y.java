package org.p415a.p418b;

import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.y */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12575y {

    /* renamed from: a */
    private final InputStream f25518a;

    /* renamed from: b */
    private final int f25519b;

    /* renamed from: c */
    private final byte[][] f25520c;

    public C12575y(InputStream inputStream) {
        this(inputStream, C12466bz.m1693a(inputStream));
    }

    public C12575y(InputStream inputStream, int i) {
        this.f25518a = inputStream;
        this.f25519b = i;
        this.f25520c = new byte[11];
    }

    /* renamed from: a */
    private void m1600a(boolean z) {
        InputStream inputStream = this.f25518a;
        if (inputStream instanceof C12461bu) {
            ((C12461bu) inputStream).m1702a(z);
        }
    }

    /* renamed from: a */
    public InterfaceC12554f m1602a() {
        int read = this.f25518a.read();
        if (read == -1) {
            return null;
        }
        m1600a(false);
        int m1654a = C12559k.m1654a(this.f25518a, read);
        boolean z = (read & 32) != 0;
        int m1650b = C12559k.m1650b(this.f25518a, this.f25519b);
        if (m1650b < 0) {
            if (z) {
                C12575y c12575y = new C12575y(new C12461bu(this.f25518a, this.f25519b), this.f25519b);
                return (read & 64) != 0 ? new C12412ad(m1654a, c12575y) : (read & 128) != 0 ? new C12422am(true, m1654a, c12575y) : c12575y.m1601a(m1654a);
            }
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        C12459bs c12459bs = new C12459bs(this.f25518a, m1650b);
        if ((read & 64) != 0) {
            return new C12424ao(z, m1654a, c12459bs.m1703b());
        }
        if ((read & 128) != 0) {
            return new C12422am(z, m1654a, new C12575y(c12459bs));
        }
        if (!z) {
            if (m1654a != 4) {
                try {
                    return C12559k.m1655a(m1654a, c12459bs, this.f25520c);
                } catch (IllegalArgumentException e) {
                    throw new C12557i("corrupted stream detected", e);
                }
            }
            return new C12441ba(c12459bs);
        } else if (m1654a != 4) {
            if (m1654a != 8) {
                switch (m1654a) {
                    case 16:
                        return new C12445be(new C12575y(c12459bs));
                    case 17:
                        return new C12447bg(new C12575y(c12459bs));
                    default:
                        throw new IOException("unknown tag " + m1654a + " encountered");
                }
            }
            return new C12428as(new C12575y(c12459bs));
        } else {
            return new C12416ag(new C12575y(c12459bs));
        }
    }

    /* renamed from: a */
    InterfaceC12554f m1601a(int i) {
        if (i != 4) {
            if (i != 8) {
                switch (i) {
                    case 16:
                        return new C12418ai(this);
                    case 17:
                        return new C12420ak(this);
                    default:
                        throw new C12557i("unknown BER object encountered: 0x" + Integer.toHexString(i));
                }
            }
            return new C12428as(this);
        }
        return new C12416ag(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public AbstractC12570t m1599a(boolean z, int i) {
        if (z) {
            C12555g m1598b = m1598b();
            return this.f25518a instanceof C12461bu ? m1598b.m1661a() == 1 ? new C12421al(true, i, m1598b.m1660a(0)) : new C12421al(false, i, C12413ae.m1732a(m1598b)) : m1598b.m1661a() == 1 ? new C12449bi(true, i, m1598b.m1660a(0)) : new C12449bi(false, i, C12429at.m1724a(m1598b));
        }
        return new C12449bi(false, i, new C12435az(((C12459bs) this.f25518a).m1703b()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public C12555g m1598b() {
        C12555g c12555g = new C12555g();
        while (true) {
            InterfaceC12554f m1602a = m1602a();
            if (m1602a == null) {
                return c12555g;
            }
            c12555g.m1659a(m1602a instanceof InterfaceC12460bt ? ((InterfaceC12460bt) m1602a).mo1593e() : m1602a.mo1617h());
        }
    }
}
