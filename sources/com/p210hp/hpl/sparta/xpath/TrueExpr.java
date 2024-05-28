package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.TrueExpr */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TrueExpr extends BooleanExpr {
    static final TrueExpr INSTANCE = new TrueExpr();

    public String toString() {
        return "";
    }

    private TrueExpr() {
    }

    @Override // com.p210hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) {
        booleanExprVisitor.visit(this);
    }
}
