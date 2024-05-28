package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.EOFException;
import java.io.InputStream;

/* renamed from: a.a.a.a.a.e.a.b.h2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0157h2 extends AbstractC0169k2 {

    /* renamed from: E */
    public int f191E;

    /* renamed from: F */
    public int f192F;

    /* renamed from: G */
    public boolean f193G;

    /* renamed from: H */
    public boolean f194H;

    public C0157h2(InputStream inputStream, int i) {
        super(inputStream, i);
        this.f193G = false;
        this.f194H = true;
        this.f191E = inputStream.read();
        int read = inputStream.read();
        this.f192F = read;
        if (read >= 0) {
            m24142b();
            return;
        }
        throw new EOFException();
    }

    /* renamed from: b */
    public void m24141b(boolean z) {
        this.f194H = z;
        m24142b();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (!this.f194H && i2 >= 3) {
            if (this.f193G) {
                return -1;
            }
            int read = this.f208C.read(bArr, i + 2, i2 - 2);
            if (read >= 0) {
                bArr[i] = (byte) this.f191E;
                bArr[i + 1] = (byte) this.f192F;
                this.f191E = this.f208C.read();
                int read2 = this.f208C.read();
                this.f192F = read2;
                if (read2 >= 0) {
                    return read + 2;
                }
                throw new EOFException();
            }
            throw new EOFException();
        }
        return super.read(bArr, i, i2);
    }

    /* renamed from: b */
    private boolean m24142b() {
        if (!this.f193G && this.f194H && this.f191E == 0 && this.f192F == 0) {
            this.f193G = true;
            m24109a(true);
        }
        return this.f193G;
    }

    @Override // java.io.InputStream
    public int read() {
        if (m24142b()) {
            return -1;
        }
        int read = this.f208C.read();
        if (read >= 0) {
            int i = this.f191E;
            this.f191E = this.f192F;
            this.f192F = read;
            return i;
        }
        throw new EOFException();
    }
}
