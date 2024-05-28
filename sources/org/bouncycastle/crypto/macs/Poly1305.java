package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    /* renamed from: h0 */
    private int f26689h0;

    /* renamed from: h1 */
    private int f26690h1;

    /* renamed from: h2 */
    private int f26691h2;

    /* renamed from: h3 */
    private int f26692h3;

    /* renamed from: h4 */
    private int f26693h4;

    /* renamed from: k0 */
    private int f26694k0;

    /* renamed from: k1 */
    private int f26695k1;

    /* renamed from: k2 */
    private int f26696k2;

    /* renamed from: k3 */
    private int f26697k3;

    /* renamed from: r0 */
    private int f26698r0;

    /* renamed from: r1 */
    private int f26699r1;

    /* renamed from: r2 */
    private int f26700r2;

    /* renamed from: r3 */
    private int f26701r3;

    /* renamed from: r4 */
    private int f26702r4;

    /* renamed from: s1 */
    private int f26703s1;

    /* renamed from: s2 */
    private int f26704s2;

    /* renamed from: s3 */
    private int f26705s3;

    /* renamed from: s4 */
    private int f26706s4;
    private final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher blockCipher) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.cipher = blockCipher;
    }

    private static final long mul32x32_64(int i, int i2) {
        return (i & 4294967295L) * i2;
    }

    private void processBlock() {
        int i = this.currentBlockOffset;
        if (i < 16) {
            this.currentBlock[i] = 1;
            for (int i2 = i + 1; i2 < 16; i2++) {
                this.currentBlock[i2] = 0;
            }
        }
        long littleEndianToInt = Pack.littleEndianToInt(this.currentBlock, 0) & 4294967295L;
        long littleEndianToInt2 = Pack.littleEndianToInt(this.currentBlock, 4) & 4294967295L;
        long littleEndianToInt3 = Pack.littleEndianToInt(this.currentBlock, 8) & 4294967295L;
        long littleEndianToInt4 = 4294967295L & Pack.littleEndianToInt(this.currentBlock, 12);
        this.f26689h0 = (int) (this.f26689h0 + (littleEndianToInt & 67108863));
        this.f26690h1 = (int) (this.f26690h1 + ((((littleEndianToInt2 << 32) | littleEndianToInt) >>> 26) & 67108863));
        this.f26691h2 = (int) (this.f26691h2 + (((littleEndianToInt2 | (littleEndianToInt3 << 32)) >>> 20) & 67108863));
        this.f26692h3 = (int) (this.f26692h3 + ((((littleEndianToInt4 << 32) | littleEndianToInt3) >>> 14) & 67108863));
        this.f26693h4 = (int) (this.f26693h4 + (littleEndianToInt4 >>> 8));
        if (this.currentBlockOffset == 16) {
            this.f26693h4 += 16777216;
        }
        long mul32x32_64 = mul32x32_64(this.f26689h0, this.f26698r0) + mul32x32_64(this.f26690h1, this.f26706s4) + mul32x32_64(this.f26691h2, this.f26705s3) + mul32x32_64(this.f26692h3, this.f26704s2) + mul32x32_64(this.f26693h4, this.f26703s1);
        long mul32x32_642 = mul32x32_64(this.f26689h0, this.f26699r1) + mul32x32_64(this.f26690h1, this.f26698r0) + mul32x32_64(this.f26691h2, this.f26706s4) + mul32x32_64(this.f26692h3, this.f26705s3) + mul32x32_64(this.f26693h4, this.f26704s2);
        long mul32x32_643 = mul32x32_64(this.f26689h0, this.f26700r2) + mul32x32_64(this.f26690h1, this.f26699r1) + mul32x32_64(this.f26691h2, this.f26698r0) + mul32x32_64(this.f26692h3, this.f26706s4) + mul32x32_64(this.f26693h4, this.f26705s3);
        long mul32x32_644 = mul32x32_64(this.f26689h0, this.f26701r3) + mul32x32_64(this.f26690h1, this.f26700r2) + mul32x32_64(this.f26691h2, this.f26699r1) + mul32x32_64(this.f26692h3, this.f26698r0) + mul32x32_64(this.f26693h4, this.f26706s4);
        long mul32x32_645 = mul32x32_64(this.f26689h0, this.f26702r4) + mul32x32_64(this.f26690h1, this.f26701r3) + mul32x32_64(this.f26691h2, this.f26700r2) + mul32x32_64(this.f26692h3, this.f26699r1) + mul32x32_64(this.f26693h4, this.f26698r0);
        this.f26689h0 = ((int) mul32x32_64) & 67108863;
        long j = mul32x32_642 + (mul32x32_64 >>> 26);
        this.f26690h1 = ((int) j) & 67108863;
        long j2 = mul32x32_643 + (j >>> 26);
        this.f26691h2 = ((int) j2) & 67108863;
        long j3 = mul32x32_644 + (j2 >>> 26);
        this.f26692h3 = ((int) j3) & 67108863;
        long j4 = mul32x32_645 + (j3 >>> 26);
        this.f26693h4 = ((int) j4) & 67108863;
        this.f26689h0 += ((int) (j4 >>> 26)) * 5;
        int i3 = this.f26690h1;
        int i4 = this.f26689h0;
        this.f26690h1 = i3 + (i4 >>> 26);
        this.f26689h0 = i4 & 67108863;
    }

    private void setKey(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("Poly1305 key must be 256 bits.");
        }
        if (this.cipher != null && (bArr2 == null || bArr2.length != 16)) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
        }
        int i = 0;
        int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
        this.f26698r0 = 67108863 & littleEndianToInt;
        this.f26699r1 = ((littleEndianToInt >>> 26) | (littleEndianToInt2 << 6)) & 67108611;
        this.f26700r2 = ((littleEndianToInt2 >>> 20) | (littleEndianToInt3 << 12)) & 67092735;
        this.f26701r3 = ((littleEndianToInt3 >>> 14) | (littleEndianToInt4 << 18)) & 66076671;
        this.f26702r4 = (littleEndianToInt4 >>> 8) & 1048575;
        this.f26703s1 = this.f26699r1 * 5;
        this.f26704s2 = this.f26700r2 * 5;
        this.f26705s3 = this.f26701r3 * 5;
        this.f26706s4 = this.f26702r4 * 5;
        BlockCipher blockCipher = this.cipher;
        if (blockCipher == null) {
            i = 16;
        } else {
            byte[] bArr3 = new byte[16];
            blockCipher.init(true, new KeyParameter(bArr, 16, 16));
            this.cipher.processBlock(bArr2, 0, bArr3, 0);
            bArr = bArr3;
        }
        this.f26694k0 = Pack.littleEndianToInt(bArr, i + 0);
        this.f26695k1 = Pack.littleEndianToInt(bArr, i + 4);
        this.f26696k2 = Pack.littleEndianToInt(bArr, i + 8);
        this.f26697k3 = Pack.littleEndianToInt(bArr, i + 12);
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        int i2;
        int i3;
        if (i + 16 <= bArr.length) {
            if (this.currentBlockOffset > 0) {
                processBlock();
            }
            int i4 = this.f26690h1;
            int i5 = this.f26689h0;
            this.f26690h1 = i4 + (i5 >>> 26);
            this.f26689h0 = i5 & 67108863;
            int i6 = this.f26691h2;
            int i7 = this.f26690h1;
            this.f26691h2 = i6 + (i7 >>> 26);
            this.f26690h1 = i7 & 67108863;
            int i8 = this.f26692h3;
            int i9 = this.f26691h2;
            this.f26692h3 = i8 + (i9 >>> 26);
            this.f26691h2 = i9 & 67108863;
            int i10 = this.f26693h4;
            int i11 = this.f26692h3;
            this.f26693h4 = i10 + (i11 >>> 26);
            this.f26692h3 = i11 & 67108863;
            int i12 = this.f26689h0;
            int i13 = this.f26693h4;
            this.f26689h0 = i12 + ((i13 >>> 26) * 5);
            this.f26693h4 = i13 & 67108863;
            int i14 = this.f26690h1;
            int i15 = this.f26689h0;
            this.f26690h1 = i14 + (i15 >>> 26);
            this.f26689h0 = i15 & 67108863;
            int i16 = this.f26689h0;
            int i17 = i16 + 5;
            int i18 = this.f26690h1;
            int i19 = (i17 >>> 26) + i18;
            int i20 = this.f26691h2;
            int i21 = (i19 >>> 26) + i20;
            int i22 = this.f26692h3;
            int i23 = (i21 >>> 26) + i22;
            int i24 = i23 >>> 26;
            int i25 = 67108863 & i23;
            int i26 = this.f26693h4;
            int i27 = (i24 + i26) - 67108864;
            int i28 = (i27 >>> 31) - 1;
            int i29 = ~i28;
            this.f26689h0 = (i16 & i29) | (i17 & 67108863 & i28);
            this.f26690h1 = (i18 & i29) | (i19 & 67108863 & i28);
            this.f26691h2 = (i20 & i29) | (i21 & 67108863 & i28);
            this.f26692h3 = (i25 & i28) | (i22 & i29);
            this.f26693h4 = (i26 & i29) | (i27 & i28);
            int i30 = this.f26689h0;
            long j = ((i30 | (i2 << 26)) & 4294967295L) + (this.f26694k0 & 4294967295L);
            int i31 = this.f26690h1 >>> 6;
            long j2 = ((i31 | (i3 << 20)) & 4294967295L) + (this.f26695k1 & 4294967295L);
            int i32 = this.f26691h2 >>> 12;
            int i33 = this.f26692h3;
            long j3 = ((i32 | (i33 << 14)) & 4294967295L) + (this.f26696k2 & 4294967295L);
            Pack.intToLittleEndian((int) j, bArr, i);
            long j4 = j2 + (j >>> 32);
            Pack.intToLittleEndian((int) j4, bArr, i + 4);
            long j5 = j3 + (j4 >>> 32);
            Pack.intToLittleEndian((int) j5, bArr, i + 8);
            Pack.intToLittleEndian((int) ((((i33 >>> 18) | (this.f26693h4 << 8)) & 4294967295L) + (4294967295L & this.f26697k3) + (j5 >>> 32)), bArr, i + 12);
            reset();
            return 16;
        }
        throw new OutputLengthException("Output buffer is too short.");
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        if (this.cipher == null) {
            return "Poly1305";
        }
        return "Poly1305-" + this.cipher.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        byte[] bArr;
        if (this.cipher == null) {
            bArr = null;
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        }
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) cipherParameters).getKey(), bArr);
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.currentBlockOffset = 0;
        this.f26693h4 = 0;
        this.f26692h3 = 0;
        this.f26691h2 = 0;
        this.f26690h1 = 0;
        this.f26689h0 = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.singleByte;
        bArr[0] = b;
        update(bArr, 0, 1);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = 0;
        while (i2 > i3) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int min = Math.min(i2 - i3, 16 - this.currentBlockOffset);
            System.arraycopy(bArr, i3 + i, this.currentBlock, this.currentBlockOffset, min);
            i3 += min;
            this.currentBlockOffset += min;
        }
    }
}
