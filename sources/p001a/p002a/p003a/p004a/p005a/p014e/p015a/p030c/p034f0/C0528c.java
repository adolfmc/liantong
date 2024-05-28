package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p034f0;

import com.p201hb.omapi.union.sim.org.bouncycastle.crypto.InvalidCipherTextException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;

/* renamed from: a.a.a.a.a.e.a.c.f0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0528c implements InterfaceC0500a {

    /* renamed from: f */
    public static final String f1721f = "org.org.bouncycastle.pkcs1.strict";

    /* renamed from: g */
    public static final int f1722g = 10;

    /* renamed from: a */
    public SecureRandom f1723a;

    /* renamed from: b */
    public InterfaceC0500a f1724b;

    /* renamed from: c */
    public boolean f1725c;

    /* renamed from: d */
    public boolean f1726d;

    /* renamed from: e */
    public boolean f1727e = m22892d();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: a.a.a.a.a.e.a.c.f0.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C0529a implements PrivilegedAction {
        public C0529a() {
        }

        @Override // java.security.PrivilegedAction
        public Object run() {
            return System.getProperty(C0528c.f1721f);
        }
    }

    public C0528c(InterfaceC0500a interfaceC0500a) {
        this.f1724b = interfaceC0500a;
    }

    /* renamed from: d */
    private boolean m22892d() {
        String str = (String) AccessController.doPrivileged(new C0529a());
        return str == null || str.equals("true");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public void mo22898a(boolean z, InterfaceC0542i interfaceC0542i) {
        C0557a c0557a;
        if (interfaceC0542i instanceof C0578k0) {
            C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
            this.f1723a = c0578k0.m22800b();
            c0557a = (C0557a) c0578k0.m22801a();
        } else {
            this.f1723a = new SecureRandom();
            c0557a = (C0557a) interfaceC0542i;
        }
        this.f1724b.mo22898a(z, interfaceC0542i);
        this.f1726d = c0557a.m22845a();
        this.f1725c = z;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: b */
    public int mo22896b() {
        int mo22896b = this.f1724b.mo22896b();
        return this.f1725c ? mo22896b : mo22896b - 10;
    }

    /* renamed from: c */
    public InterfaceC0500a m22894c() {
        return this.f1724b;
    }

    /* renamed from: c */
    private byte[] m22893c(byte[] bArr, int i, int i2) {
        if (i2 <= mo22899a()) {
            int mo22899a = this.f1724b.mo22899a();
            byte[] bArr2 = new byte[mo22899a];
            if (this.f1726d) {
                bArr2[0] = 1;
                for (int i3 = 1; i3 != (mo22899a - i2) - 1; i3++) {
                    bArr2[i3] = -1;
                }
            } else {
                this.f1723a.nextBytes(bArr2);
                bArr2[0] = 2;
                for (int i4 = 1; i4 != (mo22899a - i2) - 1; i4++) {
                    while (bArr2[i4] == 0) {
                        bArr2[i4] = (byte) this.f1723a.nextInt();
                    }
                }
            }
            int i5 = mo22899a - i2;
            bArr2[i5 - 1] = 0;
            System.arraycopy(bArr, i, bArr2, i5, i2);
            return this.f1724b.mo22897a(bArr2, 0, mo22899a);
        }
        throw new IllegalArgumentException("input data too large");
    }

    /* renamed from: b */
    private byte[] m22895b(byte[] bArr, int i, int i2) {
        byte b;
        byte[] mo22897a = this.f1724b.mo22897a(bArr, i, i2);
        if (mo22897a.length >= mo22896b()) {
            byte b2 = mo22897a[0];
            if (this.f1726d) {
                if (b2 != 2) {
                    throw new InvalidCipherTextException("unknown block type");
                }
            } else if (b2 != 1) {
                throw new InvalidCipherTextException("unknown block type");
            }
            if (this.f1727e && mo22897a.length != this.f1724b.mo22896b()) {
                throw new InvalidCipherTextException("block incorrect size");
            }
            int i3 = 1;
            while (i3 != mo22897a.length && (b = mo22897a[i3]) != 0) {
                if (b2 == 1 && b != -1) {
                    throw new InvalidCipherTextException("block padding incorrect");
                }
                i3++;
            }
            int i4 = i3 + 1;
            if (i4 <= mo22897a.length && i4 >= 10) {
                int length = mo22897a.length - i4;
                byte[] bArr2 = new byte[length];
                System.arraycopy(mo22897a, i4, bArr2, 0, length);
                return bArr2;
            }
            throw new InvalidCipherTextException("no data in block");
        }
        throw new InvalidCipherTextException("block truncated");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public int mo22899a() {
        int mo22899a = this.f1724b.mo22899a();
        return this.f1725c ? mo22899a - 10 : mo22899a;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0500a
    /* renamed from: a */
    public byte[] mo22897a(byte[] bArr, int i, int i2) {
        if (this.f1725c) {
            return m22893c(bArr, i, i2);
        }
        return m22895b(bArr, i, i2);
    }
}
