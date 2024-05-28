package org.bouncycastle.math.p464ec;

import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.WTauNafPreCompInfo */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class WTauNafPreCompInfo implements PreCompInfo {
    protected ECPoint.AbstractF2m[] preComp = null;

    public ECPoint.AbstractF2m[] getPreComp() {
        return this.preComp;
    }

    public void setPreComp(ECPoint.AbstractF2m[] abstractF2mArr) {
        this.preComp = abstractF2mArr;
    }
}
