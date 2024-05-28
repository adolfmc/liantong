package com.xiaomi.push;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.hw */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11450hw extends AbstractC11456ia {

    /* renamed from: a */
    private static final C11461if f23291a = new C11461if();

    /* renamed from: a */
    protected int f23292a;

    /* renamed from: a */
    protected boolean f23293a;

    /* renamed from: a */
    private byte[] f23294a;

    /* renamed from: b */
    protected boolean f23295b;

    /* renamed from: b */
    private byte[] f23296b;

    /* renamed from: c */
    protected boolean f23297c;

    /* renamed from: c */
    private byte[] f23298c;

    /* renamed from: d */
    private byte[] f23299d;

    /* renamed from: e */
    private byte[] f23300e;

    /* renamed from: f */
    private byte[] f23301f;

    /* renamed from: g */
    private byte[] f23302g;

    /* renamed from: h */
    private byte[] f23303h;

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3018a() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3010a(C11461if c11461if) {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: b */
    public void mo3005b() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: d */
    public void mo3003d() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: e */
    public void mo3002e() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: f */
    public void mo3001f() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: g */
    public void mo3000g() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: h */
    public void mo2999h() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: i */
    public void mo2998i() {
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: j */
    public void mo2997j() {
    }

    /* renamed from: com.xiaomi.push.hw$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11451a implements InterfaceC11458ic {

        /* renamed from: a */
        protected int f23304a;

        /* renamed from: a */
        protected boolean f23305a;

        /* renamed from: b */
        protected boolean f23306b;

        public C11451a() {
            this(false, true);
        }

        public C11451a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public C11451a(boolean z, boolean z2, int i) {
            this.f23305a = false;
            this.f23306b = true;
            this.f23305a = z;
            this.f23306b = z2;
            this.f23304a = i;
        }

        @Override // com.xiaomi.push.InterfaceC11458ic
        /* renamed from: a */
        public AbstractC11456ia mo2988a(AbstractC11467ik abstractC11467ik) {
            C11450hw c11450hw = new C11450hw(abstractC11467ik, this.f23305a, this.f23306b);
            int i = this.f23304a;
            if (i != 0) {
                c11450hw.m3058b(i);
            }
            return c11450hw;
        }
    }

    public C11450hw(AbstractC11467ik abstractC11467ik, boolean z, boolean z2) {
        super(abstractC11467ik);
        this.f23293a = false;
        this.f23295b = true;
        this.f23297c = false;
        this.f23294a = new byte[1];
        this.f23296b = new byte[2];
        this.f23298c = new byte[4];
        this.f23299d = new byte[8];
        this.f23300e = new byte[1];
        this.f23301f = new byte[2];
        this.f23302g = new byte[4];
        this.f23303h = new byte[8];
        this.f23293a = z;
        this.f23295b = z2;
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3013a(C11452hx c11452hx) {
        mo3016a(c11452hx.f23307a);
        mo3007a(c11452hx.f23309a);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: c */
    public void mo3004c() {
        mo3016a((byte) 0);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3011a(C11454hz c11454hz) {
        mo3016a(c11454hz.f23312a);
        mo3016a(c11454hz.f23314b);
        mo3015a(c11454hz.f23313a);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3012a(C11453hy c11453hy) {
        mo3016a(c11453hy.f23310a);
        mo3015a(c11453hy.f23311a);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3006a(boolean z) {
        mo3016a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3016a(byte b) {
        this.f23294a[0] = b;
        this.f23324a.mo2981a(this.f23294a, 0, 1);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3007a(short s) {
        byte[] bArr = this.f23296b;
        bArr[0] = (byte) ((s >> 8) & 255);
        bArr[1] = (byte) (s & 255);
        this.f23324a.mo2981a(this.f23296b, 0, 2);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3015a(int i) {
        byte[] bArr = this.f23298c;
        bArr[0] = (byte) ((i >> 24) & 255);
        bArr[1] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[3] = (byte) (i & 255);
        this.f23324a.mo2981a(this.f23298c, 0, 4);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3014a(long j) {
        byte[] bArr = this.f23299d;
        bArr[0] = (byte) ((j >> 56) & 255);
        bArr[1] = (byte) ((j >> 48) & 255);
        bArr[2] = (byte) ((j >> 40) & 255);
        bArr[3] = (byte) ((j >> 32) & 255);
        bArr[4] = (byte) ((j >> 24) & 255);
        bArr[5] = (byte) ((j >> 16) & 255);
        bArr[6] = (byte) ((j >> 8) & 255);
        bArr[7] = (byte) (j & 255);
        this.f23324a.mo2981a(this.f23299d, 0, 8);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3009a(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo3015a(bytes.length);
            this.f23324a.mo2981a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new C11448hu("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public void mo3008a(ByteBuffer byteBuffer) {
        int limit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        mo3015a(limit);
        this.f23324a.mo2981a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11461if mo3020a() {
        return f23291a;
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11452hx mo3021a() {
        byte mo3025a = mo3025a();
        return new C11452hx("", mo3025a, mo3025a == 0 ? (short) 0 : mo3019a());
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11454hz mo2992a() {
        return new C11454hz(mo3025a(), mo3025a(), mo3023a());
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11453hy mo2993a() {
        return new C11453hy(mo3025a(), mo3023a());
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11460ie mo2991a() {
        return new C11460ie(mo3025a(), mo3023a());
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public boolean mo3017a() {
        return mo3025a() == 1;
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public byte mo3025a() {
        if (this.f23324a.mo2979b() >= 1) {
            byte b = this.f23324a.mo2984a()[this.f23324a.mo2980a_()];
            this.f23324a.mo2983a(1);
            return b;
        }
        m3059a(this.f23300e, 0, 1);
        return this.f23300e[0];
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public short mo3019a() {
        byte[] bArr = this.f23301f;
        int i = 0;
        if (this.f23324a.mo2979b() >= 2) {
            bArr = this.f23324a.mo2984a();
            i = this.f23324a.mo2980a_();
            this.f23324a.mo2983a(2);
        } else {
            m3059a(this.f23301f, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public int mo3023a() {
        byte[] bArr = this.f23302g;
        int i = 0;
        if (this.f23324a.mo2979b() >= 4) {
            bArr = this.f23324a.mo2984a();
            i = this.f23324a.mo2980a_();
            this.f23324a.mo2983a(4);
        } else {
            m3059a(this.f23302g, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public long mo3022a() {
        byte[] bArr = this.f23303h;
        int i = 0;
        if (this.f23324a.mo2979b() >= 8) {
            bArr = this.f23324a.mo2984a();
            i = this.f23324a.mo2980a_();
            this.f23324a.mo2983a(8);
        } else {
            m3059a(this.f23303h, 0, 8);
        }
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public double mo3024a() {
        return Double.longBitsToDouble(mo3022a());
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public String mo2990a() {
        int mo3023a = mo3023a();
        if (this.f23324a.mo2979b() >= mo3023a) {
            try {
                String str = new String(this.f23324a.mo2984a(), this.f23324a.mo2980a_(), mo3023a, "UTF-8");
                this.f23324a.mo2983a(mo3023a);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new C11448hu("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return m3060a(mo3023a);
    }

    /* renamed from: a */
    public String m3060a(int i) {
        try {
            m3057c(i);
            byte[] bArr = new byte[i];
            this.f23324a.m2978b(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new C11448hu("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public ByteBuffer mo2989a() {
        int mo3023a = mo3023a();
        m3057c(mo3023a);
        if (this.f23324a.mo2979b() >= mo3023a) {
            ByteBuffer wrap = ByteBuffer.wrap(this.f23324a.mo2984a(), this.f23324a.mo2980a_(), mo3023a);
            this.f23324a.mo2983a(mo3023a);
            return wrap;
        }
        byte[] bArr = new byte[mo3023a];
        this.f23324a.m2978b(bArr, 0, mo3023a);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private int m3059a(byte[] bArr, int i, int i2) {
        m3057c(i2);
        return this.f23324a.m2978b(bArr, i, i2);
    }

    /* renamed from: b */
    public void m3058b(int i) {
        this.f23292a = i;
        this.f23297c = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m3057c(int i) {
        if (i < 0) {
            throw new C11448hu("Negative length: " + i);
        } else if (this.f23297c) {
            this.f23292a -= i;
            if (this.f23292a >= 0) {
                return;
            }
            throw new C11448hu("Message length exceeded: " + i);
        }
    }
}
