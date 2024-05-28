package com.p210hp.hpl.sparta.xpath;

/* renamed from: com.hp.hpl.sparta.xpath.AttrExistsExpr */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AttrExistsExpr extends AttrExpr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AttrExistsExpr(String str) {
        super(str);
    }

    @Override // com.p210hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    @Override // com.p210hp.hpl.sparta.xpath.AttrExpr
    public String toString() {
        return "[" + super.toString() + "]";
    }
}
