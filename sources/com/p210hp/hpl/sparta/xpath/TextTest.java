package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.TextTest */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class TextTest extends NodeTest {
    static final TextTest INSTANCE = new TextTest();

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return true;
    }

    public String toString() {
        return "text()";
    }

    private TextTest() {
    }

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) throws XPathException {
        visitor.visit(this);
    }
}
