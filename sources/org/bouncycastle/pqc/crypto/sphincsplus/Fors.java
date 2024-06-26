package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Fors {
    SPHINCSPlusEngine engine;

    public Fors(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.engine = sPHINCSPlusEngine;
    }

    static int[] message_to_idxs(byte[] bArr, int i, int i2) {
        int[] iArr = new int[i];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            iArr[i3] = 0;
            int i5 = i4;
            for (int i6 = 0; i6 < i2; i6++) {
                iArr[i3] = iArr[i3] ^ (((bArr[i5 >> 3] >> (i5 & 7)) & 1) << i6);
                i5++;
            }
            i3++;
            i4 = i5;
        }
        return iArr;
    }

    public byte[] pkFromSig(SIG_FORS[] sig_forsArr, byte[] bArr, byte[] bArr2, ADRS adrs) {
        int i = 2;
        byte[][] bArr3 = new byte[2];
        byte[][] bArr4 = new byte[this.engine.f27249K];
        int i2 = this.engine.f27251T;
        int[] message_to_idxs = message_to_idxs(bArr, this.engine.f27249K, this.engine.f27246A);
        int i3 = 0;
        while (i3 < this.engine.f27249K) {
            int i4 = message_to_idxs[i3];
            byte[] sk = sig_forsArr[i3].getSK();
            adrs.setTreeHeight(0);
            int i5 = (i3 * i2) + i4;
            adrs.setTreeIndex(i5);
            bArr3[0] = this.engine.mo245F(bArr2, adrs, sk);
            byte[][] authPath = sig_forsArr[i3].getAuthPath();
            adrs.setTreeIndex(i5);
            int i6 = 0;
            while (i6 < this.engine.f27246A) {
                int i7 = i6 + 1;
                adrs.setTreeHeight(i7);
                if ((i4 / (1 << i6)) % i == 0) {
                    adrs.setTreeIndex(adrs.getTreeIndex() / i);
                    bArr3[1] = this.engine.mo244H(bArr2, adrs, bArr3[0], authPath[i6]);
                } else {
                    adrs.setTreeIndex((adrs.getTreeIndex() - 1) / 2);
                    bArr3[1] = this.engine.mo244H(bArr2, adrs, authPath[i6], bArr3[0]);
                }
                bArr3[0] = bArr3[1];
                i6 = i7;
                i = 2;
            }
            bArr4[i3] = bArr3[0];
            i3++;
            i = 2;
        }
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setType(4);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.engine.T_l(bArr2, adrs2, Arrays.concatenate(bArr4));
    }

    public SIG_FORS[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        Fors fors = this;
        ADRS adrs2 = new ADRS(adrs);
        int[] message_to_idxs = message_to_idxs(bArr, fors.engine.f27249K, fors.engine.f27246A);
        SIG_FORS[] sig_forsArr = new SIG_FORS[fors.engine.f27249K];
        int i = fors.engine.f27251T;
        int i2 = 0;
        int i3 = 0;
        while (i3 < fors.engine.f27249K) {
            int i4 = message_to_idxs[i3];
            adrs2.setType(6);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setTreeHeight(i2);
            int i5 = i3 * i;
            adrs2.setTreeIndex(i5 + i4);
            byte[] PRF = fors.engine.PRF(bArr3, bArr2, adrs2);
            adrs2.changeType(3);
            byte[][] bArr4 = new byte[fors.engine.f27246A];
            int i6 = i2;
            while (i6 < fors.engine.f27246A) {
                int i7 = 1 << i6;
                int i8 = i6;
                byte[][] bArr5 = bArr4;
                bArr5[i8] = treehash(bArr2, i5 + (((i4 / i7) ^ 1) * i7), i8, bArr3, adrs2);
                i6 = i8 + 1;
                PRF = PRF;
                bArr4 = bArr5;
                fors = this;
            }
            sig_forsArr[i3] = new SIG_FORS(PRF, bArr4);
            i3++;
            fors = this;
            i2 = 0;
        }
        return sig_forsArr;
    }

    byte[] treehash(byte[] bArr, int i, int i2, byte[] bArr2, ADRS adrs) {
        LinkedList linkedList = new LinkedList();
        int i3 = 1 << i2;
        if (i % i3 != 0) {
            return null;
        }
        ADRS adrs2 = new ADRS(adrs);
        for (int i4 = 0; i4 < i3; i4++) {
            adrs2.setType(6);
            adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
            adrs2.setTreeHeight(0);
            adrs2.setTreeIndex(i + i4);
            byte[] PRF = this.engine.PRF(bArr2, bArr, adrs2);
            adrs2.changeType(3);
            byte[] mo245F = this.engine.mo245F(bArr2, adrs2, PRF);
            adrs2.setTreeHeight(1);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).nodeHeight == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                mo245F = this.engine.mo244H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).nodeValue, mo245F);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(mo245F, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).nodeValue;
    }
}
