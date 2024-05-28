package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.TextEqualsExpr */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TextEqualsExpr extends TextCompareExpr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public TextEqualsExpr(String str) {
        super(str);
    }

    @Override // com.p210hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    public String toString() {
        return toString("=");
    }
}
