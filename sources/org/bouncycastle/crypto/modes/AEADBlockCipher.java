package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface AEADBlockCipher extends AEADCipher {
    BlockCipher getUnderlyingCipher();
}
