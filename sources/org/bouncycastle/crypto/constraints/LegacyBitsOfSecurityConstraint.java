package org.bouncycastle.crypto.constraints;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bouncycastle.crypto.CryptoServiceConstraintsException;
import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class LegacyBitsOfSecurityConstraint extends ServicesConstraint {
    private final int legacyRequiredBitsOfSecurity;
    private final int requiredBitsOfSecurity;

    public LegacyBitsOfSecurityConstraint(int i) {
        this(i, 0);
    }

    public LegacyBitsOfSecurityConstraint(int i, int i2) {
        super(Collections.EMPTY_SET);
        this.requiredBitsOfSecurity = i;
        this.legacyRequiredBitsOfSecurity = i2;
    }

    public LegacyBitsOfSecurityConstraint(int i, int i2, Set<String> set) {
        super(set);
        this.requiredBitsOfSecurity = i;
        this.legacyRequiredBitsOfSecurity = i2;
    }

    public LegacyBitsOfSecurityConstraint(int i, Set<String> set) {
        this(i, 0, set);
    }

    @Override // org.bouncycastle.crypto.CryptoServicesConstraints
    public void check(CryptoServiceProperties cryptoServiceProperties) {
        if (isException(cryptoServiceProperties.getServiceName())) {
            return;
        }
        CryptoServicePurpose purpose = cryptoServiceProperties.getPurpose();
        switch (purpose) {
            case ANY:
            case VERIFYING:
            case DECRYPTION:
            case VERIFICATION:
                if (cryptoServiceProperties.bitsOfSecurity() < this.legacyRequiredBitsOfSecurity) {
                    throw new CryptoServiceConstraintsException("service does not provide " + this.legacyRequiredBitsOfSecurity + " bits of security only " + cryptoServiceProperties.bitsOfSecurity());
                } else if (purpose == CryptoServicePurpose.ANY || !LOG.isLoggable(Level.FINE)) {
                    return;
                } else {
                    Logger logger = LOG;
                    logger.fine("usage of legacy cryptography service for algorithm " + cryptoServiceProperties.getServiceName());
                    return;
                }
            default:
                if (cryptoServiceProperties.bitsOfSecurity() >= this.requiredBitsOfSecurity) {
                    return;
                }
                throw new CryptoServiceConstraintsException("service does not provide " + this.requiredBitsOfSecurity + " bits of security only " + cryptoServiceProperties.bitsOfSecurity());
        }
    }
}
