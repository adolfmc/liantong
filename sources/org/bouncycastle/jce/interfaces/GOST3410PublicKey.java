package org.bouncycastle.jce.interfaces;

import java.math.BigInteger;
import java.security.PublicKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface GOST3410PublicKey extends PublicKey, GOST3410Key {
    BigInteger getY();
}
