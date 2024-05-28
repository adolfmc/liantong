package com.p210hp.hpl.sparta.xpath;

/* renamed from: com.hp.hpl.sparta.xpath.PositionEqualsExpr */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PositionEqualsExpr extends BooleanExpr {
    private final int position_;

    public PositionEqualsExpr(int i) {
        this.position_ = i;
    }

    @Override // com.p210hp.hpl.sparta.xpath.BooleanExpr
    public void accept(BooleanExprVisitor booleanExprVisitor) throws XPathException {
        booleanExprVisitor.visit(this);
    }

    public int getPosition() {
        return this.position_;
    }

    public String toString() {
        return "[" + this.position_ + "]";
    }
}
