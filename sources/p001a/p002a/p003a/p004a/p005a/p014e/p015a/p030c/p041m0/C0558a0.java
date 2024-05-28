package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0602n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.a0 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0558a0 implements InterfaceC0602n {

    /* renamed from: a */
    public final byte[] f1802a;

    /* renamed from: b */
    public final boolean f1803b;

    /* renamed from: c */
    public final byte[] f1804c;

    /* renamed from: d */
    public final byte[] f1805d;

    public C0558a0(byte[] bArr, boolean z, byte[] bArr2, byte[] bArr3) {
        if (bArr != null) {
            this.f1802a = C0669a.m22503a(bArr);
            this.f1803b = z;
            if (bArr2 != null && bArr2.length != 0) {
                this.f1804c = C0669a.m22503a(bArr2);
            } else {
                this.f1804c = null;
            }
            if (bArr3 == null) {
                this.f1805d = new byte[0];
                return;
            } else {
                this.f1805d = C0669a.m22503a(bArr3);
                return;
            }
        }
        throw new IllegalArgumentException("IKM (input keying material) should not be null");
    }

    /* renamed from: a */
    public static C0558a0 m22842a(byte[] bArr, byte[] bArr2) {
        return new C0558a0(bArr, true, null, bArr2);
    }

    /* renamed from: b */
    public byte[] m22841b() {
        return C0669a.m22503a(this.f1805d);
    }

    /* renamed from: c */
    public byte[] m22840c() {
        return C0669a.m22503a(this.f1804c);
    }

    /* renamed from: d */
    public boolean m22839d() {
        return this.f1803b;
    }

    /* renamed from: a */
    public static C0558a0 m22843a(byte[] bArr) {
        return new C0558a0(bArr, false, null, null);
    }

    /* renamed from: a */
    public byte[] m22844a() {
        return C0669a.m22503a(this.f1802a);
    }

    public C0558a0(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this(bArr, false, bArr2, bArr3);
    }
}
