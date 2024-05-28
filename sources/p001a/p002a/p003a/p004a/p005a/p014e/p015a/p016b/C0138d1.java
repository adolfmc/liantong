package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* renamed from: a.a.a.a.a.e.a.b.d1 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0138d1 extends AbstractC0258r {

    /* renamed from: v3 */
    public byte[] f161v3;

    public C0138d1(String str) {
        this.f161v3 = C0678j.m22448a(str);
        try {
            m24178m();
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date string: " + e.getMessage());
        }
    }

    /* renamed from: a */
    public static C0153h m24179a(Object obj) {
        if (obj != null && !(obj instanceof C0153h)) {
            if (obj instanceof C0138d1) {
                return new C0153h(((C0138d1) obj).f161v3);
            }
            if (obj instanceof byte[]) {
                try {
                    return (C0153h) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0153h) obj;
    }

    /* renamed from: p */
    private String m24175p() {
        String str = "+";
        TimeZone timeZone = TimeZone.getDefault();
        int rawOffset = timeZone.getRawOffset();
        if (rawOffset < 0) {
            str = "-";
            rawOffset = -rawOffset;
        }
        int i = rawOffset / 3600000;
        int i2 = (rawOffset - (((i * 60) * 60) * 1000)) / 60000;
        try {
            if (timeZone.useDaylightTime() && timeZone.inDaylightTime(m24178m())) {
                i += str.equals("+") ? 1 : -1;
            }
        } catch (ParseException unused) {
        }
        return "GMT" + str + m24181a(i) + ":" + m24181a(i2);
    }

    /* renamed from: q */
    private boolean m24174q() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f161v3;
            if (i == bArr.length) {
                return false;
            }
            if (bArr[i] == 46 && i == 14) {
                return true;
            }
            i++;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(this.f161v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        int length = this.f161v3.length;
        return C0177m2.m24099a(length) + 1 + length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public Date m24178m() {
        SimpleDateFormat simpleDateFormat;
        char charAt;
        String m22442b = C0678j.m22442b(this.f161v3);
        if (m22442b.endsWith("Z")) {
            if (m24174q()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS'Z'");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        } else if (m22442b.indexOf(45) <= 0 && m22442b.indexOf(43) <= 0) {
            if (m24174q()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, TimeZone.getDefault().getID()));
        } else {
            m22442b = m24177n();
            if (m24174q()) {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSSz");
            } else {
                simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssz");
            }
            simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        }
        if (m24174q()) {
            String substring = m22442b.substring(14);
            int i = 1;
            while (i < substring.length() && '0' <= (charAt = substring.charAt(i)) && charAt <= '9') {
                i++;
            }
            int i2 = i - 1;
            if (i2 > 3) {
                m22442b = m22442b.substring(0, 14) + (substring.substring(0, 4) + substring.substring(i));
            } else if (i2 == 1) {
                m22442b = m22442b.substring(0, 14) + (substring.substring(0, i) + "00" + substring.substring(i));
            } else if (i2 == 2) {
                m22442b = m22442b.substring(0, 14) + (substring.substring(0, i) + "0" + substring.substring(i));
            }
        }
        return simpleDateFormat.parse(m22442b);
    }

    /* renamed from: n */
    public String m24177n() {
        int length;
        String m22442b = C0678j.m22442b(this.f161v3);
        if (m22442b.charAt(m22442b.length() - 1) == 'Z') {
            return m22442b.substring(0, m22442b.length() - 1) + "GMT+00:00";
        }
        int length2 = m22442b.length() - 5;
        char charAt = m22442b.charAt(length2);
        if (charAt != '-' && charAt != '+') {
            char charAt2 = m22442b.charAt(m22442b.length() - 3);
            if (charAt2 != '-' && charAt2 != '+') {
                return m22442b + m24175p();
            }
            return m22442b.substring(0, length) + "GMT" + m22442b.substring(length) + ":00";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(m22442b.substring(0, length2));
        sb.append("GMT");
        int i = length2 + 3;
        sb.append(m22442b.substring(length2, i));
        sb.append(":");
        sb.append(m22442b.substring(i));
        return sb.toString();
    }

    /* renamed from: o */
    public String m24176o() {
        return C0678j.m22442b(this.f161v3);
    }

    public C0138d1(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(new SimpleTimeZone(0, "Z"));
        this.f161v3 = C0678j.m22448a(simpleDateFormat.format(date));
    }

    public C0138d1(byte[] bArr) {
        this.f161v3 = bArr;
    }

    /* renamed from: a */
    public static C0153h m24180a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0138d1)) {
            return new C0153h(((AbstractC0182o) m23004m).mo24088m());
        }
        return m24179a((Object) m23004m);
    }

    /* renamed from: a */
    private String m24181a(int i) {
        if (i < 10) {
            return "0" + i;
        }
        return Integer.toString(i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(24, this.f161v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0138d1) {
            return C0669a.m22499a(this.f161v3, ((C0138d1) abstractC0258r).f161v3);
        }
        return false;
    }
}
