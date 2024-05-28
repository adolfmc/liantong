package com.p210hp.hpl.sparta.xpath;

/* renamed from: com.hp.hpl.sparta.xpath.AttrRelationalExpr */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AttrRelationalExpr extends AttrExpr {
    private final int attrValue_;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrRelationalExpr(String str, int i) {
        super(str);
        this.attrValue_ = i;
    }

    public double getAttrValue() {
        return this.attrValue_;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String toString(String str) {
        return "[" + super.toString() + str + "'" + this.attrValue_ + "']";
    }
}
