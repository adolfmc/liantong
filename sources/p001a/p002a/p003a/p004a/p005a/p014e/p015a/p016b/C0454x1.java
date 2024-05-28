package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* renamed from: a.a.a.a.a.e.a.b.x1 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0454x1 extends AbstractC0258r {

    /* renamed from: v3 */
    public byte[] f1485v3;

    public C0454x1(String str) {
        this.f1485v3 = C0678j.m22448a(str);
        try {
            m23065o();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    /* renamed from: a */
    public static C0125a0 m23068a(Object obj) {
        if (obj != null && !(obj instanceof C0125a0)) {
            if (obj instanceof C0454x1) {
                return new C0125a0(((C0454x1) obj).f1485v3);
            }
            if (obj instanceof byte[]) {
                try {
                    return (C0125a0) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0125a0) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(this.f1485v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        int length = this.f1485v3.length;
        return C0177m2.m24099a(length) + 1 + length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public Date m23067m() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        return simpleDateFormat.parse(m23066n());
    }

    /* renamed from: n */
    public String m23066n() {
        String m23064p = m23064p();
        if (m23064p.charAt(0) < '5') {
            return "20" + m23064p;
        }
        return "19" + m23064p;
    }

    /* renamed from: o */
    public Date m23065o() {
        return new SimpleDateFormat("yyMMddHHmmssz").parse(m23064p());
    }

    /* renamed from: p */
    public String m23064p() {
        String m22442b = C0678j.m22442b(this.f1485v3);
        if (m22442b.indexOf(45) < 0 && m22442b.indexOf(43) < 0) {
            if (m22442b.length() == 11) {
                return m22442b.substring(0, 10) + "00GMT+00:00";
            }
            return m22442b.substring(0, 12) + "GMT+00:00";
        }
        int indexOf = m22442b.indexOf(45);
        if (indexOf < 0) {
            indexOf = m22442b.indexOf(43);
        }
        if (indexOf == m22442b.length() - 3) {
            m22442b = m22442b + "00";
        }
        if (indexOf == 10) {
            return m22442b.substring(0, 10) + "00GMT" + m22442b.substring(10, 13) + ":" + m22442b.substring(13, 15);
        }
        return m22442b.substring(0, 12) + "GMT" + m22442b.substring(12, 15) + ":" + m22442b.substring(15, 17);
    }

    public String toString() {
        return C0678j.m22442b(this.f1485v3);
    }

    public C0454x1(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f1485v3 = C0678j.m22448a(simpleDateFormat.format(date));
    }

    public C0454x1(byte[] bArr) {
        this.f1485v3 = bArr;
    }

    /* renamed from: a */
    public static C0125a0 m23069a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0125a0)) {
            return new C0125a0(((AbstractC0182o) m23004m).mo24088m());
        }
        return m23068a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.mo23763a(23);
        int length = this.f1485v3.length;
        c0252q.m23767b(length);
        for (int i = 0; i != length; i++) {
            c0252q.mo23763a(this.f1485v3[i]);
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0454x1) {
            return C0669a.m22499a(this.f1485v3, ((C0454x1) abstractC0258r).f1485v3);
        }
        return false;
    }
}
