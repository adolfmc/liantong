package p001a.p002a.p003a.p004a.p005a.p008d.p012g;

/* renamed from: a.a.a.a.a.d.g.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0119b {

    /* renamed from: e */
    public static final int f132e = 32;

    /* renamed from: f */
    public static final int f133f = 64;

    /* renamed from: g */
    public static final int f134g = 64;

    /* renamed from: a */
    public byte[] f135a;

    /* renamed from: b */
    public int f136b;

    /* renamed from: c */
    public byte[] f137c;

    /* renamed from: d */
    public int f138d;

    public C0119b() {
        this.f135a = new byte[64];
        this.f137c = (byte[]) C0118a.f130a.clone();
        this.f138d = 0;
    }

    /* renamed from: c */
    private byte[] m24219c() {
        byte[] bArr = new byte[64];
        int i = this.f136b;
        byte[] bArr2 = new byte[i];
        System.arraycopy(this.f135a, 0, bArr2, 0, i);
        byte[] m24227c = C0118a.m24227c(bArr2, this.f138d);
        for (int i2 = 0; i2 < m24227c.length; i2 += 64) {
            System.arraycopy(m24227c, i2, bArr, 0, 64);
            m24223a(bArr);
        }
        return this.f137c;
    }

    /* renamed from: d */
    private void m24218d() {
        byte[] bArr = new byte[64];
        for (int i = 0; i < 64; i += 64) {
            System.arraycopy(this.f135a, i, bArr, 0, 64);
            m24223a(bArr);
        }
        this.f136b = 0;
    }

    /* renamed from: a */
    public int m24225a() {
        return 32;
    }

    /* renamed from: a */
    public int m24222a(byte[] bArr, int i) {
        byte[] m24219c = m24219c();
        System.arraycopy(m24219c, 0, bArr, 0, m24219c.length);
        return 32;
    }

    /* renamed from: b */
    public void m24220b() {
        this.f136b = 0;
        this.f138d = 0;
        this.f137c = (byte[]) C0118a.f130a.clone();
    }

    /* renamed from: a */
    public void m24221a(byte[] bArr, int i, int i2) {
        int i3 = this.f136b;
        int i4 = 64 - i3;
        if (i4 < i2) {
            System.arraycopy(bArr, i, this.f135a, i3, i4);
            i2 -= i4;
            i += i4;
            m24218d();
            while (i2 > 64) {
                System.arraycopy(bArr, i, this.f135a, 0, 64);
                i2 -= 64;
                i += 64;
                m24218d();
            }
        }
        System.arraycopy(bArr, i, this.f135a, this.f136b, i2);
        this.f136b += i2;
    }

    public C0119b(C0119b c0119b) {
        this.f135a = new byte[64];
        this.f137c = (byte[]) C0118a.f130a.clone();
        this.f138d = 0;
        byte[] bArr = c0119b.f135a;
        System.arraycopy(bArr, 0, this.f135a, 0, bArr.length);
        this.f136b = c0119b.f136b;
        byte[] bArr2 = c0119b.f137c;
        System.arraycopy(bArr2, 0, this.f137c, 0, bArr2.length);
    }

    /* renamed from: a */
    private void m24223a(byte[] bArr) {
        byte[] m24240a = C0118a.m24240a(this.f137c, bArr);
        byte[] bArr2 = this.f137c;
        System.arraycopy(m24240a, 0, bArr2, 0, bArr2.length);
        this.f138d++;
    }

    /* renamed from: a */
    public void m24224a(byte b) {
        m24221a(new byte[]{b}, 0, 1);
    }
}
