package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.b.z1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0499z1 extends AbstractC0258r implements InterfaceC0452x {

    /* renamed from: w3 */
    public static final char[] f1617w3 = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: v3 */
    public byte[] f1618v3;

    public C0499z1(byte[] bArr) {
        this.f1618v3 = bArr;
    }

    /* renamed from: a */
    public static C0499z1 m22979a(Object obj) {
        if (obj != null && !(obj instanceof C0499z1)) {
            if (obj instanceof byte[]) {
                try {
                    return (C0499z1) AbstractC0258r.m23755a((byte[]) obj);
                } catch (Exception e) {
                    throw new IllegalArgumentException("encoding error getInstance: " + e.toString());
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (C0499z1) obj;
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
                stringBuffer.append(f1617w3[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(f1617w3[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException unused) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(this.f1618v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        return C0177m2.m24099a(this.f1618v3.length) + 1 + this.f1618v3.length;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return false;
    }

    /* renamed from: m */
    public byte[] m22975m() {
        return this.f1618v3;
    }

    public String toString() {
        return mo22978e();
    }

    /* renamed from: a */
    public static C0499z1 m22980a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof C0499z1)) {
            return new C0499z1(((AbstractC0182o) m23004m).mo24088m());
        }
        return m22979a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.m23773a(28, m22975m());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof C0499z1) {
            return C0669a.m22499a(this.f1618v3, ((C0499z1) abstractC0258r).f1618v3);
        }
        return false;
    }
}
