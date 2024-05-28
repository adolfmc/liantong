package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.PrivateKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface NHPrivateKey extends PrivateKey, NHKey {
    short[] getSecretData();
}
