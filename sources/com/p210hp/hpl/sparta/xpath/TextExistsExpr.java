package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.TextExistsExpr */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TextExistsExpr extends BooleanExpr {
    static final TextExistsExpr INSTANCE = new TextExistsExpr();

    public String toString() {
        return "[text()]";
    }

    private TextExistsExpr() {
    }

    @Override // com.p210hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }
}
