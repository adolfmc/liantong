package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Tape {
    private PicnicEngine engine;
    int nTapes;
    int pos = 0;
    byte[][] tapes;

    public Tape(PicnicEngine picnicEngine) {
        this.engine = picnicEngine;
        this.tapes = (byte[][]) Array.newInstance(byte.class, picnicEngine.numMPCParties, picnicEngine.andSizeBytes * 2);
        this.nTapes = picnicEngine.numMPCParties;
    }

    private void tapesToParityBits(int[] iArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            Utils.setBitInWordArray(iArr, i2, Utils.parity16(tapesToWord()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void computeAuxTape(byte[] bArr) {
        int[] iArr = new int[16];
        int[] iArr2 = new int[16];
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[16];
        int[] iArr5 = new int[16];
        iArr5[this.engine.stateSizeWords - 1] = 0;
        tapesToParityBits(iArr5, this.engine.stateSizeBits);
        KMatricesWithPointer KMatrixInv = LowmcConstants.KMatrixInv(this.engine);
        this.engine.matrix_mul(iArr4, iArr5, KMatrixInv.getData(), KMatrixInv.getMatrixPointer());
        if (bArr != null) {
            Pack.intToLittleEndian(Arrays.copyOf(iArr4, this.engine.stateSizeWords), bArr, 0);
        }
        for (int i = this.engine.numRounds; i > 0; i--) {
            KMatricesWithPointer KMatrix = LowmcConstants.KMatrix(this.engine, i);
            this.engine.matrix_mul(iArr, iArr4, KMatrix.getData(), KMatrix.getMatrixPointer());
            PicnicEngine picnicEngine = this.engine;
            picnicEngine.xor_array(iArr2, iArr2, iArr, 0, picnicEngine.stateSizeWords);
            int i2 = i - 1;
            KMatricesWithPointer LMatrixInv = LowmcConstants.LMatrixInv(this.engine, i2);
            this.engine.matrix_mul(iArr3, iArr2, LMatrixInv.getData(), LMatrixInv.getMatrixPointer());
            if (i == 1) {
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
            } else {
                this.pos = this.engine.stateSizeBits * 2 * i2;
                tapesToParityBits(iArr2, this.engine.stateSizeBits);
            }
            this.pos = (this.engine.stateSizeBits * 2 * i2) + this.engine.stateSizeBits;
            this.engine.aux_mpc_sbox(iArr2, iArr3, this);
        }
        this.pos = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAuxBits(byte[] bArr) {
        int i = this.engine.numMPCParties - 1;
        int i2 = this.engine.stateSizeBits;
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.engine.numRounds) {
            int i5 = i4;
            int i6 = 0;
            while (i6 < i2) {
                Utils.setBit(this.tapes[i], (i2 * 2 * i3) + i2 + i6, Utils.getBit(bArr, i5));
                i6++;
                i5++;
            }
            i3++;
            i4 = i5;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int tapesToWord() {
        byte[] bArr = new byte[4];
        for (int i = 0; i < 16; i++) {
            Utils.setBit(bArr, i, Utils.getBit(this.tapes[i], this.pos));
        }
        this.pos++;
        return Pack.littleEndianToInt(bArr, 0);
    }
}
