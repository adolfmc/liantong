package org.bouncycastle.pqc.crypto.crystals.dilithium;

import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Packing {
    Packing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] packPublicKey(PolyVecK polyVecK, DilithiumEngine dilithiumEngine) {
        byte[] bArr = new byte[dilithiumEngine.getCryptoPublicKeyBytes() - 32];
        for (int i = 0; i < dilithiumEngine.getDilithiumK(); i++) {
            System.arraycopy(polyVecK.getVectorIndex(i).polyt1Pack(), 0, bArr, i * 320, 320);
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[][] packSecretKey(byte[] bArr, byte[] bArr2, byte[] bArr3, PolyVecK polyVecK, PolyVecL polyVecL, PolyVecK polyVecK2, DilithiumEngine dilithiumEngine) {
        byte[][] bArr4 = new byte[6];
        bArr4[0] = bArr;
        bArr4[1] = bArr3;
        bArr4[2] = bArr2;
        bArr4[3] = new byte[dilithiumEngine.getDilithiumL() * dilithiumEngine.getDilithiumPolyEtaPackedBytes()];
        for (int i = 0; i < dilithiumEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).polyEtaPack(bArr4[3], dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i);
        }
        bArr4[4] = new byte[dilithiumEngine.getDilithiumK() * dilithiumEngine.getDilithiumPolyEtaPackedBytes()];
        for (int i2 = 0; i2 < dilithiumEngine.getDilithiumK(); i2++) {
            polyVecK2.getVectorIndex(i2).polyEtaPack(bArr4[4], dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i2);
        }
        bArr4[5] = new byte[dilithiumEngine.getDilithiumK() * 416];
        for (int i3 = 0; i3 < dilithiumEngine.getDilithiumK(); i3++) {
            polyVecK.getVectorIndex(i3).polyt0Pack(bArr4[5], i3 * 416);
        }
        return bArr4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] packSignature(byte[] bArr, PolyVecL polyVecL, PolyVecK polyVecK, DilithiumEngine dilithiumEngine) {
        byte[] bArr2 = new byte[dilithiumEngine.getCryptoBytes()];
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        for (int i = 0; i < dilithiumEngine.getDilithiumL(); i++) {
            System.arraycopy(polyVecL.getVectorIndex(i).zPack(), 0, bArr2, (dilithiumEngine.getDilithiumPolyZPackedBytes() * i) + 32, dilithiumEngine.getDilithiumPolyZPackedBytes());
        }
        int dilithiumL = 32 + (dilithiumEngine.getDilithiumL() * dilithiumEngine.getDilithiumPolyZPackedBytes());
        for (int i2 = 0; i2 < dilithiumEngine.getDilithiumOmega() + dilithiumEngine.getDilithiumK(); i2++) {
            bArr2[dilithiumL + i2] = 0;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < dilithiumEngine.getDilithiumK()) {
            int i5 = i4;
            for (int i6 = 0; i6 < 256; i6++) {
                if (polyVecK.getVectorIndex(i3).getCoeffIndex(i6) != 0) {
                    bArr2[i5 + dilithiumL] = (byte) i6;
                    i5++;
                }
            }
            bArr2[dilithiumEngine.getDilithiumOmega() + dilithiumL + i3] = (byte) i5;
            i3++;
            i4 = i5;
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static PolyVecK unpackPublicKey(PolyVecK polyVecK, byte[] bArr, DilithiumEngine dilithiumEngine) {
        int i = 0;
        while (i < dilithiumEngine.getDilithiumK()) {
            Poly vectorIndex = polyVecK.getVectorIndex(i);
            int i2 = i * 320;
            i++;
            vectorIndex.polyt1Unpack(Arrays.copyOfRange(bArr, i2, (i * 320) + 32));
        }
        return polyVecK;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void unpackSecretKey(PolyVecK polyVecK, PolyVecL polyVecL, PolyVecK polyVecK2, byte[] bArr, byte[] bArr2, byte[] bArr3, DilithiumEngine dilithiumEngine) {
        for (int i = 0; i < dilithiumEngine.getDilithiumL(); i++) {
            polyVecL.getVectorIndex(i).polyEtaUnpack(bArr2, dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i);
        }
        for (int i2 = 0; i2 < dilithiumEngine.getDilithiumK(); i2++) {
            polyVecK2.getVectorIndex(i2).polyEtaUnpack(bArr3, dilithiumEngine.getDilithiumPolyEtaPackedBytes() * i2);
        }
        for (int i3 = 0; i3 < dilithiumEngine.getDilithiumK(); i3++) {
            polyVecK.getVectorIndex(i3).polyt0Unpack(bArr, i3 * 416);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean unpackSignature(PolyVecL polyVecL, PolyVecK polyVecK, byte[] bArr, DilithiumEngine dilithiumEngine) {
        int i = 0;
        while (i < dilithiumEngine.getDilithiumL()) {
            Poly vectorIndex = polyVecL.getVectorIndex(i);
            i++;
            vectorIndex.zUnpack(Arrays.copyOfRange(bArr, (dilithiumEngine.getDilithiumPolyZPackedBytes() * i) + 32, 32 + (dilithiumEngine.getDilithiumPolyZPackedBytes() * i)));
        }
        int dilithiumL = 32 + (dilithiumEngine.getDilithiumL() * dilithiumEngine.getDilithiumPolyZPackedBytes());
        int i2 = 0;
        for (int i3 = 0; i3 < dilithiumEngine.getDilithiumK(); i3++) {
            for (int i4 = 0; i4 < 256; i4++) {
                polyVecK.getVectorIndex(i3).setCoeffIndex(i4, 0);
            }
            if ((bArr[dilithiumEngine.getDilithiumOmega() + dilithiumL + i3] & 255) < i2 || (bArr[dilithiumEngine.getDilithiumOmega() + dilithiumL + i3] & 255) > dilithiumEngine.getDilithiumOmega()) {
                return false;
            }
            for (int i5 = i2; i5 < (bArr[dilithiumEngine.getDilithiumOmega() + dilithiumL + i3] & 255); i5++) {
                if (i5 > i2) {
                    int i6 = dilithiumL + i5;
                    if ((bArr[i6] & 255) <= (bArr[i6 - 1] & 255)) {
                        return false;
                    }
                }
                polyVecK.getVectorIndex(i3).setCoeffIndex(bArr[dilithiumL + i5] & 255, 1);
            }
            i2 = bArr[dilithiumEngine.getDilithiumOmega() + dilithiumL + i3];
        }
        while (i2 < dilithiumEngine.getDilithiumOmega()) {
            if ((bArr[dilithiumL + i2] & 255) != 0) {
                return false;
            }
            i2++;
        }
        return true;
    }
}
