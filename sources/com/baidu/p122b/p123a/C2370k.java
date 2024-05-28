package com.baidu.p122b.p123a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.a.k */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2370k {

    /* renamed from: a */
    private final byte[] f4117a;

    /* renamed from: b */
    private final int[] f4118b;

    /* renamed from: c */
    private final byte[] f4119c;

    /* renamed from: d */
    private final int f4120d;

    /* renamed from: e */
    private int f4121e;

    /* renamed from: f */
    private int f4122f;

    /* JADX INFO: Access modifiers changed from: protected */
    public C2370k() {
        this(0);
    }

    protected C2370k(int i) {
        this.f4117a = new byte[1];
        this.f4118b = new int[4];
        this.f4119c = new byte[16];
        this.f4120d = i;
        m20357b();
    }

    /* renamed from: a */
    private static int m20359a(byte[] bArr, int i) {
        return (int) (m20355b(bArr, i, 4) & 4294967295L);
    }

    /* renamed from: b */
    private static long m20355b(byte[] bArr, int i, int i2) {
        if (i2 <= 8) {
            long j = 0;
            for (int i3 = 0; i3 < i2; i3++) {
                j |= (bArr[i + i3] & 255) << (i3 * 8);
            }
            return j;
        }
        throw new IllegalArgumentException("can't read more than eight bytes into a long value");
    }

    /* renamed from: b */
    private void m20357b() {
        int[] iArr = this.f4118b;
        int i = this.f4120d;
        iArr[0] = (i - 1640531535) - 2048144777;
        iArr[1] = (-2048144777) + i;
        iArr[2] = i;
        iArr[3] = i - (-1640531535);
    }

    /* renamed from: b */
    private void m20356b(byte[] bArr, int i) {
        int[] iArr = this.f4118b;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int[] iArr2 = this.f4118b;
        iArr2[0] = Integer.rotateLeft(i2 + (m20359a(bArr, i) * (-2048144777)), 13) * (-1640531535);
        iArr2[1] = Integer.rotateLeft(i3 + (m20359a(bArr, i + 4) * (-2048144777)), 13) * (-1640531535);
        iArr2[2] = Integer.rotateLeft(i4 + (m20359a(bArr, i + 8) * (-2048144777)), 13) * (-1640531535);
        iArr2[3] = Integer.rotateLeft(i5 + (m20359a(bArr, i + 12) * (-2048144777)), 13) * (-1640531535);
        this.f4122f = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public long m20360a() {
        int i = 0;
        int rotateLeft = (this.f4121e > 16 ? Integer.rotateLeft(this.f4118b[0], 1) + Integer.rotateLeft(this.f4118b[1], 7) + Integer.rotateLeft(this.f4118b[2], 12) + Integer.rotateLeft(this.f4118b[3], 18) : this.f4118b[2] + 374761393) + this.f4121e;
        int i2 = this.f4122f - 4;
        while (i <= i2) {
            rotateLeft = Integer.rotateLeft(rotateLeft + (m20359a(this.f4119c, i) * (-1028477379)), 17) * 668265263;
            i += 4;
        }
        while (i < this.f4122f) {
            rotateLeft = Integer.rotateLeft(rotateLeft + ((this.f4119c[i] & 255) * 374761393), 11) * (-1640531535);
            i++;
        }
        int i3 = (rotateLeft ^ (rotateLeft >>> 15)) * (-2048144777);
        int i4 = (i3 ^ (i3 >>> 13)) * (-1028477379);
        return (i4 ^ (i4 >>> 16)) & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m20358a(byte[] bArr, int i, int i2) {
        if (i2 <= 0) {
            return;
        }
        this.f4121e += i2;
        int i3 = i + i2;
        int i4 = this.f4122f;
        if (i4 + i2 < 16) {
            System.arraycopy(bArr, i, this.f4119c, i4, i2);
            this.f4122f += i2;
            return;
        }
        if (i4 > 0) {
            int i5 = 16 - i4;
            System.arraycopy(bArr, i, this.f4119c, i4, i5);
            m20356b(this.f4119c, 0);
            i += i5;
        }
        int i6 = i3 - 16;
        while (i <= i6) {
            m20356b(bArr, i);
            i += 16;
        }
        if (i < i3) {
            this.f4122f = i3 - i;
            System.arraycopy(bArr, i, this.f4119c, 0, this.f4122f);
        }
    }
}
