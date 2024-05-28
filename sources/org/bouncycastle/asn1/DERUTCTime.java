package org.bouncycastle.asn1;

import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DERUTCTime extends ASN1UTCTime {
    public DERUTCTime(String str) {
        super(str);
    }

    public DERUTCTime(Date date) {
        super(date);
    }

    DERUTCTime(byte[] bArr) {
        super(bArr);
    }
}
