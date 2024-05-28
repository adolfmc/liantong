package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.BooleanExprVisitor */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface BooleanExprVisitor {
    void visit(AttrEqualsExpr attrEqualsExpr) throws XPathException;

    void visit(AttrExistsExpr attrExistsExpr) throws XPathException;

    void visit(AttrGreaterExpr attrGreaterExpr) throws XPathException;

    void visit(AttrLessExpr attrLessExpr) throws XPathException;

    void visit(AttrNotEqualsExpr attrNotEqualsExpr) throws XPathException;

    void visit(PositionEqualsExpr positionEqualsExpr) throws XPathException;

    void visit(TextEqualsExpr textEqualsExpr) throws XPathException;

    void visit(TextExistsExpr textExistsExpr) throws XPathException;

    void visit(TextNotEqualsExpr textNotEqualsExpr) throws XPathException;

    void visit(TrueExpr trueExpr);
}
