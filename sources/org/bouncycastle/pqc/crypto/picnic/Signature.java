package org.bouncycastle.pqc.crypto.picnic;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Signature {
    byte[] challengeBits;
    Proof[] proofs;
    byte[] salt = new byte[32];

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Proof {
        byte[] communicatedBits;
        int[] inputShare;
        byte[] seed1;
        byte[] seed2;
        byte[] view3Commitment;
        byte[] view3UnruhG;

        Proof(PicnicEngine picnicEngine) {
            this.seed1 = new byte[picnicEngine.seedSizeBytes];
            this.seed2 = new byte[picnicEngine.seedSizeBytes];
            this.inputShare = new int[picnicEngine.stateSizeBytes];
            this.communicatedBits = new byte[picnicEngine.andSizeBytes];
            this.view3Commitment = new byte[picnicEngine.digestSizeBytes];
            this.view3UnruhG = picnicEngine.UnruhGWithInputBytes > 0 ? new byte[picnicEngine.UnruhGWithInputBytes] : null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Signature(PicnicEngine picnicEngine) {
        this.challengeBits = new byte[Utils.numBytes(picnicEngine.numMPCRounds * 2)];
        this.proofs = new Proof[picnicEngine.numMPCRounds];
        int i = 0;
        while (true) {
            Proof[] proofArr = this.proofs;
            if (i >= proofArr.length) {
                return;
            }
            proofArr[i] = new Proof(picnicEngine);
            i++;
        }
    }
}
