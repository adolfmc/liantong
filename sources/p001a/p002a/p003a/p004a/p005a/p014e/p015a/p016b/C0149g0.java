package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: a.a.a.a.a.e.a.b.g0 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0149g0 extends AbstractC0182o {

    /* renamed from: x3 */
    public static final int f180x3 = 1000;

    /* renamed from: w3 */
    public AbstractC0182o[] f181w3;

    /* renamed from: a.a.a.a.a.e.a.b.g0$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0150a implements Enumeration {

        /* renamed from: a */
        public int f182a = 0;

        public C0150a() {
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f182a < C0149g0.this.f181w3.length;
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            AbstractC0182o[] abstractC0182oArr = C0149g0.this.f181w3;
            int i = this.f182a;
            this.f182a = i + 1;
            return abstractC0182oArr[i];
        }
    }

    public C0149g0(byte[] bArr) {
        super(bArr);
    }

    /* renamed from: p */
    private Vector m24149p() {
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

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: i */
    public int mo22977i() {
        Enumeration mo24150o = mo24150o();
        int i = 0;
        while (mo24150o.hasMoreElements()) {
            i += ((InterfaceC0136d) mo24150o.nextElement()).mo23015d().mo22977i();
        }
        return i + 2 + 2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: j */
    public boolean mo22976j() {
        return true;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o
    /* renamed from: m */
    public byte[] mo24088m() {
        return this.f233v3;
    }

    /* renamed from: o */
    public Enumeration mo24150o() {
        if (this.f181w3 == null) {
            return m24149p().elements();
        }
        return new C0150a();
    }

    public C0149g0(AbstractC0182o[] abstractC0182oArr) {
        super(m24151a(abstractC0182oArr));
        this.f181w3 = abstractC0182oArr;
    }

    /* renamed from: a */
    public static byte[] m24151a(AbstractC0182o[] abstractC0182oArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i != abstractC0182oArr.length; i++) {
            try {
                byteArrayOutputStream.write(((C0168k1) abstractC0182oArr[i]).mo24088m());
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(abstractC0182oArr[i].getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0182o, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public void mo22982a(C0252q c0252q) {
        c0252q.mo23763a(36);
        c0252q.mo23763a(128);
        Enumeration mo24150o = mo24150o();
        while (mo24150o.hasMoreElements()) {
            c0252q.mo23772a((InterfaceC0136d) mo24150o.nextElement());
        }
        c0252q.mo23763a(0);
        c0252q.mo23763a(0);
    }

    /* renamed from: a */
    public static C0149g0 m24152a(AbstractC0263s abstractC0263s) {
        AbstractC0182o[] abstractC0182oArr = new AbstractC0182o[abstractC0263s.mo23745o()];
        Enumeration mo23747m = abstractC0263s.mo23747m();
        int i = 0;
        while (mo23747m.hasMoreElements()) {
            abstractC0182oArr[i] = (AbstractC0182o) mo23747m.nextElement();
            i++;
        }
        return new C0149g0(abstractC0182oArr);
    }
}
