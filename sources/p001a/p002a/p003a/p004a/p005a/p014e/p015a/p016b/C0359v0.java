package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.C0690a;

/* renamed from: a.a.a.a.a.e.a.b.v0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0359v0 extends AbstractC0258r implements InterfaceC0452x {

    /* renamed from: x3 */
    public static final char[] f927x3 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: v3 */
    public byte[] f928v3;

    /* renamed from: w3 */
    public int f929w3;

    public C0359v0(byte b, int i) {
        this.f928v3 = r0;
        byte[] bArr = {b};
        this.f929w3 = i;
    }

    /* renamed from: a */
    public static byte[] m23560a(int i) {
        int i2 = 4;
        for (int i3 = 3; i3 >= 1 && ((255 << (i3 * 8)) & i) == 0; i3--) {
            i2--;
        }
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            bArr[i4] = (byte) ((i >> (i4 * 8)) & 255);
        }
        return bArr;
    }

    /* renamed from: b */
    public static int m23556b(int i) {
        int i2;
        int i3 = 3;
        while (true) {
            if (i3 < 0) {
                i2 = 0;
                break;
            } else if (i3 != 0) {
                int i4 = i >> (i3 * 8);
                if (i4 != 0) {
                    i2 = i4 & 255;
                    break;
                }
                i3--;
            } else if (i != 0) {
                i2 = i & 255;
                break;
            } else {
                i3--;
            }
        }
        if (i2 == 0) {
            return 7;
        }
        int i5 = 1;
        while (true) {
            i2 <<= 1;
            if ((i2 & 255) == 0) {
                return 8 - i5;
            }
            i5++;
        }
    }

    /* renamed from: b */
    public static C0359v0 m23555b(byte[] bArr) {
        if (bArr.length >= 1) {
            byte b = bArr[0];
            int length = bArr.length - 1;
            byte[] bArr2 = new byte[length];
            if (length != 0) {
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
            }
            return new C0359v0(bArr2, b);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0452x
    /* renamed from: e */
    public String mo22978e() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new C0252q(byteArrayOutputStream).mo23772a((InterfaceC0136d) this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(f927x3[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(f927x3[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException unused) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return this.f929w3 ^ C0669a.m22472b(this.f928v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f928v3.length + 1) + 1 + this.f928v3.length + 1;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public byte[] m23554m() {
        return this.f928v3;
    }

    /* renamed from: n */
    public int m23553n() {
        return this.f929w3;
    }

    /* renamed from: o */
    public int m23552o() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f928v3;
            if (i == bArr.length || i == 4) {
                break;
            }
            i2 |= (bArr[i] & 255) << (i * 8);
            i++;
        }
        return i2;
    }

    public String toString() {
        return mo22978e();
    }

    public C0359v0(byte[] bArr, int i) {
        this.f928v3 = bArr;
        this.f929w3 = i;
    }

    /* renamed from: a */
    public static C0359v0 m23557a(Object obj) {
        if (obj != null && !(obj instanceof C0359v0)) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0359v0) obj;
    }

    public C0359v0(byte[] bArr) {
        this(bArr, 0);
    }

    public C0359v0(int i) {
        this.f928v3 = m23560a(i);
        this.f929w3 = m23556b(i);
    }

    public C0359v0(InterfaceC0136d interfaceC0136d) {
        this.f928v3 = interfaceC0136d.mo23015d().m24102a("DER");
        this.f929w3 = 0;
    }

    /* renamed from: a */
    public static C0359v0 m23558a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0359v0)) {
            return m23555b(((AbstractC0182o) m23004m).mo24088m());
        }
        return m23557a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        int length = m23554m().length + 1;
        byte[] bArr = new byte[length];
        bArr[0] = (byte) m23553n();
        System.arraycopy(m23554m(), 0, bArr, 1, length - 1);
        c0252q.m23773a(3, bArr);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0359v0) {
            C0359v0 c0359v0 = (C0359v0) abstractC0258r;
            return this.f929w3 == c0359v0.f929w3 && C0669a.m22499a(this.f928v3, c0359v0.f928v3);
        }
        return false;
    }

    /* renamed from: a */
    public static C0359v0 m23559a(int i, InputStream inputStream) {
        if (i >= 1) {
            int read = inputStream.read();
            int i2 = i - 1;
            byte[] bArr = new byte[i2];
            if (i2 != 0 && C0690a.m22391a(inputStream, bArr) != i2) {
                throw new EOFException("EOF encountered in middle of BIT STRING");
            }
            return new C0359v0(bArr, read);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }
}
