package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;

/* renamed from: a.a.a.a.a.e.a.b.m */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0174m implements InterfaceC0136d {
    /* renamed from: a */
    public byte[] m24102a(String str) {
        if (str.equals("DER")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new C0176m1(byteArrayOutputStream).mo23772a(this);
            return byteArrayOutputStream.toByteArray();
        } else if (str.equals("DL")) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            new C0131b2(byteArrayOutputStream2).mo23772a(this);
            return byteArrayOutputStream2.toByteArray();
        } else {
            return m24101g();
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public abstract AbstractC0258r mo23015d();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InterfaceC0136d) {
            return mo23015d().equals(((InterfaceC0136d) obj).mo23015d());
        }
        return false;
    }

    /* renamed from: g */
    public byte[] m24101g() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new C0252q(byteArrayOutputStream).mo23772a(this);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: h */
    public AbstractC0258r m24100h() {
        return mo23015d();
    }

    public int hashCode() {
        return mo23015d().hashCode();
    }

    /* renamed from: a */
    public static boolean m24103a(Object obj, int i) {
        return (obj instanceof byte[]) && ((byte[]) obj)[0] == i;
    }
}
