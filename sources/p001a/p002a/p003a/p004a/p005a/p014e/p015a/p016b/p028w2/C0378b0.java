package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import java.io.IOException;
import java.util.StringTokenizer;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0263s;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0494y;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0146f1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0164j1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0168k1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0360v1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0132c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p026v2.C0364d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0672d;

/* renamed from: a.a.a.a.a.e.a.b.w2.b0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0378b0 extends AbstractC0174m implements InterfaceC0132c {

    /* renamed from: A3 */
    public static final int f1014A3 = 3;

    /* renamed from: B3 */
    public static final int f1015B3 = 4;

    /* renamed from: C3 */
    public static final int f1016C3 = 5;

    /* renamed from: D3 */
    public static final int f1017D3 = 6;

    /* renamed from: E3 */
    public static final int f1018E3 = 7;

    /* renamed from: F3 */
    public static final int f1019F3 = 8;

    /* renamed from: x3 */
    public static final int f1020x3 = 0;

    /* renamed from: y3 */
    public static final int f1021y3 = 1;

    /* renamed from: z3 */
    public static final int f1022z3 = 2;

    /* renamed from: v3 */
    public InterfaceC0136d f1023v3;

    /* renamed from: w3 */
    public int f1024w3;

    public C0378b0(C0430s1 c0430s1) {
        this.f1023v3 = C0364d.m23537a(c0430s1);
        this.f1024w3 = 4;
    }

    /* renamed from: a */
    public static C0378b0 m23455a(Object obj) {
        if (obj != null && !(obj instanceof C0378b0)) {
            if (obj instanceof AbstractC0494y) {
                AbstractC0494y abstractC0494y = (AbstractC0494y) obj;
                int mo22994f = abstractC0494y.mo22994f();
                switch (mo22994f) {
                    case 0:
                        return new C0378b0(mo22994f, AbstractC0263s.m23750a(abstractC0494y, false));
                    case 1:
                        return new C0378b0(mo22994f, C0146f1.m24158a(abstractC0494y, false));
                    case 2:
                        return new C0378b0(mo22994f, C0146f1.m24158a(abstractC0494y, false));
                    case 3:
                        throw new IllegalArgumentException("unknown tag: " + mo22994f);
                    case 4:
                        return new C0378b0(mo22994f, C0364d.m23538a(abstractC0494y, true));
                    case 5:
                        return new C0378b0(mo22994f, AbstractC0263s.m23750a(abstractC0494y, false));
                    case 6:
                        return new C0378b0(mo22994f, C0146f1.m24158a(abstractC0494y, false));
                    case 7:
                        return new C0378b0(mo22994f, AbstractC0182o.m24090a(abstractC0494y, false));
                    case 8:
                        return new C0378b0(mo22994f, C0164j1.m24122a(abstractC0494y, false));
                }
            }
            if (obj instanceof byte[]) {
                try {
                    return m23455a(AbstractC0258r.m23755a((byte[]) obj));
                } catch (IOException unused) {
                    throw new IllegalArgumentException("unable to parse encoded general name");
                }
            }
            throw new IllegalArgumentException("unknown object in getInstance: " + obj.getClass().getName());
        }
        return (C0378b0) obj;
    }

    /* renamed from: b */
    private void m23451b(String str, byte[] bArr, int i) {
        int parseInt = Integer.parseInt(str);
        for (int i2 = 0; i2 != parseInt; i2++) {
            int i3 = (i2 / 8) + i;
            bArr[i3] = (byte) (bArr[i3] | (1 << (7 - (i2 % 8))));
        }
    }

    /* renamed from: c */
    private int[] m23450c(String str) {
        int[] iArr = new int[8];
        int parseInt = Integer.parseInt(str);
        for (int i = 0; i != parseInt; i++) {
            int i2 = i / 16;
            iArr[i2] = iArr[i2] | (1 << (15 - (i % 16)));
        }
        return iArr;
    }

    /* renamed from: d */
    private byte[] m23449d(String str) {
        int[] m23450c;
        if (!C0672d.m22457e(str) && !C0672d.m22458d(str)) {
            if (C0672d.m22459c(str) || C0672d.m22460b(str)) {
                int indexOf = str.indexOf(47);
                if (indexOf < 0) {
                    byte[] bArr = new byte[4];
                    m23454a(str, bArr, 0);
                    return bArr;
                }
                byte[] bArr2 = new byte[8];
                m23454a(str.substring(0, indexOf), bArr2, 0);
                String substring = str.substring(indexOf + 1);
                if (substring.indexOf(46) > 0) {
                    m23454a(substring, bArr2, 4);
                } else {
                    m23451b(substring, bArr2, 4);
                }
                return bArr2;
            }
            return null;
        }
        int indexOf2 = str.indexOf(47);
        if (indexOf2 < 0) {
            byte[] bArr3 = new byte[16];
            m23453a(m23452b(str), bArr3, 0);
            return bArr3;
        }
        byte[] bArr4 = new byte[32];
        m23453a(m23452b(str.substring(0, indexOf2)), bArr4, 0);
        String substring2 = str.substring(indexOf2 + 1);
        if (substring2.indexOf(58) > 0) {
            m23450c = m23452b(substring2);
        } else {
            m23450c = m23450c(substring2);
        }
        m23453a(m23450c, bArr4, 16);
        return bArr4;
    }

    /* renamed from: f */
    public int m23448f() {
        return this.f1024w3;
    }

    /* renamed from: i */
    public InterfaceC0136d m23447i() {
        return this.f1023v3;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f1024w3);
        stringBuffer.append(": ");
        int i = this.f1024w3;
        if (i != 1 && i != 2) {
            if (i == 4) {
                stringBuffer.append(C0364d.m23537a(this.f1023v3).toString());
            } else if (i != 6) {
                stringBuffer.append(this.f1023v3.toString());
            }
            return stringBuffer.toString();
        }
        stringBuffer.append(C0146f1.m24157a(this.f1023v3).mo22978e());
        return stringBuffer.toString();
    }

    public C0378b0(C0364d c0364d) {
        this.f1023v3 = c0364d;
        this.f1024w3 = 4;
    }

    /* renamed from: b */
    private int[] m23452b(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ":", true);
        int[] iArr = new int[8];
        if (str.charAt(0) == ':' && str.charAt(1) == ':') {
            stringTokenizer.nextToken();
        }
        int i = -1;
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals(":")) {
                iArr[i2] = 0;
                i = i2;
                i2++;
            } else if (nextToken.indexOf(46) < 0) {
                int i3 = i2 + 1;
                iArr[i2] = Integer.parseInt(nextToken, 16);
                if (stringTokenizer.hasMoreTokens()) {
                    stringTokenizer.nextToken();
                }
                i2 = i3;
            } else {
                StringTokenizer stringTokenizer2 = new StringTokenizer(nextToken, ".");
                int i4 = i2 + 1;
                iArr[i2] = (Integer.parseInt(stringTokenizer2.nextToken()) << 8) | Integer.parseInt(stringTokenizer2.nextToken());
                i2 = i4 + 1;
                iArr[i4] = Integer.parseInt(stringTokenizer2.nextToken()) | (Integer.parseInt(stringTokenizer2.nextToken()) << 8);
            }
        }
        if (i2 != 8) {
            int i5 = i2 - i;
            int i6 = 8 - i5;
            System.arraycopy(iArr, i, iArr, i6, i5);
            while (i != i6) {
                iArr[i] = 0;
                i++;
            }
        }
        return iArr;
    }

    public C0378b0(int i, InterfaceC0136d interfaceC0136d) {
        this.f1023v3 = interfaceC0136d;
        this.f1024w3 = i;
    }

    public C0378b0(int i, String str) {
        this.f1024w3 = i;
        if (i == 1 || i == 2 || i == 6) {
            this.f1023v3 = new C0146f1(str);
        } else if (i == 8) {
            this.f1023v3 = new C0178n(str);
        } else if (i == 4) {
            this.f1023v3 = new C0364d(str);
        } else if (i == 7) {
            byte[] m23449d = m23449d(str);
            if (m23449d != null) {
                this.f1023v3 = new C0168k1(m23449d);
                return;
            }
            throw new IllegalArgumentException("IP Address is invalid");
        } else {
            throw new IllegalArgumentException("can't process String for tag: " + i);
        }
    }

    /* renamed from: a */
    public static C0378b0 m23456a(AbstractC0494y abstractC0494y, boolean z) {
        return m23455a(AbstractC0494y.m23009a(abstractC0494y, true));
    }

    /* renamed from: a */
    private void m23454a(String str, byte[] bArr, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "./");
        int i2 = 0;
        while (stringTokenizer.hasMoreTokens()) {
            bArr[i2 + i] = (byte) Integer.parseInt(stringTokenizer.nextToken());
            i2++;
        }
    }

    /* renamed from: a */
    private void m23453a(int[] iArr, byte[] bArr, int i) {
        for (int i2 = 0; i2 != iArr.length; i2++) {
            int i3 = i2 * 2;
            bArr[i3 + i] = (byte) (iArr[i2] >> 8);
            bArr[i3 + 1 + i] = (byte) iArr[i2];
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        int i = this.f1024w3;
        if (i == 4) {
            return new C0360v1(true, i, this.f1023v3);
        }
        return new C0360v1(false, i, this.f1023v3);
    }
}
