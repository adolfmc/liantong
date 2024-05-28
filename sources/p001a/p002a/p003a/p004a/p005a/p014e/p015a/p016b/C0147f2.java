package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.EOFException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.C0690a;

/* renamed from: a.a.a.a.a.e.a.b.f2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0147f2 extends AbstractC0169k2 {

    /* renamed from: G */
    public static final byte[] f177G = new byte[0];

    /* renamed from: E */
    public final int f178E;

    /* renamed from: F */
    public int f179F;

    public C0147f2(InputStream inputStream, int i) {
        super(inputStream, i);
        if (i >= 0) {
            this.f178E = i;
            this.f179F = i;
            if (i == 0) {
                m24109a(true);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("negative lengths not allowed");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0169k2
    /* renamed from: a */
    public int mo24110a() {
        return this.f179F;
    }

    /* renamed from: b */
    public byte[] m24154b() {
        int i = this.f179F;
        if (i == 0) {
            return f177G;
        }
        byte[] bArr = new byte[i];
        int m22391a = i - C0690a.m22391a(this.f208C, bArr);
        this.f179F = m22391a;
        if (m22391a == 0) {
            m24109a(true);
            return bArr;
        }
        throw new EOFException("DEF length " + this.f178E + " object truncated by " + this.f179F);
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f179F == 0) {
            return -1;
        }
        int read = this.f208C.read();
        if (read >= 0) {
            int i = this.f179F - 1;
            this.f179F = i;
            if (i == 0) {
                m24109a(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.f178E + " object truncated by " + this.f179F);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        int i3 = this.f179F;
        if (i3 == 0) {
            return -1;
        }
        int read = this.f208C.read(bArr, i, Math.min(i2, i3));
        if (read >= 0) {
            int i4 = this.f179F - read;
            this.f179F = i4;
            if (i4 == 0) {
                m24109a(true);
            }
            return read;
        }
        throw new EOFException("DEF length " + this.f178E + " object truncated by " + this.f179F);
    }
}
