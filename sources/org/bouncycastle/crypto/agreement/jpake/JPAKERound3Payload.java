package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class JPAKERound3Payload {
    private final BigInteger macTag;
    private final String participantId;

    public JPAKERound3Payload(String str, BigInteger bigInteger) {
        this.participantId = str;
        this.macTag = bigInteger;
    }

    public BigInteger getMacTag() {
        return this.macTag;
    }

    public String getParticipantId() {
        return this.participantId;
    }
}
