package org.bouncycastle.crypto.p458ec;

import org.bouncycastle.math.p464ec.ECPoint;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.crypto.ec.ECPair */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class ECPair {

    /* renamed from: x */
    private final ECPoint f26551x;

    /* renamed from: y */
    private final ECPoint f26552y;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f26551x = eCPoint;
        this.f26552y = eCPoint2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public ECPoint getX() {
        return this.f26551x;
    }

    public ECPoint getY() {
        return this.f26552y;
    }

    public int hashCode() {
        return this.f26551x.hashCode() + (this.f26552y.hashCode() * 37);
    }
}
