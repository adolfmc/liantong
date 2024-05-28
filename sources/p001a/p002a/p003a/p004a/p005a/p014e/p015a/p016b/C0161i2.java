package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;
import java.util.Enumeration;

/* renamed from: a.a.a.a.a.e.a.b.i2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0161i2 implements Enumeration {

    /* renamed from: a */
    public C0162j f198a;

    /* renamed from: b */
    public Object f199b = m24136a();

    public C0161i2(byte[] bArr) {
        this.f198a = new C0162j(bArr, true);
    }

    /* renamed from: a */
    private Object m24136a() {
        try {
            return this.f198a.m24124d();
        } catch (IOException e) {
            throw new ASN1ParsingException("malformed DER construction: " + e, e);
        }
    }

    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return this.f199b != null;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        Object obj = this.f199b;
        this.f199b = m24136a();
        return obj;
    }
}
