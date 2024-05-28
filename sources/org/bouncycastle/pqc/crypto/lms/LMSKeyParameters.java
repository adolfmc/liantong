package org.bouncycastle.pqc.crypto.lms;

import java.io.IOException;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Encodable;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class LMSKeyParameters extends AsymmetricKeyParameter implements Encodable {
    /* JADX INFO: Access modifiers changed from: protected */
    public LMSKeyParameters(boolean z) {
        super(z);
    }

    public abstract byte[] getEncoded() throws IOException;
}
