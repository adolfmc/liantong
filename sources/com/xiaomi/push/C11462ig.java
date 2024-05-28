package com.xiaomi.push;

import com.xiaomi.push.C11450hw;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: com.xiaomi.push.ig */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11462ig extends C11450hw {

    /* renamed from: b */
    private static int f23330b = 10000;

    /* renamed from: c */
    private static int f23331c = 10000;

    /* renamed from: d */
    private static int f23332d = 10000;

    /* renamed from: e */
    private static int f23333e = 10485760;

    /* renamed from: f */
    private static int f23334f = 104857600;

    public C11462ig(AbstractC11467ik abstractC11467ik, boolean z, boolean z2) {
        super(abstractC11467ik, z, z2);
    }

    /* renamed from: com.xiaomi.push.ig$a */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11463a extends C11450hw.C11451a {
        public C11463a() {
            super(false, true);
        }

        public C11463a(boolean z, boolean z2, int i) {
            super(z, z2, i);
        }

        @Override // com.xiaomi.push.C11450hw.C11451a, com.xiaomi.push.InterfaceC11458ic
        /* renamed from: a */
        public AbstractC11456ia mo2988a(AbstractC11467ik abstractC11467ik) {
            C11462ig c11462ig = new C11462ig(abstractC11467ik, this.f23305a, this.f23306b);
            if (this.f23304a != 0) {
                c11462ig.m3058b(this.f23304a);
            }
            return c11462ig;
        }
    }

    @Override // com.xiaomi.push.C11450hw, com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11454hz mo2992a() {
        byte a = mo2993a();
        byte a2 = mo2993a();
        int a3 = mo2993a();
        if (a3 > f23330b) {
            throw new C11457ib(3, "Thrift map size " + a3 + " out of range!");
        }
        return new C11454hz(a, a2, a3);
    }

    @Override // com.xiaomi.push.C11450hw, com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11453hy mo2993a() {
        byte a = mo2993a();
        int a2 = mo2993a();
        if (a2 > f23331c) {
            throw new C11457ib(3, "Thrift list size " + a2 + " out of range!");
        }
        return new C11453hy(a, a2);
    }

    @Override // com.xiaomi.push.C11450hw, com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public C11460ie mo2991a() {
        byte a = mo2993a();
        int a2 = mo2993a();
        if (a2 > f23332d) {
            throw new C11457ib(3, "Thrift set size " + a2 + " out of range!");
        }
        return new C11460ie(a, a2);
    }

    @Override // com.xiaomi.push.C11450hw, com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public String mo2990a() {
        int a = mo2993a();
        if (a > f23333e) {
            throw new C11457ib(3, "Thrift string size " + a + " out of range!");
        } else if (this.f23324a.mo2979b() >= a) {
            try {
                String str = new String(this.f23324a.mo2984a(), this.f23324a.mo2980a_(), a, "UTF-8");
                this.f23324a.mo2983a(a);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new C11448hu("JVM DOES NOT SUPPORT UTF-8");
            }
        } else {
            return m3060a(a);
        }
    }

    @Override // com.xiaomi.push.C11450hw, com.xiaomi.push.AbstractC11456ia
    /* renamed from: a */
    public ByteBuffer mo2989a() {
        int a = mo2993a();
        if (a > f23334f) {
            throw new C11457ib(3, "Thrift binary size " + a + " out of range!");
        }
        m3057c(a);
        if (this.f23324a.mo2979b() >= a) {
            ByteBuffer wrap = ByteBuffer.wrap(this.f23324a.mo2984a(), this.f23324a.mo2980a_(), a);
            this.f23324a.mo2983a(a);
            return wrap;
        }
        byte[] bArr = new byte[a];
        this.f23324a.m2978b(bArr, 0, a);
        return ByteBuffer.wrap(bArr);
    }
}
