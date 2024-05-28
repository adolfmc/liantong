package org.bouncycastle.crypto.agreement;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.DHKeyParameters;
import org.bouncycastle.crypto.params.ECKeyParameters;
import org.bouncycastle.crypto.params.X25519PrivateKeyParameters;
import org.bouncycastle.crypto.params.X448PrivateKeyParameters;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class Utils {
    Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CryptoServiceProperties getDefaultProperties(String str, DHKeyParameters dHKeyParameters) {
        return new DefaultServiceProperties(str, ConstraintUtils.bitsOfSecurityFor(dHKeyParameters.getParameters().getP()), dHKeyParameters, CryptoServicePurpose.AGREEMENT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CryptoServiceProperties getDefaultProperties(String str, ECKeyParameters eCKeyParameters) {
        return new DefaultServiceProperties(str, ConstraintUtils.bitsOfSecurityFor(eCKeyParameters.getParameters().getCurve()), eCKeyParameters, CryptoServicePurpose.AGREEMENT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CryptoServiceProperties getDefaultProperties(String str, X25519PrivateKeyParameters x25519PrivateKeyParameters) {
        return new DefaultServiceProperties(str, 128, x25519PrivateKeyParameters, CryptoServicePurpose.AGREEMENT);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CryptoServiceProperties getDefaultProperties(String str, X448PrivateKeyParameters x448PrivateKeyParameters) {
        return new DefaultServiceProperties(str, 224, x448PrivateKeyParameters, CryptoServicePurpose.AGREEMENT);
    }
}
