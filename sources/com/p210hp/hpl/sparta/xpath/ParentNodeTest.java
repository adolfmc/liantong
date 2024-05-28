package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.ParentNodeTest */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ParentNodeTest extends NodeTest {
    static final ParentNodeTest INSTANCE = new ParentNodeTest();

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return "..";
    }

    private ParentNodeTest() {
    }

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) throws XPathException {
        visitor.visit(this);
    }
}
