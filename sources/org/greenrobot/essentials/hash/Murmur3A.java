package org.greenrobot.essentials.hash;

import java.util.zip.Checksum;
import org.greenrobot.essentials.PrimitiveArrayUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Murmur3A implements Checksum {

    /* renamed from: C1 */
    private static final int f27402C1 = -862048943;

    /* renamed from: C2 */
    private static final int f27403C2 = 461845907;
    private static PrimitiveArrayUtils primitiveArrayUtils = PrimitiveArrayUtils.getInstance();

    /* renamed from: h1 */
    private int f27404h1;
    private int length;
    private int partialK1;
    private int partialK1Pos;
    private final int seed;

    public Murmur3A() {
        this.seed = 0;
    }

    public Murmur3A(int i) {
        this.seed = i;
        this.f27404h1 = i;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        switch (this.partialK1Pos) {
            case 0:
                this.partialK1 = i & 255;
                this.partialK1Pos = 1;
                break;
            case 1:
                this.partialK1 = ((i & 255) << 8) | this.partialK1;
                this.partialK1Pos = 2;
                break;
            case 2:
                this.partialK1 = ((i & 255) << 16) | this.partialK1;
                this.partialK1Pos = 3;
                break;
            case 3:
                this.partialK1 = ((i & 255) << 24) | this.partialK1;
                applyK1(this.partialK1);
                this.partialK1Pos = 0;
                break;
        }
        this.length++;
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        while (this.partialK1Pos != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        int i3 = i2 & 3;
        int i4 = (i2 + i) - i3;
        for (int i5 = i; i5 < i4; i5 += 4) {
            applyK1(primitiveArrayUtils.getIntLE(bArr, i5));
        }
        this.length += i4 - i;
        for (int i6 = 0; i6 < i3; i6++) {
            update(bArr[i4 + i6]);
        }
    }

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    public void updateShort(short s) {
        switch (this.partialK1Pos) {
            case 0:
                this.partialK1 = s & 65535;
                this.partialK1Pos = 2;
                break;
            case 1:
                this.partialK1 = ((s & 65535) << 8) | this.partialK1;
                this.partialK1Pos = 3;
                break;
            case 2:
                this.partialK1 = ((s & 65535) << 16) | this.partialK1;
                applyK1(this.partialK1);
                this.partialK1Pos = 0;
                break;
            case 3:
                this.partialK1 |= (s & 255) << 24;
                applyK1(this.partialK1);
                this.partialK1 = (s >> 8) & 255;
                this.partialK1Pos = 1;
                break;
        }
        this.length += 2;
    }

    public void updateShort(short... sArr) {
        int i;
        int length = sArr.length;
        int i2 = 0;
        if (length > 0 && ((i = this.partialK1Pos) == 0 || i == 2)) {
            if (this.partialK1Pos == 2) {
                this.partialK1 |= (sArr[0] & 65535) << 16;
                applyK1(this.partialK1);
                this.partialK1Pos = 0;
                length--;
                i2 = 1;
            }
            int i3 = (length & (-2)) + i2;
            while (i2 < i3) {
                applyK1((sArr[i2] & 65535) | ((sArr[i2 + 1] & 65535) << 16));
                i2 += 2;
            }
            if (i3 < sArr.length) {
                this.partialK1 = sArr[i3] & 65535;
                this.partialK1Pos = 2;
            }
            this.length += sArr.length * 2;
            return;
        }
        int length2 = sArr.length;
        while (i2 < length2) {
            updateShort(sArr[i2]);
            i2++;
        }
    }

    public void updateInt(int i) {
        switch (this.partialK1Pos) {
            case 0:
                applyK1(i);
                break;
            case 1:
                this.partialK1 |= (16777215 & i) << 8;
                applyK1(this.partialK1);
                this.partialK1 = i >>> 24;
                break;
            case 2:
                this.partialK1 |= (65535 & i) << 16;
                applyK1(this.partialK1);
                this.partialK1 = i >>> 16;
                break;
            case 3:
                this.partialK1 |= (i & 255) << 24;
                applyK1(this.partialK1);
                this.partialK1 = i >>> 8;
                break;
        }
        this.length += 4;
    }

    public void updateInt(int... iArr) {
        int i = 0;
        if (this.partialK1Pos == 0) {
            int length = iArr.length;
            while (i < length) {
                applyK1(iArr[i]);
                i++;
            }
            this.length += iArr.length * 4;
            return;
        }
        int length2 = iArr.length;
        while (i < length2) {
            updateInt(iArr[i]);
            i++;
        }
    }

    public void updateLong(long j) {
        switch (this.partialK1Pos) {
            case 0:
                applyK1((int) (j & (-1)));
                applyK1((int) (j >>> 32));
                break;
            case 1:
                this.partialK1 = (int) (this.partialK1 | ((16777215 & j) << 8));
                applyK1(this.partialK1);
                applyK1((int) ((j >>> 24) & (-1)));
                this.partialK1 = (int) (j >>> 56);
                break;
            case 2:
                this.partialK1 = (int) (this.partialK1 | ((65535 & j) << 16));
                applyK1(this.partialK1);
                applyK1((int) ((j >>> 16) & (-1)));
                this.partialK1 = (int) (j >>> 48);
                break;
            case 3:
                this.partialK1 = (int) (((255 & j) << 24) | this.partialK1);
                applyK1(this.partialK1);
                applyK1((int) ((j >>> 8) & (-1)));
                this.partialK1 = (int) (j >>> 40);
                break;
        }
        this.length += 8;
    }

    public void updateLong(long... jArr) {
        int i = 0;
        if (this.partialK1Pos == 0) {
            int length = jArr.length;
            while (i < length) {
                long j = jArr[i];
                applyK1((int) ((-1) & j));
                applyK1((int) (j >>> 32));
                i++;
            }
            this.length += jArr.length * 8;
            return;
        }
        int length2 = jArr.length;
        while (i < length2) {
            updateLong(jArr[i]);
            i++;
        }
    }

    public void updateFloat(float f) {
        updateInt(Float.floatToIntBits(f));
    }

    public void updateDouble(double d) {
        updateLong(Double.doubleToLongBits(d));
    }

    public void updateBoolean(boolean z) {
        update(z ? 1 : 0);
    }

    private void applyK1(int i) {
        int i2 = i * (-862048943);
        this.f27404h1 = (((i2 >>> 17) | (i2 << 15)) * 461845907) ^ this.f27404h1;
        int i3 = this.f27404h1;
        this.f27404h1 = (i3 >>> 19) | (i3 << 13);
        this.f27404h1 = (this.f27404h1 * 5) - 430675100;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        int i = this.f27404h1;
        if (this.partialK1Pos > 0) {
            int i2 = this.partialK1 * (-862048943);
            i ^= ((i2 >>> 17) | (i2 << 15)) * 461845907;
        }
        int i3 = i ^ this.length;
        int i4 = (i3 ^ (i3 >>> 16)) * (-2048144789);
        int i5 = (i4 ^ (i4 >>> 13)) * (-1028477387);
        return (i5 ^ (i5 >>> 16)) & 4294967295L;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        this.f27404h1 = this.seed;
        this.length = 0;
        this.partialK1Pos = 0;
    }
}
