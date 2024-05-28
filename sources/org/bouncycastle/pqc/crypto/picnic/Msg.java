package org.bouncycastle.pqc.crypto.picnic;

import java.lang.reflect.Array;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Msg {
    byte[][] msgs;
    int pos = 0;
    int unopened = -1;

    public Msg(PicnicEngine picnicEngine) {
        this.msgs = (byte[][]) Array.newInstance(byte.class, picnicEngine.numMPCParties, picnicEngine.andSizeBytes);
    }
}
