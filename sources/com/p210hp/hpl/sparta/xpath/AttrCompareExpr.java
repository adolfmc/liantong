package com.p210hp.hpl.sparta.xpath;

import com.p210hp.hpl.sparta.Sparta;

/* renamed from: com.hp.hpl.sparta.xpath.AttrCompareExpr */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AttrCompareExpr extends AttrExpr {
    private final String attrValue_;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrCompareExpr(String str, String str2) {
        super(str);
        this.attrValue_ = Sparta.intern(str2);
    }

    public String getAttrValue() {
        return this.attrValue_;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String toString(String str) {
        return "[" + super.toString() + str + "'" + this.attrValue_ + "']";
    }
}
