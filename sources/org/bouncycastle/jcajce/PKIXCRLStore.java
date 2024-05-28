package org.bouncycastle.jcajce;

import java.security.cert.CRL;
import java.util.Collection;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface PKIXCRLStore<T extends CRL> extends Store<T> {
    @Override // org.bouncycastle.util.Store
    Collection<T> getMatches(Selector<T> selector) throws StoreException;
}
