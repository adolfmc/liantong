package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.b.h0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0154h0 extends C0145f0 {
    public C0154h0(OutputStream outputStream) {
        super(outputStream);
        m24162a(36);
    }

    /* renamed from: a */
    public OutputStream m24144a(byte[] bArr) {
        return new C0155a(bArr);
    }

    /* renamed from: c */
    public OutputStream m24143c() {
        return m24144a(new byte[1000]);
    }

    public C0154h0(OutputStream outputStream, int i, boolean z) {
        super(outputStream, i, z);
        m24162a(36);
    }

    /* renamed from: a.a.a.a.a.e.a.b.h0$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0155a extends OutputStream {

        /* renamed from: C */
        public byte[] f185C;

        /* renamed from: D */
        public int f186D = 0;

        /* renamed from: E */
        public C0176m1 f187E;

        public C0155a(byte[] bArr) {
            this.f185C = bArr;
            this.f187E = new C0176m1(C0154h0.this.f195a);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            int i = this.f186D;
            if (i != 0) {
                byte[] bArr = new byte[i];
                System.arraycopy(this.f185C, 0, bArr, 0, i);
                C0168k1.m24111a(this.f187E, bArr);
            }
            C0154h0.this.m24160b();
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            byte[] bArr = this.f185C;
            int i2 = this.f186D;
            int i3 = i2 + 1;
            this.f186D = i3;
            bArr[i2] = (byte) i;
            if (i3 == bArr.length) {
                C0168k1.m24111a(this.f187E, bArr);
                this.f186D = 0;
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            while (i2 > 0) {
                int min = Math.min(i2, this.f185C.length - this.f186D);
                System.arraycopy(bArr, i, this.f185C, this.f186D, min);
                int i3 = this.f186D + min;
                this.f186D = i3;
                byte[] bArr2 = this.f185C;
                if (i3 < bArr2.length) {
                    return;
                }
                C0168k1.m24111a(this.f187E, bArr2);
                this.f186D = 0;
                i += min;
                i2 -= min;
            }
        }
    }
}
