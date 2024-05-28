package org.bouncycastle.jcajce.interfaces;

import java.security.PrivateKey;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface XDHPrivateKey extends PrivateKey, XDHKey {
    XDHPublicKey getPublicKey();
}
