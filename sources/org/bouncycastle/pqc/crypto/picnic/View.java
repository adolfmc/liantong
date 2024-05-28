package org.bouncycastle.pqc.crypto.picnic;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class View {
    byte[] communicatedBits;
    int[] inputShare;
    int[] outputShare;

    public View(PicnicEngine picnicEngine) {
        this.inputShare = new int[picnicEngine.stateSizeBytes];
        this.communicatedBits = new byte[picnicEngine.andSizeBytes];
        this.outputShare = new int[picnicEngine.stateSizeBytes];
    }
}
