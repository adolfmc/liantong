package org.bouncycastle.crypto.params;

import java.math.BigInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {

    /* renamed from: c */
    private BigInteger f26783c;

    /* renamed from: d */
    private BigInteger f26784d;

    /* renamed from: h */
    private BigInteger f26785h;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.f26783c = bigInteger;
        this.f26784d = bigInteger2;
        this.f26785h = bigInteger3;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPublicKeyParameters) {
            CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
            return cramerShoupPublicKeyParameters.getC().equals(this.f26783c) && cramerShoupPublicKeyParameters.getD().equals(this.f26784d) && cramerShoupPublicKeyParameters.getH().equals(this.f26785h) && super.equals(obj);
        }
        return false;
    }

    public BigInteger getC() {
        return this.f26783c;
    }

    public BigInteger getD() {
        return this.f26784d;
    }

    public BigInteger getH() {
        return this.f26785h;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.f26783c.hashCode() ^ this.f26784d.hashCode()) ^ this.f26785h.hashCode()) ^ super.hashCode();
    }
}
