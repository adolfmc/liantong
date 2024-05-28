package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0;

import java.util.Hashtable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0631q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0568f0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0673e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0674f;

/* renamed from: a.a.a.a.a.e.a.c.j0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0548c implements InterfaceC0641v {

    /* renamed from: h */
    public static final byte f1784h = 54;

    /* renamed from: i */
    public static final byte f1785i = 92;

    /* renamed from: j */
    public static Hashtable f1786j;

    /* renamed from: a */
    public InterfaceC0605o f1787a;

    /* renamed from: b */
    public int f1788b;

    /* renamed from: c */
    public int f1789c;

    /* renamed from: d */
    public InterfaceC0674f f1790d;

    /* renamed from: e */
    public InterfaceC0674f f1791e;

    /* renamed from: f */
    public byte[] f1792f;

    /* renamed from: g */
    public byte[] f1793g;

    static {
        Hashtable hashtable = new Hashtable();
        f1786j = hashtable;
        hashtable.put("GOST3411", C0673e.m22455a(32));
        f1786j.put("MD2", C0673e.m22455a(16));
        f1786j.put("MD4", C0673e.m22455a(64));
        f1786j.put("MD5", C0673e.m22455a(64));
        f1786j.put("RIPEMD128", C0673e.m22455a(64));
        f1786j.put("RIPEMD160", C0673e.m22455a(64));
        f1786j.put("SHA-1", C0673e.m22455a(64));
        f1786j.put("SHA-224", C0673e.m22455a(64));
        f1786j.put("SHA-256", C0673e.m22455a(64));
        f1786j.put("SHA-384", C0673e.m22455a(128));
        f1786j.put("SHA-512", C0673e.m22455a(128));
        f1786j.put("Tiger", C0673e.m22455a(64));
        f1786j.put("Whirlpool", C0673e.m22455a(64));
    }

    public C0548c(InterfaceC0605o interfaceC0605o) {
        this(interfaceC0605o, m22871a(interfaceC0605o));
    }

    /* renamed from: a */
    public static int m22871a(InterfaceC0605o interfaceC0605o) {
        if (interfaceC0605o instanceof InterfaceC0631q) {
            return ((InterfaceC0631q) interfaceC0605o).mo22696d();
        }
        Integer num = (Integer) f1786j.get(interfaceC0605o.mo22748a());
        if (num != null) {
            return num.intValue();
        }
        throw new IllegalArgumentException("unknown digest passed: " + interfaceC0605o.mo22748a());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: b */
    public void mo22651b() {
        this.f1787a.mo22744b();
        InterfaceC0605o interfaceC0605o = this.f1787a;
        byte[] bArr = this.f1792f;
        interfaceC0605o.mo22745a(bArr, 0, bArr.length);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: c */
    public int mo22650c() {
        return this.f1788b;
    }

    /* renamed from: d */
    public InterfaceC0605o m22869d() {
        return this.f1787a;
    }

    public C0548c(InterfaceC0605o interfaceC0605o, int i) {
        this.f1787a = interfaceC0605o;
        int mo22743c = interfaceC0605o.mo22743c();
        this.f1788b = mo22743c;
        this.f1789c = i;
        this.f1792f = new byte[i];
        this.f1793g = new byte[i + mo22743c];
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public String mo22656a() {
        return this.f1787a.mo22748a() + "/HMAC";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22654a(InterfaceC0542i interfaceC0542i) {
        byte[] bArr;
        this.f1787a.mo22744b();
        byte[] m22816a = ((C0568f0) interfaceC0542i).m22816a();
        int length = m22816a.length;
        if (length > this.f1789c) {
            this.f1787a.mo22745a(m22816a, 0, length);
            this.f1787a.mo22746a(this.f1792f, 0);
            length = this.f1788b;
        } else {
            System.arraycopy(m22816a, 0, this.f1792f, 0, length);
        }
        while (true) {
            bArr = this.f1792f;
            if (length >= bArr.length) {
                break;
            }
            bArr[length] = 0;
            length++;
        }
        System.arraycopy(bArr, 0, this.f1793g, 0, this.f1789c);
        m22870a(this.f1792f, this.f1789c, (byte) f1784h);
        m22870a(this.f1793g, this.f1789c, (byte) f1785i);
        InterfaceC0605o interfaceC0605o = this.f1787a;
        if (interfaceC0605o instanceof InterfaceC0674f) {
            InterfaceC0674f mo22453e = ((InterfaceC0674f) interfaceC0605o).mo22453e();
            this.f1791e = mo22453e;
            ((InterfaceC0605o) mo22453e).mo22745a(this.f1793g, 0, this.f1789c);
        }
        InterfaceC0605o interfaceC0605o2 = this.f1787a;
        byte[] bArr2 = this.f1792f;
        interfaceC0605o2.mo22745a(bArr2, 0, bArr2.length);
        InterfaceC0605o interfaceC0605o3 = this.f1787a;
        if (interfaceC0605o3 instanceof InterfaceC0674f) {
            this.f1790d = ((InterfaceC0674f) interfaceC0605o3).mo22453e();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22655a(byte b) {
        this.f1787a.mo22747a(b);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22652a(byte[] bArr, int i, int i2) {
        this.f1787a.mo22745a(bArr, i, i2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public int mo22653a(byte[] bArr, int i) {
        this.f1787a.mo22746a(this.f1793g, this.f1789c);
        InterfaceC0674f interfaceC0674f = this.f1791e;
        if (interfaceC0674f != null) {
            ((InterfaceC0674f) this.f1787a).mo22454a(interfaceC0674f);
            InterfaceC0605o interfaceC0605o = this.f1787a;
            interfaceC0605o.mo22745a(this.f1793g, this.f1789c, interfaceC0605o.mo22743c());
        } else {
            InterfaceC0605o interfaceC0605o2 = this.f1787a;
            byte[] bArr2 = this.f1793g;
            interfaceC0605o2.mo22745a(bArr2, 0, bArr2.length);
        }
        int mo22746a = this.f1787a.mo22746a(bArr, i);
        int i2 = this.f1789c;
        while (true) {
            byte[] bArr3 = this.f1793g;
            if (i2 >= bArr3.length) {
                break;
            }
            bArr3[i2] = 0;
            i2++;
        }
        InterfaceC0674f interfaceC0674f2 = this.f1790d;
        if (interfaceC0674f2 != null) {
            ((InterfaceC0674f) this.f1787a).mo22454a(interfaceC0674f2);
        } else {
            InterfaceC0605o interfaceC0605o3 = this.f1787a;
            byte[] bArr4 = this.f1792f;
            interfaceC0605o3.mo22745a(bArr4, 0, bArr4.length);
        }
        return mo22746a;
    }

    /* renamed from: a */
    public static void m22870a(byte[] bArr, int i, byte b) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ b);
        }
    }
}
