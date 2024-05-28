package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.C0690a;

/* renamed from: a.a.a.a.a.e.a.b.e1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0142e1 extends AbstractC0158i {

    /* renamed from: b */
    public boolean f166b;

    /* renamed from: c */
    public boolean f167c;

    /* renamed from: d */
    public int f168d;

    public AbstractC0142e1(OutputStream outputStream) {
        super(outputStream);
        this.f166b = false;
    }

    /* renamed from: a */
    private void m24165a(OutputStream outputStream, int i) {
        if (i > 127) {
            int i2 = i;
            int i3 = 1;
            while (true) {
                i2 >>>= 8;
                if (i2 == 0) {
                    break;
                }
                i3++;
            }
            outputStream.write((byte) (i3 | 128));
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                outputStream.write((byte) (i >> i4));
            }
            return;
        }
        outputStream.write((byte) i);
    }

    public AbstractC0142e1(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this.f166b = false;
        this.f166b = true;
        this.f167c = z;
        this.f168d = i;
    }

    /* renamed from: a */
    public void m24163a(OutputStream outputStream, int i, byte[] bArr) {
        outputStream.write(i);
        m24165a(outputStream, bArr.length);
        outputStream.write(bArr);
    }

    /* renamed from: a */
    public void m24166a(int i, byte[] bArr) {
        if (this.f166b) {
            int i2 = this.f168d;
            int i3 = i2 | 128;
            if (this.f167c) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                m24163a(byteArrayOutputStream, i, bArr);
                m24163a(this.f195a, i2 | 32 | 128, byteArrayOutputStream.toByteArray());
                return;
            } else if ((i & 32) != 0) {
                m24163a(this.f195a, i3 | 32, bArr);
                return;
            } else {
                m24163a(this.f195a, i3, bArr);
                return;
            }
        }
        m24163a(this.f195a, i, bArr);
    }

    /* renamed from: a */
    public void m24164a(OutputStream outputStream, int i, InputStream inputStream) {
        m24163a(outputStream, i, C0690a.m22389b(inputStream));
    }
}
