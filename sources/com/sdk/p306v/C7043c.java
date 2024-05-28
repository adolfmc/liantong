package com.sdk.p306v;

import java.math.BigInteger;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.math.p464ec.ECPoint;

/* renamed from: com.sdk.v.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7043c {
    static {
        "1234567812345678".getBytes();
        System.setProperty("org.bouncycastle.asn1.allow_unsafe_integer", "true");
    }

    /* renamed from: a */
    public final ECPublicKeyParameters m8118a(byte[] bArr) {
        byte[] bArr2 = new byte[32];
        byte[] bArr3 = new byte[32];
        System.arraycopy(bArr, 1, bArr2, 0, 32);
        System.arraycopy(bArr, 33, bArr3, 0, 32);
        ECPoint createPoint = ECNamedCurveTable.getParameterSpec("sm2p256v1").getCurve().createPoint(new BigInteger(1, bArr2), new BigInteger(1, bArr3));
        ECNamedCurveParameterSpec parameterSpec = ECNamedCurveTable.getParameterSpec("sm2p256v1");
        return new ECPublicKeyParameters(createPoint, new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(), parameterSpec.getN(), parameterSpec.getH(), parameterSpec.getSeed()));
    }
}
