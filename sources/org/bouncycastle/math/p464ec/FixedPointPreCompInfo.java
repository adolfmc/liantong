package org.bouncycastle.math.p464ec;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.FixedPointPreCompInfo */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class FixedPointPreCompInfo implements PreCompInfo {
    protected ECPoint offset = null;
    protected ECLookupTable lookupTable = null;
    protected int width = -1;

    public ECLookupTable getLookupTable() {
        return this.lookupTable;
    }

    public ECPoint getOffset() {
        return this.offset;
    }

    public int getWidth() {
        return this.width;
    }

    public void setLookupTable(ECLookupTable eCLookupTable) {
        this.lookupTable = eCLookupTable;
    }

    public void setOffset(ECPoint eCPoint) {
        this.offset = eCPoint;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
