package org.p415a.p445f;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.p415a.p418b.p423e.C12521a;
import org.p415a.p424c.AbstractC12615l;
import org.p415a.p424c.C12587ai;
import org.p415a.p424c.C12612i;
import org.p415a.p424c.C12616m;
import org.p415a.p424c.C12626w;
import org.p415a.p424c.C12628y;
import org.p415a.p424c.InterfaceC12606c;
import org.p415a.p445f.p446a.InterfaceC12903a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.o */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12944o {

    /* renamed from: h */
    private static final int[] f26210h = {19, 18, 17, 16};

    /* renamed from: a */
    C12626w f26211a;

    /* renamed from: b */
    C12587ai f26212b;

    /* renamed from: c */
    List f26213c;

    /* renamed from: d */
    List f26214d;

    /* renamed from: e */
    List f26215e;

    /* renamed from: f */
    List f26216f;

    /* renamed from: g */
    List f26217g;

    /* renamed from: i */
    private long f26218i;

    /* renamed from: j */
    private byte[] f26219j;

    /* renamed from: k */
    private int f26220k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12944o(C12626w c12626w, C12587ai c12587ai, List list, List list2, List list3, List list4, InterfaceC12903a interfaceC12903a) {
        this.f26213c = new ArrayList();
        this.f26214d = new ArrayList();
        this.f26215e = new ArrayList();
        this.f26216f = new ArrayList();
        this.f26217g = null;
        this.f26211a = c12626w;
        this.f26212b = c12587ai;
        this.f26213c = list;
        this.f26214d = list2;
        this.f26215e = list3;
        this.f26216f = list4;
        m453a(interfaceC12903a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12944o(C12626w c12626w, C12587ai c12587ai, List list, InterfaceC12903a interfaceC12903a) {
        this.f26213c = new ArrayList();
        this.f26214d = new ArrayList();
        this.f26215e = new ArrayList();
        this.f26216f = new ArrayList();
        this.f26217g = null;
        this.f26211a = c12626w;
        this.f26212b = c12587ai;
        this.f26217g = list;
        m453a(interfaceC12903a);
    }

    /* renamed from: a */
    private void m453a(InterfaceC12903a interfaceC12903a) {
        int mo908a;
        BigInteger m1546b;
        C12628y c12628y;
        InterfaceC12606c m1539c = this.f26211a.m1539c();
        this.f26219j = interfaceC12903a.mo504a(this.f26211a);
        if (this.f26211a.m1541a() <= 3) {
            c12628y = (C12628y) m1539c;
            this.f26218i = c12628y.m1534c().longValue();
        } else {
            byte[] bArr = this.f26219j;
            this.f26218i = ((bArr[bArr.length - 3] & 255) << 16) | ((bArr[bArr.length - 8] & 255) << 56) | ((bArr[bArr.length - 7] & 255) << 48) | ((bArr[bArr.length - 6] & 255) << 40) | ((bArr[bArr.length - 5] & 255) << 32) | ((bArr[bArr.length - 4] & 255) << 24) | ((bArr[bArr.length - 2] & 255) << 8) | (bArr[bArr.length - 1] & 255);
            if (!(m1539c instanceof C12628y)) {
                if (m1539c instanceof C12612i) {
                    m1546b = ((C12612i) m1539c).m1556c();
                } else if (!(m1539c instanceof C12616m)) {
                    if (m1539c instanceof AbstractC12615l) {
                        mo908a = C12521a.m1673a(((AbstractC12615l) m1539c).m1547e()).m1669a().mo908a();
                        this.f26220k = mo908a;
                    }
                    return;
                } else {
                    m1546b = ((C12616m) m1539c).m1546b();
                }
                mo908a = m1546b.bitLength();
                this.f26220k = mo908a;
            }
            c12628y = (C12628y) m1539c;
        }
        m1546b = c12628y.m1534c();
        mo908a = m1546b.bitLength();
        this.f26220k = mo908a;
    }

    /* renamed from: a */
    public long m454a() {
        return this.f26218i;
    }

    /* renamed from: b */
    public int m452b() {
        return this.f26211a.m1540b();
    }

    /* renamed from: c */
    public C12626w m451c() {
        return this.f26211a;
    }
}
