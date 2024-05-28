package org.bouncycastle.pqc.crypto.crystals.dilithium;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class PolyVecMatrix {
    private final int dilithiumK;
    private final int dilithiumL;
    private final PolyVecL[] mat;

    public PolyVecMatrix(DilithiumEngine dilithiumEngine) {
        this.dilithiumK = dilithiumEngine.getDilithiumK();
        this.dilithiumL = dilithiumEngine.getDilithiumL();
        this.mat = new PolyVecL[this.dilithiumK];
        for (int i = 0; i < this.dilithiumK; i++) {
            this.mat[i] = new PolyVecL(dilithiumEngine);
        }
    }

    private String addString() {
        StringBuilder sb;
        String str;
        String str2 = "[";
        for (int i = 0; i < this.dilithiumK; i++) {
            String str3 = (str2 + "Outer Matrix " + i + " [") + this.mat[i].toString();
            if (i == this.dilithiumK - 1) {
                sb = new StringBuilder();
                sb.append(str3);
                str = "]\n";
            } else {
                sb = new StringBuilder();
                sb.append(str3);
                str = "],\n";
            }
            sb.append(str);
            str2 = sb.toString();
        }
        return str2 + "]\n";
    }

    public void expandMatrix(byte[] bArr) {
        for (int i = 0; i < this.dilithiumK; i++) {
            for (int i2 = 0; i2 < this.dilithiumL; i2++) {
                this.mat[i].getVectorIndex(i2).uniformBlocks(bArr, (short) ((i << 8) + i2));
            }
        }
    }

    public void pointwiseMontgomery(PolyVecK polyVecK, PolyVecL polyVecL) {
        for (int i = 0; i < this.dilithiumK; i++) {
            polyVecK.getVectorIndex(i).pointwiseAccountMontgomery(this.mat[i], polyVecL);
        }
    }

    public String toString(String str) {
        return str.concat(": \n" + addString());
    }
}
