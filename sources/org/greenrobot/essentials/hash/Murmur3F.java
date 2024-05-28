package org.greenrobot.essentials.hash;

import java.math.BigInteger;
import org.greenrobot.essentials.PrimitiveArrayUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Murmur3F implements Checksum128 {

    /* renamed from: C1 */
    private static final long f27405C1 = -8663945395140668459L;

    /* renamed from: C2 */
    private static final long f27406C2 = 5545529020109919103L;
    private static PrimitiveArrayUtils primitiveArrayUtils = PrimitiveArrayUtils.getInstance();
    private boolean finished;
    private long finishedH1;
    private long finishedH2;

    /* renamed from: h1 */
    private long f27407h1;

    /* renamed from: h2 */
    private long f27408h2;
    private int length;
    private long partialK1;
    private long partialK2;
    private int partialPos;
    private final long seed;

    private long fmix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }

    public Murmur3F() {
        this.seed = 0L;
    }

    public Murmur3F(int i) {
        this.seed = i & 4294967295L;
        long j = this.seed;
        this.f27408h2 = j;
        this.f27407h1 = j;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        this.finished = false;
        switch (this.partialPos) {
            case 0:
                this.partialK1 = i & 255;
                break;
            case 1:
                this.partialK1 |= (i & 255) << 8;
                break;
            case 2:
                this.partialK1 |= (i & 255) << 16;
                break;
            case 3:
                this.partialK1 |= (i & 255) << 24;
                break;
            case 4:
                this.partialK1 |= (i & 255) << 32;
                break;
            case 5:
                this.partialK1 |= (i & 255) << 40;
                break;
            case 6:
                this.partialK1 |= (i & 255) << 48;
                break;
            case 7:
                this.partialK1 = ((i & 255) << 56) | this.partialK1;
                break;
            case 8:
                this.partialK2 = i & 255;
                break;
            case 9:
                this.partialK2 |= (i & 255) << 8;
                break;
            case 10:
                this.partialK2 |= (i & 255) << 16;
                break;
            case 11:
                this.partialK2 |= (i & 255) << 24;
                break;
            case 12:
                this.partialK2 |= (i & 255) << 32;
                break;
            case 13:
                this.partialK2 |= (i & 255) << 40;
                break;
            case 14:
                this.partialK2 |= (i & 255) << 48;
                break;
            case 15:
                this.partialK2 = ((i & 255) << 56) | this.partialK2;
                break;
        }
        this.partialPos++;
        if (this.partialPos == 16) {
            applyKs(this.partialK1, this.partialK2);
            this.partialPos = 0;
        }
        this.length++;
    }

    public void updateLongLE(long j) {
        this.finished = false;
        int i = this.partialPos;
        if (i == 0) {
            this.partialK1 = j;
        } else if (i == 8) {
            this.partialK2 = j;
        } else {
            throw new IllegalStateException("Cannot mix long with other alignments than 8: " + this.partialPos);
        }
        this.partialPos += 8;
        if (this.partialPos == 16) {
            applyKs(this.partialK1, this.partialK2);
            this.partialPos = 0;
        }
        this.length += 8;
    }

    public void updateLongBE(long j) {
        updateLongLE(Long.reverseBytes(j));
    }

    public void update(byte[] bArr) {
        update(bArr, 0, bArr.length);
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        this.finished = false;
        while (this.partialPos != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        int i3 = i2 & 15;
        int i4 = (i2 + i) - i3;
        for (int i5 = i; i5 < i4; i5 += 16) {
            applyKs(primitiveArrayUtils.getLongLE(bArr, i5), primitiveArrayUtils.getLongLE(bArr, i5 + 8));
        }
        this.length += i4 - i;
        for (int i6 = 0; i6 < i3; i6++) {
            update(bArr[i4 + i6]);
        }
    }

    private void applyKs(long j, long j2) {
        this.f27407h1 = (Long.rotateLeft(j * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.f27407h1;
        this.f27407h1 = Long.rotateLeft(this.f27407h1, 27);
        this.f27407h1 += this.f27408h2;
        this.f27407h1 = (this.f27407h1 * 5) + 1390208809;
        this.f27408h2 = (Long.rotateLeft(j2 * 5545529020109919103L, 33) * (-8663945395140668459L)) ^ this.f27408h2;
        this.f27408h2 = Long.rotateLeft(this.f27408h2, 31);
        this.f27408h2 += this.f27407h1;
        this.f27408h2 = (this.f27408h2 * 5) + 944331445;
    }

    private void checkFinished() {
        if (this.finished) {
            return;
        }
        this.finished = true;
        this.finishedH1 = this.f27407h1;
        this.finishedH2 = this.f27408h2;
        int i = this.partialPos;
        if (i > 0) {
            if (i > 8) {
                this.finishedH2 = (Long.rotateLeft(this.partialK2 * 5545529020109919103L, 33) * (-8663945395140668459L)) ^ this.finishedH2;
            }
            this.finishedH1 = (Long.rotateLeft(this.partialK1 * (-8663945395140668459L), 31) * 5545529020109919103L) ^ this.finishedH1;
        }
        long j = this.finishedH1;
        int i2 = this.length;
        this.finishedH1 = j ^ i2;
        this.finishedH2 ^= i2;
        long j2 = this.finishedH1;
        long j3 = this.finishedH2;
        this.finishedH1 = j2 + j3;
        long j4 = this.finishedH1;
        this.finishedH2 = j3 + j4;
        this.finishedH1 = fmix64(j4);
        this.finishedH2 = fmix64(this.finishedH2);
        long j5 = this.finishedH1;
        long j6 = this.finishedH2;
        this.finishedH1 = j5 + j6;
        this.finishedH2 = j6 + this.finishedH1;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        checkFinished();
        return this.finishedH1;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public long getValueHigh() {
        checkFinished();
        return this.finishedH2;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public BigInteger getValueBigInteger() {
        return new BigInteger(1, getValueBytesBigEndian());
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public String getValueHexString() {
        checkFinished();
        return getPaddedHexString(this.finishedH2) + getPaddedHexString(this.finishedH1);
    }

    private String getPaddedHexString(long j) {
        String hexString = Long.toHexString(j);
        while (hexString.length() < 16) {
            hexString = '0' + hexString;
        }
        return hexString;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public byte[] getValueBytesBigEndian() {
        checkFinished();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (255 & (this.finishedH2 >>> (56 - (i * 8))));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + 8] = (byte) ((this.finishedH1 >>> (56 - (i2 * 8))) & 255);
        }
        return bArr;
    }

    @Override // org.greenrobot.essentials.hash.Checksum128
    public byte[] getValueBytesLittleEndian() {
        checkFinished();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 8; i++) {
            bArr[i] = (byte) (255 & (this.finishedH1 >>> (i * 8)));
        }
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + 8] = (byte) ((this.finishedH2 >>> (i2 * 8)) & 255);
        }
        return bArr;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        long j = this.seed;
        this.f27408h2 = j;
        this.f27407h1 = j;
        this.length = 0;
        this.partialPos = 0;
        this.finished = false;
        this.partialK2 = 0L;
        this.partialK1 = 0L;
        this.finishedH2 = 0L;
        this.finishedH1 = 0L;
    }
}
