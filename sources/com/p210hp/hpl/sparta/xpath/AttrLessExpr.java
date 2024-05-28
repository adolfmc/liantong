package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.AttrLessExpr */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AttrLessExpr extends AttrRelationalExpr {
    public AttrLessExpr(String str, int i) {
        super(str, i);
    }

    @Override // com.p210hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    @Override // com.p210hp.hpl.sparta.xpath.AttrExpr
    public String toString() {
        return toString("<");
    }
}
