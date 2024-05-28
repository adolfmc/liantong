package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j;

import java.util.Hashtable;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0605o;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0673e;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.o0.j.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0621d {

    /* renamed from: a */
    public static final Hashtable f1935a;

    static {
        Hashtable hashtable = new Hashtable();
        f1935a = hashtable;
        hashtable.put("SHA-1", C0673e.m22455a(128));
        f1935a.put("SHA-224", C0673e.m22455a(192));
        f1935a.put("SHA-256", C0673e.m22455a(256));
        f1935a.put("SHA-384", C0673e.m22455a(256));
        f1935a.put("SHA-512", C0673e.m22455a(256));
        f1935a.put("SHA-512/224", C0673e.m22455a(192));
        f1935a.put("SHA-512/256", C0673e.m22455a(256));
    }

    /* renamed from: a */
    public static int m22716a(InterfaceC0605o interfaceC0605o) {
        return ((Integer) f1935a.get(interfaceC0605o.mo22748a())).intValue();
    }

    /* renamed from: a */
    public static int m22714a(InterfaceC0641v interfaceC0641v) {
        String mo22656a = interfaceC0641v.mo22656a();
        return ((Integer) f1935a.get(mo22656a.substring(0, mo22656a.indexOf("/")))).intValue();
    }

    /* renamed from: a */
    public static byte[] m22715a(InterfaceC0605o interfaceC0605o, byte[] bArr, int i) {
        int i2 = (i + 7) / 8;
        byte[] bArr2 = new byte[i2];
        int mo22743c = i2 / interfaceC0605o.mo22743c();
        int mo22743c2 = interfaceC0605o.mo22743c();
        byte[] bArr3 = new byte[mo22743c2];
        int i3 = 0;
        int i4 = 1;
        for (int i5 = 0; i5 <= mo22743c; i5++) {
            interfaceC0605o.mo22747a((byte) i4);
            interfaceC0605o.mo22747a((byte) (i >> 24));
            interfaceC0605o.mo22747a((byte) (i >> 16));
            interfaceC0605o.mo22747a((byte) (i >> 8));
            interfaceC0605o.mo22747a((byte) i);
            interfaceC0605o.mo22745a(bArr, 0, bArr.length);
            interfaceC0605o.mo22746a(bArr3, 0);
            int i6 = i5 * mo22743c2;
            int i7 = i2 - i6;
            if (i7 > mo22743c2) {
                i7 = mo22743c2;
            }
            System.arraycopy(bArr3, 0, bArr2, i6, i7);
            i4++;
        }
        int i8 = i % 8;
        if (i8 != 0) {
            int i9 = 8 - i8;
            int i10 = 0;
            while (i3 != i2) {
                int i11 = bArr2[i3] & 255;
                bArr2[i3] = (byte) ((i10 << (8 - i9)) | (i11 >>> i9));
                i3++;
                i10 = i11;
            }
        }
        return bArr2;
    }

    /* renamed from: a */
    public static boolean m22713a(byte[] bArr, int i) {
        return bArr != null && bArr.length > i;
    }
}
