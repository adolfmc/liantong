package org.bouncycastle.jce.interfaces;

import javax.crypto.interfaces.DHKey;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ElGamalKey extends DHKey {
    ElGamalParameterSpec getParameters();
}
