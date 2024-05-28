package org.bouncycastle.pqc.crypto.picnic;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Signature2 {
    int[] challengeC;
    byte[] challengeHash;
    int[] challengeP;
    byte[] cvInfo;
    int cvInfoLen;
    byte[] iSeedInfo;
    int iSeedInfoLen;
    Proof2[] proofs;
    byte[] salt = new byte[32];

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Proof2 {

        /* renamed from: C */
        byte[] f27232C;
        byte[] aux;
        byte[] input;
        byte[] msgs;
        byte[] seedInfo = null;
        int seedInfoLen = 0;

        public Proof2(PicnicEngine picnicEngine) {
            this.f27232C = new byte[picnicEngine.digestSizeBytes];
            this.input = new byte[picnicEngine.stateSizeBytes];
            this.aux = new byte[picnicEngine.andSizeBytes];
            this.msgs = new byte[picnicEngine.andSizeBytes];
        }
    }

    public Signature2(PicnicEngine picnicEngine) {
        this.challengeHash = new byte[picnicEngine.digestSizeBytes];
        this.challengeC = new int[picnicEngine.numOpenedRounds];
        this.challengeP = new int[picnicEngine.numOpenedRounds];
        this.proofs = new Proof2[picnicEngine.numMPCRounds];
    }
}
