package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.w */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC0642w {

    /* renamed from: a */
    public byte[] f1971a;

    /* renamed from: b */
    public byte[] f1972b;

    /* renamed from: c */
    public int f1973c;

    /* renamed from: a */
    public abstract InterfaceC0542i m22648a(int i);

    /* renamed from: a */
    public abstract InterfaceC0542i m22647a(int i, int i2);

    /* renamed from: a */
    public void m22646a(byte[] bArr, byte[] bArr2, int i) {
        this.f1971a = bArr;
        this.f1972b = bArr2;
        this.f1973c = i;
    }

    /* renamed from: b */
    public abstract InterfaceC0542i m22643b(int i);

    /* renamed from: b */
    public byte[] m22644b() {
        return this.f1971a;
    }

    /* renamed from: c */
    public byte[] m22641c() {
        return this.f1972b;
    }

    /* renamed from: b */
    public static byte[] m22642b(char[] cArr) {
        if (cArr != null) {
            int length = cArr.length;
            byte[] bArr = new byte[length];
            for (int i = 0; i != length; i++) {
                bArr[i] = (byte) cArr[i];
            }
            return bArr;
        }
        return new byte[0];
    }

    /* renamed from: c */
    public static byte[] m22640c(char[] cArr) {
        if (cArr != null) {
            return C0678j.m22441b(cArr);
        }
        return new byte[0];
    }

    /* renamed from: a */
    public int m22649a() {
        return this.f1973c;
    }

    /* renamed from: a */
    public static byte[] m22645a(char[] cArr) {
        if (cArr != null && cArr.length > 0) {
            byte[] bArr = new byte[(cArr.length + 1) * 2];
            for (int i = 0; i != cArr.length; i++) {
                int i2 = i * 2;
                bArr[i2] = (byte) (cArr[i] >>> '\b');
                bArr[i2 + 1] = (byte) cArr[i];
            }
            return bArr;
        }
        return new byte[0];
    }
}
