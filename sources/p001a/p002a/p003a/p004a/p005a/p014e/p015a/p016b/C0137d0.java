package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: a.a.a.a.a.e.a.b.d0 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0137d0 extends C0149g0 {

    /* renamed from: z3 */
    public static final int f159z3 = 1000;

    /* renamed from: y3 */
    public Vector f160y3;

    public C0137d0(byte[] bArr) {
        super(bArr);
    }

    /* renamed from: a */
    public static byte[] m24184a(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != vector.size(); i++) {
            try {
                byteArrayOutputStream.write(((C0168k1) vector.elementAt(i)).mo24088m());
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(vector.elementAt(i).getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    public static byte[] m24183b(AbstractC0258r abstractC0258r) {
        try {
            return abstractC0258r.m24101g();
        } catch (IOException unused) {
            throw new IllegalArgumentException("Unable to encode object");
        }
    }

    /* renamed from: p */
    private Vector m24182p() {
        Vector vector = new Vector();
        int i = 0;
        while (true) {
            byte[] bArr = this.f233v3;
            if (i >= bArr.length) {
                return vector;
            }
            int i2 = i + 1000;
            int length = (i2 > bArr.length ? bArr.length : i2) - i;
            byte[] bArr2 = new byte[length];
            System.arraycopy(this.f233v3, i, bArr2, 0, length);
            vector.addElement(new C0168k1(bArr2));
            i = i2;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0149g0, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o
    /* renamed from: m */
    public byte[] mo24088m() {
        return this.f233v3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0149g0
    /* renamed from: o */
    public Enumeration mo24150o() {
        Vector vector = this.f160y3;
        if (vector == null) {
            return m24182p().elements();
        }
        return vector.elements();
    }

    public C0137d0(Vector vector) {
        super(m24184a(vector));
        this.f160y3 = vector;
    }

    public C0137d0(AbstractC0258r abstractC0258r) {
        super(m24183b(abstractC0258r));
    }

    public C0137d0(InterfaceC0136d interfaceC0136d) {
        this(interfaceC0136d.mo23015d());
    }

    /* renamed from: a */
    public static C0149g0 m24185a(AbstractC0263s abstractC0263s) {
        Vector vector = new Vector();
        Enumeration mo23747m = abstractC0263s.mo23747m();
        while (mo23747m.hasMoreElements()) {
            vector.addElement(mo23747m.nextElement());
        }
        return new C0137d0(vector);
    }
}
